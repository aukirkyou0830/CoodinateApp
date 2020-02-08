package com.monodukuri.coodinateapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class TestOpenHelper extends SQLiteOpenHelper {
    // データーベースのバージョン
    public static final int DATABASE_VERSION = 1;
    // データーベース名
    public static final String DATABASE_NAME = "closet.db";
    TestOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase closet) {
        // テーブル作成
        closet.execSQL("create table if not exists "+
                closet +
                        ("clozet_no integer primary key autoincrement," +
                "type_no integer not null," +
                "category_no integer not null, "+
                "color text , " +
                "remark text ," +
                "clothes_image blob not null," +
                " regist_date text not null default(datetime(CURRENT_TIMESTAMP, 'localtime')),"+
                "foreign key (category_no) references ClothesCategory(category_no)," +
                "foreign key (type_no) references ClothesType(type_no)")
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase closet,
                          int oldVersion, int newVersion) {
        // アップデートの判別、古いバージョンは削除して新規作成
        closet.execSQL(
                "drop table if exists " + closet
        );
        onCreate(closet);
    }
}

