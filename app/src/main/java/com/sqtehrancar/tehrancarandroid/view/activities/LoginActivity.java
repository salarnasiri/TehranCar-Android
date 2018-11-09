package com.sqtehrancar.tehrancarandroid.view.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.sqtehrancar.tehrancarandroid.R;
import com.sqtehrancar.tehrancarandroid.contorller.network.ApiService;
import com.sqtehrancar.tehrancarandroid.contorller.network.RetrofitClientInstance;
import com.sqtehrancar.tehrancarandroid.model.LoginInfo;
import com.sqtehrancar.tehrancarandroid.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
                if (validate()) {
                    ApiService service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
                    Call<User> call = service.loginUser(new LoginInfo(edittextsigninUsername.getText().toString(),
                            edittextsigninPassword.getText().toString()));
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                            if (response.isSuccessful()) {
                                if (checkboxSigninAdmin.isChecked()) {
                                    startActivity(new Intent(LoginActivity.this, ListCarsAdminActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, response.body().getUsername() + " Logged In", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, ListCarsActivity.class));
                                    finish();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Logging Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.button_signup:
                startActivity(new Intent(LoginActivity.this,WebViewActivity.class));
                break;
        }
    }

    public boolean validate() {
        boolean valid = true;

        String email = edittextsigninUsername.getText().toString();
        String password = edittextsigninPassword.getText().toString();

        if (email.isEmpty()) {
            edittextsigninUsername.setError("enter a valid email address");
            requestFocus(edittextsigninUsername);
            valid = false;
        } else {
            edittextsigninUsername.setError(null);
        }

        if (password.isEmpty()) {
            edittextsigninPassword.setError("Password is empty");
            requestFocus(edittextsigninPassword);
            valid = false;
        } else {
            edittextsigninPassword.setError(null);
        }

        return valid;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}

