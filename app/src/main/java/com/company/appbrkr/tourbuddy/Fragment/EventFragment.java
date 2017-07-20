package com.company.appbrkr.tourbuddy.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;

import com.company.appbrkr.tourbuddy.Class.EventDatabase;
import com.company.appbrkr.tourbuddy.Class.EventDatabaseOpenHelper;
import com.company.appbrkr.tourbuddy.Class.EventModel;
import com.company.appbrkr.tourbuddy.R;

import java.util.ArrayList;


/**
 * Created by Safkat on 6/23/2017.
 */

public class EventFragment extends Fragment implements View.OnClickListener{

    private ImageButton addEvent;
    private RecyclerView myRecycleView;
    private String eventName,eventDesti,eventDate,eventTime,eventDes,eventBudget,cardName;
    private ArrayList<EventModel> eventlist=new ArrayList<>();

    public EventFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initializing the array list
        initializeList();

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflating the ui
        View rootView = inflater.inflate(R.layout.event_frag,container, false);
        //initializing the RecycleView and Floating action button

        myRecycleView=(RecyclerView) rootView.findViewById(R.id.cardView);
        addEvent=(ImageButton) rootView.findViewById(R.id.imageButton);

        //Setting the Ui parameters of the Recycle View
        myRecycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        if(eventlist.size()>0 && myRecycleView != null) {
            //Setting the Adapter of the recycle view
            myRecycleView.setAdapter(new MyAdapter(eventlist));
        }

        myRecycleView.setLayoutManager(layoutManager);

        //onclick of floating action button
        addEvent.setOnClickListener(this);

        return rootView;


    }


    @Override
    public void onClick(View v) {

        //Firing the Dialog Fragment
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        DialogFragForEvent dialogFragForEvent=new DialogFragForEvent();
        dialogFragForEvent.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
        dialogFragForEvent.show(fragmentManager,"Event Dialog");



    }

    //Custom adapter class

    public static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private ArrayList<EventModel> list;

        //constructor to populate the list
        public MyAdapter(ArrayList<EventModel> data) {
            this.list=data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Setting up the ui

            View v=LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_item,parent,false);
            //calling the custom view holder class

            MyViewHolder holder=new MyViewHolder(v);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            //setting the parameters for the views

            holder.eName.setText(list.get(position).getEventName()); //using the EventModel class to get the values of ArrayList
            holder.eDesti.setText(list.get(position).getEventDesti());
            holder.eDate.setText(list.get(position).getEventDate());
            holder.eTime.setText(list.get(position).getEventTime());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView eName,eDesti,eDate,eTime;

        public MyViewHolder(View v) {
            super(v);

            //initializing the views
            eName=(TextView)v.findViewById(R.id.setEName);
            eDesti=(TextView)v.findViewById(R.id.setEDestination);
            eDate=(TextView)v.findViewById(R.id.setEDate);
            eTime=(TextView)v.findViewById(R.id.setETime);
        }
    }



    //initialize the list
    private void initializeList() {
        //getting data from the database and populating the list

        EventDatabaseOpenHelper mDbhelper=new EventDatabaseOpenHelper(getContext());
        SQLiteDatabase db=mDbhelper.getReadableDatabase();
        String query = "SELECT * FROM " + EventDatabase.DefineTable.TABLE_NAME;

        Cursor res = db.rawQuery(query,null);

        while (res.moveToNext()) {
            eventName=res.getString(0);
            eventDesti=res.getString(1);
            eventDate=res.getString(2);
            eventTime=res.getString(3);
            eventDes=res.getString(4);
            eventBudget=res.getString(5);

            eventlist.add(new EventModel(eventName,eventDesti,eventDate,eventTime,eventDes,eventBudget));
        }


    }

}
