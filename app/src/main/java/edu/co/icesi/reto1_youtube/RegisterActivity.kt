package edu.co.icesi.reto1_youtube

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterActivity : AppCompatActivity() {

    private lateinit var signin_link_btn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        signin_link_btn = findViewById(R.id.signin_link_btn)

        signin_link_btn.paintFlags = signin_link_btn.paintFlags or Paint.UNDERLINE_TEXT_FLAG


        signin_link_btn.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}