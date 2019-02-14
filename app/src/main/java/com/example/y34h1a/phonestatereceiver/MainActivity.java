package com.example.y34h1a.phonestatereceiver;

import android.os.Bundle;
import android.app.Activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    Button button3;
    Button button2;
    private RecyclerView recyclerView;
    private CallListAdapter callListAdapter;
    private List<CallInfo> callInfos  = new ArrayList<>();
    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i= new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);

            }
        });



        recyclerView = (RecyclerView) findViewById(R.id.rvCallList);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        dbManager = CommonObjClass.getDatabaseHelper(getApplicationContext());
        callInfos = dbManager.getCallInfos();
        callListAdapter  = new CallListAdapter(getApplicationContext(), callInfos);
        recyclerView.setAdapter(callListAdapter);


        try {
            String path = Environment.getExternalStorageDirectory()
                    + "/";
            File myFile = new File(path, "calllist.txt");
            StringBuilder stringBuilder = new StringBuilder();
            FileOutputStream fOut = new FileOutputStream(myFile,true);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            for (int i = 0 ; i < callInfos.size(); i++){
                String latitude = callInfos.get(i).getLatitute();
                String longitude = callInfos.get(i).getLongitude();

                stringBuilder.append(callInfos.get(i).getPhnNumber());
                stringBuilder.append("\n");
                stringBuilder.append(" Lat: ").append(latitude).append(" Long: ").append(longitude);
                stringBuilder.append("\n\n\n");
            }

            myOutWriter.append(stringBuilder.toString());
            myOutWriter.close();
            fOut.close();
            Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println("In Main");
    }







}