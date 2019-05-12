package com.pengembangsebelah.network

import android.app.Activity
import android.os.AsyncTask
import android.util.Log
import com.bringin.mysemah.R
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL




class DetailApp {
    companion object {
       var TAG="DETAILAPP"
        var APPNAME=""
        var PRIVACYPOLICY=""
        var acceptPrivacyPolicy=""
        var _btnKeluarga=""
        var _btnperawatan=""
        var _btnmerawatpasien=""
        var _penangananGejala=""
        var _catatan=""
    }
    var url: String = ""
    fun Ascyn(act: Activity){
        url = act.getString(R.string.url_lang)
        Log.d(TAG,url)
        val task = OpenUrl()
        task.execute(url)
    }
    class OpenUrl() : AsyncTask<String, String, String>(){
        var _result : Result? = null
        fun Set(_result : Result){
            this._result =_result
        }

        var lines = ""
        var data = ""
        override fun doInBackground(vararg params: String?): String {
            val url=URL(params[0])
            val urlConnect = url.openConnection() as HttpURLConnection
            urlConnect.connectTimeout=500

            val dataJsonAsString=convertStreanToString(urlConnect.inputStream)
            val json= JSONObject(dataJsonAsString);

            APPNAME = json.getString("appname")
            PRIVACYPOLICY =json.getString("privacyPolicy")
            acceptPrivacyPolicy = json.getString("acceptPrivacyPolicy")
            _btnKeluarga = json.getString("_btnKeluarga")
            _btnperawatan = json.getString("_btnperawatan")
            _btnmerawatpasien = json.getString("_btnmerawatpasien")
            _penangananGejala = json.getString("_penangananGejala")
            _catatan = json.getString("_catatan")




            return data;
        }

        fun convertStreanToString(inputStream:InputStream):String{
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

    }

}
