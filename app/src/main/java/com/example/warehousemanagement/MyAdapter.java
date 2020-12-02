package com.example.warehousemanagement;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanagement.RoomDataBase.DataBase;
import com.example.warehousemanagement.RoomDataBase.MyData;
import com.example.warehousemanagement.ui.recyclerview.FruitAdapter;

import java.util.List;

 public class  MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> implements View.OnClickListener {
     private OnItemClickListener onItemclickListener;
     private final LayoutInflater mInflater;
     private RecyclerView recyclerView;
     private List<MyData> mWords; // Cached copy of words

     @Override
     public void onClick(View view) {
         // 根據 RecyclerView 獲得當前 view 的位置
         int position = recyclerView.getChildAdapterPosition(view) ;
         // 程式執行到這，會去執行具體實現的 onItemclick() 方法
         if (onItemclickListener!=null){
         onItemclickListener.onItemclick(recyclerView, view, position, mWords.get(position).getName());
         }
     }
    /**
     * 定義 RecyclerView 項目被點擊事件的回調接口
     * */
     public interface OnItemClickListener {
     // 參數 (父組件,當前點擊的view,點擊的view的位置,資料)
     void onItemclick(RecyclerView parent, View view, int position, String data);
    }
    /**
     *  在 RecyclerView 的 Adapter 裡聲明該接口，並提供 setter 方法
     * */
     public void setOnItemclickListener (OnItemClickListener onItemclickListener){
        this.onItemclickListener = onItemclickListener;
     }
     /**
      * 將 RecyclerView 附加到 Adapter
      * */
     @Override
     public void onAttachedToRecyclerView(RecyclerView recyclerView) {
         super.onAttachedToRecyclerView(recyclerView) ;
         this.recyclerView=recyclerView;
     }

     public MyAdapter(Context context) {
         mInflater = LayoutInflater.from(context);
     }

     class MyAdapterViewHolder extends RecyclerView.ViewHolder {
         private final TextView wordItemView;
         RecyclerView mRecyclerView;
         private MyAdapterViewHolder(View itemView) {
             super(itemView);
             wordItemView = itemView.findViewById(R.id.fruit_name);
             mRecyclerView=itemView.findViewById(R.id.recycler_view);
         }
     }

     @Override
     public MyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View itemView = mInflater.inflate(R.layout.item_list, parent, false);
         itemView.setOnClickListener(this);
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


