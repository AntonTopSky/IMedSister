package com.bars.topilskiyanton.imedsister.Wards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.bars.topilskiyanton.imedsister.Patient.AddPatient;
import com.bars.topilskiyanton.imedsister.R;

/**
 * Created by Topilskiy Anton on 21.02.2018.
 */

public class Ward extends AppCompatActivity{
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ward);
        Intent intent = getIntent();
        setTitle("Палата № " + intent.getStringExtra("numberWard"));
        buildListPatient(this);
    }

    public void buildListPatient(AppCompatActivity mainActivity){
        listView = mainActivity.findViewById(R.id.list_patient);
        ListPatientAdapter adapter = new ListPatientAdapter(mainActivity);
        listView.setAdapter(adapter);
        for (int i = 1; i < 8; i++) {
            //adapter.add(new ItemPatient());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add: {
                Intent add_intent = new Intent(Ward.this, AddPatient.class);
                //add_intent.putExtra("numberWard", String.valueOf(listView.getAdapter().getCount() + 1));
                add_intent.putExtra("action", "add");
                startActivity(add_intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
