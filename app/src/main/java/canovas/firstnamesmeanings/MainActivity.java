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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnButtonClickedListener,
        HoroscopeFragment.OnButtonClickedListener,
        CompatibilityFragment.OnButtonClickedListener {

    Fragment fragment = null;

    private MyRequest mMyRequest;
    private RequestQueue queue;
    private String token;

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;

    RelativeLayout toolBar;
    RelativeLayout searchBar;
    EditText searchInput;
    ImageView submitSearch_imgView;
    ImageView appBar_logo_imgView;

    Fragment selectedFragment = null;

    public static String CURRENT_TAG;
    private static final String TAG_HOME = "home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Click on app bar logo => back to home fragment
        appBar_logo_imgView = findViewById(R.id.app_bar_logo);
        appBar_logo_imgView.setOnClickListener(this);

        toolBar = findViewById(R.id.toolbar_rl);
        searchBar = findViewById(R.id.searchbar_rl);
        searchInput = findViewById(R.id.search_input);
        submitSearch_imgView = findViewById(R.id.app_bar_search2);

        submitSearch_imgView.setOnClickListener(this);

        this.disableInput();

        this.setUpToolbar();
        this.setUpFragment(savedInstanceState);

        this.configureDrawerLayout();
        this.configureNavigationView();

        setUpSearch();
        closeUpSearch();

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        mMyRequest = new MyRequest(this, queue);
        token = Config.ANDROID_TOKEN;

    }

    private void configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.setDrawerIndicatorEnabled(false);
        toggle.setToolbarNavigationClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        drawerLayout.openDrawer(GravityCompat.START);
                    }
                });
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void configureNavigationView() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void setUpSearch() {
        ImageView search_imgView = findViewById(R.id.app_bar_search);
        search_imgView.setOnClickListener(this);
    }

    private void closeUpSearch() {
        ImageView close_imgView = findViewById(R.id.app_bar_close_search);
        close_imgView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.app_bar_search:
                enableInput();
                break;
            case R.id.app_bar_close_search:
                disableInput();
                break;
            case R.id.app_bar_search2:
                submitSearch();
                break;
            case R.id.app_bar_logo:
                if (!(fragment instanceof HomeFragment)) {
                    fragment = new HomeFragment();
                    openNewFragment(fragment);
                }


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


    private void disableInput() {

        toolBar.setVisibility(View.VISIBLE);
        searchBar.setVisibility(View.GONE);

        hideKeyboard();
    }

    private void submitSearch() {
        String query = searchInput.getText().toString();
        String url = Config.URL_GET_NAME + "?firstName=" + query + "&token=" + token;
        this.getData(url);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_compatibility_txtView:
                fragment = new CompatibilityFragment();
                openNewFragment(fragment);
                break;
            case R.id.nav_horoscope_txtView:
                fragment = new HoroscopeFragment();
                openNewFragment(fragment);
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    private void setUpToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

        if (fragment instanceof HoroscopeFragment) {
            HoroscopeFragment horoscopeFragment = (HoroscopeFragment) fragment;
            horoscopeFragment.setOnButtonClickedListener(this);
        }

        if (fragment instanceof CompatibilityFragment) {
            CompatibilityFragment compatibilityFragment = (CompatibilityFragment) fragment;
            compatibilityFragment.setOnButtonClickedListener(this);
        }


    }

    //Button clicked in Home Fragment
    @Override
    public void onButtonClicked(View view) {

        switch (view.getId()) {
            case R.id.home_search_btn:
                enableInput();
                break;
            case R.id.home_horoscope_btn:
                fragment = new HoroscopeFragment();
                openNewFragment(fragment);
                break;
            case R.id.home_compatibility_btn:
                fragment = new CompatibilityFragment();
                openNewFragment(fragment);
                break;
            case R.id.home_ranking_btn:
                fragment = new RankingFragment();
                openNewFragment(fragment);
                break;
        }

    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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


    //Request to get data from PHP app
    public void getData(String url) {

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Create fragment
                FirstNameFragment firstNameFragment = new FirstNameFragment();

                //Create bundle to attach data to fragment
                Bundle bundle = new Bundle();
                bundle.putString("firstName", response);

                //Attach bundle to frag
                firstNameFragment.setArguments(bundle);
                openNewFragment(firstNameFragment);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                Log.d("APP", "ERROR = " + error);

            }
        });

        queue.add(request);

    }
}
