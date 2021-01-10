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

import DAO.LoaiMonAnDAO;
import DTO.LoaiMonAnDTO;
import DTO.NhanVienDTO;

public class ThemLoaiThucDonActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnThemLoaiTD;
    EditText editLoaiMon;
    LoaiMonAnDAO loaiMonAnDAO;
    int maloai = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_them_loai_thuc_don);

        loaiMonAnDAO = new LoaiMonAnDAO(this);
        editLoaiMon = (EditText) findViewById(R.id.editLoaiMonAn);
        btnThemLoaiTD =(Button) findViewById(R.id.btnThemLoaiMonAn);
        btnThemLoaiTD.setOnClickListener(this);

        maloai = getIntent().getIntExtra("maloaisanpham",0);
        if (maloai != 0){
            btnThemLoaiTD.setText("CẬP NHẬT");
            LoaiMonAnDTO loaiMonAnDTO = loaiMonAnDAO.LoaiMonAn(maloai);

            editLoaiMon.setText(loaiMonAnDTO.getTENLOAI());

        }

    }
    private void CapNhatLoaiMonAn(){
        String sTenLoai = editLoaiMon.getText().toString();
        LoaiMonAnDTO loaiMonAnDTO = new LoaiMonAnDTO();
        loaiMonAnDTO.setMALOAI(maloai);
        loaiMonAnDTO.setTENLOAI(sTenLoai);
        boolean kiemtra = loaiMonAnDAO.CapNhatLoaiMonAn(loaiMonAnDTO);
        if (kiemtra){
            Toast.makeText(ThemLoaiThucDonActivity.this,getResources().getString(R.string.capnhatthanhcong),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(ThemLoaiThucDonActivity.this,getResources().getString(R.string.capnhatthatbai),Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    private void ThemLoaiMonAn(){
        String sTenLoai = editLoaiMon.getText().toString();
        if (sTenLoai != null || sTenLoai.equals("")){
            boolean kiemtra = loaiMonAnDAO.ThemLoaiMonAn(sTenLoai);
            Intent intent = new Intent();
            intent.putExtra("kiemtraloaimonan",kiemtra);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }else {
            Toast.makeText(this,getResources().getString(R.string.vuilongnhapdulieu),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (maloai != 0){
            // Sửa nhân viên.
            CapNhatLoaiMonAn();
        }else {
            // Thêm nhân viên
            ThemLoaiMonAn();
        }

    }
}
