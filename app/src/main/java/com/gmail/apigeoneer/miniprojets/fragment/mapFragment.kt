package com.gmail.apigeoneer.miniprojets.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.myapplication.ui.main.place.Place
import com.example.myapplication.ui.main.place.PlaceRenderer
import com.gmail.apigeoneer.miniprojets.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.codelabs.buildyourfirstmap.place.PlacesReader
import com.google.maps.android.clustering.ClusterManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val places: List<Place> by lazy {
        PlacesReader(requireContext()).read()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment?  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment!!.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            mMap.clear() //clear old markers

            val googlePlex = CameraPosition.builder()
                .target(LatLng(36.898439, 10.180599))
                .zoom(10f)
                .bearing(0f)
                .tilt(45f)
                .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)


            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(36.887269, 10.330595))
                    .title("Cleaning La Marsa Beach event")
                    .snippet("reward : 2000 points / 13 particpiant")
            )

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(36.866606, 10.349390))
                    .title("Cleaning Sidi Bou Said Beach event")
                    .snippet("reward : 1800 points / 15 participant")

            )
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(36.887269, 10.330595))
                    .title("Cleaning Gammarth Beach event")
                    .snippet("reward : 2500 points / 11 participant")

            )
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(36.996466, 9.032202))
                    .title("Cleaning Barrage Sidi El Barrak forest")
                    .snippet("reward : 2000 points / 36 participant")

            )
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(34.724404, 11.191038))
                    .title("Cleaning Kerkanah Beach event")
                    .snippet("reward : 1800 points / 13 participant")

            )
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(33.159362, 9.170983))
                    .title("Cleaning Jebil Park event")
                    .snippet("reward : 1300 points / 8 participant")

            )
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(37.344092, 9.743537))
                    .title("Cleaning Cap Angela Forest")
                    .snippet("reward : 1700 points / 4 participant")

            )
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(37.334543, 9.691692))
                    .title("Cleaning Cap Hmem Beach event")
                    .snippet("reward : 1850 points / 16 participant")

            )
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(37.314869, 9.739871))
                    .title("Cleaning Douar Ghiran Forest")
                    .snippet("reward : 1450 points / 11 participant")

            )
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(37.322572, 9.799224))
                    .title("Cleaning Ain Damous Beach event")
                    .snippet("reward : 1700 points / 17 participant")

            )
        }

        return rootView
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}