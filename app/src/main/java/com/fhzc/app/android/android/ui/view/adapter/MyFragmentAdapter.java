package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.models.MyProductModel;
import com.fhzc.app.android.models.SuggestAssetsModel;
import com.fhzc.app.android.utils.android.MathUtil;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016/7/25.
 */
public class MyFragmentAdapter extends PagerAdapter {

    private Context mContext;
    private View view1;
    private View view2;

    public MyFragmentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position == 0 && view1 == null) {
            view1 = LayoutInflater.from(mContext).inflate(R.layout.view_pie_chart, null);
            container.addView(view1);
            return view1;
        }
        if (position == 1 && view2 == null) {
            view2 = LayoutInflater.from(mContext).inflate(R.layout.view_pie_chart, null);
            container.addView(view2);
            return view2;
        }

        return view1;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void setMyProductData(List<MyProductModel> model, int total) {
        //持仓饼状图
        PieChart chart1 = (PieChart) view1.findViewById(R.id.mPieChart);
        showMyProductChart(chart1, getMyProductPieData(model, total));

    }

    public void setSuggestData(List<SuggestAssetsModel> model) {
        //资产配置饼状图
        PieChart chart2 = (PieChart) view2.findViewById(R.id.mPieChart);
        TextView textView = (TextView) view2.findViewById(R.id.proportionText);
        textView.setText("推荐比例");
        showSuggestChart(chart2, model);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    private void showMyProductChart(PieChart pieChart, PieData pieData) {
        pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(40f);  //半径
        pieChart.setTransparentCircleRadius(40f); // 半透明圈

        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字

        pieChart.setDrawHoleEnabled(true);
        pieChart.setDescription("");
        pieChart.setRotationAngle(90); // 初始旋转角度
        pieChart.setNoDataText("");
        pieChart.setUsePercentValues(true);  //显示成百分比

        pieData.setValueTextSize(13f);
        pieData.setValueTextColor(Color.WHITE);
        pieData.setValueFormatter(new PercentFormatter());
        pieChart.setCenterText("");  //饼状图中间的文字
        pieChart.setEnabled(false);
        pieChart.setOnTouchListener(null);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setData(pieData);
        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setEnabled(false);

    }

    private void showSuggestChart(PieChart pieChart, List<SuggestAssetsModel> list) {
        LinearLayout container = (LinearLayout)view2.findViewById(R.id.containerwe);
        container.removeAllViews();
        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容
        for (int i = 0; i < list.size(); i++) {
            xValues.add("");  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        }
        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        ArrayList<Integer> colors = new ArrayList<Integer>();

        String[] strings = mContext.getResources().getStringArray(R.array.chartColor);
        // 饼图颜色
        for (int i = 0; i < list.size(); i++) {
            yValues.add(new Entry(list.get(i).getProportion(), i));
            colors.add(Color.parseColor(strings[i]));
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_legend,
                    null, false);
            View colorview=view.findViewById(R.id.colorView);
            TextView productName=(TextView) view.findViewById(R.id.productName);
            colorview.setBackgroundColor(Color.parseColor(strings[i]));
            productName.setText(list.get(i).getTypeName());
            container.addView(view);
        }

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "");
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离
        pieDataSet.setColors(colors);
        pieDataSet.setSelectionShift(0); // 选中态多出的长度
        PieData pieData = new PieData(xValues, pieDataSet);
        pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(40f);  //半径
        pieChart.setTransparentCircleRadius(40f); // 半透明圈

        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字

        pieChart.setDrawHoleEnabled(true);
        pieChart.setDescription("");
        pieChart.setRotationAngle(90); // 初始旋转角度
        pieChart.setNoDataText("");
        pieChart.setUsePercentValues(true);  //显示成百分比

        pieData.setValueTextSize(13f);
        pieData.setValueTextColor(Color.WHITE);
        pieData.setValueFormatter(new PercentFormatter());
        pieChart.setCenterText("");  //饼状图中间的文字
        pieChart.setEnabled(false);
        pieChart.setOnTouchListener(null);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setData(pieData);
        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setEnabled(false);
    }


    private PieData getMyProductPieData(List<MyProductModel> list, int total) {
        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容
        for (int i = 0; i < list.size(); i++) {
            xValues.add("");  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        }
        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        ArrayList<Integer> colors = new ArrayList<Integer>();
        String[] strings = mContext.getResources().getStringArray(R.array.chartColor);
        // 饼图颜色
        for (int i = 0; i < list.size(); i++) {
            yValues.add(new Entry(MathUtil.getPercent(list.get(i).getAmount(), total), i));
            colors.add(Color.parseColor(strings[i]));
        }
        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "");
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离
        pieDataSet.setColors(colors);
        pieDataSet.setSelectionShift(0); // 选中态多出的长度
        return new PieData(xValues, pieDataSet);
    }

}
