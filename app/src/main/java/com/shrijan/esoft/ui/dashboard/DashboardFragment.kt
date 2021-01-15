package com.shrijan.esoft.ui.dashboard

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.shrijan.esoft.R
import com.shrijan.esoft.adapter.StudentAdapter
import com.shrijan.esoft.model.DetailModel
import com.shrijan.esoft.module.students

class DashboardFragment : Fragment(),View.OnClickListener {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var etFn : TextInputEditText
    private lateinit var etAge : TextInputEditText
    private lateinit var etAddress : TextInputEditText
    private lateinit var rgGroup : RadioGroup
    private lateinit var btnSave : Button
    private lateinit var etProfile : TextInputEditText
    var gender = "Male"
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        binding(root)
        listener()
        return root
    }

    private fun binding(v:View?)
    {
        etFn = v!!.findViewById(R.id.etFn)
        etAge = v!!.findViewById(R.id.etAge)
        etAddress = v!!.findViewById(R.id.etAddress)
        rgGroup = v!!.findViewById(R.id.rgGroup)
        btnSave = v!!.findViewById(R.id.btnSave)
        etProfile = v!!.findViewById(R.id.etProfile)
    }

    private fun listener()
    {
        rgGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId)
            {
                R.id.rbMale ->{
                    gender = "Male"
                }

                R.id.rbFemale ->{
                    gender = "Female"
                }
                R.id.rbOthers ->{
                    gender = "Others"
                }


            }
        }

        btnSave.setOnClickListener(this)
    }

    private fun validation():Boolean
    {
        if(TextUtils.isEmpty(etFn.text))
        {
            etFn.error = "Enter Firstname"
            etFn.requestFocus()
            return false
        }
        else if(TextUtils.isEmpty(etAge.text))
        {
            etAge.error = "Enter Age"
            etAge.requestFocus()
            return false
        }
        else if(TextUtils.isEmpty(etAddress.text))
        {
            etAddress.error = "Enter Address"
            etAddress.requestFocus()
            return false
        }
        else if(TextUtils.isEmpty(etProfile.text))
        {
            etProfile.error = "Enter Address"
            etProfile.requestFocus()
            return false
        }
        else if(!Regex("""[a-z][a-z]+""").matches(etFn.text.toString().toLowerCase().replace(" ","")))
        {
            etAddress.error = "Full name should not contain any numbers"
            etAddress.requestFocus()
            return false
        }
        else if(etAge.text.toString().toInt() > 40 || etAge.text.toString().toInt() < 18)
        {
            etAge.error = "Age should be in between 18-40"
            etAge.requestFocus()
            return false
        }
        else
        {
            return true
        }
    }

    private fun addStudent()
    {
        if(validation())
        {
            var etfn = etFn.text.toString()
            var etAge = etAge.text.toString().toInt()
            var url = etProfile.text.toString()
            var address = etAddress.text.toString()

            val myDetail = DetailModel(etfn,etAge,address,gender,url)
            students.add(myDetail)
            clear()
            alert("Success","${etfn} Added!!")
            StudentAdapter(context,students).notifyDataSetChanged()
        }
    }

    private fun alert(title:String,msg:String)
    {
        var builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setNeutralButton("Ok"){
            dialog: DialogInterface?, which: Int ->
            
        }

        val al = builder.create()
        al.setCancelable(false)
        al.show()
    }


    private fun clear()
    {
        etFn.text!!.clear()
        etAge.text!!.clear()
        etAddress.text!!.clear()
        etProfile.text!!.clear()

    }


    override fun onClick(v: View?) {
       when(v?.id)
       {
           R.id.btnSave ->{
               addStudent()
           }
       }
    }
}