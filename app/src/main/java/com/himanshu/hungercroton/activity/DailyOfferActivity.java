package com.himanshu.hungercroton.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.adapter.DailyOfferAdapter;
import com.himanshu.hungercroton.helper.GridSpacing;
import com.himanshu.hungercroton.helper.InitializeList;
import com.himanshu.hungercroton.models.DailyOfferModel;

import java.util.ArrayList;
import java.util.List;

import static com.smarteist.autoimageslider.IndicatorView.utils.DensityUtils.dpToPx;

public class DailyOfferActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_offer);

        inIt();
    }

    private void inIt() {
        ActionBar actionBar = getSupportActionBar();
        InitializeList.baseInitialize(actionBar);
        List<DailyOfferModel> albumList = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.addItemDecoration(new GridSpacing.GridSpacingItemDecoration(10, dpToPx(10), false));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(new DailyOfferAdapter(this, albumList));

        btnData(recyclerview, albumList);
    }

    private void btnData(RecyclerView recyclerView, List<DailyOfferModel> albumList) {
        int[] covers = new int[]{
                R.drawable.ic_user_circle};

        DailyOfferModel a = new DailyOfferModel("0", covers[0], "Honey Chili Patato", "2 Naan with butter , 2 Subji , Salad, Juice", "$120", "$115", true);
        albumList.add(a);

        a = new DailyOfferModel("1", covers[0], "Chease Naal with Gravy", "2 Naan with butter , 2 Subji , Salad, Juice", "$250", "$200", false);
        albumList.add(a);

        a = new DailyOfferModel("2", covers[0], "Rasmalai", "2 Naan with butter , 2 Subji , Salad, Juice", "$84", "$77", true);
        albumList.add(a);

        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}