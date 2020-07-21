package com.jamestrada.chatwithjames.registerlogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jamestrada.chatwithjames.R
import com.jamestrada.chatwithjames.messages.LatestMessagesActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()

            Log.d("Login", "Attempt login with email/pw: $email/***")

            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("LoginActivity", "Successfully logged user with uid: ${it.result?.user?.uid}")
                        val intent = Intent(this, LatestMessagesActivity::class.java)
                        // clear off all previous activities on the stack so when user presses back button goes to home screen and not register/login screen of the app
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
                .addOnFailureListener {
                    Log.d("LoginActivity", "Failed to log user: ${it.message}")
                }
        }

        back_to_register_textview.setOnClickListener {
            finish()
        }
    }
}