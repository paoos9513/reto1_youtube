package edu.co.icesi.reto1_youtube.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import edu.co.icesi.reto1_youtube.R
import edu.co.icesi.reto1_youtube.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    private lateinit var showHomeFrag : ShowHomeFragment
    private lateinit var showPublicationFrag : ShowAddFragment
    private lateinit var showProfileFrag: ShowProfileFragment
    private lateinit var theBinding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        theBinding = ActivityNavigationBinding.inflate(layoutInflater)
        val view = theBinding.root
        setContentView(view)
        showHomeFrag = ShowHomeFragment.newInstance()
        showPublicationFrag = ShowAddFragment.newInstance()
        showProfileFrag = ShowProfileFragment.newInstance()

        showFragment(showHomeFrag)
       theBinding.navigationBarId.setOnNavigationItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.homeitem){
                showFragment(showHomeFrag)
            }else if(menuItem.itemId == R.id.postitem){
                showFragment(showPublicationFrag)
            }else if(menuItem.itemId == R.id.profileitem){
                showFragment(showProfileFrag)
            }
            true
        }
    }

    private fun showFragment(fragment: Fragment){
        val changeFragments = supportFragmentManager.beginTransaction()
        changeFragments.replace(R.id.fragmentContainer, fragment)
        changeFragments.commit()
    }
}