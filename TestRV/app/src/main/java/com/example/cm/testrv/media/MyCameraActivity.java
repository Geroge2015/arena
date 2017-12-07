package com.example.cm.testrv.media;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cm.testrv.R;
import com.example.cm.testrv.utils.CommonUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by George on 2017/12/6.
 */

public class MyCameraActivity extends AppCompatActivity {
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private ImageView pic;
    private Uri imageUri;

    public static boolean startActivity(Context context) {
        Intent intent = new Intent(context, MyCameraActivity.class);
        return CommonUtils.startActivity(context, intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_camera_photo);
        init();
    }

    private void init() {
        Button btn = ((Button) findViewById(R.id.capture_button));
        Button choose = ((Button) findViewById(R.id.choose_from_album));
        pic = ((ImageView) findViewById(R.id.photo_display_view));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MyCameraActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MyCameraActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && PackageManager.PERMISSION_GRANTED == grantResults[0]) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission !!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);    // open the album.
    }

    private void takePhoto() {
        //create a file object to save the photo to be captured.
        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");

        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(MyCameraActivity.this,
                    "com.example.cm.testrv.camerafileprovider", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        // start the camera
        Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
        it.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(it, TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                //display the captured photo.
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    pic.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case CHOOSE_PHOTO:
                if (RESULT_OK == resultCode) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //if it is the document type, handle it with document id
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // if it is the content type, handle it as normal way.
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            pic.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "Failed to get Image", Toast.LENGTH_SHORT).show();
        }

    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //retrieve the picture path via uri and selection
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }
}
