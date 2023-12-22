package com.example.a487_project.CustomAdapters



import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.a487_project.Classes.ClothingItemKami
import com.example.a487_project.R

class ClothingListAdapther(private val items: List<ClothingItemKami>) :
    RecyclerView.Adapter<ClothingListAdapther.ViewHolder>() {

    private var onItemClick: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClick = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_clothes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        val resourceId = holder.itemView.resources.getIdentifier(
            item.imageName, "drawable", holder.itemView.context.packageName
        )

        // Set the image resource based on the resource identifier
        holder.imageView.setImageResource(resourceId)


        holder.imageView.setOnClickListener {
            onItemClick?.invoke(position)

            // Your custom logic for handling ImageView clicks here
            val selectedItem = items[position]
            val imageViewId = "layer" + selectedItem.layer
            val imageView: ImageView? = holder.itemView.rootView.findViewById(
                holder.itemView.resources.getIdentifier(imageViewId, "id", holder.itemView.context.packageName)
            )

            imageView?.setImageResource(resourceId)


        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}