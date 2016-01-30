package br.com.dreambox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.dreambox.R;
import butterknife.OnClick;

public class DreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream);
    }

    @OnClick(R.id.create_dream_button)
    protected void createDream() {
        startActivity(new Intent(this, ProfileActivity.class));
    }



}
