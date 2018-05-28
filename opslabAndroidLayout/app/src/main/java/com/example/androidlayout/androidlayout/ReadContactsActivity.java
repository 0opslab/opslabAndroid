package com.example.androidlayout.androidlayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReadContactsActivity extends AppCompatActivity {

    List<String> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_contacts);

        Button sharedPreftBtn = findViewById(R.id.readContacts_Read);
        sharedPreftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //动态申请权限
                if(ContextCompat.checkSelfPermission(ReadContactsActivity.this,
                        Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ReadContactsActivity.this,
                            new String[]{Manifest.permission.READ_CONTACTS},1);
                }else{
                    contactsList =  readContacts();
                    StringBuilder sbuf = new StringBuilder();
                    if(contactsList!=null){
                        for(String str:contactsList){
                            sbuf.append(str+"\n");
                        }
                    }
                    EditText text = findViewById(R.id.readContacts_text);
                    text.setText(sbuf.toString());
                }

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    List<String> lists = readContacts();
                    StringBuilder sbuf = new StringBuilder();
                    if(lists!=null){
                        for(String str:lists){
                            sbuf.append(str+"\n");
                        }
                    }
                    EditText text = findViewById(R.id.readContacts_text);
                    text.setText(sbuf.toString());
                } else {
                    Toast.makeText(this, "YOU DENIED THE PERMISSION", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    private List<String> readContacts(){
        List<String> list = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,null,null,null);
            if(cursor != null){
                while(cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phone = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    list.add(name+"/"+phone);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return list;
    }
}
