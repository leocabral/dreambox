package br.com.dreambox.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;

import br.com.dreambox.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @Bind(R.id.img_avatar)
    CircularImageView imgAvatar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        bindProfile();
    }

    private void bindProfile() {
        imgAvatar.setImageResource(R.drawable.avatar_12);
        userNickname.setText("Mario");
        userMail.setText("leonardo@gessmail.com");
        userLocationAge.setText("9 anos, Campinas - SP");
        userFoundation.setText("Fundação do Sonhador");
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Editar", Toast.LENGTH_LONG).show();
            }
        });
    }
}
