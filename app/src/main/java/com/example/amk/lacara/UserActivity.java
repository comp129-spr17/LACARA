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

//public class UserActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
public class UserActivity extends GestureDetector.SimpleOnGestureListener
{
    //private GestureDetectorCompat detector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    //@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //detector = new GestureDetectorCompat(this,this);

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
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        float x1 = e1.getX();
        float y1 = e1.getY();

        float x2 = e2.getX();
        float y2 = e2.getY();

        Direction direction = getDirection(x1, y1, x2, y2);
        return onSwipe(direction);

        //        try {
//            //up to down swipe
//            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
//                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                    Intent manualIntent = new Intent(UserActivity.this, ManualEnterActivity.class);
//                    UserActivity.this.startActivity(manualIntent);
//                    return true;
//            }
//            //down to up swipe
//            else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
//                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                Intent budgetOverviewIntent = new Intent(UserActivity.this, BudgetOverviewActivity.class);
//                UserActivity.this.startActivity(budgetOverviewIntent);
//                return true;
//            }
//
//                // right to left swipe
//            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
//                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                Intent calendarIntent = new Intent(UserActivity.this, CalendarActivity.class);
//                UserActivity.this.startActivity(calendarIntent);
//                return true;
//            }
//            // left to right swipe
//            else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
//                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                Intent graphsIntent = new Intent(UserActivity.this, GraphsActivity.class);
//                UserActivity.this.startActivity(graphsIntent);
//                return true;
//            }
//        } catch (Exception e) {
//
//        }
//        return false;
    }

    public boolean onSwipe(Direction direction)
    {
        return false;
    }

    public Direction getDirection(float x1, float y1, float x2, float y2)
    {
        double angle = getAngle(x1,y1,x2,y2);
        return Direction.get(angle);
    }

    public double getAngle(float x1, float y1, float x2, float y2)
    {
        double rad = Math.atan(y1-y2,x2-x1) + Math.PI;
        return (rad*180/Math.PI + 180)%360;
    }

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
//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        detector.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }
}

