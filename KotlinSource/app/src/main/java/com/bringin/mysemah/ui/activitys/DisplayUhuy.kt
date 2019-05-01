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
        title_activity.setText(intent.getStringExtra(BaseActivity.TITLEMENU))
    }
}
