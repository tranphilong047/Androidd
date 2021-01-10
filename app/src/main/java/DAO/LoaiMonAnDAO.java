package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.LoaiMonAnDTO;
import DTO.NhanVienDTO;
import Database.CreateDataBase;

public class LoaiMonAnDAO {
    SQLiteDatabase database;
    public LoaiMonAnDAO(Context context){
        CreateDataBase createDataBase = new CreateDataBase(context);
        database = createDataBase.open();
    }
    public boolean ThemLoaiMonAn(String tenLoai){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TB_LOAIMONAN_TENLOAI,tenLoai);
        long kiemtra = database.insert(CreateDataBase.TB_LOAIMONAN,null,contentValues);
        if (kiemtra !=0){
            return true;
        }else {
            return false;
        }
    }
    public boolean CapNhatLoaiMonAn(LoaiMonAnDTO loaiMonAnDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TB_LOAIMONAN_TENLOAI,loaiMonAnDTO.getTENLOAI() );
        long kiemtra = database.update(CreateDataBase.TB_LOAIMONAN,contentValues,CreateDataBase.TB_LOAIMONAN_MALOAI+ " = "+loaiMonAnDTO.getMALOAI(),null);
        if (kiemtra !=0){
            return true;
        }else {
            return false;
        }
    }
    public List<LoaiMonAnDTO> DanhSachMon(){
        List<LoaiMonAnDTO> loaiMonAnDTOS = new ArrayList<LoaiMonAnDTO>();
        String truyvan = "SELECT * FROM "+CreateDataBase.TB_LOAIMONAN;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            LoaiMonAnDTO loaiMonAnDTO = new LoaiMonAnDTO();
            loaiMonAnDTO.setMALOAI(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_LOAIMONAN_MALOAI)));
            loaiMonAnDTO.setTENLOAI(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_LOAIMONAN_TENLOAI)));
            loaiMonAnDTOS.add(loaiMonAnDTO);
            cursor.moveToNext();
        }
        return loaiMonAnDTOS;
    }
    public boolean xoaLoaiMonAn(int maLoai){
        long kiemtra =  database.delete(CreateDataBase.TB_LOAIMONAN,CreateDataBase.TB_LOAIMONAN_MALOAI+" = "+maLoai,null);
        if (kiemtra !=0){
            return true;
        }else {
            return false;
        }
    }
    public LoaiMonAnDTO LoaiMonAn(int maLoai){
        LoaiMonAnDTO loaiMonAnDTO = new LoaiMonAnDTO();
        String truyvan = "SELECT * FROM "+ CreateDataBase.TB_LOAIMONAN+" WHERE "+CreateDataBase.TB_LOAIMONAN_MALOAI + " = "+ maLoai;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            loaiMonAnDTO.setTENLOAI(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_LOAIMONAN_TENLOAI)));
            loaiMonAnDTO.setMALOAI(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_LOAIMONAN_MALOAI)));
            cursor.moveToNext();
        }

        return loaiMonAnDTO;
    }

}
