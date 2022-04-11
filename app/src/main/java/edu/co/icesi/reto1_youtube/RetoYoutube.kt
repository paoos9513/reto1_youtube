package edu.co.icesi.reto1_youtube

import android.app.Application

class RetoYoutube:Application() {

    companion object{
        lateinit var myInformation : Preferences
    }
    override fun onCreate() {
        super.onCreate()
        myInformation = Preferences(applicationContext)
        myInformation.newUser()
    }
}