package com.imarahtech.grocery.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.imarahtech.grocery.R;
import com.imarahtech.grocery.activity.LocationActivity;
import com.imarahtech.grocery.activity.MainActivity;
import com.imarahtech.grocery.activity.ShopViewActivity;
import com.imarahtech.grocery.utils.Constants;
import com.imarahtech.grocery.utils.PreferenceHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopsFragment extends Fragment {

    RecyclerView rv_shops;
    RelativeLayout rl_location;

    public ShopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shops, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_shops = view.findViewById(R.id.rv_shops);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_shops.setLayoutManager(layoutManager);


        ArrayList<ItemModel> list = new ArrayList<>();
        list.add(new ItemModel("1", "Spinneys", "http://universalsupermarket.net/wp-content/uploads/2017/11/banner-about-us2-1.jpg"));
        list.add(new ItemModel("1", "MileStone supermarket", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToW6y20IHc2E-tkXYbbliRHEL0xoFSf4g-UAWh7GpBij8ydfbuqQ"));
        list.add(new ItemModel("1", "Geant", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJh1536hg-CbzaPli5iz7lOh87xm86NLXyOXkkpNunhkzKXpDY"));
        list.add(new ItemModel("1", "HyperPanda", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRc6GNzgVkO3FQJgDWmJ55juFaAdl1IkJ-qlD-vr5Whthog_EaUCg"));
        list.add(new ItemModel("1", "Al madina supermarket", "https://cdn.aarp.net/content/dam/aarp/money/budgeting_savings/2018/07/99-ways-logo/1140-food-and-grocery-ls.imgcache.rev3e1e908123481ab4ed62480bfd887315.jpg"));
        list.add(new ItemModel("1", "Batool Grocery", "http://www.chennaionlinegrocery.com/media/wysiwyg/Bannerb4.jpg"));
        list.add(new ItemModel("1", "Mashaar perfumes", "https://d3h5vr5d2kzrhb.cloudfront.net/wp-content/uploads/2017/08/FB-generic-fb-img.png"));

        ShopsAdapter shopsAdapter = new ShopsAdapter(list, getContext());
        rv_shops.setAdapter(shopsAdapter);

        rl_location = view.findViewById(R.id.rl_location);
        rl_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getContext(), LocationActivity.class);
                startActivity(ii);
            }
        });

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


    private class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ViewHolder>{

        ArrayList<ItemModel> list = new ArrayList<>();
        Context context;

        public ShopsAdapter(ArrayList<ItemModel> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items_shopsnearby, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            ItemModel model = list.get(position);


            Glide.with(context)
                    .load(list.get(position).getUrl())
                    .into(holder.imageView);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            ImageView imageView, iv_pin_shop;
            public ViewHolder(View itemView) {
                super(itemView);

                imageView = (ImageView) itemView.findViewById(R.id.imageView);
                iv_pin_shop = (ImageView) itemView.findViewById(R.id.iv_pin_shop);
//                bt_next = (Button) itemView.findViewById(R.id.bt_next);

//                imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent ii = new Intent( context, ShopViewActivity.class);
//                        startActivity(ii);
//                    }
//                });

                iv_pin_shop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pin = new PreferenceHelper(context).getInt(Constants.PIN_A_SHOP);
                        if (pin == 0){
                            new PreferenceHelper(context).putInt(Constants.PIN_A_SHOP, 1);
                            Toast.makeText(context, "you have pinned!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "you have already pinned a shop", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Fragment fragment = new ShopProductFragment();
//                        Bundle arguments = new Bundle();
//                        arguments.putInt( "VIEW_TYPE" , 1);
//                        fragment.setArguments(arguments);
//                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.fl_main_container, fragment, "VIEW_TYPE_BUNDLE");
//                        fragmentTransaction.commit();
//
//                        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Al madheena");

                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fl_main_container, new BlankFragment());
                        fragmentTransaction.commit();
                    }
                });
            }
        }
    }

}
