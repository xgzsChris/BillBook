package com.example.billbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;

public class BillBookAdapter extends ArrayAdapter<BillBook> {
    private int resourceId;

    public BillBookAdapter(@NonNull Context context, int resource, @NonNull List<BillBook> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }
    public View getView(int position, View convertView , ViewGroup parent){
        BillBook billBook=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.booktype=(TextView)view.findViewById(R.id.booktype);
            viewHolder.bookdate=(TextView)view.findViewById(R.id.bookdate);
            viewHolder.bookdescribe=(TextView)view.findViewById(R.id.bookdescribe);
            viewHolder.bookmoney=view.findViewById(R.id.bookmoney);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.bookmoney.setText(String.valueOf(billBook.getMoney()));
        viewHolder.bookdescribe.setText(billBook.getDescribe());
        viewHolder.bookdate.setText(billBook.getDate());
        viewHolder.booktype.setText(billBook.getType());
        return view;
    }
}
class ViewHolder{
    TextView booktype;
    TextView bookmoney;
    TextView bookdescribe;
    TextView bookdate;
}