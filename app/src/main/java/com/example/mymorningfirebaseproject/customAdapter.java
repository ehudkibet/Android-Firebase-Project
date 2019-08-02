package com.example.mymorningfirebaseproject;

//CustomAdapter
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Item> data;//modify here

    public CustomAdapter(Context mContext, ArrayList<Item> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual item
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_layout, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.mName =  convertView.findViewById(R.id.tvName);
            viewHolder.mMail =  convertView.findViewById(R.id.tvEmail);
            viewHolder.mPass =  convertView.findViewById(R.id.tvPassword);
            viewHolder.mBtnDel = convertView.findViewById(R.id.btnDel);
            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        final Item person = data.get(position);//modify here
        viewHolder.mName.setText(person.getmName());//modify here
        viewHolder.mMail.setText(person.getmEmail());//modify here
        viewHolder.mPass.setText(person.getmName());//modify here
        viewHolder.mBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users/"+person.getmId());
                ref.removeValue();
                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;

    }
    static class ViewHolder {
        TextView mName;
        TextView mMail;
        TextView mPass;//These don't have to be same names as the Id's
        //MODIFY THIS BLOCK OF CODE
        //TextView textViewTitle;
        Button mBtnDel;
    }

}
