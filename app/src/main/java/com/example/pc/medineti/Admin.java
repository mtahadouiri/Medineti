package com.example.pc.medineti;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.pc.medineti.Adapters.FragmentAdapter;
import com.example.pc.medineti.Entities.Réclamation;
import com.example.pc.medineti.Frags.MapsAdmin;
import com.example.pc.medineti.Frags.Recs;
import com.example.pc.medineti.Frags.RecsAdmin;
import com.example.pc.medineti.Frags.SuggAdmin;
import com.example.pc.medineti.Frags.myRec;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity implements RecsAdmin.OnFragmentInteractionListener,SuggAdmin.OnFragmentInteractionListener,MapsAdmin.OnFragmentInteractionListener{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        initViewPager();
    }
    public void initViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_main);

        List<String> titles = new ArrayList<>();
        titles.add("Réclamations");
        titles.add("Suggestions");
        titles.add("Maps");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecsAdmin());
        fragments.add(new SuggAdmin());
        fragments.add(new MapsAdmin());

        mViewPager.setOffscreenPageLimit(2);

        FragmentAdapter mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
