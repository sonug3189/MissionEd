package com.missionedappdev.missoned.ui.home;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.missionedappdev.missoned.R;

public class dashboardFragment extends Fragment {

private dashboardViewModel dashboardViewModel;
public dashboardFragment(){}

@Override
public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {

        Toast.makeText(getActivity(),"profile", Toast.LENGTH_LONG).show();

        View view =  inflater.inflate(R.layout.activity_profile, container, false);

        /* dashboardViewModel =
                ViewModelProviders.of(this).get(dashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

        */
        return view;
        }
        }
