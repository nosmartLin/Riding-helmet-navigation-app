package com.example.gaodemapdemo.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.amap.api.services.route.RideStep;
import com.amap.api.services.route.WalkStep;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.util.MapUtil;

import java.util.List;

/**
 * 骑行段列表适配器
 *
 * @author llw
 * @date 2021/2/23 10:25
 */
public class RideSegmentListAdapter extends BaseQuickAdapter<RideStep, BaseViewHolder> {
    private List<RideStep> mItemList;
    RideStep ride = new RideStep();
    RideStep ride1 = new RideStep();
    public RideSegmentListAdapter(int layoutResId, @Nullable List<RideStep> data) {
        super(layoutResId, data);
        mItemList = data;
        //给起点和终点腾出位置。
        mItemList.add(0,ride);
        mItemList.add(mItemList.size(),ride1);
    }

    @Override
    protected void convert(BaseViewHolder helper, RideStep item) {
        TextView lineName = helper.getView(R.id.bus_line_name);
        ImageView dirIcon = helper.getView(R.id.bus_dir_icon);
        ImageView dirUp = helper.getView(R.id.bus_dir_icon_up);
        ImageView dirDown = helper.getView(R.id.bus_dir_icon_down);
        ImageView splitLine = helper.getView(R.id.bus_seg_split_line);
        int position = getItemPosition(item);
        if (position == 0) {
            dirIcon.setImageResource(R.drawable.dir_start);
            lineName.setText("出发");
            dirUp.setVisibility(View.INVISIBLE);
            dirDown.setVisibility(View.VISIBLE);
            splitLine.setVisibility(View.INVISIBLE);
        } else if (position == mItemList.size() - 1) {
            dirIcon.setImageResource(R.drawable.dir_end);
            lineName.setText("到达终点");
            dirUp.setVisibility(View.VISIBLE);
            dirDown.setVisibility(View.INVISIBLE);
        } else {
            splitLine.setVisibility(View.VISIBLE);
            dirUp.setVisibility(View.VISIBLE);
            dirDown.setVisibility(View.VISIBLE);
            String actionName = item.getAction();
            int resID = MapUtil.getWalkActionID(actionName);
            dirIcon.setImageResource(resID);
            lineName.setText(item.getInstruction());
        }
    }
}