package com.himanshu.hungercroton.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.adapter.OrderListAdapter;
import com.himanshu.hungercroton.helper.GridSpacing;
import com.himanshu.hungercroton.models.OrderListModel;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import static com.smarteist.autoimageslider.IndicatorView.utils.DensityUtils.dpToPx;

public class HomeFragment extends Fragment {
    LineChartView lineChartView;
    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
            "Oct", "Nov", "Dec"};
    int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};
    View view;
    private RecyclerView newOrderRecyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_home,container,false);

        inIt();
        return view;
    }

    private void inIt() {

        List<OrderListModel> albumList =new ArrayList<>();
        newOrderRecyclerview=view.findViewById(R.id.recyclerviewneworder);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        newOrderRecyclerview.setLayoutManager(mLayoutManager);
        newOrderRecyclerview.addItemDecoration(new GridSpacing.GridSpacingItemDecoration(10, dpToPx(10), false));
        newOrderRecyclerview.setItemAnimator(new DefaultItemAnimator());
        newOrderRecyclerview.setAdapter(new OrderListAdapter(getContext(), albumList));

        btnData(newOrderRecyclerview,albumList);

        lineChartView = view.findViewById(R.id.chart);
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#E25409"));

        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(14);
        axis.setTextColor(Color.parseColor("#E25409"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setTextColor(Color.parseColor("#E25409"));
        yAxis.setTextSize(14);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = 100;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);

    }

    private void btnData(RecyclerView newOrderRecyclerview, List<OrderListModel> albumList) {
        int[] covers = new int[]{
                R.drawable.ic_user_circle};

        OrderListModel a = new OrderListModel("0",covers[0],"sohan","Today 10:12","#21541","125","Message : Hi please pack green salid in my order","0");
        albumList.add(a);

        a = new OrderListModel("1",covers[0],"Mohan","Today 11:12","#2514","200","Message : Hi please pack green salid in my order","0");
        albumList.add(a);

        a = new OrderListModel("2",covers[0],"Deepak","Today 12:30","#21541","500","Message : Hi please pack green salid in my order","0");
        albumList.add(a);

        newOrderRecyclerview.setVisibility(View.VISIBLE);
    }

}
