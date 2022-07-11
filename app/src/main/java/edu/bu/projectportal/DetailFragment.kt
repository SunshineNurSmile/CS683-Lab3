package edu.bu.projectportal

import android.content.Context
import android.os.Bundle
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.bu.projectportal.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
//    private lateinit var projTitle:TextView
//    private lateinit var projDesc:TextView
//    private lateinit var editProj: ImageButton

    private lateinit var binding:FragmentDetailBinding

    private lateinit var editProjListener: EditProjectListener


    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EditProjectListener) {
            editProjListener = context
        } else {
            throw RuntimeException("Must implement EditProjectListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

       // return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

//        projTitle = view.findViewById(R.id.projTitle)
//        projDesc =  view.findViewById(R.id.projDesc)
//        editProj = view.findViewById(R.id.editProj)

        binding.projTitle.text =  Project.projects[Project.curProjId].title
        binding.projDesc.text = Project.projects[Project.curProjId].description
        binding.projAuthor1.text = Project.projects[Project.curProjId].author1
        binding.projAuthor2.text = Project.projects[Project.curProjId].author2
        binding.projAuthor3.text = Project.projects[Project.curProjId].author3
        binding.projLink1.text = Project.projects[Project.curProjId].link1
        binding.projLink2.text = Project.projects[Project.curProjId].link2
        binding.projLink3.text = Project.projects[Project.curProjId].link3
        binding.projKey1.text = Project.projects[Project.curProjId].key1
        binding.projKey2.text = Project.projects[Project.curProjId].key2
        binding.projKey3.text = Project.projects[Project.curProjId].key3
        binding.favoriteDisplay.text = Project.projects[Project.curProjId].favorite.toString()

        binding.editProj.setOnClickListener{
           editProjListener.editProj()

        }
    }

    override fun onResume() {
        super.onResume()
        updateProj(Project.curProjId)
    }

    fun updateProj(projId:Int){
        binding.projTitle.text =  Project.projects[projId].title
        binding.projDesc.text = Project.projects[projId].description
        binding.projAuthor1.text = Project.projects[projId].author1
        binding.projAuthor2.text = Project.projects[projId].author2
        binding.projAuthor3.text = Project.projects[projId].author3
        binding.projLink1.text = Project.projects[projId].link1
        binding.projLink2.text = Project.projects[projId].link2
        binding.projLink3.text = Project.projects[projId].link3
        binding.projKey1.text = Project.projects[projId].key1
        binding.projKey2.text = Project.projects[projId].key2
        binding.projKey3.text = Project.projects[projId].key3
        binding.favoriteDisplay.text = Project.projects[projId].favorite.toString()
    }
}