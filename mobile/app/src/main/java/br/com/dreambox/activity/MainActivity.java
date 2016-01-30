package br.com.dreambox.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchBox.SearchListener;
import com.quinny898.library.persistentsearch.SearchResult;

import br.com.dreambox.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.search_box)
    public SearchBox search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupSearchBox();
    }

    public void setupSearchBox() {
        search.enableVoiceRecognition(this);
        for(int x = 0; x < 10; x++){
            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.ic_clear));
            search.addSearchable(option);
        }
        search.setSearchListener(new SearchListener(){

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
                Toast.makeText(MainActivity.this, searchTerm +"onSearchTermChanged", Toast.LENGTH_LONG).show();
            }


            @Override
            public void onSearch(String searchTerm) {
                Toast.makeText(MainActivity.this, searchTerm +" Searched", Toast.LENGTH_LONG).show();
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
}
