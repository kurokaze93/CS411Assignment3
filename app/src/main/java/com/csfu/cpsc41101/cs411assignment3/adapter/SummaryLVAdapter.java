package com.csfu.cpsc41101.cs411assignment3.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.csfu.cpsc41101.cs411assignment3.DetailStudentActivity;
import com.csfu.cpsc41101.cs411assignment3.R;
import com.csfu.cpsc41101.cs411assignment3.model.Course;
import com.csfu.cpsc41101.cs411assignment3.model.Student;
import com.csfu.cpsc41101.cs411assignment3.model.StudentDB;

public class SummaryLVAdapter extends BaseAdapter {

    StudentDB mStudentDB;

    public SummaryLVAdapter(Context context){
        mStudentDB = new StudentDB(context);
        mStudentDB.retrieveStudentObjects();
    }


    @Override
    public int getCount(){

        return mStudentDB.getStudent().size();
    }

    @Override
    public Object getItem(int i){

        return mStudentDB.getStudent().get(i);
    }

    @Override
    public long getItemId(int i){

        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row_view;

        if (view == null) {
            //cnt++;
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.student_row, viewGroup, false);
        } else row_view = view;

        Student s = mStudentDB.getStudent().get(i);
        TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name);
        firstNameView.setText(s.getFirstName());
        TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name);
        lastNameView.setText(s.getLastName());
        row_view.setTag(new Integer(i));

        //

        row_view.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(view.getContext(), DetailStudentActivity.class);
                        intent.putExtra("StudentIndex", ((Integer)view.getTag()).intValue());
                        view.getContext().startActivity(intent);
                    }
                }
        );

        return row_view;
    }





}




