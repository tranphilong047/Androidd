package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.NhanVienDTO;
import DTO.ThanhToanDTO;
import Database.CreateDataBase;

public class HoaDonDAO {
    SQLiteDatabase database;
    public HoaDonDAO(Context context){
        CreateDataBase createDataBase = new CreateDataBase(context);
        database = createDataBase.open();
    }
    public long ThemHoaDon(HoaDonDTO hoaDonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TB_HOADON_MABAN,hoaDonDTO.getMABAN());
        contentValues.put(CreateDataBase.TB_HOADON_MANV,hoaDonDTO.getMANV());
        contentValues.put(CreateDataBase.TB_HOADON_NGAYTAO,hoaDonDTO.getNGAYGOI());
        contentValues.put(CreateDataBase.TB_HOADON_TINHTRANG,hoaDonDTO.getTINHTRANG());

        long mahoadon = database.insert(CreateDataBase.TB_HOADON,null,contentValues);
        return mahoadon;

    }
    public long LayMaHoaDonTheoBan(int MaBan,String tinhtrang){

        String truyvan = "SELECT * FROM " + CreateDataBase.TB_HOADON + " WHERE " + CreateDataBase.TB_HOADON_MABAN + " = " + MaBan + " AND "
                + CreateDataBase.TB_HOADON_TINHTRANG + " = '" + tinhtrang+"' ";
        Cursor cursor = database.rawQuery(truyvan,null);
        long MaHoaDon = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            MaHoaDon = cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_HOADON_MAHD));
            cursor.moveToNext();
        }
        return  MaHoaDon;
    }
    public boolean kiemtratinhtrangmonan(int mahoadon,int mamonan){
        String truyvan = "SELECT * FROM " + CreateDataBase.TB_CHITIETHOADON + " WHERE " + CreateDataBase.TB_CHITIETHOADON_MAMONAN + " = " + mamonan + " AND "
                + CreateDataBase.TB_CHITIETHOADON_MADH + " = " + mahoadon;
        Cursor cursor = database.rawQuery(truyvan,null);
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
    public int  soluongmonan(int mahoadon,int mamonan){
        int soluong = 0;
        String truyvan = "SELECT * FROM " + CreateDataBase.TB_CHITIETHOADON + " WHERE " + CreateDataBase.TB_CHITIETHOADON_MAMONAN + " = " + mamonan + " AND "
                + CreateDataBase.TB_CHITIETHOADON_MADH + " = " + mahoadon;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            soluong = cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_CHITIETHOADON_SOLUONG));
            cursor.moveToNext();
        }
        return soluong;
    }
    public boolean capnhatsoluong(ChiTietHoaDonDTO chiTietHoaDonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TB_CHITIETHOADON_SOLUONG,chiTietHoaDonDTO.getSOLUONG());

        long kiemtra = database.update(CreateDataBase.TB_CHITIETHOADON,contentValues,CreateDataBase.TB_CHITIETHOADON_MADH +" = "+chiTietHoaDonDTO.getMAHOADON()+" AND "
                + CreateDataBase.TB_CHITIETHOADON_MAMONAN + " = "+chiTietHoaDonDTO.getMAMONAN(),null);
        if (kiemtra != 0 ){
            return true;
        }else {
            return false;
        }
    }
    public boolean ThemChiTietHoaDon(ChiTietHoaDonDTO chiTietHoaDonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TB_CHITIETHOADON_SOLUONG,chiTietHoaDonDTO.getSOLUONG());
        contentValues.put(CreateDataBase.TB_CHITIETHOADON_MADH,chiTietHoaDonDTO.getMAHOADON());
        contentValues.put(CreateDataBase.TB_CHITIETHOADON_MAMONAN,chiTietHoaDonDTO.getMAMONAN());

        long kiemtra = database.insert(CreateDataBase.TB_CHITIETHOADON,null,contentValues);
        if (kiemtra != 0 ){
            return true;
        }else {
            return false;
        }
    }
    public List<ThanhToanDTO> DanhSachMonTheoHoaDon(int mahoadon){
        String truyvan = "SELECT * FROM " + CreateDataBase.TB_CHITIETHOADON + ","+CreateDataBase.TB_MONAN
                + " WHERE "+CreateDataBase.TB_CHITIETHOADON_MADH +" = "+ mahoadon
        +" AND "+CreateDataBase.TB_CHITIETHOADON+"."+CreateDataBase.TB_CHITIETHOADON_MAMONAN +" = "
                + CreateDataBase.TB_MONAN+"."+CreateDataBase.TB_MONAN_MAMON;
        List<ThanhToanDTO> thanhToanDTOS = new ArrayList<ThanhToanDTO>();
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ThanhToanDTO thanhToanDTO = new ThanhToanDTO();
            thanhToanDTO.setSOLUONG(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_CHITIETHOADON_SOLUONG)));
            thanhToanDTO.setGIATIEN(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_MONAN_GIATIEN)));
            thanhToanDTO.setTENMONAN(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_MONAN_TENMONAN)));

            thanhToanDTOS.add(thanhToanDTO);
            cursor.moveToNext();
        }
        return thanhToanDTOS;
    }
    public boolean CapNhatTrangThaiHoaDonTheoMaBan(int maban,String tinhtrang){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TB_HOADON_TINHTRANG,maban);

        long kiemtra = database.update(CreateDataBase.TB_HOADON,contentValues,CreateDataBase.TB_HOADON_MABAN + " = '"+ maban+"'",null);
        if (kiemtra !=0){
            return true;
        }else {
            return false;
        }
    }
    public List<HoaDonDTO> DanhSachHoaDon(){
        List<HoaDonDTO> hoaDonDTOS = new ArrayList<HoaDonDTO>();
        String truyvan = "SELECT * FROM "+ CreateDataBase.TB_HOADON;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            HoaDonDTO hoaDonDTO = new HoaDonDTO();
            hoaDonDTO.setNGAYGOI(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_HOADON_NGAYTAO)));
            hoaDonDTO.setMANV(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_HOADON_MANV)));
            hoaDonDTO.setMABAN(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_HOADON_MABAN)));
            hoaDonDTO.setTINHTRANG(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_HOADON_TINHTRANG)));
            hoaDonDTO.setMAHOADON(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_HOADON_MAHD)));
            hoaDonDTOS.add(hoaDonDTO);
            cursor.moveToNext();
        }

        return hoaDonDTOS;
    }
    public boolean xoaHoaDon(int mahoadon){
        long kiemtra =  database.delete(CreateDataBase.TB_HOADON,CreateDataBase.TB_HOADON_MAHD+" = "+mahoadon,null);
        if (kiemtra !=0){
            return true;
        }else {
            return false;
        }
    }
}
