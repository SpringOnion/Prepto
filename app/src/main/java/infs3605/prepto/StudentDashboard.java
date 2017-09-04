package infs3605.prepto;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StudentDashboard extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mDrawerAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dashboard);
        mActivityTitle = "Student Dashboard";
        mDrawerList = (ListView) findViewById(R.id.navigationList);
        addDrawerItems(); //calls the function below, which is hardcoded to add the specific items
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawer();
        //TODO: Add Content
    }

    //function to add items to the navigation drawers
    private void addDrawerItems() {
        //create array with the name of the buttons
        String[] menuArray = {"Calendar", "Reminders", "Settings", "Help"};
        //create array adapter which lets you put the array into the list
        mDrawerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuArray);
        //add the list to the element (list view)
        mDrawerList.setAdapter(mDrawerAdapter);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };
    }
}



