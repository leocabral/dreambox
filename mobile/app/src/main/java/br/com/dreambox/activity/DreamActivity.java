package br.com.dreambox.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.dreambox.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class DreamActivity extends AppCompatActivity {

    @Bind(R.id.dream_tag_container)
    public TagContainerLayout tagContainerLayout;

    @Bind(R.id.dream_text_tag)
    public EditText textTag;

    @Bind(R.id.dream_add_tag)
    public Button btnAddTag;

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

