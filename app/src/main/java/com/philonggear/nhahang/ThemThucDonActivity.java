package com.philonggear.nhahang;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import CustomAdapter.AdapterHienThiLoaiMonAn;
import DAO.LoaiMonAnDAO;
import DAO.MonAnDAO;
import DTO.LoaiMonAnDTO;
import DTO.MonAnDTO;

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {

    public static int REQUEST_CODE_THEMLOAITHUCDON = 113;
    public static int REQUEST_CODE_MOHINH = 123;
    Button btnThemLoaiThucDon,btnDongYThem,btnThoat;
    EditText extTenMon,extGia,extMota;
    Spinner spinnerLoaiThucDon;
    LoaiMonAnDAO loaiMonAnDAO;
    MonAnDAO MonAnDAOs;
    List<LoaiMonAnDTO> loaiMonAnDTOS;
    AdapterHienThiLoaiMonAn adapterHienThiLoaiMonAn;
    ImageView imHinhMonAn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_them_thuc_don);

        AnhXa();
        loaiMonAnDAO = new LoaiMonAnDAO(this);
        MonAnDAOs = new MonAnDAO(this);
        HienThiLoaiMonAn();

        btnThemLoaiThucDon.setOnClickListener(this);
        imHinhMonAn.setOnClickListener(this);
        btnDongYThem.setOnClickListener(this);
        btnThoat.setOnClickListener(this);

    }
    private void AnhXa(){
        btnThemLoaiThucDon = (Button) findViewById(R.id.btnThemloai);
        btnDongYThem = (Button) findViewById(R.id.dongythem);
        btnThoat = (Button) findViewById(R.id.btnthoat);
        spinnerLoaiThucDon = (Spinner) findViewById(R.id.loaimon);
        imHinhMonAn = (ImageView) findViewById(R.id.hinhanh);
        extTenMon = (EditText) findViewById(R.id.edittenmon);
        extGia = (EditText) findViewById(R.id.txtMaBan);
        extMota = (EditText) findViewById(R.id.editmota);
    }
    private void HienThiLoaiMonAn(){
        loaiMonAnDTOS = loaiMonAnDAO.DanhSachMon();
        adapterHienThiLoaiMonAn = new AdapterHienThiLoaiMonAn(ThemThucDonActivity.this,R.layout.layout_customer_loai_mon_an,loaiMonAnDTOS);
        spinnerLoaiThucDon.setAdapter(adapterHienThiLoaiMonAn);
        adapterHienThiLoaiMonAn.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnThemloai:
                Intent intent = new Intent(ThemThucDonActivity.this,ThemLoaiThucDonActivity.class);
                startActivityForResult(intent,REQUEST_CODE_THEMLOAITHUCDON);
                break;
            case R.id.hinhanh:
                Intent iMoHinh = new Intent(Intent.ACTION_PICK);
                iMoHinh.setType("image/*");
                startActivityForResult(iMoHinh,REQUEST_CODE_MOHINH);
                break;
            case R.id.dongythem:
                int vitri = spinnerLoaiThucDon.getSelectedItemPosition();
                int maLoai =loaiMonAnDTOS.get(vitri).getMALOAI();
                String TenMonAn = extTenMon.getText().toString();
                String Gia = extGia.getText().toString();
                String MoTa = extMota.getText().toString();

                if (TenMonAn !=null && Gia != null && MoTa != null && !TenMonAn.equals("") && !Gia.equals("") && !MoTa.equals("")){
                    //chuyên data image -> byte
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imHinhMonAn.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream bytwArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,bytwArray);
                    byte[] hinhanh = bytwArray.toByteArray();
                    Log.d("test",hinhanh+"");


                    MonAnDTO monAnDTOs = new MonAnDTO();
                    monAnDTOs.setGIATIEN(Gia);
                    monAnDTOs.setHINHANH(hinhanh);
                    monAnDTOs.setTENMONAN(TenMonAn);
                    monAnDTOs.setMOTA(MoTa);
                    monAnDTOs.setMALOAI(maLoai);

                    boolean kiemtra = MonAnDAOs.ThemMonAn(monAnDTOs);
                    if (kiemtra){
                        Toast.makeText(this,getResources().getString(R.string.thembanthanhcong),Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,getResources().getString(R.string.thembankhongthanhcong),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,getResources().getString(R.string.thembankhongthanhcong),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnthoat:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_THEMLOAITHUCDON){
            if (resultCode == Activity.RESULT_OK){
                Intent intent = data;
                boolean kiemtra = intent.getBooleanExtra("kiemtraloaimonan",false);
                if (kiemtra){
                    HienThiLoaiMonAn();
                    Toast.makeText(this,getResources().getString(R.string.thembanthanhcong),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,getResources().getString(R.string.thembankhongthanhcong),Toast.LENGTH_SHORT).show();
                }
            }
        }else if(REQUEST_CODE_MOHINH == requestCode) {
            if (resultCode == Activity.RESULT_OK && data != null){
                Uri uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imHinhMonAn.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

