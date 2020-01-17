package com.aleksandre.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        checkLogin()
        loginBtn.setOnClickListener { logIn() }
        gosignup.setOnClickListener { startActivity(Intent(this, ActivityRegister::class.java)) }
    }

    private fun logIn()
    {
        val email = loginEmail.text.toString()
        val password = loginPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty())
        {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(this, "ავტორიზაცია დასრულდა წარმატებით.", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            Toast.makeText(this, "ზემოთ მოცემული ველები სავალდებულოა!", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkLogin()
    {
        if(auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
