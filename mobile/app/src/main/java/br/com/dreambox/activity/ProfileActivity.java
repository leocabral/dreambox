package br.com.dreambox.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.mikhaellopez.circularimageview.CircularImageView;

import br.com.dreambox.R;
import br.com.dreambox.adapter.DreamAdapter;
import br.com.dreambox.api.DreamboxApi;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProfileActivity extends AppCompatActivity {

    private DreamAdapter dreamAdapter;

    @Bind(R.id.img_avatar)
    CircularImageView imgAvatar;

    @Bind(R.id.user_nickname)
    TextView userNickname;

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
    }

    private void bindProfile() {



    }


}
