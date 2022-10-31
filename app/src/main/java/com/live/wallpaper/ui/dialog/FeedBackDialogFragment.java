package com.live.wallpaper.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.live.wallpaper.R;
import com.live.wallpaper.present.MainPresent;
import com.live.wallpaper.util.ValidateUtils;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FeedBackDialogFragment extends DialogFragment {
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_suggestion)
    EditText et_suggestion;
    @BindView(R.id.tv_ok)
    TextView tv_ok;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    private Unbinder unbinder;
    private MainPresent mPresent;
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_feed_back, null);
        Dialog dialog = new Dialog(getContext(), R.style.MaterialDialogSheet);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        unbinder= ButterKnife.bind(this,view);
        mPresent=new MainPresent();
        onEvent();
        return dialog;
    }
    private void onEvent(){
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.feedBack(getContext(),et_email.getText().toString(),et_suggestion.getText().toString());
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(ValidateUtils.isValidate(unbinder)){
            unbinder.unbind();
        }
    }
}
