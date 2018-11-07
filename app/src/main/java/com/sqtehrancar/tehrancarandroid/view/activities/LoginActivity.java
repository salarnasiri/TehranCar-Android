package com.sqtehrancar.tehrancarandroid.view.activities;



import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.sqtehrancar.tehrancarandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edittext_signin_username)
    AppCompatEditText edittextsigninUsername;
    @BindView(R.id.edittext_signin_password)
    AppCompatEditText edittextsigninPassword;
    @BindView(R.id.checkbox_signin_admin)
    AppCompatCheckBox checkboxSigninAdmin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
        checkboxSigninAdmin.setTypeface(ResourcesCompat.getFont(LoginActivity.this, R.font.font_normal));
    }


    @OnClick({R.id.button_signin, R.id.button_signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_signin:
                break;
            case R.id.button_signup:
                break;
        }
    }
}
