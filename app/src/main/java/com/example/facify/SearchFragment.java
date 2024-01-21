package com.example.facify;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    View rootview;
    SearchView searchView;
    ListView listView;
    DatabaseReference databaseReference;
    Customview customview;
    ArrayList<String> arrayListSongName = new ArrayList<>();
    ArrayList<String> arrayListSongUrl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_search, container, false);

//        getParentFragmentManager().setFragmentResultListener("loc", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                String data = result.getString("location");
//                Toast.makeText(getActivity(), ""+data, Toast.LENGTH_SHORT).show();
//            }
//        });
        searchView = rootview.findViewById(R.id.searchView);
        listView = rootview.findViewById(R.id.S_listview);

        retriveSongs();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return rootview;
    }

    public void searchList(String text){

    }

    private void retriveSongs() {
        databaseReference = FirebaseDatabase.getInstance().getReference("default/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds:snapshot.getChildren()){
                    UploadSongClass showSongObj = ds.getValue(UploadSongClass.class);
                    arrayListSongName.add(showSongObj.getName());
                    arrayListSongUrl.add(showSongObj.getUrl());
//                    jcAudios.add(JcAudio.createFromURL(showSongObj.getName(),showSongObj.getUrl()));
                }

                customview= new Customview(getContext(),arrayListSongName);
                listView.setAdapter(customview);
                listView.setTextFilterEnabled(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}