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


class AddFragment : Fragment() {

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

    private lateinit var addProjListener: AddProjectListener

    companion object {
        fun newInstance() = AddFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddProjectListener) {
            addProjListener = context
        } else {
            throw RuntimeException("Must implement AddProjectListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
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

        submit = view.findViewById(R.id.submit_add)
        cancel = view.findViewById(R.id.cancel_add)

        submit.setOnClickListener {
            val newProject = Project(
                Project.projects.size,
                projTitle.text.toString(),
                projDesc.text.toString(),
                projAuthor1.text.toString(),
                projAuthor2.text.toString(),
                projAuthor3.text.toString(),
                projLink1.text.toString(),
                projLink2.text.toString(),
                projLink3.text.toString(),
                projKey1.text.toString(),
                projKey2.text.toString(),
                projKey3.text.toString(),
                favoriteCheckBox.isChecked
            )
            Project.projects.add(newProject)
            addProjListener.addProjDone()

        }

        cancel.setOnClickListener {
            addProjListener.addProjDone()
        }
    }

}