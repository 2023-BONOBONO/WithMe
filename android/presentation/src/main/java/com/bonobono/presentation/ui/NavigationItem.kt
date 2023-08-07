package com.bonobono.presentation.ui

import androidx.annotation.DrawableRes
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_FREE
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_REPORT
import com.bonobono.presentation.ui.NavigationRouteName.COMMUNITY_WITH
import com.bonobono.presentation.ui.NavigationRouteName.DEEP_LINK_SCHEME
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_CHATTING
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_COMMUNITY
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_HOME
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_MAP
import com.bonobono.presentation.ui.NavigationRouteName.MAIN_MY_PAGE

sealed class MainNav(
    override val route: String,
    @DrawableRes val icon: Int,
    override val title: String) : Destination
{
    object Home : MainNav(MAIN_HOME, R.drawable.ic_home, NavigationTitle.MAIN_HOME)
    object Map : MainNav(MAIN_MAP, R.drawable.ic_map, NavigationTitle.MAIN_MAP)
    object Community : MainNav(MAIN_COMMUNITY, R.drawable.ic_community, NavigationTitle.MAIN_COMMUNITY)
    object Chatting : MainNav(MAIN_CHATTING, R.drawable.ic_chat, NavigationTitle.MAIN_CHATTING)
    object MyPage : MainNav(MAIN_MY_PAGE, R.drawable.ic_profile, NavigationTitle.MAIN_MY_PAGE)

    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
    )

    companion object {
        fun isMainRoute(route: String?): Boolean {
            return when (route) {
                MAIN_HOME, MAIN_MAP, MAIN_CHATTING, MAIN_COMMUNITY, MAIN_MY_PAGE,
                COMMUNITY_REPORT, COMMUNITY_WITH, COMMUNITY_FREE-> true
                else -> false
            }
        }
    }
}

object MissionNav : Destination {
    override val route: String = NavigationRouteName.MISSION
    override val title: String = NavigationTitle.MISSION
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
    )
}

object EncyclopediaNav : Destination {
    override val route: String = NavigationRouteName.ENCYCLOPEDIA
    override val title: String = NavigationTitle.ENCYCLOPEDIA
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
    )
}

object NoticeNav : Destination {
    override val route: String = NavigationRouteName.NOTICE
    override val title: String = NavigationTitle.NOTICE
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
    )
}

object CommunityFreeNav : Destination {
    override val route: String = NavigationRouteName.COMMUNITY_FREE
    override val title: String = NavigationTitle.COMMUNITY_FREE
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${route}" }
    )
}

object CommunityWithNav : Destination {
    override val route: String = NavigationRouteName.COMMUNITY_WITH
    override val title: String = NavigationTitle.COMMUNITY_WITH
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${route}" }
    )
}

object CommunityReportNav : Destination {
    override val route: String = NavigationRouteName.COMMUNITY_REPORT
    override val title: String = NavigationTitle.COMMUNITY_REPORT
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${route}" }
    )
}

object BoardDetailNav : Destination {
    override val route: String = NavigationRouteName.BOARD_DETAIL
    override val title: String = ""
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${route}" }
    )
}

object CameraNav : Destination {
    override val route: String = NavigationRouteName.CAMERA
    override val title: String = NavigationTitle.CAMERA
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${NoticeNav.route}" }
    )
}

object SettingNav : Destination {
    override val route: String = NavigationRouteName.SETTING
    override val title: String = NavigationTitle.SETTING
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$MAIN_MY_PAGE" }
    )
}

object ProfileEditNav : Destination {
    override val route: String = NavigationRouteName.PROFILE_EDIT
    override val title: String = NavigationTitle.PROFILE_EDIT
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$MAIN_MY_PAGE" })
}

object PointStoreNav : Destination {
    override val route: String = NavigationRouteName.POINT_STORE
    override val title: String = NavigationTitle.POINT_STORE
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$MAIN_MY_PAGE" })
}

object FindIdNav : Destination {
    override val route: String = NavigationRouteName.FIND_ID
    override val title: String = NavigationTitle.FIND_ID
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${LoginNav.route}" })
}

object FindPasswordNav : Destination {
    override val route: String = NavigationRouteName.FIND_PASSWORD
    override val title: String = NavigationTitle.FIND_PASSWORD
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${LoginNav.route}" })
}

object LoginNav : Destination {
    override val route: String = NavigationRouteName.LOGIN
    override val title: String = NavigationTitle.LOGIN
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME.$route" })
}
object JoinNav : Destination {
    override val route: String = NavigationRouteName.JOIN
    override val title: String = NavigationTitle.JOIN
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${LoginNav.route}" })
}

object ChattingEditNav : Destination {
    override val route: String = NavigationRouteName.CHATTING_EDIT
    override val title: String = NavigationTitle.CHATTING_EDIT
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${MainNav.Chatting.route}" })
}

object ChattingNav : Destination {
    override val route: String = NavigationRouteName.CHATTING
    override val title: String = NavigationTitle.CHATTING
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME${MainNav.Chatting.route}" })
}

interface Destination {
    val route: String
    val title: String
    val deepLinks: List<NavDeepLink>
}

object NavigationRouteName {
    const val DEEP_LINK_SCHEME = "bonobono://"
    const val MAIN_HOME = "main_home"
    const val MAIN_COMMUNITY = "main_community"
    const val MAIN_MY_PAGE = "main_my_page"
    const val MAIN_CHATTING = "main_chat"
    const val MAIN_MAP = "main_map"

    const val MISSION = "mission"
    const val ENCYCLOPEDIA = "encyclopedia"
    const val NOTICE = "notice"

    // Community
    const val COMMUNITY_FREE = "free_community"
    const val COMMUNITY_WITH = "with_community"
    const val COMMUNITY_REPORT = "report_community"
    // FAB Route
    const val COMMUNITY_POST = "write_free"
    const val COMMUNITY_POST_WITH = "write_with"
    const val COMMUNITY_POST_REPORT = "write_report"
    // Gallery Route
    const val GALLERY = "gallery"
    const val GALLERY_WITH = "gallery_with"
    const val GALLERY_REPORT = "gallery_report"
    // Post Detail
    const val BOARD_DETAIL = "board_detail"

    const val CAMERA = "camera"

    const val SETTING = "setting"
    const val PROFILE_EDIT = "profile_edit"
    const val POINT_STORE = "point_store"

    const val LOGIN = "login"
    const val JOIN = "join"
    const val FIND_ID = "find_id"
    const val FIND_PASSWORD = "find_password"

    const val CHATTING_EDIT = "chatting_edit"
    const val CHATTING = "chatting"
}

object NavigationTitle {
    const val MAIN_HOME = "홈"
    const val MAIN_MAP = "지도"
    const val MAIN_CHATTING = "채팅"
    const val MAIN_COMMUNITY = "게시판"
    const val MAIN_MY_PAGE = "프로필"

    const val COMMUNITY_FREE = "자유게시판"
    const val COMMUNITY_WITH = "함께게시판"
    const val COMMUNITY_REPORT = "신고게시판"

    const val MISSION = "미션"
    const val ENCYCLOPEDIA = "도감"
    const val NOTICE = "공지"

    const val CAMERA = "카메라"

    const val SETTING = "설정"
    const val PROFILE_EDIT = "회원정보 수정"
    const val POINT_STORE = "포인트 상점"

    const val LOGIN = "로그인"
    const val JOIN = "회원가입"
    const val FIND_ID = "아이디 찾기"
    const val FIND_PASSWORD = "비밀번호 찾기"

    const val CHATTING_EDIT = "편집"
    const val CHATTING = "채팅"
}