package com.imarahtech.grocery.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.CheckedTextView;

import com.imarahtech.grocery.R;
import com.imarahtech.grocery.callback.ViewByCallBack;

public class ViewByDialog implements View.OnClickListener{

    Context context;
    ViewByCallBack viewByCallBack;
    int VIEW_TYPE;
    Dialog dialog;

    public ViewByDialog(Context context, ViewByCallBack viewByCallBack) {
        this.context = context;
        this.viewByCallBack = viewByCallBack;
        initialiseView();
    }

    private void initialiseView() {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialogfragment_viewby);
        dialog.show();

        CheckedTextView ctv_list = dialog.findViewById(R.id.ctv_list);
        ctv_list.setOnClickListener(this);
        CheckedTextView ctv_grid = dialog.findViewById(R.id.ctv_grid);
        ctv_grid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ctv_grid:
                VIEW_TYPE = 0;
                break;
            case R.id.ctv_list:
                VIEW_TYPE = 1;
                break;
        }

        viewByCallBack.viewTypeCallback(VIEW_TYPE);
        dialog.dismiss();
    }
}
