package org.frigy.frigymobile.Activities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import org.frigy.frigymobile.Adapters.CheckinBasketItemAdapter
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Models.Product
import org.frigy.frigymobile.Persistence.ProductRepository
import org.frigy.frigymobile.R
import org.frigy.frigymobile.ViewModels.CheckinBasketModel

class CheckInActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {

    private lateinit var mScannerView: ZBarScannerView
    private lateinit var itemBasketRecycler: RecyclerView
    private lateinit var viewAdapter: CheckinBasketItemAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var itemBasketModel: CheckinBasketModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO to Main Activity
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 0)
        }

        itemBasketModel = ViewModelProviders.of(this).get(CheckinBasketModel::class.java)
        itemBasketModel.mBasketItems.observe(this, Observer { it -> viewAdapter.updateItems(it!!) })

        mScannerView = ZBarScannerView(this)
        setContentView(R.layout.activity_check_in)
        val barcodeLayout: ViewGroup = findViewById(R.id.barcode_layout)

        val flashSwitch: Switch = findViewById(R.id.flashSwitch)

        flashSwitch.setOnCheckedChangeListener({ compoundButton: CompoundButton, b: Boolean -> mScannerView.flash = b })

        barcodeLayout.addView(mScannerView)
        //Hack for huawei phones
        //mScannerView.setAspectTolerance(0.5f);

        viewManager = LinearLayoutManager(this)
        viewAdapter = CheckinBasketItemAdapter(this)

        itemBasketRecycler = findViewById<RecyclerView>(R.id.checkin_recycler)
        itemBasketRecycler.layoutManager = viewManager
        itemBasketRecycler.adapter = viewAdapter

    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.setAutoFocus(true)
        mScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }

    override fun handleResult(result: Result?) {
        Toast.makeText(this, "Scan successful: " + result?.contents + " (" + result?.barcodeFormat?.name + ")", Toast.LENGTH_SHORT).show()
        val products: LiveData<List<Product>> = ProductRepository(this).searchByBarcode(result?.contents.toString())

        products.observe(this, (Observer<List<Product>> {
            if (products.value!!.isEmpty()) {
                Toast.makeText(this, "No products found for barcode: " + result?.contents, Toast.LENGTH_SHORT).show()

            } else if (products.value!!.size == 1) {
                itemBasketModel.createItemFromProduct(products.value!!.get(0))
            } else {
/*                for (product in products.value!!) {
                    resultView.append(product.toString())
                }*/
            }
        }))

        //TODO dont resume camera immediately
        mScannerView.resumeCameraPreview(this)
    }

}