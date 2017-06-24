package com.company.appbrkr.tourbuddy;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private Fragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_frag);
      /*  FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);*/

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,new login_frag(),"HOME_FRAG").commit();
        }

    }




    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        frag=getSupportFragmentManager().findFragmentByTag("HOME_FRAG");
        if(frag != null && frag.isVisible()) {

            super.onBackPressed();
            this.finish();

        }

        if (count == 0 && frag ==null ) {


            frag=getSupportFragmentManager().findFragmentByTag("REGISTER_FRAG");
            if(frag != null && frag.isVisible()) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new login_frag(),"HOME_FRAG").commit();

            }
            frag=getSupportFragmentManager().findFragmentByTag("HOME_FRAG");
            if(frag != null && frag.isVisible()) {
                super.onBackPressed();
                this.finish();
            }

        }



    }

}
