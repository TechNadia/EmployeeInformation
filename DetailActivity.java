package com.example.nadiaakter.employeeinformation;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class DetailActivity extends ActionBarActivity {
    private TextView tvName;
    private TextView tvContact;
    private TextView tvEmail;
    private TextView tvAddress;
    private TextView tvEmpID;
    private TextView tvJobTitle;

    private String name;
    private String contact;
    private String email;
    private String address;
    private String empID;
    private String jobTitle;

    private  Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initialize();
        Bundle b = getIntent().getExtras();
        employee = b.getParcelable("employee");
        setValue();

        /*String str = "Name: " + employee.getFullName()
                + "\nContact: "+ employee.getContact()
                //+ "\nJob title: "+ employee.getJobTitle()
                + "\nAddress: " + employee.getAddress();
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();*/
    }

    private void setValue() {
        name = employee.getFullName();
        contact = employee.getContact();
        email = employee.getEmail();
        address = employee.getAddress();
        empID = employee.getEmpId();
        jobTitle = employee.getJobTitle();

        tvName.setText(name);
        tvContact.setText(contact);
        tvEmail.setText(email);
        tvAddress.setText(address);
        tvEmpID.setText(empID);
        tvJobTitle.setText(jobTitle);
    }

    private void initialize() {
        tvName = (TextView) findViewById(R.id.tvName);
        tvContact = (TextView) findViewById(R.id.tvContact);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvEmpID = (TextView) findViewById(R.id.tvEmployeeID);
        tvJobTitle = (TextView) findViewById(R.id.tvJobTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                Intent intent = new Intent(DetailActivity.this, EditActivity.class);
//                intent.putExtra("employee", employee);
                //showMsg("=="+employee.getId());
                intent.putExtra("id", employee.getId());
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showMsg(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}
