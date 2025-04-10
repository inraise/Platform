package com.example.platform.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.platform.presentation.navigation.SetupNavigationGraph
import com.example.platform.presentation.screens.main.BottomBarScreen
import com.example.platform.presentation.screens.search.SearchViewModel
import com.example.platform.presentation.screens.weather.LocationViewModel
import com.example.platform.presentation.screens.weather.WeatherViewModel
import com.example.platform.presentation.theme.PlatformTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val searchViewModel: SearchViewModel by viewModels()
    private val locationViewModel: LocationViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        var keepSplashScreen = true
        super.onCreate(savedInstanceState)
        splashscreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            delay(500)
            keepSplashScreen = false
        }
        enableEdgeToEdge()
        setContent {
            PlatformTheme {
                val navHost = rememberNavController()
                var buttonsVisible by remember { mutableStateOf(true) }
                var latitude by remember { mutableDoubleStateOf(0.0) }
                var longitude by remember { mutableDoubleStateOf(0.0) }
                var showPermissionResultText by remember { mutableStateOf(false) }

                RequestLocationPermission(
                    onPermissionGranted = {
                        showPermissionResultText = true
                        getLastUserLocation(
                            onGetLastLocationSuccess = {
                                latitude = it.first
                                longitude = it.second
                            },
                            onGetLastLocationFailed = {
                                showPermissionResultText = true
                            },
                            onGetLastLocationIsNull = {
                                getCurrentLocation(
                                    onGetCurrentLocationSuccess = {
                                        latitude = it.first
                                        longitude = it.second
                                    },
                                    onGetCurrentLocationFailed = {
                                        showPermissionResultText = true
                                    }
                                )
                            }
                        )
                    },
                    onPermissionDenied = {
                        showPermissionResultText = true
                    },
                    onPermissionsRevoked = {
                        showPermissionResultText = true
                    }
                )

                if (latitude + longitude != 0.0) {
                    weatherViewModel.getWeather(latitude, longitude)
                    locationViewModel.getLocation(latitude, longitude)
                }

                Scaffold(bottomBar = {
                    if (buttonsVisible) {
                        BottomBarScreen(
                            navController = navHost,
                            state = buttonsVisible,
                            modifier = Modifier
                        )
                    }
                }) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        SetupNavigationGraph(
                            navHostController = navHost,
                            locationViewModel = locationViewModel,
                            weatherViewModel = weatherViewModel,
                            lat = latitude,
                            lon = longitude,
                            searchViewModel = searchViewModel
                        ) { isVisible ->
                            buttonsVisible = isVisible
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun RequestLocationPermission(
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit,
        onPermissionsRevoked: () -> Unit
    ) {
        val permissionState = rememberMultiplePermissionsState(
            listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )

        LaunchedEffect(key1 = permissionState) {
            val allPermissionsRevoked =
                permissionState.permissions.size == permissionState.revokedPermissions.size
            val permissionsToRequest = permissionState.permissions.filter {
                !it.status.isGranted
            }
            if (permissionsToRequest.isNotEmpty()) permissionState.launchMultiplePermissionRequest()

            if (allPermissionsRevoked) {
                onPermissionsRevoked()
            } else {
                if (permissionState.allPermissionsGranted) {
                    onPermissionGranted()
                } else {
                    onPermissionDenied()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastUserLocation(
        onGetLastLocationSuccess: (Pair<Double, Double>) -> Unit,
        onGetLastLocationFailed: (Exception) -> Unit,
        onGetLastLocationIsNull: () -> Unit
    ) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (areLocationPermissionsGranted()) {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        onGetLastLocationSuccess(Pair(it.latitude, it.longitude))
                    }?.run {
                        onGetLastLocationIsNull()
                    }
                }
                .addOnFailureListener { exception ->
                    onGetLastLocationFailed(exception)
                }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(
        onGetCurrentLocationSuccess: (Pair<Double, Double>) -> Unit,
        onGetCurrentLocationFailed: (Exception) -> Unit,
        priority: Boolean = true
    ) {
        val accuracy = if (priority) Priority.PRIORITY_HIGH_ACCURACY
        else Priority.PRIORITY_BALANCED_POWER_ACCURACY

        if (areLocationPermissionsGranted()) {
            fusedLocationProviderClient.getCurrentLocation(
                accuracy, CancellationTokenSource().token,
            ).addOnSuccessListener { location ->
                location?.let {
                    onGetCurrentLocationSuccess(Pair(it.latitude, it.longitude))
                }?.run {
                    //Location null
                }
            }.addOnFailureListener { exception ->
                onGetCurrentLocationFailed(exception)
            }
        }
    }

    private fun areLocationPermissionsGranted(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
    }
}