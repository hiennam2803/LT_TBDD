package com.example.signwithgg.service

import android.content.Context
import android.os.Build
import android.widget.Toast

class PermissionService(private val context: Context) {

    fun onCameraResult(granted: Boolean) {
        Toast.makeText(
            context,
            if (granted) "Camera đã được cho phép" else "Camera bị từ chối",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onLocationResult(granted: Boolean) {
        Toast.makeText(
            context,
            if (granted) "Vị trí đã được cho phép" else "Vị trí bị từ chối",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onNotificationResult(granted: Boolean) {
        Toast.makeText(
            context,
            if (granted) "Thông báo đã được cho phép" else "Thông báo bị từ chối",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun needNotificationPermission(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    }
}
