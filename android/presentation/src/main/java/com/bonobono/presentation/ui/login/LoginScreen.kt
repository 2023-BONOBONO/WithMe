package com.bonobono.presentation.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.FindIdNav
import com.bonobono.presentation.ui.FindPasswordNav
import com.bonobono.presentation.ui.JoinNav
import com.bonobono.presentation.ui.common.BasicTextField
import com.bonobono.presentation.ui.common.button.PrimaryColorButton
import com.bonobono.presentation.ui.common.text.CustomTextStyle.appNameText
import com.bonobono.presentation.ui.common.text.CustomTextStyle.loginDarkGrayText
import com.bonobono.presentation.ui.login.view.LoginTextButton
import com.bonobono.presentation.ui.login.view.SNSButton
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

private const val TAG = "LoginScreen"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.bonobono_app_name),
                style = appNameText
            )
            Spacer(modifier = Modifier.height(32.dp))
            BasicTextField(
                value = viewModel.username,
                hint = "아이디",
                keyboardType = KeyboardType.Text
            ) { userName -> viewModel.updateUserName(userName) }
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = viewModel.password,
                hint = "비밀번호",
                keyboardType = KeyboardType.Password
            ) { password -> viewModel.updatePassword(password) }
            Spacer(modifier = Modifier.height(16.dp))
            AutoLogin(viewModel)
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryColorButton(
                text = R.string.login_login,
                enabled = true,
                backgroundColor = PrimaryBlue,
                action = {
                    Log.d(TAG, "LoginScreen: ${viewModel.username} ${viewModel.password}")

                        coroutineScope.launch {
                            if (viewModel.username.isBlank() || viewModel.password.isBlank()) {
                                snackbarHostState.showSnackbar(
                                    "아이디 비밀번호 입력 값을 모두 기입하세요"
                                )
                            } else {
                                viewModel.login()
                                navController.navigate("main_screen")
                            }
                        }
                })
            Spacer(modifier = Modifier.height(16.dp))
            LoginHelpOptions(navController)
            Spacer(modifier = Modifier.height(60.dp))
            SNSLoginGuide()
            Spacer(modifier = Modifier.height(12.dp))
            SNSLoginButtons()
        }
    }
}


@Composable
fun AutoLogin(viewModel: LoginViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = viewModel.autoLoginState,
            colors = CheckboxDefaults.colors(
                checkedColor = PrimaryBlue,
                uncheckedColor = LightGray
            ),
            modifier = Modifier.size(24.dp),
            onCheckedChange = { isChecked ->
                viewModel.updateAutoLoginState(isChecked)
            })
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "로그인 상태 유지",
            style = loginDarkGrayText
        )
    }
}

@Composable
fun LoginHelpOptions(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        LoginTextButton(text = "아이디 찾기", FindIdNav.route, navController)
        LoginHelpOptionDivider()
        LoginTextButton(text = "비밀번호 찾기", FindPasswordNav.route, navController)
        LoginHelpOptionDivider()
        LoginTextButton(text = "회원가입", JoinNav.route, navController)
    }
}

@Composable
fun LoginHelpOptionDivider() {
    Spacer(modifier = Modifier.width(8.dp))
    Divider(
        color = Color.LightGray,
        modifier = Modifier
            .height(20.dp)
            .width(1.dp)
    )
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun SNSLoginGuide() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "SNS 계정으로 로그인",
            style = loginDarkGrayText
        )
        Spacer(modifier = Modifier.width(8.dp))
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
fun SNSLoginButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SNSButton(img = R.drawable.ic_google, contentDescription = "google") {

        }
        Spacer(modifier = Modifier.width(20.dp))
        SNSButton(img = R.drawable.ic_kakao, contentDescription = "kakao") {

        }
        Spacer(modifier = Modifier.width(20.dp))
        SNSButton(img = R.drawable.ic_naver, contentDescription = "naver") {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}