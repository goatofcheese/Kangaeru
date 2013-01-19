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
        Button but = (Button) findViewById(R.id.returnbutton);
        but.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i;
				i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			}
		});
        
        //dickaboots
        Zinnia test = new Zinnia();
        long chr = test.zinnia_character_new();
        String input = "(character (width 1000)(height 1000)Å@(strokes ((243 273)(393 450))((700 253)(343 486)(280 716)(393 866)(710 880))))";
        test.zinnia_character_parse(chr, input);
        //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_notecard, menu);
        return true;
    }
}
