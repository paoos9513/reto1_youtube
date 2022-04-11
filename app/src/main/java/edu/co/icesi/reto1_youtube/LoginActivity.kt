package edu.co.icesi.reto1_youtube

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import edu.co.icesi.reto1_youtube.RetoYoutube.Companion.myInformation
import edu.co.icesi.reto1_youtube.fragments.NavigationActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginId : EditText
    private lateinit var passwordId : EditText
    private lateinit var textInformationError : TextView
    private lateinit var loginBtnId : Button
    private lateinit var signupBtnId :Button
    private lateinit var remindId : CheckBox

    var isRemind = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginId = findViewById(R.id.loginId)
        passwordId = findViewById(R.id.passwordId)
        textInformationError = findViewById(R.id.textInformationError)
        loginBtnId = findViewById(R.id.loginBtnId)
        signupBtnId = findViewById(R.id.signupBtnId)
        remindId = findViewById(R.id.remindId)
        signupBtnId.paintFlags = signupBtnId.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        isRemind = myInformation.getRemind()

        if(isRemind){
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
            finish()
        }
        signupBtnId.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        loginBtnId.setOnClickListener {
            val account : String = loginId.text.toString()
            val password : String = passwordId.text.toString()
            val check : Boolean = remindId.isChecked

            if((account =="alfa@gmail.com" || account == "beta@gmail.com") && password == "aplicacionesmoviles"){
                if(account=="alfa@gmail.com"){

                    myInformation.remindSave(check)
                    myInformation.userLogueo("ALFA")
                    Toast.makeText(this, "Informacion guardada!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, NavigationActivity::class.java )
                    startActivity(intent)
                    finish()
                }else{

                    myInformation.userLogueo("BETA")
                    myInformation.remindSave(check)

                    Toast.makeText(this, "Informacion guardada!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, NavigationActivity::class.java )
                    startActivity(intent)
                    finish()
                }
            }else{
                val credenciaError = "Email or password Incorrect"
                textInformationError.setText(credenciaError)

            }
        }
    }
}