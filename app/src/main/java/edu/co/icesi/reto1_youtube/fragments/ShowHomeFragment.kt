package edu.co.icesi.reto1_youtube.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import edu.co.icesi.reto1_youtube.Adapter.PostAdapter
import edu.co.icesi.reto1_youtube.HomeViewModel
import edu.co.icesi.reto1_youtube.Model.Publication
import edu.co.icesi.reto1_youtube.databinding.FragmentShowHomeBinding

class ShowHomeFragment : Fragment(), ShowAddFragment.OnNewPostListener {

    private var _binding:FragmentShowHomeBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel : HomeViewModel
    private val adapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentShowHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        val publicationRecycler = binding.recyclerHomeId
        publicationRecycler.setHasFixedSize(true)
        publicationRecycler.layoutManager = LinearLayoutManager(activity)
        publicationRecycler.adapter = adapter
        viewModel= ViewModelProvider(this).get(HomeViewModel::class.java)

       viewModel.publication.observe(viewLifecycleOwner) {
            adapter.clear()
            for (post in it) {
                adapter.addPost(post)
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
          @JvmStatic
        fun newInstance() =
            ShowHomeFragment()
    }

    override fun onNewPost(post: Publication) {
        adapter.addPost(post)
    }
}