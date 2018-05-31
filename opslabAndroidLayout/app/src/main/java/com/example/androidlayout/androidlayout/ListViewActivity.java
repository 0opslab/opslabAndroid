package com.example.androidlayout.androidlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {
    private static String[] data = new String[]{
            "Java","C","C++","Visual Basic","PHP","Perl","Python","C#","JavaScript",
            "Delphi","SAS","Ruby","PL/SQL","R","TypeScript","autohotkey","Swift","matlab","shell",
            "Haskell"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ListViewActivity.this,
                android.R.layout.simple_list_item_1,
                data
        );
        ListView listView = findViewById(R.id.listview_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this,data[i],Toast.LENGTH_SHORT).show();
            }
        });
    }
}
