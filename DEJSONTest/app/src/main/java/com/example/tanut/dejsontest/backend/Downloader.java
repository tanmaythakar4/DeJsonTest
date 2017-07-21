package com.example.tanut.dejsontest.backend;

import android.os.AsyncTask;

import com.example.tanut.dejsontest.MainActivity;
import com.example.tanut.dejsontest.beans.BookBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by tanut on 7/21/2017.
 */

public class Downloader extends AsyncTask<String,Integer,ArrayList> {
   MainActivity context;
     final String BOOKURL = "http://de-coding-test.s3.amazonaws.com/books.json";

    public Downloader(MainActivity activity){
        this.context = activity;
    }

    @Override
    protected ArrayList doInBackground(String... params) {
        ArrayList<BookBean> resultList = new ArrayList<BookBean>();
        try {
            URL book_url = new URL(BOOKURL);
            InputStream is = new BufferedInputStream(book_url.openConnection().getInputStream());
            String json = convertStreamToString(is);
            JSONArray bookArray = new JSONArray(json);
            for (int i=0;i<bookArray.length();i++){
                JSONObject book = bookArray.getJSONObject(i);
                BookBean bean = new BookBean();
                bean.title = book.getString("title");
                if(book.has("imageURL")){
                    bean.imageUrl =  book.getString("imageURL");
                }
                if(book.has("author")){
                    bean.author =  book.getString("author");
                }

                resultList.add(bean);
                bean = null;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultList;
    }
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        this.context.callAdapter(arrayList);
    }
}
