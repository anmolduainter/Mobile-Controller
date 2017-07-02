package com.example.anmol.client;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by anmol on 2/7/17.
 */

public class JoyStick extends AppCompatActivity {

    final int PORT=1200;
    String ipaddr;
    Button up,down,left,right,z,x;
    int type=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joystick);

        up= (Button) findViewById(R.id.upMov);
        down= (Button) findViewById(R.id.downMov);
        left= (Button) findViewById(R.id.leftMov);
        right= (Button) findViewById(R.id.rightMov);
        z= (Button) findViewById(R.id.z);
        x= (Button) findViewById(R.id.x);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type=1;
                new doit().execute();

            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type=2;
                new doit().execute();

            }

        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type=3;
                new doit().execute();

            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type=4;
                new doit().execute();


            }
        });

        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type=5;
                new doit().execute();


            }
        });

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type=6;
                new doit().execute();


            }
        });

        ipaddr=getIntent().getExtras().toString();
    }

    class doit extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Socket socket= null;
            try {
                socket = new Socket("192.168.0.105",PORT);
                BufferedWriter br=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                if (type==1){
                   br.write("up");
                }
                else if (type==2){
                    br.write("down");
                }
                else if (type==3){
                    br.write("left");
                }
                else if (type==4){
                    br.write("right");
                }
                else if (type==5){
                     br.write("z");
                }
                else if (type==6){
                    br.write("x");
                }

                br.flush();
                br.close();

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
