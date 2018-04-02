package com.bars.topilskiyanton.imedsister.AdditionalWindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bars.topilskiyanton.imedsister.R;

/**
 * Created by Topilskiy Anton on 01.03.2018.
 */

public class CheckWindow extends Activity {

    TextView textView;
    Button button_yes;
    Button button_cancel;
    Intent intentParent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        textView = findViewById(R.id.title_check_window);
        button_yes = findViewById(R.id.check_ok);
        button_cancel = findViewById(R.id.check_cancel);
        intentParent = getIntent();
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentParent.putExtra("RESULT", "cancel");
                setResult(RESULT_OK, intentParent);
                finish();
            }
        });
        switch (intentParent.getStringExtra("message")){
            case "removeWard":{
                String checkMessage = "Вы уверены что хотите удалить Палату №"+intentParent.getStringExtra("numberWard");
                textView.setText(checkMessage);
                button_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intentParent.putExtra("RESULT", "OK");
                        intentParent.putExtra("numberWard", intentParent.getStringExtra("numberWard"));
                        setResult(RESULT_OK, intentParent);
                        finish();
                    }
                });
                break;
            }
        }
    }
}
