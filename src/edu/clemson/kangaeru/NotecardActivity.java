package edu.clemson.kangaeru;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class NotecardActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notecard);
        setTitle("Notecards");
        Button but = (Button) findViewById(R.id.returnbutton);
        but.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i;
				i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_notecard, menu);
        return true;
    }
}
