package com.example.transition

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ItemAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(this, 4)

        adapter.submitList(getHoloLiveMember(this))

    }
}

class ItemAdapter(
    private val context: Context
) : ListAdapter<Item, ItemAdapter.ViewHolder>(ItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        val imageView = holder.imageView
        Picasso.with(imageView.context)
            .load(item.imageUrl)
            .into(imageView)
    }

    class ItemDiffUtil : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class ViewHolder(
        view: View,
        val imageView: ImageView = view.findViewById(R.id.imageView)
    ) : RecyclerView.ViewHolder(view)
}

data class Item(
    val name: String,
    val imageUrl: String
)