package com.example.a2alexf68.networkcommunication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.URL;

public class AddSongActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        Button addSongButton = (Button) findViewById(R.id.addSongButton);
        addSongButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        EditText.editSongTitle = (EditText) findViewById(R.id.editSongTitle);
        String songTitle = editSongTitle.getText().toString();

        EditText.editArtist = (EditText) findViewById(R.id.editArtist);
        String artist = editArtist.getText().toString();

        EditText.editYear = (EditText) findViewById(R.id.editYear);
        String year = editYear.getText().toString();

        (new AddSongAsyncTask()).execute(songTitle, artist, year);
    }
    class AddSongAsyncTask extends AsyncTask <String, Void, String> {

        @Override
        protected String doinBackground(String... params)
        {
            String postData = "song=" + params [0] + "%artist=" +params [1] + params [2];

            try{
                URL urlOject = new URL("http://www.free-map.org.uk/course/mad/ws/hits.php");
            }
            catch (IOException e){
                return "Error" + e.getMessage();
            }

            return null;
        }
    }
}
