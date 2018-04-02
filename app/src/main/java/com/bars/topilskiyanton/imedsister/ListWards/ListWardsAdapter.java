package com.bars.topilskiyanton.imedsister.ListWards;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bars.topilskiyanton.imedsister.R;

/**
 * Created by Topilskiy Anton on 19.02.2018.
 */

public class ListWardsAdapter extends ArrayAdapter<ItemWard> {
    AppCompatActivity main;

    public ListWardsAdapter(AppCompatActivity main) {
        super(main, R.layout.activity_list_wards);
        this.main = main;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View view = main.getLayoutInflater().inflate(R.layout.activity_list_wards, null);
        ItemWard itemWard = getItem(position);
        ImageView imageView = view.findViewById(R.id.gender);
        ((TextView) view.findViewById(R.id.nameWard)).setText("Палата №"+itemWard.getNumberWard());
        ((TextView) view.findViewById(R.id.quanityPatients)).setText(itemWard.getQuanity()+"/"+itemWard.getCapacity());
        if (itemWard.getGender().equals("male")) {
            imageView.setImageResource(R.drawable.mars);
        } else {
            imageView.setImageResource(R.drawable.venus);
        }
        return view;
    }
}
