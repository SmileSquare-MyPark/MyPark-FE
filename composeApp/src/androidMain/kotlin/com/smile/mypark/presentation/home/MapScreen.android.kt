package com.smile.mypark.presentation.home

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.util.FusedLocationSource

@Composable
actual fun MyNaverMap(
    modifier: Modifier,
    onReady: (() -> Unit)?
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val activity = context as Activity

    val mapView = remember { MapView(context).apply { onCreate(null) } }

    DisposableEffect(lifecycleOwner, mapView) {
        val observer = LifecycleEventObserver { _, e ->
            when (e) {
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    val locationSource = remember { FusedLocationSource(activity, /*requestCode*/ 1000) }
    var needFollow by remember { mutableStateOf(false) }
    val permLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { grants ->
        val granted = (grants["android.permission.ACCESS_FINE_LOCATION"] == true) ||
                (grants["android.permission.ACCESS_COARSE_LOCATION"] == true)
        needFollow = granted
    }

    fun ensurePermissionAndFollow() {
        val fine = ActivityCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val coarse = ActivityCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (fine || coarse) {
            needFollow = true
        } else {
            permLauncher.launch(arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        }
    }

    AndroidView(
        modifier = modifier,
        factory = {
            mapView.apply {
                getMapAsync { naverMap ->
                    naverMap.locationSource = locationSource

                    naverMap.uiSettings.isLocationButtonEnabled = true

                    val seoul = LatLng(37.5665, 126.9780)
                    naverMap.moveCamera(CameraUpdate.scrollAndZoomTo(seoul, 14.0))

                    naverMap.locationOverlay.isVisible = true

                    ensurePermissionAndFollow()

                    onReady?.invoke()
                }
            }
        },
        update = { view ->
            if (needFollow) {
                view.getMapAsync { naverMap ->
                    naverMap.locationTrackingMode = LocationTrackingMode.Follow
                }
                needFollow = false
            }
        }
    )
}

actual fun openNativeMap() {

}
actual fun isMapSupported(): Boolean = true