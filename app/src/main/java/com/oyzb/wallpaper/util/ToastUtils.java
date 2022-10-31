package com.oyzb.wallpaper.util;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.oyzb.wallpaper.App;
import com.oyzb.wallpaper.R;

public class ToastUtils {

    public static  boolean        isShow = true;
    private static ProgressDialog sDialog;
    private static Toast          mToast;

    /**
     * 短时间显示Toast
     */
    @SuppressLint("InflateParams")
    public static void showShort(CharSequence message) {
        if (isShow) {
            mToast = new Toast(App.getInstance().getApplicationContext());
        }
        View     view     = LayoutInflater.from(App.getInstance().getApplicationContext()).inflate(R.layout.view_toast, null, false);
        TextView textView = view.findViewById(R.id.message);
        textView.setText(message);
        mToast.setView(view);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.BOTTOM, 0, 350);
        mToast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (isShow) {
            mToast = new Toast(context);
        }
        View     view     = LayoutInflater.from(context).inflate(R.layout.view_toast, null, false);
        TextView textView = view.findViewById(R.id.message);
        textView.setText(message);
        mToast.setView(view);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.BOTTOM, 0, 100);
        mToast.show();
        //			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (isShow) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow) {
            mToast = new Toast(context);
        }
        View     view     = LayoutInflater.from(context).inflate(R.layout.view_toast, null, false);
        TextView textView = view.findViewById(R.id.message);
        textView.setText(message);
        mToast.setView(view);
        mToast.setDuration(duration);
        mToast.setGravity(Gravity.BOTTOM, 0, 100);
        mToast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (isShow) {
            Toast.makeText(context, message, duration).show();
        }
    }

    /**
     * 自定义显示Toast的位置
     *
     * @param context
     * @param message
     * @param duration
     */
    private static Toast toast;

    public static void showPosition(Context context, String string) {
        if (isShow)
        // Toast.makeText(context, message,Toast.LENGTH_SHORT).show();
        {
            toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    //进度条对话框

    public static void setProgressDialog(Context context, boolean isShow) {
        if (!isShow) {
            if (sDialog != null) {
                sDialog.dismiss();
            }
            return;
        }

        if (sDialog == null) {

            sDialog = new ProgressDialog(context);
        }
        //设置进度条风格，风格为圆形，旋转的
        sDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //设置ProgressDialog 标题
        //   dialog.setTitle("进度对话框");
        //设置ProgressDialog 提示信息
        sDialog.setMessage("拼命加载中。。。");
        //设置ProgressDialog 标题图标
        //   dialog.setIcon(android.R.drawable.ic_dialog_map);
        //设置ProgressDialog 的一个Button
		/*dialog.setButton("确定", new ProgressDialog.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});*/
        //设置ProgressDialog 的进度条是否不明确
        sDialog.setIndeterminate(false);
        //设置ProgressDialog 是否可以按退回按键取消
        //dialog.setCancelable(true);
        //显示

        sDialog.show();
    }
}
