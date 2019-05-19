package com.pengembangsebelah.network

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.bringin.mysemah.BaseActivity
import com.bringin.mysemah.BuildConfig
import com.google.firebase.auth.FirebaseAuth
import com.pengembangsebelah.auth.Initializ
import com.pengembangsebelah.model.JSON
import com.pengembangsebelah.model.keluhan
import com.pengembangsebelah.model.obat
import org.json.JSONObject
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
                BaseActivity.datas = result
                res.Succes("su");
            }else{
                res.Succes("ssGog")
            }
        }

    }

    public class UpdateKeluhan(apikey:String,keluhan:String,gambaran:String,id:String,pos:Int,listenenr:Result): AsyncTask<Void, Void, Void>(){
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            BaseActivity.datas.user[0].keluhan!![pose].keluhan = kel
            BaseActivity.datas.user[0].keluhan!![pose].gambaran = gam
            listenenr.Succes("Oke")
        }

        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("yyyy-M-dd HH:mm:ss")
        val currentDate = sdf.format(Date())
        val listenenr:Result = listenenr
        val gam:String = gambaran
        val kel:String = keluhan
        val pose:Int = pos

        override fun doInBackground(vararg params: Void?): Void? {
            with(mUrl.openConnection() as HttpURLConnection) {
                // optional default is GET
                Log.d("YAYA", mUrl.toString()+" "+gam+kel)
                requestMethod = "POST"

                val sar = reqapi+today+reqkeluhan+reqgambaran+ideUser+idBase
                val wr = OutputStreamWriter(getOutputStream());
                wr.write(sar);
                wr.flush();

                val dataJsonAsString=Function.convertStreanToString(inputStream)
//                val json= JSONObject(dataJsonAsString)
                Log.d("YAYA","TNISD "+ dataJsonAsString+ idBase)

            }
            return null;
        }
        val mUrl = URL(BuildConfig.API_URL+Constant.UPDATE+"Keluhan")
        var reqapi = URLEncoder.encode("apikey", "UTF-8") + "=" + apikey
        var idBase = "&" + URLEncoder.encode("id", "UTF-8") + "=" + id
        var reqkeluhan = "&" + URLEncoder.encode("keluhan", "UTF-8") + "=" + keluhan
        var reqgambaran = "&" + URLEncoder.encode("gambaran", "UTF-8") + "=" + gambaran
        var today = "&" + URLEncoder.encode("date", "UTF-8") + "=" + currentDate
        var ideUser = "&" + URLEncoder.encode("idUser", "UTF-8") + "=" + BaseActivity.datas.user[0].id
    }
    public class UpdateObat(apikey:String,obate:String,jadwal:String,kegunaan:String,dokter:String,efekSamping:String,id:String,pos:Int,listenenr:Result): AsyncTask<Void, Void, Void>(){
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            BaseActivity.datas.user[0].obat!![pose].nama_obat = a
            BaseActivity.datas.user[0].obat!![pose].jadwal_pemberian = b
            BaseActivity.datas.user[0].obat!![pose].kegunaan = c
            BaseActivity.datas.user[0].obat!![pose].dokter = d
            BaseActivity.datas.user[0].obat!![pose].efek_samping = e
            listenenr.Succes("Oke")
        }

        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("yyyy-M-dd HH:mm:ss")
        val currentDate = sdf.format(Date())
        val listenenr:Result = listenenr
        val a:String = obate
        val b:String = jadwal
        val c:String = kegunaan
        val d:String = dokter
        val e:String = efekSamping
        val pose:Int = pos

        override fun doInBackground(vararg params: Void?): Void? {
            with(mUrl.openConnection() as HttpURLConnection) {
                // optional default is GET
                requestMethod = "POST"

                val sar = reqapi+idBase+today+ideUser+reqobat+reqjadwal+reqkegunaan+reqdokter+reqefeksamping
                val wr = OutputStreamWriter(getOutputStream());
                wr.write(sar);
                wr.flush();

                val dataJsonAsString=Function.convertStreanToString(inputStream)
                Log.d("YAYA", " mantul "+dataJsonAsString+"  ")

            }
            return null;
        }
        val mUrl = URL(BuildConfig.API_URL+Constant.UPDATE+"Obat")
        var reqapi = URLEncoder.encode("apikey", "UTF-8") + "=" + apikey
        var idBase = "&" + URLEncoder.encode("id", "UTF-8") + "=" + id
        var reqobat = "&" + URLEncoder.encode("nama_obat", "UTF-8") + "=" + obate
        var reqjadwal = "&" + URLEncoder.encode("jadwal_pemberian", "UTF-8") + "=" + jadwal
        var reqkegunaan = "&" + URLEncoder.encode("kegunaan", "UTF-8") + "=" + kegunaan
        var reqdokter = "&" + URLEncoder.encode("dokter", "UTF-8") + "=" + dokter
        var reqefeksamping = "&" + URLEncoder.encode("efek_samping", "UTF-8") + "=" + efekSamping
        var today = "&" + URLEncoder.encode("date", "UTF-8") + "=" + currentDate
        var ideUser = "&" + URLEncoder.encode("idUser", "UTF-8") + "=" + BaseActivity.datas.user[0].id
    }
    public class DeleteKeluhan(apikey:String,id:String,pos:Int,listenenr:Result): AsyncTask<Void, Void, Void>(){
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            BaseActivity.datas.user[0].keluhan!!.removeAt(pos)
            listenenr.Succes("OKE")
        }
        val listenenr:Result = listenenr
        val kel:String = id
        val pos:Int = pos

        override fun doInBackground(vararg params: Void?): Void? {
            with(mUrl.openConnection() as HttpURLConnection) {
                // optional default is GET
                requestMethod = "POST"

                val sar = reqapi+reqid+reqidUser
                val wr = OutputStreamWriter(getOutputStream());
                wr.write(sar);
                wr.flush();

                val dataJsonAsString=Function.convertStreanToString(inputStream)
                Log.d("YAYA", " mantul "+dataJsonAsString)


            }
            return null;
        }
        val mUrl = URL(BuildConfig.API_URL+Constant.DELETE+"Keluhan")
        var reqapi = URLEncoder.encode("apikey", "UTF-8") + "=" + apikey
        var reqid = "&" + URLEncoder.encode("id", "UTF-8") + "=" + id
        var reqidUser = "&" + URLEncoder.encode("idUser", "UTF-8") + "=" + BaseActivity.datas.user[0].id
    }
    public class DeleteObat(apikey:String,id:String,pos:Int,listenenr:Result): AsyncTask<Void, Void, Void>(){
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            BaseActivity.datas.user[0].obat!!.removeAt(pos)
            listenenr.Succes("OKE")
        }
        val listenenr:Result = listenenr
        val kel:String = id
        val pos:Int = pos

        override fun doInBackground(vararg params: Void?): Void? {
            with(mUrl.openConnection() as HttpURLConnection) {
                // optional default is GET
                requestMethod = "POST"

                val sar = reqapi+reqid+reqidUser
                val wr = OutputStreamWriter(getOutputStream());
                wr.write(sar);
                wr.flush();

                val dataJsonAsString=Function.convertStreanToString(inputStream)
                Log.d("YAYA", " mantul "+dataJsonAsString)


            }
            return null;
        }
        val mUrl = URL(BuildConfig.API_URL+Constant.DELETE+"Obat")
        var reqapi = URLEncoder.encode("apikey", "UTF-8") + "=" + apikey
        var reqid = "&" + URLEncoder.encode("id", "UTF-8") + "=" + id
        var reqidUser = "&" + URLEncoder.encode("idUser", "UTF-8") + "=" + BaseActivity.datas.user[0].id
    }
    public class CreateObat(apikey:String,obate:String,jadwal:String,kegunaan:String,dokter:String,efekSamping:String,listenenr:Result): AsyncTask<Void, Void, Void>(){
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            val keluh:obat = obat()
            keluh.id = idNe
            keluh.created_at = currentDate
            keluh.date = currentDate
            keluh.nama_obat = a
            keluh.jadwal_pemberian = b
            keluh.kegunaan = c
            keluh.dokter = d
            keluh.efek_samping = e
            keluh.updated_at = currentDate
            BaseActivity.datas.user[0].obat!!.add(keluh)
            listenenr.Succes("Oke")
        }

        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("yyyy-M-dd HH:mm:ss")
        val currentDate = sdf.format(Date())
        val listenenr:Result = listenenr
        val a:String = obate
        val b:String = jadwal
        val c:String = kegunaan
        val d:String = dokter
        val e:String = efekSamping
        lateinit var idNe:String;

        override fun doInBackground(vararg params: Void?): Void? {
            with(mUrl.openConnection() as HttpURLConnection) {
                // optional default is GET
                Log.d("YAYA", mUrl.toString()+" ")
                requestMethod = "POST"

                val sar = reqapi+today+ideUser+reqobat+reqjadwal+reqkegunaan+reqdokter+reqefeksamping
                val wr = OutputStreamWriter(getOutputStream());
                wr.write(sar);
                wr.flush();

                val dataJsonAsString=Function.convertStreanToString(inputStream)
                val json= JSONObject(dataJsonAsString);
                Log.d("YAYA", " mantul "+dataJsonAsString+"  "+json.getInt("result"))
                idNe = json.getInt("result").toString()

            }
            return null;
        }
        val mUrl = URL(BuildConfig.API_URL+Constant.CREATE+"Obat")
        var reqapi = URLEncoder.encode("apikey", "UTF-8") + "=" + apikey
        var reqobat = "&" + URLEncoder.encode("nama_obat", "UTF-8") + "=" + obate
        var reqjadwal = "&" + URLEncoder.encode("jadwal_pemberian", "UTF-8") + "=" + jadwal
        var reqkegunaan = "&" + URLEncoder.encode("kegunaan", "UTF-8") + "=" + kegunaan
        var reqdokter = "&" + URLEncoder.encode("dokter", "UTF-8") + "=" + dokter
        var reqefeksamping = "&" + URLEncoder.encode("efek_samping", "UTF-8") + "=" + efekSamping
        var today = "&" + URLEncoder.encode("date", "UTF-8") + "=" + currentDate
        var ideUser = "&" + URLEncoder.encode("idUser", "UTF-8") + "=" + BaseActivity.datas.user[0].id
    }
    public class CreateKeluhan(apikey:String,keluhan:String,gambaran:String,listenenr:Result): AsyncTask<Void, Void, Void>(){
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            val keluh:keluhan = keluhan()
            keluh.id = idNe
            keluh.created_at = currentDate
            keluh.date = currentDate
            keluh.gambaran = gam
            keluh.keluhan = kel
            keluh.updated_at = currentDate
            BaseActivity.datas.user[0].keluhan!!.add(keluh)
            listenenr.Succes("Oke")
        }

        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("yyyy-M-dd HH:mm:ss")
        val currentDate = sdf.format(Date())
        val listenenr:Result = listenenr
        val gam:String = gambaran
        val kel:String = keluhan
        lateinit var idNe:String;

        override fun doInBackground(vararg params: Void?): Void? {
            with(mUrl.openConnection() as HttpURLConnection) {
                // optional default is GET
                Log.d("YAYA", mUrl.toString()+" "+gam+kel)
                requestMethod = "POST"

                val sar = reqapi+today+reqkeluhan+reqgambaran+ideUser
                val wr = OutputStreamWriter(getOutputStream());
                wr.write(sar);
                wr.flush();

                val dataJsonAsString=Function.convertStreanToString(inputStream)
                val json= JSONObject(dataJsonAsString);
                Log.d("YAYA", " mantul "+dataJsonAsString+"  "+json.getInt("result"))
                idNe = json.getInt("result").toString()

            }
            return null;
        }
        val mUrl = URL(BuildConfig.API_URL+Constant.CREATE+"Keluhan")
        var reqapi = URLEncoder.encode("apikey", "UTF-8") + "=" + apikey
        var reqkeluhan = "&" + URLEncoder.encode("keluhan", "UTF-8") + "=" + keluhan
        var reqgambaran = "&" + URLEncoder.encode("gambaran", "UTF-8") + "=" + gambaran
        var today = "&" + URLEncoder.encode("date", "UTF-8") + "=" + currentDate
        var ideUser = "&" + URLEncoder.encode("idUser", "UTF-8") + "=" + BaseActivity.datas.user[0].id
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