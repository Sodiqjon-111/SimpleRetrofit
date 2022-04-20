package uz.digitalone.driverapp.core.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.DatePickerDialog
import android.app.Service
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import android.os.FileUtils
import android.provider.MediaStore
import android.telephony.TelephonyManager
import android.transition.Transition
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import date
import logd
import month
import toDate
import uz.sodiqjon.simpleretrofit.R
import year
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.util.*
import kotlin.math.ceil
import kotlin.math.roundToInt


fun Context.getLocationManager() = getSystemService(Context.LOCATION_SERVICE) as LocationManager

fun Context.dpToPx(dp: Float): Float {
    val resources = resources
    val metrics = resources.displayMetrics
    val px = dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    return px
}

fun Context.convertDpToPx(dp: Float): Int {
    val resources = resources
    val metrics = resources.displayMetrics
    val px = dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    return px.toInt()
}

fun Context.pxToDp(px: Float): Float {
    val resources = resources
    val metrics = resources.displayMetrics
    val dp = px * DisplayMetrics.DENSITY_DEFAULT / (metrics.densityDpi.toFloat())
    return dp
}

fun Context.getColorCompat(@ColorRes colorId: Int): Int = ContextCompat.getColor(this, colorId)
//fun Fragment.uz.digitalone.driverapp.core.ext.getColorCompat(@ColorRes colorId: Int) = activity.uz.digitalone.driverapp.core.ext.getColorCompat(colorId)

fun Activity.hideKeyBoard() {
    val view = this.currentFocus
    view ?: return
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.getGoogleKey(): String {
    val ai: ApplicationInfo = packageManager
        .getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    return ai.metaData["com.google.android.geo.API_KEY"].toString()
}

fun Activity.showKeyBoard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Context.getColorFromAttrs(@AttrRes resId: Int): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(resId, typedValue, true)
    return typedValue.data
}

fun Context.toastSH(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.toastLN(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.getNavigationBarPosition(): Point {
    val appUsableSize = getAppUsableScreenSize()
    val realScreenSize = getRealScreenSize()

    // navigation bar on the right
    if (appUsableSize.x < realScreenSize.x) {
        return Point(realScreenSize.x - appUsableSize.x, appUsableSize.y);
    }

    // navigation bar at the bottom
    if (appUsableSize.y < realScreenSize.y) {
        return Point(appUsableSize.x, realScreenSize.y - appUsableSize.y);
    }

    // navigation bar is not present
    return Point()
}

fun Context.getNavigationBarHeight(): Int {
    val appUsableSize = getAppUsableScreenSize()
    val realScreenSize = getRealScreenSize()

    // navigation bar at the bottom
    if (appUsableSize.y < realScreenSize.y) {
        return (realScreenSize.y - appUsableSize.y)
    }

    // navigation bar on the right
    if (appUsableSize.x < realScreenSize.x) {
        return (realScreenSize.x - appUsableSize.x)
    }

    return 0
}


fun Context.getAppUsableScreenSize(): Point {
    val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}

fun Context.toastNotImplemented() {
    Toast.makeText(this, """ not implemented yet ¯\_(ツ)_/¯ """, Toast.LENGTH_SHORT).show()
}


fun Context.getRealScreenSize(): Point {
    val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()

    display.getRealSize(size)

    return size
}

fun Context.inflate(
    @LayoutRes resId: Int,
    root: ViewGroup? = null,
    attachToRoot: Boolean = false
): View = LayoutInflater.from(this).inflate(resId, root, attachToRoot)


fun Context.getCompatDrawable(@DrawableRes resId: Int) = ContextCompat.getDrawable(this, resId)

fun Context.isOnline(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting
}

fun Context.dpToPx(dps: Int): Int {
    return (this.resources.displayMetrics.density * dps).roundToInt()
}

fun Context.getDrawableFromVector(res: Int): VectorDrawableCompat? {
    return VectorDrawableCompat.create(this.resources, res, this.theme)
}

fun Context.getBitmapFromVectorDrawableRes(res: Int): Bitmap? {
    val vectorDrawable = getDrawableFromVector(res)
    return vectorDrawable?.let {
        val bitmap =
            Bitmap.createBitmap(it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        it.setBounds(0, 0, canvas.width, canvas.height)
        it.draw(canvas)
        bitmap
    }
}


@SuppressLint("NewApi")
fun initSharedViewTransitionListener(window: Window, onTransitionFinish: () -> Unit) {
    val sharedElementEnterTransition = window.sharedElementEnterTransition
    sharedElementEnterTransition.addListener(object : Transition.TransitionListener {
        override fun onTransitionEnd(p0: Transition?) {
            onTransitionFinish()
            sharedElementEnterTransition.removeListener(this)
        }

        override fun onTransitionResume(p0: Transition?) {

        }

        override fun onTransitionPause(p0: Transition?) {
        }

        override fun onTransitionCancel(p0: Transition?) {
        }

        override fun onTransitionStart(p0: Transition?) {

        }

    })
}

fun Activity.finishCompatAfterTransition() = ActivityCompat.finishAfterTransition(this)


fun Activity.showNotImplementedDialog() {
//    alert {
//
//        setMessage("""Not implemented yet ¯\_(ツ)_/¯""")
//        setCancelable(true)
//        okButton {  }
//    }.show()
}

fun Fragment.showNotImplementedDialog() {
    activity?.showNotImplementedDialog()
}

fun Context.makeCall(number: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null))
    this.startActivity(intent)
//    if (intent.resolveActivity(this.packageManager) != null) this.startActivity(intent)
//    else Toast.makeText(this, "Error call", Toast.LENGTH_LONG).show()
}

fun Context.makeMail(mail: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:") // only email apps should handle this
        putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
    }
    try {
        if (intent.resolveActivity(this.packageManager) != null) {
            this.startActivity(intent)
        } else toastLN("Unknown Error")
    } catch (e: Exception) {
        toastLN("NO mail Api")
    }

}

