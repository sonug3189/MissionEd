package com.missionedappdev.missoned.ui.slideshow;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.missionedappdev.missoned.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;

    public SettingsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(),"payment option", Toast.LENGTH_LONG).show();

            View view =  inflater.inflate(R.layout.fragment_premium, container, false);


            //view.findViewById()
        /*settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_premium, container, false);
        final TextView textView = root.findViewById(R.id.text_settings);
        settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

         */
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final FragmentActivity activity=getActivity();
        View view=getView();
        if(activity==null || view==null) {
            Toast.makeText(getContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("Status:","We are in Payment page");
    }
}
