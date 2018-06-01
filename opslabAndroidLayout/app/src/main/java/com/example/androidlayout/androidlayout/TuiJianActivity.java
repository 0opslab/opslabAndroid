package com.example.androidlayout.androidlayout;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.Toast;

import java.util.ArrayList;

public class TuiJianActivity extends AppCompatActivity {

    private int[] imgResIDs = new int[]
            { R.drawable.tuijian_a, R.drawable.tuijian_b, R.drawable.tuijian_c,
                    R.drawable.tuijian_d, R.drawable.tuijian_e };
    private int[] radioButtonID = new int[]
            { R.id.tuijian_radio0, R.id.tuijian_radio1,
                    R.id.tuijian_radio2, R.id.tuijian_radio3, R.id.tuijian_radio4 };
    private ViewPager pager;
    private RadioGroup mGroup;
    private ArrayList<View> items = new ArrayList<View>();
    private Runnable runnable;
    private int mCurrentItem = 0;
    private int mItem;
    private Runnable mPagerAction;
    private boolean isFrist = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_jian);
        pager = (ViewPager) findViewById(R.id.tuijian_pager);
        mGroup = (RadioGroup) findViewById(R.id.tuijian_radioGroup1);
        initAllItems();
        pager.setAdapter(new PagerAdapter()
        {
            // ????
            @Override
            public Object instantiateItem(View container, int position)
            {
                View layout = items.get(position % items.size());
                pager.addView(layout);
                return layout;
            }

            // ????
            @Override
            public void destroyItem(View container, int position, Object object)
            {
                View layout = items.get(position % items.size());
                pager.removeView(layout);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1)
            {
                return arg0 == arg1;

            }

            @Override
            public int getCount()
            {
                return imgResIDs.length;
            }

        });
        pager.setOnPageChangeListener(new OnPageChangeListener()
        {

            @Override
            public void onPageSelected(final int arg0)
            {
                mCurrentItem = arg0 % items.size();
                pager.setCurrentItem(mCurrentItem);
                mGroup.check(radioButtonID[mCurrentItem]);
                items.get(arg0).findViewById(R.id.tuijian_header_img).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Toast.makeText(TuiJianActivity.this, arg0 + "", 1000).show();
                    }
                });
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int arg0)
            {
                // TODO Auto-generated method stub

            }
        });
        mPagerAction = new Runnable()
        {
            @Override
            public void run()
            {

                if (mItem != 0)
                {
                    if (isFrist == true)
                    {
                        mCurrentItem = 0;
                        isFrist = false;
                    }
                    else
                    {
                        if (mCurrentItem == items.size() - 1)
                        {
                            mCurrentItem = 0;
                        }
                        else
                        {
                            mCurrentItem++;
                        }
                    }

                    pager.setCurrentItem(mCurrentItem);
                    mGroup.check(radioButtonID[mCurrentItem]);

                }
                pager.postDelayed(mPagerAction, 2500);
            }
        };
        pager.postDelayed(mPagerAction, 100);
    }

    private void initAllItems()
    {
        // ?????Viewpager??????item
        for (int i = 0; i < imgResIDs.length; i++)
        {
            items.add(initPagerItem(imgResIDs[i]));
        }
        mItem = items.size();
    }

    private View initPagerItem(int resID)
    {
        View layout1 = getLayoutInflater().inflate(R.layout.tuijian_header, null);
        ImageView imageView1 = (ImageView) layout1.findViewById(R.id.tuijian_header_img);
        imageView1.setImageResource(resID);
        return layout1;
    }
}
