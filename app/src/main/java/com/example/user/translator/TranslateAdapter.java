package com.example.user.translator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TranslateAdapter extends RecyclerView.Adapter<TranslateAdapter.TranslateViewHolder>{

    public List<TranslateData> dataList;

    public TranslateAdapter (List<TranslateData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public TranslateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutId = R.layout.translate_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(layoutId, viewGroup, false);

        return new TranslateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TranslateViewHolder translateViewHolder, int i) {
        TranslateData translateData = dataList.get(i);
        translateViewHolder.bind(translateData);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class TranslateViewHolder extends RecyclerView.ViewHolder {

        TextView languagesView;
        TextView startTextView;
        TextView translateView;

        public TranslateViewHolder(@NonNull View itemView) {
            super(itemView);

            languagesView = itemView.findViewById(R.id.tvLanguages);
            startTextView = itemView.findViewById(R.id.tvStartText);
            translateView = itemView.findViewById(R.id.tvTranslate);
        }

        void bind(TranslateData data) {
            languagesView.setText(data.lang);
            startTextView.setText(data.text);
            translateView.setText(data.translatedText);
        }
    }
}
