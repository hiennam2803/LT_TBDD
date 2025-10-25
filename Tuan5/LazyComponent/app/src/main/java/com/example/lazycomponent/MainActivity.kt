package com.example.lazycomponent


import android.os.Bundle
import android.telecom.Call
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lazycomponent.ui.theme.LazyComponentTheme
import androidx.compose.ui.text.font.FontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyComponentTheme {
                NavigationApp()
            }
        }
    }
}


@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("lazycolumn") { LazyColumn(navController) }
        composable("detail") { Details(navController) }
    }
}


//Wellcome screen
@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_jpc),
                    contentDescription = "Navagation",
                    modifier = Modifier.size(120.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Navagation",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Is a framework that simplifies the implementation of navagation between different UI components (activities, fragments or composables) in an app",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        // Nút
        Button(
            onClick = {navController.navigate("LazyColumn") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Push", color = Color.White, fontSize = 16.sp)
        }
    }
}

//Lazy Column Screen
@Composable
fun LazyColumn(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
//            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            // Nút quay lại
            Text(
                text = "<",
                fontSize = 26.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .padding(end = 8.dp)
            )

            // Tiêu đề
            Text(
                text = "Lazy Column",
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp
            )
        }

        // Phần Display
        var n = 1000000;
        //Column bình thường
//        Column {
//            repeat(n) { i ->
//                ComponentCard(title = "Item $i", description = "Desc $i", navController, "texts")
//            }
//        }

        LazyColumn{
            repeat(n) { i ->
                item {
                    ComponentCard(
                        title = "Item $i",
                        description = "Desc $i",
                        navController,
                        "detail"
                    )
                }
            }
        }
    }

}

//@Composable
//fun SectionTitle(title: String) {
//    Text(
//        text = title,
//        fontSize = 18.sp,
//        fontWeight = FontWeight.SemiBold,
//        modifier = Modifier.padding(vertical = 8.dp)
//    )
//}

@Composable
fun ComponentCard(
    title: String,
    description: String,
    navController: NavController,
    route: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFBBDEFB), shape = RoundedCornerShape(8.dp))
            .clickable { navController.navigate(route) }
            .padding(12.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = description,
            fontSize = 14.sp
        )
    }

    Spacer(modifier = Modifier.height(8.dp))
}

//Detail Screen

@Composable
fun Details(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            // Nút quay lại
            Text(
                text = "<",
                fontSize = 26.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .padding(end = 8.dp)
            )

            // Tiêu đề
            Text(
                text = "Text Detail",
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp
            )
        }

        // Nội dung
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
//            Text(
//                buildAnnotatedString {
//                    append("The ")
//                    withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
//                        append("quick")
//                    }
//                    withStyle(style = SpanStyle(color = Color(0xFF8B4513), fontWeight = FontWeight.Bold)) {
//                        append(" Brown ")
//                    }
//                    append("fox\n")
//                    withStyle(style = SpanStyle()) {
//                        append("j u m p s ")
//                    }
//                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
//                        append("over ")
//                    }
//                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
//                        append("the")
//                    }
//                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, fontFamily = FontFamily.Cursive)) {
//                        append(" lazy ")
//                    }
//                    append("dog.")
//                },
//                fontSize = 30.sp,
//                textAlign = TextAlign.Center,
//                lineHeight = 50.sp
//            )
            // Nút
            Button(
                onClick = {navController.navigate("welcome") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),

                ) {
                Text(text = "Back to ROOT", color = Color.White, fontSize = 16.sp)
            }
        }

    }
}