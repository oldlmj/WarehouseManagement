package com.example.warehousemanagement.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanagement.FragmentToDB;
import com.example.warehousemanagement.MainActivity;
import com.example.warehousemanagement.MyAdapter;
import com.example.warehousemanagement.R;
import com.example.warehousemanagement.RoomDataBase.DataBase;
import com.example.warehousemanagement.RoomDataBase.MyData;
import com.example.warehousemanagement.ui.recyclerview.Fruit;
import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {
    private List<Fruit> fruitList = new ArrayList<>();
    private HomeViewModel homeViewModel;
    private RecyclerView mRcyclerView;
    private FloatingActionButton fab;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(getContext());//設置資料庫監視


    }
    @Override
    public void onStart() {
        super.onStart();

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.layout_recycler_view, container, false);
        MyAdapter myAdapter=new MyAdapter(getContext());
        mRcyclerView = root.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAdapter.setOnItemclickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemclick(RecyclerView parent, View view, int position, String data) {
                Toast.makeText(getActivity(),"這是第 "+data,Toast.LENGTH_LONG).show();
            }
        });
        mRcyclerView.setAdapter(myAdapter);
        mRcyclerView.setLayoutManager(layoutManager);
        mRcyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));//設置分隔線
        homeViewModel=new ViewModelProvider(this).get(HomeViewModel.class);


        homeViewModel.getAllWords().observe(getActivity(), new Observer<List<MyData>>() {
            @Override
            public void onChanged(List<MyData> mydata) {
                myAdapter.setWords(mydata);
                //資料改變刷新元件
                myAdapter.notifyDataSetChanged();
            }
        });
        fab =getActivity().findViewById(R.id.fbtnAdd);
        //FAB 點擊事件，跳轉新增資料列介面
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment
//                        .findNavController(HomeFragment.this)
//                        .navigate(R.id.action_navigation_home_to_navigation_fragmenttodB);
                Intent intent = new Intent(getActivity(), FragmentToDB.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                          }
        });


        return root;
    }
    private void initFruits() {
        for(int i = 0; i < 10; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.img1);
            fruitList.add(apple);
            Fruit banana = new Fruit("banana", R.drawable.img2);
            fruitList.add(banana);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            MyData word = new MyData(data.getStringExtra(FragmentToDB.EXTRA_REPLY));
            homeViewModel.insert(word);
        } else {
            Toast.makeText(
                    getActivity(),
                    "喀拉喀拉",
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }

}