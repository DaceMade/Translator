package com.example.user.translator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String KEY = "trnsl.1.1.20190206T154559Z.258d51a8f7a70874.7867fce499f3f1d1e94f7fdb2800632b04ec896f";

    public Button firstLang;
    public Button secondLang;
    public Button switchButton;
    public EditText inputText;

    static String lastText;
    static int idCount;
    static String currLang1 = "en";
    static String currLang2 = "ru";
    static boolean isOnFirstClick = false;

    AppDatabase db = App.getInstance().getDatabase();
    TranslateDataDao dao = db.translateDataDao();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchButton = findViewById(R.id.switchButton);
        firstLang = findViewById(R.id.firstLang);
        secondLang = findViewById(R.id.secondLang);
        inputText = findViewById(R.id.inputText);

        firstLang.setText(currLang1);
        secondLang.setText(currLang2);
        inputText.setText(lastText);

        firstLang.setOnClickListener(v -> startLanguagesActivity(true));

        secondLang.setOnClickListener(v -> startLanguagesActivity(false));

        switchButton.setOnClickListener(v -> {
            String copy = currLang1;
            currLang1 = currLang2;
            currLang2 = copy;
            firstLang.setText(currLang1);
            secondLang.setText(currLang2);
        });
    }

    private void startLanguagesActivity(boolean bFlag) {
        lastText = inputText.getText().toString();
        isOnFirstClick = bFlag;
        Intent intent = new Intent(this, LanguagesActivity.class);
        startActivity(intent);
    }

    public void onTranslateClick(View view) throws Exception {
        String firstLangString = firstLang.getText().toString();
        String secondLangString = secondLang.getText().toString();
        String inputTextString = inputText.getText().toString();

        new Translate(firstLangString, secondLangString, inputTextString).getTranslate((translate) -> {
            TextView outputText = findViewById(R.id.outputText);
            runOnUiThread(() -> {
                outputText.setText(translate);
            });
            idCount = dao.getAll().size();
            dao.insert(new TranslateData(idCount, firstLangString + "-" + secondLangString, inputTextString, outputText.getText().toString()));
        });


    }
}