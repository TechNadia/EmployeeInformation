package com.example.nadiaakter.employeeinformation;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditActivity extends ActionBarActivity {
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etContact;
    private EditText etEmail;
    private EditText etAddress;
    private EditText etEmpID;
    private EditText etJobTitle;
    private Button btnUpdate;

    private String fName;
    private String lName;
    private String contact;
    private String email;
    private String address;
    private String empID;
    private String jobTitle;

    private Employee employee;
    private EmployeeDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initialize();
    }

    private void initialize() {
        Intent intent = getIntent();
        final int id = intent.getExtras().getInt("id");
        Log.d("============ id: ================", "====" + id);


        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etContact = (EditText) findViewById(R.id.etContact);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etEmpID = (EditText) findViewById(R.id.etEmpID);
        etJobTitle = (EditText) findViewById(R.id.etJobTitle);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        dbHandler = new EmployeeDBHandler(EditActivity.this);
        employee = dbHandler.getEmployeeByID(id);

        etFirstName.setText(employee.getFirstName());
        etLastName.setText(employee.getLastName());
        etEmail.setText(employee.getEmail());
        etContact.setText(employee.getContact());
        etAddress.setText(employee.getAddress());
        etEmpID.setText(employee.getEmpId());
        etJobTitle.setText(employee.getJobTitle());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            employee = new Employee();

            fName = etFirstName.getText().toString();
            lName = etLastName.getText().toString();
            email = etEmail.getText().toString();
            contact = etContact.getText().toString();
            address = etAddress.getText().toString();
            empID = etEmpID.getText().toString();
            jobTitle = etJobTitle.getText().toString();
            showMessage(jobTitle);

            employee.setId(id);
            employee.setFirstName(fName);
            employee.setLastName(lName);
            employee.setEmail(email);
            employee.setContact(contact);
            employee.setAddress(address);
            employee.setEmpId(empID);
            employee.setJobTitle(jobTitle);

            dbHandler.updateEmployee(employee);
            clear();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void showMessage(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    private void clear(){
        etFirstName.getText().clear();
        etLastName.getText().clear();
        etContact.getText().clear();
        etEmail.getText().clear();
        etAddress.getText().clear();
        etEmpID.getText().clear();
        etJobTitle.getText().clear();
    }
}
