package hr.foi.air.webservice.handlers

interface MyWebserviceHandler {
    fun <T> onDataArrived(result: List<T>, ok: Boolean, timeStamp: Long?)
}