package com.example.duanmau1.admin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.duanmau1.R;
import com.example.duanmau1.admin.fragment.HomeAdminFragment;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initView();
        setView();
    }

    private void initView() {
        toolbar = findViewById(R.id.tb_adminAct);
        frameLayout = findViewById(R.id.frLayout_adminAct);
        navigationView = findViewById(R.id.drawerNav_adminAct);
        drawerLayout =findViewById(R.id.drawerLayout_adminAct);
    }

    private void setView() {
        //set ToolBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_24);

        //set first Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frLayout_adminAct, new HomeAdminFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


}