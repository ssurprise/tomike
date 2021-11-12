package com.skx.tomike.tank.widget.activity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.utils.ToastTool;
import com.skx.tomike.tank.R;
import com.skx.tomike.tank.widget.fragment.PlanetFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTER_GROUP;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_DRAWER_LAYOUT;

@Route(path = ROUTE_PATH_DRAWER_LAYOUT, group = ROUTER_GROUP)
public class DrawerLayoutActivity extends AppCompatActivity implements PlanetFragment.OnFragmentInteractionListener {

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


    private void initializeData() {
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
    }

    private void initializeView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.drawerLayout_toolbar);
        mDrawerList = findViewById(R.id.left_drawer);
    }

    private void refreshView() {

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
        int i = item.getItemId();
        if (i == R.id.action_search) {//                SearchView searchView = (SearchView) item.findItem(R.id.action_search).getActionView();
            ToastTool.showToast(DrawerLayoutActivity.this, "搜索");
            // User chose the "Settings" item, show the app settings UI...
            return true;
        } else if (i == R.id.action_share) {
            ToastTool.showToast(DrawerLayoutActivity.this, "分享");
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            return true;
        }
        ToastTool.showToast(DrawerLayoutActivity.this, "设置");
        // If we got here, the user's action was not recognized.
        // Invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);
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
        FragmentManager fragmentManager = getSupportFragmentManager();
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
