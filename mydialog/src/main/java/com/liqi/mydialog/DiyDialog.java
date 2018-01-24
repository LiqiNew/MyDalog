package com.liqi.mydialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Dialog自定义对象
 *
 * @author Liqi
 */
public class DiyDialog extends Dialog implements
        View.OnClickListener {
    private DialogParameter mParameter;

    protected DiyDialog(Context context, int theme) {
        super(context, theme);
        ini();
    }

    protected DiyDialog(Context context, boolean cancelable,
                        OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        ini();
    }

    protected DiyDialog(Context context) {
        super(context);
        ini();
    }

    /**
     * Dialog默认参数设置
     */
    private void ini() {

    }

    /**
     * 设置dialog参数对象
     *
     * @param mParameter
     * @return
     */
    private void setParameter(DialogParameter mParameter) {
        this.mParameter = mParameter;
    }

    /**
     * 设置dialog布局里面所有控件点击事件
     *
     * @param mClickInterfac 控件点击事件接口
     * @return DiyDialog对象
     */
    public DiyDialog setDialogViewOnClickInterfac(
            DialogViewOnClickInterfac mClickInterfac) {
        this.mParameter.mClickInterfac = mClickInterfac;
        searchViewId();
        return this;
    }

    /**
     * 通过控件ID获取控件
     *
     * @param widgetId 控件ID
     * @param <T>      基类view的泛型
     * @return T
     */
    public <T extends View> T $(@IdRes int widgetId) {

        if (null != this.mParameter) {
            //把当前控件ID存储进SparseArray里面
            if (null == this.mParameter.mArrayIds
                    || this.mParameter.mArrayIds.size() <= 0)
                searchViewId();

            if (null != this.mParameter.mArrayIds
                    && this.mParameter.mArrayIds.size() > 0) {
                return (T) this.mParameter.mArrayIds.get(widgetId);
            }
        }
        return (T) new Object();
    }

    /**
     * 获取存储控件的集合
     *
     * @return 控件的集合
     */
    public SparseArray<Object> getAllView() {
        if (null != this.mParameter) {
            if (null != this.mParameter.mArrayIds
                    && this.mParameter.mArrayIds.size() > 0) {
                return this.mParameter.mArrayIds;
            } else {
                searchViewId();
                return this.mParameter.mArrayIds;
            }
        } else
            return new SparseArray<>();
    }

    /**
     * 设置DialogXY轴移动数和dialog显示位置还有透明度
     *
     * @param x       X轴
     * @param y       Y轴
     * @param f       透明度
     * @param gravity 显示位置（上，下，左，右，居中）位置
     * @return DiyDialog对象
     */
    public DiyDialog setDialogXYAndWH(int x, int y, float f, int gravity) {
        Window dialogWindow = setDialogLocation(gravity);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = x; // 新位置X坐标
        lp.y = y; // 新位置Y坐标
        if (f > 0)
            lp.alpha = f; // 透明度
        dialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 设置dialog的宽度|高度|显示位置
     *
     * @param width   宽度
     * @param height  高度
     * @param gravity 显示位置（上，下，左，右，居中）位置
     * @return DiyDialog对象
     */
    public DiyDialog setDialogWH(int width, int height, int gravity) {
        Window dialogWindow = setDialogLocation(gravity);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = width; // 设置宽度
        lp.height = height; // 设置高度
        dialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 设置dialog显示的（上，下，左，右，居中）位置
     *
     * @param gravity 显示位置（上，下，左，右，居中）位置
     * @see Gravity
     *
     * @return 当前dialog的Window对象
     *
     */
    public Window setDialogLocation(int gravity) {
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(gravity);
        return dialogWindow;
    }

    /**
     * 清除dialog数据
     *
     */
    public void clear() {
        if (null != this.mParameter)
            this.mParameter.clear();
        super.cancel();
    }

    /**
     * 搜索布局里面的控件存储并设置点击事件监听
     */
    private void searchViewId() {
        if (null != this.mParameter && null != this.mParameter.mLayoutView)
            pollFindWidget(this.mParameter.mLayoutView);
    }

    /**
     * 轮询找到View布局里面的控件
     *
     * @param view
     */
    private void pollFindWidget(View view) {
        if (!(view instanceof ViewGroup)) {
            judgeWdgetId(view, view.getId());
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                for (int j = 0; j < childCount; j++) {
                    View childAt = viewGroup.getChildAt(j);
                    int id = childAt.getId();
                    // 布局轮询获取控件
                    if (childAt instanceof ViewGroup) {
                        judgeWdgetId(childAt, id);
                        pollFindWidget(childAt);
                    } else
                        judgeWdgetId(childAt, id);
                }
                judgeWdgetId(view, view.getId());
            } else
                judgeWdgetId(view, view.getId());
        }
    }

    /**
     * 判断控件是否赋值ID，赋值ID就存储进SparseArray集合里面并设置点击事件
     *
     * @param childAt 控件
     * @param id      控件ID
     */
    private void judgeWdgetId(View childAt, int id) {
        if (id > 0) {
            // 那些控件不能设置点击事件，把它排除掉
            if (!(childAt instanceof ListView)
                    && !(childAt instanceof GridView)
                    && !(childAt instanceof ViewPager)
                    && !(childAt instanceof HorizontalScrollView)
                    && !(childAt instanceof ScrollView)
                    && !(childAt instanceof WebView))
                // 控件必须允许点击才设置点击事件监听
                if (childAt.isClickable())
                    childAt.setOnClickListener(this);

            this.mParameter.mArrayIds.put(id, childAt);
        }
    }

    @Override
    public void onClick(View v) {
        if (null != this.mParameter && null != this.mParameter.mClickInterfac)
            this.mParameter.mClickInterfac.viewOnClick(v);
    }

    /**
     * DiyDialog创建对象
     *
     * @author Liqi
     */
    public static class Builder {
        private final DialogParameter mParameter;

        /**
         * 创建Builder对象
         *
         * @param context  上下文
         * @param layoutId 布局ID
         */
        public Builder(Context context, int layoutId) {
            this.mParameter = new DialogParameter(context);
            this.mParameter.mLayoutView = View.inflate(context, layoutId, null);
        }

        /**
         * 创建Builder对象
         *
         * @param context  上下文
         * @param theme    主题样式
         * @param layoutId 布局ID
         */
        public Builder(Context context, int theme, int layoutId) {
            this.mParameter = new DialogParameter(context, theme);
            this.mParameter.mLayoutView = View.inflate(context, layoutId, null);
        }

        /**
         * 设置点击屏幕是否消失dialog
         *
         * @param mCanceledTouch false 不消失，true 消失
         * @return
         */
        public Builder setCanceledTouch(boolean mCanceledTouch) {
            this.mParameter.mCanceledTouch = mCanceledTouch;
            return this;
        }

        /**
         * 设置dialog是否可以用返回键关闭
         *
         * @param mCancelable true 可以，false 不可以
         * @return
         */
        public Builder setCancelable(boolean mCancelable) {
            this.mParameter.mCancelable = mCancelable;
            return this;
        }

        /**
         * 创建对话框
         *
         * @return
         */
        public DiyDialog create() {
            DiyDialog dialog;
            if (this.mParameter.mTheme > 0)
                dialog = new DiyDialog(this.mParameter.mContext,
                        this.mParameter.mTheme);
            else
                dialog = new DiyDialog(this.mParameter.mContext);

            if (null != this.mParameter.mLayoutView)
                dialog.setContentView(this.mParameter.mLayoutView);

            if (!this.mParameter.mCanceledTouch)
                dialog.setCanceledOnTouchOutside(this.mParameter.mCanceledTouch);// 设置点击屏幕不消失

            if (!this.mParameter.mCancelable)
                dialog.setCancelable(this.mParameter.mCancelable);// 按返回键不可关闭对话框
            dialog.setParameter(this.mParameter);
            return dialog;
        }
    }

}
