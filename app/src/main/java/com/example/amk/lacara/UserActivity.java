package com.example.amk.lacara;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.GestureDetector;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class UserActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    private GestureDetectorCompat detector;
    MyDBHandler myDBHandler;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //nav drawer
        mDrawerList = (ListView)findViewById(R.id.navList);

        //toggle switch on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        //Create new director for gesture detection
        detector = new GestureDetectorCompat(this, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar MainToolbar = (Toolbar) findViewById(R.id.MainTB);
        setSupportActionBar(MainToolbar);


        final EditText entEmailLogin = (EditText) findViewById(R.id.entEmailLogin);

        Intent intent = getIntent();
        String useremail = intent.getStringExtra("useremail");

        String message = useremail + "is logged in!";


        final ImageButton settings = (ImageButton) findViewById(R.id.BTSettings);
        final ImageButton camera = (ImageButton) findViewById(R.id.BTCamera);
        final ImageButton graphs = (ImageButton) findViewById(R.id.BTGRAPHS);
        final ImageButton calendar = (ImageButton) findViewById(R.id.BTCalendar);
        final ImageButton manual = (ImageButton) findViewById(R.id.BTManual);
        final ImageButton alert = (ImageButton) findViewById(R.id.BTAlert);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(UserActivity.this, CameraActivity.class);
                UserActivity.this.startActivity(cameraIntent);
            }
        });
        graphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent graphsIntent = new Intent(UserActivity.this, GraphsActivity.class);
                UserActivity.this.startActivity(graphsIntent);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendarIntent = new Intent(UserActivity.this, CalendarActivity.class);
                UserActivity.this.startActivity(calendarIntent);
            }
        });
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manualIntent = new Intent(UserActivity.this, ManualEnterActivity.class);
                UserActivity.this.startActivity(manualIntent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(UserActivity.this, UserSettings.class);
                UserActivity.this.startActivity(settingsIntent);
            }
        });

        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlerts(true);
            }
        });

        //Setting the Budget
        //double temp = myDBHandler.getBudget();
        String currentBudget = "$"+"100.00";//String.valueOf(temp);
        TextView budgetView = (TextView) findViewById(R.id.budgetView);
        budgetView.setText(currentBudget);
    }



//adds items to the nav drawer
    private void addDrawerItems() {
        String[] osArray = { "Manual Entry", "Camera", "Calendar", "Graphs", "Setttings" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

    mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
        }
    });

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    public void showAlerts(boolean overSpend){
            AlertDialog.Builder alert = new AlertDialog.Builder(UserActivity.this);
            alert.setMessage("Warning ! you spend too much this month!");
            alert.setCancelable(false);

            alert.setPositiveButton(
                    "Understand",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            alert.setNegativeButton(
                    "Don't Care",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = alert.create();
            if(overSpend) {
                alert11.show();
            }
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            //Swipe left and right
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    if (diffX > 0) {
                        //Swipe to right to reach this
                        Intent manualIntent = new Intent(UserActivity.this, ManualEnterActivity.class);
                        UserActivity.this.startActivity(manualIntent);
                    } else {
                        //swipe to left to reach this
                        Intent budgetOverviewIntent = new Intent(UserActivity.this, BudgetOverviewActivity.class);
                        UserActivity.this.startActivity(budgetOverviewIntent);
                    }
                    result = true;
                }
            }

            //Swipe down and up
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                if (diffY > 0) {
                    //Swipe down to reach this page
                    //still have some problems swiping down
                    Intent calendarIntent = new Intent(UserActivity.this, CalendarActivity.class);
                    UserActivity.this.startActivity(calendarIntent);
                } else {
                    //Swipe up to reach this page
                    Intent graphsIntent = new Intent(UserActivity.this, GraphsActivity.class);
                    UserActivity.this.startActivity(graphsIntent);
                }
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public boolean onSwipe(Direction direction) {

        return false;
    }



    public enum Direction {
        up, down, left, right;

        public static Direction get(double angle) {
            if (inRange(angle, 45, 135)) {
                return Direction.up;
            } else if (inRange(angle, 0, 45) || inRange(angle, 315, 360)) {
                return Direction.right;
            } else if (inRange(angle, 225, 315)) {
                return Direction.down;
            } else {
                return Direction.left;
            }
        }

        public static boolean inRange(double angle, float init, float end) {
            return (angle >= init) && (angle < end);
        }

    }
}