package com.bonobono.backend.chatting.service;

import com.bonobono.backend.chatting.config.MyServer;
import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.chatting.repository.ChatMessageRepository;
import com.bonobono.backend.chatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    //메시지 저장
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;


    public String save(Long id) {
        //채팅방 정보 불러오기
        ChatRoom chatRoom =  chatRoomRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("채팅방이 존재하지 않습니다"+id));

        try {
            MyServer chatServer = new MyServer();
            chatServer.start(chatRoom);
            //messagerequest dto에 세팅하기
        }catch(IOException e) {
            System.out.println("[서버] " + e.getMessage());
        }
        //message객체를 만듬
        //repository.save()


        return "hello";
//        return this.chatMessageRepository.save(requestDto.toEntity()).getId();
    }
//        try {
//            MyServer chatServer = new MyServer();
//            chatServer.start();
//
//            Scanner scanner = new Scanner(System.in); //input값
//            while(true) {
//                String key = scanner.nextLine();
//                if(key.equals("q")) 	break;
//            }
//            scanner.close();
//            chatServer.stop();
//        } catch(IOException e) {
//            System.out.println("[서버] " + e.getMessage());
//        }
//        return this.chatMessageRepository.save(requestDto.toEntity()).getId();
    }

