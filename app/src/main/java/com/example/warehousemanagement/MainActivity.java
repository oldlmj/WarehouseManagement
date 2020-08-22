package com.example.warehousemanagement;

import  androidx.fragment.app.FragmentManager;
import  androidx.fragment.app.FragmentTransaction;

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
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FruitAdapter fruitAdapter;
    MyData nowSelectedData;//取得在畫面上顯示中的資料內容
    FloatingActionButton fbtnAdd;
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
        fbtnAdd = (FloatingActionButton) findViewById(R.id.fbtnAdd);
        fbtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchButton(0);
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

    //定義方法填充Activity右側的fragment，並通過傳參修改文字內容
    public void switchButton(int data){


    //通過呼叫RightFragment中的getInstance方法傳修改文字
        FragmentToDB fragmentToDB =FragmentToDB.newInstance(data);
    //此時使用add方法會造成右側fragment中文字重疊（未設定BackGround時）
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment,fragmentToDB)
            .addToBackStack(null)
                .commit();

    }

}