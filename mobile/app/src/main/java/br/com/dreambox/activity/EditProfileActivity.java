package br.com.dreambox.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;

import br.com.dreambox.R;
import br.com.dreambox.api.DreamboxApi;
import br.com.dreambox.model.Dream;
import br.com.dreambox.model.Dreamer;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EditProfileActivity extends AppCompatActivity {

    @Bind(R.id.img_avatar)
    ImageView imgAvatar;

    @Bind(R.id.dreamer_name)
    AppCompatEditText dreamerName;

    @Bind(R.id.dreamer_last_name)
    AppCompatEditText dreamerLastName;

    @Bind(R.id.dreamer_birthday)
    AppCompatEditText dreamerBirthday;

    @Bind(R.id.dreamer_organization)
    AppCompatEditText dreamerOrganization;

    @Bind(R.id.dreamer_nickname)
    AppCompatEditText dreamerNickname;

    @Bind(R.id.dreamer_email)
    AppCompatEditText dreamerEmail;

    @Bind(R.id.button_edit_dreamer)
    Button buttonEditDreamer;

    private Dreamer dreamer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        dreamer = Parcels.unwrap(getIntent().getExtras().getParcelable("dreamer"));
        setFields(dreamer);
    }

    private void setFields(Dreamer dreamer) {
        dreamerName.setText(dreamer.getName());
        dreamerLastName.setText(dreamer.getLastName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        dreamerBirthday.setText(dateFormat.format(dreamer.getBirthday()));
        dreamerBirthday.setEnabled(false);
        dreamerOrganization.setText(dreamer.getFoundation());
        dreamerNickname.setText(dreamer.getNickname());
        dreamerEmail.setText(dreamer.getEmail());
        buttonEditDreamer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateDreamer(getDreamerFromFields());
            }
        });
    }

    private void updateDreamer(Dreamer dreamer) {
        DreamboxApi.get().putDreamer(dreamer.getCode(), dreamer.getName(), dreamer.getLastName(),
                dreamer.getNickname(), dreamer.getEmail(), dreamer.getFoundation(), new Callback<JsonObject>() {

            @Override
            public void success(JsonObject jsonObject, Response response) {
                Toast.makeText(getBaseContext(), "Sonhador atualizado!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getBaseContext(), "Sonhador não atualizado!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private Dreamer getDreamerFromFields() {
        dreamer.setName(dreamerName.getText().toString());
        dreamer.setLastName(dreamerLastName.getText().toString());
        dreamer.setFoundation(dreamerOrganization.getText().toString());
        dreamer.setNickname(dreamerNickname.getText().toString());
        dreamer.setEmail(dreamerEmail.getText().toString());

        return dreamer;
    }
}
