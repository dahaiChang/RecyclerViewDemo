package com.cyh.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyh.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.VH> {

    private RecyclerView mRecyclerView;
    private Context mContext;
    private List<String> dataSource;

    public void setDataSource(List<String> dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    //传递上下文
    public MyRecyclerViewAdapter(Context context,RecyclerView recyclerView){
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        dataSource = new ArrayList<>();
    }
    /**创建并返回ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false));
    }

    /**
     *通过ViewHolder绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.iv.setImageResource(getIcon(position));
        holder.tv.setText(dataSource.get(position));

        /**
         * 只在瀑布流布局中使用随机高度
         */
        if (mRecyclerView.getLayoutManager().getClass() == StaggeredGridLayoutManager.class){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getRandomHeight());
            holder.tv.setLayoutParams(params);
        }else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.tv.setLayoutParams(params);
        }
    }

    private int getIcon(int position) {
        switch (position % 5){
            case 0:
                return R.mipmap.a;
            case 1:
                return R.mipmap.b;
            case 2:
                return R.mipmap.c;
            case 3:
                return R.mipmap.d;
            case 4:
                return R.mipmap.e;
        }
        return 0;
    }

    /**
     * 返回RecyclerView中的itemView数量
     * @return
     */
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    //内部类ViewHolder，与Adapter建立连接
    //初始化item布局
    class VH extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;

        public VH(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    /**
     * 返回不同高度的itemView
     * @return
     */
    private int getRandomHeight(){
        return (int) (Math.random()*1000);
    }

}
