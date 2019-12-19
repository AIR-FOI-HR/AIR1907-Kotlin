package hr.foi.air.core

import androidx.appcompat.app.AppCompatActivity

class CurrentActivity {
    companion object{
        @JvmField
        public var activity:AppCompatActivity? = null

        fun setActivity(activity: AppCompatActivity){
            this.activity = activity
        }

        fun getActivity(): AppCompatActivity? {
            return this.activity
        }
    }
}