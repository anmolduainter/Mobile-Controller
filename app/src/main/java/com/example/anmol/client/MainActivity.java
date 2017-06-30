package com.example.anmol.client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button Up,Down,Suspend;
    SeekBar seekBar;
    int brightnessProgress=0;
    final String HOST="192.168.0.101";
    final int PORT=9999;
    int type=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Up= (Button) findViewById(R.id.VolumeUp);
        Down= (Button) findViewById(R.id.VolumeDown);
        seekBar= (SeekBar) findViewById(R.id.backLight);
        Suspend= (Button) findViewById(R.id.suspend);
        Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=1;
                new doit().execute();

            }
        });

        Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=2;
                new doit().execute();
            }
        });

        Suspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=4;
                new doit().execute();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                brightnessProgress=i;
                type=3;
                new doit().execute();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    class doit extends AsyncTask<Void,Void,Void>{


        String line="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

         //SOCKET start

            try {
                Socket socket=new Socket(HOST,PORT);
                BufferedWriter br=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                if(type==1) {

                    br.write("up");
                }
                else if (type==2){
                    br.write("down");
                }
                else if (type==3){
                    br.write("backLight"+brightnessProgress);
                }
                else if (type==4){
                    br.write("suspend");
                }

                br.flush();
                br.close();
//                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                line=bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            type=0;
        }
    }

}
