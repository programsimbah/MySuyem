package com.bringin.mysemah.ui.activitys

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bringin.mysemah.BaseActivity
import com.bringin.mysemah.R
import com.google.firebase.auth.FirebaseAuth
import com.pengembangsebelah.auth.Initializ
import com.pengembangsebelah.auth.SucessLoginListener
import com.pengembangsebelah.network.Constant
import com.pengembangsebelah.network.DetailApp
import com.pengembangsebelah.network.Function
import com.pengembangsebelah.network.Result
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.dialog_field_splash.view.*
import kotlinx.android.synthetic.main.dialog_login_splash.view.*

class SplashActivity : BaseActivity() {
    override fun onDestroy() {
//        if(!isComplete){
//            SignOut()
//        }
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Loading()

    }
    fun Loading(){
        val timer = object: CountDownTimer(3000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                loading_ProgressBar.visibility = View.VISIBLE
            }

            override fun onFinish() {
                loading_ProgressBar.visibility = View.GONE
                if(FirebaseAuth.getInstance().currentUser==null) {
                    showDialogFirst()
                }else{
                    class see : SucessLoginListener{
                        override fun Success(code: Int) {
                            isOnData = true
                            if(code==4) {
                                Menu()
                            }else if(code==3){
                                FieldAc()

                            }else {
                                isOnData =false
                            }
                        }

                        override fun Fail(code: Int, message: String) {

                        }

                    }
                    val s = Initializ().SetListenenr(see(), FirebaseAuth.getInstance())
                }
            }
        }
        timer.start()
    }

    //fungsi dialog
    private fun showDialogFirst() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_login_splash, null)
        dialogBuilder.setView(dialogView)
        val textV = dialogView.title_layout_dialog
        textV.text = DetailApp.PRIVACYPOLICY
        val verifikasi = dialogView.checkbox_dialog_veriv
        verifikasi.text = DetailApp.acceptPrivacyPolicy
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

        alertDialog = dialogBuilder.create()
        alertDialog.setOnDismissListener { if (!isOnData)showDialogFirst() }
        alertDialog.show()
    }

    private lateinit var alertDialog :AlertDialog
    var isOnData :Boolean = false;
    var isComplete:Boolean =false;
    private fun DialogLogin(){
        class mumu : SucessLoginListener {
            override fun Success(code: Int) = if(code==4) {
                Menu()
            }else if(code==3){
                Log.d("YAYA","PUSH TO REGION")
                FieldAc()

            }else {
                loading_ProgressBar.visibility = View.VISIBLE
                alertDialog.dismiss()
                isOnData =true;
            }


            override fun Fail(code: Int, message: String) {
                Log.d("Test","Success "+code+" "+message)
            }

        }
        val obj = mumu()
        Login(obj)
    }
    //fungsi login
    fun PUSHUP(a:String,b:String,c:String){
        Log.d("YAYA","PUSH TO REGION 1")
        class rere:Result{
            override fun Succes(message: String) {
                Menu()
            }

            override fun Failed(message: String) {

            }

        }

        Function().PushOrigin(Constant.LOGINGOOGLE,Function.USER(FirebaseAuth.getInstance().currentUser!!.uid,
            FirebaseAuth.getInstance().currentUser!!.email.toString(),
            b,
            a,
            c,
            FirebaseAuth.getInstance().currentUser!!.photoUrl.toString()),
            rere())
    }

    //gotomenu
    fun Menu(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun FieldAc() {
        var email =FirebaseAuth.getInstance().currentUser!!.email
        var name =FirebaseAuth.getInstance().currentUser!!.displayName

        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_field_splash, null)
        dialogBuilder.setView(dialogView)

        val dilEm = dialogView.email_user
        dilEm.setText(email)
        val dilnam = dialogView.name_user
        dilnam.setText(name)
        val dilAl = dialogView.alamat_user
        val dilPhon = dialogView.no_telp_user

        val bucon = dialogView.confirm_btn_data
        bucon.setOnClickListener(View.OnClickListener { PUSHUP(dilAl.text.toString(),dilPhon.text.toString(),dilnam.text.toString()) })

        alertDialog = dialogBuilder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}
