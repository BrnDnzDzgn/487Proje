package com.example.a487_project.CustomAdapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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


        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}