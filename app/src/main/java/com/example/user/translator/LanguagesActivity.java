package com.example.user.translator;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LanguagesActivity extends ListActivity {

    final static String[] LANGUAGES = new String[]{
            "Russian", "English", "French", "German", "Spanish",
            "Azerbaijani", "Malayalam", "Albanian", "Maltese", "Amharic",
            "Macedonian", "Maori", "Arabic", "Marathi", "Armenian", "Mari",
            "Afrikaans", "Mongolian", "Basque", "Bashkir", "Nepali",
            "Belarusian", "Norwegian", "Bengali", "Punjabi",
            "Burmese", "Papiamento", "Bulgarian", "Persian",
            "Bosnian", "Polish", "Welsh", "Portuguese", "Hungarian",
            "Romanian", "Vietnamese", "Haitian", "Sebouan", "Galician",
            "Serbian", "Dutch", "Sinhala", "Gornomariysky", "Slovak",
            "Greek", "Slovenian", "Georgian", "Swahili", "Gujarati",
            "Sundanese", "Danish", "Tajik", "Hebrew", "Thai",
            "Yiddish", "Tagalog", "Indonesian", "Tamil", "Irish", "Tatar",
            "Italian", "Telugu", "Icelandic", "Turkish", "Udmurt",
            "Kazakh", "Uzbek", "Kannada", "Ukrainian", "Catalan", "Urdu",
            "Kyrgyz", "Finnish", "Chinese", "Korean", "Hindi",
            "Croatian", "Cosa", "Khmer", "Czech", "Lao", "Swedish",
            "Latin", "Scottish", "Latvian", "Estonian", "Lithuanian", "Esperanto",
            "Luxembourgish", "Javanese", "Malagasy", "Japanese", "Malay"
    };

    final static String[] LANGUAGES_CODE = new String[]{
            "ru", "en", "fr", "de", "es",
            "az", "ml", "sq", "mt", "am",
            "mk", "mi", "ar", "mr", "hy", "mhr",
            "af", "mn", "eu", "ba", "ne",
            "be", "no", "bn", "pa",
            "my", "pap", "bg", "fa",
            "bs", "pl", "cy", "pt", "hu",
            "ro", "vi", "ht", "ceb", "gl",
            "sr", "nl", "si", "mrj", "sk",
            "el", "sl", "ka", "sw", "gu",
            "su", "da", "tj", "he", "th",
            "yi", "tl", "id", "ta", "ga", "tt",
            "it", "te", "is", "tr", "udm",
            "kk", "uz", "kn", "uk", "ca", "ur",
            "ky", "fi", "zh", "ko", "hi",
            "hr", "xh", "km", "cs", "lo", "sw",
            "la", "gd", "lv", "et", "lt", "eo",
            "lb", "jv", "mg", "ja", "ms"
    };

    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, LANGUAGES);
        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, MainActivity.class);
        if (MainActivity.isOnFirstClick) {
            MainActivity.currLang1 = LANGUAGES_CODE[position];
        } else {
            MainActivity.currLang2 = LANGUAGES_CODE[position];
        }
        startActivity(intent);
    }
}
