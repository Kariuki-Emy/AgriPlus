package com.example.ecommerce5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    lateinit var mbtnCart: ImageButton
    lateinit var mtvttlmain: TextView
    lateinit var mimg1: ImageView
    lateinit var cdview: CardView
    lateinit var mimgpic1: ImageView
    lateinit var mImgpic2: ImageView
    lateinit var mimgpic3: ImageView
    lateinit var tvCart: TextView
    lateinit var cdviewpc1: CardView
    lateinit var cdviewpc2: CardView
    lateinit var cdviewpc3: CardView
    lateinit var mbtn_profile: ImageButton
    lateinit var productbtn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mbtnCart = findViewById(R.id.mbtn_cart)
        mtvttlmain = findViewById(R.id.tvtitlemain)
        mimg1 = findViewById(R.id.img1)
        cdview = findViewById(R.id.cdvw1)
        mimgpic1 = findViewById(R.id.imgviewpic1)
        mImgpic2 = findViewById(R.id.imgviewpic2)
        mimgpic3 = findViewById(R.id.imgviewpic3)
        tvCart = findViewById(R.id.tvcart)
        cdviewpc1 = findViewById(R.id.cardviewpic1)
        cdviewpc2 = findViewById(R.id.cardviewpic2)
        cdviewpc3 = findViewById(R.id.cardviewpic3)
        mbtn_profile=findViewById(R.id.mbtn_profile)
        productbtn = findViewById(R.id.mbtn_products)

        productbtn.setOnClickListener {
            var intent= Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }

        mbtnCart.setOnClickListener {
            var intent= Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        mbtn_profile.setOnClickListener{
            var intent= Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}