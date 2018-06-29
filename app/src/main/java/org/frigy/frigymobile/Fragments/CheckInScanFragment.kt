package org.frigy.frigymobile.Fragments

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import org.frigy.frigymobile.Adapters.CheckinBasketItemAdapter
import org.frigy.frigymobile.Models.Product
import org.frigy.frigymobile.Persistence.ProductRepository
import org.frigy.frigymobile.R
import org.frigy.frigymobile.ViewModels.CheckinBasketViewModel

private const val PAGE_TITLE: String = "pageTitle"
private const val PAGE_NUMBER: String = "pageNumber"

class CheckInScanFragment : Fragment(), ZBarScannerView.ResultHandler {

    private var pageTitle: String = "title"
    private var pageNumber: Int = 0
    private var isViewLoaded: Boolean = false
    //TODO prevent exeption Camera is being used after Camera.release() was called
    // private var isCameraActive: Boolean = false


    private lateinit var mScannerView: ZBarScannerView
    private lateinit var itemRecycler: RecyclerView
    private lateinit var viewAdapter: CheckinBasketItemAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: CheckinBasketViewModel


    private val timedHandler: Handler = Handler()


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
        return inflater.inflate(R.layout.fragment_check_in, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mScannerView = ZBarScannerView(view!!.context)
        val barcodeLayout: ViewGroup = view.findViewById(R.id.barcode_layout)
        barcodeLayout.addView(mScannerView)

        val flashSwitch: Switch = view.findViewById(R.id.flashSwitch)
        flashSwitch.setOnCheckedChangeListener({ compoundButton: CompoundButton, b: Boolean -> mScannerView.flash = b })

        itemRecycler = view.findViewById(R.id.checkin_recycler)
        itemRecycler.layoutManager = viewManager
        itemRecycler.adapter = viewAdapter
        isViewLoaded = true;
    }


    override fun onResume() {
        super.onResume()
        startCamera()
    }

    override fun onPause() {
        super.onPause()
        stopCamera()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isViewLoaded = false;
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isViewLoaded) {
            if (isVisibleToUser) {
                startCamera()
            } else {
                stopCamera()
            }
        }
    }

    private fun startCamera() {
        mScannerView.setResultHandler(this)
        mScannerView.setAutoFocus(true)
        mScannerView.startCamera()
    }

    private fun stopCamera() {
        mScannerView.stopCamera()
    }

    private fun resumeCameraPreview() {
        mScannerView.resumeCameraPreview(this)
    }

    override fun handleResult(result: Result?) {
        Toast.makeText(activity, "Scan successful: " + result?.contents + " (" + result?.barcodeFormat?.name + ")", Toast.LENGTH_SHORT).show()
        val products: LiveData<List<Product>> = ProductRepository(activity).searchByBarcode(result?.contents.toString())

        products.observe(this, (Observer<List<Product>> {
            var success: Boolean = false
            if (products.value!!.isEmpty()) {
                Toast.makeText(activity, "No products found for barcode: " + result?.contents, Toast.LENGTH_SHORT).show()
            } else if (products.value!!.size == 1) {
                viewModel.createItemFromProduct(products.value!!.get(0))
                success = true
            } else {
                //TODO multiple products found
/*                for (product in products.value!!) {
                    resultView.append(product.toString())
                }*/
                success = true
            }

            if (!success) {
                resumeCameraPreview()
            } else {
                timedHandler.postDelayed({ resumeCameraPreview() }, 2000)
            }

        }))

    }

    companion object {
        @JvmStatic
        fun newInstance(pageTitle: String, pageNumber: Int) =
                CheckInScanFragment().apply {
                    arguments = Bundle().apply {
                        putString(PAGE_TITLE, pageTitle)
                        putInt(PAGE_NUMBER, pageNumber)
                    }
                }
    }

}