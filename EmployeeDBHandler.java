package com.example.nadiaakter.employeeinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDBHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String EMPLOYEE_DB = "employeeDB";

    private static final String EMPLOYEE = "tbl_employee";

    public static final String ID = "_id";
    public static final String EMPLOYEE_ID = "emp_id";
    public static final String EMPLOYEE_FIRST_NAME = "first_name";
    public static final String EMPLOYEE_LAST_NAME = "last_name";
    public static final String EMPLOYEE_CONTACT = "phoneNo";
    public static final String EMPLOYEE_EMAIL = "email";
    public static final String EMPLOYEE_ADDRESS = "address";
    public static final String EMPLOYEE_JOB_TITLE= "job_title";

    public static final String[] COLUMNS = new String[]{
            ID,
            EMPLOYEE_ID,
            EMPLOYEE_FIRST_NAME,
            EMPLOYEE_LAST_NAME,
            EMPLOYEE_CONTACT,
            EMPLOYEE_EMAIL,
            EMPLOYEE_ADDRESS,
            EMPLOYEE_JOB_TITLE
    };


    public EmployeeDBHandler(Context context) {
        super(context, EMPLOYEE_DB, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + EMPLOYEE + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + EMPLOYEE_ID + " TEXT,"
                + EMPLOYEE_FIRST_NAME + " TEXT," + EMPLOYEE_LAST_NAME + " TEXT, "
                + EMPLOYEE_CONTACT + " TEXT," + EMPLOYEE_EMAIL + " TEXT, "
                + EMPLOYEE_ADDRESS + " TEXT, " + EMPLOYEE_JOB_TITLE + " TEXT)";
        db.execSQL(SQL_CREATE_EMPLOYEE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE);
        // Create tables again
        onCreate(db);

    }

    void addEmployee(Employee aEmployee) {
        SQLiteDatabase db = super.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMPLOYEE_ID, aEmployee.getEmpId());
        values.put(EMPLOYEE_FIRST_NAME, aEmployee.getFirstName());
        values.put(EMPLOYEE_LAST_NAME, aEmployee.getLastName());
        values.put(EMPLOYEE_CONTACT, aEmployee.getContact()== null ? "" : aEmployee.getContact());
        values.put(EMPLOYEE_EMAIL, aEmployee.getEmail()== null ? "":  aEmployee.getEmail());
        values.put(EMPLOYEE_ADDRESS, aEmployee.getAddress()== null ? "": aEmployee.getAddress());
        values.put(EMPLOYEE_JOB_TITLE, aEmployee.getJobTitle()== null ? "": aEmployee.getJobTitle());

        // Inserting Row
        long result = db.insert(EMPLOYEE, null, values);
//        Log.d("=========== Name ==========", "=====================" + result);

        db.close(); // Closing database connection
    }

    public Cursor getAllCursorEmployee(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        cursor = db.query(EMPLOYEE, COLUMNS, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<Employee>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + EMPLOYEE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setEmpId(cursor.getString(1));
                employee.setFirstName(cursor.getString(2));
                employee.setLastName(cursor.getString(3));
                employee.setContact(cursor.getString(4));
                employee.setEmail(cursor.getString(5));
                employee.setAddress(cursor.getString(6));
                employee.setJobTitle(cursor.getString(7));
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
        db.close();
        return employeeList;
    }

    public Employee getEmployeeByID(int id) {
        String selectQuery = "SELECT  * FROM " + EMPLOYEE + " where _id = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Employee employee = new Employee();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            employee.setId(Integer.parseInt(cursor.getString(0)));
            employee.setEmpId(cursor.getString(1));
            employee.setFirstName(cursor.getString(2));
            employee.setLastName(cursor.getString(3));
            employee.setContact(cursor.getString(4));
            employee.setEmail(cursor.getString(5));
            employee.setAddress(cursor.getString(6));
            employee.setJobTitle(cursor.getString(7));
        }
        db.close();
        return employee;
    }


    public int updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(EMPLOYEE_ID, employee.getEmpId());
        values.put(EMPLOYEE_FIRST_NAME, employee.getFirstName());
        values.put(EMPLOYEE_LAST_NAME, employee.getLastName());
        values.put(EMPLOYEE_CONTACT, employee.getContact());
        values.put(EMPLOYEE_EMAIL, employee.getEmail());
        values.put(EMPLOYEE_ADDRESS, employee.getAddress());
        values.put(EMPLOYEE_JOB_TITLE, employee.getJobTitle());


        return db.update(EMPLOYEE, values, ID + " = ?",
                new String[]{String.valueOf(employee.getId())});
    }

    void deleteEmployee(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(EMPLOYEE, ID +"= ?", new String[] {String.valueOf(id)});
        db.close();
    }
}

