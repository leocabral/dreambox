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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchBox.SearchListener;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

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

    @Bind(R.id.home_loading)
    ProgressBar mProgressBar;

    private View currentDream;
    private List<Dream> dreams;

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
        search.setHint(getString(R.string.dream_search));

        for (int x = 0; x < 10; x++) {
            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.ic_clear));
            search.addSearchable(option);
        }

        search.setSearchListener(new SearchListener() {
            @Override
            public void onSearchOpened() {
            }

            @Override
            public void onSearchClosed() {
                // quando a aba de possíveis respostas fecha
            }

            @Override
            public void onSearchTermChanged(String searchTerm) {
                DreamboxApi.get().dreams(new Callback<JsonArray>() {
                    @Override
                    public void success(JsonArray jsonElements, Response response) {
                        for (JsonElement x : jsonElements) {
                            JsonObject obj = x.getAsJsonObject();
                            dreams.add(Dream.fromJson(obj));

                            String title = obj.get("name_search").getAsString();

                            SearchResult option = new SearchResult(title, getResources().getDrawable(R.drawable.ic_clear));
                            search.addSearchable(option);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(MainActivity.this, "API GET got wrong ;-;", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onSearch(String searchTerm) {
            }

            @Override
            public void onResultClick(final SearchResult result) {
                // basicamente esse é o método quando um resultado é selecionado
//                d.getTitle().equalsIgnoreCase(result.toString().trim())
            }

            @Override
            public void onSearchCleared() {//Toast.makeText(MainActivity.this, "onSearchCleared", Toast.LENGTH_LONG).show();
            }
        });

        search.setMenuListener(new SearchBox.MenuListener() {

            @Override
            public void onMenuClick() {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

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
                    case R.id.home_item:
                        return true;
                    case R.id.fulfilled_dreams_item:
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
            mProgressBar.setVisibility(ProgressBar.VISIBLE);

            DreamboxApi.get().getRandomDream(new Callback<JsonObject>() {
                @Override
                public void success(JsonObject jsonObject, Response response) {
                    Dream dream = Dream.fromJson(jsonObject);
                    showDreamCardView(dream);
                }

                @Override
                public void failure(RetrofitError error) {
                    System.out.println("Dentro do cloundClicked");
                }
            });

            return;
        }

        removeCurrentDream();
    }

    private void showDreamCardView(Dream dream) {
        currentDream = LayoutInflater.from(MainActivity.this).inflate(R.layout.card_dream_detail, null, false);
        CardDreamViewHolder cd = new CardDreamViewHolder(currentDream);
        cd.fillFields(dream);
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

        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
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

    static class CardDreamViewHolder {

        @Bind(R.id.name_dreamer)
        TextView nameDreamer;

        @Bind(R.id.dream_title)
        TextView dreamTitle;

        @Bind(R.id.description_dream)
        TextView description;

        public CardDreamViewHolder(View v) {
            ButterKnife.bind(this, v);
        }

        public void fillFields(Dream d) {
            this.description.setText(d.getDescription());
            this.dreamTitle.setText(d.getTitle());
            this.nameDreamer.setText(d.getDreamer().getName());
        }

        @OnClick(R.id.follow_button)
        public void followClick() {
            // fazer o code para post de follow :P
        }

        @OnClick(R.id.share_button)
        public void shareClick() {
//            String url = "http://caixa-de-sonhos.appspot.com";
//            Intent i = new Intent();
//            i.setAction(Intent.ACTION_SEND);
//            i.putExtra(Intent.EXTRA_TEXT, url);
//            i.setType("text/plain");
//            startActivity(Intent.createChooser(i, "Compartilhe esse sonho!!"));
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
