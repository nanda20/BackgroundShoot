package net.simplifiedcoding.navigationdrawerexample;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class DetailImage extends AppCompatActivity {
    public String img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);
        ImageView imageView= (ImageView) findViewById(R.id.imageDetail);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

        Intent intent= getIntent();
        img= intent.getStringExtra("image");
        Picasso.with(getApplicationContext()).load(img).into(imageView);

        Button btnShare=(Button)findViewById(R.id.btnShare);
        Button btnSave=(Button)findViewById(R.id.btnSave);
        Button btnSetAs=(Button)findViewById(R.id.btnSetAs);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                shareIntent.setAction();
                shareIntent.putExtra(Intent.EXTRA_STREAM,img);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent,"Share TO"));

            }
        });
        btnSetAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    WallpaperManager myWallpaper= WallpaperManager.getInstance(DetailImage.this);
                    InputStream ins = new URL(img).openStream();
                    myWallpaper.setStream(ins);
                    Toast.makeText(getBaseContext(),"Wallpaper Has Been Changed",Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String root = Environment.getExternalStorageDirectory().toString();
//                Toast.makeText(getApplicationContext(),root,Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext())
                        .load(img)
                        .into(new Target() {
                                  @Override
                                  public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                      try {
                                          String root = Environment.getExternalStorageDirectory().toString();
                                          File myDir = new File(root + "/BackgroundShoot");

                                          if (!myDir.exists()) {
                                              myDir.mkdirs();
                                          }

                                          String name = new Date().toString() + ".jpg";
                                          myDir = new File(myDir, name);
                                          FileOutputStream out = new FileOutputStream(myDir);
                                          bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

                                          out.flush();
                                          out.close();
                                      } catch(Exception e){
                                          // some action
                                      }
                                  }

                                  @Override
                                  public void onBitmapFailed(Drawable errorDrawable) {
                                  }

                                  @Override
                                  public void onPrepareLoad(Drawable placeHolderDrawable) {
                                  }
                              }
                        );
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

