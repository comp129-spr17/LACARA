package com.example.amk.lacara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BudgetOverviewActivity extends AppCompatActivity {
    MyDBHandler dbHandler;
    TextView budget;
    TextView spent;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_overview);

        dbHandler = new MyDBHandler(this, null, null, 1);
        budget =(TextView)findViewById(R.id.budget);
        spent =(TextView)findViewById(R.id.spent);
        update = (Button)findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //budget.setText(String.valueOf(dbHandler.getBudget()));
                //budget.setText("100");
                spent.setText(String.valueOf(dbHandler.getTotalSpending()));
            }
        });
    }
}
