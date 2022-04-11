package edu.co.icesi.reto1_youtube.Viewholder


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.text.format.DateFormat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.reto1_youtube.Model.Publication
import edu.co.icesi.reto1_youtube.R
import edu.co.icesi.reto1_youtube.RetoYoutube
import java.time.Month

import java.util.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //UI Controllers
    var postTextRow : TextView = itemView.findViewById(R.id.postTextRow)
    var captionPostET: TextView = itemView.findViewById(R.id.captionPostET)
    var datePostET: TextView = itemView.findViewById(R.id.datePostET)
    var cityPostET: TextView = itemView.findViewById(R.id.cityPostET)
    var imagePost : ImageView = itemView.findViewById(R.id.imagePost)
    var profilePhotoPost : ImageView = itemView.findViewById(R.id.profilePhotoPost)
    var username : TextView = itemView.findViewById(R.id.username)

    //State

    init{

    }

    fun bind(post : Publication){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val monthNumber =
            Month.of(post.date.get(Calendar.MONTH)+1).toString()

            val day = post.date.get(Calendar.DAY_OF_MONTH).toString()
            val year = post.date.get(Calendar.YEAR).toString()
            datePostET.text = day + " " + monthNumber + " " + year

        } else {
            val dayOfTheWeek = DateFormat.format("EEEE", post.date.time) as String // Thursday
            val day = DateFormat.format("dd", post.date.time) as String // 20
            val monthString = DateFormat.format("MMM", post.date.time) as String // Jun
            val monthNumber = DateFormat.format("MM", post.date.time) as String // 06
            val year = DateFormat.format("yyyy", post.date.time) as String // 2013
            datePostET.text = day + " " + monthString+ " " + year
        }


        val bitmap = BitmapFactory.decodeFile(post.image)
        val thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.width/4, bitmap.height/4, true)


        postTextRow.text = RetoYoutube.myInformation.getUsersId(post.userId).name
        captionPostET.text = post.caption
        cityPostET.text = post.city
        imagePost.setImageBitmap(thumbnail)
        profilePhotoPost.setImageBitmap(BitmapFactory.decodeFile(RetoYoutube.myInformation.getUsersId(post.userId).image))
        username.text = RetoYoutube.myInformation.getUsersId(post.userId).username

    }
}