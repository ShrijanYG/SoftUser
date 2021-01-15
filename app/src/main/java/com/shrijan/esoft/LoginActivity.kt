package com.shrijan.esoft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.shrijan.esoft.module.login

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etUn : TextInputEditText
    private lateinit var etPw : TextInputEditText
    private lateinit var btnLogin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding()
        listener()
    }

    private fun binding()
    {
        etUn = findViewById(R.id.etUn)
        etPw = findViewById(R.id.etPw)
        btnLogin = findViewById(R.id.btnLogin)
    }

    private fun listener()
    {
        btnLogin.setOnClickListener(this)
    }

    private fun validation():Boolean{
        if(TextUtils.isEmpty(etUn.text))
        {
            etUn.error = "Insert Username"
            etUn.requestFocus()
            return false
        }
        else if(TextUtils.isEmpty(etPw.text))
        {
            etPw.error = "Insert Password"
            etPw.requestFocus()
            return false
        }
        else
        {
            return true
        }
    }

    private fun getLogin()
    {
        if(validation())
        {
            var getData = login(etUn.text.toString(),etPw.text.toString())
            if(!TextUtils.isEmpty(getData.un))
            {
                var intent = Intent(this,MainActivity::class.java)
                intent.putExtra("admin",getData)
                startActivity(intent)
            }

        }
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.btnLogin ->{
                getLogin()
            }
        }
    }
}