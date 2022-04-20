package uz.digitalone.driverapp.core.ext

import android.provider.SyncStateContract
import formatted
import java.text.SimpleDateFormat
import java.util.*

fun String?.isMail(): Boolean {
    if (this.isNullOrBlank()) return false
    val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.{1,})";
    return emailRegex.toRegex().matches(this)
}

//fun String?.isValidCode(): Boolean {
//    if (this.isNullOrBlank()) return false
//    return this.length == SyncStateContract.Constants.VERIFICATION_CODE_LENGTH
//}

//fun String?.isValidPassword(): Boolean {
//    if (this.isNullOrBlank()) return false
//    return this.length >= SyncStateContract.Constants.MIN_PASSWORD_LENGTH
//}

fun String?.convertTimeZoneToDate(
    fromDateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
    toDateFormat: String = "yyyy/MM/dd-hh:mm"
): String? {
    if (this.isNullOrBlank()) return null
    val format = SimpleDateFormat(fromDateFormat, Locale.US)
    var date: Date?
    return try {
        date = format.parse(this)
        date?.formatted(toDateFormat)
    } catch (e: Exception) {
        try {
            date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US).parse(this)
            date?.formatted(toDateFormat)
        } catch (e: Exception) {
            this
        }
    }

}

fun String?.longToTime(toDateFormat: String = "hh:mm"): String? {
    if (this.isNullOrBlank()) return null
    val date = Date(this.toLong())
    return date.formatted(toDateFormat)
}

fun Long?.toTime(toDateFormat: String = "hh:mm"): String? {
    if (this == null) return null
    val date = Date(this)
    return date.formatted(toDateFormat)
}