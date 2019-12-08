package hr.foi.air.webservice

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.webservice.handlers.MyWebserviceHandler
import hr.foi.air.webservice.responses.MyWebserviceResponse
import retrofit.*
import java.lang.Exception
import java.lang.reflect.Type

class MyWebserviceCaller {
    var retrofit: Retrofit? = null
    private val baseUrl: String = "http://cortex.foi.hr/mtl/courses/air/"

    private var myWebserviceHandler: MyWebserviceHandler? = null

    constructor(myWebserviceHandler: MyWebserviceHandler){
        this.myWebserviceHandler = myWebserviceHandler
        val client: OkHttpClient = OkHttpClient()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getAll(method: String, entityType: Type) {

        val serviceCaller: MyWebservice? = retrofit?.create(MyWebservice::class.java)
        var call: Call<MyWebserviceResponse>? = null

        if(entityType==Store::class.java){
            if (serviceCaller != null) {
                call = serviceCaller.getStores(method)
            }
        }else{
            if (serviceCaller != null) {
                call = serviceCaller.getDiscounts(method)
            }
        }

        if(call != null){
            call.enqueue(object: Callback <MyWebserviceResponse>{
                override fun onFailure(t: Throwable?) {
                    t?.printStackTrace()
                }

                override fun onResponse(
                    response: Response<MyWebserviceResponse>?,
                    retrofit: Retrofit?
                ) {
                    try{
                        if (response != null) {
                            if(response.isSuccess()){
                                if(entityType == Store::class.java){
                                    println("Got stores...")
                                    processStoreResponse(response)
                                }else if(entityType==Discount::class.java){
                                    println("Got discounts")
                                }else{
                                    println("Unrecognized class")
                                }
                            }
                        }
                    }catch (ex: Exception){
                        ex.printStackTrace()
                    }
                }

            })
        }
    }

    private fun processStoreResponse(response: Response<MyWebserviceResponse>){
        val gson: Gson = Gson()
        val storeItem: Array<Store>? = gson?.fromJson(
                response.body().items, Array<Store>::class.java
        )

        if (storeItem != null) {
            myWebserviceHandler?.onDataArrived<Store>(
                storeItem.toList(),
                true,
                response.body().timeStamp
            )
        }

    }

    private fun processDiscountResponse(response: Response<MyWebserviceResponse>){
        val gson: Gson = GsonBuilder()
            .setDateFormat("yyy-MM-dd")
            .create()

        val discountItem: Array<Discount>? = gson?.fromJson(
            response.body().items, Array<Discount>::class.java
        )

        if (discountItem != null) {
            myWebserviceHandler?.onDataArrived<Discount>(
                discountItem.toList(),
                true,
                response.body().timeStamp
            )
        }
    }

}