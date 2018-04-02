package com.bars.topilskiyanton.imedsister.Wards;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bars.topilskiyanton.imedsister.Patient.ItemPatient;
import com.bars.topilskiyanton.imedsister.R;

/**
 * Created by Topilskiy Anton on 21.02.2018.
 */

public class ListPatientAdapter extends ArrayAdapter<ItemPatient> {
    AppCompatActivity main;

    public ListPatientAdapter(AppCompatActivity main) {
        super(main, R.layout.activity_mini_patient);
        this.main = main;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemPatient itemPatient = getItem(position);
       return main.getLayoutInflater().inflate(R.layout.activity_mini_patient, null);
    }

}
