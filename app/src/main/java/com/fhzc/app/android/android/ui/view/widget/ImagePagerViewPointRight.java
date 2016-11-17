package com.fhzc.app.android.android.ui.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fhzc.app.android.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 展示图片的容日
 */
public class ImagePagerViewPointRight extends RelativeLayout implements Handler.Callback {

    // 使用universal-image-loader插件读取网络图片，需要工程导入universal-image-loader-1.8.6-with-sources.jar


    //轮播图图片数量
    private final static int IMAGE_COUNT = 5;
    //自动轮播的时间间隔
    private final static int TIME_INTERVAL = 5;
    //自动轮播启用开关
    private final static boolean isAutoPlay = true;
    OnItemClickLisener onItemClickLisener;
    //自定义轮播图的资源
    private String[] imageUrls;
    //放轮播图片的ImageView 的list
    private List<ImageView> imageViewsList;
    //放圆点的View的list
    private List<View> dotViewsList;
    private List<TextView> textViewsList;
    private ViewPager viewPager;
    //当前轮播页
    public int currentItem = 0;
    //定时任务
    private ScheduledExecutorService scheduledExecutorService;
    private Context context;
    //Handler
    private Handler handler;

    public ImagePagerViewPointRight(Context context) {
        this(context, null);
    }

    public ImagePagerViewPointRight(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImagePagerViewPointRight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        handler = new Handler(this);
    }

    public void initData(List<String> paths) {
        imageUrls = new String[paths.size()];
        paths.toArray(imageUrls);


        initData();
        initUI(context);
        if (isAutoPlay) {
            startPlay();
        }
    }
    List<String> textpath=new ArrayList<>();
    public void initData(List<String> paths,List<String> textpaths) {
        imageUrls = new String[paths.size()];
        paths.toArray(imageUrls);
        textpath=textpaths;

        initData();
        initUI(context);
        if (isAutoPlay) {
            startPlay();
        }
    }

    public void initData(String[] paths) {
        this.imageUrls = paths;

        initData();
        initUI(context);
        if (isAutoPlay) {
            startPlay();
        }
    }

    /**
     * 开始轮播图切换
     */
    public void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, TIME_INTERVAL, TimeUnit.SECONDS);
    }
    public void stopService(){
        if (scheduledExecutorService==null)
            return;
        scheduledExecutorService.shutdown();
    }

    /**
     * 停止轮播图切换
     */
    public void stopPlay() {
        scheduledExecutorService.shutdownNow();
    }

    /**
     * 初始化相关Data
     */
    private void initData() {
        imageViewsList = new ArrayList<>();
        dotViewsList = new ArrayList<>();
        textViewsList=new ArrayList<>();
        // 一步任务获取图片
    }

    /**
     * 初始化Views等UI
     */
    private void initUI(Context context) {
        if (imageUrls == null || imageUrls.length == 0)
            return;
        LayoutInflater.from(context).inflate(R.layout.view_image_shower_point_right, this, true);
        LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();

        final TextView tv=(TextView)findViewById(R.id.textViewtitle);
        tv.setText(textpath.get(0));
        // 热点个数与图片特殊相等
        for (int i = 0; i < imageUrls.length; i++) {
            ImageView view = new ImageView(context);

            view.setOnClickListener(new OnclickLis(i));
            view.setTag(R.id.tag_position, imageUrls[i]);
            if (i == 0)//给一个默认图
//                view.setBackgroundResource(R.drawable.black);
                view.setBackgroundResource(R.color.gray);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewsList.add(view);


            TextView textview=new TextView(context);
            textview.setTag(R.id.textViewtitle,textpath.get(i));
            textViewsList.add(textview);

            ImageView dotView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 4;
            params.rightMargin = 4;
            dotLayout.addView(dotView, params);
            dotViewsList.add(dotView);
        }
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean autoPlay = false;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                for (int i = 0; i < dotViewsList.size(); i++) {
                    if (i == position) {
                        (dotViewsList.get(position)).setBackgroundResource(R.drawable.detailselect);
                    } else {
                        (dotViewsList.get(i)).setBackgroundResource(R.drawable.detailunselect);
                    }
                }
                tv.setText(textpath.get(position));
            }

            @Override
            public synchronized void onPageScrollStateChanged(int state) {
                switch (state) {
                    case 1:// 手势滑动，空闲中
                        autoPlay = false;
                        break;
                    case 2:// 界面切换中
                        autoPlay = true;
                        break;
                    case 0:// 滑动结束，即切换完毕或者加载完毕
                        // 当前为最后一张，此时从右向左滑，则切换到第一张
                        if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !autoPlay) {
                            viewPager.setCurrentItem(0);
                        }
                        // 当前为第一张，此时从左向右滑，则切换到最后一张
                        else if (viewPager.getCurrentItem() == 0 && !autoPlay) {
                            viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                        }
                        break;
                }
            }
        });

    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        boolean autoPlay = false;

        @Override
        public synchronized void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    autoPlay = false;
                    break;
                case 2:// 界面切换中
                    autoPlay = true;
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !autoPlay) {
                        viewPager.setCurrentItem(0);
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (viewPager.getCurrentItem() == 0 && !autoPlay) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public synchronized void onPageSelected(int pos) {
            currentItem = pos;
            for (int i = 0; i < dotViewsList.size(); i++) {
                if (i == pos) {
                    (dotViewsList.get(pos)).setBackgroundResource(R.drawable.detailselect);
                } else {
                    (dotViewsList.get(i)).setBackgroundResource(R.drawable.detailunselect);
                }
            }
        }

    }
    public void setOnItemClickLisener(OnItemClickLisener onItemClickLisener) {
        this.onItemClickLisener = onItemClickLisener;
    }

    /**
     * 销毁ImageView资源，回收内存
     */
    public void destoryBitmaps() {

        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        viewPager.setCurrentItem(currentItem);
        return false;
    }

    public interface OnItemClickLisener {
        void onItemClick(View view, int position);
    }

    private class OnclickLis implements OnClickListener {
        int index;

        public OnclickLis(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickLisener != null) {
                onItemClickLisener.onItemClick(v, index);
            }
        }
    }

    /**
     * 填充ViewPager的页面适配器
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViewsList.get(position);
//            ImageLoaderManager.getInstance().disPlayImage(imageView.getTag() + "", null, imageView);
            Glide.with(context)
                    .load(imageView.getTag(R.id.tag_position))
                    .error(R.drawable.default_pictures)
                    .into(imageView);

//            Glide.with(context).load(imageView.getTag(R.id.tag_position)).centerCrop().into(imageView);
//            imageView.setImageResource(R.drawable.test_pager_big);
            container.addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(ViewGroup arg0) {

        }

        @Override
        public void finishUpdate(ViewGroup arg0) {

        }

    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }

    }


}



