package com.company.appbrkr.tourbuddy.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.appbrkr.tourbuddy.R;

/**
 * Created by Safkat on 6/23/2017.
 */

public class GalleryFragment extends Fragment {

    public GalleryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.gallery_frag,container, false);

        return rootView;


    }

}
