package com.example.ecommerce5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    lateinit var Txt_gotoreg: TextView
    lateinit var EdteMail: EditText
    lateinit var EdtPass: EditText
    lateinit var Btnlogin: Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Txt_gotoreg=findViewById(R.id.Tv_login)
        EdteMail=findViewById(R.id.EdtlogEmail)
        EdtPass=findViewById(R.id.EdtlogPass)
        Btnlogin=findViewById(R.id.btn_login)
        auth=FirebaseAuth.getInstance()

        Txt_gotoreg.setOnClickListener {
            val intent= Intent(this, Registration::class.java)
            startActivity(intent)
        }
        Btnlogin.setOnClickListener {
            val home= Intent(this, MainActivity::class.java)
            startActivity(home)
            login()
        }
    }
    private fun login(){
        val email=EdteMail.text.toString()
        val pass=EdtPass.text.toString()
        if (email.isBlank()||pass.isBlank()){
            Toast.makeText(this,"Password and email can't be blank!", Toast.LENGTH_LONG).show()
            return
        }
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this) {
            if (it.isSuccessful){
                Toast.makeText(this, "Successfully logged in", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Log in failed", Toast.LENGTH_LONG).show()
            }
        }
    }

}