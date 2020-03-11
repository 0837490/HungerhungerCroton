package com.himanshu.hungercroton.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.adapter.SettingAdapter;
import com.himanshu.hungercroton.helper.GridSpacing;
import com.himanshu.hungercroton.helper.InitializeList;
import com.himanshu.hungercroton.models.SettingModel;
import com.himanshu.hungercroton.untils.FuncsVars;

import java.util.ArrayList;
import java.util.List;

import static com.smarteist.autoimageslider.IndicatorView.utils.DensityUtils.dpToPx;

public class ProfileActivity extends AppCompatActivity {

    private ImageView ivuserimage;
    private TextView txusername;
    private TextView txemail;
    private TextView txphone;

    private RecyclerView recyclerView;

    private static void btnData(RecyclerView recyclerView, List<SettingModel> albumList) {

        int[] covers = new int[]{R.drawable.right};

        SettingModel a = new SettingModel("0", "Notification",  covers[0],"0");
        albumList.add(a);
        a = new SettingModel("1", "Minimum Order", covers[0],"1");
        albumList.add(a);
        a = new SettingModel("2", "Set Delivery Area's", covers[0],"2");
        albumList.add(a);
        a = new SettingModel("3", "Privacy Policy",  covers[0],"2");
        albumList.add(a);

        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        inIt();
        setFont();
    }

    private void inIt() {

        ActionBar actionBar = getSupportActionBar();
        InitializeList.baseInitialize(actionBar);
        ivuserimage=findViewById(R.id.ivuserimage);
        txusername=findViewById(R.id.txusername);
        txemail=findViewById(R.id.txemail);
        txphone=findViewById(R.id.txphone);

        List<SettingModel> albumList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacing.GridSpacingItemDecoration(1, dpToPx(0), false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new SettingAdapter(this, albumList));


        btnData(recyclerView, albumList);



    }

    private void setFont() {
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txusername);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, txemail);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, txphone);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
