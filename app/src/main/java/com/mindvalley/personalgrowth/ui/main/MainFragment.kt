package com.mindvalley.personalgrowth.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mindvalley.personalgrowth.App
import com.mindvalley.personalgrowth.R
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.saveCategories()

        viewModel.name.observe(this, Observer {

        })

        viewModel.channelCategories.observe(this, Observer {

            it?.let {
                message.text = it.data.categories[1].name
                var names = ""
                it.data.categories.forEach {
                    names = names + it.name + " "
                }
                message.text = names
            }
        })
    }

}