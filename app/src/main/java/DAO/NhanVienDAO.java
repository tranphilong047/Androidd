package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.BanAnDTO;
import DTO.NhanVienDTO;
import Database.CreateDataBase;

public class NhanVienDAO {
    SQLiteDatabase database;
    public NhanVienDAO(Context context){
        CreateDataBase createDataBase = new CreateDataBase(context);
        database = createDataBase.open();
    }
    public long ThemNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TB_NHANVIEN_SDT,nhanVienDTO.getSDT());
        contentValues.put(CreateDataBase.TB_NHANVIEN_EMAIL,nhanVienDTO.getEMAIL());
        contentValues.put(CreateDataBase.TB_NHANVIEN_MATKHAU,nhanVienDTO.getMATKHAU());
        contentValues.put(CreateDataBase.TB_NHANVIEN_HOTEN,nhanVienDTO.getHOTEN());

        long kiemtra = database.insert(CreateDataBase.TB_NHANVIEN,null,contentValues);
        return kiemtra;
    }
    public boolean CapNhatNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TB_NHANVIEN_SDT,nhanVienDTO.getSDT());
        contentValues.put(CreateDataBase.TB_NHANVIEN_EMAIL,nhanVienDTO.getEMAIL());
        contentValues.put(CreateDataBase.TB_NHANVIEN_MATKHAU,nhanVienDTO.getMATKHAU());
        contentValues.put(CreateDataBase.TB_NHANVIEN_HOTEN,nhanVienDTO.getHOTEN());

        long kiemtra = database.update(CreateDataBase.TB_NHANVIEN,contentValues,CreateDataBase.TB_NHANVIEN_MANV + " = "+nhanVienDTO.getMANV(),null);
        if (kiemtra !=0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean KiemTraTaiKhoan(){
        String truyvan = "SELECT * FROM " + CreateDataBase.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(truyvan,null);
        if (cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
    public int KiemTraDangNhap(String SodienThoai,String MatKhau){
        String truyvan = "SELECT * FROM " + CreateDataBase.TB_NHANVIEN + " WHERE "+ CreateDataBase.TB_NHANVIEN_SDT + " = '"+ SodienThoai
                +"' AND " +CreateDataBase.TB_NHANVIEN_MATKHAU + "='"+MatKhau+"'";
        int manhanvien = 0;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            manhanvien = cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_MANV));
            cursor.moveToNext();
        }
        return manhanvien;
    }
    public List<NhanVienDTO> DanhSachNhanVien(){
        List<NhanVienDTO> nhanVienDTOS = new ArrayList<NhanVienDTO>();
        String truyvan = "SELECT * FROM "+ CreateDataBase.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            NhanVienDTO nhanVienDTO = new NhanVienDTO();
            nhanVienDTO.setHOTEN(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_HOTEN)));
            nhanVienDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_MATKHAU)));
            nhanVienDTO.setEMAIL(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_EMAIL)));
            nhanVienDTO.setSDT(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_SDT)));
            nhanVienDTO.setMAQUYEN(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_MAQUYEN)));
            nhanVienDTO.setMANV(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_MANV)));
            nhanVienDTOS.add(nhanVienDTO);
            cursor.moveToNext();
        }

        return nhanVienDTOS;
    }
    public boolean xoaNhanVien(int manhanvien){
        long kiemtra =  database.delete(CreateDataBase.TB_NHANVIEN,CreateDataBase.TB_NHANVIEN_MANV+" = "+manhanvien,null);
        if (kiemtra !=0){
            return true;
        }else {
            return false;
        }
    }
    public NhanVienDTO NhanVien(int maNhanVien){
        NhanVienDTO nhanVienDTO = new NhanVienDTO();
        String truyvan = "SELECT * FROM "+ CreateDataBase.TB_NHANVIEN+" WHERE "+CreateDataBase.TB_NHANVIEN_MANV + " = "+ maNhanVien;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            nhanVienDTO.setHOTEN(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_HOTEN)));
            nhanVienDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_MATKHAU)));
            nhanVienDTO.setEMAIL(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_EMAIL)));
            nhanVienDTO.setSDT(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_SDT)));
            nhanVienDTO.setMAQUYEN(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_MAQUYEN)));
            nhanVienDTO.setMANV(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_NHANVIEN_MANV)));
            cursor.moveToNext();
        }

        return nhanVienDTO;
    }
}
