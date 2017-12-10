package com.project.roy.qiscusinterview;

import android.content.res.ColorStateList;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.roy.qiscusinterview.Adapter.ViewPagerAdapter;
import com.project.roy.qiscusinterview.Fragment.FragmentAddProduct;
import com.project.roy.qiscusinterview.Fragment.FragmentGetProduct;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView tvNama, tvEmail;
    private ImageView ivProfil;
    private TabLayout tabLayout = null;
    public static String STR_GET_FRAGMENT = "PRODUCT";
    public static String STR_ADD_FRAGMENT = "ADD PRODUCT";
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        initTab();
    }

    private void initTab() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorIndivateTab));
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.colorIndivateTab)));
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabTitle();
    }

    private void setupTabTitle() {
        tabLayout.getTabAt(0).setText("Product");
        tabLayout.getTabAt(1).setText("Add Product");
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentGetProduct(), STR_GET_FRAGMENT);
        adapter.addFragment(new FragmentAddProduct(), STR_ADD_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }
}
