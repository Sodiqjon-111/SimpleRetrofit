package uz.digitalone.driverapp.core.ext

import android.webkit.MimeTypeMap
import java.io.File
import java.util.*

fun File.getMimeType(fallback: String = "*/*"): String {
    return MimeTypeMap.getFileExtensionFromUrl(toString())
        ?.run {
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(lowercase(Locale.getDefault()))
        }
        ?: fallback // You might set it to */*
}