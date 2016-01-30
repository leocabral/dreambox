package br.com.dreambox.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.dreambox.R;
import br.com.dreambox.adapter.DreamAdapter;

public class ProfileActivity extends AppCompatActivity {

    private DreamAdapter dreamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }


}
