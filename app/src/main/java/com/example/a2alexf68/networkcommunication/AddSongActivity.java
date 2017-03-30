package com.example.a2alexf68.networkcommunication;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddSongActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        Button addSongButton = (Button) findViewById(R.id.addSongButton2);
        addSongButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        EditText editSongTitle = (EditText) findViewById(R.id.editSongTitle);
        String songTitle = editSongTitle.getText().toString();

        EditText editArtist = (EditText) findViewById(R.id.editArtist);
        String artist = editArtist.getText().toString();

        EditText editYear = (EditText) findViewById(R.id.editYear);
        String year = editYear.getText().toString();

        (new AddSongAsyncTask()).execute(songTitle, artist, year);
    }
    class AddSongAsyncTask extends AsyncTask <String, Void, String> {

        @Override
        protected String doInBackground(String... params)
        {
            String postData = "song=" + params [0] + "%artist=" +params [1] + params [2];
            HttpURLConnection conn = null;

            try{
                URL urlObject = new URL("http://www.free-map.org.uk/course/mad/ws/hits.php");
                conn = (HttpURLConnection)urlObject.openConnection();

                conn.setDoOutput(true);
                conn.setFixedLengthStreamingMode(postData.length());

                OutputStream out = null;
                out = conn.getOutputStream();
                out.write(postData.getBytes());
                if(conn.getResponseCode() == 200)
                {
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String all = "", line;
                    while((line = br.readLine()) !=null)
                        all += line;
                    return all;
                }
                else
                    return "HTTP ERROR: " + conn.getResponseCode();


            }
            catch(IOException e)
            {
                return e.toString();
            }
            finally
            {
                if(conn!=null)
                    conn.disconnect();
            }
        }
        public void onPostExecute(String result)
        {
            TextView tv2= (TextView)findViewById(R.id.textView2);
            tv2.setText(result);
        }

    }

}


