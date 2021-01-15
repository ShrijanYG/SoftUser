package com.shrijan.esoft.adapter

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shrijan.esoft.R
import com.shrijan.esoft.`interface`.ValueKeeper
import com.shrijan.esoft.model.DetailModel
import de.hdodenhof.circleimageview.CircleImageView

class StudentAdapter(val context: Context?, val lstDetail : MutableList<DetailModel>): RecyclerView.Adapter<StudentAdapter.StudentHolder>(),
    ValueKeeper {

    class StudentHolder(v: View) : RecyclerView.ViewHolder(v) {
        var cvImage: CircleImageView
        var tvName: TextView
        var tvAge: TextView
        var tvGender: TextView
        var tvAddress: TextView
        var btnDelete: View
        var btnUpdate: View

        init {
            cvImage = v.findViewById(R.id.cvImage)
            tvName = v.findViewById(R.id.tvName)
            tvAge = v.findViewById(R.id.tvAge)
            tvGender = v.findViewById(R.id.tvGender)
            btnDelete = v.findViewById(R.id.btnFloat)
            tvAddress = v.findViewById(R.id.tvAddress)
            btnUpdate = v.findViewById(R.id.btnUpdate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_list, parent, false)
        return StudentHolder(view)
    }

    override fun getItemCount(): Int {
        return lstDetail.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val data = lstDetail[position]

        holder.tvName.text = data.name
        holder.tvAge.text = data.age.toString()
        holder.tvGender.text = data.gender
        holder.tvAddress.text = data.address

        Glide.with(context!!).load(data.img).into(holder.cvImage)

        holder.btnDelete.setOnClickListener {
            lstDetail.removeAt(position)
            notifyDataSetChanged()
        }

        val dialog = Dialog(context)
        dialog.setContentView(R.layout.alert_update)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)
        var etFn: EditText = dialog.findViewById(R.id.etFn)
        var etAge: EditText = dialog.findViewById(R.id.etAge)
        var etAddress: EditText = dialog.findViewById(R.id.etAddress)
        var etProfile: EditText = dialog.findViewById(R.id.etProfile)
        var rgGroup: RadioGroup = dialog.findViewById(R.id.rgGroup)
        var gender = "Male"
        var btnUpdate: Button = dialog.findViewById(R.id.btnUpdate)
        var btnCancel: Button = dialog.findViewById(R.id.btnCancel)

        holder.btnUpdate.setOnClickListener {
            etFn.setText(data.name)
            etAge.setText(data.age.toString())
            etAddress.setText(data.address)
            etProfile.setText(data.img)
            when (data.gender) {
                "Male" -> rgGroup.check(R.id.rbMale)
                "Female" -> rgGroup.check(R.id.rbFemale)
                "Others" -> rgGroup.check(R.id.rbOthers)
            }
            dialog.show()
        }


        rgGroup.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.rbMale -> {
                    gender = "Male"
                }

                R.id.rbFemale -> {
                    gender = "Female"
                }
                R.id.rbOthers -> {
                    gender = "Others"
                }
            }

        }

        btnUpdate.setOnClickListener {
            if (TextUtils.isEmpty(etFn.text)) {
                etFn.error = "Enter Firstname"
                etFn.requestFocus()

            } else if (TextUtils.isEmpty(etAge.text)) {
                etAge.error = "Enter Age"
                etAge.requestFocus()

            } else if (TextUtils.isEmpty(etAddress.text)) {
                etAddress.error = "Enter Address"
                etAddress.requestFocus()

            } else if (TextUtils.isEmpty(etProfile.text)) {
                etProfile.error = "Enter Address"
                etProfile.requestFocus()

            } else if (!Regex("""[a-z][a-z]+""").matches(
                    etFn.text.toString().toLowerCase().replace(" ", "")
                )
            ) {
                etAddress.error = "Full name should not contain any numbers"
                etAddress.requestFocus()

            } else if (etAge.text.toString().toInt() > 40 || etAge.text.toString().toInt() < 18) {
                etAge.error = "Age should be in between 18-40"
                etAge.requestFocus()

            } else {
                lstDetail[position].name = etFn.text.toString()
                lstDetail[position].address = etAddress.text.toString()
                lstDetail[position].img = etProfile.text.toString()
                lstDetail[position].age = etAge.text.toString().toInt()
                lstDetail[position].gender = gender
                notifyDataSetChanged()
                dialog.cancel()
            }
        }

        btnCancel.setOnClickListener {
            dialog.cancel()
        }

    }

    override fun studentSizeKeeper(): Int {
        return lstDetail.size
    }
}