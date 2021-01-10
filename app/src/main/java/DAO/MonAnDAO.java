package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import DTO.MonAnDTO;
import Database.CreateDataBase;

public class MonAnDAO {
    SQLiteDatabase database;
    public MonAnDAO(Context context){
        CreateDataBase createDataBase = new CreateDataBase(context);
        database = createDataBase.open();
    }
    public boolean ThemMonAn(MonAnDTO monAnDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TB_MONAN_TENMONAN,monAnDTO.getTENMONAN());
        contentValues.put(CreateDataBase.TB_MONAN_GIATIEN,monAnDTO.getGIATIEN());
        contentValues.put(CreateDataBase.TB_MONAN_MALOAI,monAnDTO.getMALOAI());
        contentValues.put(CreateDataBase.TB_MONAN_MOTA,monAnDTO.getMOTA());
        contentValues.put(CreateDataBase.TB_MONAN_HINHANH,monAnDTO.getHINHANH());

        long kiemtra = database.insert(CreateDataBase.TB_MONAN,null,contentValues);
        if (kiemtra!=0){
            return true;
        }else {
            return false;
        }
    }
    public List<MonAnDTO> DanhSachMonAn(int MaLoai){
        List<MonAnDTO> monAnDTOS = new ArrayList<MonAnDTO>();
        String truyvan = "SELECT * FROM " + CreateDataBase.TB_MONAN + " WHERE " + CreateDataBase.TB_MONAN_MALOAI + " = '" + MaLoai + "' ";
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            MonAnDTO monAnDTO = new MonAnDTO();
            monAnDTO.setTENMONAN(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_MONAN_TENMONAN)));
            monAnDTO.setGIATIEN(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_MONAN_GIATIEN)));
            monAnDTO.setMOTA(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_MONAN_MOTA)));
            monAnDTO.setHINHANH(cursor.getBlob(cursor.getColumnIndex(CreateDataBase.TB_MONAN_HINHANH)));
            monAnDTO.setMAMON(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_MONAN_MAMON)));
            monAnDTO.setMALOAI(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_MONAN_MALOAI)));
            monAnDTOS.add(monAnDTO);
            cursor.moveToNext();
        }
        return monAnDTOS;
    }
    public boolean xoaMonAn(int mamon){
        long kiemtra =  database.delete(CreateDataBase.TB_MONAN,CreateDataBase.TB_MONAN_MAMON+" = "+mamon,null);
        if (kiemtra !=0){
            return true;
        }else {
            return false;
        }
    }
}
