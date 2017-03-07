package com.example.amk.lacara;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;


public class UserActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    private GestureDetectorCompat detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Create new director for gesture detection
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

        camera.setOnClickListener(new View.OnClickListener()
        {
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
    public boolean onDown(MotionEvent motionEvent)
    {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent)
    {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent)
    {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1)
    {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent)
    {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            //Swipe left and right
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if(Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    if (diffX > 0 ) {
                        //Swipe to right to reach this
                        Intent manualIntent = new Intent(UserActivity.this, ManualEnterActivity.class);
                        UserActivity.this.startActivity(manualIntent);
                    }
                    else {
                        //swipe to left to reach this
                        Intent budgetOverviewIntent = new Intent(UserActivity.this, BudgetOverviewActivity.class);
                        UserActivity.this.startActivity(budgetOverviewIntent);
                    }
                    result = true;
                }
            }

            //Swipe down and up
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                if (diffY > 0 ) {
                    //Swipe down to reach this page
                    Intent calendarIntent = new Intent(UserActivity.this, CalendarActivity.class);
                    UserActivity.this.startActivity(calendarIntent);
                }
                else {
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
    public boolean onTouchEvent(MotionEvent event){
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public boolean onSwipe(Direction direction)
    {
        return false;
    }
/*
    public Direction getDirection(float x1, float y1, float x2, float y2)
    {
        double angle = getAngle(x1,y1,x2,y2);
        return Direction.get(angle);
    }

    public double getAngle(float x1, float y1, float x2, float y2)
    {
        double rad = Math.atan(y1-y2,x2-x1) + Math.PI;
        return (rad*180/Math.PI + 180)%360;
    }*/

    public enum Direction
    {
        up, down, left, right;
        public static Direction get(double angle)
        {
            if (inRange(angle, 45, 135))
            {
                return Direction.up;
            }
            else if (inRange(angle,0,45) || inRange(angle, 315, 360))
            {
                return Direction.right;
            }
            else if (inRange(angle, 225, 315))
            {
                return Direction.down;
            }
            else
            {
            return Direction.left;
            }
        }
        public static boolean inRange(double angle, float init, float end)
        {
            return (angle >= init) && (angle < end);
        }
    }
}
//testingg