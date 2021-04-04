package com.example.universities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class StartFragment extends Fragment implements View.OnClickListener{
    View view;
    RetrofitProcess retrofit;
    Spinner spinner1, spinner2, spinner3, spinner4, spinner5;
    Button btn;
    SharedPreferences sharedPreferences;
    String subject1, subject2, subject3, subject4, subject5;

    public StartFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start, container, false);
        retrofit = new RetrofitProcess();
        initViews();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject1 = spinner1.getSelectedItem().toString();
                subject2 = spinner2.getSelectedItem().toString();
                subject3 = spinner3.getSelectedItem().toString();
                subject4 = spinner4.getSelectedItem().toString();
                subject5 = spinner5.getSelectedItem().toString();

                sharedPreferences = getActivity().getSharedPreferences("INFORMATION",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                HashSet<String> strings = new HashSet<>();
                strings.add(subject1);
                strings.add(subject2);
                strings.add(subject3);
                strings.add(subject4);
                strings.add(subject5);

                if (subject1.equals("Русский язык") || subject2.equals("Русский язык") ||
                        subject3.equals("Русский язык") || subject4.equals("Русский язык") ||
                        subject5.equals("Русский язык")) {
                    if (strings.size() <= 3){
                        Snackbar.make(view, "Выберите два необязательных предмета!",
                                BaseTransientBottomBar.LENGTH_LONG).show();
                    } else {

                    editor.putString("SUB1", subject1);
                    editor.putString("SUB2", subject2);
                    editor.putString("SUB3", subject3);
                    editor.putString("SUB4", subject4);
                    editor.putString("SUB5", subject5);
                    editor.apply();
                    Log.e("First", "OK");
                    ((NavigationHost) getActivity()).navigateTo(new MainFragment(), true);
                    }
                } else {
                    Snackbar.make(view, "Не забудьте: русский язык - обязательный предмет!",
                            BaseTransientBottomBar.LENGTH_LONG).show();
                }

            }
        });
        return view;
    }

    void initViews(){
        btn = view.findViewById(R.id.btn_continue);
        spinner1 = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);
        spinner3 = view.findViewById(R.id.spinner3);
        spinner4 = view.findViewById(R.id.spinner4);
        spinner5 = view.findViewById(R.id.spinner5);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.subject_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) { }
}