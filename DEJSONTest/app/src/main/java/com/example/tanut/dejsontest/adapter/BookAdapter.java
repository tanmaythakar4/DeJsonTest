package com.example.tanut.dejsontest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanut.dejsontest.R;
import com.example.tanut.dejsontest.beans.BookBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by tanut on 7/21/2017.
 */

public class BookAdapter extends ArrayAdapter{

    ArrayList<BookBean> booksList;
    Context context;
    public BookAdapter(@NonNull Context context,  ArrayList<BookBean> resource) {
        super(context, R.layout.row_book,resource);
        this.booksList = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        BookViewHolder holder = null;
        if(row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_book,parent,false);
            holder = new BookViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder = (BookViewHolder) row.getTag();
        }

        BookBean book = booksList.get(position);
        holder.tv_title.setText(book.title);

        if(!book.author.equalsIgnoreCase("")){
            holder.tv_author.setVisibility(View.VISIBLE);
            holder.tv_author.setText("author: "+book.author);
        }
        else{
            holder.tv_author.setVisibility(View.GONE);
        }
        if(!book.imageUrl.equalsIgnoreCase("")){
            Picasso.with(context).load(book.imageUrl).resize(180, 250).
                    centerCrop().into(holder.iv_book);
        }
        return row;
    }

    class BookViewHolder {
        ImageView iv_book;
        TextView tv_title;
        TextView tv_author;

        BookViewHolder(View view){
            iv_book = (ImageView)view.findViewById(R.id.iv_book);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_author = (TextView)view.findViewById(R.id.tv_author);
        }
    }


}
