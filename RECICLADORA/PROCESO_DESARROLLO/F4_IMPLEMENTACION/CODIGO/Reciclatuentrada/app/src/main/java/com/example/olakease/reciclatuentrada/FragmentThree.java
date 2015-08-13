package com.example.olakease.reciclatuentrada;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.olakease.reciclatuentrada.R;

import java.io.File;


public class FragmentThree extends Fragment  {

	View view;
    Button btnvideo1,btnvideo2,btnvideo3;
	
	
	//DataBaseManager manager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		 view = inflater.inflate(R.layout.fragment_layout_three, container,
				false);
        btnvideo1=(Button)view.findViewById(R.id.btnvideo);
        btnvideo2=(Button)view.findViewById(R.id.btnvideo2);
        btnvideo3=(Button)view.findViewById(R.id.btnvideo3);


        btnvideo1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                File videoFile2Play = new File("/storage/extSdCard/Nueva carpeta/video promocional 2 L.mp4");
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.fromFile(videoFile2Play), "video/mpeg");
                startActivity(i);
            }
        });
        btnvideo2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                File videoFile2Play = new File("/storage/extSdCard/Nueva carpeta/video promocional 1.mp4");
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.fromFile(videoFile2Play), "video/mpeg");
                startActivity(i);
            }
        });
        btnvideo3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                File videoFile2Play = new File("/storage/extSdCard/Nueva carpeta/video promocional 3 L.mp4");
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.fromFile(videoFile2Play), "video/mpeg");
                startActivity(i);
            }
        });



        return view;
	

	}


}


		
		
		
		
		
		
		
		
		
