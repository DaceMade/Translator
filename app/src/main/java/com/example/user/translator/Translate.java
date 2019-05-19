package com.example.user.translator;

import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translate {
    private final static String KEY = "trnsl.1.1.20190206T154559Z.258d51a8f7a70874.7867fce499f3f1d1e94f7fdb2800632b04ec896f";
    private final int FIRST_BRACKET_POSITION = 34;


    private String firstLang;
    private String secondLang;
    private String text;

    public Translate(String firstLang, String secondLang, String text) {
        this.firstLang = firstLang;
        this.secondLang = secondLang;
        this.text = text;
    }

    public Translate(String text) {
        this("en", "ru", text);
    }

    public void getTranslate(CallBack callBack) throws Exception {

        text = URLEncoder.encode(text, "utf-8");
        String urlTranslate = "https://translate.yandex.net/api/v1.5/tr.json/translate?lang=" + firstLang + "-" + secondLang + "&key=" + KEY + "&text=" + text;
        get(urlTranslate, (response) -> {
            StringBuilder translation = new StringBuilder();

            for (int i = FIRST_BRACKET_POSITION + 2; i < response.length() - 3; i++) {
                translation.append(response.charAt(i));
            }

            callBack.onResult(translation.toString());
        });
    }


    private void get(String url, CallBack callBack) {
        new Thread(() -> {
            try {
                URL obj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                callBack.onResult(response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
