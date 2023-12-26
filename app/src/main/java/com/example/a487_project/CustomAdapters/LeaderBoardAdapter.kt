package com.example.a487_project.CustomAdapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a487_project.R

class LeaderboardAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    // Provide a reference to the views for each leaderboard
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rankTextView: TextView = view.findViewById(R.id.textViewRank)

        // Add references to other views as needed
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_leaderboard, parent, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position
        // Replace the contents of the view with that element
        holder.rankTextView.text = "${position + 1}."

        // Set item click listener
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(position)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return 10 // Change this to the actual size of your dataset
    }
}
