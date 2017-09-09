package infs3605.prepto;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class WeeklyVideoPage extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    static final String GOOGLE_API_KEY = "AIzaSyClFw7LYLSxONvMZmyQz0L-eQ5Cz9GAIcM";
    static final String YOUTUBE_VIDEO_ID = "x5xz__S6Q_c";
    private static final String TAG = "WeeklyVideoPage";
    private YouTubePlayerView youTubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_video_page);

        youTubeView = findViewById(R.id.youtube_view);
        youTubeView.initialize(GOOGLE_API_KEY, this);
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
