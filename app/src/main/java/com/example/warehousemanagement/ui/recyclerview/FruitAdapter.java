package com.example.warehousemanagement.ui.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanagement.R;
import com.example.warehousemanagement.RoomDataBase.DataBase;
import com.example.warehousemanagement.RoomDataBase.MyData;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    FruitAdapter myAdapter;
    private List<Fruit> mFruitList;
    private List<MyData> myData;
    private Activity activity;
    private AdapterView.OnItemClickListener onItemClickListener;
    public FruitAdapter(Activity activity, List<MyData> myData) {
        this.activity = activity;
        this.myData = myData;
    }
    /**建立對外接口*/
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;
        EditText fruitedHobby;
        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
            fruitedHobby = view.findViewById(R.id.editText_Hobby);
        }

    }

    /**DB更新資料*/
    public void refreshView() {
        new Thread(()->{
            //this.myData = DataBase.getInstance(activity).getDataUao().displayAll();
            activity.runOnUiThread(() -> {
                notifyDataSetChanged();
            });
        }).start();
    }
    /**DB刪除資料*/
    public void deleteData(int position){
        new Thread(()->{
           // DataBase.getInstance(activity).getDataUao().deleteData(myData.get(position).getId());
            activity.runOnUiThread(()->{
                notifyItemRemoved(position);
                refreshView();
            });
        }).start();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(view.getContext(), "你點擊了view" + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(view.getContext(), "你點擊了圖片"+ fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
        holder.fruitedHobby.setText(fruit.getName());
        holder.fruitView.setOnClickListener((v)->{
           // onItemClickListener.onItemClick(myData.get(position));
        });
        //onItemClickListener.onItemClick(myData.get(position));
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
//
//    public FruitAdapter(List<Fruit> fruitList) {
//        mFruitList = fruitList;
//    }
//
    /**建立對外接口*/
    public interface OnItemClickListener {
        void onItemClick(MyData myData);
    }
    /**設置RecyclerView的左滑刪除行為*/
    private void setRecyclerFunction(RecyclerView recyclerView){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {//設置RecyclerView手勢功能
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                switch (direction){
                    case ItemTouchHelper.LEFT:
                    case ItemTouchHelper.RIGHT:
                        myAdapter.deleteData(position);
                        break;

                }
            }
        });
        helper.attachToRecyclerView(recyclerView);
    }

}
