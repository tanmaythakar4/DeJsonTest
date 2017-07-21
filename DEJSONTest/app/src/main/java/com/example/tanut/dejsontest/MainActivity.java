package com.example.tanut.dejsontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.tanut.dejsontest.adapter.BookAdapter;
import com.example.tanut.dejsontest.backend.Downloader;
import com.example.tanut.dejsontest.beans.BookBean;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView books_list;
    ArrayList<BookBean> my_books = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        books_list = (ListView) findViewById(R.id.book_list);
        Downloader downloader = new Downloader(this);
        downloader.execute();
    }


    public void callAdapter(ArrayList<BookBean> books){

        BookAdapter adapter = new BookAdapter(this,books);
        books_list.setAdapter(adapter);
    }

}
