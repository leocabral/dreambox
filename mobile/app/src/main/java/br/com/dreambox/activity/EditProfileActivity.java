package br.com.dreambox.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.parceler.Parcels;

import br.com.dreambox.R;
import br.com.dreambox.model.Dream;
import br.com.dreambox.model.Dreamer;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Dreamer dreamer = Parcels.unwrap(getIntent().getExtras().getParcelable("dreamer"));
    }
}
