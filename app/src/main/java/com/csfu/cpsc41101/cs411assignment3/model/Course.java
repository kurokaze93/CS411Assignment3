package com.csfu.cpsc41101.cs411assignment3.model;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Course extends PersistentObject{

    protected String mCourseID;
    protected String mGrade;
    protected String mCWID;

    public Course(String courseid, String grade, String cwid){

        mCourseID = courseid;
        mGrade = grade;
        mCWID  = cwid;
    }

    public Course(){}

    public String getCourseId(){

        return mCourseID;
    }

    public void setCourseID(String courseid){

        mCourseID = courseid;
    }

    public String getGrade(){

        return mGrade;
    }

    public void setGrade(String grade){

        mGrade = grade;
    }

    @Override
    public void insert(SQLiteDatabase db) {

        ContentValues vals = new ContentValues();
        vals.put("CourseID", mCourseID);
        vals.put("Grade", mGrade);
        vals.put("Student",mCWID);
        db.insert("CourseEnrollment", null, vals);
    }

    @Override
    public void createTable(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment(CourseID Text, Grade Text, Student Text)");

    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor c) {

        mCourseID = c.getString(c.getColumnIndex("CourseID"));
        mGrade = c.getString(c.getColumnIndex("Grade"));
        mCWID = c.getString(c.getColumnIndex("Student"));
    }
}




