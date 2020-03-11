package com.himanshu.hungercroton.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.adapter.OrderItemAdapter;
import com.himanshu.hungercroton.helper.InitializeList;
import com.himanshu.hungercroton.models.OrderNameListModel;
import com.himanshu.hungercroton.untils.FuncsVars;

import java.util.ArrayList;
import java.util.List;

public class OrderViewActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    private ImageView image;
    private ImageView ivphone;
    private ImageView ivemail;
    private ImageView ivnavigate;

    private TextView txridername;
    private TextView txpickuptiming;
    private TextView txorderid;
    private TextView txamout;
    private TextView txphone;
    private TextView call;
    private TextView txemail;
    private TextView etmail;
    private TextView txnavigate;
    private TextView etnavigate;
    private TextView txmessage;
    private TextView message;
    private TextView txitem;
    private TextView txqty;
    private TextView txprice;
    private TextView txprintinvoice;
    private TextView txsubtotal;
    private TextView txdeliveryfee;
    private TextView txservicecharge;
    private TextView txdiscount;
    private TextView txtotal;
    private TextView txstatus;
    private Button btncancel;

    private RecyclerView recyclerView;
    private Spinner spinnerstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view);
        inIt();
    }

    private void inIt() {

        ActionBar actionBar = getSupportActionBar();
        InitializeList.baseInitialize(actionBar);
        image=findViewById(R.id.image);
        ivphone=findViewById(R.id.ivphone);
        ivemail=findViewById(R.id.ivemail);
        ivnavigate=findViewById(R.id.ivnavigate);

        txridername=findViewById(R.id.txridername);
        txpickuptiming=findViewById(R.id.txpickuptiming);
        txorderid=findViewById(R.id.txorderid);
        txamout=findViewById(R.id.txamout);
        txphone=findViewById(R.id.txphone);
        call=findViewById(R.id.call);
        txemail=findViewById(R.id.txemail);
        etmail=findViewById(R.id.etmail);
        txnavigate=findViewById(R.id.txnavigate);
        etnavigate=findViewById(R.id.etnavigate);
        txmessage=findViewById(R.id.txmessage);
        message=findViewById(R.id.message);
        txitem=findViewById(R.id.txitem);
        txqty=findViewById(R.id.txqty);
        txprice=findViewById(R.id.txprice);
        txprintinvoice=findViewById(R.id.txprintinvoice);
        txsubtotal=findViewById(R.id.txsubtotal);
        txdeliveryfee=findViewById(R.id.txdeliveryfee);
        txservicecharge=findViewById(R.id.txservicecharge);
        txdiscount=findViewById(R.id.txdiscount);
        txtotal=findViewById(R.id.txtotal);
        txstatus=findViewById(R.id.txstatus);
        btncancel=findViewById(R.id.btncancel);


        recyclerView=findViewById(R.id.rectangle);
        spinnerstatus=findViewById(R.id.spinnerstatus);

        setRecyclerview();
        setSpinner();
        setFont();
    }

    private void setRecyclerview() {
        List<OrderNameListModel> orderNameListModels=new ArrayList<>();

        OrderNameListModel a = new OrderNameListModel("0","Daal Makhni" ,"2","$120");
        orderNameListModels.add(a);
        a = new OrderNameListModel("1","Simple Thali-Veg" ,"2","$150");
        orderNameListModels.add(a);
        a = new OrderNameListModel("3","Simple Thali-Veg" ,"2","$150");
        orderNameListModels.add(a);

        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new OrderItemAdapter(this, orderNameListModels));
    }

    private void setSpinner() {
        List<String> categories = new ArrayList<String>();
        categories.add("Order Prepairing");
        categories.add("On the way");
        categories.add("Order Dispached");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerstatus.setAdapter(dataAdapter);

    }

    private void setFont() {
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, txridername);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, txpickuptiming);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, txorderid);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, txamout);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txphone);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, call);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txemail);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, etmail);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txnavigate);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, etnavigate);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txmessage);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, message);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txitem);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txqty);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txprice);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txprintinvoice);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, txsubtotal);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, txdeliveryfee);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, txservicecharge);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, txdiscount);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txtotal);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txstatus);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, btncancel);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
