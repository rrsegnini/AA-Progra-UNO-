package com.example.danielalvarado.insta_aa;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.example.danielalvarado.insta_aa.R.id.picImageView;

public class FilterActivity extends AppCompatActivity {


    public static final int CAMERA_REQUEST = 10;
    public static final int IMAGE_GALLERY_REQUEST = 13;
    private ImageView imagePicture;
    private Bitmap originalImage;



    private static final String TAG = "FilterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
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

        imagePicture = (ImageView) findViewById(picImageView);

        /*
        Button buttonDesaturation = (Button) findViewById(R.id.desaturationBtn);
        buttonDesaturation.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                try {
                    //DESATURATION FUNCTION
                    Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
                    Bitmap newDesBitmap = desaturation(bitmap);
                    imagePicture.setImageBitmap(newDesBitmap);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        Button buttonAveraging = (Button) findViewById(R.id.averagingBtn);
        buttonAveraging.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                try {
                    //AVERAGING FUNCTION
                    Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
                    Bitmap newAvgBitmap = avergingImage(bitmap);
                    imagePicture.setImageBitmap(newAvgBitmap);
                    // Show toast when method is called:
                    //Toast.makeText(this, "AVERAGEALLED", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        Button buttonDeMin = (Button) findViewById(R.id.decompMinBtn);
        buttonDeMin.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                try {
                    //DECOMP MIN FUNCTION
                    Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
                    Bitmap newDeMinBitmap = decompositionMin(bitmap);
                    imagePicture.setImageBitmap(newDeMinBitmap);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        Button buttonDeMax = (Button) findViewById(R.id.decompMaxBtn);
        buttonDeMax.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                try {
                    //DECOMP MAX FUNCTION
                    Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
                    Bitmap newDeMaxBitmap = decompositionMax(bitmap);
                    imagePicture.setImageBitmap(newDeMaxBitmap);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        */

    }
    public void saveImgBtnClicked(View view) {
        Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
        //storeImage(bitmap);
        Toast.makeText(FilterActivity.this,
                "Your image is saved to this folder", Toast.LENGTH_LONG)
                .show();
        //SaveImage(bitmap);
        //saveImageToExternalStorage(bitmap);
        saveImg(bitmap);

    }

    public void desaturationBtnClicked(View view) {
        //DESATURATION FUNCTION
        Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
        Bitmap newDesBitmap = desaturation(originalImage);
        imagePicture.setImageBitmap(newDesBitmap);
        //gets a message
        Toast.makeText(FilterActivity.this,
                "Desaturation button clicked", Toast.LENGTH_LONG)
                .show();

    }

    public  void avgBtnClicked(View view) {
        //AVERAGING FUNCTION
        Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
        Bitmap newAvgBitmap = avergingImage(originalImage);
        imagePicture.setImageBitmap(newAvgBitmap);

        //gets a message
        Toast.makeText(FilterActivity.this,
                "Average button clicked", Toast.LENGTH_LONG)
                .show();

    }

    public void decompMaxBtnClicked(View view) {
        //DECOMP MAX FUNCTION
        Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
        Bitmap newDeMaxBitmap = decompositionMax(originalImage);
        imagePicture.setImageBitmap(newDeMaxBitmap);
        //gets a message
        Toast.makeText(FilterActivity.this,
                "Decomposition Max button clicked", Toast.LENGTH_LONG)
                .show();

    }


    public void decompMinBtnClicked(View view) {
        //DECOMP MAX FUNCTION
        Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
        Bitmap newDeMinBitmap = decompositionMin(originalImage);
        imagePicture.setImageBitmap(newDeMinBitmap);
        //gets a message
        Toast.makeText(FilterActivity.this,
                "Decomposition Max button clicked", Toast.LENGTH_LONG)
                .show();

    }




    public void takePhotoBtnClicked(View view) {
        Intent cameraIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,CAMERA_REQUEST);

    }

    public  void imageGalleryClicked(View view) {
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST)  {
                // we are hearing back from the camera.
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");//accessing the image taken
                //Averaging
                //Bitmap newImage = avergingImage(cameraImage);
                //Decomposition MAX
                //Bitmap newImage = decompositionMax(cameraImage);

                imagePicture.setImageBitmap(cameraImage);

                //creates the private ORIGINAL IMAGE (GLOBAL)
                originalImage = cameraImage.copy(Bitmap.Config.ARGB_8888, true);

                //storeImage(cameraImage);

            }
        }
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
                    Bitmap imagePicked = BitmapFactory.decodeStream(inputStream);

                    //show the image in the selected image view (imagePicture is that image view)
                    imagePicture.setImageBitmap(imagePicked);

                    //
                    originalImage = imagePicked.copy(Bitmap.Config.ARGB_8888, true);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Unable to open image",Toast.LENGTH_LONG).show();
                }

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


    ////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////


    /**
     * Stores the image in the SD card
     * @param image bitmap image that is going to be saved
     */
    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d(TAG,
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }


    /** Create a File for saving an image or video */
    private  File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveImageToExternalStorage(Bitmap finalBitmap) {
        String root =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file1 = new File(myDir, fname);
        if (file1.exists())
            file1.delete();
        try {
            FileOutputStream out = new FileOutputStream(file1);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(this, new String[] { file1.toString() },                null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });

    }

    public void saveImg(Bitmap  image) {
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureName = getPictureName();
        File imageFile = new File(pictureDirectory,pictureName);
        Uri pictureUri = Uri.fromFile(imageFile);

        if (imageFile.exists())
            imageFile.delete();
        try {
            FileOutputStream out = new FileOutputStream(imageFile);
            image.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(this, new String[] { imageFile.toString() },null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });


    }

    public String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStamp = sdf.format(new Date());
        return "FilterImg" + timeStamp + ".jpg";

    }

}
