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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;

    RelativeLayout toolBar;
    RelativeLayout searchBar;
    EditText searchInput;

    Fragment selectedFragment = null;

    public static String CURRENT_TAG;
    private static final String TAG_HOME = "home";

    //DB TEST
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = findViewById(R.id.toolbar_rl);
        searchBar = findViewById(R.id.searchbar_rl);
        searchInput = findViewById(R.id.search_input);


        setUpToolbar();
        setUpNavigationView();
        setUpFragment(savedInstanceState);

        setUpSearch();
        closeUpSearch();

        //DB TEST
        //listView = findViewById(R.id.listView);
        //new DownloadJSON(this).execute();

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

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(searchInput.getWindowToken(), 0);
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
        //actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_menu2);
    }


    private void setUpFragment(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            CURRENT_TAG = TAG_HOME;
            selectedFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                    selectedFragment).addToBackStack(null).commit();
        }
    }


    //DB TEST
    private class DownloadJSON extends AsyncTask<Void, Void, String> {

        private WeakReference<MainActivity> activityReference;

        // only retain a weak reference to the activity
        DownloadJSON(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // get a reference to the activity if it is still there
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            try {
                loadIntoListView(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("INSERT_URL_HERE");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json).append("\n");
                }
                return sb.toString().trim();
            } catch (Exception e) {
                return null;
            }
        }
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] names = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            names[i] = obj.getString("first_name") + " " + obj.getString("gender");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(arrayAdapter);
    }
}
