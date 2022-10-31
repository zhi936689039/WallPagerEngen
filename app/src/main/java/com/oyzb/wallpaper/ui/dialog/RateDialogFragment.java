package com.oyzb.wallpaper.ui.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.oyzb.wallpaper.R;
import com.oyzb.wallpaper.util.LogUtil;
import com.oyzb.wallpaper.util.ToastUtils;
import com.oyzb.wallpaper.util.ValidateUtils;


public class RateDialogFragment extends DialogFragment {
    private FeedBackDialogFragment mDialogFragment;
    @NonNull
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_rate, null);
        Dialog dialog = new Dialog(getContext(), R.style.MaterialDialogSheet);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        mDialogFragment=new FeedBackDialogFragment();
        event(view);
        return dialog;
    }
    @Override
    public void dismiss() {
        if (getFragmentManager()==null){
            LogUtil.w("fragment", "dismiss: "+this+" not associated with a fragment manager." );
        }else {
            super.dismissAllowingStateLoss();
        }
    }
    private void event(View view){
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
        view.findViewById(R.id.ll_rate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogFragment.show(getFragmentManager(),"dialog");
                dismissAllowingStateLoss();
            }
        });
        view.findViewById(R.id.iv_start5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidateUtils.hasInstallSoft(getContext(), "com.android.vending")) {
                    dismissAllowingStateLoss();
                    final String GOOGLE_PLAY = "com.android.vending";
                    Uri uri = Uri.parse("market://details?id=com.live.wallpaper");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage(GOOGLE_PLAY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    ToastUtils.showShort(getContext().getResources().getString(R.string.google_play_install_tip));
                }
            }
        });
    }
}
