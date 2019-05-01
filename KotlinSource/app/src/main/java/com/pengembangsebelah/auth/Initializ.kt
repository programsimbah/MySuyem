package com.pengembangsebelah.auth

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.util.Log
import com.bringin.mysemah.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class Initializ {
    companion object {
        private const val TAG = "Auth By Pengembang Sebelah"
        private const val RC_SIGN_IN = 567
    }

    private lateinit var context: Activity
    private lateinit var gso:GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var listener: SucessLoginListener

    public fun userNow(): FirebaseAuth? {
        return FirebaseAuth.getInstance()
    }
    public fun init(context: Activity){
        this.context=context
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)
        auth = FirebaseAuth.getInstance()
    }

    public fun ResultActivity(requestCode: Int, resultCode: Int, data: Intent?){
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                //Google failed
                listener.Fail(0,data.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    listener.Success()
                } else {
                    listener.Fail(1,task.result.toString())
                }
            }
    }

    public fun signIn(listener: SucessLoginListener) {
        this.listener=listener
        val signInIntent = googleSignInClient.signInIntent
        context.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public fun signOut() {
        auth.signOut()
        googleSignInClient.signOut()
    }
}