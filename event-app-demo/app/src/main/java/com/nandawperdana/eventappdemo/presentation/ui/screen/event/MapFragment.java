package com.nandawperdana.eventappdemo.presentation.ui.screen.event;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nandawperdana.eventappdemo.R;
import com.nandawperdana.eventappdemo.api.event.EventModel;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private List<EventModel> listEvent = new ArrayList<>();

    MapView mMapView;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.listEvent = ((EventActivity) getActivity()).doRetrieveModel().getListEvents();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        mMapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        if (listEvent.size() != 0) {
            for (EventModel item : listEvent) {
                LatLng latlng = new LatLng(item.getLatitude(), item.getLongitude());
                MarkerOptions marker = new MarkerOptions().position(latlng).title(item.getName());

                builder.include(marker.getPosition());
                mMap.addMarker(marker);
            }
            LatLngBounds bounds = builder.build();

            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 70));
        }

    }
}
