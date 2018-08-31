package com.imarahtech.grocery.fragment;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imarahtech.grocery.R;
import com.imarahtech.grocery.callback.ViewByCallBack;
import com.imarahtech.grocery.dialogs.OptionsDialog;
import com.imarahtech.grocery.dialogs.QuantityDialog;
import com.imarahtech.grocery.dialogs.ViewByDialog;
import com.imarahtech.grocery.model.Category;
import com.imarahtech.grocery.model.SubCategory;
import com.imarahtech.grocery.utils.ExpandableListAdapter;
import com.imarahtech.grocery.utils.PinchRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopProductFragment extends Fragment implements ViewByCallBack {

//    RecyclerView rv_products;
    RecyclerView rv_products;
    ProductListAdapter productListAdapter;
    ArrayList<ItemModel> productList = new ArrayList<>();
    TextView tv_viewby;

    //0 if grid, 1 if list, based on actionbar view listener, initially 0.
    private static int  ViewType;

    private ScaleGestureDetector mScaleGestureDetector;

    private GridLayoutManager mGridLayoutManager1, mGridLayoutManager2, mGridLayoutManager3;
    private RecyclerView.LayoutManager mCurrentLayoutManager;

    ExpandableListView expandableListView;
    ExpandableListAdapter listAdapter;
    List<Category> listDataHeader = new ArrayList<>();
    HashMap<Category, ArrayList<SubCategory>> listDataChild = new HashMap<Category, ArrayList<SubCategory>>();


    View view;
    public ShopProductFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sv_product, container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        expandableListView = view.findViewById(R.id.rv_shopview_product);

        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);

        expandableListView.setAdapter(listAdapter);
        expandableListView.setGroupIndicator(null);

        listDataHeader.add(new Category("1", "Apple (Royal gala)", "https://bestapples.com/wp-content/uploads/2018/01/ambrosia-apple.jpg"));
        listDataHeader.add(new Category("1", "Masafi Apple juice", "https://hybrisprod.azureedge.net/sys-master-prod/h52/hb5/8957477421086/636594_main.jpg_480Wx480H"));
        listDataHeader.add(new Category("1", "Lays Chili", "https://supermart.ae/6479-large_default/lays-chili-185g.jpg"));
        listDataHeader.add(new Category("1", "Galaxy Minstrels", "https://refreshmentshop.co.uk/images/large/galaxy-minstrels.jpg"));
        listDataHeader.add(new Category("1", "Princes Tuna Chunks", "http://www.tonyson.ng/2834-home_default/princess-tuna-chunk-in-sun-flower-oil.jpg"));
        listDataHeader.add(new Category("1", "Tank Orange", "https://usgroceries.co.uk/wp-content/uploads/sites/5/2016/10/tang_powder_juice_orange_pineapple_1.jpg"));
        listDataHeader.add(new Category("1", "Surf Aloha flash", "https://cdn7.bigcommerce.com/s-um6op5fe4t/products/993/images/1807/Surf_Detergent_Powder_Blossom_Fresh_1100g__06429.1499844609.500.750.jpg?c=2"));
        listDataHeader.add(new Category("1", "London Diary Vanilla", "https://westgatelifecare.com/wp-content/uploads/2017/10/London-diary-double-chocolate-tub-1ltr.jpg"));

        listAdapter.notifyDataSetChanged();

    }

    //    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        Bundle arguments = getArguments();
