package com.example.a2alexf68.networkcommunication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MenuActivity extends AppCompatActivity implements  View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button downloadSongButton = (Button) findViewById(R.id.downloadSongButton);
        downloadSongButton.setOnClickListener(this);

        Button addSongButton = (Button) findViewById(R.id.addSongButton);
        addSongButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.downloadSongButton)
        {
            startActivity(new Intent(this,DownloadActivity.class));
        }
        else if ((view.getId() == R.id.addSongButton))
        {
            startActivity(new Intent(this,AddSongActivity.class));

        }

    }


}
