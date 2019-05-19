package com.bringin.mysemah.ui.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bringin.mysemah.BaseActivity
import com.bringin.mysemah.R
import kotlinx.android.synthetic.main.activity_display_uhuy.*

class DisplayUhuy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_uhuy)
        button_kembali.setOnClickListener { finish() }
        title_activity_dd.setText(intent.getStringExtra(BaseActivity.TITLEMENU))
        val tit:String = intent.getStringExtra(BaseActivity.TITLEMENU)
        if(tit=="Keluarga"){
            tek_piew.text = getString(R.string.keluargaku_dat)
        }else if(tit=="Perawatan Diri"){
            tek_piew.text = getString(R.string.perawatan_dir)
        }else if(tit=="Kebersihan"){
            tek_piew.text = getString(R.string.kebersihan_dir)
        }else if(tit=="Nutrisi / Zat Gizi"){
            tek_piew.text = getString(R.string.nutrisi_zat_dir)
        }else if(tit=="Minum Obat"){
            tek_piew.text = getString(R.string.minum_obat_dir)
        }else if(tit=="Spiritual"){
            tek_piew.text = getString(R.string.spiritual_dir)
        }else if(tit=="Kenyamanan"){
            tek_piew.text = getString(R.string.kenyamanan_dir)
        }else if(tit=="Nyeri"){
            tek_piew.text = getString(R.string.nyeri_dir)
        }else if(tit=="Mual Muntah"){
            tek_piew.text = getString(R.string.mual_muntah_dir)
        }else if(tit=="Kelelahan"){
            tek_piew.text = getString(R.string.kelelahan_dir)
        }else if(tit=="Penurunan Nafsu Makan"){
            tek_piew.text = getString(R.string.penurunan_nafsu_makan_dir)
        }else if(tit=="Sesak Nafas"){
            tek_piew.text = getString(R.string.sesak_nafas_dir)
        }


        else{
            tek_piew.text = ""
        }

        //evd
    }
}
