package com.sfedorchuk.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sfedorchuk.R
import com.sfedorchuk.adapters.MoviesAdapter
import com.sfedorchuk.view.MoviesItem

class MoviesListFragment(private val items: ArrayList<MoviesItem>) : Fragment() {
    companion object {
        const val TAG = "debug"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val orientation: Int = resources.configuration.orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            val layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = layoutManager
        } else {
            recyclerView.layoutManager = GridLayoutManager(view.context, 2)
        }
        recyclerView
            .adapter = MoviesAdapter(
            LayoutInflater.from(requireContext()),
            items,
            (activity as? MoviesAdapter.MoviesClickListener?)
        )
    }
}