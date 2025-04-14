package com.example.bt2_tuan5.viewmodel

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _navigateToProfile = MutableStateFlow(false)
    val navigateToProfile: StateFlow<Boolean> = _navigateToProfile

    init {
        if (firebaseAuth.currentUser != null) {
            _navigateToProfile.value = true
        }
    }

    fun signInWithGoogle(
        activity: Activity,
        oneTapClient: SignInClient,
        launcher: (IntentSenderRequest) -> Unit
    ) {
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(activity.getString(com.example.bt2_tuan5.R.string.default_web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(activity) { result ->
                val intentSenderRequest = IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
                launcher(intentSenderRequest)
            }
            .addOnFailureListener { e ->
                Log.e("LoginViewModel", "Đăng nhập Google thất bại", e)
            }
    }

    fun handleSignInResult(data: Intent?, oneTapClient: SignInClient) {
        try {
            val credential = oneTapClient.getSignInCredentialFromIntent(data)
            val idToken = credential.googleIdToken
            if (idToken != null) {
                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                firebaseAuth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _navigateToProfile.value = true
                        } else {
                            Log.e("LoginViewModel", "Đăng nhập Firebase thất bại", task.exception)
                        }
                    }
            }
        } catch (e: Exception) {
            Log.e("LoginViewModel", "Xử lý kết quả đăng nhập thất bại", e)
        }
    }

    fun resetNavigation() {
        _navigateToProfile.value = false
    }
}