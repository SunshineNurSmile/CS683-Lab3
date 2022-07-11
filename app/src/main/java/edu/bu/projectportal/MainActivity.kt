package edu.bu.projectportal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Button
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity(),
    EditProjectListener,
    AddProjectListener,
    ProjectListFragment.OnClickListener  {
    private var detailContainer: FragmentContainerView? = null
    private var listContainer: FragmentContainerView? = null
    private var container:FragmentContainerView? = null
    private var favorite:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        container = findViewById<FragmentContainerView>(R.id.containerId)
        detailContainer = findViewById<FragmentContainerView>(R.id.detailContainerId)
        listContainer = findViewById<FragmentContainerView>(R.id.listContainerId)

        savedInstanceState?.let{
            Log.d("MainActivity","saved state")
        }
        container?.let {
            Log.d("MainActivity", "portrait ")
        }

        detailContainer?.let {
            Log.d("MainActivity", "landscape: list  ")
        }

        listContainer?.let {
            Log.d("MainActivity", "landsacpe:detail  ")
        }

        //executing the add fragment transaction when the container is not null


        container?.let { framelayout->
            supportFragmentManager.beginTransaction()
                .add(framelayout.id, ProjectListFragment.newInstance(1))
                .commit()
        }

        listContainer?.let { frameLayout ->
            supportFragmentManager.beginTransaction()
                .add(frameLayout.id, ProjectListFragment.newInstance(1))
                .commit()
        }

        detailContainer?.let { frameLayout ->
            supportFragmentManager.beginTransaction()
                .add(frameLayout.id, DetailFragment.newInstance())
                .commit()
        }
    }

    override fun editProj(){
        var editContainer = detailContainer?:container
        editContainer?.let { frameLayout ->
            Log.d("mainActivity", "switchtoedit")
            supportFragmentManager.beginTransaction()
                .replace(frameLayout.id, EditFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }

    }

    override fun addProj() {
        var addContainer = detailContainer?:container
        addContainer?.let { frameLayout ->
            Log.d("mainActivity", "switchtoadd")
            supportFragmentManager.beginTransaction()
                .replace(frameLayout.id, AddFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }


    override fun editProjDone(){
        var editContainer = detailContainer?:container
        editContainer?.let { frameLayout ->
            supportFragmentManager.popBackStack()
        }
        val listFragment = supportFragmentManager.findFragmentById(R.id.listContainerId)
        if (listFragment is ProjectListFragment)
            listFragment.updateProj()
    }

    override fun addProjDone(){
        var addContainer = detailContainer?:container
        addContainer?.let { frameLayout ->
            supportFragmentManager.popBackStack()
        }
        val listFragment = supportFragmentManager.findFragmentById(R.id.listContainerId)
        if (listFragment is ProjectListFragment)
            listFragment.updateProj()
    }

    override fun onClick(projId:Int){
        Project.curProjId = projId
        if (detailContainer == null) {
            // portra
            container?.let { frameLayout ->
                supportFragmentManager.beginTransaction()
                    .replace(frameLayout.id, DetailFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }else {
            //
            val detailFragment = supportFragmentManager.findFragmentById(R.id.detailContainerId)
            if (detailFragment is DetailFragment)
                detailFragment.updateProj(projId)
        }

    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.favorite -> {
                    favorite = checked
                }
            }
        }
    }

}