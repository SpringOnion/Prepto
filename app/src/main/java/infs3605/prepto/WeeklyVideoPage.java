package infs3605.prepto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    Intent intent;

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
        if (getQuizResults(week, username)) {
            intent = new Intent(WeeklyVideoPage.this, QuizReviewActivity.class);
            Toast.makeText(this, "Press next to go to the quiz review", Toast.LENGTH_SHORT).show();
        } else {
            intent = new Intent(WeeklyVideoPage.this, QuizPage.class);
            Toast.makeText(this, "Press next to go to the quiz", Toast.LENGTH_SHORT).show();
        }
        intent.putExtra("Week", week);
        intent.putExtra("Student", username);

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
                Toast.makeText(WeeklyVideoPage.this, "Going to quiz", Toast.LENGTH_SHORT).show();
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

    private boolean getQuizResults(int week, String username) {
        SQLiteDatabase db = DatabaseHelper.getInstance(this).getReadableDatabase();
        String query = "SELECT quiz FROM results WHERE quiz='" + week + "' AND student='" + username + "'; ";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }
}
