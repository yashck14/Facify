package com.example.facify;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class PlaylistFragment extends Fragment {

    ImageView upl;
    Uri Fileuri;
    String SongName;
    View rootView;
    ListView listView;
    ArrayList<String> arrayListSongName = new ArrayList<>();
    ArrayList<String> arrayListSongUrl = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    TextView playlisyTitle;




    private static final int selectFileRequestCode = 219;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    String data;
    int temp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_playlist, container, false);

        Bundle bundle = this.getArguments();
        data = bundle.getString("title");
        temp = bundle.getInt("keyno");
        playlisyTitle = rootView.findViewById(R.id.PlaylistTitle);
        playlisyTitle.setText(data);


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("ll");

        upl = rootView.findViewById(R.id.UploadButton);
        listView = rootView.findViewById(R.id.listView);

        retriveSongs();

        upl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectfile();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),HomeActivity.class);
                intent.putExtra("Sonagnamearray",arrayListSongName);
                intent.putExtra("UrlSongarray",arrayListSongUrl);
                intent.putExtra("position",position);
                intent.putExtra("play",data);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    private void retriveSongs() {
        databaseReference = FirebaseDatabase.getInstance().getReference("default/"+data);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds:snapshot.getChildren()){
                    UploadSongClass showSongObj = ds.getValue(UploadSongClass.class);
                    arrayListSongName.add(showSongObj.getName());
                    arrayListSongUrl.add(showSongObj.getUrl());
                }
                senddata(arrayListSongName,arrayListSongUrl);
                Customview customview= new Customview(getContext(),arrayListSongName);
                listView.removeAllViewsInLayout();
                listView.setAdapter(customview);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void senddata(ArrayList<String> arrayListSongName, ArrayList<String> arrayListSongUrl) {
    }

    private void selectfile() {
        Intent intent= new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select song"),selectFileRequestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == selectFileRequestCode && resultCode == RESULT_OK && data.getData()!=null){
            Fileuri = data.getData();

            Toast.makeText(getActivity(),"selected",Toast.LENGTH_SHORT).show();


            Cursor mcursur = getContext().getContentResolver().query(Fileuri,null,null,null,null);
            int indexed = mcursur.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            mcursur.moveToFirst();
            SongName = mcursur.getString(indexed);
            mcursur.close();
            uploadfiles(data.getData());

        }
    }

    private void uploadfiles(Uri data) {

        StorageReference reference = storageReference.child("default/").child(""+SongName+"");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri url = uriTask.getResult();

                int j = SongName.indexOf(".mp3");

                UploadSongClass songclass = new UploadSongClass(SongName.substring(25,j),url.toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(songclass);

                Toast.makeText(getContext(),"uploaded",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"not uploaded",Toast.LENGTH_SHORT).show();

            }
        });
    }
}