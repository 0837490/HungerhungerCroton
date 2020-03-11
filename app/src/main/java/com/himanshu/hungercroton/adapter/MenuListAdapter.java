package com.himanshu.hungercroton.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.models.MenuListModel;
import com.himanshu.hungercroton.untils.FuncsVars;

import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MyViewHolder> {

    private Context mContext;
    private List<MenuListModel> albumList;

    public MenuListAdapter(Context mContext, List<MenuListModel> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MenuListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_layout, parent, false);

        return new MenuListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MenuListAdapter.MyViewHolder holder, int position) {

        MenuListModel menuListModel = albumList.get(position);

        holder.txproductname.setText(menuListModel.getName());


    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView txproductname;
        public LinearLayout linear;


        public MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            txproductname = view.findViewById(R.id.txproductname);
            linear = view.findViewById(R.id.linear);

            setFont();
        }

        private void setFont() {
            FuncsVars.setTypeface(mContext, FuncsVars.Fonts.PTSANS_SEMIBOLD, txproductname);
        }
    }
}