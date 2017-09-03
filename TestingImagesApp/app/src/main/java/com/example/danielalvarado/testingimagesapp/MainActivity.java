package com.example.danielalvarado.testingimagesapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST = 10;
    private ImageView imgCameraPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        imgCameraPhoto = (ImageView) findViewById(R.id.cameraImageView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void takePhotoBtnClicked(View view) {
        Intent cameraIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,CAMERA_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST)  {
                 // we are hearing back from the camera.
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");//accessing the image taken
                //Averaging
                Bitmap newImage = avergingImage(cameraImage);
                //Decomposition MAX
                //Bitmap newImage = decompositionMax(cameraImage);
                imgCameraPhoto.setImageBitmap(newImage);

            }
        }

    }


    /**
     * This method takes a image (bitmap) and mutates it to an avering gray filter
     * @param bitmap image received
     */
    public Bitmap avergingImage(Bitmap bitmap) {
        int color, red, blue, green,newColor;

        //Makes a mutable copy of the bitmap image
        Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        for (int y= 0;y<image.getHeight();y++) {
            for (int x = 0; x< image.getWidth();x++) {
                color = image.getPixel(x,y);
                blue = Color.blue(color);
                green = Color.green(color);
                red = Color.red(color);
                //newColor = Color.rgb(red/3,green/3,blue/3);
                //Averaging:
                newColor = ((red+green+blue)/3)*0x00010101;
                image.setPixel(x, y, newColor);
            }
        }
        return image;
    }

    public Bitmap decompositionMax(Bitmap bitmap) {
        int color, red, blue, green,newColor;

        //Makes a mutable copy of the bitmap image
        Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        for (int y= 0;y<image.getHeight();y++) {
            for (int x = 0; x< image.getWidth();x++) {
                color = image.getPixel(x,y);
                blue = Color.blue(color);
                green = Color.green(color);
                red = Color.red(color);

                ////Decomposition max
                newColor = ((Math.max(Math.max(red, green), blue)))*0x00010101;

                image.setPixel(x, y, newColor);
            }
        }
        return image;
    }

    public Bitmap decompositionMin(Bitmap bitmap) {
        int color, red, blue, green,newColor;

        //Makes a mutable copy of the bitmap image
        Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        for (int y= 0;y<image.getHeight();y++) {
            for (int x = 0; x< image.getWidth();x++) {
                color = image.getPixel(x,y);
                blue = Color.blue(color);
                green = Color.green(color);
                red = Color.red(color);

                //Decomposition min
                newColor = ((Math.min(Math.min(red, green), blue)))*0x00010101;

                image.setPixel(x, y, newColor);
            }
        }
        return image;
    }

    public Bitmap desaturation(Bitmap bitmap) {
        int color, red, blue, green,newColor;

        //Makes a mutable copy of the bitmap image
        Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        for (int y= 0;y<image.getHeight();y++) {
            for (int x = 0; x< image.getWidth();x++) {
                color = image.getPixel(x,y);
                blue = Color.blue(color);
                green = Color.green(color);
                red = Color.red(color);
                //Desaturation
                newColor = (((Math.max(Math.max(red, green), blue) + Math.min(Math.min(red, green),
                        blue))/2) *0x00010101);

                image.setPixel(x, y, newColor);
            }
        }
        return image;
    }




}
