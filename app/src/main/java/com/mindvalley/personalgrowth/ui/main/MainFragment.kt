package com.mindvalley.personalgrowth.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindvalley.personalgrowth.App
import com.mindvalley.personalgrowth.R
import com.mindvalley.personalgrowth.ui.adapter.CategoriesAdapter
import com.mindvalley.personalgrowth.ui.adapter.ChannelAdapter
import com.mindvalley.personalgrowth.ui.adapter.NewEpisodesAdapter
import kotlinx.android.synthetic.main.main_fragment.view.*
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel
    lateinit var newEpisodesAdapter: NewEpisodesAdapter
    lateinit var channelAdapter: ChannelAdapter
    lateinit var categoriesAdapter: CategoriesAdapter
    lateinit var rootView: View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = inflater.inflate(R.layout.main_fragment, container, false)

        rootView.newEpisodesRcl.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rootView.channelRcl.layoutManager =
            LinearLayoutManager(requireContext())
//        rootView.categoryRcl.layoutManager =
//            LinearLayoutManager(requireContext())

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.name.observe(this, Observer {

        })

        viewModel.newEpisodes.observe(this, Observer {
            it?.data?.media?.let {

                newEpisodesAdapter = NewEpisodesAdapter(requireContext(), it)
                rootView.newEpisodesRcl.adapter = newEpisodesAdapter

            }
        })

        viewModel.channels.observe(this, Observer {

            it?.data?.channels?.let {

                channelAdapter = ChannelAdapter(requireContext(), it)
                rootView.channelRcl.adapter = channelAdapter

            }
        })


        viewModel.channelCategories.observe(this, Observer {

            it?.data?.categories?.let {

                categoriesAdapter = CategoriesAdapter(requireContext(), it)
                rootView.categoryRcl.adapter = categoriesAdapter

            }
        })
    }

}