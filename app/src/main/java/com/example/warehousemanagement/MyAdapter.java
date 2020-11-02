package com.example.warehousemanagement;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanagement.RoomDataBase.DataBase;
import com.example.warehousemanagement.RoomDataBase.MyData;

import java.util.List;

 public class  MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {

     class MyAdapterViewHolder extends RecyclerView.ViewHolder {
         private final TextView wordItemView;

         private MyAdapterViewHolder(View itemView) {
             super(itemView);
             wordItemView = itemView.findViewById(R.id.fruit_name);
         }
     }

     private final LayoutInflater mInflater;
     private List<MyData> mWords; // Cached copy of words

    public MyAdapter(Context context) {
         mInflater = LayoutInflater.from(context);
     }

     @Override
     public MyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View itemView = mInflater.inflate(R.layout.item_list, parent, false);
         return new MyAdapterViewHolder(itemView);
     }

     @Override
     public void onBindViewHolder(MyAdapterViewHolder holder, int position) {
         if (mWords != null) {
             MyData current = mWords.get(position);
             holder.wordItemView.setText(current.getName());
         } else {
             // Covers the case of data not being ready yet.
             holder.wordItemView.setText("No Word");
         }
     }

     public void setWords(List<MyData> words) {
         mWords = words;
         notifyDataSetChanged();
     }

     // getItemCount() is called many times, and when it is first called,
     // mWords has not been updated (means initially, it's null, and we can't return null).
     @Override
     public int getItemCount() {
         if (mWords != null)
             return mWords.size();
         else return 0;
     }
 }


