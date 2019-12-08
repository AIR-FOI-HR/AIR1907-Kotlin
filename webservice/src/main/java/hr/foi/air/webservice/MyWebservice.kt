package hr.foi.air.webservice

import hr.foi.air.webservice.responses.MyWebserviceResponse
import retrofit.Call
import retrofit.http.Field
import retrofit.http.FormUrlEncoded
import retrofit.http.POST
import java.lang.reflect.Method

interface MyWebservice {
    @FormUrlEncoded
    @POST("stores.php")
    fun getStores(@Field("method") method: String):Call<MyWebserviceResponse>

    @FormUrlEncoded
    @POST("discounts.php")
    fun getDiscounts(@Field("method") method: String):Call<MyWebserviceResponse>

}