fun Context.showDatePicker(callback: (Date) -> Unit, @StyleRes theme: Int) {
    val currentDate = Calendar.getInstance()
    val resultDate = Calendar.getInstance()
    DatePickerDialog(this, theme, { _, year, month, day ->
        resultDate.set(year, month, day)
        callback(resultDate.toDate())
    }, currentDate.year(), currentDate.month(), currentDate.date()).show()
}

fun Context.browse(link: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)

    } catch (e: Exception) {
        toastSH(e.localizedMessage ?: "Error")
    }
}

fun Context.withStyleAttributes(
    attrs: AttributeSet?,
    filter: IntArray,
    block: TypedArray.() -> Unit
) {
    with(obtainStyledAttributes(attrs, filter)) {
        block()
        recycle()
    }
}

fun Context.makeCallEvent(number: String?) {
    TODO()
//    number ?: return
//    val numbers = number.split("/").filter(String::isNotBlank)
//    if (numbers.size == 1)
//        uz.digitalone.driverapp.core.ext.makeCall(number)
//    else {
//        val builderSingle = AlertDialog.Builder(this)
//        builderSingle.setIcon(R.drawable.ic_call)
//        builderSingle.setTitle(R.string.mess_choose_number)
//        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_item)
//        numbers.forEach { arrayAdapter.add(it) }
//        builderSingle.setAdapter(arrayAdapter)
//        { _, which -> uz.digitalone.driverapp.core.ext.makeCall(numbers[which]) }
//        builderSingle.show()
//    }
}

fun Context.isApplicationOnPause(): Boolean {
    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val tasks = activityManager.getRunningTasks(1)
    if (tasks.isNotEmpty()) {
        val topActivity = tasks[0].topActivity
        return topActivity?.packageName != packageName
    }
    return false
}

fun Activity.getStatusBarHeight(): Int {
    val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resId > 0) resources.getDimensionPixelSize(resId)
    else (ceil(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) 25.0 else 24.0) * resources
        .displayMetrics
        .density
            ).toInt()
}

