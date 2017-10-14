package infs3605.prepto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class StudentDashboard extends AppCompatActivity {

    Toolbar toolbar;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    Button b1;
    Button b2;
    String username;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayAdapter<String> mDrawerAdapter;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        username = getIntent().getExtras().getString("Username");

        b1 = (Button) findViewById(R.id.student_view_1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentDashboard.this, WeeklyVideoPage.class);
                intent.putExtra("Week", 1);
                intent.putExtra("Student", username);
                startActivity(intent);
            }
        });

        b2 = (Button) findViewById(R.id.student_view_2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StudentDashboard.this, WeeklyVideoPage.class);
                intent.putExtra("Week", 2);
                intent.putExtra("Student", username);
                startActivity(intent);
            }
        });

        /* This is the old button function
        buttonNext = (Button) findViewById(R.id.button_test);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentDashboard.this, WeeklyVideoPage.class);
                startActivity(intent);
            }
        });
        */


        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        //mDrawerList = (ListView) findViewById(R.id.navigation_view);
        Toolbar studentToolbar = (Toolbar) findViewById(R.id.StudentToolbar);
        setSupportActionBar(studentToolbar);
        //addDrawerItems(); //calls the function below, which is hardcoded to add the specific items
        //setupDrawer();
        /*
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //this is the part that links the navigation to new pages
            //each case corresponds to the item in the list (0 is first, 1 is second, etc.)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0: //calendar atm, TODO set these up
                        Intent a = new Intent(StudentDashboard.this, CalendarPage.class);
                        startActivity(a);
                        break;
                    case 1: //reminders  - TODO the others here
                        Intent intent = new Intent(StudentDashboard.this, LoginPage.class);
                        startActivity(intent);
                        break;
                    case 2: // settings
                        Intent settings = new Intent(StudentDashboard.this, LoginPage.class);
                        startActivity(settings);
                        break;
                    case 3: //help
                        Intent help = new Intent(StudentDashboard.this, LoginPage.class);
                        startActivity(help);
                        break;
                    default:
                }
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        */

    }


    //function to add items to the navigation drawers
    /*
    private void addDrawerItems() {
        //create array with the name of the buttons
        String[] menuArray = {"Calendar", "Reminders", "Settings", "Help"};
        //create array adapter which lets you put the array into the list
        mDrawerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuArray);
        //add the list to the element (list view)
        mDrawerList.setAdapter(mDrawerAdapter);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
    */


}



