package com.bonobono.data.repository.register

import android.util.Log
import com.bonobono.data.local.PreferenceDataSource
import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.RegisterService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.registration.LoginInput
import com.bonobono.domain.model.registration.LoginResult
import com.bonobono.domain.model.registration.Member
import com.bonobono.domain.model.registration.Password
import com.bonobono.domain.model.registration.Register
import com.bonobono.domain.model.registration.Token
import com.bonobono.domain.repository.registration.RegisterRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private const val TAG = "싸피"
class RegisterRepositoryImpl @Inject constructor(
    private val  preferenceDatasource: PreferenceDataSource,
    private val registerService: RegisterService
) : RegisterRepository {
    override suspend fun updateMember(member: Member): NetworkResult<Member> {
        return handleApi { registerService.updateMember(member).toDomain() }
    }

    override suspend fun updatePassword(password: Password): NetworkResult<Member> {
        return handleApi { registerService.passwordChange(password).toDomain() }
    }

    override suspend fun checkUserName(username: Member): NetworkResult<String> {
        return handleApi { registerService.checkUserName(username) }
    }

    override suspend fun checkNickName(nickname: Member): NetworkResult<String> {
        return handleApi { registerService.checkNickName(nickname) }
    }

    override suspend fun signUp(register: Register): NetworkResult<Member> {
        return handleApi { registerService.signUp(register).toDomain() }
    }

    override suspend fun login(loginInput: LoginInput) : NetworkResult<LoginResult> {
        Log.d(TAG, "login: fcmtoken ${loginInput.fcmtoken}")
        return handleApi { registerService.login(loginInput).toDomain() }
    }

    override suspend fun logout(): NetworkResult<String> {
        return handleApi { registerService.logout() }
    }

    override suspend fun reissue(token: Token): NetworkResult<Token> {
        return handleApi { registerService.reissue(token).toDomain() }
    }

    override suspend fun getMember(): NetworkResult<Member> {
        return handleApi { registerService.getMember().toDomain() }
    }

    override suspend fun deleteMember(): NetworkResult<String> {
        return handleApi { registerService.deleteMember() }
    }

    override suspend fun putLoginResult(loginResult: LoginResult) {
        preferenceDatasource.putString("access_token", loginResult.tokenDto.accessToken)
        preferenceDatasource.putString("refresh_token", loginResult.tokenDto.refreshToken)
        preferenceDatasource.putInt("member_id", loginResult.memberId)
        preferenceDatasource.putString("role", loginResult.role[0].role)
        Log.d(TAG, "putToken: access_token : ${preferenceDatasource.getString("access_token")} refresh_token : ${preferenceDatasource.getString("refresh_token")} member_id : ${preferenceDatasource.getInt("member_id")}\nrole: ${preferenceDatasource.getString("role")}")
    }

    override suspend fun putMemberInfo(member: Member) {
//        preferenceDatasource.putString("member_id", member)
    }

    override suspend fun putLoginInfo(loginInput: LoginInput) {
        preferenceDatasource.putString("username", loginInput.username)
        preferenceDatasource.putString("password", loginInput.password)
        preferenceDatasource.putString("fcmtoken", loginInput.fcmtoken)
    }

    override suspend fun getLoginInfo(): LoginInput {
        val fcmtoken = preferenceDatasource.getString("fcmtoken", "")
        val username = preferenceDatasource.getString("username", "")
        val password = preferenceDatasource.getString("password", "")
        return LoginInput(fcmtoken = fcmtoken!!, username = username!!, password = password!!)
    }

    override suspend fun getFcmToken(): String = suspendCoroutine { continuation ->
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d(TAG, "getFcmToken: Fetching FCM token failed", task.exception)
                    continuation.resume("")
                } else {
                    val token = task.result
                    Log.d(TAG, "getFcmToken: FCM token is $token")
                    continuation.resume(token ?: "")
                }
            }
    }
}