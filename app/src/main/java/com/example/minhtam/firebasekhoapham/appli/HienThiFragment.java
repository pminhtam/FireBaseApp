package com.example.minhtam.firebasekhoapham.appli;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.minhtam.firebasekhoapham.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HienThiFragment extends Fragment {


    public HienThiFragment() {
        // Required empty public constructor
    }

    ListView lvHienThi;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    ArrayList<ItemImage> imgs;
    HienThiAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hien_thi, container, false);
        lvHienThi = (ListView) view.findViewById(R.id.lvHienThi);
        imgs = new ArrayList<>();
        adapter = new HienThiAdapter(getActivity(),imgs);
        lvHienThi.setAdapter(adapter);
        mData.child("hinhanh").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                imgs.add(dataSnapshot.getValue(ItemImage.class));
//                Log.e("HienThi",dataSnapshot.getValue().toString());
                adapter.notifyDataSetChanged();
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

        return view;
    }

}