////        ViewType = arguments.getInt("VIEW_TYPE");
//        ViewType = 0;
//
//        rv_products = view.findViewById(R.id.rv_shopview_product);
////        RecyclerView.LayoutManager layoutManager;
////        if (ViewType ==0){
////            layoutManager= new GridLayoutManager(getContext(), 2 );
////        } else {
////            layoutManager= new LinearLayoutManager(getContext());
////        }
//
//        mGridLayoutManager1 = new GridLayoutManager(getContext(), 1);
//        mGridLayoutManager2 = new GridLayoutManager(getContext(), 2);
//        mGridLayoutManager3 = new GridLayoutManager(getContext(), 3);
//
//
//        mCurrentLayoutManager = mGridLayoutManager2;
//        rv_products.setLayoutManager(mGridLayoutManager2);
//
//        productListAdapter = new ProductListAdapter(getContext(), productList, ViewType);
//        rv_products.setAdapter(productListAdapter);
//
//        productList.add(new ItemModel("1", "Apple (Royal gala)", "https://bestapples.com/wp-content/uploads/2018/01/ambrosia-apple.jpg"));
//        productList.add(new ItemModel("1", "Masafi Apple juice", "https://hybrisprod.azureedge.net/sys-master-prod/h52/hb5/8957477421086/636594_main.jpg_480Wx480H"));
//        productList.add(new ItemModel("1", "Lays Chili", "https://supermart.ae/6479-large_default/lays-chili-185g.jpg"));
//        productList.add(new ItemModel("1", "Galaxy Minstrels", "https://refreshmentshop.co.uk/images/large/galaxy-minstrels.jpg"));
//        productList.add(new ItemModel("1", "Princes Tuna Chunks", "http://www.tonyson.ng/2834-home_default/princess-tuna-chunk-in-sun-flower-oil.jpg"));
//        productList.add(new ItemModel("1", "Tank Orange", "https://usgroceries.co.uk/wp-content/uploads/sites/5/2016/10/tang_powder_juice_orange_pineapple_1.jpg"));
//        productList.add(new ItemModel("1", "Surf Aloha flash", "https://cdn7.bigcommerce.com/s-um6op5fe4t/products/993/images/1807/Surf_Detergent_Powder_Blossom_Fresh_1100g__06429.1499844609.500.750.jpg?c=2"));
//        productList.add(new ItemModel("1", "London Diary Vanilla", "https://westgatelifecare.com/wp-content/uploads/2017/10/London-diary-double-chocolate-tub-1ltr.jpg"));
//
//        productListAdapter.notifyDataSetChanged();
//
//        tv_viewby = view.findViewById(R.id.tv_viewby);
//        tv_viewby.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ViewByDialog dialogFragment = new ViewByDialog(getContext(), ShopProductFragment.this);
//            }
//        });
//
//
//        mScaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener() {
//            @Override
//            public boolean onScale(ScaleGestureDetector detector) {
//
//                //wrong logic
//                    /*if (detector.getScaleFactor() < 0.8) {
//                        if (mCurrentLayoutManager != mGridLayoutManager3) {
//                            mCurrentLayoutManager = mGridLayoutManager3;
//                            mRvPhotos.setLayoutManager(mGridLayoutManager3);
//                        }
//                    } else if (detector.getScaleFactor() < 1.2) {
//                        if (mCurrentLayoutManager != mGridLayoutManager2) {
//                            mCurrentLayoutManager = mGridLayoutManager2;
//                            mRvPhotos.setLayoutManager(mGridLayoutManager2);
//                        }
//                    } else {
//                        if (mCurrentLayoutManager != mGridLayoutManager1) {
//                            mCurrentLayoutManager = mGridLayoutManager1;
//                            mRvPhotos.setLayoutManager(mGridLayoutManager1);
//                        }
//                    }*/
//
//                //wrong logic
//                    /*if (detector.getScaleFactor() < 0.2) {
//                        if (mCurrentLayoutManager == mGridLayoutManager1) {
//                            mCurrentLayoutManager = mGridLayoutManager2;
//                            mRvPhotos.setLayoutManager(mGridLayoutManager2);
//                        } else if (mCurrentLayoutManager == mGridLayoutManager2) {
//                            mCurrentLayoutManager = mGridLayoutManager3;
//                            mRvPhotos.setLayoutManager(mGridLayoutManager3);
//                        }
//                    } else if (detector.getScaleFactor() > 1.2) {
//                        if (mCurrentLayoutManager == mGridLayoutManager3) {
//                            mCurrentLayoutManager = mGridLayoutManager2;
//                            mRvPhotos.setLayoutManager(mGridLayoutManager2);
//                        } else if (mCurrentLayoutManager == mGridLayoutManager2) {
//                            mCurrentLayoutManager = mGridLayoutManager1;
//                            mRvPhotos.setLayoutManager(mGridLayoutManager1);
//                        }
//                    }*/
//
//                //correct logic
//                if (detector.getCurrentSpan() > 200 && detector.getTimeDelta() > 200) {
//                    if (detector.getCurrentSpan() - detector.getPreviousSpan() < -1) {
//                        if (mCurrentLayoutManager == mGridLayoutManager1) {
//                            mCurrentLayoutManager = mGridLayoutManager2;
//                            rv_products.setLayoutManager(mGridLayoutManager2);
//                            return true;
//                        } else if (mCurrentLayoutManager == mGridLayoutManager2) {
//                            mCurrentLayoutManager = mGridLayoutManager3;
//                            rv_products.setLayoutManager(mGridLayoutManager3);
//                            return true;
//                        }
//                    } else if(detector.getCurrentSpan() - detector.getPreviousSpan() > 1) {
//                        if (mCurrentLayoutManager == mGridLayoutManager3) {
//                            mCurrentLayoutManager = mGridLayoutManager2;
//                            rv_products.setLayoutManager(mGridLayoutManager2);
//                            return true;
//                        } else if (mCurrentLayoutManager == mGridLayoutManager2) {
//                            mCurrentLayoutManager = mGridLayoutManager1;
//                            rv_products.setLayoutManager(mGridLayoutManager1);
//                            return true;
//                        }
//                    }
//                }
//
//                return false;
//            }
//
//            @Override
//            public void onScaleEnd(ScaleGestureDetector detector) {
//                super.onScaleEnd(detector);
//
//                    /*if (detector.getCurrentSpan() > 200) {
//                        if (detector.getCurrentSpan() - detector.getPreviousSpan() < 0) {
//                            if (mCurrentLayoutManager == mGridLayoutManager1) {
//                                mCurrentLayoutManager = mGridLayoutManager2;
//                                mRvPhotos.setLayoutManager(mGridLayoutManager2);
//                            } else if (mCurrentLayoutManager == mGridLayoutManager2) {
//                                mCurrentLayoutManager = mGridLayoutManager3;
//                                mRvPhotos.setLayoutManager(mGridLayoutManager3);
//                            }
//                        } else if(detector.getCurrentSpan() - detector.getPreviousSpan() > 0) {
//                            if (mCurrentLayoutManager == mGridLayoutManager3) {
//                                mCurrentLayoutManager = mGridLayoutManager2;
//                                mRvPhotos.setLayoutManager(mGridLayoutManager2);
//                            } else if (mCurrentLayoutManager == mGridLayoutManager2) {
//                                mCurrentLayoutManager = mGridLayoutManager1;
//                                mRvPhotos.setLayoutManager(mGridLayoutManager1);
//                            }
//                        }
//                    }*/
//            }
//        });
//
//        //set touch listener on recycler view
//        rv_products.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                mScaleGestureDetector.onTouchEvent(event);
//                return false;
//            }
//        });
//
//
//    }


    @Override
    public void viewTypeCallback(int view_type) {
        ViewType = view_type;
        RecyclerView.LayoutManager layoutManager;
        if (ViewType ==0){
            layoutManager= new GridLayoutManager(getContext(), 2);
        } else {
            layoutManager= new LinearLayoutManager(getContext());
        }
        rv_products.setLayoutManager(layoutManager);

        productListAdapter = new ProductListAdapter(getContext(), productList, ViewType);
        rv_products.setAdapter(productListAdapter);

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
//            if (VT == 0){
//                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_product_sv_grid, parent, false);
//            } else {
//                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_product_sv_list, parent, false);
//            }

            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_sample, parent, false);


            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            ItemModel album = albumList.get(position);
