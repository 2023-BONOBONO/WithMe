package com.bonobono.presentation.ui.main.ecyclopedia

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bonobono.domain.model.character.OurCharacter
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.button.PrimaryButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.main.component.AnimatedProfile
import com.bonobono.presentation.ui.main.component.BlindProfilePhoto
import com.bonobono.presentation.ui.main.component.ProfilePhoto
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.utils.characterList
import com.bonobono.presentation.viewmodel.CharacterViewModel
import com.bonobono.presentation.viewmodel.MissionViewModel

// 현재 대표 동물 이미지로
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncyclopediaScreen(characterViewModel: CharacterViewModel = hiltViewModel(), missionViewModel: MissionViewModel = hiltViewModel()) {
    val mainId = missionViewModel.getCompletedTime(Constants.MAIN_CHARACTER)
    LaunchedEffect(Unit) {
        characterViewModel.getUserCharacterList()
        characterViewModel.getOurCharacterList()
    }
    val userCharacterList by characterViewModel.userCharacterList.collectAsState()
    val ourCharacterList by characterViewModel.ourCharacterList.collectAsState()
    var selectedIdx = remember {
        mutableStateOf(mainId)
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier) {
            AnimatedProfile(
                profileImage = R.drawable.beluga_whale,
                source = R.raw.animation_card
            )
            ElevatedFilterChip(
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.TopEnd),
                selected = false,
                onClick = {
                },
                label = {
                    Text(
                        text = "대표 캐릭터 설정",
                        style = CustomTextStyle.gameGuideTextStyle
                    )
                })
        }
        CurInformation(selectedIdx, userCharacterList, ourCharacterList)
        Spacer(modifier = Modifier.size(12.dp))
        UserCharacters(userCharacterList)
        OurCharacters(ourCharacterList)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurInformation(selectedId: MutableState<Long>, userCharacterList: List<UserCharacter>, ourCharacterList: List<OurCharacter>) {
    selectedId.value = 1
    var cur: UserCharacter = userCharacterList.find { it.id.toLong() == selectedId.value } ?: UserCharacter()
    val selectedCharacter = remember { cur }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_pixel_chat),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "${cur.custom_name}  ${cur.level} \nExp: ${cur.experience}\n${cur.description}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun UserCharacters(userCharacterList: List<UserCharacter>) {
    Card(
        modifier = Modifier.padding(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(80.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.heightIn(max = 700.dp).wrapContentHeight(),
            userScrollEnabled = false
        ) {
            // 보유중 / 아닌 것들 나눠서 표시
            items(userCharacterList) {
                ProfilePhoto(
                    profileImage = characterList[it.id].icon, modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(LightGray)
                        .border(BorderStroke(1.dp, DarkGray), shape = CircleShape)
                )
            }
        }
    }
}

@Composable
fun OurCharacters(ourCharacterList: List<OurCharacter>) {
    Card(
        modifier = Modifier.padding(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.heightIn(max = 700.dp).wrapContentHeight(),
            userScrollEnabled = false
        ) {
            // 보유중 / 아닌 것들 나눠서 표시
            items(ourCharacterList) { item ->
                characterList.find { it.name == item.name }?.let {
                    BlindProfilePhoto( it.icon ) {

                    }
                }
            }
        }
    }
}