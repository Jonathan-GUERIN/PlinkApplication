package com.example.cameraplinkv0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int Back_Pic =  1;
    private Button btnpic;
    private ImageView imageView;
    private String picpath = null;
    private Button nextbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();

    }
    private void initActivity(){
         btnpic = (Button)findViewById(R.id.btnpic);
         imageView = (ImageView) findViewById(R.id.imageView);
         createOnBtnPic();
         nextbtn = (Button) findViewById(R.id.btnpic);
    }

    private void createOnBtnPic(){
        btnpic.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePic();

            }
        });

    }
    //pour prendre une photo et la mémoriser
    private void takePic(){
        // créer une intent pour ouvrir une fenêtre pour prendre des photos
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //pour savoir si l'appareil utilisé peut gérer la commande précédente (il a un appareil photo)
        if (intent.resolveActivity(getPackageManager()) != null){
            //creer un nom de fichier unique
            String timeStamp = new SimpleDateFormat( "yyyyMMdd_HHmm").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File picfile = File.createTempFile(imageFileName,".jpg",storageDir);
                //enregistrer le chemin complet
                picpath = picfile.getAbsolutePath();
                //Créer l'URI
                Uri picuri = FileProvider.getUriForFile(MainActivity.this,
                        MainActivity.this.getApplicationContext().getPackageName()+ ".provider", picfile);
                //transfert uri vers intant pour enregistrement photo dans fichier temporaire
                intent.putExtra(MediaStore.EXTRA_OUTPUT,picuri);
                startActivityForResult(intent,Back_Pic );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //vérifie le bon code de retour et l'état de retour ok
        if (requestCode == Back_Pic && resultCode == RESULT_OK){
            //récuper l'image
            Bitmap picture = BitmapFactory.decodeFile(picpath);
            //afficher l'image
            imageView.setImageBitmap(picture);
            nextbtn.setVisibility(View.VISIBLE);
            nextbtn.setOnClickListener(new Button.OnClickListener()
            {
                public void onClick(View v)
                {
                }
            });



        }
    }
    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(picpath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW/targetW, photoH/targetH));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(picpath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }




}