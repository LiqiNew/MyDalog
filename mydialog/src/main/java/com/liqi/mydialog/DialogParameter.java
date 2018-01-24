package com.liqi.mydialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;


/**
 * Dialog参数对象
 *
 * @author Liqi
 */
class DialogParameter {
    /**
     * 存储布局里面控件ID集合
     */
    final SparseArray<Object> mArrayIds = new SparseArray<>();
    /**
     * 上下文
     */
    Context mContext;
    /**
     * 布局View
     */
    View mLayoutView;
    /**
     * 默认点击屏幕消失
     */
    boolean mCanceledTouch = true;
    /**
     * 默认可以点击返回按键
     */
    boolean mCancelable = true;
    /**
     * 主题
     */
    int mTheme = R.style.RemindDialogStyle;
    /**
     * 布局里面的控件点击接口
     */
    DialogViewOnClickInterfac mClickInterfac;

    DialogParameter(Context mContext) {
        this.mContext = mContext;
    }

    DialogParameter(Context mContext, int mTheme) {
        this.mContext = mContext;
        this.mTheme = mTheme;
    }

    /**
     * 数据清除
     */
    void clear() {
        if (null != mLayoutView)
            mLayoutView = null;

        if (null != mArrayIds && mArrayIds.size() > 0)
            mArrayIds.clear();

        if (null != mClickInterfac)
            mClickInterfac = null;
        System.gc();
    }
}
