package com.example.universities;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilterFragment extends DialogFragment implements View.OnClickListener{
    MainFragment mainFragment;
    View view;
    List<Spinner> spinners;
    Button btn;
    SharedPreferences sharedPreferences;

    FilterFragment(MainFragment mainFragment){
        this.mainFragment = mainFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.filter_fragment,
                new LinearLayout(getActivity()), false);

        spinners = new ArrayList<>();
        sharedPreferences = getActivity().getSharedPreferences("INFORMATION",
                Context.MODE_PRIVATE);
        btn = view.findViewById(R.id.btn_continue);
        btn.setOnClickListener(this);
        initSpinners();

        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.
                graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        return builder;
    }

    public void initSpinners(){
        spinners.add(view.findViewById(R.id.spinner1));
        spinners.add(view.findViewById(R.id.spinner2));
        spinners.add(view.findViewById(R.id.spinner3));
        spinners.add(view.findViewById(R.id.spinner4));
        spinners.add(view.findViewById(R.id.spinner5));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.subject_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (Integer i = 0; i < 5; i++){
            spinners.get(i).setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();

        for (Integer i = 0; i < 5; i++){
            editor.putString("SUB" + i.toString(), spinners.get(i).getSelectedItem().toString());
        }
        onDismiss(getDialog());
    }
}
