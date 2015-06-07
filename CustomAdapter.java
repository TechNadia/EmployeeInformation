package com.example.nadiaakter.employeeinformation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nadia Akter on 5/17/2015.
 */
public class CustomAdapter extends ArrayAdapter<Employee> {
    LayoutInflater inflater;
    List<Employee> empList;

    public CustomAdapter(Context context, List<Employee> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        empList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        Employee employee;

        if (convertView == null){
            view = inflater.inflate(R.layout.custom_view, null);
            holder = new ViewHolder();

            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            holder.tvPosition = (TextView) view.findViewById(R.id.tvPosition);
            holder.ivPerson = (ImageView) view.findViewById(R.id.ivPerson);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        employee = empList.get(position);
        Log.d("===================Full name============", employee.getFullName());
        holder.tvName.setText(employee.getFullName());
        holder.tvPosition.setText(employee.getJobTitle());

        return view;
    }

    private class ViewHolder{
        private TextView tvName;
        private TextView tvPosition;
        private ImageView ivPerson;
    }
}
