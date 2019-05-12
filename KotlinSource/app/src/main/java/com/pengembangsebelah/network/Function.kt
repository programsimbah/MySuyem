package com.pengembangsebelah.network

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import com.beust.klaxon.Klaxon
import com.bringin.mysemah.BuildConfig
import com.google.firebase.auth.FirebaseAuth
import com.pengembangsebelah.auth.Initializ
import com.pengembangsebelah.model.JSON
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

class Function {
    companion object {
        var APIKEY = ""


        fun convertStreanToString(inputStream: InputStream):String{
            val  bufferReader = BufferedReader(InputStreamReader(inputStream))
            var line:String
            var allstring:String=""

            try {
                do {
                    line=bufferReader.readLine()
                    if(line!=null)
                        allstring+=line
                }while (line!=null)
                bufferReader.close()
            }catch (ex:Exception){}


            return allstring
        }

        var __user : FirebaseAuth? = null

        var data : JSON? =null
    }
    class USER (val apikey: String,val email:String,val noTelp:String,val alamat:String,val name:String,val avatar:String)

    class DoLogin(login:String, _user: USER?,RESULT: Result): AsyncTask<Void, Void, Void>(){
        var _result =RESULT
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            _result.Succes("su")
        }

        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("yyyy-M-dd HH:mm:ss")
        val currentDate = sdf.format(Date())

        val mURL = URL(BuildConfig.API_URL+login)
        var reqapi = URLEncoder.encode("apikey", "UTF-8") + "=" + _user!!.apikey
        var reqemail = "&" + URLEncoder.encode("email", "UTF-8") + "=" + _user!!.email
        var reqnotelp = "&" + URLEncoder.encode("no_telp", "UTF-8") + "=" + _user!!.noTelp
        var reqalamat = "&" + URLEncoder.encode("alamat", "UTF-8") + "=" + _user!!.alamat
        var reqname = "&" + URLEncoder.encode("name", "UTF-8") + "=" + _user!!.name
        var reqavatar = "&" + URLEncoder.encode("avatar", "UTF-8") + "=" + _user!!.avatar
        var today = "&" + URLEncoder.encode("now", "UTF-8") + "=" + currentDate

        override fun doInBackground(vararg params: Void?): Void? {
            with(mURL.openConnection() as HttpURLConnection) {
                // optional default is GET
                val sar = reqapi+reqemail+reqnotelp+reqalamat+reqname+reqavatar+today
                Log.d("YAYA", "dasdad "+mURL.toString()+"\n"+sar)
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream());
                wr.write(sar)
                wr.flush()

                val dataJsonAsString=Function.convertStreanToString(inputStream)
                Log.d("YAYA", " aku taerljur cinta kepadamu dan talakd "+dataJsonAsString)
            }
            return null;
        }

    }

    class Login(apikey:String,RESULT: Result) : AsyncTask<Void, Void, Void>() {
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            Dodo(json)
        }

        val res :Result = RESULT
        var json =""

        override fun doInBackground(vararg params: Void?): Void? {
            with(mURL.openConnection() as HttpURLConnection) {
                // optional default is GET
                Log.d("YAYA", mURL.toString())
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream());
                wr.write(reqParam);
                wr.flush();

                val dataJsonAsString=Function.convertStreanToString(inputStream)
               // val json= JSONObject(dataJsonAsString);
               json = dataJsonAsString


            }
            return null;
        }

        var reqParam = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(apikey, "UTF-8")
        val mURL = URL(BuildConfig.API_URL+Constant.CHECK+ apikey)
        val key =apikey;

        fun Dodo(json:String){
            val result = Klaxon().parse<JSON>(json)
            data = result!!
            Log.d("YAYA","Response : "+ json+ result!!.user[0].apikey + result!!.user[0].error)
            if(result!!.user[0].error!="access denied"){
                res.Succes("su");
            }else{
                res.Succes("ssGog")
            }
        }

    }

    fun PushOrigin(login:String, _user: USER?,RESULT: Result){
        Log.d("YAYA","PUSH TO REGION 2")
        DoLogin(login,_user,RESULT).execute()
    }

    fun Check(user: FirebaseAuth, RESULT:Result){
        __user = user
        Login(user.currentUser!!.uid,RESULT).execute()
    }


    class OpenUrl() : AsyncTask<String, String, String>(){
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            _result!!.Succes(result.toString())
        }

        var _result : Result? = null
        fun Set(_result : Result){
            this._result =_result
        }
        override fun doInBackground(vararg params: String?): String {
            val url= URL(params[0])
            val urlConnect = url.openConnection() as HttpURLConnection
            urlConnect.connectTimeout=500

            val dataJsonAsString=Function.convertStreanToString(urlConnect.inputStream)

            return toString();
        }


    }
}