package hr.foi.air.core

interface DataLoader {
    fun loadData(listener: DataLoadedListener)
    fun isDataLoaded(): Boolean
}