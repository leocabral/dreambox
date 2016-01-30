package br.com.dreambox.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchBox.SearchListener;
import com.quinny898.library.persistentsearch.SearchResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import br.com.dreambox.R;
import br.com.dreambox.api.DreamboxApi;
import br.com.dreambox.listener.OnSwipeTouchListener;
import br.com.dreambox.model.Dream;
import br.com.dreambox.util.ViewUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class MainActivity extends AppCompatActivity {

    private static final String HOME_SHOWCASE = "homeShowcase";

    private List<Dream> dreams;

    @Bind(R.id.search_box)
    SearchBox search;

    @Bind(R.id.target_clouds_tutorial)
    View mCloudsTarget;

    @Bind(R.id.robot)
    View mRobot;

    @Bind(R.id.dream_card_container)
    FrameLayout mDreamCardContainer;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    private View currentDream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.dreams = new ArrayList<>();

        setupSearchBox();
        showTutorialScreen();
        setupDrawer();
    }

    public void setupSearchBox() {
        search.enableVoiceRecognition(this);
        for (int x = 0; x < 10; x++) {
            // linha abaixo adiciona sugestões da busca baseado no que já foi digitado
            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.ic_clear));
            search.addSearchable(option);
        }
        search.setSearchListener(new SearchListener() {

        DreamboxApi.get().dreams(new Callback<JsonArray>() {
            @Override
            public void success(JsonArray jsonElements, Response response) {
                //Toast.makeText(MainActivity.this, jsonElements.toString(), Toast.LENGTH_LONG).show();
                for (int x = 0; x < jsonElements.size(); x++) {
                    // linha abaixo adiciona sugestões da busca baseado no que já foi digitado
                    JsonObject obj = (JsonObject) jsonElements.getAsJsonArray().get(x);
                    String title = obj.get("name_search").getAsString();
                    String descr = obj.get("description").getAsString();
                    Dream d = new Dream(title, descr);
                    try {
                        d.fromJson(obj);
                        MainActivity.this.dreams.add(d);
                    } catch (JSONException e) {e.printStackTrace();}

                    SearchResult option = new SearchResult(title, getResources().getDrawable(R.drawable.ic_clear));
                    search.addSearchable(option);
                }
            }

            @Override
            public void failure(RetrofitError error) {Toast.makeText(MainActivity.this, "API GET got wrong ;-;", Toast.LENGTH_LONG).show();}
        });

        search.setSearchListener(new SearchListener() {
            @Override
            public void onSearchOpened() {
                //Toast.makeText(MainActivity.this, "onSearchOpened", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSearchClosed() {
                // quando a aba de possíveis respostas fecha
                //Toast.makeText(MainActivity.this, "onSearchClosed", Toast.LENGTH_LONG).show();
                //search.revealFromMenuItem();
            }

            @Override
            public void onSearchTermChanged(String searchTerm) {
                //toda vez que uma letra mudar e consequentemente alterar as sugestões de busca
                //Toast.makeText(MainActivity.this, searchTerm + "onSearchTermChanged", Toast.LENGTH_LONG).show();
                //if (searchTerm.length() < 4)  return;

            }


            @Override
            public void onSearch(String searchTerm) {
                //Toast.makeText(MainActivity.this, searchTerm + " Searched", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResultClick(final SearchResult result) {
                // basicamente esse é o método quando um resultado é selecionado
                if (currentDream == null) {
                    currentDream = LayoutInflater.from(MainActivity.this).inflate(R.layout.card_dream_detail, null, false);

                    CardDreamHolder cd = new CardDreamHolder(currentDream);

                    for(Dream d : MainActivity.this.dreams)
                        if (d.getTitle().equalsIgnoreCase(result.toString().trim())) {
                            cd.description.setText(d.getDescription());
                            cd.dreamTitle.setText(d.getTitle());
                            cd.nameDreamer.setText(d.getDreamer().getName());
                            break;
                        }

                    MainActivity.this.mDreamCardContainer.addView(currentDream);

                    DisplayMetrics displayMetrics = ViewUtils.getDisplayMetrics(MainActivity.this);
                    ScaleAnimation anim = new ScaleAnimation(0.1f, 1f, 0.1f, 1f,
                            displayMetrics.widthPixels / 2, displayMetrics.heightPixels / 2);
                    anim.setDuration(400);

                    anim.setFillAfter(true);
                    currentDream.startAnimation(anim);

                    mDreamCardContainer.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {

                        @Override
                        public void onSwipeTop() {
                            removeCurrentDream();
                        }
                    });

                    return;
                }

                removeCurrentDream();
            }

            @Override
            public void onSearchCleared() {
                //Toast.makeText(MainActivity.this, "onSearchCleared", Toast.LENGTH_LONG).show();
            }
        });

        search.setMenuListener(new SearchBox.MenuListener() {

            @Override
            public void onMenuClick() {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }

        });
    }

    // Esse método seria pro teste de uso do searchBox, abrindo outra tela
    //private void openCard(String result){
    //Intent i = new Intent(this, Classe.class);
    //i.putExtra(DREAMER, result);
    //startActivity(i);
    //}

    private void showTutorialScreen() {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, HOME_SHOWCASE);
        sequence.setConfig(config);

        String gotIt = getString(R.string.got_it);

        sequence.addSequenceItem(mCloudsTarget, getString(R.string.clouds_instruction), gotIt);
        sequence.addSequenceItem(mRobot, getString(R.string.robot_instruction), gotIt);

        sequence.start();
    }

    private void setupDrawer() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);

                switch (item.getItemId()) {
                    case R.id.item_1:
                        return true;
                    case R.id.item_2:
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    @OnClick(R.id.clouds)
    protected void cloudClicked() {
        if (currentDream == null) {
            currentDream = LayoutInflater.from(this).inflate(R.layout.card_dream_detail, null, false);

            CardDreamHolder cd = new CardDreamHolder(currentDream);
            DreamboxApi.get().getRandomDream(new Callback<JsonArray>() {
                @Override
                public void success(JsonArray jsonElements, Response response) {
                    JsonObject obj = jsonElements.get(0).getAsJsonObject();
                    String re = obj.get("name_title").getAsString();

                }

                @Override
                public void failure(RetrofitError error) {Toast.makeText(MainActivity.this, "API GET got wrong ;-;", Toast.LENGTH_LONG).show();}
            });

            DreamboxApi.get().getRandomDream(new Callback<JsonObject>() {
                @Override
                public void success(JsonObject jsonObject, Response response) {
                    Toast.makeText(MainActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                    Dream dream = new Dream();
                    dream = dream.fromJson(jsonObject);
                    //TODO load info into currentDream
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
            mDreamCardContainer.addView(currentDream);

            DisplayMetrics displayMetrics = ViewUtils.getDisplayMetrics(this);
            ScaleAnimation anim = new ScaleAnimation(0.1f, 1f, 0.1f, 1f,
                    displayMetrics.widthPixels / 2, displayMetrics.heightPixels / 2);
            anim.setDuration(400);

            anim.setFillAfter(true);
            currentDream.startAnimation(anim);

            mDreamCardContainer.setOnTouchListener(new OnSwipeTouchListener(this) {

                @Override
                public void onSwipeTop() {
                    removeCurrentDream();
                }
            });

            return;
        }

        removeCurrentDream();
    }

    private void removeCurrentDream() {
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(300);
        animation.setFillAfter(true);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDreamCardContainer.removeView(currentDream);
                currentDream = null;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        currentDream.startAnimation(animation);
    }

    public class CardDreamHolder {
        @Bind(R.id.name_dreamer)
        TextView nameDreamer;

        @Bind(R.id.dream_title)
        TextView dreamTitle;

        @Bind(R.id.description_dream)
        TextView description;

        public CardDreamHolder(View v) {
            ButterKnife.bind(this, v);
        }

        @OnClick(R.id.follow_button)
        public void followClick() {

        }

        @OnClick(R.id.share_button)
        public void shareClick() {

        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }
}
