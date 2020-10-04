package canovas.firstnamesmeanings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnButtonClickedListener,
        HoroscopeFragment.OnButtonClickedListener,
        CompatibilityFragment.OnButtonClickedListener{

    Fragment fragment = null;

    private MyRequest mMyRequest;
    private RequestQueue queue;

    private Toolbar toolbar;
    private DrawerLayout drawer;

    RelativeLayout toolBar;
    RelativeLayout searchBar;
    EditText searchInput;
    ImageView submitSearch_imgView;

    Fragment selectedFragment = null;

    public static String CURRENT_TAG;
    private static final String TAG_HOME = "home";

    //DB TEST
    private static final String url = "jdbc:mysql://localhost:8889/SignificationPrenom_test";
    private static final String user = "root";
    private static final String pass = "root";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = findViewById(R.id.toolbar_rl);
        searchBar = findViewById(R.id.searchbar_rl);
        searchInput = findViewById(R.id.search_input);
        submitSearch_imgView = findViewById(R.id.app_bar_search2);

        submitSearch_imgView.setOnClickListener(this);

        disableInput();

        setUpToolbar();
        setUpNavigationView();
        setUpFragment(savedInstanceState);

        setUpSearch();
        closeUpSearch();

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        mMyRequest = new MyRequest(this, queue);

    }

    private void setUpSearch() {
        ImageView search_imgView = findViewById(R.id.app_bar_search);
        search_imgView.setOnClickListener(this);
    }

    private void closeUpSearch(){
        ImageView close_imgView = findViewById(R.id.app_bar_close_search);
        close_imgView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.app_bar_search:
                enableInput();
                break;
            case R.id.app_bar_close_search:
                disableInput();
            case R.id.app_bar_search2:
                submitSearch();
        }
    }

    private void enableInput() {

        toolBar.setVisibility(View.GONE);
        searchBar.setVisibility(View.VISIBLE);

        searchInput.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.showSoftInput(searchInput, InputMethodManager.SHOW_IMPLICIT);
    }


    private void disableInput(){

        toolBar.setVisibility(View.VISIBLE);
        searchBar.setVisibility(View.GONE);

        hideKeyboard();
    }

    private void submitSearch() {
        String query = searchInput.getText().toString();
        mMyRequest.getData(query);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    private void setUpToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Set up Navigation View
    private void setUpNavigationView() {

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getHeaderView(0);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);

        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setToolbarNavigationClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        drawer.openDrawer(GravityCompat.START);
                    }
                });
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_menu);
    }


    private void setUpFragment(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            CURRENT_TAG = TAG_HOME;
            selectedFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                    selectedFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            HomeFragment homeFragment = (HomeFragment) fragment;
            homeFragment.setOnButtonClickedListener(this);
        }
    }

    @Override
    public void onButtonClicked(View view) {

        switch(view.getId()){
            case R.id.home_search_btn:
                enableInput();
                break;
            case R.id.home_horoscope_btn:
                fragment = new HoroscopeFragment();
                openNewFragment(fragment);
        }

    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(searchInput.getWindowToken(), 0);
    }


    private void openNewFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                fragment).addToBackStack(null).commit();
    }

    @Override
    public void onHoroscopeSubmit(String firstName, String email, boolean isSubscribed) {

        //Search name in DB
        //Launch new fragment with results
    }

    @Override
    public void onCompatibilitySubmit(String name1, String name2) {

        //Calculate score
        //Open new fragment with results
    }
}
