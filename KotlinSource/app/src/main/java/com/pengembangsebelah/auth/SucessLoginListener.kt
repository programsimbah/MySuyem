package com.pengembangsebelah.auth

interface SucessLoginListener {
    fun Success(code:Int)
    fun Fail(code:Int,message:String)

}