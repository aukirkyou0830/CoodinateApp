package com.monodukuri.coodinateapp


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_closet.*

class ClosetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_closet)

        /// 表示するテキスト配列を作る [テキスト0, テキスト1, ....]
        val list = Array<String>(10) {"テキスト$it"}
        val adapter = RecyclerAdapter(list)
        val layoutManager = LinearLayoutManager(this)

        // アダプターとレイアウトマネージャーをセット
        wearRecyclerView.layoutManager = layoutManager
        wearRecyclerView.adapter = adapter
        wearRecyclerView.setHasFixedSize(true)

        adapter.setOnItemClickListener(object:RecyclerAdapter.OnItemClickListener{
            override fun onItemClickListener(view: View, position: Int, clickedText: String) {
                toNextView(view)
            }
        })


    }
    fun toNextView(view: View){
        //Intentのインスタンスを作成
        val intent = Intent(this, closetDetail::class.java)
        //textは受け渡す変数
        val text = "1"
        //intent変数をつなげる(第一引数はキー，第二引数は渡したい変数)
        intent.putExtra("TEXT_KEY",text)
        //画面遷移を開始
        startActivity(intent)
    }
}
