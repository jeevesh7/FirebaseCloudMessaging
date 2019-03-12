package com.example.jeevesh.firebasecloudmessaging;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView logListView;
    ArrayList<String> logDataList;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView logListView = findViewById(R.id.log_list_view);
        logDataList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.layout,R.id.log,logDataList);
        logListView.setAdapter(arrayAdapter);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            updateList("getInstanceId failed");
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        updateList("Token: "+token);

                    }
                });
    }

    void updateList(String data){
        logDataList.add(data);
        arrayAdapter.notifyDataSetChanged();
    }

}

