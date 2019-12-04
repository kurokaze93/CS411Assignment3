package com.csfu.cpsc41101.cs411assignment3;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.csfu.cpsc41101.cs411assignment3.R;
import com.csfu.cpsc41101.cs411assignment3.model.Course;
import com.csfu.cpsc41101.cs411assignment3.model.Student;
import com.csfu.cpsc41101.cs411assignment3.model.StudentDB;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {

    protected Menu addMenu;
    protected  int index = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add);

        Button addCourse = (Button) findViewById(R.id.add_course_button);
        addCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                LinearLayout courseLayout = findViewById(R.id.course_layout);
                LinearLayout gradeLayout = findViewById(R.id.grade_layout);
                EditText newCourse = new EditText(courseLayout.getContext());
                EditText newGrade = new EditText(gradeLayout.getContext());

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                newGrade.setGravity(Gravity.CENTER_HORIZONTAL);

                newCourse.setLayoutParams(lp);
                newGrade.setLayoutParams(lp);


                courseLayout.addView(newCourse, index);
                gradeLayout.addView(newGrade, index);

                //courseLayout.invalidate();
                //gradeLayout.invalidate();

                index++;

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_menu, menu);
        addMenu = menu;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.action_done){

            //int i = 1;

            EditText fNameView = (EditText) findViewById(R.id.first_name_val_id_add);
            EditText lNameView = (EditText) findViewById(R.id.last_name_val_id_add);
            EditText sCWIDView = (EditText) findViewById(R.id.s_cwid_val_id_add);


            String newFName = fNameView.getText().toString();
            String newLName = lNameView.getText().toString();
            String newCWID = sCWIDView.getText().toString();
            String newCourse;
            String newGrade;


            Student s = new Student(newFName, newLName, newCWID);
            Course c;

            ArrayList<Course> courses = new ArrayList<Course>();
            ArrayList<Student> studentList = new ArrayList<Student>();

            LinearLayout courseLayout = findViewById(R.id.course_layout);
            LinearLayout gradeLayout = findViewById(R.id.grade_layout);



            for(int i = 1; i < courseLayout.getChildCount(); i++){

                EditText sCourseView = (EditText) courseLayout.getChildAt(i);
                EditText sGradeView = (EditText) gradeLayout.getChildAt(i);

                newCourse = sCourseView.getText().toString();
                newGrade  = sGradeView.getText().toString();

                c = new Course(newCourse,newGrade, s.getCWID());

                courses.add(c);

            }

            s.setCourses(courses);
            s.insert(new StudentDB(this).getDatabase());
             new StudentDB(this).addStudent(s);
        }


        return super.onOptionsItemSelected(item);
    }
}
