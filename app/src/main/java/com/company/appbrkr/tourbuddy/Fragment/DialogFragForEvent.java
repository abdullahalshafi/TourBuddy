package com.company.appbrkr.tourbuddy.Fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.appbrkr.tourbuddy.Class.EventDatabase;
import com.company.appbrkr.tourbuddy.Class.EventDatabaseOpenHelper;
import com.company.appbrkr.tourbuddy.Class.EventModel;
import com.company.appbrkr.tourbuddy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Safkat on 7/20/2017.
 */


public class DialogFragForEvent extends android.support.v4.app.DialogFragment implements View.OnClickListener{

    private Button addEvent;
    private EditText title,destination,date,time,description,budget;
    private String eventName,eventDesti,eventDate,eventTime,eventDes,eventBudget,delId;


    public DialogFragForEvent() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //sets the interface

        View rootView=inflater.inflate(R.layout.dialog_frag_for_event,container,false);
        //set the title

        getDialog().setTitle("Add Event");

        //initializing the views

        addEvent=(Button) rootView.findViewById(R.id.addEvent);
        title=(EditText) rootView.findViewById(R.id.setTitle);
        destination=(EditText) rootView.findViewById(R.id.setDestination);
        date=(EditText) rootView.findViewById(R.id.setDate);
        time=(EditText) rootView.findViewById(R.id.setTimeHour);
        description=(EditText) rootView.findViewById(R.id.shortDescription);
        budget=(EditText) rootView.findViewById(R.id.setBudget);

        //on click listener

        addEvent.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {

        //getting values from the ui

        eventName=title.getText().toString();
        eventDesti=destination.getText().toString();
        eventDate=date.getText().toString();
        eventTime=time.getText().toString();
        eventDes=description.getText().toString();
        eventBudget=budget.getText().toString();
        delId=String.valueOf(System.currentTimeMillis());
        //saving those data into the database

        saveIntoDatabase(eventName,eventDesti,eventDate,eventTime,eventDes,eventBudget,delId);

        //reloading the Event Fragment, because new data has been added
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.motherView,new EventFragment(),"event_frag").commit();

        //Dismissing the Dialog Fragment
        getDialog().dismiss();

    }

    private void saveIntoDatabase(String eName,String eDesti, String eDate, String eTime, String eDes, String eBudget,String delId) {

        //Getting the instance of database open helper class
        EventDatabaseOpenHelper mDbhelper=new EventDatabaseOpenHelper(getContext());
        //opening the database in writable format
        SQLiteDatabase db=mDbhelper.getWritableDatabase();

        //query to inset data
        ContentValues values=new ContentValues();
        values.put(EventDatabase.DefineTable.COLUMN_NAME_ENAME,eName);
        values.put(EventDatabase.DefineTable.COLUMN_NAME_DESTI,eDesti);
        values.put(EventDatabase.DefineTable.COLUMN_NAME_DATE,eDate);
        values.put(EventDatabase.DefineTable.COLUMN_NAME_TIME,eTime);
        values.put(EventDatabase.DefineTable.COLUMN_NAME_DES,eDes);
        values.put(EventDatabase.DefineTable.COLUMN_NAME_BUDGET,eBudget);
        values.put(EventDatabase.DefineTable.COLUMN_NAME_DEL_ID,delId);
        db.insert(EventDatabase.DefineTable.TABLE_NAME,null,values);

        Toast.makeText(getContext(), "Inserted", Toast.LENGTH_SHORT).show();

    }
}

