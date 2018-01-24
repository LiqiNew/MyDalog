package com.liqi.mydialog.demo;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.liqi.mydialog.DialogFactory;
import com.liqi.mydialog.DialogViewOnClickInterfac;
import com.liqi.mydialog.DiyDialog;


/**
 * Dialog处理对象
 * <p>
 * 注意看注释和调用方法内部注释
 * 此处使用者可以按照自己的业务去实现对应的一个dialog处理对象。
 * 如果当前要显示的界面context是创造dialog的context，那么就显示出来不在创建一个新dialog。
 * </p>
 * Created by LiQi on 2017/12/9.
 */

public class TestDialogDispose implements DialogViewOnClickInterfac {
    private static TestDialogDispose mTestDialogDispose;
    private Context mContext;
    private DiyDialog mDiyDialog;
    private TextView mDialogHint;

    private TestDialogDispose() {

    }

    public static TestDialogDispose getTestDialogDispose() {
        return mTestDialogDispose = null == mTestDialogDispose ? new TestDialogDispose() : mTestDialogDispose;
    }

    /**
     * 释放静态内存，防止内存泄露和溢出。
     */
    public static void clear() {
        if (null != mTestDialogDispose) {
            mTestDialogDispose.closeDialog();
            mTestDialogDispose.mDiyDialog.clear();
            mTestDialogDispose.mDiyDialog= null;
            mTestDialogDispose.mContext = null;
            mTestDialogDispose.mDialogHint = null;
        }
    }

    private void init(Context context) {
        //判断当前上下文是否是同一个对象
        if (null != mContext && mContext != context) {
            mDiyDialog.clear();
            mDiyDialog = null;
        }
        if (null == mDiyDialog) {
            mDiyDialog = DialogFactory.getDiyDialog(context, R.layout.test_diy_dialog, false, true);
            // 设置Dialog对象里面点击事件监听。如果要让DiyDialog内部去监听点击事件，那么请在控件里面设置android:clickable="true"
            mDiyDialog.setDialogViewOnClickInterfac(this);
            mDiyDialog.setDialogWH(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
            mDialogHint = mDiyDialog.$(R.id.dialog_hint);
        }
        this.mContext = context;
    }

    public void showDialog(Context context, String hint) {
        init(context);
        mDialogHint.setText(hint);
        if (null != mDiyDialog && !mDiyDialog.isShowing()) {
            mDiyDialog.show();
        }
    }

    public void closeDialog() {
        if (null != mDiyDialog && mDiyDialog.isShowing()) {
            mDiyDialog.dismiss();
        }
    }

    @Override
    public void viewOnClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_button:
                mDialogHint.setText("自定义dialog点击了.");
                break;
        }
    }
}
