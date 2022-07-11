package edu.bu.projectportal

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import edu.bu.projectportal.databinding.FragmentProjectBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ProjectListRecyclerViewAdapter(
    private val projects: List<Project>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<ProjectListRecyclerViewAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(projId:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]
        holder.titleView.text = project.title

        holder.cardView.setOnClickListener {
            Project.curProjId = position
            onClickListener.onItemClick(position)
        }

    }

    override fun getItemCount(): Int = projects.size

    inner class ViewHolder(binding: FragmentProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleView: TextView = binding.projTitleinCard
        val cardView: CardView = binding.projectCard


        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }

}