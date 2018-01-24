package com.liqi.mydialog;

import android.content.Context;

/**
 * dialog生产工厂
 * 
 * @author Liqi
 * 
 */
public class DialogFactory {
	/**
	 * 获取DiyDialog对象
	 * 
	 * @param context
	 *            上下文
	 * @param theme
	 *            样式
	 * @param layoutId
	 *            布局ID
	 * @param mCanceledTouch
	 *            是否触摸关闭
	 * @param mCancelable
	 *            是否可以使用返回按键
	 * @return DiyDialog对象
	 */
	public static DiyDialog getDiyDialog(Context context, int theme,
			int layoutId, boolean mCanceledTouch, boolean mCancelable) {
		DiyDialog.Builder dialogBuilder = new DiyDialog.Builder(context, theme,
				layoutId);
		dialogBuilder.setCanceledTouch(mCanceledTouch);
		dialogBuilder.setCancelable(mCancelable);
		return dialogBuilder.create();
	}

	/**
	 * 获取DiyDialog对象
	 * 
	 * @param context
	 *            上下文
	 * @param layoutId
	 *            布局ID
	 * @param mCanceledTouch
	 *            是否触摸关闭
	 * @param mCancelable
	 *            是否可以使用返回按键
	 * @return DiyDialog对象
	 */
	public static DiyDialog getDiyDialog(Context context, int layoutId,
			boolean mCanceledTouch, boolean mCancelable) {
		DiyDialog.Builder dialogBuilder = new DiyDialog.Builder(context,
				layoutId);
		dialogBuilder.setCanceledTouch(mCanceledTouch);
		dialogBuilder.setCancelable(mCancelable);
		return dialogBuilder.create();
	}
}
