package br.com.dreambox.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import br.com.dreambox.R;
import br.com.dreambox.adapter.DreamAdapter;
import butterknife.Bind;

public class MyDreamsActivity extends AppCompatActivity {

    @Bind(R.id.dreams_recycler_view)
    RecyclerView dreamsRecycler;

    @Bind(R.id.add_dream_fab)
    FloatingActionButton addDreamFab;

    private DreamAdapter mDreamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dreams);
    }

    
}
