package com.himanshu.hungercroton.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.models.SettingModel;
import com.himanshu.hungercroton.untils.FuncsVars;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.MyViewHolder> {

    private Context mContext;
    private List<SettingModel> albumList;

    public SettingAdapter(Context mContext, List<SettingModel> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public SettingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.setting_layout, parent, false);

        return new SettingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SettingAdapter.MyViewHolder holder, int position) {

        SettingModel settingModel = albumList.get(position);


        try {
            holder.txname.setText(settingModel.getName().trim());
            if (settingModel.getStatus() == "0") {

                holder.txlinerlayout.setClickable(false);
                holder.checkbox.setVisibility(View.VISIBLE);
                holder.linear.setVisibility(View.GONE);
                holder.image.setVisibility(View.GONE);
            }else if (settingModel.getStatus()=="1"){
                holder.txlinerlayout.setClickable(false);
                holder.checkbox.setVisibility(View.GONE);
                holder.linear.setVisibility(View.VISIBLE);
                holder.image.setVisibility(View.GONE);
            }else if (settingModel.getStatus()=="2"){
                holder.txlinerlayout.setClickable(false);
                holder.checkbox.setVisibility(View.GONE);
                holder.linear.setVisibility(View.GONE);
                holder.image.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(settingModel.getImage()).into(holder.image);
            }


        } catch (NullPointerException e) {
            Log.d("error", String.valueOf(e));
        }
        holder.txlinerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent;
//                switch (buttonModel.getId()) {
//
//                    case "0":
//                        intent = new Intent(mContext, ProfileActivity.class);
//                        mContext.startActivity(intent);
//                        break;
//
//                    case "1":
//                        intent = new Intent(mContext, OrderHistoryActivity.class);
//                        mContext.startActivity(intent);
//                        break;
//
//                    case "2":
//                        intent = new Intent(mContext, DeliveryAddressActivity.class);
//                        mContext.startActivity(intent);
//                        break;
//
//                    case "3":
//                        intent = new Intent(mContext, AboutUsActivity.class);
//                        mContext.startActivity(intent);
//                        break;
//
//                    case "4":
//                        intent = new Intent(mContext, SupportCenterActivity.class);
//                        mContext.startActivity(intent);
//                        break;
//
//                    default:
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView txname;
        public FrameLayout txlinerlayout;
        public CheckBox checkbox;
        public LinearLayout linear;

        public MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            txname = view.findViewById(R.id.txname);
            txlinerlayout = view.findViewById(R.id.txlinerlayout);
            linear = view.findViewById(R.id.linear);
            checkbox = view.findViewById(R.id.checkbox);
            setFont();
        }

        private void setFont() {
            FuncsVars.setTypeface(mContext, FuncsVars.Fonts.PTSANS_SEMIBOLD, txname);
        }
    }
}