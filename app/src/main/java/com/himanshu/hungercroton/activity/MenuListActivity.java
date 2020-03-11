package com.himanshu.hungercroton.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.adapter.MenuListAdapter;
import com.himanshu.hungercroton.helper.GridSpacing;
import com.himanshu.hungercroton.helper.InitializeList;
import com.himanshu.hungercroton.helper.SwipeController;
import com.himanshu.hungercroton.helper.SwipeControllerActions;
import com.himanshu.hungercroton.models.MenuListModel;

import java.util.ArrayList;
import java.util.List;

import static com.smarteist.autoimageslider.IndicatorView.utils.DensityUtils.dpToPx;

public class MenuListActivity extends AppCompatActivity {

    SwipeController swipeController = null;
    MenuListAdapter menuListAdapter;
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_list);
        inIt();

    }

    private void inIt() {
        ActionBar actionBar = getSupportActionBar();
        InitializeList.baseInitialize(actionBar);
        List<MenuListModel> albumList = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.addItemDecoration(new GridSpacing.GridSpacingItemDecoration(1, dpToPx(0), false));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(new MenuListAdapter(this,albumList));
        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {

            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerview);

        recyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
        btnData(recyclerview, albumList);
    }

    private void btnData(RecyclerView recyclerview, List<MenuListModel> albumList) {
        int[] covers = new int[]{R.drawable.ab};

        MenuListModel a = new MenuListModel("0", "Snacks", covers[0]);
        albumList.add(a);
        a = new MenuListModel("1", "Chinese", covers[0]);
        albumList.add(a);
        a = new MenuListModel("2", "Specail Meal", covers[0]);
        albumList.add(a);
        a = new MenuListModel("3", "Dessert", covers[0]);
        albumList.add(a);

        recyclerview.setVisibility(View.VISIBLE);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
