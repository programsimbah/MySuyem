package com.bringin.mysemah.ui.activitys

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bringin.mysemah.BaseActivity
import com.bringin.mysemah.R
import com.bumptech.glide.Glide
import com.pengembangsebelah.network.Function
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profil_manage.*

class ProfilManageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_manage)
        title_activity_dd.setText("Profil Manager")

        if(UserNow()!!.currentUser!=null) {
            Glide.with(this).load(Function.data!!.user.get(0).avatar).into(profil_image)
        }
        logout_account.setOnClickListener { Logout() }

        val s = Function.data!!.user[0]
        email_edt.setText(s.email)
        name_edt.setText(s.name)
        alamat_edt.setText(s.alamat)
        no_hp_edt.setText(s.no_telp)
    }

    fun Logout(){
        SignOut()
        val intent = Intent(this,SplashActivity::class.java)
        startActivity(intent)
        finish()

    }
}
