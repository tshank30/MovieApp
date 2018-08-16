package ftelematics.myapplication.activities;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ftelematics.myapplication.R;
import ftelematics.myapplication.fragments.HomeScreen;
import ftelematics.myapplication.fragments.SplashScreen;
import ftelematics.myapplication.fragments.TopMoviesScreen;
import ftelematics.myapplication.utils.BaseActivity;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SearchView searchView;
    private String searchQuery = "";
    private HomeScreen homeScreen;
    private MenuItem searchItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //hide action bar for splashScreen
        hideActionBar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SplashScreen splashScreen = new SplashScreen();
        changeFragment(splashScreen, false);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        searchItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) searchItem.getActionView();
        setupSearchView();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_search :
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // Search widget
    private void setupSearchView() {
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
            searchView.setSearchableInfo(info);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                searchQuery = newText;

                if (homeScreen != null) {
                    if (newText.length() > 0)
                        homeScreen.search(newText);
                }

                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return false;
            }
        });

        // Handling focus change of search view
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                // Focus changed after pressing back key or pressing done in keyboard
                if (!hasFocus) {
                    searchQuery = "";
                    homeScreen=null;
                }
                else
                {
                    //resuse homeScreen as Search Fragment
                    homeScreen = new HomeScreen();
                    changeFragment(homeScreen, true);
                }
            }
        });
    }

    public void hideActionBar()
    {
        getSupportActionBar().hide();
    }

    public void unHideActionBar()
    {
        getSupportActionBar().show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.popular) {
            HomeScreen homeScreen = new HomeScreen();
            changeFragment(homeScreen, false);
            // open home screen
        } else if (id == R.id.top) {
            TopMoviesScreen splashScreen = new TopMoviesScreen();
            changeFragment(splashScreen, false);
            // open Top Movies screen
        } else if (id == R.id.search) {
            //searchView.callOnClick();
            searchItem.expandActionView();
            searchView.requestFocus();
            // open Search screen
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
