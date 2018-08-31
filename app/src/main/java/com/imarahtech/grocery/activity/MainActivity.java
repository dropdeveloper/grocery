package com.imarahtech.grocery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.imarahtech.grocery.R;
import com.imarahtech.grocery.dialogs.LanguageDialog;
import com.imarahtech.grocery.fragment.CategoryFragment;
import com.imarahtech.grocery.fragment.DealsFragment;
import com.imarahtech.grocery.fragment.MessagesFragment;
import com.imarahtech.grocery.fragment.ShopCategoryFragment;
import com.imarahtech.grocery.fragment.ShopProductFragment;
import com.imarahtech.grocery.fragment.ShopsFragment;
import com.imarahtech.grocery.utils.BottomNavigationBehavior;
import com.imarahtech.grocery.utils.BottomNavigationViewHelper;
import com.imarahtech.grocery.utils.Constants;
import com.imarahtech.grocery.utils.PreferenceHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView ;
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;
    ImageView iv_pro;
    LinearLayout ll_language;
    TextView tv_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseViews();

    }

    private void initialiseViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ll_language =  navigationView.getHeaderView(0).findViewById(R.id.ll_language);
        tv_language =  navigationView.getHeaderView(0).findViewById(R.id.tv_language);
        tv_language.setText("English");

        tv_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageDialog languageDialog = new LanguageDialog(MainActivity.this);
                languageDialog.show();
            }
        });

        iv_pro = navigationView.getHeaderView(0).findViewById(R.id.iv_profile);

        Glide.with(this)
                .load("http://www.personalbrandingblog.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640-300x300.png")
                .apply(new RequestOptions()
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(iv_pro);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        Menu menu = navigationView.getMenu();

        // find MenuItem you want to change
        MenuItem mi_home = menu.findItem(R.id.nav_home);
        mi_home.setTitle("Stores");

        int pin = new PreferenceHelper(MainActivity.this).getInt(Constants.PIN_A_SHOP);
        if (pin == 0){
            Fragment selectedFragment1 =  new ShopsFragment();
            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
            transaction1.replace(R.id.fl_main_container, selectedFragment1);
            transaction1.commitAllowingStateLoss();

            getSupportActionBar().setTitle("Stores");
        } else {
            Fragment fragment = new ShopProductFragment();
            Bundle arguments = new Bundle();
            arguments.putInt( "VIEW_TYPE" , 1);
            fragment.setArguments(arguments);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_main_container, fragment, "VIEW_TYPE_BUNDLE");
            fragmentTransaction.commit();

            getSupportActionBar().setTitle("Al madeena grocery");
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bn_shops:
                    int pin = new PreferenceHelper(MainActivity.this).getInt(Constants.PIN_A_SHOP);
                    if (pin == 0){
                        Fragment selectedFragment1 =  new ShopsFragment();
                        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                        transaction1.replace(R.id.fl_main_container, selectedFragment1);
                        transaction1.commitAllowingStateLoss();

                        getSupportActionBar().setTitle("Stores");
                    } else {
                        Fragment fragment = new ShopProductFragment();
                        Bundle arguments = new Bundle();
                        arguments.putInt( "VIEW_TYPE" , 1);
                        fragment.setArguments(arguments);
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fl_main_container, fragment, "VIEW_TYPE_BUNDLE");
                        fragmentTransaction.commitAllowingStateLoss();

                        getSupportActionBar().setTitle("Al madeena grocery");
                    }
                    break;

                case R.id.bn_category:
//                    Fragment selectedFragment2 =  new ShopCategoryFragment();
//                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.fl_main_container, selectedFragment2);
//                    transaction2.commitAllowingStateLoss();

                    Fragment fragment = new ShopCategoryFragment();
                    Bundle arguments = new Bundle();
                    arguments.putInt( "VIEW_TYPE" , 1);
                    fragment.setArguments(arguments);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fl_main_container, fragment, "VIEW_TYPE_BUNDLE");
                    fragmentTransaction.commitAllowingStateLoss();

                    break;

                case R.id.bn_deals:
//                    Fragment selectedFragment2 =  new ShopCategoryFragment();
//                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.fl_main_container, selectedFragment2);
//                    transaction2.commitAllowingStateLoss();

                    Fragment fragment1 = new DealsFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.fl_main_container, fragment1);
                    fragmentTransaction1.commitAllowingStateLoss();

                    break;

                case R.id.bn_messages:
//                    Fragment selectedFragment2 =  new ShopCategoryFragment();
//                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
//                    transaction2.replace(R.id.fl_main_container, selectedFragment2);
//                    transaction2.commitAllowingStateLoss();

                    Fragment fragment2 = new MessagesFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.fl_main_container, fragment2);
                    fragmentTransaction2.commitAllowingStateLoss();

                    break;
                case R.id.bn_cart:
                    Intent ii = new Intent(MainActivity.this, CartActivity.class);
                    startActivity(ii);

                    break;
            }
            return true;
        }
    };



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
        getMenuInflater().inflate(R.menu.main_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Intent ii = new Intent(this, SearchActivity.class);
            ii.putExtra("CASE","1");
            startActivity(ii);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

                Fragment selectedFragment1 =  new ShopsFragment();
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.fl_main_container, selectedFragment1);
                transaction1.commitAllowingStateLoss();

                getSupportActionBar().setTitle("Stores");

        } else if (id == R.id.nav_profile){

                Intent ii = new Intent(this, ProfileActivity.class);
                startActivity(ii);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        String current_fragment = this.getSupportFragmentManager().findFragmentById(R.id.fl_main_container).getClass().getSimpleName();
        if(current_fragment.equals("ShopsFragment") || current_fragment.equals("ShopProductFragment")){
            bottomNavigationView.setSelectedItemId(R.id.bn_shops);
        } else if(current_fragment.equals("ShopCategoryFragment")){
            bottomNavigationView.setSelectedItemId(R.id.bn_category);
        }else if(current_fragment.equals("MessagesFragment")){
            bottomNavigationView.setSelectedItemId(R.id.bn_messages);
        }else if(current_fragment.equals("DealsFragment")){
            bottomNavigationView.setSelectedItemId(R.id.bn_deals);
        }

    }
}