fun Context.pixelsToDp(pixels: Int): Float {
    val metrics = resources.displayMetrics
    return pixels.toFloat() / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

fun Context.isServiceRunning(serviceClass: Class<*>): Boolean {
    val manager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    return manager.getRunningServices(Integer.MAX_VALUE)
        .any { it.service.className == serviceClass.name }
}

//fun Context.startBackgroundLocation() {
//    val interval = 1000
//    val fastestInterval = 2000
//    val priority: Int = LocationRequest.PRIORITY_HIGH_ACCURACY
//    val maxWaitTime = 10000
//    val smallestDisplacement = 20
//
//    val locationTrackerParams = LocationTrackerParams(
//        interval.toLong(), fastestInterval.toLong(), priority,
//        maxWaitTime.toLong(), smallestDisplacement.toFloat()
//    )
//    LocationTracker.requestLocationUpdates(this,LocationTrackerService::class.java,locationTrackerParams)
//}
//
//fun Context.stopBackgroundLocation() {
//    LocationTracker.removeLocationUpdates(this)
//}
//fun Context.startBackgroundLocation() {
//    stopBackgroundLocation()// if there is any background service running first stops the service and start new
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        startForegroundService(Intent(this, LocationService::class.java))
//    } else {
//        startService(Intent(this, LocationService::class.java))
//    }
//}

//fun Context.stopBackgroundLocation() {
//    if (isServiceRunning(LocationService::class.java)) {
//        stopService(Intent(this, LocationService::class.java))
//    }
//}

//fun Context.getLocationInfo(
//    lng: Double,
//    lat: Double,
//    gpsStatus: Int,
//    accuracy: Int = 0,
//    address: String? = null
//): LocationInfo {

//
//    val deviceModel = (Build.MANUFACTURER
//            + " " + Build.MODEL + " " + Build.VERSION.RELEASE
//            + " " + Build.VERSION_CODES::class.java.fields[Build.VERSION.SDK_INT].name)
//    return LocationInfo(
//        model = deviceModel,
//        batteryLevel = getBatteryLevel().roundToInt(),
//        cellarLevel = accuracy,
//        longitude = lng,
//        latitude = lat,
//        gpsStatus = gpsStatus,
//        timeStamp = System.currentTimeMillis(),
//        networkType = getProviderName(),
//        address = address
//    )
//}

fun Context.getBatteryLevel(): Float {
    val batteryIntent =
        applicationContext.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    return if (batteryIntent != null) {
        val level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        level.toFloat() / scale.toFloat() * 100.0f
    } else (-1).toFloat()
}

fun Context.getProviderName(): String {
    val manager = applicationContext.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager
    return manager.networkOperatorName
}

//fun Context.downloadFile(url: String?, listener: FetchListener?) {
//    if (url == null) {
//        showToast("Sorry file can't be downloaded")
//        return
//    }
//
//}

//fun Context.copyFileToDownloads(context: Context, downloadedFile: File): Uri? {
//    val resolver = context.contentResolver
//    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//        val contentValues = ContentValues().apply {
//            put(MediaStore.MediaColumns.DISPLAY_NAME, downloadedFile.name)
//            put(MediaStore.MediaColumns.MIME_TYPE, FileUtils.getMimeType(downloadedFile))
//            put(MediaStore.MediaColumns.SIZE, downloadedFile.length())
//        }
//        resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
//    } else {
//        val authority = "${context.packageName}.provider"
//        val downloadedDir =
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//        val destinyFile = File(downloadedDir, downloadedFile.name)
//        FileProvider.getUriForFile(context, authority, destinyFile)
//    }?.also { downloadedUri ->
//        resolver.openOutputStream(downloadedUri).use { outputStream ->
//            val brr = ByteArray(1024)
//            var len: Int
//            val bufferedInputStream =
//                BufferedInputStream(FileInputStream(downloadedFile.absoluteFile))
//            while ((bufferedInputStream.read(brr, 0, brr.size).also { len = it }) != -1) {
//                outputStream?.write(brr, 0, len)
//            }
//            outputStream?.flush()
//            bufferedInputStream.close()
//        }
//    }
//}

fun Context.isFileExist(fileName: String): Boolean {
    val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    dir?.listFiles()?.forEach {
        if (it.name == fileName) {
            return true
        }
    }
    logd(dir?.listFiles()?.size?.toString() ?: "nofiles")
    return false

}



