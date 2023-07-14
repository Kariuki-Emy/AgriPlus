package com.example.ecommerce5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class ProfileActivity : AppCompatActivity() {
    lateinit var imgprof: ImageView
    lateinit var tvname: TextView
    lateinit var cardacc: CardView
    lateinit var imgaccount: ImageView
    lateinit var  tvaccount: TextView
    lateinit var cardsec: CardView
    lateinit var tvsec: TextView
    lateinit var imgsec: ImageView
    lateinit var cardhelp: CardView
    lateinit var tvhelp: TextView
    lateinit var imghelp: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        imgprof =findViewById(R.id.imgprof)
        tvname=findViewById(R.id.tvname)
        cardacc=findViewById(R.id.cardaccount)
        tvaccount=findViewById(R.id.tvAccount)
        imgaccount=findViewById(R.id.imgviewaccount)
        cardsec=findViewById(R.id.cardsec)
        tvsec=findViewById(R.id.tvSecurity)
        imgsec=findViewById(R.id.imgviewsecurity)
        cardhelp=findViewById(R.id.cardhelp)
        tvhelp=findViewById(R.id.tvHelp)
        imghelp=findViewById(R.id.imgviewhelp)




        val textView = findViewById<TextView>(R.id.tvname)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val userName = sharedPreferences.getString("User.name", "")

        textView.text = userName
    }
}