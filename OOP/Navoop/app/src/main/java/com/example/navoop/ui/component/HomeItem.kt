package com.example.navoop.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navoop.data.model.OnboardingPage

@Composable
fun HomeScreen(
    navController: NavController,
    page: OnboardingPage
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Ảnh minh họa
            Image(
                painter = painterResource(id = page.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(0.dp))

            // Tiêu đề
            Text(
                text = page.title,
                fontSize = 25.sp,
                color = Color(0xFF3F51B5),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

//            Spacer(modifier = Modifier.height(12.dp))
//
//            // Hàng chứa 2 nút (Back - Next)
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                horizontalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                // Nút Back (1/3)
//                OutlinedButton(
//                    onClick = onBackClick,
//                    shape = RoundedCornerShape(30.dp),
//                    modifier = Modifier
//                        .weight(1f)
//                        .fillMaxHeight()
//                ) {
//                    Text("Back", fontSize = 16.sp)
//                }
//
//                // Nút Next (2/3)
//                Button(
//                    onClick = onNextClick,
//                    shape = RoundedCornerShape(30.dp),
//                    modifier = Modifier
//                        .weight(2f)
//                        .fillMaxHeight()
//                ) {
//                    Text("Next", fontSize = 16.sp)
//                }
//            }
//        }
//
//        // Skip góc phải trên
//        TextButton(
//            onClick = onSkipClick,
//            modifier = Modifier.align(Alignment.TopEnd)
//        ) {
//            Text("Skip")
//        }
        }
    }
}
