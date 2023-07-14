package com.example.ecommerce5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val productList: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
        Picasso.get()
            .load(product.img)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        private val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(product: Product) {
            nameTextView.text = product.name
            priceTextView.text = product.price.toString()

            addToCartButton.setOnClickListener {
                onItemClick(product)
            }
            Picasso.get().load(product.img).into(imageView)
        }
    }
}






