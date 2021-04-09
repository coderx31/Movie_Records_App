package com.coderx.assignment02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import java.net.URL;

public class ImageDisplayActivity extends AppCompatActivity {
    private ImageView posterView;
    private String image_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // policies to download image from web
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        posterView = findViewById(R.id.posterView);



        new Thread(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = getIntent().getExtras();
                if (bundle != null){
                    image_url = bundle.getString("image"); // updating the image_url

                }
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // after decoding update the ImageView
                                URL url = new URL(image_url);
                                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                posterView.setImageBitmap(bitmap);
                            }catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                    });
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        }).start();
    }


}
