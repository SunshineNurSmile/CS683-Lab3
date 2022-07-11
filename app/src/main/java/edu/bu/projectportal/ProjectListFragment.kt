package edu.bu.projectportal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.content.Intent

import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Button
import edu.bu.projectportal.databinding.FragmentDetailBinding


/**
 * A fragment representing a list of Items.
 */
class ProjectListFragment : Fragment() {

    private var columnCount = 1
    private lateinit var onClickListener:OnClickListener
    private lateinit var addProjListener: AddProjectListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickListener) {
            onClickListener = context
        } else {
            throw RuntimeException("Must implement EditProjectListener")
        }

        if (context is AddProjectListener) {
            addProjListener = context
        } else {
            throw RuntimeException("Must implement AddProjectListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }


    interface OnClickListener{
        fun onClick(projId:Int);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_project_list, container, false)
        var addButton = view.findViewById<Button>(R.id.addButton)

        addButton.setOnClickListener {
            addProjListener.addProj()
        }


        // setupSimpleListView(view)
        setupRecyclerView(view.findViewById(R.id.recylReview))
        return view
    }

    companion object {
        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ProjectListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }


    fun setupSimpleListView(view: View){
        val projectTitleList = mutableListOf<String>()
        Project.projects.forEach{ projectTitleList?.add(it.title?:"") }

        val projectsListAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_list_item_1, projectTitleList)

        if (view is ListView) {
            view.adapter =projectsListAdapter

            view.setOnItemClickListener(OnItemClickListener { adapterView, view, i, l ->
                onClickListener.onClick(i)
            })
        }

    }


    fun setupRecyclerView(view:View){
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = ProjectListRecyclerViewAdapter(Project.projects,
                    object : ProjectListRecyclerViewAdapter.OnClickListener {
                        override fun onItemClick(projId:Int) {
                            onClickListener.onClick(projId)
                        }
                    })
            }
        }
    }

    fun updateProj(){
       val recyler_view = activity?.findViewById<RecyclerView>(R.id.recylReview)
        recyler_view?.adapter?.notifyDataSetChanged()
    }
}