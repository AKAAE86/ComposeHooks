package xyz.junerver.composehooks

import com.ctrip.flight.mmkv.defaultMMKV
import xyz.junerver.compose.hooks.notifyDefaultPersistentObserver

/*
  Description:
  Author: Junerver
  Date: 2024/9/11-11:02
  Email: junerver@gmail.com
  Version: v1.0
*/

val mmkv = defaultMMKV()

fun mmkvSave(key: String, value: Any?) {
    when (value) {
        is Int -> mmkv.set(key, value)
        is Long -> mmkv.set(key, value)
        is Double -> mmkv.set(key, value)
        is Float -> mmkv.set(key, value)
        is Boolean -> mmkv.set(key, value)
        is String -> mmkv.set(key, value)
        is ByteArray -> mmkv.set(key, value)
//        is Parcelable -> mmkv.set(key, value)
    }
    notifyDefaultPersistentObserver(key)
}

fun mmkvGet(key: String, value: Any): Any {
    return when (value) {
        is Int -> mmkv.takeInt(key, value)
        is Long -> mmkv.takeLong(key, value)
        is Double -> mmkv.takeDouble(key, value)
        is Float -> mmkv.takeFloat(key, value)
        is Boolean -> mmkv.takeBoolean(key, value)
        is String -> mmkv.takeString(key, value)
        is ByteArray -> mmkv.takeByteArray(key, value)
//        is Parcelable -> mmkv.takeParcelable(key, value.javaClass)
        else -> error("wrong type of default value！")
    } as Any
}

fun mmkvClear(key: String) {
    mmkv.removeValueForKey(key)
}
