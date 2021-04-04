package com.example.universities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class MainFragment extends Fragment implements View.OnClickListener{
    View view;
    RecyclerView recyclerView;
    UniversityAdapter universityAdapter;
    private MainFragment mainFragment = this;
    RetrofitProcess retrofit;
    Map<String, List<String>> university_faculties;
    List<String> universities;
    Map<String, String> images;
    Constants constants;
    SharedPreferences shared;
    String res1, res2, res3, res4, res5;

    public MainFragment() {
        university_faculties = new HashMap<>();
        universities = new ArrayList<>();
        images = new HashMap<>();
        constants = new Constants();
        this.images = constants.IMAGES;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);


        retrofit = new RetrofitProcess();

        shared = getActivity().getSharedPreferences("INFORMATION",
                Context.MODE_PRIVATE);

        HashMap<String, List<String>> subjects = new HashMap<>();
        List<String> requested_subjects = new ArrayList<>();
        HashSet<String> sub = new HashSet<>();

        sub.add(shared.getString("SUB1", ""));
        sub.add(shared.getString("SUB2", ""));
        sub.add(shared.getString("SUB3", ""));
        sub.add(shared.getString("SUB4", ""));
        sub.add(shared.getString("SUB5", ""));

        List<String> default_subjects = new ArrayList<>();
        default_subjects.add("Математика");
        default_subjects.add("Русский язык");
        default_subjects.add("Физика");
        default_subjects.add("Информатика");
        default_subjects.add("Биология");
        default_subjects.add("Химия");
        default_subjects.add("Обществознание");
        default_subjects.add("История");
        default_subjects.add("Иностранный язык");
        default_subjects.add("Литература");
        default_subjects.add("География");
        default_subjects.add("Вступительные");

        for (String s: default_subjects){
            if (sub.contains(s)){
                requested_subjects.add(s);
            }
        }
        subjects.put("subjects", requested_subjects);
        for (String el: requested_subjects){
            Log.e("TRRRRy", el);
        }

        retrofit.getFaculties(subjects, new RetrofitProcess.facultiesCallbacks() {
            @Override
            public void onGetFaculties(Map<String, List<String>> unis) {
                university_faculties = unis;
                universities.addAll(unis.keySet());
                universityAdapter = new UniversityAdapter(getContext(), mainFragment,
                        university_faculties, universities, images);

                recyclerView = view.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(universityAdapter);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
    }
}