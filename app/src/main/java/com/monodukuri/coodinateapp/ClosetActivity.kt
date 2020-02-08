package com.monodukuri.coodinateapp


import android.os.Bundle
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
    }
}