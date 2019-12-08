package hr.foi.air.webservice.handlers

import java.util.*

interface MyWebserviceHandler {
    fun onDataArrived(result: Object, ok: Boolean, timeStamp: Long)
}