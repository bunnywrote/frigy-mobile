package org.frigy.frigymobile

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class CheckInActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {

    private lateinit var mScannerView: ZBarScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 0)
        }

        mScannerView = ZBarScannerView(this)
        setContentView(R.layout.activity_check_in)
        val barcodeLayout: ViewGroup = findViewById(R.id.barcode_layout)
        barcodeLayout.addView(mScannerView)
        //mScannerView.setAspectTolerance(0.5f);

    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }


//Implementing the ResultHandler interface and overriding handleResult function


    override fun handleResult(result: Result?) {
        Toast.makeText(this, "Scan successful", Toast.LENGTH_SHORT).show()
        val resultView: TextView = findViewById(R.id.text_result)
        resultView.append(result?.contents + " (" + result?.barcodeFormat?.name + ")\n")

        //Camera will stop after scanning result, so we need to resume the
        //preview in order scan more codes
        mScannerView.resumeCameraPreview(this)
    }
}



//    companion object {
//
//        fun newIntent(context: Context, user: User): Intent {
//            val intent = Intent(context, UserDetailActivity::class.java)
//            intent.putExtra(INTENT_USER_ID, user.id)
//            return intent
//        }
//    }

