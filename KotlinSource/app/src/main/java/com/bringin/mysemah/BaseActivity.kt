package com.bringin.mysemah

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bringin.mysemah.models.Datas
import com.bringin.mysemah.ui.activitys.DisplayUhuy
import com.bringin.mysemah.ui.activitys.SplashActivity
import com.google.firebase.auth.FirebaseAuth
import com.pengembangsebelah.auth.Initializ
import com.pengembangsebelah.auth.SucessLoginListener

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var listenerBase: SucessLoginListener
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        initializ.ResultActivity(requestCode,resultCode,data)
    }

    private lateinit var initializ: Initializ
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializ = Initializ()
        initializ.init(this)
    }

    public fun Login(listener: SucessLoginListener){
        this.listenerBase = listener
        initializ.signIn(listenerBase)
    }
    public fun SignOut(){
        initializ.signOut()
    }

    public fun UserNow(): FirebaseAuth? {
        return initializ.userNow()
    }

    companion object {
        var TITLEMENU = "menrfeds"
    }

    public fun OpenDisply(contex: Activity, data: Datas.Display){
        val intent = Intent(this, DisplayUhuy::class.java)
        intent.putExtra(TITLEMENU,data.title)
        startActivity(intent)
    }
}