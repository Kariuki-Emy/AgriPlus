package com.example.ecommerce5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: MutableList<Product>
    private lateinit var database: FirebaseDatabase
    private lateinit var productsRef: DatabaseReference
    private lateinit var cartRef: DatabaseReference
    private lateinit var cartListener: ValueEventListener
    private lateinit var gotocart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        recyclerView = findViewById(R.id.recyclerView)
        gotocart=findViewById(R.id.gotocart)
        gotocart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }


        productList = mutableListOf(
            Product("https://firebasestorage.googleapis.com/v0/b/ecommerce5-2a331.appspot.com/o/watermelon.jpeg?alt=media&token=367a3b0f-4ade-4d6b-b9ca-2722bb8ac36a","Watermelon", 115.00 ),
            Product("https://firebasestorage.googleapis.com/v0/b/ecommerce5-2a331.appspot.com/o/banana.jpeg?alt=media&token=47dd5408-fec5-4b43-8acc-c423920640d7", "Banana", 15.00),
            Product("https://firebasestorage.googleapis.com/v0/b/ecommerce5-2a331.appspot.com/o/pear.jpeg?alt=media&token=9684bea2-5600-4444-afc4-e386cf5311c4", "Pear", 20.00),
            Product("https://firebasestorage.googleapis.com/v0/b/ecommerce5-2a331.appspot.com/o/spinach.jpeg?alt=media&token=eafbe512-748a-4bfa-a01b-6ee35d8822eb","Spinach", 50.00 ),
            Product("https://firebasestorage.googleapis.com/v0/b/ecommerce5-2a331.appspot.com/o/beans.jpeg?alt=media&token=4e152f08-29f8-4619-85c3-b140355f4578","Beans", 60.00  ),
            Product("https://firebasestorage.googleapis.com/v0/b/ecommerce5-2a331.appspot.com/o/brocoli.jpeg?alt=media&token=b0a34535-1b41-44eb-9671-44745befc7da", "Brocoli", 55.00 ),
            Product( "https://firebasestorage.googleapis.com/v0/b/ecommerce5-2a331.appspot.com/o/maize.jpeg?alt=media&token=08ae3764-e0cd-4309-bf15-3184493f8603","Maize", 15.00 ),
            Product("https://firebasestorage.googleapis.com/v0/b/ecommerce5-2a331.appspot.com/o/orange.jpeg?alt=media&token=a2b50d3a-3e08-4ea8-91ae-626bfed5c650", "Orange", 10.00),
            Product( "https://firebasestorage.googleapis.com/v0/b/ecommerce5-2a331.appspot.com/o/tomatoes.jpeg?alt=media&token=a448d08c-a75b-4250-a980-a37d808dcbb9", "Tomatoes", 20.00),
            Product("https://firebasestorage.googleapis.com/v0/b/ecommerce5-2a331.appspot.com/o/pineapple.jpeg?alt=media&token=db581e82-bb5c-442b-bf64-f2870d8183fe", "Pineapple", 85.00)
        )
        productAdapter = ProductAdapter(productList) { product ->
            addToCart(product)
        }
        recyclerView.adapter = productAdapter

        database = FirebaseDatabase.getInstance()
        productsRef = database.getReference("products")
        cartRef = database.getReference("cart")

        cartListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Update the cart badge or handle cart data changes if needed
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error here
            }
        }
        cartRef.addValueEventListener(cartListener)

        productsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                for (productSnapshot in snapshot.children) {
                    val name = productSnapshot.child("name").getValue(String::class.java)
                    val price = productSnapshot.child("price").getValue(Double::class.java)
                    if (name != null && price != null) {
                        val product = Product(name, price.toString())
                        productList.add(product)
                    }
                }
                productAdapter.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
                Log.e("ProductActivity", "Error retrieving products: ${error.message}")
            }
        }
        )


    }

    private fun addToCart(product: Product) {
        // Add the selected product to the cart in the Firebase Realtime Database
        val cartItem = cartRef.push()
        cartItem.child("name").setValue(product.name)
        cartItem.child("price").setValue(product.price)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove the cart listener when the activity is destroyed
        cartRef.removeEventListener(cartListener)
    }
}


