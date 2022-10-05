package com.personalgrowth.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.personalgrowth.R
import com.personalgrowth.database.entity.ChannelCategory
import com.personalgrowth.database.entity.Channels
import com.personalgrowth.database.entity.NewEpisodes
import com.personalgrowth.databinding.MainFragmentBinding
import com.personalgrowth.ui.adapter.CategoriesAdapter
import com.personalgrowth.ui.adapter.ChannelAdapter
import com.personalgrowth.ui.adapter.NewEpisodesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var newEpisodesAdapter: NewEpisodesAdapter
    private lateinit var channelAdapter: ChannelAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var rootView: MainFragmentBinding
    private var mainFragmentBinding: MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = MainFragmentBinding.inflate(inflater, container, false)

        mainFragmentBinding = rootView

        newEpisodesAdapter = NewEpisodesAdapter()
        rootView.channelContent.newEpisodesRcl.adapter = newEpisodesAdapter

        channelAdapter = ChannelAdapter()
        rootView.channelContent.channelRcl.adapter = channelAdapter

        categoriesAdapter = CategoriesAdapter()
        rootView.channelCategoryContent.channelCategoryRcl.adapter = categoriesAdapter

        rootView.refreshSrl.setOnRefreshListener {
            viewModel.processNewEpisodes()
            viewModel.processChannels()
            viewModel.processCategories()
        }

        return rootView.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.newEpisodes.observe(viewLifecycleOwner) {

            emptyEpisodes(it)

            it?.data?.media?.let { courseList ->

                newEpisodesAdapter.submitList(courseList)

                rootView.refreshSrl.isRefreshing = false
                rootView.shimmerChannelContent.shimmerEpisode.isVisible = false
                rootView.browseShimmerCategoryTxt.isVisible = false
                rootView.shimmerChannelContent.root.isVisible = true
                rootView.browseCategoryTxt.isVisible = true

            }
        }

        viewModel.channels.observe(viewLifecycleOwner) {

            emptyChannel(it)

            it?.data?.channels?.let { channelItemList ->

                channelAdapter.submitList(channelItemList)

                rootView.refreshSrl.isRefreshing = false
                rootView.shimmerChannelContent.shimmerChannel.isVisible = false
                rootView.browseShimmerCategoryTxt.isVisible = false
                rootView.channelContent.root.isVisible = true
                rootView.browseCategoryTxt.isVisible = true

            }
        }

        viewModel.channelCategories.observe(viewLifecycleOwner) {

            emptyChannelCategory(it)

            it?.data?.categories?.let { categoryNamesList ->

                categoriesAdapter.submitList(categoryNamesList)

                rootView.refreshSrl.isRefreshing = false
                rootView.shimmerChannelCategoryContent.shimmerChannelCategory.isVisible = false
                rootView.browseShimmerCategoryTxt.isVisible = false
                rootView.channelCategoryContent.root.isVisible = true
                rootView.browseCategoryTxt.isVisible = true

            }
        }

        viewModel.newEpisodesErrorLD.observe(viewLifecycleOwner) {

            it?.let {
                rootView.refreshSrl.isRefreshing = false
                Snackbar.make(
                    rootView.contentNsv,
                    getString(R.string.unstable_internet_message),
                    Snackbar.LENGTH_LONG
                ).show()
            }

        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun emptyChannelCategory(it: ChannelCategory?) {
        if (it == null) {
            rootView.shimmerChannelCategoryContent.root.isVisible = true
            rootView.browseShimmerCategoryTxt.isVisible = true
            rootView.shimmerChannelCategoryContent.shimmerChannelCategory.startShimmer()
        }
    }

    private fun emptyEpisodes(it: NewEpisodes?) {
        if (it == null) {
            rootView.shimmerChannelContent.root.isVisible = true
            rootView.browseShimmerCategoryTxt.isVisible = true
            rootView.shimmerChannelContent.shimmerEpisode.startShimmer()
        }
    }

    private fun emptyChannel(it: Channels?) {
        if (it == null) {
            rootView.shimmerChannelContent.root.isVisible = true
            rootView.browseShimmerCategoryTxt.isVisible = true
            rootView.shimmerChannelContent.shimmerChannel.startShimmer()
        }
    }

    override fun onDestroyView() {
        mainFragmentBinding = null
        super.onDestroyView()
    }

}