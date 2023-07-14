package com.example.ecommerce5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.*

class CartActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartList: MutableList<CartItem>
    private lateinit var database: FirebaseDatabase
    private lateinit var cartRef: DatabaseReference
    private lateinit var cartListener: ValueEventListener
    private lateinit var btnCheckout: Button
    private lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.recyclerView)
        cartList = mutableListOf()
        cartAdapter = CartAdapter(cartList)
        recyclerView.adapter = cartAdapter

        btnCheckout = findViewById(R.id.btnCheckout)
        tvTotal = findViewById(R.id.tvTotal)

        database = FirebaseDatabase.getInstance()
        cartRef = database.getReference("cart")

        cartListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cartList.clear()
                var total = 0.0
                for (cartItemSnapshot in snapshot.children) {
                    val name = cartItemSnapshot.child("name").getValue(String::class.java)
                    val priceString = cartItemSnapshot.child("price").getValue(String::class.java)
                    val price = priceString?.toDoubleOrNull()
                    if (name != null && price != null) {
                        val cartItem = CartItem(name, price)
                        cartList.add(cartItem)
                        total += price
                    }
                }
                cartAdapter.notifyDataSetChanged()
                tvTotal.text = "Total: $total" // Set the total price in the TextView
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error here
            }
        }


        cartRef.addValueEventListener(cartListener)

        btnCheckout.setOnClickListener {
            // Perform the checkout process here
            checkout()
        }
    }

    private fun checkout() {
        // Perform the checkout process
        // You can implement your own logic here, such as payment processing, order confirmation, etc.
        // After completing the checkout, you may want to clear the cart by removing all items from the cart node in the Firebase Realtime Database.
        cartRef.removeValue()

        // Open a new activity or perform any other action after checkout
        val intent = Intent(this, CheckoutActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove the cart listener when the activity is destroyed
        cartRef.removeEventListener(cartListener)
    }
}

