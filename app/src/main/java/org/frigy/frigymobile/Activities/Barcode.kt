package org.frigy.frigymobile.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import org.frigy.frigymobile.R
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.widget.ImageView
import com.google.android.gms.vision.barcode.BarcodeDetector
import android.util.SparseArray
import com.google.android.gms.vision.Frame
import android.widget.TextView
import com.google.android.gms.vision.barcode.Barcode.*


class Barcode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

        val btn = findViewById<View>(R.id.button) as Button

        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val myImageView = findViewById<View>(R.id.imgview) as ImageView
                val txtView = findViewById<View>(R.id.txtContent) as TextView

                val myBitmap = BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.puppy)

                myImageView.setImageBitmap(myBitmap)

                val detector = BarcodeDetector.Builder(applicationContext)
                        .setBarcodeFormats(DATA_MATRIX or QR_CODE or EAN_13)
                        .build()
                if (!detector.isOperational) {
                    txtView.setText("Could not set up the detector!")
                    return
                }

                val frame = Frame.Builder().setBitmap(myBitmap).build()
                val barcodes = detector.detect(frame)

                val thisCode = barcodes.valueAt(0)
                txtView.text = thisCode.rawValue

            }
        })


    }
}
