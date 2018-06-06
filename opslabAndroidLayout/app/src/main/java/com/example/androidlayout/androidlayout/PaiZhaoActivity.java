package com.example.androidlayout.androidlayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidlayout.androidlayout.util.FileUtil;

import java.io.File;
import java.io.IOException;

public class PaiZhaoActivity extends AppCompatActivity {



    private static final String tag = "PaiZhaoActivity";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_zhao);

        imageView = (ImageView) this.findViewById(R.id.image_view);

        Button button = (Button) this.findViewById(R.id.open_camera);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(PaiZhaoActivity.this, Manifest.permission.CAMERA);
                    if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(PaiZhaoActivity.this,new String[]{Manifest.permission.CAMERA},222);
                        return;
                    }else{

                        takePicture();;//调用具体方法
                    }
                } else {

                    takePicture();;//调用具体方法
                }


            }
        });

        Button pickImageBtn = (Button) this.findViewById(R.id.pick_image);
        pickImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });

    }

    private static String picFileFullName;
    //拍照
    public void takePicture(){
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
            File outFile =  new File(outDir, System.currentTimeMillis() + ".jpg");
            picFileFullName = outFile.getAbsolutePath();
            Uri  mOriginUri = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mOriginUri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".fileprovider",
                        outFile);
            } else {
                mOriginUri = Uri.fromFile(outFile);
            }

            intent.putExtra(MediaStore.EXTRA_OUTPUT, mOriginUri);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);



            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } else{
            Log.e(tag, "请确认已经插入SD卡");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            //就像onActivityResult一样这个地方就是判断你是从哪来的。
            case 222:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    takePicture();
                } else {
                    // Permission Denied
                    Toast.makeText(PaiZhaoActivity.this, "很遗憾你把相机权限禁用了。请务必开启相机权限享受我们提供的服务吧。", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //打开本地相册
    public void openAlbum(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        this.startActivityForResult(intent, PICK_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.e(tag, "获取图片成功，path="+picFileFullName);
                toast("获取图片成功，path="+picFileFullName);
                setImageView(picFileFullName);
            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消了图像捕获
                Log.e(tag, "用户取消了图像捕获");
            } else {
                // 图像捕获失败，提示用户
                Log.e(tag, "拍照失败");
            }
        } else if (requestCode == PICK_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if(uri != null){
                    Log.e(tag, "onActivityResult: "+uri.toString() );
                    String realPath = FileUtil.getFilePathByUri(this,uri);
                    Log.e(tag, "获取图片成功，path="+realPath);
                    toast("获取图片成功，path="+realPath);
                    setImageView(realPath);
                }else{
                    Log.e(tag, "从相册获取图片失败");
                }
            }
        }
    }

    private void setImageView(String realPath){
        Bitmap bmp = BitmapFactory.decodeFile(realPath);
        int degree = readPictureDegree(realPath);
        if(degree <= 0){
            imageView.setImageBitmap(bmp);
        }else{
            Log.e(tag, "rotate:"+degree);
            //创建操作图片是用的matrix对象
            Matrix matrix=new Matrix();
            //旋转图片动作
            matrix.postRotate(degree);
            //创建新图片
            Bitmap resizedBitmap=Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
            imageView.setImageBitmap(resizedBitmap);
        }
    }



    /**
     * 读取照片exif信息中的旋转角度<br/>
     * http://www.eoeandroid.com/thread-196978-1-1.html
     *
     * @param path 照片路径
     * @return 角度
     */
    public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
