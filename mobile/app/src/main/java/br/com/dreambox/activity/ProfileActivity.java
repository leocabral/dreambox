package br.com.dreambox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import org.parceler.Parcels;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import br.com.dreambox.R;
import br.com.dreambox.model.Dreamer;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @Bind(R.id.img_avatar)
    CircularImageView imgAvatar;

    @Bind(R.id.user_name)
    TextView userName;

    @Bind(R.id.user_nickname)
    TextView userNickname;

    @Bind(R.id.user_mail)
    TextView userMail;


    @Bind(R.id.user_location_age)
    TextView userLocationAge;

    @Bind(R.id.user_foundation)
    TextView userFoundation;

    @Bind(R.id.edit_profile)
    FloatingActionButton editProfile;

    private Dreamer dreamer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        dreamer = getDreamer();
        bindProfile(dreamer);
    }

    private void bindProfile(final Dreamer dreamer) {
        imgAvatar.setImageResource(R.drawable.avatar_12);
        userName.setText(dreamer.getName() + " " + dreamer.getLastName());
        userNickname.setText(dreamer.getNickname());
        userMail.setText(dreamer.getEmail());
        userLocationAge.setText(getAgeAndLocation(dreamer));
        userFoundation.setText(dreamer.getFoundation());
        editProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("dreamer", Parcels.wrap(dreamer));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private String getAgeAndLocation(Dreamer dreamer) {
        int age = getAge(dreamer.getBirthday());
        String strAge = String.valueOf(age) + " anos";
        return strAge + ", " + dreamer.getCity() + " - " + dreamer.getState();
    }

    private int getAge(Date birthday) {
        Calendar today = Calendar.getInstance();
        Calendar bday = Calendar.getInstance();
        bday.setTime(birthday);
        int age = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < bday.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == bday.get(Calendar.MONTH) &&
                        today.get(Calendar.DAY_OF_MONTH) <= bday.get(Calendar.DAY_OF_MONTH))) {
            age++;
        }
        return age;
    }

    public Dreamer getDreamer() {

        Dreamer dreamer = new Dreamer();

        dreamer.setName("Leonardo");
        dreamer.setLastName("Sibela");
        dreamer.setNickname("Mario");
        dreamer.setEmail("leonardo@gessmail.com");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(2010, 1, 14);
        dreamer.setBirthday(calendar.getTime());
        dreamer.setCity("Campinas");
        dreamer.setState("SP");
        dreamer.setFoundation("Fundação do Sonhador");

        return dreamer;
    }
}