package com.example.androidlayout.androidlayout;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class ImageSelectedActivity extends AppCompatActivity {

    public static final int SELECTED_PHOTO = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selected);

        Button button = findViewById(R.id.iamgeselected_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(ImageSelectedActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ImageSelectedActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    startAlbum();
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startAlbum();
                } else {
                    Toast.makeText(this, "YOU DENIED THE PERMISSION", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 打开相册
     */
    private void startAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("imagge/*");
        startActivityForResult(intent,SELECTED_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case SELECTED_PHOTO:
                if(requestCode == RESULT_OK){
                    //判断手机系统版本号
                    if(Build.VERSION.SDK_INT >= 19){
                        //4.4及以上的系统
                        String imgagePath = null;
                        Uri uri = data.getData();
                        if(DocumentsContract.isDocumentUri(this,uri)){
                            String docid = DocumentsContract.getDocumentId(uri);
                            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                                String id = docid.split(":")[1];
                                String selection = MediaStore.Images.Media._ID +"="+id;
                                imgagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
                            }else if("com.android.providers.downloads.docuemnts".equals(uri.getAuthority())){
                                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                                        Long.valueOf(docid));
                                imgagePath = getImagePath(contentUri,null);
                            }else if("content".equalsIgnoreCase(uri.getScheme())){
                                imgagePath = getImagePath(uri,null);
                            }else if("file".equalsIgnoreCase(uri.getScheme())){
                                imgagePath = uri.getPath();
                            }

                            if(imgagePath != null){
                                Bitmap bitmap = BitmapFactory.decodeFile(imgagePath);
                                ImageView imageView = findViewById(R.id.iamgeselected_img);
                                imageView.setImageBitmap(bitmap);
                            }else{

                            }
                        }
                    }else{
                        //4.4一下的系统
                    }
                }
                break;
                default:
                    break;
        }
    }

    /**
     * 通过Uri和selection来获取真实的图片路径
     * @param uri
     * @param selection
     * @return
     */
    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


}
