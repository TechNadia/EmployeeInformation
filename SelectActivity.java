package com.example.nadiaakter.employeeinformation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class SelectActivity extends ActionBarActivity {
    private ListView lvEmployee;
    private CustomAdapterSelect adapter;
    private List<Employee> employeeList;
    private EmployeeDBHandler dbHandler;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        initialize();
    }

    private void initialize() {
        Intent intent = getIntent();
        from = intent.getExtras().getString("from");

        lvEmployee = (ListView) findViewById(R.id.lvEmployee);
        dbHandler = new EmployeeDBHandler(this);
        employeeList = dbHandler.getAllEmployee();
        adapter = new CustomAdapterSelect(this, employeeList);
        lvEmployee.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.edit:
                Intent intent = new Intent(SelectActivity.this, EditActivity.class);
                Log.v("----------------------idList size ----------------------", "size: " + CustomAdapterSelect.idList.size());
                
                if (CustomAdapterSelect.idList.size()==1){
                    for (int i : CustomAdapterSelect.idList.keySet()) {
                        int id = CustomAdapterSelect.idList.get(i);
                        Log.d("============ id: ================", "====" + id);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        clear();
                        finish();
                        Toast.makeText(getApplicationContext(), "id: " + id, Toast.LENGTH_SHORT).show();
                        Log.v("----------------------name" + i + "-------------------------", "id: " + id);
                    }

                }
                return true;

            case R.id.delete:
                new AlertDialog.Builder(SelectActivity.this)
                        .setTitle("Delete Log")
                        .setMessage("This log will be deleted")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i : CustomAdapterSelect.idList.keySet()) {
                                    int id = CustomAdapterSelect.idList.get(i);
                                    dbHandler.deleteEmployee(id);

                                    Toast.makeText(getApplicationContext(), "id: " + id, Toast.LENGTH_SHORT).show();
                                    Log.v("----------------------name" + i + "-------------------------", "id: " + id);
                                }
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();

                                CustomAdapterSelect.idList.clear();
                                CustomAdapterSelect.itemChecked.clear();
                                Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Do nothing
                            }
                        })
                        .show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem edit = menu.findItem(R.id.edit);
        MenuItem delete = menu.findItem(R.id.delete);
        if (from.equals("edit")){
            delete.setVisible(false);
            if (CustomAdapterSelect.idList.size() > 1){
                edit.setEnabled(false);
            }
            else if (CustomAdapterSelect.idList.size() <= 1){
                edit.setEnabled(true);
            }
        }
        else if (from.equals("delete")){
            edit.setVisible(false);
            delete.setVisible(true);
        }


        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        clear();
        showMessage("on Resume");
    }

    private void clear(){
        CustomAdapterSelect.idList.clear();
        CustomAdapterSelect.itemChecked.clear();
    }
    private void showMessage(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
