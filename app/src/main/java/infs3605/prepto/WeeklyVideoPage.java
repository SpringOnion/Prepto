package infs3605.prepto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class WeeklyVideoPage extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    static final String GOOGLE_API_KEY = "AIzaSyClFw7LYLSxONvMZmyQz0L-eQ5Cz9GAIcM";
    static String YOUTUBE_VIDEO_ID;
    //private YouTubePlayerView youTubeView;
    YouTubePlayerFragment youTubePlayerFragment;
    FloatingActionButton buttonNext;
    TextView contentWeek;
    TextView contentTitle;
    TextView contentDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_video_page_test);

        int week = getIntent().getExtras().getInt("Week");
        final String username = getIntent().getExtras().getString("Student");
        if (week == 1) {
            YOUTUBE_VIDEO_ID = "JvXro0dzJY8";
        } else if (week == 2) {
            YOUTUBE_VIDEO_ID = "KOxdqo4eAZM";
        } else {
            YOUTUBE_VIDEO_ID = "sbXe__EtGg4";
        }
        contentWeek = findViewById(R.id.contentWeek);
        contentWeek.setText("W E E K 2");

        contentTitle = findViewById(R.id.contentTopic);
        contentTitle.setText("Ethernet Protocol");

        contentDesc = findViewById(R.id.text_manuscript);
        contentDesc.setText("In this video, you will learn about the basics of how Ethernet LAN Protocol and Network Connections work.");

        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeplayerfragment);
        youTubePlayerFragment.initialize(GOOGLE_API_KEY, this);
        buttonNext = findViewById(R.id.button_video_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int week = getIntent().getExtras().getInt("Week");
                Intent intent = new Intent(WeeklyVideoPage.this, QuizPage.class);
                intent.putExtra("Week", week);
                intent.putExtra("Student", username);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        final int REQUEST_CODE = 1;
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YoutubePlayer (%1$S", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }

    }

}
