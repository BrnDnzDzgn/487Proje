package com.example.a487_project.CustomAdapters



import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.a487_project.Classes.ClothingItemKami
import com.example.a487_project.Classes.Look
import com.example.a487_project.R

class ClothingListAdapther(private var items: MutableList<ClothingItemKami>) :
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

    private fun setDrawable(imageView: ImageView, @DrawableRes drawableRes: Int) {
        val drawableName = imageView.resources.getResourceEntryName(drawableRes)
        imageView.setImageResource(drawableRes)
        imageView.tag = drawableName // Storing the resource name in the tag for later retrieval
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        val resourceId = holder.itemView.resources.getIdentifier(
            item.imageName, "drawable", holder.itemView.context.packageName
        )

        setDrawable(holder.imageView, resourceId)

        // Set the image resource based on the resource identifier
        holder.imageView.setImageResource(resourceId)

        var curLook = Look()
        holder.imageView.setOnClickListener {
            onItemClick?.invoke(position)

            if(item.category == "Dress"){
                val imageViewLayer5: ImageView? = holder.itemView.rootView.findViewById(R.id.layer5)
                imageViewLayer5?.setImageResource(android.R.color.transparent)

                val imageViewLayer6: ImageView? = holder.itemView.rootView.findViewById(R.id.layer6)
                imageViewLayer6?.setImageResource(android.R.color.transparent)
            }

            if(item.category == "Top" || item.category == "Bottom" ){
                val imageViewLayer4: ImageView? = holder.itemView.rootView.findViewById(R.id.layer4)
                imageViewLayer4?.setImageResource(android.R.color.transparent)

            }

            // Your custom logic for handling ImageView clicks here
            val selectedItem = items[position]
            val imageViewId = "layer" + selectedItem.layer
            val imageView: ImageView? = holder.itemView.rootView.findViewById(
                holder.itemView.resources.getIdentifier(imageViewId, "id", holder.itemView.context.packageName)

            )

            imageView?.setImageResource(resourceId)

        }
    }



    fun updateData(newItems: List<ClothingItemKami>, categoryFilter: String? = null) {
        if (categoryFilter.isNullOrBlank()) {
            items.addAll(newItems)
        } else {
            // Filter items based on the category
            items.clear()
            items.addAll(newItems.filter { it.category == categoryFilter })
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}