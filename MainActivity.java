package com.example.nadiaakter.employeeinformation;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView lvEmployee;
    private CustomAdapter adapter;
    private List<Employee> employeeList;
    private EmployeeDBHandler dbHandler;
    private Intent intent;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        lvEmployee = (ListView) findViewById(R.id.lvEmployee);
        dbHandler = new EmployeeDBHandler(this);
        updateDatabase();

        lvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                employee = (Employee) parent.getItemAtPosition(position);
                intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("employee", employee);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                add();
                return true;
            case R.id.update:
                intent = new Intent(MainActivity.this, SelectActivity.class);
                intent.putExtra("from", "edit");
                startActivity(intent);
                return true;
            case R.id.delete:
                intent = new Intent(MainActivity.this, SelectActivity.class);
                intent.putExtra("from", "delete");
                startActivity(intent);
                return true;
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void updateDatabase(){
        employeeList = dbHandler.getAllEmployee();
        adapter = new CustomAdapter(this, employeeList);
        lvEmployee.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void addMethod(View view){
        add();
    }

    public void add(){
        intent = new Intent(MainActivity.this, AddEmployee.class);
        startActivity(intent);
    }
}
