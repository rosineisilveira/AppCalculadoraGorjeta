package com.example.appcalculadoragorjeta

import android.icu.text.DecimalFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var valor               : EditText
    private lateinit var textValorPorcentagem: TextView
    private lateinit var seekBarPorcentagem  : SeekBar
    private lateinit var textGorjeta         : TextView
    private lateinit var textValorTotal      : TextView
    private var          porcentagem         : Double = 0.0
    val                  dec : java.text.DecimalFormat = java.text.DecimalFormat("#,##0.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        valor                = findViewById(R.id.valor)
        textValorPorcentagem = findViewById(R.id.textValorPorcentagem)
        seekBarPorcentagem   = findViewById(R.id.seekBarPorcentagem)
        textGorjeta          = findViewById(R.id.textValorGorjeta)
        textValorTotal       = findViewById(R.id.textValorTotal)

        seekBarPorcentagem.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                porcentagem = p1.toDouble()
                textValorPorcentagem.text = ("${Math.round(porcentagem)} %")
                calcular()
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
        })
    }
    fun calcular(){

        var valorRecuperado : String = valor.text.toString()

        if (valorRecuperado == null || valorRecuperado == ""){
            Toast.makeText(
                this,"É necessário informar um valor !!!",
                Toast.LENGTH_LONG).show()
        }else{
            var valorDigitado : Double = valorRecuperado.toDouble()
            var gorjeta                = valorDigitado * (porcentagem/100)
            var total : Double         = valorDigitado + gorjeta

            textGorjeta.text    = ("R$ ${dec.format(gorjeta)}")
            textValorTotal.text = ("R$ ${dec.format(total)}")
        }

    }
}