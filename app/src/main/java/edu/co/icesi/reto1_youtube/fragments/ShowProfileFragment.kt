package edu.co.icesi.reto1_youtube.fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.co.icesi.reto1_youtube.ConfigurationActivity
import edu.co.icesi.reto1_youtube.RetoYoutube.Companion.myInformation
import edu.co.icesi.reto1_youtube.databinding.FragmentShowProfileBinding

class ShowProfileFragment : Fragment() {
    private var _binding:FragmentShowProfileBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            _binding = FragmentShowProfileBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.editAccountBtn.setOnClickListener{
            startActivity(Intent(context, ConfigurationActivity::class.java))
        }
        checkDetails()
        return view
    }

    fun checkDetails(){
        binding.fullNameProfilefragment.setText(myInformation.getUserLogueado()?.username)
        binding.bioProfilefragment.setText(myInformation.getUserLogueado()?.bio)
        binding.profileimageProfilefragment.setImageBitmap(BitmapFactory.decodeFile(myInformation.getUserLogueado()!!.image))
        binding.profileFragment.setText(myInformation.getUserLogueado()?.username)
    }

    override fun onPause() {
        super.onPause()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ShowProfileFragment()
    }
}