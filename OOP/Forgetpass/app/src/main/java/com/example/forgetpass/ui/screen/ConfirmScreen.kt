package com.example.forgetpass.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.forgetpass.R
import com.example.forgetpass.viewmodel.ForgetPasswordViewModel


@Composable
fun ConfirmScreen(viewModel: ForgetPasswordViewModel, onBack: () -> Unit) {
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
        Text("Confirm", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("We are here to help you!")
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(value = viewModel.email, onValueChange = {}, label = { Text("Email") }, enabled = false)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(value = viewModel.code, onValueChange = {}, label = { Text("Code") }, enabled = false)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(value = viewModel.password, onValueChange = {}, label = { Text("Password") }, enabled = false)

        Spacer(Modifier.height(24.dp))
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(
            0xFF074A8D
        )
        )) {
            Text("Submit")
        }
    }
}
