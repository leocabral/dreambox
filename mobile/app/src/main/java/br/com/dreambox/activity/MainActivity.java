package br.com.dreambox.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchBox.SearchListener;
import com.quinny898.library.persistentsearch.SearchResult;
import android.view.View;

import br.com.dreambox.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.search_box)
    public SearchBox search;

    @Bind(R.id.target_clouds_tutorial)
    View mClouds;

    @Bind(R.id.robot)
    View mRobot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupSearchBox();
        showTutorialScreen();
    }

    public void setupSearchBox() {
        search.enableVoiceRecognition(this);
        for(int x = 0; x < 10; x++){
            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.ic_clear));
            search.addSearchable(option);
        }
        search.setSearchListener(new SearchListener() {

            @Override
            public void onSearchOpened() {
                Toast.makeText(MainActivity.this, "onSearchOpened", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSearchClosed() {
                Toast.makeText(MainActivity.this, "onSearchClosed", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onSearchTermChanged(String searchTerm) {
                Toast.makeText(MainActivity.this, searchTerm + "onSearchTermChanged", Toast.LENGTH_LONG).show();
            }


            @Override
            public void onSearch(String searchTerm) {
                Toast.makeText(MainActivity.this, searchTerm + " Searched", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResultClick(SearchResult result) {
                Toast.makeText(MainActivity.this, result + " onResultClick", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSearchCleared() {
                Toast.makeText(MainActivity.this, "onSearchCleared", Toast.LENGTH_LONG).show();
            }

        });

    }

    private void showTutorialScreen() {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);
        sequence.setConfig(config);

        String gotIt = getString(R.string.got_it);

        sequence.addSequenceItem(mClouds, getString(R.string.clouds_instruction), gotIt);
        sequence.addSequenceItem(mRobot, getString(R.string.robot_instruction), gotIt);

        sequence.start();
    }
}
