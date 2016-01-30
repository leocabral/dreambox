package br.com.dreambox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import br.com.dreambox.R;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @OnClick(R.id.sign_up_button)
    protected void signUp() {
        startActivity(new Intent(this, HomeActivity.class));
    }
}
