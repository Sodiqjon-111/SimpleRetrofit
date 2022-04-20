package uz.sodiqjon.simpleretrofit.data.local.sharedPref

interface SharedPref {
    fun saveToken(token: String?)
    fun saveFirebaseToken(token: String?)
    fun getToken(): String?
    fun getFirebaseToken(): String?
    fun getBooleanValue(key: String): Boolean
    fun getStringValue(key: String): String?
    fun getIntValue(key: String): Int
    fun getLongValue(key: String): Long?
    fun getDoubleValue(key: String): Double
    fun saveValue(key: String, value: String?)
    fun saveValue(key: String, value: Int?)
    fun saveValue(key: String, value: Long?)
    fun saveValue(key: String, value: Double?)
    fun saveValue(key: String, value: Boolean)
    fun clearSharedPref()

}