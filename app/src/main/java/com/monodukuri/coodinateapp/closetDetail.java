package com.monodukuri.coodinateapp;
//AndroidX
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class closetDetail extends AppCompatActivity {
    /**
     * 変数の作成
     */
    private SQLiteDatabase closet;
    private TestOpenHelper helper;
    private EditText Key;
    private View remark;
    private ImageView image;
    String str;
    String str1;
    String str2;
    View view;                //view
    int TypeNo;               //type番号
    int CategoryNo;           //カテゴリ
    // 選択肢
    private String ColorItem[] = {"赤", "青", "黄", "紺", "緑", "黒", "白", "グレー", "茶", "カーキ"};
    private String TypeItem[] = {"ジャケット", "ジーンズ", "チノパン", "セーター"};
    private String CategoryItem[] = {"フォーマル", "カジュアル"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet_detail_);
        Key = findViewById(R.id.key);
        remark = findViewById(R.id.Input);
        image = findViewById(R.id.detailPicture);
        final Spinner type = findViewById(R.id.type);
        final Spinner color = findViewById(R.id.color);
        final Spinner category = findViewById(R.id.catgory);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button insertButton = findViewById(R.id.registrationButton);
        System.out.println(getIntent().getStringExtra("TEXY_KEY"));
        Intent intent = getIntent();
        String TextKey = intent.getStringExtra("TEXT_KEY");
        if(TextKey != null) {
            readData(TextKey);
        }
        // ArrayAdapter　色
        ArrayAdapter adapter
                = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ColorItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // color に adapter をセット
        color.setAdapter(adapter);
        // リスナーを登録
        color.setOnItemSelectedListener(new OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String item = (String) spinner.getSelectedItem();
                str = item;
            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        // ArrayAdapter　種別
        ArrayAdapter adapter1
                = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TypeItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // type に adapter をセット
        type.setAdapter(adapter1);
        // リスナーを登録
        type.setOnItemSelectedListener(new OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String item = (String) spinner.getSelectedItem();
                str1 = item;
            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        // ArrayAdapter　カテゴリ
        ArrayAdapter adapter2
                = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CategoryItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // Category に adapter をセット
        category.setAdapter(adapter2);
        // リスナーを登録
        category.setOnItemSelectedListener(new OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String item = (String) spinner.getSelectedItem();
                str2 = item;
            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
        /**
         * 登録ボタンアクション
         */
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (helper == null) {
                    helper = new TestOpenHelper(getApplicationContext());
                }
                if (closet == null) {
                    closet = helper.getWritableDatabase();
                }
                //必要な項目の取得
                String key = Key.getText().toString();
                String Category = category.getSelectedItem().toString();
                String Color = color.getSelectedItem().toString();
                String Type = type.getSelectedItem().toString();
                String Remark = remark.toString();
                ImageView image1 = image;
                //idの有り無しの取得で条件分岐
                if (key != "") {
                    updateData(closet, key, Type, Category , Color ,Remark, image1);
                } else {
                    insertData(closet, key, Type, Category , Color ,Remark, image1);
                }
            }
        });
        /***
         * 削除ボタンアクション
         */
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (helper == null) {
                    helper = new TestOpenHelper(getApplicationContext());
                }
                if (closet == null) {
                    closet = helper.getWritableDatabase();
                }
                String key = Key.getText().toString();
                if (key != "") {
                    deleteData(closet, key);
                } else {
                    showAlertDialog(view);
                }
            }
        });
    }

    /**
     * 画面の初期表示データの取得
     * @return
     */
    private void readData(String Key) {
        if (helper == null) {
            helper = new TestOpenHelper(getApplicationContext());
        }
        if (closet == null) {
            closet = helper.getReadableDatabase();
        }
        String[] startKey  = new String[1];
        startKey[0] = Key;
        String[] projection = {
            "closet_no",
            "type_no",
            "category_no",
            "color",
            "remark",
            "clothes_image"
        };
        Log.d("debug", "**********Cursor");
        Cursor cursor = closet.query("closet", projection, "closet_no", startKey, null, null, null);
        int getType;
        int getCatogory;
        String getcolor;
        String getremark;

        while (cursor.moveToNext()){
           getType = cursor.getInt(cursor.getColumnIndexOrThrow("type_no"));
           getCatogory = cursor.getInt(cursor.getColumnIndexOrThrow("category_no"));
           getcolor = cursor.getString(cursor.getColumnIndexOrThrow("color"));
           getremark = cursor.getString(cursor.getColumnIndexOrThrow("remark"));
        }

        cursor.close();
    }

    /**
     * データを更新する。
     * @param closet
     * @param type
     * @param category
     * @param color
     * @param remark
     * @param image
     */
    private void updateData(SQLiteDatabase closet,  String key,String type, String category, String color, String remark, ImageView image) {
        ContentValues values = new ContentValues();
        int type_No = setTypeMethod(type);
        int Category_No = setCategoryMethod(category);
        values.put("closet_no", key);
        values.put("type_no", type_No);
        values.put("category_no", Category_No);
        values.put("color", color);
        values.put("remark", remark);
        values.put("clothes_image", String.valueOf(image));
        closet.update("closet", values, "closet_no = key, type_no = type_No, category_no = Category_no, color = color, remark = remark, clothes_image = image", null);
        showAlertInsertDialog(view);
    }

    /**
     * データを追加する。
     * @param closet
     * @param type
     * @param category
     * @param color
     * @param remark
     * @param image
     */

    private void insertData(SQLiteDatabase closet, String key, String type, String category, String color, String remark, ImageView image) {
        ContentValues values = new ContentValues();
        int type_No = setTypeMethod(type);
        int Category_No = setCategoryMethod(category);
        values.put("closet_no", key);
        values.put("type_no", type_No);
        values.put("category_no", Category_No);
        values.put("color", color);
        values.put("remark", remark);
        values.put("clothes_image", String.valueOf(image));
        closet.insert("closet", "", values);
        showAlertAddDialog(view);
    }

    /**
     * 削除機能
     * @param closet
     * @param key
     */

    private void deleteData(SQLiteDatabase closet, String key) {
         String Key[];
         Key = new String[1];
         Key[0] = key;
         closet.delete("closet", "clozet_no", Key);
         showAlertDeleteDialog(view);
    }

    /**
     * 警告メッセージダイアログ
     * @param v
     */

    public void showAlertDialog(View v) {
        new AlertDialog.Builder(this).setTitle("alert")
                .setMessage("このデータは登録されていません")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
    }

    /**
     * 削除メッセージダイアログ
     * @param v
     */

    public void showAlertDeleteDialog(View v) {
        new AlertDialog.Builder(this).setTitle("alert")
                .setMessage("削除を行いました")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
    }

    /**
     * 登録メッセージダイアログ
     * @param v
     */

    public void showAlertAddDialog(View v) {
        new AlertDialog.Builder(this).setTitle("alert")
                .setMessage("登録を行いました")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
    }

    /**
     * 追加メッセージ表示ダイアログ
     * @param v
     */

    public void showAlertInsertDialog(View v) {
        new AlertDialog.Builder(this).setTitle("alert")
                .setMessage("追加を行いました")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
    }

    /**
     * colorをcolorNoに詰めなおしてrequestにreturnする。
     * 例外　空白の場合、9999を送信する。
     * @param item
     * @return
     */
    private int setTypeMethod(String item){
        if(item == "ジャケット"){
            TypeNo = 0;
        }else if(item == "ジーンズ"){
            TypeNo = 1;
        }else if(item == "チノパン"){
            TypeNo = 2;
        }else if(item == "セーター"){
            TypeNo = 3;
        }else if(item == "タートルネック"){
            TypeNo = 4;
        }else if(item == "スカート"){
            TypeNo = 5;
        }else if(item == "test1"){
            TypeNo = 6;
        }else if(item == "test2"){
            TypeNo = 7;
        }else if(item == "test3"){
            TypeNo = 8;
        }else if(item == "test4"){
            TypeNo = 9;
        }else {
            TypeNo = 9999;
        }
        return TypeNo;
    }

    /**
     * カテゴリを設定する。
     * @param item
     * @return
     */
    private int setCategoryMethod(String item){
        if(item == "フォーマル"){
            CategoryNo = 0;
        }else if(item == "カジュアル"){
            CategoryNo = 1;
        }else{
            CategoryNo = 9999;
        }
         return CategoryNo;
    }

}