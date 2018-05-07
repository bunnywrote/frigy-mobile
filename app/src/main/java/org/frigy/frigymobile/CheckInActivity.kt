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
import android.os.AsyncTask
import android.util.JsonReader
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import com.android.volley.AuthFailureError
import com.android.volley.VolleyError
import com.google.gson.Gson
import org.frigy.frigymobile.Models.Product
import org.frigy.frigymobile.Models.dto.FoodrepoProduct
import org.json.JSONArray
import org.json.JSONObject


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
        mScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }


//Implementing the ResultHandler interface and overriding handleResult function


    override fun handleResult(result: Result?) {
        //Toast.makeText(this, "Scan successful", Toast.LENGTH_SHORT).show()
        resultView.append(result?.contents + " (" + result?.barcodeFormat?.name + ")\n")
        queryProductDatabase(result!!.contents)

        //Camera will stop after scanning result, so we need to resume the
        //preview in order scan more codes
        mScannerView.resumeCameraPreview(this)
    }

    private fun queryProductDatabase(query: String) {

        val foodRepoProductSearchEndpoint = URL("https://www.foodrepo.org/api/v3/products/_search")

        val queue = Volley.newRequestQueue(this)

        val req = object : JsonObjectRequest(foodRepoProductSearchEndpoint.toString(), createJsonRequestObject(query),
                Response.Listener { response ->
                    val prodcuts: Array<FoodrepoProduct> = mapJsonResponseToProduct(response)
                    for (product in prodcuts) {
                        resultView.append(product.toString())
                    }
                },
                Response.ErrorListener { error ->
                    resultView.text = "Error while fetching data from API: " + error
                }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Token token=38ceddd2f7f4b099087b7a2f19f1f7db"
                return headers
            }
        }
        queue.add(req)

    }
}

private fun mapJsonResponseToProduct(json: JSONObject): Array<FoodrepoProduct> {

    val hits: JSONObject = json.getJSONObject("hits")
    val hitsArray: JSONArray = hits.getJSONArray("hits")

    var products: Array<FoodrepoProduct> = Array(hitsArray.length(), { i -> FoodrepoProduct() })

    val mapper: Gson = Gson()

    for (i in 0..(hitsArray.length() - 1)) {
        val source: JSONObject = hitsArray.getJSONObject(i).getJSONObject("_source")
        products[i] = mapper.fromJson<FoodrepoProduct>(source.toString(), FoodrepoProduct::class.java)
    }
    return products;
}

/*    private fun callProductAPI(query: String): String{

lateinit var result: String

//val myConnection = githubEndpoint.openConnection() as HttpsURLConnection
//myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1")
myConnection.sRequestProperty("Accept", "application/json")
myConnection.setRequestProperty("Content-Type", "application/vnd.api+json")
myConnection.setRequestProperty("Authorization", "Token token=38ceddd2f7f4b099087b7a2f19f1f7db")
myConnection.setRequestMethod("POST");
myConnection.doOutput = true
}*/


private fun createPostQuery(query: String): String {
    var query: String = "{\n" +
            "  \"_source\": {\n" +
            "    \"includes\": []\n" +
            "  },\n" +
            "  \"size\": 20,\n" +
            "  \"query\": {\n" +
            "    \"query_string\": {\n" +
            "      \"fields\" : [\n" +
            "        \"barcode\"\n" +
            "      ],\n" +
            "      \"query\" : \"" + query + "\"\n" +
            "    }\n" +
            "  }\n" +
            "}"
    return query
}

private fun createJsonRequestObject(query: String): JSONObject {
    var request = JSONObject(createPostQuery(query))
    return request;
}


//    companion object {
//
//        fun newIntent(context: Context, user: User): Intent {
//            val intent = Intent(context, UserDetailActivity::class.java)
//            intent.putExtra(INTENT_USER_ID, user.id)
//            return intent
//        }
//    }