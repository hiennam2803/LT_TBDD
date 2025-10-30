package com.example.mailsign

import android.os.Bundle
import android.service.credentials.GetCredentialRequest
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    // Instantiate a Google sign-in request
    val googleIdOption = GetGoogleIdOption.Builder()
        // Your server's client ID, not your Android client ID.
        .setServerClientId(getString(R.string.default_web_client_id))
        // Only show accounts previously used to sign in.
        .setFilterByAuthorizedAccounts(true)
        .build()

    // Create the Credential Manager request
    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()
}