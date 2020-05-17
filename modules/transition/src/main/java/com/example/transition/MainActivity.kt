package com.example.transition

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
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

        val adapter = ItemAdapter(this) { item, imageView ->
            transitionSubActivity(item, imageView)
        }
        recycler_view.adapter = adapter
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = GridLayoutManager(
            this, 3, GridLayoutManager.VERTICAL, false
        )

        adapter.submitList(getHoloLiveMember(this))
    }

    fun transitionSubActivity(item: Item, imageView: ImageView) {

        val intent = Intent(this, SubActivity::class.java).apply {
            putExtra(TAG_NAME, item.name)
            putExtra(TAG_IMAGE_URL, item.imageUrl)
        }

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            Pair(imageView, VIEW_NAME_IMAGE)
        )

        ActivityCompat.startActivity(this, intent, options.toBundle())
    }
}

class ItemAdapter(
    private val context: Context,
    private val onClick: (Item, ImageView) -> Unit
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

        holder.textView.text = item.name

        val view = holder.itemView
        view.setOnClickListener {
            onClick(item, imageView)
        }
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
        val imageView: ImageView = view.findViewById(R.id.imageView),
        val textView: TextView = view.findViewById(R.id.textView)
    ) : RecyclerView.ViewHolder(view)
}

data class Item(
    val name: String,
    val imageUrl: String
)