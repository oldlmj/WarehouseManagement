package com.example.warehousemanagement.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanagement.R;
import com.example.warehousemanagement.ui.recyclerview.Fruit;
import com.example.warehousemanagement.ui.recyclerview.FruitAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private List<Fruit> fruitList = new ArrayList<>();
    private HomeViewModel homeViewModel;
    private RecyclerView mNewTitleRcyclerView;
    private ImageView mLastImageView;

    @Override
    public void onStart() {
        super.onStart();
        initFruits();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.layout_recycler_view, container, false);
       // final TextView textView = root.findViewById(R.id.text_home);
        mNewTitleRcyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNewTitleRcyclerView.setLayoutManager(layoutManager);

        FruitAdapter adapter = new FruitAdapter(fruitList);
        mNewTitleRcyclerView.setAdapter(adapter);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
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
}