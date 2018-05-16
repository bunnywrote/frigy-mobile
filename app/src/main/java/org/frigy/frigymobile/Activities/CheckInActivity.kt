package org.frigy.frigymobile.Activities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import org.frigy.frigymobile.Models.Product
import org.frigy.frigymobile.Models.dto.FoodrepoProduct
import org.frigy.frigymobile.Persistence.FridgyInternalDatabase
import org.frigy.frigymobile.Persistence.ProductRepository
import org.frigy.frigymobile.R
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL


class CheckInActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {

    private lateinit var mScannerView: ZBarScannerView
    private lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 0)
        }


        mScannerView = ZBarScannerView(this)
        setContentView(R.layout.activity_check_in)
        val barcodeLayout: ViewGroup = findViewById(R.id.barcode_layout)
        resultView = findViewById(R.id.text_result)

        barcodeLayout.addView(mScannerView)
        //mScannerView.setAspectTolerance(0.5f);

    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.setAutoFocus(true)
        //TODO add toggle for flashlight
        //mScannerView.flash = true
        mScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }


    //Implementing the ResultHandler interface and overriding handleResult function


    override fun handleResult(result: Result?) {
        Toast.makeText(this, "Scan successful: " + result?.contents + " (" + result?.barcodeFormat?.name + ")", Toast.LENGTH_SHORT).show()
        //resultView.append(result?.contents + " (" + result?.barcodeFormat?.name + ")\n")
        val products: LiveData<List<Product>> = ProductRepository(this).searchByBarcode(result?.contents.toString())

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        products.observe(this, (Observer<List<Product>> {
            if (products.value!!.isEmpty()) {
                Toast.makeText(this, "No products found for barcode: " + result?.contents, Toast.LENGTH_SHORT).show()

            } else {
                for (product in products.value!!) {
                    resultView.append(product.toString())
                }
            }
        }))
        //TODO dont resume camera immediately
        mScannerView.resumeCameraPreview(this)

        //{products -> {for (product in products){ resultView.append(product.toString())}}
        //Camera will stop after scanning result, so we need to resume the
        //preview in order scan more codes
    }

}