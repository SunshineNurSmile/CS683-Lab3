package edu.bu.projectportal

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener


class EditFragment : Fragment() {

    private lateinit var projTitle: EditText
    private lateinit var projDesc: EditText
    private lateinit var projAuthor1: EditText
    private lateinit var projAuthor2: EditText
    private lateinit var projAuthor3: EditText
    private lateinit var projLink1: EditText
    private lateinit var projLink2: EditText
    private lateinit var projLink3: EditText
    private lateinit var projKey1: EditText
    private lateinit var projKey2: EditText
    private lateinit var projKey3: EditText
    private lateinit var favoriteCheckBox: CheckBox

    private lateinit var submit: Button
    private lateinit var cancel: Button

    private lateinit var editProjListener: EditProjectListener

    companion object {
        fun newInstance() = EditFragment()
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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projTitle = view.findViewById(R.id.projTitleEdit)
        projDesc = view.findViewById(R.id.projDescEdit)
        projAuthor1 = view.findViewById(R.id.projAuthorEdit1)
        projAuthor2 = view.findViewById(R.id.projAuthorEdit2)
        projAuthor3 = view.findViewById(R.id.projAuthorEdit3)
        projLink1 = view.findViewById(R.id.projLinkEdit1)
        projLink2 = view.findViewById(R.id.projLinkEdit2)
        projLink3 = view.findViewById(R.id.projLinkEdit3)
        projKey1 = view.findViewById(R.id.projKeyEdit1)
        projKey2 = view.findViewById(R.id.projKeyEdit2)
        projKey3 = view.findViewById(R.id.projKeyEdit3)
        favoriteCheckBox = view.findViewById(R.id.favorite)

        submit = view.findViewById(R.id.submit)
        cancel = view.findViewById(R.id.cancel)

        projTitle.setText(Project.projects[Project.curProjId].title)
        projDesc.setText(Project.projects[Project.curProjId].description)
        projAuthor1.setText(Project.projects[Project.curProjId].author1)
        projAuthor2.setText(Project.projects[Project.curProjId].author2)
        projAuthor3.setText(Project.projects[Project.curProjId].author3)
        projLink1.setText(Project.projects[Project.curProjId].link1)
        projLink2.setText(Project.projects[Project.curProjId].link2)
        projLink3.setText(Project.projects[Project.curProjId].link3)
        projKey1.setText(Project.projects[Project.curProjId].key1)
        projKey2.setText(Project.projects[Project.curProjId].key2)
        projKey3.setText(Project.projects[Project.curProjId].key3)
        favoriteCheckBox.isChecked = Project.projects[Project.curProjId].favorite


        submit.setOnClickListener {
            Project.projects[Project.curProjId].title = projTitle.text.toString()
            Project.projects[Project.curProjId].description = projDesc.text.toString()
            Project.projects[Project.curProjId].author1 = projAuthor1.text.toString()
            Project.projects[Project.curProjId].author2 = projAuthor2.text.toString()
            Project.projects[Project.curProjId].author3 = projAuthor3.text.toString()
            Project.projects[Project.curProjId].link1 = projLink1.text.toString()
            Project.projects[Project.curProjId].link2 = projLink2.text.toString()
            Project.projects[Project.curProjId].link3 = projLink3.text.toString()
            Project.projects[Project.curProjId].key1 = projKey1.text.toString()
            Project.projects[Project.curProjId].key2 = projKey2.text.toString()
            Project.projects[Project.curProjId].key3 = projKey3.text.toString()
            Project.projects[Project.curProjId].favorite = favoriteCheckBox.isChecked
            editProjListener.editProjDone()

        }

        cancel.setOnClickListener {
            editProjListener.editProjDone()
        }
    }

}