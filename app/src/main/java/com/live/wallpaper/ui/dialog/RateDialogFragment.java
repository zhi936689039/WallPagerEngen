package com.live.wallpaper.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.live.wallpaper.R;
import com.live.wallpaper.util.ValidateUtils;

import butterknife.BindView;


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
    }
}
