package com.example.amk.lacara;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;

public class UserActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
private GestureDetectorCompat detector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        detector = new GestureDetectorCompat(this,this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final EditText entEmailLogin = (EditText) findViewById(R.id.entEmailLogin);

        Intent intent = getIntent();
        String useremail = intent.getStringExtra("useremail");

        String message = useremail + "is logged in!";
        final Button camera = (Button) findViewById(R.id.BTcamera);
        final Button graphs = (Button) findViewById(R.id.BTGraphs);
        final Button calendar = (Button) findViewById(R.id.BTCalendar);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent cameraIntent = new Intent(UserActivity.this, CameraActivity.class);
                UserActivity.this.startActivity(cameraIntent);
            }
        });
        graphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent graphsIntent = new Intent(UserActivity.this, GraphsActivity.class);
                UserActivity.this.startActivity(graphsIntent);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent calendarIntent = new Intent(UserActivity.this, GraphsActivity.class);
                UserActivity.this.startActivity(calendarIntent);
            }
        });

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

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
        try {
            //up to down swipe
            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Intent manualIntent = new Intent(UserActivity.this, ManualEnterActivity.class);
                    UserActivity.this.startActivity(manualIntent);
                    return true;
            }
            //down to up swipe
            else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Intent budgetOverviewIntent = new Intent(UserActivity.this, BudgetOverviewActivity.class);
                UserActivity.this.startActivity(budgetOverviewIntent);
                return true;
            }

                // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Intent calendarIntent = new Intent(UserActivity.this, CalendarActivity.class);
                UserActivity.this.startActivity(calendarIntent);
                return true;
            }
            // left to right swipe
            else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Intent graphsIntent = new Intent(UserActivity.this, GraphsActivity.class);
                UserActivity.this.startActivity(graphsIntent);
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
