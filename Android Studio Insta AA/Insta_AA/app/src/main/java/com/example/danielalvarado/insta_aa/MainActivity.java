package com.example.danielalvarado.insta_aa;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 13;
    private ImageView imagePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagePicture = (ImageView) findViewById(R.id.imageViewGallery);

    }

    public void viewImageClicked(View view) {
        //invoke image gallery using a implicit intent
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        //where do we want to find the data
        //get external directory where certain media type is stored. In this case, images
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getParent();
        //get the UI representation
        Uri data = Uri.parse(pictureDirectoryPath);

        //set the data and type (where do we want to lok for this media and what media.
        //get all image types
        photoPickerIntent.setDataAndType(data,"image/*");

        //request code is a number thats not been used before.
        //we will invoke this activity and get something from it.
        startActivityForResult(photoPickerIntent,IMAGE_GALLERY_REQUEST);


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST)  {
                // we are hearing back from the camera.
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");//accessing the image taken
                //Averaging
                Bitmap newImage = avergingImage(cameraImage);
                //Decomposition MAX
                //Bitmap newImage = decompositionMax(cameraImage);
                imagePicture.setImageBitmap(newImage);

            }
        }
        */

        if (resultCode == RESULT_OK) {
            //THIS IS NEEDED SO I CAN ACCESS THE SD CARD
            // <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/> IN ANDROID
            //MANAGEMENT
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                //we are hearing back form the image gallery.
                //Uri and a data\

                //universal resource indicator -> the adress of the image in the SD card.
                Uri imageUri = data.getData();

                //declare a stream to read image data from the SD card
                InputStream inputStream;

                //anytime where reading a stream of data it might fail
                //we are getting a imput stream based on the URI of the image
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    //get a bitmap from the stream
                    Bitmap imagepicked = BitmapFactory.decodeStream(inputStream);

                    //show the image in the selected image view (imagePicture is that image view)
                    imagePicture.setImageBitmap(imagepicked);
                    imagePicture.setVisibility(View.VISIBLE);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
