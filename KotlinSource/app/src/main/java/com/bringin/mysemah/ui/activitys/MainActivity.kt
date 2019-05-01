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

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this).load(UserNow()!!.currentUser!!.photoUrl).into(profile_account)
        profile_account.setOnClickListener(View.OnClickListener { Logout() })
        btn1.setOnClickListener(View.OnClickListener { Open(Datas.Display("Keluarga")) })
        btn2.setOnClickListener(View.OnClickListener { Open(Datas.Display("Perawatan Diri")) })


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
