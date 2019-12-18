package hr.foi.air.discountlocatorkotlin.helper

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.preference.PreferenceManager
import java.util.*

class Util {
    @Suppress("DEPRECATION")
    fun setLanguage(context: Context){
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val lang: String? = preferences.getString("language", "system")
        val config: Configuration = Configuration(context.resources.configuration)
        if(lang == "system"){
            config.setLocale(Locale.getDefault())
        }else{
            config.setLocale(Locale(lang))
        }

        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}