package com.example.debugging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.debugging.databinding.ActivityMainBinding
import java.text.NumberFormat

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }
    fun calculateTip(){
        val cost = binding.costOfServiceInput.text.toString().toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(selectedId){
            R.id.option_15percent -> 0.15
            R.id.option_18percent -> 0.18
            R.id.option_20percent -> 0.20
            else -> 0.18
        }
        var tip = tipPercentage * cost
        if(binding.roundOffSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }
        var formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.result.text = getString(R.string.result_text,formattedTip)
    }
}