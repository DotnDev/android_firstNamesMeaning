package canovas.firstnamesmeanings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;

    Fragment selectedFragment = null;

    public static String CURRENT_TAG;
    private static final String TAG_HOME = "home";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();
        setUpNavigationView();
        setUpFragment(savedInstanceState);
    }

    @Override
    public void onClick(View view) {

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

        Log.d("testing", "yup in navbar");

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
        //actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_menu);
    }


    private void setUpFragment(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            CURRENT_TAG = TAG_HOME;
            selectedFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                    selectedFragment).addToBackStack(null).commit();
        }
    }
}
