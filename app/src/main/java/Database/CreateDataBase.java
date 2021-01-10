package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDataBase extends SQLiteOpenHelper {

    public static String TB_NHANVIEN = "NHANVIEN";
    public static String TB_MONAN = "MONAN";
    public static String TB_LOAIMONAN = "LOAIMONAN";
    public static String TB_BANAN = "BANAN";
    public static String TB_HOADON = "HOADON";
    public static String TB_CHITIETHOADON = "CHITIETHOADON";
    public static String TB_QUYEN = "QUYEN";

    public static String TB_QUYEN_MAQUYEN = "MAQUYEN";
    public static String TB_QUYEN_TENQUYEN = "TENQUYEN";

    public static String TB_NHANVIEN_MANV = "MANV";
    public static String TB_NHANVIEN_MAQUYEN = "MAQUYEN";
    public static String TB_NHANVIEN_HOTEN = "HOTEN";
    public static String TB_NHANVIEN_MATKHAU = "MATKHAU";
    public static String TB_NHANVIEN_EMAIL = "EMAIL";
    public static String TB_NHANVIEN_SDT = "SDT";

    public static String TB_MONAN_MAMON = "MAMON";
    public static String TB_MONAN_TENMONAN = "TENMONAN";
    public static String TB_MONAN_GIATIEN = "GIATIEN";
    public static String TB_MONAN_MALOAI = "MALOAI";
    public static String TB_MONAN_HINHANH = "HINHANH";
    public static String TB_MONAN_MOTA = "MOTA";


    public static String TB_LOAIMONAN_MALOAI = "MALOAI";
    public static String TB_LOAIMONAN_TENLOAI = "TENLOAI";

    public static String TB_BANAN_MABAN = "MABAN";
    public static String TB_BANAN_TENBAN = "TENBAN";
    public static String TB_BANAN_TINHTRANG = "TINHTRANG";

    public static String TB_HOADON_MAHD = "MAHD";
    public static String TB_HOADON_MANV = "MANV";
    public static String TB_HOADON_NGAYTAO = "NGAYTAO";
    public static String TB_HOADON_TINHTRANG = "TINHTRANG";
    public static String TB_HOADON_MABAN = "MABAN";

    public static String TB_CHITIETHOADON_MADH = "MAHD";
    public static String TB_CHITIETHOADON_MAMONAN = "MAMONAN";
    public static String TB_CHITIETHOADON_SOLUONG = "SOLUONG";

    public CreateDataBase(@Nullable Context context) {
        super(context, "NhaHang", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbNHANVIEN = "CREATE TABLE " + TB_NHANVIEN + " ( " + TB_NHANVIEN_MANV + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_NHANVIEN_HOTEN + " TEXT, " + TB_NHANVIEN_MATKHAU + " TEXT, " + TB_NHANVIEN_EMAIL + " TEXT, "
                + TB_NHANVIEN_SDT + " TEXT, " + TB_NHANVIEN_MAQUYEN + " INTEGER )";

        String tbBANAN = "CREATE TABLE " + TB_BANAN + " ( " + TB_BANAN_MABAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_BANAN_TENBAN + " TEXT, " + TB_BANAN_TINHTRANG + " TEXT )";

        String tbMONAN = "CREATE TABLE " + TB_MONAN + " ( " + TB_MONAN_MAMON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_MONAN_TENMONAN + " TEXT, " + TB_MONAN_MALOAI + " INTEGER, " + TB_MONAN_GIATIEN + " TEXT, "
                + TB_MONAN_MOTA + " TEXT, " + TB_MONAN_HINHANH + " BLOB ) ";

        String tbLOAIMON = "CREATE TABLE " + TB_LOAIMONAN + " ( " + TB_LOAIMONAN_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TB_LOAIMONAN_TENLOAI + " TEXT )";

        String tbQUYEN = "CREATE TABLE " + TB_QUYEN + " ( " + TB_QUYEN_MAQUYEN + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TB_QUYEN_TENQUYEN + " TEXT )";

        String tbHOADON = "CREATE TABLE " + TB_HOADON + " ( " + TB_HOADON_MAHD + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_HOADON_MABAN + " INTEGER, " + TB_HOADON_MANV + " INTEGER, " + TB_HOADON_NGAYTAO + " TEXT, "
                + TB_HOADON_TINHTRANG + " TEXT )";

        String tbCHITIETHOADON = "CREATE TABLE " + TB_CHITIETHOADON + " ( " + TB_CHITIETHOADON_MADH + " INTEGER, "
                + TB_CHITIETHOADON_MAMONAN + " INTEGER, " + TB_CHITIETHOADON_SOLUONG + " INTEGER, "
                + " PRIMARY KEY ( " + TB_CHITIETHOADON_MADH + "," + TB_CHITIETHOADON_MAMONAN + "))";

        db.execSQL(tbNHANVIEN);
        db.execSQL(tbBANAN);
        db.execSQL(tbMONAN);
        db.execSQL(tbLOAIMON);
        db.execSQL(tbHOADON);
        db.execSQL(tbCHITIETHOADON);
        db.execSQL(tbQUYEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
