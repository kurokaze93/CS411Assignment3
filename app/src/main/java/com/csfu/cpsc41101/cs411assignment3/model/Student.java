package com.csfu.cpsc41101.cs411assignment3.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Student extends PersistentObject{

    protected String mFirstName;
    protected String mLastName;
    protected String mCWID;
    protected ArrayList<Course> mCourses;

    public Student(String fname, String lname, String cwid){

        mFirstName = fname;
        mLastName = lname;
        mCWID = cwid;
    }

    public Student(){}

    public String getFirstName(){

        return mFirstName;
    }

    public  void setFirstName(String fname){

        mFirstName = fname;

    }

    public String getLastName(){

        return mLastName;
    }

    public void setLastName(String lname){

        mLastName = lname;
    }

    public String getCWID(){

        return mCWID;
    }

    public void setCWID(String cwid){

        mCWID = cwid;
    }

    public ArrayList<Course> getCourses(){

        return mCourses;

    }

    public void setCourses(ArrayList<Course> courses){

        mCourses = courses;
    }

    @Override
    public void insert(SQLiteDatabase db) {

        ContentValues vals = new ContentValues();
        vals.put("FirstName", mFirstName);
        vals.put("LastName", mLastName);
        vals.put("CWID", mCWID);
        db.insert("Student", null, vals);

        for (int i = 0; i < mCourses.size(); i++){

            mCourses.get(i).insert(db);
        }
    }

    @Override
    public void createTable(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Student(FirstName Text, LastName Text, CWID Text)");

    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor c) {

        mFirstName = c.getString(c.getColumnIndex("FirstName"));
        mLastName = c.getString(c.getColumnIndex("LastName"));
        mCWID = c.getString(c.getColumnIndex("CWID"));

        mCourses = new ArrayList<Course>();
        Cursor cursor = db.query("CourseEnrollment", null, "Student=?",new String[]{mCWID},null, null,null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Course cObj = new Course();
                cObj.initFrom(db, cursor);
                mCourses.add(cObj);
            }
        }

    }
}
