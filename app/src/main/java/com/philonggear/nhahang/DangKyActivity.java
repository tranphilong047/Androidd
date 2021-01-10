package com.philonggear.nhahang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import FragmentAPP.NhanVien;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtDangKy;
    EditText extSDT,extEMAIL,extMATKHAU,extHOTEN;
    Button btnDangKy,btnDangNhap;
    NhanVienDAO nhanVienDAO;
    int maNhanVien = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dang_ky);
        Anhxa();

        btnDangNhap.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);

        nhanVienDAO = new NhanVienDAO(this);

        maNhanVien = getIntent().getIntExtra("manhanvien",0);
        if (maNhanVien != 0){
            txtDangKy.setText(getResources().getString(R.string.capnhatnhanvien));
            btnDangKy.setText("CẬP NHẬT");
            NhanVienDTO nhanVienDTO = nhanVienDAO.NhanVien(maNhanVien);

            extSDT.setText(nhanVienDTO.getSDT());
            extEMAIL.setText(nhanVienDTO.getEMAIL());
            extHOTEN.setText(nhanVienDTO.getHOTEN());
            extMATKHAU.setText(nhanVienDTO.getMATKHAU());

        }

    }
    private void Anhxa(){
        extSDT = findViewById(R.id.editTextPhone);
        extEMAIL = findViewById(R.id.editsdt);
        extMATKHAU = findViewById(R.id.editTextmatkhau);
        extHOTEN = findViewById(R.id.editTexthoten);
        btnDangKy = findViewById(R.id.btnDangNhap);
        btnDangNhap = findViewById(R.id.btnDangNhaplai);
        txtDangKy = findViewById(R.id.txtDangKy);
    }

    private void CapNhatNhanVien(){
        String sSDT = extSDT.getText().toString();
        String sEMAIL = extEMAIL.getText().toString();
        String sHOTEN = extHOTEN.getText().toString();
        String sMATKHAU = extMATKHAU.getText().toString();
        NhanVienDTO nhanVienDTO = new NhanVienDTO();
        nhanVienDTO.setMANV(maNhanVien);
        nhanVienDTO.setSDT(sSDT);
        nhanVienDTO.setEMAIL(sEMAIL);
        nhanVienDTO.setHOTEN(sHOTEN);
        nhanVienDTO.setMATKHAU(sMATKHAU);

        boolean kiemtra = nhanVienDAO.CapNhatNhanVien(nhanVienDTO);
        if (kiemtra){
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.capnhatthanhcong),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.capnhatthatbai),Toast.LENGTH_SHORT).show();
        }

    }
    private void DongYThemNhanVien(){
        String sSDT = extSDT.getText().toString();
        String sEMAIL = extEMAIL.getText().toString();
        String sHOTEN = extHOTEN.getText().toString();
        String sMATKHAU = extMATKHAU.getText().toString();
        if (sSDT == null || sSDT.equals("")){
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.toastdienthongtinsdt),Toast.LENGTH_LONG).show();
        }else if(sEMAIL == null || sEMAIL.equals("")){
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.toastdienthongtinemail),Toast.LENGTH_LONG).show();
        }else if(sHOTEN == null || sHOTEN.equals("")){
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.toastdienthongtinhoten),Toast.LENGTH_LONG).show();
        }else if(sMATKHAU == null || sMATKHAU.equals("")){
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.toastdienthongtinmatkhau),Toast.LENGTH_LONG).show();
        }else {
            NhanVienDTO nhanVienDTO = new NhanVienDTO();
            nhanVienDTO.setSDT(sSDT);
            nhanVienDTO.setEMAIL(sEMAIL);
            nhanVienDTO.setHOTEN(sHOTEN);
            nhanVienDTO.setMATKHAU(sMATKHAU);
            long kiemtra = nhanVienDAO.ThemNhanVien(nhanVienDTO);
            if (kiemtra !=0){
                Toast.makeText(DangKyActivity.this,getResources().getString(R.string.themthanhcong),Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(DangKyActivity.this,getResources().getString(R.string.themkhongthanhcong),Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDangNhap:
                if (maNhanVien != 0){
                    // Sửa nhân viên.
                    CapNhatNhanVien();
                }else {
                    // Thêm nhân viên
                    DongYThemNhanVien();
                }

                break;
            case R.id.btnDangNhaplai:
                finish();
                break;
        }
    }
}