package com.bonobono.data.mapper

import com.bonobono.data.model.character.response.UserCharacterResponse
import com.bonobono.data.model.map.response.CampaignResponse
import com.bonobono.data.model.map.response.OurCharacterResponse
import com.bonobono.data.model.map.response.RankingResponse
import com.bonobono.domain.model.character.OurCharacter
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.domain.model.map.Campaign
import com.bonobono.domain.model.map.Ranking

fun UserCharacterResponse.toDomain(): UserCharacter {
    return UserCharacter(
        char_ord_id, createdDate, custom_name, description, experience, id, is_main, level, memberId
    )
}

fun RankingResponse.toDomain(): Ranking {
    return Ranking(count, nickname, profileImg)
}

fun CampaignResponse.toDomain(): Campaign {
    return Campaign(authority, completionStatus, endDate, locationName, name, startDate)
}

fun com.bonobono.data.model.character.response.OurCharacterResponse.toDomain() : OurCharacter {
    return OurCharacter(name, description, level)
}