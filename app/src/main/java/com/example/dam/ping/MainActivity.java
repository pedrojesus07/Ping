package com.example.dam.ping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity {

    private Button btPing, btClear;
    private TextView tvPing;

    private void doPing(){
//       ProcessBuilder pb = new ProcessBuilder().command("/system/bin/ping", "-c 5", "127.0.0.1");
   //     ProcessBuilder pb = new ProcessBuilder().command("/system/bin/ping", "-c 5", "8.8.8.8");
        ProcessBuilder pb = new ProcessBuilder().command("ip", "route");

        try{
            Process process = pb.start();

            InputStream processStdOutput = process.getInputStream();
            Reader r = new InputStreamReader(processStdOutput);
            BufferedReader br = new BufferedReader(r);
            String line;
            while ((line = br.readLine()) != null) {
                Log.v("xyz", line);
                tvPing.append(line + "\n");
            }
        }catch (IOException ioe){
            ioe.getMessage();
            Log.v("xyz", "Error al ejecutar");
            tvPing.setText("Error al ejecutar");
        }
    }

    private void events(){
        btPing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doPing();
            }
        });

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPing.setText("");
            }
        });
    }

    private void init() {
        btPing = findViewById(R.id.btPing);
        tvPing = findViewById(R.id.tvPing);
        btClear = findViewById(R.id.btClear);

        events();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
}
