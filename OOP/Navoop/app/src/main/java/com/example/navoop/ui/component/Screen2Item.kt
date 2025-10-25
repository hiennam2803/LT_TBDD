package com.example.navoop.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navoop.data.model.OnboardingPage
import com.example.navoop.ui.screen.Screen2

@Composable
fun Screen2Item(
    navController: NavController.Companion,
    page: OnboardingPage,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding( top = 150.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Ảnh minh họa
            Image(
                painter = painterResource(id = page.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Tiêu đề
            Text(
                text = page.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Mô tả
            Text(
                text = page.description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f)) // đẩy nút xuống cuối

            // Hàng chứa 2 nút (Back - Next)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Nút Back (1/3)
//                OutlinedButton(
//                    onClick = onBackClick,
//                    shape = RoundedCornerShape(30.dp),
//                    modifier = Modifier
//                        .weight(1f)
//                        .fillMaxHeight()
//                ) {
//                    Text("Back", fontSize = 16.sp)
//                }

                // Nút Next (2/3)
                Button(
                    onClick = onNextClick,
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                ) {
                    Text("Next", fontSize = 16.sp)
                }
            }
        }

        // Skip góc phải trên
        TextButton(
            onClick = onSkipClick,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Text("Skip")
        }
    }
}
