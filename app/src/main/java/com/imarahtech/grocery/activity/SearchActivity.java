package com.imarahtech.grocery.activity;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imarahtech.grocery.R;
import com.imarahtech.grocery.dialogs.QuantityDialog;
import com.imarahtech.grocery.fragment.ShopProductFragment;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    //CASE : 1 = search, 2 = shop category, 3 = main category
    String CATEGORY_ID, CATEGORY_NAME, CASE, SHOP_ID, SHOP_NAME;
    Toolbar toolbar;
    RecyclerView rv_search;
    EditText et_serach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getIntentValues();
    }

    private void getIntentValues() {

        CASE = getIntent().getExtras().getString("CASE");
        if (CASE != null && CASE.equals("2")){
            CATEGORY_ID = getIntent().getExtras().getString("CATEGORY_ID");
            CATEGORY_NAME = getIntent().getExtras().getString("CATEGORY_NAME");
            SHOP_ID = getIntent().getExtras().getString("SHOP_ID");
            SHOP_NAME = getIntent().getExtras().getString("SHOP_NAME");
        } else if(CASE !=null && CASE.equals("3")){
            CATEGORY_ID = getIntent().getExtras().getString("CATEGORY_ID");
            CATEGORY_NAME = getIntent().getExtras().getString("CATEGORY_NAME");
        }

        initialiseViews();
    }

    private void initialiseViews() {
        toolbar = findViewById(R.id.tb_search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_search = findViewById(R.id.rv_search);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_search.setLayoutManager(layoutManager);

        et_serach = findViewById(R.id.et_search);

        if (CASE !=null && CASE.equals("2")){
            et_serach.setText(CATEGORY_NAME + " in " + SHOP_NAME);
            setRecycleData();
        } else if (CASE != null && CASE.equals("3")){
            et_serach.setText(CATEGORY_NAME);
            setRecycleData();
        }
    }

    private void setRecycleData() {

        ArrayList<ItemModel> productList = new ArrayList<>();

        ProductListAdapter productListAdapter = new ProductListAdapter(this, productList, 1);
        rv_search.setAdapter(productListAdapter);

        productList.add(new ItemModel("1", "Vegetables", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpF_Xtu4cVWGl18IHo3QoQoK8IL-l2018_iHcaPcSH3l1SDaxGkA"));
        productList.add(new ItemModel("1", "Fruits", "https://www.organicfacts.net/wp-content/uploads/2013/05/Fruits3.jpg"));
        productList.add(new ItemModel("1", "snacks", "https://www.dollargeneral.com/media/catalog/category/SNACKSCOOKIES.jpg"));
        productList.add(new ItemModel("1", "drinks", "https://i.dietdoctor.com/wp-content/uploads/2017/04/Dietdoctor_Dietdrinks.jpg?auto=compress%2Cformat&w=1200&h=979&fit=crop"));

        productListAdapter.notifyDataSetChanged();

    }

    private class ItemModel{

        private String id;
        private String name;
        private String url;

        public ItemModel(String id, String name, String url) {
            this.id = id;
            this.name = name;
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    private class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

        private Context mContext;
        private ArrayList<ItemModel> albumList;
        private int VT;

        public ProductListAdapter(Context mContext, ArrayList<ItemModel> albumList, int viewType) {
            this.mContext = mContext;
            this.albumList = albumList;
            this.VT = viewType;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            if (VT == 0){
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_product_sv_grid, parent, false);
            } else {
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_product_sv_list, parent, false);
            }

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
           ItemModel album = albumList.get(position);
            holder.title.setText(album.getName());

            holder.tv_disc.setPaintFlags(holder.tv_disc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            // loading album cover using Glide library
            Glide.with(mContext).load(album.getUrl()).into(holder.thumbnail);

        }


        @Override
        public int getItemCount() {
            return albumList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title, tv_disc;
            ImageView thumbnail, iv_remove;
            Button bt_add;
            LinearLayout numpicker_main, quantity;
            MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.title);
                tv_disc = (TextView) view.findViewById(R.id.tv_disc_price);
                thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
                bt_add = itemView.findViewById(R.id.bt_add);
                numpicker_main = itemView.findViewById(R.id.ll_numberpick);
                quantity = itemView.findViewById(R.id.ll_quantity_grid);
                iv_remove = itemView.findViewById(R.id.iv_remove);

                iv_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        numpicker_main.setVisibility(View.GONE);
                        bt_add.setVisibility(View.VISIBLE);
                    }
                });

                bt_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bt_add.setVisibility(View.GONE);
                        numpicker_main.setVisibility(View.VISIBLE);

                    }
                });


                    quantity.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            QuantityDialog quantityDialog = new QuantityDialog(mContext);
                            quantityDialog.show();

                        }
                    });

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
