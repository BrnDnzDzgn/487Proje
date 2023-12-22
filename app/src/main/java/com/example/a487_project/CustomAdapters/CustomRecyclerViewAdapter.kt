package com.example.a487_project.CustomAdapters //Boran

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a487_project.Activities.FashionRoomActivity
import com.example.a487_project.R
import com.example.a487_project.Classes.Category
import com.example.a487_project.Classes.ClothingItemKami
import com.example.a487_project.Classes.ClothingSys

class CustomRecyclerViewAdapter(private val context: Context, private val recyclerItemValues: ArrayList<Category>)
    :
    RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {


    //idk
    private lateinit var adapter2: ClothingListAdapther

    fun setAdapter2(adapter: ClothingListAdapther) {
        this.adapter2 = adapter
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): CustomRecyclerViewItemHolder {
        val inflator = LayoutInflater.from(context)
        val itemView = inflator.inflate(R.layout.recycler_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(
        myRecyclerViewItemHolder: CustomRecyclerViewAdapter.CustomRecyclerViewItemHolder,
        i: Int
    ) {
        val currentItem = recyclerItemValues[i]
        myRecyclerViewItemHolder.tv.text = currentItem.category

        myRecyclerViewItemHolder.tv.setOnClickListener {

            ClothingSys.cleanClothings()
            ClothingSys.generateClothingItems(currentItem.category)

            Toast.makeText(
                context,
                currentItem.category, // Use currentItem here
                Toast.LENGTH_LONG
            ).show()

            adapter2.updateData(ClothingSys.clothings, currentItem.category)
        }
    }



    override fun getItemCount(): Int {
        return recyclerItemValues.size
    }

    inner class CustomRecyclerViewItemHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
                var tv: TextView

                init{
                    tv = itemView.findViewById(R.id.buttonItem)
                }
            }

}
