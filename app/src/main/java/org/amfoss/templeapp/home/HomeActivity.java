package org.amfoss.templeapp.home;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.Util.ActivityUtils;
import org.amfoss.templeapp.income.addDonation.AddDonationActivity;
import org.amfoss.templeapp.poojas.addPooja.AddPoojaActivity;
import org.amfoss.templeapp.settings.SettingsActivity;

/**
* @author Chromicle (ajayprabhakar369@gmail.com)
* @since 02/12/2019
*/
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Object View;
    private Toolbar toolbar;
    private TextView userName;
    private TextView userEmail;
    private ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setNavigationView();
        setViewPagers();
    }

    private void setViewPagers() {
        SectionsPagerAdapter sectionsPagerAdapter =
                new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private void setNavigationView() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        android.view.View headerview = navigationView.getHeaderView(0);
        userName = headerview.findViewById(R.id.userName);
        userEmail = headerview.findViewById(R.id.userEmail);
        userImage = headerview.findViewById(R.id.userImage);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawer,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);

        UserModel user = new UserModel();

        userName.setText("Namaskaram " + user.getUserName());
        userEmail.setText(user.getUserEmail());

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_pooja) {
            ActivityUtils.launchActivity(HomeActivity.this, AddPoojaActivity.class, false);
            // Handle the camera action
        } else if (id == R.id.nav_income_record) {

        } else if (id == R.id.nav_expense_record) {

        } else if (id == R.id.nav_donate_money) {
            ActivityUtils.launchActivity(HomeActivity.this, AddDonationActivity.class, false);
        } else if (id == R.id.nav_settings) {
            ActivityUtils.launchActivity(HomeActivity.this, SettingsActivity.class, false);
        } else if (id == R.id.nav_log_out) {
            FirebaseAuth.getInstance().signOut();
            ActivityUtils.launchActivity(HomeActivity.this, LoginActivity.class, true);
        }
        return true;
    }
}
