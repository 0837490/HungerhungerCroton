package com.himanshu.hungercroton.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.adapter.OrderListAdapter;
import com.himanshu.hungercroton.helper.GridSpacing;
import com.himanshu.hungercroton.helper.InitializeList;
import com.himanshu.hungercroton.models.OrderListModel;

import java.util.ArrayList;
import java.util.List;

import static com.smarteist.autoimageslider.IndicatorView.utils.DensityUtils.dpToPx;

public class ConfirmOrderActivity extends AppCompatActivity {
    private RecyclerView OrderRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        inIt();

    }

    private void inIt() {
        ActionBar actionBar = getSupportActionBar();
        InitializeList.baseInitialize(actionBar);
        List<OrderListModel> albumList = new ArrayList<>();
        OrderRecyclerview = findViewById(R.id.recyclerviewneworder);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        OrderRecyclerview.setLayoutManager(mLayoutManager);
        OrderRecyclerview.addItemDecoration(new GridSpacing.GridSpacingItemDecoration(10, dpToPx(10), false));
        OrderRecyclerview.setItemAnimator(new DefaultItemAnimator());
        OrderRecyclerview.setAdapter(new OrderListAdapter(this, albumList));

        btnData(OrderRecyclerview, albumList);
    }

    private void btnData(RecyclerView orderRecyclerview, List<OrderListModel> albumList) {
        int[] covers = new int[]{
                R.drawable.ic_user_circle};

        OrderListModel a = new OrderListModel("0", covers[0], "sohan", "Today 10:12", "#21541", "125", "Message : Hi please pack green salid in my order", "1");
        albumList.add(a);

        a = new OrderListModel("1", covers[0], "Mohan", "Today 11:12", "#2514", "200", "Message : Hi please pack green salid in my order", "1");
        albumList.add(a);

        a = new OrderListModel("2", covers[0], "Deepak", "Today 12:30", "#21541", "500", "Message : Hi please pack green salid in my order", "1");
        albumList.add(a);

        OrderRecyclerview.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
