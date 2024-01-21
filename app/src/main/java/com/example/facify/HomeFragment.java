package com.example.facify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import android.widget.LinearLayout;
import android.widget.ScrollView;

public class HomeFragment extends Fragment {
    LinearLayout l1,l2,l3,l4,l5,l6,l7,l8;
    ScrollView k;
    ImageButton i1,i2,i3,i4,i5,i6,i7,i8;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        l1 = view.findViewById(R.id.linear3);
        l2 = view.findViewById(R.id.linear1);
        l3 = view.findViewById(R.id.linear2);
        l4 = view.findViewById(R.id.linear4);
        l5 = view.findViewById(R.id.linear5);
        l6 = view.findViewById(R.id.linear6);
        l7 = view.findViewById(R.id.linear7);
        l8 = view.findViewById(R.id.linear8);
        i1 = (ImageButton) view.findViewById(R.id.imageView7);
        i2 = (ImageButton) view.findViewById(R.id.imageView8);
        i3 = (ImageButton) view.findViewById(R.id.imageView6);
        i4 = (ImageButton) view.findViewById(R.id.imageView61);
        i5 = (ImageButton) view.findViewById(R.id.imageView62);
        i6 = (ImageButton) view.findViewById(R.id.imageView70);
        i7 = (ImageButton) view.findViewById(R.id.imageView71);
        i8 = (ImageButton) view.findViewById(R.id.imageView72);

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

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"Hit Hindi Songs","title");
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"Hit English Songs","title");
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"playlist1","title");
            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"playlist2","title");
            }
        });
        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"playlist3","title");
            }
        });
        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"playlist4","title");
            }
        });
        i7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"playlist5","title");
            }
        });
        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replacefragment(new PlaylistFragment(),"playlist6","title");
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