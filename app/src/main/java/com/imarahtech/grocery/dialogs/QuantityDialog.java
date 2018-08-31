package com.imarahtech.grocery.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.imarahtech.grocery.R;

import java.util.ArrayList;

public class QuantityDialog extends Dialog {

    RecyclerView rv_quantity;
    ArrayList<QuantityItem> list = new ArrayList<>();
    TextView tv_title;

    public QuantityDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_quantity);

        tv_title = findViewById(R.id.tv_quantity);
        tv_title.setText("Select Quantity");

        rv_quantity = findViewById(R.id.rv_quantity);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext());
        rv_quantity.setLayoutManager(layoutManager);

        list.add(new QuantityItem("2", "1"));
        list.add(new QuantityItem("2", "2"));
        list.add(new QuantityItem("2", "3"));
        list.add(new QuantityItem("2", "4"));
        list.add(new QuantityItem("2", "5"));
        list.add(new QuantityItem("2", "6"));
        list.add(new QuantityItem("2", "7"));


        QuantityAdapter quantityAdapter = new QuantityAdapter(list, getContext());
        rv_quantity.setAdapter(quantityAdapter);


    }

    private  class QuantityAdapter extends RecyclerView.Adapter<QuantityAdapter.ViewHolder>{

        Context context;
        ArrayList<QuantityItem> list = new ArrayList<>();

        public QuantityAdapter(ArrayList<QuantityItem> list, Context context) {
            this.context = context;
            this.list  = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_quantity, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            QuantityItem item = list.get(position);
            holder.tv_name.setText(item.getName());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_name;
            ViewHolder(View itemView) {
                super(itemView);

                tv_name = itemView.findViewById(R.id.tv_quantity);

                tv_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });

            }
        }
    }

    private class QuantityItem{

        String id;
        String name;

        public QuantityItem(String id, String name) {
            this.id = id;
            this.name = name;
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
    }
}
