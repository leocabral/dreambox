package br.com.dreambox.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.dreambox.R;
import butterknife.OnClick;

public class DreamerAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dreamer);
    }

    @OnClick(R.id.button_create_dreamer)
    public void createDreamer() {
        startActivity(new Intent(this, HomeActivity.class));
    }
}
