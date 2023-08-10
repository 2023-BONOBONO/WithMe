package com.bonobono.backend.character.service;

import com.bonobono.backend.character.domain.LocationOurCharacter;
import com.bonobono.backend.character.dto.catchCharacter.NowPositionRequestDto;
import com.bonobono.backend.character.dto.catchCharacter.OurChacracterWithSeaResponseDto;
import com.bonobono.backend.character.repository.LocationOurCharacterRepository;
import com.bonobono.backend.dailymission.domain.OXQuizProblem;
import com.bonobono.backend.dailymission.dto.OXQuizResponseDto;
import com.bonobono.backend.dailymission.repository.OXQuizProblemRepository;
import com.bonobono.backend.global.exception.LocationOurChracterNotFoundException;
import com.bonobono.backend.location.entity.Location;
import com.bonobono.backend.location.repository.LocationRepository;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@RequiredArgsConstructor
@Service
public class OurCharacterService {

    private final LocationOurCharacterRepository locationOurCharacterRepository;
    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;
    private final OXQuizProblemRepository oxQuizProblemRepository;

    public List<OurChacracterWithSeaResponseDto> SeaOurFindList(NowPositionRequestDto nowPositionRequestDto) {
        //위경도로 해변위치 찾아온다
        Location location = locationRepository.findByName(nowPositionRequestDto.getName())
                .orElseThrow(()->new IllegalArgumentException("해당 해변이 존재하지 않습니다 +location"+ nowPositionRequestDto.getName()));

        //해변위치를 바탕으로 해변의 캐릭터들을 리스트로 받아옴
        List<LocationOurCharacter> locationOurCharacterList = locationOurCharacterRepository.findByLocation_id(location.getId());

        List<OurChacracterWithSeaResponseDto> bound = new ArrayList<>();
        if (!locationOurCharacterList.isEmpty()) {
            //리스트의 캐릭터의 하나하나의 위치를, 그 해변의 위경도를 중심으로 랜덤으로 지정해서  OurChacracterWithSeaResponseDto에 넣는다
            double leftLatitude = location.getLeftlatitude();
            double rightLatitude = location.getRightlatitude();
            double leftLongtitude = location.getLeftlongitude();
            double rightLongtitude = location.getRightlatitude();

            Random random = new Random();
            int character_count = 10;

            //캐릭터 id, 위도, 경도를 dto에 저장
            for (LocationOurCharacter locationOurCharacter : locationOurCharacterList) {
                double randomLatitude = leftLatitude + (rightLatitude - leftLatitude) * random.nextDouble();
                double randomLongitude = leftLongtitude + (rightLongtitude - leftLongtitude) * random.nextDouble();

                bound.add(new OurChacracterWithSeaResponseDto(randomLatitude, randomLongitude, locationOurCharacter));
            }
        }
        else {
            throw new LocationOurChracterNotFoundException("해당 해변에 캐릭터리스트가 없습니다 + 해변ID:" + location.getId());
        }

        return bound;
    }

    public OXQuizResponseDto ReturnQuiz(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당멤버가 존재하지 않습니다+id" + memberId));

        long qty = oxQuizProblemRepository.findAll().size();
        int idx = (int) (Math.random()*qty);

        Page<OXQuizProblem> oxquizPage = oxQuizProblemRepository.findAll(PageRequest.of(idx,1, Sort.by("id")));
        OXQuizProblem oxQuizProblem = null;

        if(oxquizPage.hasContent()) {
            oxQuizProblem=oxquizPage.getContent().get(0);
        } else {
            throw new IllegalStateException("ox퀴즈를 찾을 수 없습니다");
        }

        return new OXQuizResponseDto(oxQuizProblem.getAnswer(), oxQuizProblem.getProblem(), oxQuizProblem.getId(),oxQuizProblem.getCommentary());

    }
}
