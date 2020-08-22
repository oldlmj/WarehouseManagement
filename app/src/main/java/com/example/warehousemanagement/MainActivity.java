package com.example.warehousemanagement;

import android.os.Bundle;
import android.view.View;

import com.example.warehousemanagement.RoomDataBase.MyData;
import com.example.warehousemanagement.ui.recyclerview.FruitAdapter;
import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    FruitAdapter fruitAdapter;
    MyData nowSelectedData;//取得在畫面上顯示中的資料內容
    FloatingActionButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Stetho.initializeWithDefaults(this);//設置資料庫監視
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        // 右下角那顆圓形新增
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar
                        .make(view, "是snackbar元件", Snackbar.LENGTH_LONG)
                        .setAction("好的", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 按了「好的」要做的事寫在這裡..
                            }
                        });
                snackbar.show(); //顯示snackbar
            }
        });

    }



}