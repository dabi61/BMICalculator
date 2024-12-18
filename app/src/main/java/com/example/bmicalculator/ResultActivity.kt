package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val height = intent.getStringExtra("height")?.toFloat()
        val weight = intent.getStringExtra("weight")?.toFloat()
        var type = ""
        var index = 0.0f
        if (height != null && weight != null) {
            index = weight / (height/100 * height/100)
            type = if(index < 18.5){
                "Thiếu cân"
            } else if(index < 22.9){
                "Bình thường"
            } else if (index < 24.9) {
                "Thừa cân"
            } else
                "Béo phì"
        }
        binding.tvResult.text = ("Chỉ số BMI của bạn là: $index và thuộc loại $type ")
        binding.btBack.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("result_data", "chỉ số BMI của bạn là: " + index + " và thuộc loại " + type + " ") // Gửi dữ liệu
            setResult(RESULT_OK, resultIntent)  // Gửi kết quả với mã RESULT_OK
            finish()
        }
    }
}