package com.example.warehousemanagement;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;
import androidx.fragment.app.Fragment;
import  androidx.fragment.app.FragmentManager;
import  androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.warehousemanagement.RoomDataBase.MyData;
import com.example.warehousemanagement.ui.dashboard.DashboardFragment;
import com.example.warehousemanagement.ui.home.HomeFragment;
import com.example.warehousemanagement.ui.notifications.NotificationsFragment;
import com.example.warehousemanagement.ui.recyclerview.FruitAdapter;
import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    //當前顯示的fragment
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private FragmentToDB mFragmentToDB;
   //
    //private List<Fragment> fragments = new ArrayList<>();
    private int currentIndex = 0;
    private NavController navController ;

    FruitAdapter fruitAdapter;
    MyData nowSelectedData;//取得在畫面上顯示中的資料內容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Stetho.initializeWithDefaults(this);//設置資料庫監視

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        FloatingActionButton fab=findViewById(R.id.fbtnAdd);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener()
        {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.navigation_home) {
                    //toolbar.setVisibility(View.GONE);
                    fab.show();
                    Toast.makeText(getBaseContext(),destination.getId()+"",Toast.LENGTH_SHORT).show();
                    //fab.setVisibility(View.GONE);
                } else {
                    //toolbar.setVisibility(View.VISIBLE);
                    Toast.makeText(getBaseContext(),destination.getId()+"",Toast.LENGTH_SHORT).show();
                    fab.hide();
                }
            }

        });
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        Stetho.initializeWithDefaults(this);
   }
    @Nullable

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //“內存重啟”時保存當前的fragment名字
        outState.putInt(CURRENT_FRAGMENT,currentIndex);
        super.onSaveInstanceState(outState);
    }

}