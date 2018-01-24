[![](https://jitpack.io/v/liqinew/mydalog.svg)](https://jitpack.io/#liqinew/mydalog)
[![](https://img.shields.io/badge/%E4%BD%9C%E8%80%85-%E6%9D%8E%E5%A5%87-orange.svg)](https://github.com/LiqiNew)

# MyDalog
MyDalog是一个Dialog辅助工具。减轻繁琐的代码调用。
# 如何使用

#### 如想更简单的使用，请下载demo阅读。

#### Gradle远程依赖 ####
**1：在项目根目录build.gradley**	<br>

```gradle
allprojects {
　　repositories {
  　　//依赖仓库
　　　maven { url 'https://jitpack.io' }
　　}
}
```

**2：依赖WidgetUtils**<br>

```gradle
compile 'com.github.liqinew:mydalog:V.1.0.0'
```

# A P I

### DialogFactory调用API（静态调用）

#### 通过DialogFactory对象去获取DiyDialog，DiyDialog对象API请阅读DiyDialog调用API。
```java
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
	DialogFactory.getDiyDialog(Context context, int theme,int layoutId, boolean mCanceledTouch, boolean mCancelable);

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
	DialogFactory.getDiyDialog(Context context, int layoutId,boolean mCanceledTouch, boolean mCancelable);
```

### DiyDialog调用API（非静态调用）

#### DiyDialog对象继承Dialog

```java
   /**
     * 设置dialog布局里面所有控件点击事件
     * <p>
     *     如果要让DiyDialog内部去监听点击事件，那么请在控件里面设置android:clickable="true"
     * </p>
     *
     * @param mClickInterfac 控件点击事件接口
     * @return DiyDialog对象
     */
    DiyDialog.setDialogViewOnClickInterfac(DialogViewOnClickInterfac mClickInterfac);

    /**
     * 通过控件ID获取控件
     *
     * @param widgetId 控件ID
     * @param <T>      基类view的泛型
     * @return T
     */
   DiyDialog.$(@IdRes int widgetId);

    /**
     * 获取存储控件的集合
     *
     * @return 控件的集合
     */
    DiyDialog.getAllView();

    /**
     * 设置DialogXY轴移动数和dialog显示位置还有透明度
     *
     * @param x       X轴
     * @param y       Y轴
     * @param f       透明度
     * @param gravity 显示位置（上，下，左，右，居中）位置
     * @return DiyDialog对象
     */
    DiyDialog.setDialogXYAndWH(int x, int y, float f, int gravity);

    /**
     * 设置dialog的宽度|高度|显示位置
     *
     * @param width   宽度
     * @param height  高度
     * @param gravity 显示位置（上，下，左，右，居中）位置
     * @return DiyDialog对象
     */
    DiyDialog.setDialogWH(int width, int height, int gravity);

    /**
     * 设置dialog显示的（上，下，左，右，居中）位置
     *
     * @param gravity 显示位置（上，下，左，右，居中）位置
     * @see Gravity
     *
     * @return 当前dialog的Window对象
     *
     */
    DiyDialog.setDialogLocation(int gravity);

    /**
     * 清除dialog数据
     *
     */
    DiyDialog.clear();
```
