package com.philonggear.nhahang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import DAO.NhanVienDAO;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {

    EditText extSDT,extMATKHAU;
    Button btnDangNhap,btnDangKy;
    NhanVienDAO nhanVienDAO;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_dang_nhap);
        AnhXa();

        btnDangNhap.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);
        nhanVienDAO = new NhanVienDAO(this);

        btnDangKy.setVisibility(View.GONE);
        HienThibtn();
    }
    private void AnhXa(){
        extMATKHAU = findViewById(R.id.editmatkhau);
        extSDT = findViewById(R.id.editsdt);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDangNhap:
                DangNhap();
                break;
            case R.id.btnDangKy:
                Dangky();
                break;
        }
    }
    private void DangNhap(){
        String sSODIENTHOAI = extSDT.getText().toString();
        String sMATKHAU = extMATKHAU.getText().toString();
        int kiemtra = nhanVienDAO.KiemTraDangNhap(sSODIENTHOAI,sMATKHAU);
        if (kiemtra !=0){
            Intent intent = new Intent(DangNhapActivity.this,TrangChuActivity.class);
            intent.putExtra("SODT",extSDT.getText().toString());
            intent.putExtra("MANHANVIEN",kiemtra);
            startActivity(intent);
            overridePendingTransition(R.anim.hieu_ung_chuyen_activity,R.anim.hieu_ung_an_ban_an);
        }else{
            Toast.makeText(DangNhapActivity.this,getResources().getString(R.string.DNkhongthanhcong),Toast.LENGTH_LONG).show();
        }
    }
    private void Dangky(){
        Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
        startActivity(intent);

    }
    private void HienThibtn(){
        boolean kiemtra = nhanVienDAO.KiemTraTaiKhoan();
        if (kiemtra){
            btnDangNhap.setVisibility(View.VISIBLE);
            btnDangKy.setVisibility(View.VISIBLE);
        }else {
            btnDangNhap.setVisibility(View.GONE);
            btnDangKy.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        HienThibtn();
    }
}
