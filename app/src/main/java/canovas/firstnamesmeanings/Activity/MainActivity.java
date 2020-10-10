package canovas.firstnamesmeanings.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.navigation.NavigationView;

import canovas.firstnamesmeanings.Config.Config;
import canovas.firstnamesmeanings.Fragment.CompatibilityFragment;
import canovas.firstnamesmeanings.Fragment.CompatibilityResultFragment;
import canovas.firstnamesmeanings.Fragment.FirstNameFragment;
import canovas.firstnamesmeanings.Fragment.HomeFragment;
import canovas.firstnamesmeanings.Fragment.HoroscopeFragment;
import canovas.firstnamesmeanings.Fragment.HoroscopeResultFragment;
import canovas.firstnamesmeanings.Fragment.SettingsFragment;
import canovas.firstnamesmeanings.R;
import canovas.firstnamesmeanings.Fragment.RankingFragment;
import canovas.firstnamesmeanings.VolleySingleton;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnButtonClickedListener,
        HoroscopeFragment.OnButtonClickedListener,
        CompatibilityFragment.OnButtonClickedListener, SettingsFragment.OnButtonClickedListener {

    Fragment fragment = null;

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

    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = this.getApplicationContext().getSharedPreferences("userPrefs", 0);

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



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_compatibility_txtView:
                fragment = new CompatibilityFragment();
                openNewFragment(fragment);
                break;
            case R.id.nav_horoscope_txtView:
                horoscopeSelect();
                break;
            case R.id.nav_settings_txtView:
                fragment = new SettingsFragment();
                openNewFragment(fragment);
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

        if (fragment instanceof SettingsFragment) {
            SettingsFragment settingsFragment = (SettingsFragment) fragment;
            settingsFragment.setOnButtonClickedListener(this);
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
                horoscopeSelect();
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

    public void horoscopeSelect(){
        //Check if name is saved in shared prefs
        String nameSaved = checkSharedPreferences();

        //Send result fragment straight away
        if(!nameSaved.equals("")){
            onHoroscopeSubmit(nameSaved,false);

            //Else open main fragment
        }else{
            fragment = new HoroscopeFragment();
            openNewFragment(fragment);
        }
    }

    public String checkSharedPreferences(){
        return mSharedPreferences.getString("name","");
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


    private void submitSearch() {
        String query = searchInput.getText().toString();
        String url = Config.URL_GET_NAME + "?firstName=" + query + "&token=" + token;
        FirstNameFragment firstNameFragment = new FirstNameFragment();

        //Create bundle to attach data to fragment
        Bundle bundle = new Bundle();

        this.getData(url,firstNameFragment, bundle,"firstName");
    }

    @Override
    public void onHoroscopeSubmit(String firstName, boolean saveName) {

        //Search name in DB
        //Launch new fragment with results
        String url = Config.URL_GET_HOROSCOPE
                + "?firstName=" + firstName
                + "&token=" + token;
        HoroscopeResultFragment horoscopeResultFragment = new HoroscopeResultFragment();

        //Create bundle to attach data to fragment
        Bundle bundle = new Bundle();
        bundle.putString("firstName", firstName);
        bundle.putBoolean("saveName", saveName);

        this.getData(url, horoscopeResultFragment, bundle,"horoscope");
    }

    @Override
    public void onCompatibilitySubmit(String name1, String name2) {

        //Calculate score
        //Open new fragment with results
        String url = Config.URL_GET_COMPATIBILITY + "?firstName1=" + name1 + "&firstName2=" + name2 + "&token=" + token;
        CompatibilityResultFragment compatibilityResultFragment = new CompatibilityResultFragment();

        //Create bundle to attach data to fragment
        Bundle bundle = new Bundle();
        bundle.putString("firstName1", name1);
        bundle.putString("firstName2", name2);

        this.getData(url, compatibilityResultFragment, bundle, "compatibility");
    }

    //Request to get data from PHP app
    public void getData(String url, final Fragment fragment, final Bundle bundle,final String bundleName) {

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Attach data to fragment
                bundle.putString(bundleName, response);

                //Attach bundle to frag
                fragment.setArguments(bundle);
                openNewFragment(fragment);

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

    @Override
    public void onResetButtonClicked(View view) {

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.clear();

        editor.apply();

        Toast.makeText(getApplicationContext(), "Name successfully reset", Toast.LENGTH_LONG).show();

    }
}
