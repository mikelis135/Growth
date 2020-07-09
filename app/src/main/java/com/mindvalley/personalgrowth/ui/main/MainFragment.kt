package com.mindvalley.personalgrowth.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.mindvalley.personalgrowth.App
import com.mindvalley.personalgrowth.R
import com.mindvalley.personalgrowth.ui.adapter.CategoriesAdapter
import com.mindvalley.personalgrowth.ui.adapter.ChannelAdapter
import com.mindvalley.personalgrowth.ui.adapter.NewEpisodesAdapter
import kotlinx.android.synthetic.main.channel_category_content.view.*
import kotlinx.android.synthetic.main.channel_content.view.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import kotlinx.android.synthetic.main.shimmer_channel_category_content.view.*
import kotlinx.android.synthetic.main.shimmer_channel_content.view.*
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var newEpisodesAdapter: NewEpisodesAdapter
    private lateinit var channelAdapter: ChannelAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var rootView: View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = inflater.inflate(R.layout.main_fragment, container, false)

        rootView.refreshSrl.setOnRefreshListener {
            viewModel.processNewEpisodes()
            viewModel.processChannels()
            viewModel.processCategories()
        }

        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.newEpisodes.observe(this, Observer {

            if (it == null) {
                rootView.shimmerChannelContent.visibility = View.VISIBLE
                rootView.browseShimmerCategoryTxt.visibility = View.VISIBLE
                rootView.shimmerEpisode.startShimmer()
            }

            it?.data?.media?.let { courseList ->

                rootView.refreshSrl.isRefreshing = false
                rootView.shimmerEpisode.visibility = View.GONE
                rootView.browseShimmerCategoryTxt.visibility = View.GONE
                rootView.shimmerChannelContent.visibility = View.VISIBLE
                rootView.browseCategoryTxt.visibility = View.VISIBLE

                newEpisodesAdapter = NewEpisodesAdapter(requireContext(), courseList)
                rootView.newEpisodesRcl.adapter = newEpisodesAdapter

            }
        })

        viewModel.channels.observe(this, Observer {

            if (it == null) {
                rootView.shimmerChannelContent.visibility = View.VISIBLE
                rootView.browseShimmerCategoryTxt.visibility = View.VISIBLE
                rootView.shimmerChannel.startShimmer()
            }

            it?.data?.channels?.let { channelItemList ->

                rootView.refreshSrl.isRefreshing = false
                rootView.shimmerChannel.visibility = View.GONE
                rootView.browseShimmerCategoryTxt.visibility = View.GONE
                rootView.channelContent.visibility = View.VISIBLE
                rootView.browseCategoryTxt.visibility = View.VISIBLE

                channelAdapter = ChannelAdapter(requireContext(), channelItemList)
                rootView.channelRcl.adapter = channelAdapter

            }
        })

        viewModel.channelCategories.observe(this, Observer {

            if (it == null) {
                rootView.shimmerChannelCategoryContent.visibility = View.VISIBLE
                rootView.browseShimmerCategoryTxt.visibility = View.VISIBLE
                rootView.shimmerChannelCategory.startShimmer()
            }

            it?.data?.categories?.let { categoryNamesList ->

                rootView.refreshSrl.isRefreshing = false
                rootView.shimmerChannelCategory.visibility = View.GONE
                rootView.browseShimmerCategoryTxt.visibility = View.GONE
                rootView.channelCategoryContent.visibility = View.VISIBLE
                rootView.browseCategoryTxt.visibility = View.VISIBLE

                categoriesAdapter = CategoriesAdapter(requireContext(), categoryNamesList)
                rootView.channelCategoryRcl.adapter = categoriesAdapter

            }
        })

        viewModel.newEpisodesError.observe(this, Observer {

            it?.let {
                rootView.refreshSrl.isRefreshing = false
                Snackbar.make(
                    rootView.contentNsv,
                    "Unstable internet? Pull to refresh",
                    Snackbar.LENGTH_LONG
                ).show()
            }

        })

    }

}