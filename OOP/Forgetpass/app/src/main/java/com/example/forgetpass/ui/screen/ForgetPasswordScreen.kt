package com.example.forgetpass.ui.screen

import com.example.forgetpass.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.forgetpass.viewmodel.ForgetPasswordViewModel


@Composable
fun ForgetPasswordScreen(viewModel: ForgetPasswordViewModel, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource (R.drawable.img_uth),
            contentDescription = "Forget Password Image",
            modifier = Modifier.size(150.dp)
        )
        Text("Forget Password?", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Enter your Email, we will send you a verification code.")
        Spacer(Modifier.height(24.dp))



        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("Your Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))

        Button(onClick = onNext, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(
            0xFF074A8D
        )
        )) {
            Text("Next")
        }
    }
}
