package edu.co.icesi.reto1_youtube.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.reto1_youtube.Model.Publication
import edu.co.icesi.reto1_youtube.R
import edu.co.icesi.reto1_youtube.Viewholder.PostViewHolder

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private val posts = mutableListOf<Publication>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        //Inflate: XML -->View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.postrow, parent, false)
        val postViewHolder = PostViewHolder(view)
        return postViewHolder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postn = posts[position]
       holder.bind(postn)
    }

    fun addPost(post: Publication){
        posts.add(post)
        posts.sortByDescending{
            it.date
        }
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun clear() {
        posts.clear()
    }
}