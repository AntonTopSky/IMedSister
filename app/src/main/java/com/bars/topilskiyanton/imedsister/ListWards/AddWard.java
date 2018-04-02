package com.bars.topilskiyanton.imedsister.ListWards;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bars.topilskiyanton.imedsister.DataBase.OpWithDB;
import com.bars.topilskiyanton.imedsister.R;

/**
 * Created by Topilskiy Anton on 22.02.2018.
 */

public class AddWard extends Activity implements TextWatcher {
    EditText editText_numWard;
    EditText editText_capacity;
    TextView title;
    RadioButton radioButton_male;
    RadioButton radioButton_female;
    String gender;
    Button button_ok;
    Button button_cancel;
    Intent intent;
    String editNumberWard;
    String LOG_TAG = "myLogs";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ward);
        editText_numWard = findViewById(R.id.add_numWard);
        editText_capacity = findViewById(R.id.add_capacityWard);
        editText_numWard.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText_capacity.setInputType(InputType.TYPE_CLASS_NUMBER);
        radioButton_male = findViewById(R.id.add_gender_male);
        radioButton_female = findViewById(R.id.add_gender_female);
        title = findViewById(R.id.titleAddWard);
        button_ok = findViewById(R.id.saveWard);
        button_cancel = findViewById(R.id.cancel);
        editText_numWard.addTextChangedListener(this);
        editText_capacity.addTextChangedListener(this);
        intent = getIntent();

        button_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("RESULT", "cancel");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        switch (intent.getStringExtra("action")) {
            case "add": {
                add();
                break;
            }
            case "update": {
                update();
            }
        }
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    // Обработчик заполненности полей
    @Override
    public void afterTextChanged(Editable editable) {
        if (editText_capacity.length() != 0 && editText_numWard.length() != 0) {
            button_ok.setEnabled(true);
        } else {
            button_ok.setEnabled(false);
        }
    }


    // Добавление палаты
    private void add() {
        title.setText("Добавить палату");
        editText_numWard.setText(intent.getStringExtra("numberWard"));
        button_ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton_male.isChecked()) {
                    gender = "male";
                } else {
                    gender = "female";
                }

                OpWithDB opWithDB = OpWithDB.getInstance(AddWard.this);
                ContentValues contentValues = new ContentValues();
                contentValues.put("number", editText_numWard.getText().toString());
                contentValues.put("quanity", "0");
                contentValues.put("capacity", editText_capacity.getText().toString());
                contentValues.put("gender", gender);

                if (opWithDB.addField("Wards", contentValues)) {
                    intent.putExtra("RESULT", "OK");
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    errorInWindow("Палата с таким номером уже существует!");
                }
            }
        });
    }

    // Редактирование палаты
    private void update() {
        editNumberWard = intent.getStringExtra("numberWard");
        title.setText("Палата №" + editNumberWard);
        editText_numWard.setText(editNumberWard);
        editText_capacity.setText(intent.getStringExtra("capacity"));
        switch (intent.getStringExtra("gender")) {
            case "male": {
                radioButton_male.setChecked(true);

                break;
            }
            case "female": {
                radioButton_female.setChecked(true);
            }
        }
        button_ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton_male.isChecked()) {
                    gender = "male";
                } else {
                    gender = "female";
                }
                OpWithDB opWithDB = OpWithDB.getInstance(AddWard.this);
                ContentValues contentValues = new ContentValues();
                contentValues.put("number", editText_numWard.getText().toString());
                contentValues.put("quanity", "0");
                contentValues.put("capacity", editText_capacity.getText().toString());
                contentValues.put("gender", gender);

                if (opWithDB.updateField("Wards", contentValues, editNumberWard)) {
                     intent.putExtra("RESULT", "OK");
                     setResult(RESULT_OK, intent);
                     finish();
                } else {
                    errorInWindow("Палата с таким номером уже существует!");
                }
            }
        });

    }

    // Вывод ошибок
    private void errorInWindow(String error) {
        ((TextView) findViewById(R.id.error)).setText(error);
    }
}
