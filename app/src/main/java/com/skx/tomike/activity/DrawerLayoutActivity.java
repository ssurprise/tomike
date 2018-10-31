package com.skx.tomike.activity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.skx.tomike.R;
import com.skx.tomike.fragment.PlanetFragment;
import com.skx.tomike.util.ToastTool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawerLayoutActivity extends SkxBaseActivity implements PlanetFragment.OnFragmentInteractionListener {

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        initializeData();
        initializeView();
        refreshView();
    }


    @Override
    public void initializeData() {
        super.initializeData();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
    }

    @Override
    public void initializeView() {
        super.initializeView();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.drawerLayout_toolbar);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
    }

    @Override
    public void refreshView() {
        super.refreshView();

//        mToolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        mToolbar.setTitle("Skx");//设置主标题
        setSupportActionBar(mToolbar);

        //        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tool_bar, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setInputType(InputType.TYPE_CLASS_NUMBER);
//        searchView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.skx_30c3a6));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_search:
//                SearchView searchView = (SearchView) item.findItem(R.id.action_search).getActionView();
                ToastTool.showToast(mContext, "搜索");
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_share:
                ToastTool.showToast(mContext, "分享");
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                ToastTool.showToast(mContext, "设置");
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        Fragment fragment = PlanetFragment.newInstance(String.valueOf(position), mPlanetTitles[position]);

        // Insert the fragment by replacing any existing fragment
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
//        mTitle = title;
//        getActionBar().setTitle(mTitle);
    }


    private List<String> getData() {
        List<String> userList = new ArrayList<>();
        Collections.addAll(userList, mPlanetTitles);

        return userList;
    }
}
