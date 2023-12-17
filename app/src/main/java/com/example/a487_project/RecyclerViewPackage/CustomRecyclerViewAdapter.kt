package com.example.a487_project.RecyclerViewPackage //Boran

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a487_project.Activities.MainActivity
import com.example.a487_project.R
import com.example.a487_project.Classes.Category

class CustomRecyclerViewAdapter(private val context: Context, private val recyclerItemValues: ArrayList<Category>)
    :
    RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): CustomRecyclerViewAdapter.CustomRecyclerViewItemHolder {
        val inflator = LayoutInflater.from(context)
        val itemView = inflator.inflate(R.layout.recycler_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(
        myRecyclerViewItemHolder: CustomRecyclerViewAdapter.CustomRecyclerViewItemHolder,
        i: Int
    ) {
        val currentItem = recyclerItemValues[i]
        myRecyclerViewItemHolder.tv.text = currentItem.name

        val sm = recyclerItemValues[i]

        myRecyclerViewItemHolder.tv.text = sm.name

        myRecyclerViewItemHolder.tv.setOnClickListener{
            Toast.makeText(
                context,
                sm.name,
                Toast.LENGTH_LONG
            ).show()
        }

    }

    override fun getItemCount(): Int {
        return recyclerItemValues.size
    }

    inner class CustomRecyclerViewItemHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
                var tv: TextView

                init{
                    tv = itemView.findViewById(R.id.tvItemCategoryName)
                }
            }


    fun setHorizontalLayoutManager(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
    }
}
