package br.com.dreambox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.dreambox.R;
import br.com.dreambox.api.DreamboxApi;
import br.com.dreambox.model.Dreamer;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DreamerActivity extends AppCompatActivity implements DatePickerDialogFragment.DatePickerDialogHandler{

    @Bind(R.id.dreamer_birthday)
    public EditText birthday;

    @Bind(R.id.dreamer_name)
    public EditText name;

    @Bind(R.id.dreamer_email)
    public EditText email;

    @Bind(R.id.dreamer_last_name)
    public EditText lastName;

    @Bind(R.id.dreamer_nickname)
    public EditText nickname;

    @Bind(R.id.dreamer_organization)
    public EditText organization;

    @Bind(R.id.dreamer_password)
    public EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dreamer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_create_dreamer)
    public void createDreamer() {
        Dreamer dreamer = new Dreamer();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
        Date date = null;
        try {
            date = format.parse(birthday.getText().toString());
        } catch (ParseException e) {
        }
        dreamer.setBirthday(date);
        dreamer.setName(name.getText().toString());
        dreamer.setEmail(email.getText().toString());
        dreamer.setFoundation(organization.getText().toString());
        dreamer.setNickname(nickname.getText().toString());
        dreamer.setLastName(lastName.getText().toString());
        dreamer.setPassword(password.getText().toString());

        DreamboxApi.get().addDreamer(dreamer.getName(), dreamer.getLastName(), dreamer.getBirthday(), dreamer.getNickname(),
                dreamer.getPassword(), dreamer.getEmail(), dreamer.getFoundation(),
                new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject jsonObject, Response response) {
                        Toast.makeText(getApplicationContext(), "Perfil criado com sucesso", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), "Erro, tente novamente", Toast.LENGTH_SHORT).show();
                    }
                });

        startActivity(new Intent(this, HomeActivity.class));
    }

    @OnClick(R.id.dreamer_birthday)
    public void pickDate() {
        DatePickerBuilder dpb = new DatePickerBuilder()
                .setFragmentManager(getSupportFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment)
                .setYearOptional(true);
        dpb.show();
    }

    @Override
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
        String d = year + "/" + (monthOfYear+1) + "/" + dayOfMonth;
        birthday.setText(d);
    }
}
