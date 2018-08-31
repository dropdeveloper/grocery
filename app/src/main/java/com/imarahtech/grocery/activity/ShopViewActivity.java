package com.imarahtech.grocery.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.imarahtech.grocery.R;
import com.imarahtech.grocery.fragment.ShopCategoryFragment;
import com.imarahtech.grocery.fragment.ShopProductFragment;

public class ShopViewActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    //0 if grid, 1 if list, based on actionbar view listener, initially 0.
    private static int  ViewType = 0;
    //0 if product, 1 if category, based on bottomnavigationview listener, initially 0.
    private static int  TabType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_view);

        initialiseViews();
    }

    private void initialiseViews() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Al Madeena Grocery");

        bottomNavigationView = findViewById(R.id.bn_shopview);

        loadGridProduct();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bn_products:
                        TabType = 0;
                        if (ViewType == 0){
                            loadGridProduct();
                        } else {
                            loadListProduct();
                        }
                        break;
                    case R.id.bn_categories:
                        TabType = 1;
                        if (ViewType == 0){
                            loadGridCategory();
                        } else {
                            loadListCategory();
                        }
                        break;
                    case R.id.bn_search:
                        Intent ii = new Intent(ShopViewActivity.this, SearchActivity.class);
                        ii.putExtra("CASE", "1");
                        startActivity(ii);
                        break;
                }
                return true;
            }
        });

    }

    private void loadGridCategory(){
        Fragment fragment = new ShopCategoryFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("VIEW_TYPE" , 0);
        fragment.setArguments(arguments);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_shopview_container, fragment, "VIEW_TYPE_BUNDLE");
        fragmentTransaction.commit();
    }

    private void loadListCategory(){
        Fragment fragment = new ShopCategoryFragment();
        Bundle arguments = new Bundle();
        arguments.putInt( "VIEW_TYPE" , 1);
        fragment.setArguments(arguments);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_shopview_container, fragment, "VIEW_TYPE_BUNDLE");
        fragmentTransaction.commit();
    }

    private void loadGridProduct(){
        Fragment fragment = new ShopProductFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("VIEW_TYPE", 0);
        fragment.setArguments(arguments);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_shopview_container, fragment, "VIEW_TYPE_BUNDLE");
        fragmentTransaction.commit();
    }

    private void loadListProduct(){
        Fragment fragment = new ShopProductFragment();
        Bundle arguments = new Bundle();
        arguments.putInt( "VIEW_TYPE" , 1);
        fragment.setArguments(arguments);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_shopview_container, fragment, "VIEW_TYPE_BUNDLE");
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.action_grid:
                ViewType = 0;
                if (TabType == 0){
                    loadGridProduct();
                } else{
                    loadGridCategory();
                }
                return true;

            case R.id.action_list:
                ViewType = 1;
                if (TabType == 0){
                    loadListProduct();
                } else{
                    loadListCategory();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shopview_actionbar, menu);
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if(TabType == 0){
            bottomNavigationView.setSelectedItemId(R.id.bn_products);
        } else if(TabType == 1){
            bottomNavigationView.setSelectedItemId(R.id.bn_categories);
        }
    }
}
