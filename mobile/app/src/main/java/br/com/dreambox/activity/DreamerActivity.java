package br.com.dreambox.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;

import java.util.Calendar;
import br.com.dreambox.R;
import br.com.dreambox.activity.HomeActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DreamerActivity extends AppCompatActivity implements DatePickerDialogFragment.DatePickerDialogHandler{

    @Bind(R.id.date_field)
    EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dreamer_acitivity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_create_dreamer)
    public void createDreamer() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @OnClick(R.id.date_field)
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
        date.setText(d);
    }
}
