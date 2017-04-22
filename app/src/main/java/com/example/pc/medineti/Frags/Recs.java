package com.example.pc.medineti.Frags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.medineti.Adapters.ReclamationAdapater;
import com.example.pc.medineti.Adapters.myReclamationAdapter;
import com.example.pc.medineti.Entities.Réclamation;
import com.example.pc.medineti.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.pc.medineti.MainActivity.carrierName;
import static com.example.pc.medineti.MainActivity.imei;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Recs.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Recs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Recs extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<Réclamation> lstRéclamation;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ReclamationAdapater adapter;
    private RecyclerView rv;

    public Recs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Recs.
     */
    // TODO: Rename and change types and number of parameters
    public static Recs newInstance(String param1, String param2) {
        Recs fragment = new Recs();
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
// Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recs, container, false);
        lstRéclamation = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Reclamations");
        adapter = new ReclamationAdapater(lstRéclamation, getContext());
        rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        rv.setLayoutManager(llm);
        updateList();
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

    private void updateList() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                lstRéclamation.add(dataSnapshot.getValue(Réclamation.class));
                adapter.notifyDataSetChanged();
                Log.d("DatasnapAdded", dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Réclamation post = dataSnapshot.getValue(Réclamation.class);
                Log.d("DatasnapChanged", dataSnapshot.getValue().toString());
                int index = getIndex(post);
                Log.d("Index", "" + index);
                lstRéclamation.set(index, post);
                adapter.notifyItemChanged(index, post);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Réclamation post = dataSnapshot.getValue(Réclamation.class);
                Log.d("DatasnapRemoved", dataSnapshot.getValue().toString());
                int index = getIndex(post);
                Log.d("Index", index + "");
                lstRéclamation.remove(index);
                adapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
