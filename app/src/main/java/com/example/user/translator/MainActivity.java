package com.example.user.translator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY = "trnsl.1.1.20190206T154559Z.258d51a8f7a70874.7867fce499f3f1d1e94f7fdb2800632b04ec896f";

    public Button firstLang;
    public Button secondLang;
    public Button switchButton;
    public EditText inputText;

    static String lastText;
    static String currLang1 = "en";
    static String currLang2 = "ru";

    static boolean isOnFirstClick = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchButton = (Button) findViewById(R.id.switchButton);
        firstLang = (Button) findViewById(R.id.firstLang);
        secondLang = (Button) findViewById(R.id.secondLang);
        inputText = (EditText) findViewById(R.id.inputText);

        firstLang.setText(currLang1);
        secondLang.setText(currLang2);
        inputText.setText(lastText);
    }

    public void onTranslateClick(View view) throws Exception {
        new Translate(firstLang.getText().toString(), secondLang.getText().toString(), inputText.getText().toString()).getTranslate((translate) -> {
            TextView outputText = (TextView) findViewById(R.id.outputText);
            runOnUiThread(() -> {
                outputText.setText(translate);
            });
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.firstLang:
                lastText = inputText.getText().toString();
                isOnFirstClick = true;
                Intent intent = new Intent(this, Languages.class);
                startActivity(intent);
                break;
            case R.id.secondLang:
                lastText = inputText.getText().toString();
                isOnFirstClick = false;
                intent = new Intent(this, Languages.class);
                startActivity(intent);
                break;
            case R.id.switchButton:
                String copy = currLang1;
                currLang1 = currLang2;
                currLang2 = copy;
                firstLang.setText(currLang1);
                secondLang.setText(currLang2);
                break;
        }
    }
}
