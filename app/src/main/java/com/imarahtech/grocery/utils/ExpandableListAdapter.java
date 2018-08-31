package com.imarahtech.grocery.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imarahtech.grocery.R;
import com.imarahtech.grocery.model.Category;
import com.imarahtech.grocery.model.SubCategory;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<Category> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<Category, ArrayList<SubCategory>> _listDataChild;

    public ExpandableListAdapter(Context context, List<Category> listDataHeader,
                                 HashMap<Category, ArrayList<SubCategory>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition,  int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

//        ArrayList<SubCategory> list = this._listDataChild.get(this._listDataHeader.get(groupPosition));

        ArrayList<SubCategory> list = new ArrayList<>();

        list.add(new SubCategory("1", "Apple (Royal gala)", "https://bestapples.com/wp-content/uploads/2018/01/ambrosia-apple.jpg"));
        list.add(new SubCategory("1", "Masafi Apple juice", "https://hybrisprod.azureedge.net/sys-master-prod/h52/hb5/8957477421086/636594_main.jpg_480Wx480H"));
        list.add(new SubCategory("1", "Lays Chili", "https://supermart.ae/6479-large_default/lays-chili-185g.jpg"));
        list.add(new SubCategory("1", "Galaxy Minstrels", "https://refreshmentshop.co.uk/images/large/galaxy-minstrels.jpg"));


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expandable_child, parent, false);
        }

        RecyclerView recyclerView = convertView.findViewById(R.id.rv_subcat);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(_context);
        recyclerView.setLayoutManager(layoutManager1);

        HomeAdapter homeAdapter1 = new HomeAdapter(list,_context);
        recyclerView.setAdapter(homeAdapter1);

        RecyclerView rv_child = convertView.findViewById(R.id.lblListItem);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(_context, 3);
        rv_child.setLayoutManager(layoutManager);
        rv_child.setNestedScrollingEnabled(false);

        HomeAdapter homeAdapter = new HomeAdapter(list,_context);
        rv_child.setAdapter(homeAdapter);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
       Category headerItem = (Category) getGroup(groupPosition);
        String headerTitle = headerItem.getName();
        String headerIvURL = headerItem.getImage();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expandable_parent, parent,false);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setText(headerTitle);

        ImageView iv_image = (ImageView) convertView
                .findViewById(R.id.iv_listHeader);
        Glide.with(_context)
                .load(headerIvURL)
                .into(iv_image);


        if (isExpanded) {
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.ic_up_ashdark, 0);
        } else {
            lblListHeader.setTypeface(null, Typeface.NORMAL);
            lblListHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.ic_down_ashdark, 0);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

        List<SubCategory> arrayList = new ArrayList<>();
        Context context;

        public HomeAdapter(List<SubCategory> arrayList, Context context) {
            this.arrayList = arrayList;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_shopbycategory, parent,false);
            return new ViewHolder(view1);
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
            public ViewHolder(View itemView) {
                super(itemView);

                iv_image = itemView.findViewById(R.id.image);
                tv_name = itemView.findViewById(R.id.tv_category_grid);

            }
        }
    }
}