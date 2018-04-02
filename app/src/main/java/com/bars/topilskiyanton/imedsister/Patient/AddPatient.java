package com.bars.topilskiyanton.imedsister.Patient;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bars.topilskiyanton.imedsister.R;

/**
 * Created by Topilskiy Anton on 02.03.2018.
 */

public class AddPatient extends AppCompatActivity {

    EditText surname,
            name,
            secname,
            adress,
            dateBirth;

    TextView errorText;

    Button buttonOk,
            buttonCancel;

    RadioButton rb_genderMale,
            rb_genderFemale;
    Intent intent;

    Boolean flagError = false;
    String gender;

    boolean ff = true;
    TableLayout tableLayout;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        setTitle("Создание карточки пациента");

        intent = getIntent();
        surname = findViewById(R.id.surnamePatient);
        name = findViewById(R.id.namePatient);
        secname = findViewById(R.id.secnamePatient);
        adress = findViewById(R.id.adressPatient);
        rb_genderMale = findViewById(R.id.genderMale);
        rb_genderFemale = findViewById(R.id.genderFemale);
        dateBirth = findViewById(R.id.dateBirth);
        buttonOk = findViewById(R.id.buttonOk);
        buttonCancel = findViewById(R.id.buttonCancel);

        tableLayout = findViewById(R.id.dop_info);
        textView = findViewById(R.id.check_a_info);

        //обработчик ввода даты рождения
        dateBirth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                switch (editable.length()) {
                    case 2: {
                        editable.append(".");
                        break;
                    }
                    case 5: {
                        editable.append(".19");
                        break;
                    }
                }

            }
        });

        //Отмена
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("RESULT", "cancel");
                setResult(RESULT_OK, intent);
                finish();

            }
        });


        // Дополнительная информация
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tableLayout.getVisibility()== View.VISIBLE) {
                    tableLayout.setLayoutParams(new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT, 0));
                    tableLayout.setVisibility(View.INVISIBLE);
                    textView.setTextColor(getResources().getColor(R.color.colorBlue));
                } else {
                    tableLayout.setVisibility(View.VISIBLE);
                    tableLayout.setLayoutParams(new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    textView.setTextColor(getResources().getColor(R.color.colorRed));
                }
            }
        });

        //Добавление пациента
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkField()) {
                    gender = "female";
                    if (rb_genderMale.isChecked()) {
                        gender = "male";
                    }
                    ItemPatient patient = new ItemPatient(surname.getText().toString(),
                            name.getText().toString(),
                            secname.getText().toString(),
                            gender, dateBirth.getText().toString());
                }
            }
        });


    }

    // проверка
    private boolean checkField() {
        if (surname.getText().length() == 0) {
            errorMessage("Обязательное поле фамилия не заполнено!");
            return false;
        }
        if (name.getText().length() == 0) {
            errorMessage("Обязательное поле имя не заполнено!");
            return false;
        }
        if (secname.getText().length() == 0) {
            errorMessage("Обязательное поле отчество не заполнено!");
            return false;
        }
        if (dateBirth.getText().length() == 0) {
            errorMessage("Обязательное поле дата рождения не заполнено!");
            return false;
        }
        return true;
    }

    private void errorMessage(String message) {
        if (flagError) {
            errorText.setText(message);
        } else {
            TableLayout tableLayout = findViewById(R.id.tableLayout);
            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            errorText = new TextView(this);
            errorText.setLayoutParams(layoutParams);
            tableLayout.addView(errorText);
            flagError = true;
            errorText.setPadding(5, 5, 5, 5);
            errorText.setTextColor(Color.RED);
            errorText.setTextSize(18);
            errorText.setText(message);
        }
    }

    private void addField(ItemPatient itemPatient) {

    }


}

