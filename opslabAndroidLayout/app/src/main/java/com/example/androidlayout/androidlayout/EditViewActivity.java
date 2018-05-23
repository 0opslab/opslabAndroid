package com.example.androidlayout.androidlayout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.System.out;

public class EditViewActivity extends AppCompatActivity {

    /**
     * 保存文件
     * @param content
     * @return
     */
    private boolean saveEditContent(String content){
        FileOutputStream out = null ;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("editviewContent.log", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(content);
            return true;
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            try{
                if(writer != null){
                    writer.close();
                }
                if(out != null){
                    out.close();
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 从文件读取内容
     * @return
     */
    private String getEditContent(){
        FileInputStream input = null ;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            input = openFileInput("editviewContent.log");
            reader = new BufferedReader(new InputStreamReader(input));
            String line = "";
            while((line = reader.readLine()) != null){
                content.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(reader != null){
                    reader.close();
                }
                if(out != null){
                    out.close();
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return content.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);



        String content = getEditContent();
        if(content != null && content.length() > 0){
            EditText text = findViewById(R.id.editView_text);
            text.setText(content);
        }


        Button button = findViewById(R.id.editView_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = findViewById(R.id.editView_text);
                String inputStr = text.getText().toString();
                if(saveEditContent(inputStr)){
                    Toast toast = Toast.makeText(EditViewActivity.this,
                            "Save Success",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(EditViewActivity.this,
                            "Save Error",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }
        });
    }
}
