package org.frigy.frigymobile.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import org.frigy.frigymobile.Activities.MainActivity
import org.frigy.frigymobile.Adapters.CheckinBasketItemAdapter
import org.frigy.frigymobile.R
import org.frigy.frigymobile.ViewModels.CheckinBasketViewModel

private const val PAGE_TITLE: String = "pageTitle"
private const val PAGE_NUMBER: String = "pageNumber"

class CheckInConfirmFragment : Fragment() {
    private var pageTitle: String = "title"
    private var pageNumber: Int = 0

    private lateinit var itemRecycler: RecyclerView
    private lateinit var viewAdapter: CheckinBasketItemAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: CheckinBasketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageTitle = it.getString(PAGE_TITLE)
            pageNumber = it.getInt(PAGE_NUMBER)
        }

        viewModel = ViewModelProviders.of(activity).get(CheckinBasketViewModel::class.java)
        viewModel.mBasketItems.observe(this, Observer { it -> viewAdapter.updateItems(it!!) })
        viewManager = LinearLayoutManager(activity)
        viewAdapter = CheckinBasketItemAdapter(activity, viewModel)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_checkin_confirm, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemRecycler = view!!.findViewById(R.id.confirm_recycler)
        itemRecycler.layoutManager = viewManager
        itemRecycler.adapter = viewAdapter

        val confirm_button = view.findViewById<Button>(R.id.confirm_button)
        confirm_button.setOnClickListener({ viewModel.commit(); backToMain() })
    }

    fun backToMain() {
        //TODO move to CheckInActivity, implement listener for this purpose there..
        val intent = Intent(activity, MainActivity::class.java)
        startActivityForResult(intent, Context.CONTEXT_INCLUDE_CODE)
    }


    companion object {
        @JvmStatic
        fun newInstance(pageTitle: String, pageNumber: Int) =
                CheckInConfirmFragment().apply {
                    arguments = Bundle().apply {
                        putString(PAGE_TITLE, pageTitle)
                        putInt(PAGE_NUMBER, pageNumber)
                    }
                }
    }
}
