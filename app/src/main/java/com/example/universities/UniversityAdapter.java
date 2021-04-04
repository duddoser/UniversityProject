package com.example.universities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.ViewHolder> {
    Context context;
    List<String> universities_names, facs, facs1;
    Map<String, List<String>> universities;
    Map<String, String> images;
    MainFragment mainFragment;
    LayoutInflater layoutInflater;
    Constants constants;
    Map<String, String> shortnames;

    public UniversityAdapter(@NonNull Context context, MainFragment mainFragment,
                             Map<String, List<String>> universities,
                             List<String> universities_names, Map<String, String> images){
        this.context = context;
        this.universities = universities;
        this.mainFragment = mainFragment;
        this.layoutInflater = LayoutInflater.from(context);
        this.universities_names = universities_names;
        this.images = images;
        constants = new Constants();
        shortnames = constants.SHORTNAMES;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = this.universities_names.get(position);
        Log.e("NAMES", name);
        holder.textName.setText(shortnames.get(name));
        facs = new ArrayList<>();
        facs = this.universities.get(name);
        facs1 = new ArrayList<>();
        if (facs.size() > 3){
            for (int i = 0; i < 3; i++){
                facs1.add(facs.get(i));
            }
        } else {
            facs1 = facs;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mainFragment.getActivity(),
                R.layout.listview_item, facs1);
        holder.textInfo.setAdapter(adapter);
        Picasso.get().load(images.get(name)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.universities_names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView textName;
        ListView textInfo;
        ImageView imageView;
        Button btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.name);
            textInfo = itemView.findViewById(R.id.information);
            imageView = itemView.findViewById(R.id.image);
            btnMore = itemView.findViewById(R.id.btnMore);
            btnMore.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String u_name = textName.getText().toString();
            for (String key: shortnames.keySet()){
                if (shortnames.get(key).equals(u_name)){
                    DialogFragment fragment = new MoreDialog(mainFragment, key,
                            universities.get(key));
                    fragment.show(mainFragment.getFragmentManager(), "new_more");
                }
            }
        }
    }
}
