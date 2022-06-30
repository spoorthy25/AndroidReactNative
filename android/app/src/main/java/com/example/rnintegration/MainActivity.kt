package com.example.rnintegration

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {
    private val OVERLAY_PERMISSION_REQ_CODE = 1212

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testReact()
    }

    private fun testReact() {
        val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout
        val editText = findViewById<EditText>(R.id.editText)
        val sendButton = findViewById<Button>(R.id.sendMessage)
        val button = Button(this)
        button.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        button.text = "Send Message to React Native"
        sendButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ReactRenderActivity::class.java)
            intent.putExtra("message_from_native", editText.text)
            startActivity(intent)
        })
        button.setBackgroundColor(Color.GREEN)
        button.setTextColor(Color.RED)

    }

}