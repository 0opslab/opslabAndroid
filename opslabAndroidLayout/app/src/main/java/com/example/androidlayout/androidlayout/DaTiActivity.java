package com.example.androidlayout.androidlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidlayout.androidlayout.domain.Question;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

public class DaTiActivity extends AppCompatActivity {

    private static ArrayBlockingQueue<Question> questionQueue = new ArrayBlockingQueue<Question>(10);

    private static Question currentQuestion;

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_ti);

        textView = findViewById(R.id.DaTi_text);


        if(questionQueue.size() == 0){
            try {
                questionQueue.put(new Question(1,"当前系统是android系统",true));
                questionQueue.put(new Question(1,"当前系统不是android系统",false));
                currentQuestion = questionQueue.take();
                setTextView(currentQuestion.getDescript());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        Button BtnTrue = findViewById(R.id.index_button);
        Button BtnFlase = findViewById(R.id.index_button2);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(R.id.index_button == view.getId()){
                    checkQuestion(true);
                }
                if(R.id.index_button2 == view.getId()){
                    checkQuestion(false);
                }
            }
        };
        BtnTrue.setOnClickListener(listener);
        BtnFlase.setOnClickListener(listener);
    }

    public void checkQuestion(boolean btnResult){
        if(currentQuestion == null){
            setTextView("victory");
            Toast toast = Toast.makeText(DaTiActivity.this,
                    "victory",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(btnResult == currentQuestion.isResult()){
            currentQuestion = questionQueue.poll();
            if(currentQuestion == null){
                Toast toast = Toast.makeText(DaTiActivity.this,
                        "victory",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                setTextView("victory");
            }else{
                setTextView(currentQuestion.getDescript());
            }

        }else{
            Toast toast = Toast.makeText(DaTiActivity.this,
                    "选择错误，请重新选择",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    private void setTextView(String descript){
        if(descript != null){
            textView.setText(descript);
        }
    }
}
