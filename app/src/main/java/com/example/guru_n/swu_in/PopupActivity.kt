package com.example.guru_n.swu_in

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.guru_n.R
import org.jetbrains.annotations.NotNull


internal class PopupActivity : Activity() {

    private lateinit var txtText: TextView
    lateinit var input:EditText
    lateinit var btnO : Button

     override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.test)

        //UI 객체생성
        txtText = findViewById(R.id.txtText)
        input = findViewById(R.id.input)
        btnO = findViewById(R.id.btnO)

        btnO.setOnClickListener{
            var intent : Intent = Intent()
            intent.putExtra("result", input.text.toString())
            setResult(RESULT_OK, intent)
        }

        //데이터 가져오기
        //var intent : Intent = getIntent ()
        //String data = intent.getStringExtra "data"
        //txtText.setText(data)
    }

}