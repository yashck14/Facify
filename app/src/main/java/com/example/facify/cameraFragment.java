package com.example.facify;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;


public class cameraFragment extends Fragment {

    View rootview;
    Button button;
    Uri Fileuri;
    String Imagename;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootview =inflater.inflate(R.layout.fragment_camera, container, false);
         button = rootview.findViewById(R.id.selectimage);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 selectimage();
             }
         });

        return rootview;
    }

    private void selectimage() {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select song"),44);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 44 && resultCode == RESULT_OK && data.getData()!=null){
            Fileuri = data.getData();
            Toast.makeText(getActivity(),"selected",Toast.LENGTH_SHORT).show();


            Cursor mcursur = getContext().getContentResolver().query(Fileuri,null,null,null,null);
            int indexed = mcursur.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            mcursur.moveToFirst();
            String Imagena = mcursur.getString(indexed);
            int o = Imagena.indexOf(".jpeg");
            Imagename = Imagena.substring(0,o);
            Toast.makeText(getContext(), ""+Imagename, Toast.LENGTH_SHORT).show();
            mcursur.close();
            play(Imagename);
        }
    }

    private void play(String imagename) {
        String s1 = "img1";
        if(imagename.equals(Imagename)){
            replacefragment(new PlaylistFragment(),"Happy","title");
        }
        else if(imagename.equals("img2"))
            replacefragment(new PlaylistFragment(),"Angry","title");
        else if(imagename.matches("img3"))
            replacefragment(new PlaylistFragment(),"Neutral","title");
        else if(imagename.matches("img4"))
            replacefragment(new PlaylistFragment(),"Fear","title");
        else if(imagename.matches("img5"))
            replacefragment(new PlaylistFragment(),"Surprise","title");
        else if(imagename.matches("img6"))
            replacefragment(new PlaylistFragment(), "Sad", "title");
        else
            Toast.makeText(getContext(), "Image Cannot be recogonize", Toast.LENGTH_SHORT).show();
    }
    private void replacefragment(Fragment fragment,String Ndata,String key){

        Bundle bundle = new Bundle();
        bundle.putString(key,Ndata);
        bundle.putString("keyno","k");

        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }
}