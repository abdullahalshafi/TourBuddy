package com.company.appbrkr.tourbuddy.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.company.appbrkr.tourbuddy.Fragment.NearbyFragment;
import com.company.appbrkr.tourbuddy.R;
import com.company.appbrkr.tourbuddy.Fragment.EventFragment;
import com.company.appbrkr.tourbuddy.Fragment.GalleryFragment;
import com.company.appbrkr.tourbuddy.Fragment.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;


public class SecondActivity extends AppCompatActivity {

    private Button logout;
    private Intent intent;
    private TextView userName;

    private FirebaseAuth mAuth;

    Fragment fragment;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    private BottomNavigationView mBottombar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);


        mBottombar=(BottomNavigationView) findViewById(R.id.navigation);

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().add(R.id.motherView,new HomeFragment()).commit();
        }

        mBottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                fragmentManager=getSupportFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                String tag;
                /*checking the ids to server correct fragment transition*/
                if(item.getItemId() == R.id.menu_nearby){
                    fragment = new NearbyFragment();
                    tag="NearbyFragment";
                    item.setChecked(true);
                }else if(item.getItemId() == R.id.menu_events){
                    fragment=new EventFragment();
                    tag="event_frag";
                    item.setChecked(true);
                }else if(item.getItemId() == R.id.menu_camera){
                    fragment=new GalleryFragment();
                    tag="camera_frag";
                    item.setChecked(true);
                }else {
                    fragment = new HomeFragment();
                    tag="HomeFragment";
                    item.setChecked(true);
                }

                updateToolbarText(item.getTitle());
                fragmentTransaction.replace(R.id.motherView,fragment,tag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();

                return false;
            }
        });


      /*  logout = (Button) findViewById(R.id.logout);
        userName=(TextView) findViewById(R.id.userName);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser userId=mAuth.getCurrentUser();
        userName.setText("Welcome "+userId.toString());

        if (mAuth.getCurrentUser() == null) {
            finish();
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }*/

         /*   logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();
                    finish();

                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });*/

        }

    /*changing the title of the toolbar according to frags*/
    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }


}
