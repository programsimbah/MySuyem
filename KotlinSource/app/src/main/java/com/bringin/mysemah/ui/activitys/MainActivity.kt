package com.bringin.mysemah.ui.activitys

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bringin.mysemah.BaseActivity
import com.bringin.mysemah.R
import com.bringin.mysemah.models.Datas
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.pengembangsebelah.auth.Initializ
import com.pengembangsebelah.network.DetailApp
import com.pengembangsebelah.network.Function

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(UserNow()!!.currentUser!=null) {
            Glide.with(this).load(Function.data!!.user.get(0).avatar).into(profile_account)
        }
        profile_account.setOnClickListener(View.OnClickListener { Logout() })
        SetText()
        btn1.setOnClickListener(View.OnClickListener { Open(Datas.Display("Keluarga")) })
        btn2.setOnClickListener(View.OnClickListener { Open(Datas.Display("Perawatan Diri")) })


    }

    fun SetText(){
        btn1.text=DetailApp._btnKeluarga
        btn2.text=DetailApp._btnperawatan
        btn3.text=DetailApp._btnmerawatpasien
        btn4.text=DetailApp._penangananGejala
        btn5.text=DetailApp._catatan
    }

    fun Open(data : Datas.Display){
        OpenDisply(this,data)
    }

    fun Logout(){

        SignOut()
        val intent = Intent(this,SplashActivity::class.java)
        startActivity(intent)
        finish()

    }

}
