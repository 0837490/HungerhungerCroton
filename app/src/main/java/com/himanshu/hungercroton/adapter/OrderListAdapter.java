package com.himanshu.hungercroton.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.activity.OrderViewActivity;
import com.himanshu.hungercroton.models.OrderListModel;
import com.himanshu.hungercroton.models.OrderNameListModel;
import com.himanshu.hungercroton.untils.FuncsVars;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListAdapterViewHolder> {


    private Context context;
    private List<OrderListModel> orderListModels;

    public OrderListAdapter(Context context, List<OrderListModel> orderListModels) {
        this.context = context;
        this.orderListModels = orderListModels;
    }

    @NonNull
    @Override
    public OrderListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.order_list_item, parent, false);
        return new OrderListAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapterViewHolder holder, int position) {
        OrderListModel buttonModel = orderListModels.get(position);

        holder.txpickuptiming.setText(buttonModel.getPickuptime());
        holder.txorderid.setText("Order Id "+buttonModel.getOrderid());
        holder.txamout.setText("Price "+buttonModel.getTotalprice());
        holder.txridername.setText(buttonModel.getRidername());
        holder.txmessage.setText(buttonModel.getMessage());

        String status=buttonModel.getStatus();

        List<OrderNameListModel> orderNameListModels=new ArrayList<>();

        OrderNameListModel a = new OrderNameListModel("0","Daal Makhni" ,"2","$120");
        orderNameListModels.add(a);
        a = new OrderNameListModel("1","Simple Thali-Veg" ,"2","$150");
        orderNameListModels.add(a);
        a = new OrderNameListModel("3","Simple Thali-Veg" ,"2","$150");
        orderNameListModels.add(a);

        holder.orderlist.setVisibility(View.VISIBLE);
        holder.orderlist.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        holder.orderlist.setAdapter(new OrderItemAdapter(context, orderNameListModels));

        if (status=="0"){
            holder.orderstatus.setVisibility(View.VISIBLE);
            holder.orderstatus1.setVisibility(View.GONE);
        }else if (status=="1"){
            holder.orderstatus.setVisibility(View.GONE);
            holder.orderstatus1.setVisibility(View.VISIBLE);

        }else if (status=="2"){
            holder.orderstatus.setVisibility(View.GONE);
            holder.spinnerstatus.setVisibility(View.GONE);
            holder.orderstatus1.setVisibility(View.VISIBLE);
            holder.tvstatus.setText("Order Staus : Order Delivered");
            holder.tvstatus.setTextColor(context.getResources().getColor(R.color.stpi_default_primary_color));
        }else if (status=="3"){
            holder.orderstatus.setVisibility(View.GONE);
            holder.spinnerstatus.setVisibility(View.GONE);
            holder.orderstatus1.setVisibility(View.VISIBLE);
            holder.tvstatus.setText("Order Staus : Order Cancelled");
            holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_red));
        }
        List<String> categories = new ArrayList<String>();
        categories.add("Order Prepairing");
        categories.add("On the way");
        categories.add("Order Dispached");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerstatus.setAdapter(dataAdapter);
        holder.spinnerstatus.setGravity(10);
        holder.spinnerstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.txviewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OrderViewActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderListModels.size();
    }

    public class OrderListAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.txridername)
        TextView txridername;
        @BindView(R.id.txpickuptiming)
        TextView txpickuptiming;
        @BindView(R.id.txorderid)
        TextView txorderid;
        @BindView(R.id.txamout)
        TextView txamout;
        @BindView(R.id.orderlist)
        RecyclerView orderlist;
        @BindView(R.id.txmessage)
        TextView txmessage;
        @BindView(R.id.txviewdetails)
        TextView txviewdetails;
        @BindView(R.id.btncancel)
        Button btncancel;
        @BindView(R.id.btnaccept)
        Button btnaccept;

        @BindView(R.id.spinnerstatus)
        Spinner spinnerstatus;
        @BindView(R.id.tvstatus)
        TextView tvstatus;

        @BindView(R.id.orderstatus)
        RelativeLayout orderstatus;

        @BindView(R.id.orderstatus1)
        RelativeLayout orderstatus1;

        public OrderListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            setFont();

        }


        private void setFont() {

            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txpickuptiming);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txorderid);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txamout);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_BOLD, tvstatus);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_BOLD, txridername);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txmessage);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txviewdetails);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_BOLD, btncancel);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_BOLD, btnaccept);

        }

    }
}

