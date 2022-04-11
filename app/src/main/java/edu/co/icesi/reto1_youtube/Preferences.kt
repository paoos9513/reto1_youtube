package edu.co.icesi.reto1_youtube

import android.content.Context
import com.google.gson.Gson
import edu.co.icesi.reto1_youtube.Model.Publication
import edu.co.icesi.reto1_youtube.Model.User
import java.util.*

class Preferences(val context: Context) {

    val PREFERENCES_NAME ="Mydtb"
    val POST_SH = "posts"
    val database = context.getSharedPreferences(PREFERENCES_NAME, 0)
    var userIdentification : String? = null
    var userLoged = "LOGGED_USER"
    val REMIND_SH = "remember"
    val gson = Gson()

    fun newUser(){
        if(database.getString("ALFA","").toString().isEmpty()||database.getString("BETA","").toString().isEmpty()){

            val userAlfa = User(UUID.randomUUID().toString(),"Paola Alfa","paoos9513Alfa","Ingenieria de sistemas Alfa","")
            val userBeta = User(UUID.randomUUID().toString(),"Paola Beta","paoos9513Beta","Ingenieria de sistemas Beta","")
            val stringAlfa = gson.toJson(userAlfa)
            val stringBeta = gson.toJson(userBeta)
            database.edit().putString("ALFA", stringAlfa).apply()
            database.edit().putString("BETA", stringBeta).apply()
        }
        else return
    }

    fun userLogueo(user : String){
        database.edit().putString(userLoged, user).apply()
    }

    fun getUserLogueado(): User? {

        userIdentification = database.getString(userLoged,"").toString()

        if(userIdentification != null){
            val userString = database.getString(userIdentification,"")
            return gson.fromJson(userString, User::class.java)
        }

        else return null
    }

    fun userActualizado(user: User){

        val userToString = gson.toJson(user)
        database.edit().putString(userIdentification,userToString).apply()
    }

    fun logout(){
        database.edit().remove(userLoged).apply()
    }

    fun getUsersId(uuid: String): User {

        val alfaUser = gson.fromJson(database.getString("ALFA",""), User::class.java)
        val betaUser = gson.fromJson(database.getString("BETA",""), User::class.java)

        if(alfaUser.id.contentEquals(uuid)) return alfaUser
        return betaUser

    }
    fun remindSave(remember:Boolean){
        database.edit().putBoolean(REMIND_SH, remember).apply()
    }
    fun getRemind(): Boolean{
        return database.getBoolean(REMIND_SH, false)
    }

    fun savePublication(post: Publication){
       var publication = getPublication()
        publication.add(post)

        publication.sortByDescending {
            it.date
        }
        database.edit().putString(POST_SH, gson.toJson(publication)).apply()

    }

    fun getPublication() : MutableList<Publication>{
        var listPublication = mutableListOf<Publication>()
        val publications = database.getString(POST_SH,"")

        if(publications!!.isEmpty()) return listPublication
        listPublication = gson.fromJson(publications, Array<Publication>::class.java).toMutableList()
        return listPublication
    }
}