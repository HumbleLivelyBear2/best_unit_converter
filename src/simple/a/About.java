package simple.a;

import java.util.HashMap;


import android.app.Activity;
import android.os.Bundle;



public class About extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setTheme(android.R.style.Theme_Dialog);
		setTitle(R.string.about_us);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us);
		
		
	}
}