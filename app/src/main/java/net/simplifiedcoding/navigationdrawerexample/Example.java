package net.simplifiedcoding.navigationdrawerexample;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Example extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        Button button= (Button) findViewById(R.id.btnSave1);
        ImageView imageView=(ImageView)findViewById(R.id.imgShow);
//        imageView.setImageResource(R.drawable.falls);
        Picasso.with(getApplicationContext()).load("https://pixabay.com/get/e836b70b28f6053ed95c4518b74f4e9fea70e6d004b0154991f4c970a6e8bd_640.jpg")
                .into(imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try {
//                    Bitmap result= Picasso.with(getBaseContext())
//                            .load("https://pixabay.com/get/e836b70b28f6053ed95c4518b74f4e9fea70e6d004b0154991f4c970a6e8bd_640.jpg")
//                            .get();

                    WallpaperManager myWallpaper= WallpaperManager.getInstance(Example.this);
                    InputStream ins = new URL("https://pixabay.com/get/e836b70b28f6053ed95c4518b74f4e9fea70e6d004b0154991f4c970a6e8bd_640.jpg").openStream();
                    myWallpaper.setStream(ins);
//                    myWallpaper.setBitmap(result);
                    Toast.makeText(Example.this,
                            "Wallpaper successfully changed", Toast.LENGTH_SHORT)
                            .show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


//                File storagePath = Environment.getExternalStorageDirectory();
//
//                try {
//                    URL url = new URL ("https://media.mnn.com/assets/images/2015/03/forest-path-germany.jpg.653x0_q80_crop-smart.jpg");
//                    OutputStream output = new FileOutputStream(storagePath + "/myImage.png");
//                    Log.d("IMG",output.toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Log.d("LOCATION",storagePath.toString());

            }
        });
    }
}
