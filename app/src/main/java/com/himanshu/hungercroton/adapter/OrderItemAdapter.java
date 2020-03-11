package com.himanshu.hungercroton.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.models.OrderNameListModel;
import com.himanshu.hungercroton.untils.FuncsVars;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemAdapterViewHolder> {


    private Context context;
    private List<OrderNameListModel> orderListModels;

    public OrderItemAdapter(Context context, List<OrderNameListModel> orderListModels) {
        this.context = context;
        this.orderListModels = orderListModels;
    }

    @NonNull
    @Override
    public OrderItemAdapter.OrderItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.order_item, parent, false);
        return new OrderItemAdapter.OrderItemAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemAdapter.OrderItemAdapterViewHolder holder, int position) {

        OrderNameListModel orderNameListModel = orderListModels.get(position);

        holder.txname.setText(orderNameListModel.getProductname());
        holder.txprice.setText(orderNameListModel.getProductprice());
        holder.txqty.setText("Qty : "+orderNameListModel.getProductqty());

        Toast.makeText(context,orderNameListModel.getProductname(),Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return orderListModels.size();
    }

    public class OrderItemAdapterViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.txname)
        TextView txname;
        @BindView(R.id.txqty)
        TextView txqty;
        @BindView(R.id.txprice)
        TextView txprice;


        public OrderItemAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            setFont();

        }

        private void setFont() {


            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txname);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txqty);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txprice);

        }

    }
}