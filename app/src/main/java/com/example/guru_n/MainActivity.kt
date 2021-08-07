package com.example.guru_n

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.guru_n.swu_in.*
import com.example.guru_n.swu_in.food.FoodResult1Activity
import com.example.guru_n.swu_out.*

class MainActivity : AppCompatActivity() {


    //외부
    private lateinit var btnMap_food : Button
    private lateinit var btnMap_cafe : Button
    private lateinit var btnMap_walk : Button
    private lateinit var btnMap_play : Button
    private lateinit var btnMap_study : Button

    //내부
    private lateinit var btnMap_food_in : Button
    private lateinit var btnMap_cafe_in : Button
    private lateinit var btnMap_photo_in : Button
    private lateinit var btnMap_rest_in : Button
    private lateinit var btnMap_study_in : Button

    private lateinit var add : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //외부
        btnMap_food = findViewById(R.id.btnMap_food)
        btnMap_cafe = findViewById(R.id.btnMap_cafe)
        btnMap_play = findViewById(R.id.btnMap_play)
        btnMap_study = findViewById(R.id.btnMap_study)
        btnMap_walk = findViewById(R.id.btnMap_walk)
        //내부
        btnMap_food_in = findViewById(R.id.btnMap_food_in)
        btnMap_cafe_in = findViewById(R.id.btnMap_cafe_in)
        btnMap_photo_in = findViewById(R.id.btnMap_photo_in)
        btnMap_rest_in = findViewById(R.id.btnMap_rest_in)
        btnMap_study_in = findViewById(R.id.btnMap_study_in)

        add = findViewById(R.id.add)

        // 외부
        btnMap_food.setOnClickListener {
            var intent = Intent(this, FoodMapFragmentActivity::class.java)
            startActivity(intent)
        }
        btnMap_cafe.setOnClickListener {
            var intent = Intent(this, CafeMapFragmentActivity::class.java)
            startActivity(intent)
        }
        btnMap_play.setOnClickListener {
            var intent = Intent(this, PlayMapFragmentActivity::class.java)
            startActivity(intent)
        }
        btnMap_study.setOnClickListener {
            var intent = Intent(this, StudyMapFragmentActivity::class.java)
            startActivity(intent)
        }
        btnMap_walk.setOnClickListener {
            var intent = Intent(this, WalkMapFragmentActivity::class.java)
            startActivity(intent)
        }

        //내부
        btnMap_food_in.setOnClickListener {
            var intent = Intent(this, In_FoodMapFragmentActivity::class.java)
            startActivity(intent)
        }
        btnMap_cafe_in.setOnClickListener {
            var intent = Intent(this, In_CafeMapFragmentActivity::class.java)
            startActivity(intent)
        }
        btnMap_photo_in.setOnClickListener {
            var intent = Intent(this, In_PhotoMapFragmentActivity::class.java)
            startActivity(intent)
        }
        btnMap_study_in.setOnClickListener {
            var intent = Intent(this, In_StudyMapFragmentActivity::class.java)
            startActivity(intent)
        }
        btnMap_rest_in.setOnClickListener {
            var intent = Intent(this, In_RestMapFragmentActivity::class.java)
            startActivity(intent)
        }

        add.setOnClickListener {
            showSettingPopup()
            //FoodbtnList4.visibility = View.VISIBLE // 화면에 보이게 한다.
        }
    }

    private fun showSettingPopup(){
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.test, null)

        val alertDialog = AlertDialog.Builder(this)
                .create()

        alertDialog.setView(view)
        alertDialog.show()

        val btnO = view.findViewById<Button>(R.id.btnO)
        val EditText = view.findViewById<EditText>(R.id.input)
        // 버튼 생성
        val buttonview = findViewById<LinearLayout>(R.id.testfood)
        val button = Button(this)
        // 버튼 속성
        /*button.text = EditText.text.toString()
        button.setBackgroundResource(R.drawable.button_background)
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, changeDP(85))
        lp.setMargins(changeDP(10), changeDP(10), changeDP(10), 0)
        button.textSize= 23.0F
        button.setTextColor(Color.parseColor("#FF535353"))
        button.layoutParams = lp*/
        buttonview.addView(button)

        button.setOnClickListener {
            var intent = Intent(this, FoodResult1Activity::class.java)
            intent.putExtra("name", EditText.text.toString())
            startActivity(intent)
        }
        btnO.setOnClickListener {
            val buttonview = findViewById<LinearLayout>(R.id.testfood)

            val button = Button(this)
            // 버튼 속성
            /*button.text = EditText.text.toString()
            button.setBackgroundResource(R.drawable.button_background)
            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, changeDP(85))
            lp.setMargins(changeDP(10), changeDP(10), changeDP(10), 0)
            button.textSize= 23.0F
            button.setTextColor(Color.parseColor("#FF535353"))
            button.layoutParams = lp
            buttonview.addView(button)*/

            button.setOnClickListener {
                var intent = Intent(this, FoodResult1Activity::class.java)
                intent.putExtra("name", EditText.text.toString())
                startActivity(intent)
            }

            //FoodbtnList4.text = EditText.text.toString()
            /*Toast.makeText(applicationContext, "Pushed save", Toast.LENGTH_SHORT).show()
            var intent = Intent()
            intent.putExtra("result", "Close Popup")
            setResult(RESULT_OK, intent)*/

            alertDialog.dismiss()

        }
    }


    private fun changeDP(value : Int) : Int{
        var displayMetrics = resources.displayMetrics
        var dp = Math.round(value * displayMetrics.density)
        return dp
    }
}