//            holder.title.setText(album.getName());
//
//            holder.tv_disc.setPaintFlags(holder.tv_disc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
//            // loading album cover using Glide library
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
            LinearLayout ll_quantity_grid, ll_numberpick, ll_item_options;
            MyViewHolder(View view) {
                super(view);
//                title = (TextView) view.findViewById(R.id.title);
//                tv_disc = (TextView) view.findViewById(R.id.tv_disc_price);
                thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
//                bt_add = itemView.findViewById(R.id.bt_add);
//                ll_quantity_grid = itemView.findViewById(R.id.ll_quantity_grid);
//                iv_remove = itemView.findViewById(R.id.iv_remove);
//                ll_numberpick = itemView.findViewById(R.id.ll_numberpick);
//                ll_item_options = itemView.findViewById(R.id.ll_l1);
//
//                ll_item_options.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        OptionsDialog optionsDialog = new OptionsDialog(mContext);
//                        optionsDialog.show();
//                    }
//                });
//
//                iv_remove.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        ll_numberpick.setVisibility(View.GONE);
//                        bt_add.setVisibility(View.VISIBLE);
//                    }
//                });
//
//                bt_add.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        bt_add.setVisibility(View.GONE);
//                        ll_numberpick.setVisibility(View.VISIBLE);
//
//                    }
//                });
//
//
//                ll_quantity_grid.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            QuantityDialog quantityDialog = new QuantityDialog(mContext);
//                            quantityDialog.show();
//
//                        }
//                    });

            }
        }
    }

}
