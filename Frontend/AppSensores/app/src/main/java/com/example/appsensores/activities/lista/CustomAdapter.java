package com.example.appsensores.activities.lista;

import android.app.Activity;
import android.content.Context;
import android.os.TestLooperManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appsensores.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataItem> {

    Context context;
    int layoutResId;
    List<DataItem> data = null;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<DataItem> objects) {
        super(context, resource, objects);

        this.layoutResId = resource;
        this.context = context;
        this.data = objects;
    }

    static class DataHolder{
        TextView tvName;
        ImageView imEdit;
        ImageView imDelete;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DataHolder holder = null;

        if(convertView==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(layoutResId, parent,false);

            holder = new DataHolder();
            holder.tvName = (TextView)convertView.findViewById(R.id.name);
            holder.imEdit = (ImageView)convertView.findViewById(R.id.edit);
            holder.imDelete = (ImageView)convertView.findViewById(R.id.deleteBtn);

            convertView.setTag(holder);
        }else{
            holder = (DataHolder)convertView.getTag();
        }
        DataItem dataItem = data.get(position);
        holder.tvName.setText(dataItem.name);
        holder.imEdit.setImageResource(dataItem.resIdThumbnail1);
        holder.imDelete.setImageResource(dataItem.resIdThumbnail2);

        return convertView;
    }
}
