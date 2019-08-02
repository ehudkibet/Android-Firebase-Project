package com.example.mymorningfirebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText mJina,mArafa,mSiri;
    Button mSave,mView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJina=findViewById(R.id.edtName);
        mArafa=findViewById(R.id.edtMail);
        mSiri=findViewById(R.id.edtPassword);
        mSave=findViewById(R.id.btnSave);
        mView=findViewById(R.id.btnView);
        dialog= new ProgressDialog(this);
        dialog.setTitle("Saving");
        dialog.setMessage("Please Wait ...");

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start by receiving data from the user
                String jina=mJina.getText().toString();
                String arafa=mArafa.getText().toString();
                String siri=mSiri.getText().toString();
                long time= System.currentTimeMillis();
                String timeYetu=String.valueOf(time);

                //Check if the user is submitting empty fields
               if (jina.isEmpty()|| arafa.isEmpty()||siri.isEmpty()){
                   Toast.makeText(MainActivity.this, "Enter all Fields", Toast.LENGTH_SHORT).show();
               }
               else {
                   //We'll use miliseconds ka id in this case. using data type long
                   //Proceed to save data

                   DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users/"+timeYetu);
                   Item Z= new Item(jina,arafa,siri,timeYetu);
                   dialog.show();
                   ref.setValue(Z).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           dialog.dismiss();
                           if (task.isSuccessful()){
                               Toast.makeText(MainActivity.this, "User saved Successfully", Toast.LENGTH_SHORT).show();
                           }
                           else {
                               Toast.makeText(MainActivity.this, "Saving Failed", Toast.LENGTH_SHORT).show();
                           }
                           mJina.setText("");
                           mArafa.setText("");
                           mSiri.setText("");
                       }
                   });
               }
            }
        });
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent users= new Intent(MainActivity.this,UsersActivity.class);
                startActivity(users);
            }
        });
    }
}
