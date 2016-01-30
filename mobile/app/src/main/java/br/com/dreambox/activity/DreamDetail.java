package br.com.dreambox.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.dreambox.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DreamDetail extends AppCompatActivity {

    @Bind(R.id.follow)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_detail);
        ButterKnife.bind(this);
        setUpFloatingActionButton();
    }

    private void setUpFloatingActionButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Seguindo esse sonho !!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
