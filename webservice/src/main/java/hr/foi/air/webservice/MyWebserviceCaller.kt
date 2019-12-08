package hr.foi.air.webservice

import com.squareup.okhttp.OkHttpClient
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import java.lang.reflect.Type

class MyWebserviceCaller {
    var retrofit: Retrofit? = null
    val baseUrl: String = "http://cortex.foi.hr/mtl/courses/air/"

    constructor(){
        val client: OkHttpClient = OkHttpClient()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getAll(method: String, entityType: Type) {
        
    }

}