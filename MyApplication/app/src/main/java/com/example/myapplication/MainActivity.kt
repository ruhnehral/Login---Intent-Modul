package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        setContentView(R.layout.activity_main)
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    private fun toDouble(str: String) : Double?{
        return try{
            str.toDouble()
        }catch (e: NumberFormatException){
            0.0
        }
    }
    override fun onClick(v: View) {
        val inputLength: String = edtLength.getText().toString().trim()
        val inputWidth: String = edtWidth.getText().toString().trim()
        val inputHeight: String = edtHeight.getText().toString().trim()
        var isEmptyField: Boolean = false
        var isInvalidDouble: Boolean = false
        if (TextUtils.isEmpty(inputLength)) {
            isEmptyField = true
            edtLength.setError("Field ini tidak boleh kosong")
        }

        if (TextUtils.isEmpty(inputWidth)) {
            isEmptyField = true
            edtWidth.setError("Field ini tidak boleh kosong")
        }

        if (TextUtils.isEmpty(inputHeight)) {
            isEmptyField = true
            edtHeight.setError("Field ini tidak boleh kosong")
        }

        val length: Double = toDouble(inputLength)!!
        val width: Double = toDouble(inputWidth)!!
        val Height: Double = toDouble(inputHeight)!!

        if (length == 0.0) {
            isInvalidDouble = true
            edtLength.setError("Field ini harus berupa nomor yang valid")
        }

        if (width == 0.0) {
            isInvalidDouble = true
            edtWidth.setError("Field ini harus berupa nomor yang valid")
        }

        if (Height == 0.0) {
            isInvalidDouble = true
            edtHeight.setError("Field ini harus berupa nomor yang valid")
        }

        if (!isEmptyField && !isInvalidDouble) {
            var volume: Double = length * width * Height
            tvResult.setText(volume.toString())

        }
    }
}
