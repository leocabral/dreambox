package br.com.dreambox.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.dreambox.R;
import br.com.dreambox.api.DreamboxApi;
import br.com.dreambox.model.Dream;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DreamActivity extends AppCompatActivity {

    @Bind(R.id.dream_tag_container)
    public TagContainerLayout tagContainerLayout;

    @Bind(R.id.dream_text_tag)
    public EditText textTag;

    @Bind(R.id.dream_add_tag)
    public Button btnAddTag;

    @Bind(R.id.dream_title)
    public TextView dreamTitle;

    @Bind(R.id.dream_description)
    public TextView dreamDescription;

    public List<String> listTag = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream);
        ButterKnife.bind(this);

        setupTagGroup();
    }

    @OnClick(R.id.create_dream_button)
    protected void createDream() {
        Dream dream = new Dream(dreamTitle.getText().toString(), dreamDescription.getText().toString());

        DreamboxApi.get().addDream(dream.getTitle(), dream.getDescription(), 5639445604728832L,
                new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject jsonObject, Response response) {
                        Toast.makeText(getApplicationContext(), "Sonho criado com sucesso", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), "Erro, tente novamente", Toast.LENGTH_SHORT).show();
                    }
        });
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @OnClick(R.id.dream_add_tag)
    public void addTag() {
        tagContainerLayout.addTag(textTag.getText().toString());
    }

    public void setupTagGroup() {
        tagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(final int position, String text) {
                AlertDialog dialog = new AlertDialog.Builder(DreamActivity.this)
                        .setTitle("Excluir tag")
                        .setMessage("Deseja excluir essa tag ?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tagContainerLayout.removeTag(position);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }

            @Override
            public void onTagLongClick(int position, String text) {
                return;
            }

        });

        tagContainerLayout.setTags(listTag);
    }
}

