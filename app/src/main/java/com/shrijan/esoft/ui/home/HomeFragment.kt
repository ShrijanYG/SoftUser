package com.shrijan.esoft.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shrijan.esoft.R
import com.shrijan.esoft.adapter.StudentAdapter
import com.shrijan.esoft.module.students


class HomeFragment : Fragment(),View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recycler : RecyclerView
//    private lateinit var tvCount : TextView
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        binding(root)
        initialize(context)
        return root
    }

    private fun binding(v:View?)
    {
        recycler = v!!.findViewById(R.id.recycler)
//        tvCount = v!!.findViewById(R.id.tvCount)
    }

    private fun initialize(context: Context?)
    {
        val adapter = StudentAdapter(context, students)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

//        if(students.size > 0)
//        {
//            tvCount.text = "${adapter.studentSizeKeeper()}"
//        }
//        else
//        {
//            tvCount.text = "0 Data Found"
//        }
    }



    override fun onClick(v: View?) {
        when(v?.id)
        {

        }
    }
}