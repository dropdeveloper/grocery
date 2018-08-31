package com.imarahtech.grocery.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.imarahtech.grocery.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ItemModel> productList = new ArrayList<>();



    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView  = view.findViewById(R.id.rv_blank);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        productList.add(new ItemModel("1", "Apple (Royal gala)", "https://bestapples.com/wp-content/uploads/2018/01/ambrosia-apple.jpg"));
        productList.add(new ItemModel("1", "Masafi Apple juice", "https://hybrisprod.azureedge.net/sys-master-prod/h52/hb5/8957477421086/636594_main.jpg_480Wx480H"));
        productList.add(new ItemModel("1", "Lays Chili", "https://supermart.ae/6479-large_default/lays-chili-185g.jpg"));
        productList.add(new ItemModel("1", "Galaxy Minstrels", "https://refreshmentshop.co.uk/images/large/galaxy-minstrels.jpg"));
        productList.add(new ItemModel("1", "Princes Tuna Chunks", "http://www.tonyson.ng/2834-home_default/princess-tuna-chunk-in-sun-flower-oil.jpg"));
        productList.add(new ItemModel("1", "Tank Orange", "https://usgroceries.co.uk/wp-content/uploads/sites/5/2016/10/tang_powder_juice_orange_pineapple_1.jpg"));
        productList.add(new ItemModel("1", "Surf Aloha flash", "https://cdn7.bigcommerce.com/s-um6op5fe4t/products/993/images/1807/Surf_Detergent_Powder_Blossom_Fresh_1100g__06429.1499844609.500.750.jpg?c=2"));
        productList.add(new ItemModel("1", "London Diary Vanilla", "https://westgatelifecare.com/wp-content/uploads/2017/10/London-diary-double-chocolate-tub-1ltr.jpg"));


        BlankAdapter blankAdapter = new BlankAdapter(productList, getContext());
        recyclerView.setAdapter(blankAdapter);

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

    private class BlankAdapter extends RecyclerView.Adapter<BlankAdapter.ViewHolder>{

        ArrayList<ItemModel> list = new ArrayList<>();
        Context context;

        public BlankAdapter(ArrayList<ItemModel> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_blank, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

            if (position != 0){
                holder.ll_rvs.setVisibility(View.GONE);
            }

            ArrayList<ItemModel> list_sub = new ArrayList<>();
            list_sub.add(new ItemModel("1", "Apple (Royal gala)", "https://bestapples.com/wp-content/uploads/2018/01/ambrosia-apple.jpg"));
            list_sub.add(new ItemModel("1", "Masafi Apple juice", "https://hybrisprod.azureedge.net/sys-master-prod/h52/hb5/8957477421086/636594_main.jpg_480Wx480H"));
            list_sub.add(new ItemModel("1", "Lays Chili", "https://supermart.ae/6479-large_default/lays-chili-185g.jpg"));
            list_sub.add(new ItemModel("1", "Galaxy Minstrels", "https://refreshmentshop.co.uk/images/large/galaxy-minstrels.jpg"));
            list_sub.add(new ItemModel("1", "Princes Tuna Chunks", "http://www.tonyson.ng/2834-home_default/princess-tuna-chunk-in-sun-flower-oil.jpg"));

            BlankAdapter2 blankAdapter2 = new BlankAdapter2(list_sub, context);
            holder.rv_sub.setAdapter(blankAdapter2);


            ArrayList<ItemModel> list_pro = new ArrayList<>();

            list_pro.add(new ItemModel("1", "Apple (Royal gala)", "https://bestapples.com/wp-content/uploads/2018/01/ambrosia-apple.jpg"));
            list_pro.add(new ItemModel("1", "Masafi Apple juice", "https://hybrisprod.azureedge.net/sys-master-prod/h52/hb5/8957477421086/636594_main.jpg_480Wx480H"));
            list_pro.add(new ItemModel("1", "Lays Chili", "https://supermart.ae/6479-large_default/lays-chili-185g.jpg"));
            list_pro.add(new ItemModel("1", "Galaxy Minstrels", "https://refreshmentshop.co.uk/images/large/galaxy-minstrels.jpg"));
            list_pro.add(new ItemModel("1", "Princes Tuna Chunks", "http://www.tonyson.ng/2834-home_default/princess-tuna-chunk-in-sun-flower-oil.jpg"));

            BlankAdapter2 blankAdapter21  = new BlankAdapter2(list_pro, context);
            holder.rv_pro.setAdapter(blankAdapter21);

            holder.ll_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.ll_rvs.getVisibility() == View.VISIBLE){
                        holder.ll_rvs.setVisibility(View.GONE);
                    } else if (holder.ll_rvs.getVisibility() == View.GONE){
                        holder.ll_rvs.setVisibility(View.VISIBLE);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            RecyclerView rv_sub, rv_pro;
            LinearLayout ll_rvs;
            RelativeLayout ll_main;
            public ViewHolder(View itemView) {
                super(itemView);
                ll_main = itemView.findViewById(R.id.ll_blank_main);
                ll_rvs = itemView.findViewById(R.id.ll_blank);

                rv_sub = itemView.findViewById(R.id.rv_blank_sub);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                rv_sub.setLayoutManager(layoutManager);

                rv_pro = itemView.findViewById(R.id.rv_blank_pro);
                RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(context, 3);
                rv_pro.setLayoutManager(layoutManager1);

            }
        }

        private class BlankAdapter2 extends RecyclerView.Adapter<BlankAdapter2.ViewHolder>{

            ArrayList<ItemModel> lists = new ArrayList<>();
            Context contextaa;

            public BlankAdapter2(ArrayList<ItemModel> lists, Context context) {
                this.lists = lists;
                this.contextaa = context;
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_sample, parent, false);

                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                Glide.with(contextaa).load(list.get(position).getUrl()).into(holder.imageView);
            }

            @Override
            public int getItemCount() {
                return lists.size();
            }

            public class ViewHolder extends RecyclerView.ViewHolder {

                ImageView imageView;
                public ViewHolder(View itemView) {
                    super(itemView);
                    imageView = itemView.findViewById(R.id.thumbnail);
                }
            }
        }

    }
}
