package com.example.a2alexf68.networkcommunication;

import android.app.Activity;
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
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.AlertDialog;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class DownloadActivity extends Activity implements View.OnClickListener {

    class DownloadSongAsyncTask extends AsyncTask<String,Void,String> {

        private static final int HTTP_OK = 200;

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String artist = params[1];

            HttpURLConnection connection;

            try {


                //open connection to url
                //String url = "http://www.free-map.org.uk/course/mad/ws/hits.php";
                URL urlObject = new URL(url + "?artist=" + artist + "&format=json");
                connection = (HttpURLConnection) urlObject.openConnection();

                if (connection.getResponseCode() == HTTP_OK) {
                    //get back JSON data
                    InputStream in = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));

                    String jsonData = "";
                    String line = br.readLine();

                    while (line != null) {
                        jsonData += line; //add it to lines
                        line = br.readLine();// read line
                    }

                    // make the json pretty plain text
                    JSONArray jsonArray = new JSONArray(jsonData);

                    String result = "";

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject songObj = jsonArray.getJSONObject(i);

                        String songTitle = songObj.getString("song");
                        String artistName = songObj.getString("artist");
                        String year = songObj.getString("year");

                        String pretty = "Song Title: " +songTitle;
                        pretty += ", Artist: " +artistName;
                        pretty += ", Year: " + year + "\n";

                        result += pretty;
                    }

                    return result;
                }
                else
                {
                    return "Error: " + connection.getResponseMessage();
                }

            } catch (IOException e) {
                return "Error: " + e.getMessage();
            }  catch (JSONException e)
            {
                return "Error: " + e.getMessage();
            }
        }
        //display the string in the result text view
        public void onPostExecute (String songs){
            TextView songsTextView = (TextView) findViewById(R.id.tv1);r
            songsTextView.setText(songs);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Button downloadSongButton = (Button)findViewById(R.id.downloadSongButton);
        downloadSongButton.setOnClickListener(this);

    }
    public void onClick(View v)
    {
        EditText etURL = (EditText) findViewById(R.id.etURL);
        String url = etURL.getText().toString();

        EditText etArtist = (EditText) findViewById(R.id.etArtist);
        String artist = etArtist.getText().toString();

        new DownloadSongAsyncTask().execute(url,artist);
    }
}



