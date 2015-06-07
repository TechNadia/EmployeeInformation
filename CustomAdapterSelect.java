package com.example.nadiaakter.employeeinformation;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Nadia Akter on 5/17/2015.
 */
public class CustomAdapterSelect extends ArrayAdapter<Employee> {

    LayoutInflater inflater;
    ArrayList<Employee> employeeArrayList;
    public static Map<Integer, Integer> idList = new ConcurrentHashMap<>();
    int id;
    String number;
    public static SparseBooleanArray itemChecked = new SparseBooleanArray();
    public static int pos;

    public CustomAdapterSelect(Context context, List<Employee> objects) {
        super(context, 0, objects);
        this.employeeArrayList = (ArrayList<Employee>) objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null){
            convertView = inflater.inflate(R.layout.custom_view_select, null);
            holder.ivPerson = (ImageView) convertView.findViewById(R.id.ivPerson);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvPosition = (TextView) convertView.findViewById(R.id.tvPosition);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Employee employee = employeeArrayList.get(position);
        holder.ivPerson.setImageResource(R.drawable.person);
        holder.tvName.setText(employee.getFullName());
        holder.tvPosition.setText(employee.getJobTitle());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox box = (CheckBox) buttonView.findViewById(R.id.checkbox);
                id = employee.getId();
                if (box.isChecked()){
                    itemChecked.delete(position);
                    itemChecked.put(position, true);
                    pos = position;
                    idList.put(position, id);
                    Log.v("===================================Checked: ======================================", "position: " + position);
                    Log.v("===================================Checked: ======================================", "id: " + id );
                }
                if (!box.isChecked()){
                    if (itemChecked.get(position)){
                        itemChecked.delete(position);
                        itemChecked.put(position, false);
                        idList.remove(position);
                    }
                    Log.v("=================================== Not Checked: ======================================", "position: " + position );
                    Log.v("===================================Not Checked: ======================================", "id: " + id );
                }
            }
        });
        boolean bool = itemChecked.get(position);
        holder.checkBox.setChecked(bool);
        return convertView;
    }

    private class ViewHolder{
        private TextView tvName;
        private TextView tvPosition;
        private ImageView ivPerson;
        private CheckBox checkBox;
    }

}

