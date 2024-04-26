package com.example.tugas_praktek;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList Book_title, Book_publisher, Book_Page, Book_year;
    int position;

    CustomAdapter(Context context, ArrayList Book_title, ArrayList Book_publisher, ArrayList Book_page, ArrayList Book_year) {
        this.context = context;
        this.Book_title = Book_title;
        this.Book_publisher = Book_publisher;
        this.Book_Page = Book_page;
        this.Book_year = Book_year;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        this.position = position;

        holder.book_title_txt.setText(String.valueOf(Book_title.get(position)));
        holder.book_publisher_txt.setText(String.valueOf(Book_publisher.get(position)));
        holder.book_page_txt.setText(String.valueOf(Book_Page.get(position)));
        holder.book_year_txt.setText(String.valueOf(Book_year.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, UpdateActivity.class);
                intent.putExtra("title", String.valueOf(Book_title.get(position)));
                intent.putExtra("publisher", String.valueOf(Book_publisher.get(position)));
                intent.putExtra("page", String.valueOf(Book_Page.get(position)));
                intent.putExtra("year", String.valueOf(Book_year.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return Book_title.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView book_title_txt, book_year_txt, book_publisher_txt, book_page_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            book_title_txt = itemView.findViewById(R.id.title_txt);
            book_publisher_txt = itemView.findViewById(R.id.Publish_txt);
            book_page_txt = itemView.findViewById(R.id.Page_txt);
            book_year_txt = itemView.findViewById(R.id.Year_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
