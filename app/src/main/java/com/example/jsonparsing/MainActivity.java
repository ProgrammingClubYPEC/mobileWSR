package com.example.jsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int currentPage = 1;
    private LinearLayout linearLayout;
    private TextView currentPageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.contactLinearLayout);
        currentPageTextView = findViewById(R.id.currentPageTextView);
        Update();
    }
    public void Update(){
        linearLayout.removeAllViews();
        new HttpConn(linearLayout,MainActivity.this).execute(getString(R.string.url)+"users?page="+String.valueOf(currentPage));
        currentPageTextView.setText(String.valueOf(currentPage));
    }
    public void OnClickBtn(View v) {
        if(v.getTag().toString().equals("l")) {
            if (currentPage != 1) {
                currentPage--;
                Update();
            }
        }
        else if(v.getTag().toString().equals("r")) {
            if (currentPage != (int)linearLayout.getTag()) {
                currentPage++;
                Update();
            }
        }
    }
}