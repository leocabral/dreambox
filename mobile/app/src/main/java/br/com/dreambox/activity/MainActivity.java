package br.com.dreambox.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.dreambox.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.target_clouds_tutorial)
    View mClouds;

    @Bind(R.id.robot)
    View mRobot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        showTutorialScreen();
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
