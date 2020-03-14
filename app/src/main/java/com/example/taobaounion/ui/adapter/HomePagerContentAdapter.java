package com.example.taobaounion.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taobaounion.R;
import com.example.taobaounion.model.domain.HomePagerContent;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePagerContentAdapter extends RecyclerView.Adapter<HomePagerContentAdapter.InnerHolder> {

    List<HomePagerContent.DataBean> mData = new ArrayList<>();
    private OnListeItemClickListener mItemClickListener = null;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_pager_content, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        HomePagerContent.DataBean dataBean = mData.get(position);
        // 设置数据
        holder.setData(dataBean);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePagerContent.DataBean item = mData.get(position);
                mItemClickListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setmData(List<HomePagerContent.DataBean> contents) {
        mData.clear();
        mData.addAll(contents);
        notifyDataSetChanged();
    }

    public void addData(List<HomePagerContent.DataBean> contents) {
        // 添加之前拿到之前的size
        int olderSize = mData.size();
        mData.addAll(contents);
        // 更新UI
        notifyItemRangeChanged(olderSize, contents.size());
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.goods_cover)
        protected ImageView cover;

        @BindView(R.id.goods_title)
        protected TextView title;

        @BindView(R.id.goods_off_price)
        protected TextView offPriceTv;

        @BindView(R.id.goods_after_off_price)
        protected TextView finalPriceTv;

        @BindView(R.id.goods_original_price)
        protected TextView originalPriceTv;

        @BindView(R.id.goods_sell_count)
        protected TextView sellCountTv;


        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(HomePagerContent.DataBean dataBean) {
            Context context = itemView.getContext();
            title.setText(dataBean.getTitle());

            ViewGroup.LayoutParams layoutParams = cover.getLayoutParams();
            int width = layoutParams.width;
            int height = layoutParams.height;

            int coverSize = (width > height ? width : height) / 2;
            String coverPath = UrlUtils.getCoverPath(dataBean.getPict_url(), coverSize);

            Glide.with(context).load(coverPath).into(cover);

            long couponAmount = dataBean.getCoupon_amount();
            String finalPrice = dataBean.getZk_final_price();
            float resultPrice = Float.parseFloat(finalPrice) - couponAmount;
            long volume = dataBean.getVolume();

            offPriceTv.setText(String.format(context.getString(R.string.text_goods_off_price), couponAmount));
            finalPriceTv.setText(String.format("%.2f", resultPrice));
            originalPriceTv.setText(String.format(context.getString(R.string.text_goods_original_price), finalPrice));
            originalPriceTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            sellCountTv.setText(String.format(context.getString(R.string.text_goods_sell_count), volume));
        }
    }

    public void setOnListeItemClickListener(OnListeItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public interface OnListeItemClickListener {
        void onItemClick(HomePagerContent.DataBean item);
    }
}
