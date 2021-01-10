package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.BanAnDTO;
import Database.CreateDataBase;
import FragmentAPP.BanAn;

public class BanAnDAO {
    SQLiteDatabase database;
    public BanAnDAO(Context context){
        CreateDataBase createDataBase = new CreateDataBase(context);
        database = createDataBase.open();
    }
    public Boolean ThemBanAn(String tenban){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TB_BANAN_TENBAN,tenban);
        contentValues.put(CreateDataBase.TB_BANAN_TINHTRANG,"false");

        long kiemtra = database.insert(CreateDataBase.TB_BANAN,null,contentValues);
        if (kiemtra!=0){
            return true;
        }else {
            return false;
        }
    }
    public List<BanAnDTO> DanhSachBanAn(){
        List<BanAnDTO> banAnDTOList = new ArrayList<BanAnDTO>();
        String truyvan = "SELECT * FROM "+ CreateDataBase.TB_BANAN;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            BanAnDTO banAnDTO = new BanAnDTO();
            banAnDTO.setMABAN(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TB_BANAN_MABAN)));
            banAnDTO.setTENBAN(cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_BANAN_TENBAN)));
            banAnDTOList.add(banAnDTO);
            cursor.moveToNext();
        }

        return banAnDTOList;
    }
    public String TinhTrangBan(int maban){
        String tinhtrang = "";
        String truyvan = "SELECT *FROM "+CreateDataBase.TB_BANAN +" WHERE "+CreateDataBase.TB_BANAN_MABAN + " = '"+maban+"'";
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tinhtrang = cursor.getString(cursor.getColumnIndex(CreateDataBase.TB_BANAN_TINHTRANG));
            cursor.moveToNext();
        }
        return tinhtrang;
    }
    public boolean CapNhatTinhTrangBan(int maban,String tinhtrang){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TB_BANAN_TINHTRANG,tinhtrang);

       long kiemtra = database.update(CreateDataBase.TB_BANAN,contentValues,CreateDataBase.TB_BANAN_MABAN +" = '"+maban+"'",null);

       if (kiemtra != 0){
           return true;
       }else {
           return false;
       }
    }
}
