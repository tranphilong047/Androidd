package com.philonggear.nhahang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import DAO.HoaDonDAO;
import DTO.ChiTietHoaDonDTO;

public class ThemSoLuongActivity extends AppCompatActivity implements View.OnClickListener {
    int maban,mamonan;
    Button btnThemSoLuong;
    EditText extSoLuong;
    HoaDonDAO hoaDonDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_them_so_luong);
        btnThemSoLuong = (Button) findViewById(R.id.btnThemLoaiMonAn);
        extSoLuong = (EditText) findViewById(R.id.editLoaiMonAn);

        hoaDonDAO = new HoaDonDAO(this);

        Intent intent = getIntent();
        mamonan = intent.getIntExtra("mamonan",0);
        maban = intent.getIntExtra("maban",0);
        btnThemSoLuong.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int MaHoaDon = (int) hoaDonDAO.LayMaHoaDonTheoBan(maban,"false");
        boolean kiemtra = hoaDonDAO.kiemtratinhtrangmonan(MaHoaDon,mamonan);
        if (kiemtra){
            //món ăn đã tồn tại
            int soluongcu = hoaDonDAO.soluongmonan(MaHoaDon,mamonan);
            int soluongmoi = Integer.parseInt(extSoLuong.getText().toString());

            int tongsoluong  = soluongcu+soluongmoi;
            ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
            chiTietHoaDonDTO.setMAHOADON(MaHoaDon);
            chiTietHoaDonDTO.setMAMONAN(mamonan);
            chiTietHoaDonDTO.setSOLUONG(tongsoluong);
            boolean kiemtrasoluong =  hoaDonDAO.capnhatsoluong(chiTietHoaDonDTO);
            if (kiemtrasoluong){
                Toast.makeText(this,getResources().getString(R.string.thembanthanhcong),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,getResources().getString(R.string.thembankhongthanhcong),Toast.LENGTH_SHORT).show();
            }
        }else {
            //món ăn chưa tồn tại
            int soluongmonan = Integer.parseInt(extSoLuong.getText().toString());
            ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
            chiTietHoaDonDTO.setMAHOADON(MaHoaDon);
            chiTietHoaDonDTO.setMAMONAN(mamonan);
            chiTietHoaDonDTO.setSOLUONG(soluongmonan);
            boolean kiemtrasoluong =  hoaDonDAO.ThemChiTietHoaDon(chiTietHoaDonDTO);
            if (kiemtrasoluong){
                Toast.makeText(this,getResources().getString(R.string.thembanthanhcong),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,getResources().getString(R.string.thembankhongthanhcong),Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
}
