package org.frigy.frigymobile.Persistence

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.frigy.frigymobile.Models.Product
import org.frigy.frigymobile.Models.dto.FoodrepoProduct
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL


class ProductRepository(context: Context) {

    private val mContext: Context = context

    private val mDB: FridgyInternalDatabase = FridgyInternalDatabase.getInstance(mContext)

    private val productDao: ProductDao = mDB.productDao()


    fun searchByBarcode(query: String): LiveData<List<Product>> {
        val resultData: MutableLiveData<List<Product>> = MutableLiveData()
        queryProductAPI(query, resultData)
        return resultData
    }

    fun insert(product: Product) {
        insertAsyncTask(productDao).execute(product)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: ProductDao) : AsyncTask<Product, Void, Void>() {

        override fun doInBackground(vararg params: Product): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private fun queryProductAPI(query: String, result: MutableLiveData<List<Product>>) {

        val foodRepoProductSearchEndpoint = URL("https://www.foodrepo.org/api/v3/products/_search")

        val queue = Volley.newRequestQueue(mContext)

        val req = object : JsonObjectRequest(foodRepoProductSearchEndpoint.toString(), createJsonRequestObject(query),
                Response.Listener { response ->
                    val hits: JSONObject = response.getJSONObject("hits")
                    val int = hits.getInt("total")
                    val resultProducts: MutableList<Product> = mutableListOf()

                    if (int > 0) {
                        val prodcuts: Array<FoodrepoProduct> = mapJsonResponseToProduct(hits)
                        for (product in prodcuts) {
                            resultProducts.add(Product(product))
                        }
                    }
                    result.postValue(resultProducts)
                },
                Response.ErrorListener { error ->
                    //TODO exception handling
                    //throw Exception("could not load data from foodrepo.org: " + error)
                    Log.d(this.javaClass.name, "could not load data from foodrepo.org: " + error)
                    //resultView.addtext = "Error while fetching data from API: " + error
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


    private fun mapJsonResponseToProduct(json: JSONObject): Array<FoodrepoProduct> {

        val hitsArray: JSONArray = json.getJSONArray("hits")

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


    //TODO build query with json-object
    private fun productBarcodeQuery(query: String): String {
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
        var request = JSONObject(productBarcodeQuery(query))
        return request;


    }
}