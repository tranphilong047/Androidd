package com.philonggear.nhahang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import CustomAdapter.AdapterHienThiThanhToan;
import DAO.BanAnDAO;
import DAO.HoaDonDAO;
import DTO.ThanhToanDTO;
import FragmentAPP.BanAn;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener {

    GridView gridView;
    Button btnThanhToan,btnThoat;
    TextView txtTongtien;
    HoaDonDAO hoaDonDAO;
    BanAnDAO banAnDAO;
    List<ThanhToanDTO> thanhToanDTOList;
    AdapterHienThiThanhToan adapterHienThiThanhToan;
    FragmentManager fragmentManager;
    long tongtien = 0;
    int maban = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanh_toan);

        gridView = (GridView) findViewById(R.id.gvthanhtoan);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToanHoaDon);
        btnThoat = (Button) findViewById(R.id.btnThoatHoaDon);
        txtTongtien = (TextView) findViewById(R.id.TongTien);

        hoaDonDAO = new HoaDonDAO(this);
        banAnDAO = new BanAnDAO(this);
        fragmentManager = getSupportFragmentManager();

        btnThoat.setOnClickListener(this);
        btnThanhToan.setOnClickListener(this) ;

        maban = getIntent().getIntExtra("maban",0);
        if (maban != 0){

            HienThiThanhToan();
            for (int i =0;i<thanhToanDTOList.size();i++){
                int soluong = thanhToanDTOList.get(i).getSOLUONG();
                int giatien = thanhToanDTOList.get(i).getGIATIEN();

                tongtien += (soluong*giatien);

            }
            txtTongtien.setText(tongtien+"");
        }

    }
    private void HienThiThanhToan(){
        int MaHoaDon = (int) hoaDonDAO.LayMaHoaDonTheoBan(maban,"false");
        thanhToanDTOList = hoaDonDAO.DanhSachMonTheoHoaDon(MaHoaDon);
        adapterHienThiThanhToan = new AdapterHienThiThanhToan(this,R.layout.customer_girdview_thanh_toan,thanhToanDTOList);
        gridView.setAdapter(adapterHienThiThanhToan);
        adapterHienThiThanhToan.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnThanhToanHoaDon:
                boolean kiemtra = banAnDAO.CapNhatTinhTrangBan(maban,"false");
                boolean kiemtrahoadon = hoaDonDAO.CapNhatTrangThaiHoaDonTheoMaBan(maban,"true");
                if (kiemtra && kiemtrahoadon){
                    Toast.makeText(ThanhToanActivity.this,"Thanh Toán Thành Công",Toast.LENGTH_SHORT).show();
                    HienThiThanhToan();
                }else {
                    Toast.makeText(ThanhToanActivity.this,"Không Thể Thanh Toán Hóa Đơn",Toast.LENGTH_SHORT).show();
                }
                Intent intent1 = new Intent(ThanhToanActivity.this,TrangChuActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnThoatHoaDon:
                Intent intent = new Intent(ThanhToanActivity.this,TrangChuActivity.class);
                startActivity(intent);
                break;
        }
    }
}
