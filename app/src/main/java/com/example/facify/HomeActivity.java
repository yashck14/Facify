package com.example.facify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.jean.jcplayer.model.JcAudio;
//import com.example.jean.jcplayer.view.JcPlayerView;
import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bnV;
    ArrayList<String> arrayListSongName = new ArrayList<>();
    ArrayList<String> arrayListSongUrl = new ArrayList<>();
    TextView t1,t2,t3;
    int position;
    JcPlayerView jcPlayerView;
    ImageView imageView;
    FrameLayout frameLayout;
    LinearLayout linearLayout;
    ConstraintLayout constraintLayout;
    ArrayList<JcAudio> jcAudios = new ArrayList<>();
    private Boolean doublebackonce= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        replacefragment(new HomeFragment());

        bnV = findViewById(R.id.bottomnavview);
        jcPlayerView = findViewById(R.id.jcplayer);
        t1 = findViewById(R.id.textView2);
        t2 = findViewById(R.id.textView3);
        t3 = findViewById(R.id.textView6);
        imageView = findViewById(R.id.imageView9);
        frameLayout = findViewById(R.id.frame_layout);
        linearLayout = findViewById(R.id.linerplayer);
        constraintLayout = findViewById(R.id.playerView);

        Intent intent = getIntent();
        arrayListSongName = intent.getStringArrayListExtra("Sonagnamearray");
        arrayListSongUrl = intent.getStringArrayListExtra("UrlSongarray");
        position = intent.getIntExtra("position",-1);
        String playlist_name = intent.getStringExtra("play");
        if(position !=-1){
            linearLayout.setVisibility(View.VISIBLE);
            for (int i = 0;i<arrayListSongName.size();i++){
                jcAudios.add(JcAudio.createFromURL(arrayListSongName.get(i),arrayListSongUrl.get(i)));
            }
            jcPlayerView.initPlaylist(jcAudios,null);
            jcPlayerView.playAudio(jcAudios.get(position));
            jcPlayerView.createNotification();
            String temp = "From "+playlist_name;
            t1.setText(temp);
            t3.setText(arrayListSongName.get(position));
            t2.setText(arrayListSongName.get(position));


        }
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doublebackonce = false;
                constraintLayout.setVisibility(View.VISIBLE);
                bnV.setVisibility(View.GONE);
                frameLayout.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doublebackonce = true;
                constraintLayout.setVisibility(View.GONE);
                bnV.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);

            }
        });

        bnV.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.n_Home:
                    replacefragment(new HomeFragment());
                    break;
                case R.id.n_Search:
                    replacefragment(new SearchFragment());
                    break;
                case R.id.n_camera:
                    replacefragment(new cameraFragment());
                    break;
                case R.id.n_Profile:
                    replacefragment(new profileFragment());
                    break;
            }
            return true;
        });
    }

    private void replacefragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(doublebackonce) {
            super.onBackPressed();
            return;
        }
    }
}