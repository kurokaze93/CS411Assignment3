package com.csfu.cpsc41101.cs411assignment3.model;

import android.app.Person;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

public class StudentDB {

    //private static final StudentDB ourInstance = new StudentDB();

    protected ArrayList<Student> mStudent;

   /* static public StudentDB getInstance(){

        return ourInstance;
    }*/

    SQLiteDatabase mSQLiteDatabase;

    public StudentDB(Context context){

        File dbFile = context.getDatabasePath("student1.db");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile,null);

        //create tables
        new Student(). createTable(mSQLiteDatabase);
        new Course().createTable(mSQLiteDatabase);

        //create students
        //createStudentObjects();

    }

    public ArrayList<Student> getStudent(){

        return mStudent;
    }

    public void setStudent(ArrayList<Student> student){

        mStudent = student;
    }

    public void addStudent(Student s){

        mStudent.add(s);
    }

    public SQLiteDatabase getDatabase(){
        return mSQLiteDatabase;
    }

    public ArrayList<Student> retrieveStudentObjects(){

        mStudent = new ArrayList<Student>();
        Cursor cursor = mSQLiteDatabase.query("Student",null,null,null,null,null,null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Student sObj = new Student();
                sObj.initFrom(mSQLiteDatabase, cursor);
                mStudent.add(sObj);
            }
        }
        return mStudent;
    }

    /*protected void createStudentObjects() {
        // Create Jin student object
        Student s = new Student("Jin", "Nguyen", "889342812");
        Course c = new Course("CPSC 411", "A", "889342812");
        Course c1 = new Course("CPSC 471", "A","889342812");
        ArrayList<Course> courses = new ArrayList<Course>();



        courses.add(c);
        courses.add(c1);
        s.setCourses(courses);

        mSQLiteDatabase.delete("Student","CWID=?", new String[]{"889342812"});
        //mSQLiteDatabase.delete("CourseEnrollment","Student=?", new String[]{"889342812"});
        s.insert(mSQLiteDatabase);


        mStudent = new ArrayList<Student>();
        mStudent.add(s);

        // Create Another Person
        s = new Student("Lee", "Thi", "123456789");
        c = new Course("CPSC 471", "B","123456789");

        courses = new ArrayList<Course>();
        courses.add(c);
        s.setCourses(courses);
        mSQLiteDatabase.delete("Student","CWID=?", new String[]{"123456789"});
        s.insert(mSQLiteDatabase);
        //mSQLiteDatabase.delete("CourseEnrollment","Student=?", new String[]{"123456789"});


        mStudent.add(s);

    }*/
}
