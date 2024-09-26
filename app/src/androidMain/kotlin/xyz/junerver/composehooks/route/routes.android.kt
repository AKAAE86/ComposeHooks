package xyz.junerver.composehooks.route

import androidx.compose.runtime.Composable
import xyz.junerver.composehooks.example.UseBiometricExample
import xyz.junerver.composehooks.example.UseDeviceInfoExample
import xyz.junerver.composehooks.example.UseIdleExample
import xyz.junerver.composehooks.example.UseNetworkExample
import xyz.junerver.composehooks.example.UseSensorExample
import xyz.junerver.composehooks.example.UseVibrateExample


actual fun getAndroidRoutes(): Map<String, @Composable () -> Unit> {
    return mapOf(
        "useBiometric" to { UseBiometricExample() },
        "useDeviceInfo" to { UseDeviceInfoExample() },
        "useIdle" to { UseIdleExample() },
        "useNetwork" to { UseNetworkExample() },
        "useSensor" to { UseSensorExample() },
        "useVibrate" to { UseVibrateExample() }
    )
}
