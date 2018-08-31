package com.imarahtech.grocery.activity;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imarahtech.grocery.R;
import com.imarahtech.grocery.dialogs.QuantityDialog;
import com.imarahtech.grocery.fragment.ShopProductFragment;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView rv_shopsnearby, rv_cart;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initialiseView();
    }

    private void initialiseView() {

        toolbar = findViewById(R.id.tb_cart);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Your Cart");

        rv_shopsnearby = findViewById(R.id.rv_cart_shops);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_shopsnearby.setLayoutManager(layoutManager);

        rv_cart = findViewById(R.id.rv_mycart);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        rv_cart.setLayoutManager(layoutManager1);

        setData();

    }

    private void setData() {


        ArrayList<ItemModel> list_shops = new ArrayList<>();

        list_shops.add(new ItemModel("1", "Al madina supermarket", "https://cdn.aarp.net/content/dam/aarp/money/budgeting_savings/2018/07/99-ways-logo/1140-food-and-grocery-ls.imgcache.rev3e1e908123481ab4ed62480bfd887315.jpg"));
        list_shops.add(new ItemModel("1", "Al madina supermarket", "http://www.chennaionlinegrocery.com/media/wysiwyg/Bannerb4.jpg"));
        list_shops.add(new ItemModel("1", "Al madina supermarket", "https://d3h5vr5d2kzrhb.cloudfront.net/wp-content/uploads/2017/08/FB-generic-fb-img.png"));
        list_shops.add(new ItemModel("1", "Al madina supermarket", "https://cdn.aarp.net/content/dam/aarp/money/budgeting_savings/2018/07/99-ways-logo/1140-food-and-grocery-ls.imgcache.rev3e1e908123481ab4ed62480bfd887315.jpg"));


        TopShopAdapter topShopAdapter = new TopShopAdapter(list_shops, this);
        rv_shopsnearby.setAdapter(topShopAdapter);
        rv_shopsnearby.setNestedScrollingEnabled(false);

        rv_shopsnearby.addItemDecoration(new ShopsItemDecorator());


        ArrayList<ItemModel> list_cart = new ArrayList<>();
        list_cart.add(new ItemModel("1", "Tank Orange 200gm", "https://usgroceries.co.uk/wp-content/uploads/sites/5/2016/10/tang_powder_juice_orange_pineapple_1.jpg"));
        list_cart.add(new ItemModel("1", "Surf Aloha flash 1 kg", "https://cdn7.bigcommerce.com/s-um6op5fe4t/products/993/images/1807/Surf_Detergent_Powder_Blossom_Fresh_1100g__06429.1499844609.500.750.jpg?c=2"));
        list_cart.add(new ItemModel("1", "London Diary Vanilla 5 ltr", "https://westgatelifecare.com/wp-content/uploads/2017/10/London-diary-double-chocolate-tub-1ltr.jpg"));

        MyCartAdapter myCartAdapter = new MyCartAdapter(this, list_cart);
        rv_cart.setAdapter(myCartAdapter);
        rv_cart.setNestedScrollingEnabled(false);
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

    private class TopShopAdapter extends RecyclerView.Adapter<TopShopAdapter.ViewHolder>{

        List<ItemModel> arrayList = new ArrayList<>();
        Context context;

        public TopShopAdapter(List<ItemModel> arrayList, Context context) {
            this.arrayList = arrayList;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_topshop, parent,false);
            return new ViewHolder(view1);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            ItemModel model = arrayList.get(position);

//            holder.tv_name.setText(model.getName());

            Glide.with(context)
                    .load(model.getUrl())
                    .into(holder.iv_image);

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_image;
            TextView tv_name;
            public ViewHolder(View itemView) {
                super(itemView);

                iv_image = itemView.findViewById(R.id.imageView);
//                tv_name = itemView.findViewById(R.id.title);

            }
        }
    }


    private class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyCartViewHolder> {

        Context context;
        List<ItemModel> cartList;


        public MyCartAdapter(Context context, List<ItemModel> cartList) {
            this.context = context;
            this.cartList = cartList;
        }

        @Override
        public MyCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitem_mycart, parent, false);
            return new MyCartViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyCartViewHolder holder, int position) {

            ItemModel products = cartList.get(position);

            holder.tv_productName.setText(products.getName());
            holder.tv_currentPrice.setText("20");
            holder.tv_cart_count.setText("2");
            int totalAmt = 20 * 2;
            holder.tv_cart_TPrice.setText("" +totalAmt);


            String image_link = products.getUrl();

            Glide.with(context)
                    .load(image_link)
                    .into(holder.iv_image);

        }

        @Override
        public int getItemCount() {
            return cartList.size();
        }

        class MyCartViewHolder extends RecyclerView.ViewHolder {

            ImageView iv_image;
            TextView tv_productName;
            TextView tv_cart_count;
            TextView tv_cart_TPrice;
            TextView tv_currentPrice;
            LinearLayout ll_numberpick;

            MyCartViewHolder(View itemView) {
                super(itemView);

                iv_image = (ImageView) itemView.findViewById(R.id.im_product_image2);
                tv_productName = (TextView) itemView.findViewById(R.id.tv_product_name2);
                tv_currentPrice = (TextView) itemView.findViewById(R.id.tv_cur_price2);
                tv_cart_count = (TextView) itemView.findViewById(R.id.tv_cart_count);
                tv_cart_TPrice = (TextView) itemView.findViewById(R.id.tv_cart_totalAmt);
                ll_numberpick = itemView.findViewById(R.id.ll_numberpick);

                ll_numberpick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        QuantityDialog quantityDialog = new QuantityDialog(context);
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

    private class  ShopsItemDecorator extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            outRect.set(15, 15, 15, 15);

        }

    }
}
