package com.shrijan.esoft.module

import com.shrijan.esoft.model.AuthenticateData
import com.shrijan.esoft.model.DetailModel

var students: MutableList<DetailModel>  =  mutableListOf()
var authentication : MutableList<AuthenticateData> = mutableListOf(
    AuthenticateData("softwarica","coventry")
)

fun login(username:String,password:String):AuthenticateData
{
    var auD = AuthenticateData("","")
    for(i in authentication)
    {
        if(i.un!!.toLowerCase() == username && i.pw!!.toLowerCase() == password)
        {
            auD = i
            break
        }
    }
    return auD
}