package com.example.peduliternak.view.fragment

import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.peduliternak.R
import com.example.peduliternak.databinding.FragmentMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapsBinding
    private val boundsBuilder = LatLngBounds.Builder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true


        // ... (Sesuaikan bagian onMapReady() sesuai dengan kode yang telah Anda modifikasi)

        getMyLocation()
        //setMapStyle()
        addManyMarker()
    }

    // ... (Sisipkan fungsi-fungsi tambahan seperti onCreateOptionsMenu, onOptionsItemSelected, dan lainnya)

    private fun vectorToBitmap(@DrawableRes id: Int, @ColorInt color: Int): BitmapDescriptor {
        val vectorDrawable = ResourcesCompat.getDrawable(resources, id, null)
        if (vectorDrawable == null) {
            Log.e("BitmapHelper", "Resource not found")
            return BitmapDescriptorFactory.defaultMarker()
        }
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        DrawableCompat.setTint(vectorDrawable, color)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

//    private fun setMapStyle() {
//        try {
//            val success =
//                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireActivity(), R.raw.map_style))
//            if (!success) {
//                Log.e(Companion.TAG, "Style parsing failed.")
//            }
//        } catch (exception: Resources.NotFoundException) {
//            Log.e(Companion.TAG, "Can't find style. Error: ", exception)
//        }
//    }

    data class PuskeswanPlace(
        val name: String,
        val latitude: Double,
        val longitude: Double
    )



    private fun addManyMarker() {
        // ... (Sisipkan kembali fungsi addManyMarker sesuai dengan kode yang telah Anda modifikasi)
        val tourismPlace = listOf(
            PuskeswanPlace("Puskeswan Depok", -7.7561207721766205, 110.41478891276145),
            PuskeswanPlace("Puskeswan Ngaglik", -7.688494126466912, 110.38854449723894),
            PuskeswanPlace("Puskeswan Kasihan", -7.818326395411564, 110.38491562321275),
            PuskeswanPlace("Puskeswan Berbah", -7.807326198499546, 110.44365762618537),
            PuskeswanPlace("Puskeswan Gamping", -7.8066459173262315, 110.31285166254894),
            PuskeswanPlace("Puskeswan Godean", -7.769909091979971, 110.29087900723992),
//            TourismPlace("Alun-Alun Kota Bandung", -6.9218518, 107.6025294),
//            TourismPlace("Orchid Forest Cikole", -6.780725, 107.637409),
        )
        tourismPlace.forEach { tourism ->
            val latLng = LatLng(tourism.latitude, tourism.longitude)
            mMap.addMarker(
                MarkerOptions().position(latLng).title(tourism.name)
            )
            boundsBuilder.include(latLng)
        }
        val bounds: LatLngBounds = boundsBuilder.build()
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                300
            )
        )
    }

    companion object {
        private const val TAG = "MapsActivity"
    }
}