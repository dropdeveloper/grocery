package com.imarahtech.grocery.fragment;


import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imarahtech.grocery.R;
import com.imarahtech.grocery.activity.SearchActivity;
import com.imarahtech.grocery.callback.ViewByCallBack;
import com.imarahtech.grocery.dialogs.ViewByDialog;
import com.imarahtech.grocery.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopCategoryFragment extends Fragment implements ViewByCallBack{

    //0 if grid, 1 if list, based on actionbar view listener, initially 0.
    private static int  ViewType;
    ArrayList<SubCategory> list = new ArrayList<>();
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;

    public ShopCategoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        ViewType = arguments.getInt("VIEW_TYPE");

        recyclerView = view.findViewById(R.id.rv_shop_cat);
        RecyclerView.LayoutManager layoutManager;
        if (ViewType == 0){
            layoutManager = new GridLayoutManager(getContext(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getContext());
        }
        recyclerView.setLayoutManager(layoutManager);


        list.add(new SubCategory("1", "Vegetables", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpF_Xtu4cVWGl18IHo3QoQoK8IL-l2018_iHcaPcSH3l1SDaxGkA"));
        list.add(new SubCategory("1", "Fruits", "https://www.organicfacts.net/wp-content/uploads/2013/05/Fruits3.jpg"));
        list.add(new SubCategory("1", "snacks", "https://www.dollargeneral.com/media/catalog/category/SNACKSCOOKIES.jpg"));
        list.add(new SubCategory("1", "drinks", "https://i.dietdoctor.com/wp-content/uploads/2017/04/Dietdoctor_Dietdrinks.jpg?auto=compress%2Cformat&w=1200&h=979&fit=crop"));
        list.add(new SubCategory("1", "Bakery", "https://s21425.pcdn.co/wp-content/uploads/2015/10/Bakery-Products-300x214.jpg"));
        list.add(new SubCategory("1", "Cosmetics", "data:image/jpeg;base64,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"));
        list.add(new SubCategory("1", "Beauty & Hygeine", "http://poljane-prom.hr/wp-content/uploads/2017/06/Hair-products-570x321.jpg"));


        categoryAdapter = new CategoryAdapter(list, getContext(), ViewType);
        recyclerView.setAdapter(categoryAdapter);

        TextView tv_viewby = view.findViewById(R.id.tv_viewby);
        tv_viewby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewByDialog dialogFragment = new ViewByDialog(getContext(), ShopCategoryFragment.this);
            }
        });


    }

    @Override
    public void viewTypeCallback(int view_type) {
        ViewType = view_type;
        RecyclerView.LayoutManager layoutManager;
        if (ViewType ==0){
            layoutManager= new GridLayoutManager(getContext(), 2);
        } else {
            layoutManager= new LinearLayoutManager(getContext());
        }
        recyclerView.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(list, getContext(), ViewType);
        recyclerView.setAdapter(categoryAdapter);
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

        List<SubCategory> arrayList = new ArrayList<>();
        int VT;
        Context context;

        public CategoryAdapter(List<SubCategory> arrayList, Context context, int VT) {
            this.arrayList = arrayList;
            this.context = context;
            this.VT = VT;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            if (VT == 0){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_category_sv_grid, parent,false);
            } else {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_category_sv_list, parent,false);
            }
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            SubCategory model = arrayList.get(position);

            holder.tv_name.setText(model.getName());

            Glide.with(context)
                    .load(arrayList.get(position).getImage())
                    .into(holder.iv_image);

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_image;
            TextView tv_name;
            LinearLayout ll_main;
            public ViewHolder(View itemView) {
                super(itemView);

                ll_main = itemView.findViewById(R.id.ll_shop_cat);
                iv_image = itemView.findViewById(R.id.iv_cat);
                tv_name = itemView.findViewById(R.id.tv_catname);

                ll_main.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SubCategory model = arrayList.get(getPosition());

                        Intent ii = new Intent(context, SearchActivity.class);
                        ii.putExtra("CATEGORY_ID", model.getID());
                        ii.putExtra("CATEGORY_NAME", model.getName());
                        ii.putExtra("CASE","2");
                        ii.putExtra("SHOP_ID", "1");
                        ii.putExtra("SHOP_NAME", "Al madeena");
                        startActivity(ii);
                    }
                });

            }
        }
    }

}
