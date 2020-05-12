package com.cyh.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.cyh.recyclerviewdemo.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView rv;
    private MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        adapter = new MyRecyclerViewAdapter(this,rv);

        //线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //横向排列ItemView
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //数据反向展示
        linearLayoutManager.setReverseLayout(true);

        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }

    /**
     * 添加数据
     * @param v
     */
    public void onAddDataClick(View v){
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            String s = "第" + i + "数据";
            data.add(s);
        }
        adapter.setDataSource(data);
    }

    public void onChangeLayoutClick(View v){
        //从线性布局切换为网格布局
        if (rv.getLayoutManager().getClass() == LinearLayoutManager.class){
            //网格布局
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);//2列
            rv.setLayoutManager(gridLayoutManager);
        }
        //网格布局切换为瀑布布局
        if (rv.getLayoutManager().getClass() == GridLayoutManager.class){
            //瀑布布局
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            rv.setLayoutManager(staggeredGridLayoutManager);
        }else {
            // 线性布局
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rv.setLayoutManager(linearLayoutManager);
        }
    }
}
