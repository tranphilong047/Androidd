package com.philonggear.nhahang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import DAO.BanAnDAO;

public class ThemBanAnActivity extends AppCompatActivity implements View.OnClickListener {

    EditText extTenBan;
    Button btnThemBA;
    BanAnDAO banAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_them_ban_an);
        AnhXa();

        btnThemBA.setOnClickListener(this);
        banAnDAO = new BanAnDAO(this);

    }
    private void AnhXa(){
        extTenBan = findViewById(R.id.editLoaiMonAn);
        btnThemBA = findViewById(R.id.btnThemLoaiMonAn);

    }

    @Override
    public void onClick(View v) {
        String sTenBan = extTenBan.getText().toString();
        if (sTenBan != null || sTenBan.equals("")){
            boolean kiemtra = banAnDAO.ThemBanAn(sTenBan);
            Intent intent = new Intent();
            intent.putExtra("ketquathem",kiemtra);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }else {
            Toast.makeText(this,getResources().getString(R.string.vuilongnhapdulieu),Toast.LENGTH_SHORT).show();
        }
    }
}
