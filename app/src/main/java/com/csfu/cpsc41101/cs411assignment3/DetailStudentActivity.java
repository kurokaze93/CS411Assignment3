package com.csfu.cpsc41101.cs411assignment3;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.csfu.cpsc41101.cs411assignment3.R;
import com.csfu.cpsc41101.cs411assignment3.model.Course;
import com.csfu.cpsc41101.cs411assignment3.model.Student;
import com.csfu.cpsc41101.cs411assignment3.model.StudentDB;

import java.util.ArrayList;

public class DetailStudentActivity extends AppCompatActivity {



    protected int studentIndex;
    StudentDB studentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_detail);
        studentList = new StudentDB(this);
        studentList.retrieveStudentObjects();

        studentIndex = getIntent().getIntExtra("StudentIndex", 0);

        Student sObj = studentList.getStudent().get(studentIndex);
        ArrayList<Course> courses = sObj.getCourses();
        Course cObj;

        LinearLayout courseLayout = findViewById(R.id.detail_course_layout);
        LinearLayout gradeLayout = findViewById(R.id.detail_grade_layout);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);



        EditText fNameView = (EditText) findViewById(R.id.first_name_val_id);
        EditText lNameView = (EditText) findViewById(R.id.last_name_val_id);
        EditText sCWIDView= (EditText) findViewById(R.id.s_cwid_val_id);


        fNameView.setText(sObj.getFirstName());
        lNameView.setText(sObj.getLastName());
        sCWIDView.setText(sObj.getCWID());



        for(int i = 0; i < courses.size(); i++){

            EditText sCourseView = new EditText(courseLayout.getContext());
            EditText sGradeView = new EditText(gradeLayout.getContext());

            sGradeView.setGravity(Gravity.CENTER_HORIZONTAL);

            sCourseView.setLayoutParams(lp);
            sGradeView.setLayoutParams(lp);

            cObj = courses.get(i);
            sCourseView.setText(cObj.getCourseId());
            sGradeView.setText(cObj.getGrade());

            courseLayout.addView(sCourseView,i+1);
            gradeLayout.addView(sGradeView, i+1);
            sCourseView.setEnabled(false);
            sGradeView.setEnabled(false);

        }


        fNameView.setEnabled(false);
        lNameView.setEnabled(false);
        sCWIDView.setEnabled(false);



    }


}
