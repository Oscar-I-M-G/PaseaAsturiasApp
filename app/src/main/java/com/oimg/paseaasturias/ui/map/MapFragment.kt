package com.oimg.paseaasturias.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.oimg.paseaasturias.databinding.FragmentMapBinding
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.views.MapView
import org.osmdroid.config.Configuration
import org.osmdroid.library.BuildConfig
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.OverlayItem

@AndroidEntryPoint
class MapFragment : Fragment() {
    //  -   -   -   -   View Model  -   -   -   -   -   -
    private val mapViewModel by viewModels<MapViewModel>()

    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    //  -   -   -   -   -   Binding -   -   -   -   -   -
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    //  -   -   -   -   -   Mapview -   -   -   -   -   -
    private lateinit var mapView: MapView
    //  -   -   -   -   -   -   -   -   -   -   -   -   -
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Important! Set your user agent to prevent getting banned from the osm servers
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        mapView = binding.mapView
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(9.5)
        mapView.controller.setCenter(GeoPoint(48.8583, 2.2944)) // Set center to a location

        return binding.root
    }

    fun updateMapCoordinates(coordinates: GeoPoint) {
        val latitude = arguments?.getDouble("latitude")
        val longitude = arguments?.getDouble("longitude")
        mapView.controller.setCenter(coordinates)

        // Create an OverlayItem
        val overlayItem = OverlayItem("Location", "Description", coordinates)

        // Create an ItemizedIconOverlay and add the OverlayItem
        val items = ArrayList<OverlayItem>()
        items.add(overlayItem)

        val locationOverlay = ItemizedIconOverlay(
            requireContext(),
            items,
            object : ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
                override fun onItemSingleTapUp(index: Int, item: OverlayItem): Boolean {
                    // Handle single tap on item here
                    return true
                }

                override fun onItemLongPress(index: Int, item: OverlayItem): Boolean {
                    // Handle long press on item here
                    return true
                }
            }
        )

        // Add the ItemizedIconOverlay to the MapView
        mapView.overlays.add(locationOverlay)
    }
    //initializeUI()
    override fun onResume() {
        super.onResume()
        mapView.onResume() //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()  //needed for compass, my location overlays, v6.0.0 and up
    }
}