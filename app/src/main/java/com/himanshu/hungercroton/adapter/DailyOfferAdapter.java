package com.himanshu.hungercroton.adapter;

import android.app.Dialog;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.models.DailyOfferModel;
import com.himanshu.hungercroton.untils.FuncsVars;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyOfferAdapter extends RecyclerView.Adapter<DailyOfferAdapter.DailyOfferAdapterViewHolder> {


    private Context context;
    private List<DailyOfferModel> orderListModels;

    public DailyOfferAdapter(Context context, List<DailyOfferModel> orderListModels) {
        this.context = context;
        this.orderListModels = orderListModels;
    }

    @NonNull
    @Override
    public DailyOfferAdapter.DailyOfferAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.daily_offer_item, parent, false);
        return new DailyOfferAdapter.DailyOfferAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DailyOfferAdapter.DailyOfferAdapterViewHolder holder, int position) {

        DailyOfferModel dailyOfferModel = orderListModels.get(position);

        holder.productname.setText(dailyOfferModel.getProductname());
        holder.txprice.setText(dailyOfferModel.getProductprice());
        holder.switchButton.setChecked(dailyOfferModel.getaBoolean());
        holder.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    holder.switchButton.setChecked(false);
                } else if (isChecked == false) {
                    holder.switchButton.setChecked(true);
                }
            }
        });


        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.offer_custom_layout);

                TextView txprice,txproductname,txproductdetails;
                ImageView image;
                Button btnedit,btndelete;
                txprice = dialog.findViewById(R.id.txprice);
                txproductname = dialog.findViewById(R.id.txproductname);
                txproductdetails = dialog.findViewById(R.id.txproductdetails);
                image = dialog.findViewById(R.id.image);

                txprice.setText(dailyOfferModel.getProductprice());
                image.setImageResource(R.drawable.ab);

                btnedit = dialog.findViewById(R.id.btnedit);
                btndelete = dialog.findViewById(R.id.btndelete);


                FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txprice);
                FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_BOLD, txproductname);
                FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txproductdetails);
                FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_BOLD, btnedit);
                FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_BOLD, btndelete);

                btnedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        String firstWord = "$50";
        String secondWord = "$100!";
        ForegroundColorSpan redForegroundColorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.stpi_default_primary_color));
        SpannableStringBuilder ssb = new SpannableStringBuilder(firstWord);
        ssb.setSpan(redForegroundColorSpan, 0, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.append(" ");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        ssb.append(secondWord);
        ssb.setSpan(strikethroughSpan, ssb.length() - secondWord.length(), ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.txprice.setText(ssb, TextView.BufferType.EDITABLE);
    }

    @Override
    public int getItemCount() {
        return orderListModels.size();
    }

    public class DailyOfferAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.txproductdetails)
        TextView txproductdetails;
        @BindView(R.id.price)
        TextView txprice;
        @BindView(R.id.switchButton)
        SwitchCompat switchButton;

        @BindView(R.id.cardview)
        CardView cardview;
        public DailyOfferAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            setFont();

        }

        private void setFont() {


            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_BOLD, productname);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txproductdetails);
            FuncsVars.setTypeface(context, FuncsVars.Fonts.PTSANS_REGULAR, txprice);

        }

    }
}