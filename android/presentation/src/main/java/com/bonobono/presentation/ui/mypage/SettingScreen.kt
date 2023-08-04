package com.bonobono.presentation.ui.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.topbar.screen.SettingScreen
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.LightGray_50
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.TextGray
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun SettingScreen(
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        SettingScreen.buttons
            .onEach { button ->
                when (button) {
                    SettingScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        SettingTitle(title = "계정 관리")
        SettingOptions(option = "권한설정", color = LightGray)
        SettingOptions(option = "비밀번호", color = LightGray)
        SettingOptions(option = "회원탈퇴", color = Red)
        Spacer(modifier = Modifier.height(4.dp))
        SettingDivider()
        Spacer(modifier = Modifier.height(4.dp))
        SettingTitle(title = "앱 정보")
        SettingVersionOption(version = "1.00")
        SettingOptions(option = "라이선스", color = LightGray)
        Spacer(modifier = Modifier.height(4.dp))
        SettingDivider()
        Spacer(modifier = Modifier.height(4.dp))
    }
}
@Composable
fun SettingTitle(title: String) {
    Text(title,
        color = TextGray,
        modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp))
}


@Composable
fun SettingOptions(
    option: String,
    color : Color) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (optionStr, rightButton) = createRefs()
        Text(option,
            modifier = Modifier.constrainAs(optionStr) {
                linkTo(parent.top, parent.bottom)
                start.linkTo(parent.start)
            })
        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(rightButton) {
                linkTo(parent.top, parent.bottom)
                end.linkTo(parent.end)
            },) {
            Icon(
                modifier = Modifier.size(20.dp, 20.dp),
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = "rightBtn"
            )
        }
    }
}

@Composable
fun SettingVersionOption(
    version:String
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
        val (versionStr, versionInfoStr) = createRefs()
        Text("버전정보",
            modifier = Modifier.constrainAs(versionStr) {
                linkTo(parent.top, parent.bottom)
                start.linkTo(parent.start)
            })
        Text(version,
            modifier = Modifier.constrainAs(versionInfoStr) {
                linkTo(parent.top, parent.bottom)
                end.linkTo(parent.end)
            })
    }
}

@Composable
fun SettingDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(0.5.dp),
        color = LightGray_50
    )
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingOptions(option = "비밀번호", color = LightGray)
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview2() {
    SettingVersionOption(version = "1.00")
}