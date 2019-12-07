package com.monodukuri.coodinateapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_closet_detail.*

import kotlinx.android.synthetic.main.activity_closet_detail_.*
import kotlinx.android.synthetic.main.activity_closet_detail_.registrationButton
//import kotlinx.android.synthetic.main.activity_closet_detail_.button2
import kotlinx.android.synthetic.main.activity_closet_detail_.deleteButton

class ClosetDetail_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_closet_detail)
    }
}
// // １レコード 追加
// registrationButton.setOnClickListener {
// val values = ContentValues()
// values.put("memo", memo)
// values.put("date", day )
// values.put("type", type)
// // insertOrThrow()
// // 第1引数はテーブル名
// // 第2引数はデータを挿入する際にnull値が許可されていないカラムに代わりに利用される値を指定(?)
// // 第3引数は ContentValue(データ)
// db.insertOrThrow(Clozet, null, values)
// }
// button2.setOnClickListener {
// val intent = Intent(this, ClosetActivity::class.java)
// startActivity(intent)
// }
// // キーを指定し、１レコード削除
// deleteButton.setOnClickListener(){
// db.delete(Clozet, "clozet_no=? AND clothes_type=? AND category_no=? AND color=? AND remark=? AND regist_date=?", arrayOf(clozet_no.toInt(),day.toString()))
// }
//
//
// // 指定 キーのデータあり?
// fun isRecord( type:Int ,day:Int ) :Boolean {
// val selectQql : String = "SELECT count(*) as cnt FROM " + DB_TABLE_NAME + " where type = ? and date= ? "
//
// val cursor: Cursor = db.rawQuery(selectQql, arrayOf(type.toString(),day.toString()))
// cursor.moveToFirst()
// val count = cursor.getInt(cursor.getColumnIndex("cnt"))
// cursor.close()
// return if ( 0 < count ) { true } else { false }
// }
// // キー(Type,date)を指定してmemoを修正
// fun updateMemo(type : Int, day : Int, memo : String ) {
// val values : ContentValues = ContentValues()
// values.put("memo",memo)
// // 第二引数がupdateする条件
// // 第三引数の? に第四引数が置き換わる
// db.update(DB_TABLE_NAME, values, "type=? AND date=? ", arrayOf(type.toString(),day.toString()))    }
//
// }