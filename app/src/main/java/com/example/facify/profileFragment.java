package com.example.facify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class profileFragment extends Fragment {

    LinearLayout l1,l2,l3,l4,l5,l6,l7,l8,l9;
    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        l1 = view.findViewById(R.id.librarylayout1);
        l2 = view.findViewById(R.id.librarylayout8);
        l3 = view.findViewById(R.id.librarylayout3);
        l4 = view.findViewById(R.id.librarylayout7);
        l5 = view.findViewById(R.id.librarylayout2);
        l6 = view.findViewById(R.id.librarylayout5);
        l7 = view.findViewById(R.id.librarylayout6);
        l8 = view.findViewById(R.id.librarylayout4);
        l9 = view.findViewById(R.id.librarylayout9);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"Happy","title");
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"Contempt","title");
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"Angry","title");
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"Disgust","title");
            }
        });
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"Neutral","title");
            }
        });
        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"Fear","title");
            }
        });
        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"Surprise","title");
            }
        });
        l8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"Sad","title");
            }
        });
        l9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"Personal Playlist","title");
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void replacefragment(Fragment fragment,String Ndata,String key){

        Bundle bundle = new Bundle();
        bundle.putString(key,Ndata);

        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

    }
}