package com.example.mymorningfirebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class UsersActivity extends AppCompatActivity {

    ListView list;
    ArrayList<Item> users;
    CustomAdapter adapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        list=findViewById(R.id.listYangu);
        users= new ArrayList<>();
        adapter=new CustomAdapter(this,users);
        dialog= new ProgressDialog(this);
        dialog.setTitle("Loading ");
        dialog.setMessage("Please Wait ...");
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users");
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot snap: dataSnapshot.getChildren()){
                    Item mtu= snap.getValue(Item.class);
                    users.add(mtu);
                    dialog.dismiss();
                }
                //List kuupdate immediately
                adapter.notifyDataSetChanged();
              //Most current iaapear latest
                Collections.reverse(users);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Akikataa kulipa
                Toast.makeText(UsersActivity.this, "Database stopped,Please pay to Continue", Toast.LENGTH_SHORT).show();
                //dialog.dismiss();
            }
        });
        list.setAdapter(adapter  );
    }
}
