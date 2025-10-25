package com.example.jpc

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.jpc.ui.theme.JPCTheme
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JPCTheme {
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
        composable("components") { Components(navController) }
        composable("texts") { TextScreen(navController) }
        composable("images") { ImageScreen(navController) }
        composable("textfield") { TextfieldScreen(navController) }
        composable("passwordfield") { PasswordfieldScreen(navController) }
        composable("column") { ColumnScreen(navController) }
        composable("row") { RowScreen(navController) }
        composable("other") { OtherScreen(navController) }
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
                    contentDescription = "Jetpack Compose Logo",
                    modifier = Modifier.size(120.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Jetpack Compose",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        // Nút
        Button(
            onClick = {navController.navigate("components")},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "I’m ready", color = Color.White, fontSize = 16.sp)
        }
    }
}

//Component Screen
@Composable
fun Components(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tiêu đề chính
        Text(
            text = "UI Components List",
            color = Color(0xFF2196F3),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        // Phần Display
        SectionTitle(title = "Display")
        ComponentCard(title = "Text", description = "Displays text", navController, "texts")
        ComponentCard(title = "Image", description = "Displays an image", navController, "images")

        Spacer(modifier = Modifier.height(16.dp))

        // Phần Input
        SectionTitle(title = "Input")
        ComponentCard(title = "TextField", description = "Input field for text", navController, "textfield")
        ComponentCard(title = "PasswordField", description = "Input field for passwords", navController, "passwordfield")

        Spacer(modifier = Modifier.height(16.dp))

        // Phần Layout
        SectionTitle(title = "Layout")
        ComponentCard(title = "Column", description = "Arranges elements vertically", navController, "column")
        ComponentCard(title = "Row", description = "Arranges elements horizontally", navController, "row")

        Spacer(modifier = Modifier.height(16.dp))

        //Phần other
        SectionTitle(title = "Other Component")
        ComponentCard(title = "UI Component", description = "Checkbox, Selection, Choose one", navController, "other")
    }

}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

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

//Text Screen

@Composable
fun TextScreen(navController: NavController) {
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
            Text(
                buildAnnotatedString {
                    append("The ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append("quick")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF8B4513), fontWeight = FontWeight.Bold)) {
                        append(" Brown ")
                    }
                    append("fox\n")
                    withStyle(style = SpanStyle()) {
                        append("j u m p s ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("over ")
                    }
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("the")
                    }
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, fontFamily = FontFamily.Cursive)) {
                        append(" lazy ")
                    }
                    append("dog.")
                },
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )
        }
    }
}


//Image Screen

@Composable
fun ImageScreen(navController: NavController) {
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
                text = "Images",
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp
            )
        }

        AsyncImage(
            model = "https://cdn11.dienmaycholon.vn/filewebdmclnew/public/userupload/files/kien-thuc/diem-chuan-va-hoc-phi-truong-dai-hoc-giao-thong-van-tai-uth/diem-chuan-va-hoc-phi-truong-dai-hoc-giao-thong-van-tai-uth-1.jpg",
            contentDescription = "Ảnh từ mạng",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "https://cdn11.dienmaycholon.vn/filewebdmclnew/public/userupload/files/kien-thuc/diem-chuan-va-hoc-phi-truong-dai-hoc-giao-thong-van-tai-uth/diem-chuan-va-hoc-phi-truong-dai-hoc-giao-thong-van-tai-uth-1.jpg",
            fontSize = 14.sp,
            textDecoration = TextDecoration.LineThrough,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Ảnh trong app
        Image(
            painter = painterResource(id = R.drawable.img_uth),
            contentDescription = "Ảnh trong app",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "In app",
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

//textfield Screen

@Composable
fun TextfieldScreen(navController: NavController) {

    var text by remember { mutableStateOf("") }
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
                text = "Text field",
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Thông tin nhập") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = text.ifEmpty { "Tự động cập nhật dữ liệu theo textfield" },
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

//passwordfield Screen

@Composable
fun PasswordfieldScreen(navController: NavController) {

    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
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
                text = "Password field",
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Nhập mật khẩu") },
                visualTransformation = if (isPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    Text(
                        text = if (isPasswordVisible) "Ẩn" else "Hiện",
                        color = Color.Blue,
                        modifier = Modifier
                            .clickable { isPasswordVisible = !isPasswordVisible }
                            .padding(horizontal = 8.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

//Column

@Composable
fun ColumnScreen(navController: NavController) {

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
                text = "Column",
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // tạo 3 cột
            repeat(3) { colIndex ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // mỗi cột có 3 ô dọc
                    repeat(3) { rowIndex ->
                        val isHighlighted = (rowIndex == 1)
                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (isHighlighted) Color(0xFF4285F4)
                                    else Color(0xFF90CAF9)
                                )
                        )
                    }
                }
            }
        }
    }
}

//Row

@Composable
fun RowScreen(navController: NavController) {

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
                text = "Images",
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp
            )
        }

        repeat(5) { rowIndex ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(3) { colIndex ->
                    val isHighlighted = colIndex == 1
                    Box(
                        modifier = Modifier
                            .size(width = 90.dp, height = 60.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (isHighlighted) Color(0xFF4285F4)
                                else Color(0xFF90CAF9)
                            )
                    )
                }
            }
        }
    }
}


//Other Screen

@Composable
fun OtherScreen(navController: NavController) {
    var isChecked by remember { mutableStateOf(false) }
    var isSwitchOn by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Option 1") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Hàng tiêu đề
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "<",
                fontSize = 26.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .padding(end = 8.dp)
            )

            Text(
                text = "Others",
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp
            )
        }

        // Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Text(
                text = "Enable notifications",
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Switch
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Switch(
                checked = isSwitchOn,
                onCheckedChange = { isSwitchOn = it }
            )
            Text(
                text = if (isSwitchOn) "Dark Mode ON" else "Dark Mode OFF",
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Radio Buttons
        Text(
            text = "Choose an option:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        val options = listOf("Option 1", "Option 2", "Option 3")
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedOption = option }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { selectedOption = option }
                )
                Text(
                    text = option,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    JPCTheme {
//        Greeting()
//    }
//}