package com.example.pc.medineti.Frags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pc.medineti.Entities.Réclamation;
import com.example.pc.medineti.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapsAdmin.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapsAdmin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapsAdmin extends Fragment implements GoogleMap.OnMarkerClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MapView mMapView;
    private List<Marker> mMarkers;
    private GoogleMap googleMap;
    private OnFragmentInteractionListener mListener;
    private ArrayList<Réclamation> lstRéclamation;
    private FirebaseDatabase database;
    private DatabaseReference referenceReclamations;
    private DatabaseReference referenceSugg;
    private GoogleMap mMap;
    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;

    public MapsAdmin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapsAdmin.
     */
    // TODO: Rename and change types and number of parameters
    public static MapsAdmin newInstance(String param1, String param2) {
        MapsAdmin fragment = new MapsAdmin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_maps_admin, container, false);
        lstRéclamation = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getContext(),""+marker.getTag(),Toast.LENGTH_SHORT).show();
                        marker.showInfoWindow();

                        return true;
                    }
                });
                // For showing a move to my location button

                // For dropping a marker at a point on the Map

                // For zooming automatically to the location of the marker
                referenceReclamations = database.getReference("Reclamations");
                referenceReclamations.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        lstRéclamation.add(dataSnapshot.getValue(Réclamation.class));
                        Marker m = googleMap.addMarker(new MarkerOptions().position(new LatLng(dataSnapshot.getValue(Réclamation.class).getLatt(),dataSnapshot.getValue(Réclamation.class).getLang())).title(dataSnapshot.getValue(Réclamation.class).getTitre()).snippet(dataSnapshot.getValue(Réclamation.class).getDescription()));
                        m.setTag(dataSnapshot.getValue(Réclamation.class).getId());

                        m.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                        Log.d("DatasnapAdded", dataSnapshot.getValue().toString());
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        Réclamation post = dataSnapshot.getValue(Réclamation.class);
                        Log.d("DatasnapChanged", dataSnapshot.getValue().toString());
                        int index = getIndex(post);
                        Log.d("Index", "" + index);
                        lstRéclamation.set(index, post);
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        Réclamation post = dataSnapshot.getValue(Réclamation.class);
                        Log.d("DatasnapRemoved", dataSnapshot.getValue().toString());
                        int index = getIndex(post);
                        Log.d("Index", index + "");
                        lstRéclamation.remove(index);
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                referenceSugg = database.getReference("Suggestions");
                referenceSugg.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        lstRéclamation.add(dataSnapshot.getValue(Réclamation.class));
                        Marker m = googleMap.addMarker(new MarkerOptions().position(new LatLng(dataSnapshot.getValue(Réclamation.class).getLatt(),dataSnapshot.getValue(Réclamation.class).getLang())).title(dataSnapshot.getValue(Réclamation.class).getTitre()).snippet(dataSnapshot.getValue(Réclamation.class).getDescription()));
                        m.setTag(dataSnapshot.getValue(Réclamation.class).getId());
                        m.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                        Log.d("DatasnapAdded", dataSnapshot.getValue().toString());
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
       Toast.makeText(getContext(),""+marker.getTag(),Toast.LENGTH_SHORT).show();
        return true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
        private int getIndex(Réclamation post) {
            int index = -1;
            for (int i = 0; i < lstRéclamation.size(); i++) {
                if (lstRéclamation.get(i).getKey().equals(post.getKey())) {
                    return i;
                }
            }
            return index;
        }
}
