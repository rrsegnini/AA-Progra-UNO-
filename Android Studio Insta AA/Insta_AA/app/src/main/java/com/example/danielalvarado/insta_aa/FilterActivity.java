package com.example.danielalvarado.insta_aa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static com.example.danielalvarado.insta_aa.R.id.picImageView;

public class FilterActivity extends AppCompatActivity {


    public static final int CAMERA_REQUEST = 10;
    public static final int IMAGE_GALLERY_REQUEST = 13;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private ImageView imagePicture;
    private Bitmap originalImage;

    public long avgTime,decmpMax,decmpMin,gaussTime,desatTime,SobelTime,gaussTimePRUEBA;


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
        //originalImage = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();


    }


    //problems
    public void saveImgBtnClicked(View view) {
        int hasWriteContactsPermission = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }

        Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
        //storeImage(bitmap);
        Toast.makeText(FilterActivity.this,
                "Your image is saved to this folder", Toast.LENGTH_LONG)
                .show();
        //SaveImage(bitmap);
        //saveImageToExternalStorage(bitmap);
        saveImg(bitmap);
    }

    /*
    public void saveImgBtnClicked(View view) {
        try {
            Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
            //storeImage(bitmap);
            Toast.makeText(FilterActivity.this,
                    "Your image is saved to this folder", Toast.LENGTH_LONG)
                    .show();
            //SaveImage(bitmap);
            //saveImageToExternalStorage(bitmap);
            saveImg(bitmap);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    */

    public void desaturationBtnClicked(View view) {

        try {
            //DESATURATION FUNCTION
            Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
            Bitmap newDesBitmap = desaturation(originalImage);
            imagePicture.setImageBitmap(newDesBitmap);
            //gets a message
            Toast.makeText(FilterActivity.this,
                    "Desaturation button clicked", Toast.LENGTH_LONG)
                    .show();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }



    public  void avgBtnClicked(View view) {
        try {
            //AVERAGING FUNCTION
            Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
            Bitmap newAvgBitmap = avergingImage(originalImage);
            imagePicture.setImageBitmap(newAvgBitmap);

            //gets a message
            Toast.makeText(FilterActivity.this,
                    "Average button clicked", Toast.LENGTH_LONG)
                    .show();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void decompMaxBtnClicked(View view) {
        try {
            //DECOMP MAX FUNCTION
            Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
            Bitmap newDeMaxBitmap = decompositionMax(originalImage);
            imagePicture.setImageBitmap(newDeMaxBitmap);
            //gets a message
            Toast.makeText(FilterActivity.this,
                    "Decomposition Max button clicked", Toast.LENGTH_LONG)
                    .show();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void decompMinBtnClicked(View view) {
        try {
            //DECOMP MIN FUNCTION
            Bitmap bitmap = ((BitmapDrawable )imagePicture.getDrawable()).getBitmap();
            Bitmap newDeMinBitmap = decompositionMin(originalImage);
            imagePicture.setImageBitmap(newDeMinBitmap);
            //gets a message
            Toast.makeText(FilterActivity.this,
                    "Decomposition Max button clicked", Toast.LENGTH_LONG)
                    .show();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void ourFilterbtnClicked(View view) {
        try {

            Bitmap ourFilterA = sobelOperator(originalImage);

            imagePicture.setImageBitmap(ourFilterA);
            //gets a message
            Toast.makeText(FilterActivity.this,
                    "RobDan applied", Toast.LENGTH_LONG)
                    .show();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void gaussianBtnClicked(View view) {
        try {

            Bitmap newGausBitmap = GaussianFilter(originalImage);
            imagePicture.setImageBitmap(newGausBitmap);
            //gets a message
            Toast.makeText(FilterActivity.this,
                    "Gaussian Filter applied", Toast.LENGTH_LONG)
                    .show();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
                //CODE FOR STORING AN IMAGE
                int hasWriteContactsPermission = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_CODE_ASK_PERMISSIONS);
                    }
                    return;
                }
                //storeImage(bitmap);
                Toast.makeText(FilterActivity.this,
                        "Your image is saved to this folder", Toast.LENGTH_LONG)
                        .show();
                //SaveImage(bitmap);
                //saveImageToExternalStorage(bitmap);
                saveImg(cameraImage);
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
        //start time of the algorithm
        long stime = getTimeMil();
        int color, red, blue, green,newColor;

        //Makes a mutable copy of the bitmap image
        Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888, true);


        for (int y= 0;y<image.getHeight();y++) {
            for (int x = 0; x< image.getWidth();x++) {
                color = image.getPixel(x,y);
                blue = Color.blue(color);
                green = Color.green(color);
                red = Color.red(color);
                newColor = ((red+green+blue)/3)*0x00010101;
                image.setPixel(x, y, newColor);
            }
        }
        avgTime = getTimeMil() - stime;

        return image;
    }

    public Bitmap decompositionMax(Bitmap bitmap) {
        long stime = getTimeMil();
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
        decmpMax = getTimeMil() - stime;
        return image;
    }

    public Bitmap decompositionMin(Bitmap bitmap) {
        long stime = getTimeMil();
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
        decmpMin = getTimeMil() - stime;
        return image;
    }

    public Bitmap desaturation(Bitmap bitmap) {
        long stime = getTimeMil();
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
        desatTime = getTimeMil() - stime;
        return image;
    }


    public long getTimeMil() {
        Calendar calendar = Calendar.getInstance();
      /*The method getTimeInMillis() returns the
       * Calendar's time value in milliseconds.
       */
        long timeMilli2 = calendar.getTimeInMillis();
        return timeMilli2;

    }

    /*

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

*/
    /**
     * Method used to save an image to the gallery
     * @param image bitmap image thats going to be saved to the SD card
     */
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



    //Para sacar la suma de todos los elementos del kernel
    public int kernelSum(int[][] kernel){
        int sum=0;
        for (int i = 0; i < kernel.length; i++){
            for (int j=0; j < kernel.length; j++)
                sum += kernel[i][j];

        }
        return sum;
    }


    public Bitmap GaussianFilter(Bitmap bitmap){
        long startTime = getTimeMil();
        int[ ][ ] kernel =
                        {{1, 4, 7, 4, 1},
                        {4, 16, 26, 16, 4},
                        {7, 26, 41, 26, 7},
                        {4, 16, 26, 16, 4},
                        {1, 4, 7, 4, 1}};

        Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap imagePOST = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        int color, blue, green, red, sumBlue = 0, sumGreen = 0, sumRed = 0;

        int imgHeight = image.getHeight();
        int imgWidth = image.getWidth();
        int kernelLength = kernel.length;

        for (int cantidad_filtros_aplicados=0; cantidad_filtros_aplicados < 1; cantidad_filtros_aplicados++){
            for (int y = 1;y<imgHeight-kernelLength;y=y+1){

                for (int x=1; x<imgWidth-kernelLength; x++){
                    for (int v = 0; v < kernelLength; v++){
                        for (int u = 0; u < kernelLength; u++){

                            color = image.getPixel(x+u, y+v);
                            blue = Color.blue(color);
                            green = Color.green(color);
                            red = Color.red(color);
                            sumGreen += (green) * kernel[u][v];
                            sumRed += (red) * kernel[u][v];
                            sumBlue += (blue) * kernel[u][v];

                        }
                    }
                    sumGreen = sumGreen/kernelSum(kernel);
                    sumRed = sumRed/kernelSum(kernel);
                    sumBlue = sumBlue/kernelSum(kernel);
                    imagePOST.setPixel(x, y, Color.rgb(Math.abs(sumRed), Math.abs(sumGreen), Math.abs(sumBlue)));
                }
            }
        }
        gaussTime = getTimeMil() - startTime;
        return imagePOST;

    }

    public Bitmap sobelOperator(Bitmap bitmap) {
        long stime = getTimeMil();
        int[ ][ ] kernel =
                {{-1, 0, 1},
                        {-2, 0, 2},
                        {-1, 0, 1}};


        int[ ][ ] kernel2 =
                {{-1, -2, -1},
                        {0, 0, 0},
                        {1, 2, 1}};


        Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap imagePOST = bitmap.copy(Bitmap.Config.ARGB_8888, true);


        int color, blue, green, red;


        int imgHeight = image.getHeight();
        int imgWidth = image.getWidth();
        int kernelLenght = kernel.length;

        for (int cantidad_filtros_aplicados=0; cantidad_filtros_aplicados < 1; cantidad_filtros_aplicados++){
            for (int y = 1;y<imgHeight-kernelLenght;y=y+1){

                for (int x=1; x<imgWidth-kernelLenght; x++){

                    double sumGrey=0, sumGrey2=0;


                    for (int v = 0; v < kernelLenght; v++){
                        for (int u = 0; u < kernelLenght; u++){

                            color = image.getPixel(x+u, y+v);
                            blue = Color.blue(color);
                            green = Color.green(color);
                            red = Color.red(color);

                            color = (red+green+blue)/3;
                            sumGrey += (color) * kernel[u][v];
                            sumGrey2 += (color) * kernel2[u][v];

                        }
                    }
                    double gradient1 = Math.sqrt(Math.pow(sumGrey2, 2) + Math.pow(sumGrey, 2));
                    imagePOST.setPixel(x, y, (int)gradient1<<16 | (int)gradient1<<8 | (int)gradient1);

                }

            }
        }
        SobelTime = getTimeMil() - stime;
        return imagePOST;
    }


    public Bitmap gaussiano2(Bitmap bitmap){
        long startTime = getTimeMil();
        int[][] kernel =
                {{1, 4, 7, 4, 1},
                        {4, 16, 26, 16, 4},
                        {7, 26, 41, 26, 7},
                        {4, 16, 26, 16, 4},
                        {1, 4, 7, 4, 1}};

        Bitmap image = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap imagePOST = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        int color, blue, green, red, sumBlue = 0, sumGreen = 0, sumRed = 0;

        int imgHeight = image.getHeight();
        int imgWidth = image.getWidth();
        int kernelLength = kernel.length;

        for (int cantidad_filtros_aplicados = 0; cantidad_filtros_aplicados < 1; cantidad_filtros_aplicados++) {
            for (int y = 1; y < imgHeight - kernelLength; y = y + 1) {

                for (int x = 1; x < imgWidth - kernelLength; x++) {
                    for (int v = 0; v < kernelLength; v++) {
                        for (int u = 0; u < kernelLength; u++) {

                            color = image.getPixel(x + u, y + v);
                            blue = Color.blue(color);
                            green = Color.green(color);
                            red = Color.red(color);
                            sumGreen += (green) * kernel[u][v];
                            sumRed += (red) * kernel[u][v];
                            sumBlue += (blue) * kernel[u][v];

                        }
                    }
                    sumGreen = sumGreen / kernelSum(kernel);
                    sumRed = sumRed / kernelSum(kernel);
                    sumBlue = sumBlue / kernelSum(kernel);
                    imagePOST.setPixel(x, y, Color.rgb(Math.abs(sumRed), Math.abs(sumGreen), Math.abs(sumBlue)));
                }
            }
        }
        gaussTime = getTimeMil() - startTime;
        return imagePOST;
    }


}