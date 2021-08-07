package com.example.guru_n.swu_in

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.NonNull
import com.example.guru_n.R
import com.example.guru_n.swu_in.food.FoodResult1Activity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginLeft


class In_FoodMapFragmentActivity : Activity(), OnMapReadyCallback {

    // 버튼 변수
    private lateinit var FoodbtnList1 : Button
    //private lateinit var FoodbtnList4 : Button
    private lateinit var BtnPlus : Button // 팝업 버튼

    private lateinit var mapView: MapView
    private val LOCATION_PERMISSTION_REQUEST_CODE : Int = 1000;
    private lateinit var locationSource : FusedLocationSource // 위치를 반환하는 구현체
    private lateinit var naverMap: NaverMap
    private val marker = Marker()
    private val marker1 = Marker()
    private val marker2 = Marker()
    private val marker3 = Marker()
    //설명창
    private val infoWindow1 = InfoWindow()
    private val infoWindow2 = InfoWindow()
    private val infoWindow3 = InfoWindow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.in_foodfragment_map)

        /*var View : ConstraintLayout = findViewById(R.id.in_foodfragment_map)
        var button : Button = Button(this)
        View.addView(button)*/

        // 세부정보 버튼 연결
        FoodbtnList1 = findViewById(R.id.InFoodbtnList1)
        //FoodbtnList4 = findViewById(R.id.InFoodbtnList4)
        BtnPlus = findViewById(R.id.btnPlus)

        mapView = findViewById(R.id.InFoodmap_view)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSTION_REQUEST_CODE)

        // 리스트 버튼 누르면 좌표, 장소이름 전달
        FoodbtnList1.setOnClickListener {
            var intent = Intent(this, FoodResult1Activity::class.java)
            intent.putExtra("name","아딸 떡볶이")
            intent.putExtra("lat",37.62590786013134)
            intent.putExtra("lng",127.09302582011024)
            startActivity(intent)
        }

        // FoodbtnList4.setVisibility(View.VISIBLE); // 화면에 보이게 한다.
        //FoodbtnList4.visibility = View.GONE // 화면에 안보이게 한다.

        BtnPlus.setOnClickListener {
            showSettingPopup()
            //FoodbtnList4.visibility = View.VISIBLE // 화면에 보이게 한다.
        }
    }

    // 팝업창
    private fun showSettingPopup(){
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.test, null)

        val alertDialog = AlertDialog.Builder(this)
                .create()

        alertDialog.setView(view)
        alertDialog.show()

        val btnO = view.findViewById<Button>(R.id.btnO)
        val EditText = view.findViewById<EditText>(R.id.input)

        btnO.setOnClickListener {
            var View : LinearLayout = findViewById(R.id.testfood)
            var button  = Button(this)
            View.addView(button)
            button.text = EditText.text.toString()
            button.setBackgroundResource(R.drawable.button_background)
            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, changeDP(85))
            lp.setMargins(changeDP(10), changeDP(10), changeDP(10), 0)
            button.textSize= 23.0F
            button.setTextColor(Color.parseColor("#FF535353"))
            button.layoutParams = lp

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

    /*override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기
                var result : String? = data.getStringExtra("result")
                FoodbtnList4.text = result
            }
        }
    }*/

    override fun onMapReady(@NonNull naverMap:NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        //////////////////////////////  마커 띄우기 ////////////////////////////
        // 현재 위치 마커
        marker.position = LatLng(37.6281, 127.0905)
        marker.map = naverMap
        marker.icon = MarkerIcons.BLACK
        marker.iconTintColor = Color.RED // 마커 색 빨간색으로 변경

        val cameraPosition = CameraPosition(
            LatLng(37.6281, 127.0905),  // 위치 지정
            15.0 // 줌 레벨
        )
        naverMap.cameraPosition = cameraPosition

        // 장소 리스트 마커
        marker1.position = LatLng(37.62590786013134, 127.09302582011024)
        marker1.map = naverMap
        marker1.captionText = "아딸 떡볶이"
        marker2.position = LatLng(37.628675313504935, 127.09029240920893)
        marker2.map = naverMap
        marker2.captionText = "츄밥"
        marker3.position = LatLng(37.62790614488822, 127.09041485870739)
        marker3.map = naverMap
        marker3.captionText = "팬도로시"

        /////////////////////// 정보창 띄우기 ///////////////
        infoWindow1.adapter = object : InfoWindow.DefaultTextAdapter(application) {
            override fun getText(infoWindow: InfoWindow): CharSequence {
                return "분식"
            }
        }
        infoWindow2.adapter = object : InfoWindow.DefaultTextAdapter(application) {
            override fun getText(infoWindow: InfoWindow): CharSequence {
                return "주먹밥"
            }
        }
        infoWindow3.adapter = object : InfoWindow.DefaultTextAdapter(application) {
            override fun getText(infoWindow: InfoWindow): CharSequence {
                return "파스타, 카페"
            }
        }
        infoWindow1.open(marker1)
        infoWindow2.open(marker2)
        infoWindow3.open(marker3)  // 정보창 열기

        // 마커를 클릭하면:
        val listener = Overlay.OnClickListener { overlay ->
            val markerA = overlay as Marker

            if (markerA.infoWindow == null) {
                // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                infoWindow1.open(marker1)
                infoWindow2.open(marker2)
                infoWindow3.open(marker3)
            } else {
                // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                infoWindow1.close()
                infoWindow2.close()
                infoWindow3.close()
            }

            true
        }

        marker1.onClickListener = listener
        marker2.onClickListener = listener
        marker3.onClickListener = listener

    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    fun mOnPopupClick(view: View) {}
}