package com.pengembangsebelah.auth

interface SucessLoginListener {
    fun Success()
    fun Fail(code:Int,message:String)

}