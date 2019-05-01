package com.bringin.mysemah.ui.activitys

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import com.bringin.mysemah.BaseActivity
import com.bringin.mysemah.R
import com.google.firebase.auth.FirebaseAuth
import com.pengembangsebelah.auth.SucessLoginListener
import kotlinx.android.synthetic.main.dialog_login_splash.view.*

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if(FirebaseAuth.getInstance().currentUser==null) {
            showDialogFirst(this)
        }else{
            Menu()
        }
    }

    //fungsi dialog
    private fun showDialogFirst(context:Activity) {
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = context.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_login_splash, null)
        dialogBuilder.setView(dialogView)
        val verifikasi = dialogView.checkbox_dialog_veriv
        val loginbutton = dialogView.login_button
        loginbutton.setOnClickListener(View.OnClickListener { DialogLogin() })
        if (verifikasi.isChecked) {
            loginbutton.isEnabled = true
        } else {
            loginbutton.isEnabled = false
        }
        verifikasi.setOnClickListener(View.OnClickListener {
            if (verifikasi.isChecked) {
                loginbutton.isEnabled = true
            } else {
                loginbutton.isEnabled = false
            }
        })

        val alertDialog = dialogBuilder.create()
        alertDialog.setOnDismissListener { showDialogFirst(this) }
        alertDialog.show()
    }
    private fun DialogLogin(){
        class mumu : SucessLoginListener {
            override fun Success() {
                Log.d("Test","Success")
                Menu()
            }

            override fun Fail(code: Int, message: String) {
                Log.d("Test","Success "+code+" "+message)
            }

        }
        val obj = mumu()
        Login(obj)
    }
    //fungsi login

    //gotomenu
    fun Menu(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
