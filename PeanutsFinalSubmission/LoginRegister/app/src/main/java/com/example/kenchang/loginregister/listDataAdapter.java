package com.example.kenchang.loginregister;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class listDataAdapter extends ArrayAdapter{
    private List list = new ArrayList();
    listDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(dataMaker object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() { //number of items in the list
        return list.size();
    }

    @Override
    public Object getItem(int position) { //gets position of the item
        return list.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        handler handle;
        if (row==null){ //fills the row recreate that row
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(R.layout.row_layout,parent,false); //fills row on list view
            handle = new handler();
            handle.name = (TextView) row.findViewById(R.id.text_name); //sets the handler method equal to the row layout
            row.setTag(handle); //add row in to layout

        }else{ //if row is already available we get that row
            handle= (handler) row.getTag();
        }
        dataMaker maker= (dataMaker) this.getItem(position); //gets position of object
        assert maker != null;
        handle.name.setText(maker.getName());
        return row;
    }
    static class handler{
        TextView name;
    }
}
