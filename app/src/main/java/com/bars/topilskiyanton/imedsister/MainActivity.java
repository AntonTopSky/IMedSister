package com.bars.topilskiyanton.imedsister;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ListView;

import com.bars.topilskiyanton.imedsister.AdditionalWindow.CheckWindow;
import com.bars.topilskiyanton.imedsister.ListWards.AddWard;
import com.bars.topilskiyanton.imedsister.ListWards.ItemWard;
import com.bars.topilskiyanton.imedsister.ListWards.ListWards;
import com.bars.topilskiyanton.imedsister.Wards.Ward;

public class MainActivity extends AppCompatActivity {

    ListWards listWards;
    ListView listView;
    String LOG_TAG = "myLogs";
    SharedPreferences sPref;
    final int REQUST_CODE_ADD_UPD_WARD = 1;
    final int REQUST_CODE_CHECK_WINDOW = 2;
    private static final int CM_DELETE_ID = 1;
    private static final int CM_UPDATE_ID = 2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listWards = new ListWards(this);
        listWards.buildListWards();
        listView = findViewById(R.id.wards);
        setTitle("Список палат");
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Ward.class);
                intent.putExtra("numberWard", String.valueOf(i + 1));
                startActivity(intent);
            }
        });
    }

    //Action Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Обработчик Action Bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add: {
                Intent add_intent = new Intent(MainActivity.this, AddWard.class);
                add_intent.putExtra("numberWard", String.valueOf(listView.getAdapter().getCount() + 1));
                add_intent.putExtra("action", "add");
                startActivityForResult(add_intent, REQUST_CODE_ADD_UPD_WARD);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // Контекстное меню
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить палату");
        menu.add(0, CM_UPDATE_ID, 0, "Редактировать палату");
    }

    // Обработчик контекстного меню
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //удаление записи
        if (item.getItemId() == CM_DELETE_ID) {
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
            ItemWard itemWard = (ItemWard) listView.getAdapter().getItem(acmi.position);
            Intent check_intent = new Intent(MainActivity.this, CheckWindow.class);
            check_intent.putExtra("message", "removeWard");
            check_intent.putExtra("numberWard", itemWard.getNumberWard());
            startActivityForResult(check_intent, REQUST_CODE_CHECK_WINDOW);
            //listWards.remove(itemWard.getNumberWard());
        }
        // редактирование записи
        if (item.getItemId() == CM_UPDATE_ID) {
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
            ItemWard itemWard = (ItemWard) listView.getAdapter().getItem(acmi.position);
            Intent upd_intent = new Intent(MainActivity.this, AddWard.class);
            upd_intent.putExtra("numberWard", itemWard.getNumberWard());
            upd_intent.putExtra("capacity", itemWard.getCapacity());
            upd_intent.putExtra("gender", itemWard.getGender());
            upd_intent.putExtra("action", "update");
            startActivityForResult(upd_intent, REQUST_CODE_ADD_UPD_WARD);

        }
        return super.onContextItemSelected(item);
    }

    // Обработчик возвращаемых значений от открытых окон
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUST_CODE_ADD_UPD_WARD: {
                    if (data.getStringExtra("RESULT").equals("OK")) {
                       listWards.refresh();
                    }
                    break;
                }
                case REQUST_CODE_CHECK_WINDOW: {
                    if (data.getStringExtra("RESULT").equals("OK")) {
                       listWards.remove(data.getStringExtra("numberWard"));
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
