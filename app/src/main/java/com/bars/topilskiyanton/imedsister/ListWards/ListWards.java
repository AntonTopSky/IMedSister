package com.bars.topilskiyanton.imedsister.ListWards;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.bars.topilskiyanton.imedsister.DataBase.DB;
import com.bars.topilskiyanton.imedsister.DataBase.OpWithDB;
import com.bars.topilskiyanton.imedsister.R;

import java.util.List;

/**
 * Created by Topilskiy Anton on 20.02.2018.
 */

public class ListWards {

    ListView listView;
    AppCompatActivity mainActivity;
    ListWardsAdapter adapter;
    private DB db;
    private String numberWard;
    private String quanity;
    private String gender;
    private String capacity;
    String LOG_TAG = "myLogs";
    OpWithDB opWithDB;

    public ListWards(AppCompatActivity mainActivity) {
        this.mainActivity = mainActivity;
        listView = mainActivity.findViewById(R.id.wards);
        adapter = new ListWardsAdapter(mainActivity);
        listView.setAdapter(adapter);
        opWithDB = OpWithDB.getInstance(mainActivity);
    }


    public void buildListWards() {
        List<Object> listObject = opWithDB.getAllFieldFromTable("Wards", "number");
        for (int i = 0; i < listObject.size(); i++) {
            adapter.add((ItemWard) listObject.get(i));
        }
    }

    public void remove(String numberWard) {
        opWithDB.removeField("Wards", "number", numberWard);
        refresh();
    }


    public void refresh() {
        adapter.clear();
        buildListWards();
    }
}
