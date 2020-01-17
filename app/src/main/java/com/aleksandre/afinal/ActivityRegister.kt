package com.aleksandre.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class ActivityRegister : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        signupBtn.setOnClickListener { signUp() }

    }

    private fun signUp()
    {
        val email = signupEmail.text.toString()
        val password = signupPassword.text.toString()
        val repeatPassword = signupRepeatPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty() && repeatPassword.isNotEmpty())
        {
           if(password == repeatPassword)
           {
               auth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener {
                       if(it.isSuccessful)
                       {
                           Toast.makeText(this, "რეგისტრაცია დასრულდა წარმატებით.", Toast.LENGTH_LONG).show()
                           startActivity(Intent(this, ActivityLogin::class.java))
                           finish()
                       } else {
                           Toast.makeText(this, it.result.toString(), Toast.LENGTH_LONG).show()
                       }
                   }
           } else {
               Toast.makeText(this, "პაროლები ერთმანეთს არ ემთხვევა!", Toast.LENGTH_LONG).show()
           }
        } else {
            Toast.makeText(this, "ზემოთ მოცემული ველები სავალდებულოა!", Toast.LENGTH_LONG).show()
        }
    }
}
