package com.example.universities;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MoreDialog extends DialogFragment implements View.OnClickListener{
    MainFragment mainFragment;
    View view;
    TextView tv;
    String name;
    List<String> faculties;
    Button btnLess;

    MoreDialog(MainFragment mainFragment, String name, List<String> faculties){
        this.mainFragment = mainFragment;
        this.name = name;
        this.faculties = new ArrayList<>();
        this.faculties = faculties;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.more_dialog,
                new LinearLayout(getActivity()), false);
        btnLess = view.findViewById(R.id.btnLess);
        btnLess.setOnClickListener(this);
        tv = view.findViewById(R.id.tv1);
        tv.setText(this.name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mainFragment.getActivity(),
                R.layout.listview_item, this.faculties);
        Log.e("DIALOG", this.faculties.get(0));
        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.
                graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        return builder;
    }

    @Override
    public void onClick(View v) {
        onDismiss(getDialog());
    }
}
