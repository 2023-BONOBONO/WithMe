package com.bonobono.presentation.ui.main.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.LightGray

@Composable
fun ProfilePhoto(profileImage: Int, modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = profileImage),
        contentDescription = "프로필 사진"
    )
}

@Composable
fun BlindProfilePhoto(image: Int) {
    Box {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .border(BorderStroke(1.dp, DarkGray), shape = CircleShape)
                .background(LightGray)
                .graphicsLayer {
                    alpha = 0.05f
                },
            painter = painterResource(id = image),
            contentDescription = "필터 프로필 사진"
        )
    }
}


@Composable
fun AnimatedProfile(profileImage: Int, source: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        BoxWithConstraints {
            LottieLoader(
                source = source,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
        ProfilePhoto(profileImage = profileImage, Modifier)
    }
}
