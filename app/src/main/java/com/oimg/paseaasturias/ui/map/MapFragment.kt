package com.oimg.paseaasturias.ui.map

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.oimg.paseaasturias.R
import com.oimg.paseaasturias.databinding.FragmentMapBinding
import com.oimg.paseaasturias.domain.ExtendedOverlayItem
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.library.BuildConfig
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.MapEventsOverlay
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
        mapViewModel.getMapList()
        initUIState()

    }

    private fun initEvents() {
        val mapEventsOverlay = MapEventsOverlay(object : MapEventsReceiver {
            override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {

                binding.svMapSelection.visibility = View.INVISIBLE
                return false
            }

            override fun longPressHelper(p: GeoPoint?): Boolean {

                return false
            }
        })
        mapView.overlays.add(0, mapEventsOverlay)
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mapViewModel.state.collect {
                    when (it) {
                        is MapState.Error -> errorState()
                        MapState.Loading -> loadingState()
                        is MapState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun successState(mapState: MapState.Success) {

        mapView = binding.mapView
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(9.5)
        mapView.controller.setCenter(GeoPoint(43.3614, -5.8593))

    // Creamos una lista de Overlay Items NOSE QUE ESTA PASANDO EXACTAMENTE AQUI
        val overlayItems = mapState.data?.map { mapModel ->
            mapModel.CoordenadasLatLon.chunked(2).filter{ coords ->
                coords.size == 2}.map{ coords ->
                ExtendedOverlayItem(mapModel.Name, mapModel.Direccion, GeoPoint(coords[0], coords[1]),mapModel.Telefono,mapModel.Web,mapModel.Email,mapModel.Picture).apply {
                    setMarker(context?.getDrawable(R.drawable.ic_map_pin)) // markador custom
                }

            }
        }?.flatten()

        // Creamos un ItemizedIconOverlay con los OverlayItems
        val itemizedIconOverlay = overlayItems?.let {
            ItemizedIconOverlay(context, it, object : ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
                override fun onItemSingleTapUp(index: Int, item: OverlayItem): Boolean {

                    return true
                }

                override fun onItemLongPress(index: Int, item: OverlayItem): Boolean {
                    // hacemos un cast
                    val item = item as ExtendedOverlayItem
                    mapView.controller.animateTo(item.point)
                    mapView.controller.setZoom(15.0)
                    initializeCard(item)
                    initializeContactIcons(item)
                    return true
                }
            })
        }


        // Añadimos el ItemizedIconOverlay al MapView
        if (itemizedIconOverlay != null) {
            mapView.overlays.add(itemizedIconOverlay)
        }
        initEvents()
    }
    private fun initializeCard(item: ExtendedOverlayItem) {
        binding.svMapSelection.visibility = View.VISIBLE
        binding.tvName.text = item.title
        binding.tvLocation.text = item.snippet
        Picasso.get().load(item.picture).into(binding.ivMapSelection)
    }

    private fun loadingState() {
        Toast.makeText(context, "Loading map data", Toast.LENGTH_SHORT).show()
    }

    private fun errorState() {
        Toast.makeText(context, "Error loading map data", Toast.LENGTH_SHORT).show()
    }

    private fun initializeContactIcons(item: ExtendedOverlayItem){
        if (!item.telefono.isNullOrEmpty()) {
            binding.ivPhone.visibility = View.VISIBLE
            binding.ivPhone.setOnClickListener {
                callIcon(item.telefono)
            }
        } else {
            binding.ivPhone.visibility = View.GONE
        }
        if (!item.email.isNullOrEmpty()) {
            binding.ivEmail.visibility = View.VISIBLE
            binding.ivEmail.setOnClickListener {
                emailIcon(item.email)
            }
        } else {
            binding.ivEmail.visibility = View.GONE
        }
        if (!item.web.isNullOrEmpty()) {
            binding.ivWebsite.visibility = View.VISIBLE
            binding.ivWebsite.setOnClickListener {
                webIcon(item.web)
            }
        } else {
            binding.ivWebsite.visibility = View.GONE
        }
    }
    private fun webIcon(selection: String) {
        val webpage = Uri.parse(selection)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            requireContext().startActivity(intent)
        }
        Log.d("SELECTION", "Selection clicked: website")
    }

    private fun emailIcon(selection: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + selection))
        requireContext().startActivity(intent)
        Log.d("SELECTION", "Selection clicked: email")
    }

    private fun callIcon(selection: String){
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + selection))
        requireContext().startActivity(intent)
        Log.d("SELECTION", "Selection clicked: phone")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
