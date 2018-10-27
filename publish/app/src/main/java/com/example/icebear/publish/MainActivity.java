package com.example.icebear.publish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity{

    final ArrayList<station> stations = new ArrayList<station>();
    DatabaseReference rf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        station s1 = new station("Station1",43.50646,16.438774);
        station s2 = new station("Station2",45.816207,15.988755);
        station s3 = new station("Station3",45.326941,14.443160);
        stations.add(s1);
        stations.add(s2);
        stations.add(s3);


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        rf = db.getReference();
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                station s;
                for(int i=0;i<stations.size();i++)
                {
                    s=stations.get(i);
                    s.generiraj();
                    rf.child(s.getName()).setValue(s);
                }
            }
        },0,5000);

    }


}