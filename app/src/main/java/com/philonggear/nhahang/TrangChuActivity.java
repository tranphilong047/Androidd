package com.philonggear.nhahang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import FragmentAPP.BanAn;
import FragmentAPP.CaiDat;
import FragmentAPP.HoaDon;
import FragmentAPP.NhanVien;
import FragmentAPP.ThucDon;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView txtSODT;
    FragmentManager fragmentManager;

    @SuppressLint("RestrictedApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trang_chu);
        AnhXa();

        View view = navigationView.inflateHeaderView(R.layout.header_layout_menu);
        txtSODT = (TextView) view.findViewById(R.id.SoDT);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.mo,R.string.dong){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //màu mặc định icon
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        //gửi intent có dữ liệu
        Intent intent = getIntent();
        String SODT = intent.getStringExtra("SODT");
        txtSODT.setText(SODT);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction tranHienThiBan = fragmentManager.beginTransaction();
        BanAn banAn = new BanAn();
        tranHienThiBan.replace(R.id.content,banAn);
        tranHienThiBan.commit();
    }
    private void AnhXa(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_trangchu);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        txtSODT = (TextView) findViewById(R.id.SoDT);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menutrangchu:
                FragmentTransaction tranHienThiBan = fragmentManager.beginTransaction();
                BanAn banAn = new BanAn();
                tranHienThiBan.setCustomAnimations(R.anim.hieu_ung_chuyen_activity,R.anim.hieu_ung_an_ban_an);
                tranHienThiBan.replace(R.id.content,banAn);
                tranHienThiBan.commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.menuthucdon:
                FragmentTransaction tranHienThiThucDon = fragmentManager.beginTransaction();
                ThucDon thucDon = new ThucDon();
                tranHienThiThucDon.setCustomAnimations(R.anim.hieu_ung_chuyen_activity,R.anim.hieu_ung_an_ban_an);
                tranHienThiThucDon.replace(R.id.content,thucDon);
                tranHienThiThucDon.commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.menunhanvien:
                FragmentTransaction tranHienThiNhanVien = fragmentManager.beginTransaction();
                NhanVien nhanVien = new NhanVien();
                tranHienThiNhanVien.setCustomAnimations(R.anim.hieu_ung_chuyen_activity,R.anim.hieu_ung_an_ban_an);
                tranHienThiNhanVien.replace(R.id.content,nhanVien);
                tranHienThiNhanVien.commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.menuthongke:
                FragmentTransaction tranHienThiHoaDon = fragmentManager.beginTransaction();
                HoaDon hoaDon = new HoaDon();
                tranHienThiHoaDon.setCustomAnimations(R.anim.hieu_ung_chuyen_activity,R.anim.hieu_ung_an_ban_an);
                tranHienThiHoaDon.replace(R.id.content,hoaDon);
                tranHienThiHoaDon.commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.menucaidat:
                FragmentTransaction tranHienThiCaiDat = fragmentManager.beginTransaction();
                CaiDat caiDat = new CaiDat();
                tranHienThiCaiDat.setCustomAnimations(R.anim.hieu_ung_chuyen_activity,R.anim.hieu_ung_an_ban_an);
                tranHienThiCaiDat.replace(R.id.content,caiDat);
                tranHienThiCaiDat.commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;

        }
        return false;
    }
}
