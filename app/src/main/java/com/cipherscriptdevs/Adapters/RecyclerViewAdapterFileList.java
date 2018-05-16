package com.cipherscriptdevs.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cipherscriptdevs.thedocxreader.DisplayDocumentActivity;
import com.cipherscriptdevs.thedocxreader.R;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapterFileList extends RecyclerView.Adapter<RecyclerViewAdapterFileList.MyViewHolder> {
    private ArrayList<File> allFilePaths;
    private int imagePath;
    private Context context;
    private String extension;
    public RecyclerViewAdapterFileList(ArrayList<File> filePaths , int _imagePath , Context _context , String _extension){
        this.allFilePaths = filePaths;
        this.imagePath = _imagePath;
        this.context = _context;
        this.extension = _extension;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvName.setText(allFilePaths.get(position).getName());
        holder.ivImage.setImageResource(imagePath);
    }

    @Override
    public int getItemCount() {
        if (allFilePaths != null){
            return allFilePaths.size();
        }else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName;
        private ImageView ivImage;
        private LinearLayout container;
        MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.text_cardView);
            ivImage = itemView.findViewById(R.id.image_cardView);
            container = itemView.findViewById(R.id.container);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DisplayDocumentActivity.class);
            intent.putExtra("selectedPosition",getAdapterPosition());
            switch (extension){
                case "docx" : intent.putExtra("fileType","docx");
                    break;
                case "pdf" : intent.putExtra("fileType","pdf");
                    break;
                case "pptx" : intent.putExtra("fileType","pptx");
                    break;
            }
            context.startActivity(intent);
        }
    }
}
