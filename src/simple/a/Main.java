/*package simple.a;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;


public class main extends TabActivity {
    /** Called when the activity is first created. 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main); ---> 不能用
        setTitle("單位轉換器(Unit Exchanger)");
       /// find_and_modify_view();
        
		TabHost tabHost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.main,
				tabHost.getTabContentView(), true);
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("重量")
				.setContent(new Intent(this, tab1.class)));
		//如果叫它去找main中得view,會找不到,因為沒有setContentView
		//所以我們新開一個activity
		//tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("長度")
				//.setContent(new Intent(this, tab2.class)));
		//tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("溫度")
				//.setContent(new Intent(this, tab3.class)));
				
    }
    
    
}

*/


package simple.a;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.adwhirl.AdWhirlManager;
import com.flurry.android.FlurryAgent;
import simple.a.TransparentPanel;
import simple.a.Flurry.DoFlurryType;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

//重量

    

public class Main extends Activity implements AdWhirlInterface{
	
	//設定variables
    Spinner spinner_c;
    Spinner spinner_2;
    int spinner1position=0;
    int spinner2position=0;
    int mode=0; //用來控制數字視窗的切換 mode=0--> Screen1, mode=1-->Screen2
	
    
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button0;
	private Button button_dot;
	private Button button_del;
    private FontFitTextView Screen1;
	private FontFitTextView Screen2;
	private FontFitText_Button showButton;
	private TransparentPanel buttonPanel;
	
	double dp1_number;
	double dp2_number;
	int checknumber;
	int popup_position=0;
	int dotmode=0; //0是未按過,1是按過
	int maxlength=9; //設最大可輸入位數
	//int check_sign=0; //0 is positive, 1 is negative
	
	int layout_choose=0; //0 for tab1, 1 for tab2
	private int mScreenWidth;
	private int mScreenHeight;
	
	/*
	// Google AdSense 行動網頁的網址
	String mAdSenseWebUrl01 = "http://sandjconverter.appspot.com/";
	// Google AdSense 使用的網頁瀏覽元件定義
	WebView mAdSenseWeb01 ;
	*/
	public void onStart()
	{
	   FlurryAgent.onStartSession(this, "W9ELZHJ4FA861KCZDS1K");
	   Flurry.doFlurry(DoFlurryType.FLURRY_TYPE_ON_START_ACTIVITY, this, null);
	   super.onStart();
	   
	   // your code
	}
	
	public void onStop()
	{
		FlurryAgent.onEndSession(this);
		super.onStop();
	   
	   // your code
	}
	
	

	@Override
    public void onCreate(Bundle savedInstanceState) {
		 
		
		
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.tab1);
		 layout_choose=0;
		 //
	     DisplayMetrics dm = new DisplayMetrics();
	     getWindowManager().getDefaultDisplay().getMetrics(dm);
		 int dpi=dm.densityDpi;
	     mScreenWidth = dm.widthPixels;
		 mScreenHeight = dm.heightPixels;
		 if (mScreenHeight<490 && mScreenHeight>=440){
			 this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 }
		 
		 if(mScreenHeight<440) { 
			 setContentView(R.layout.tab2);
			 //layout_choose=1;
			 this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	     }
	     
		 
	     selections = getResources().getStringArray(R.array.catagory_weight);
	     a=getResources().getStringArray(R.array.catagory_weight);
	     b=getResources().getStringArray(R.array.catagory_length);
	     c=getResources().getStringArray(R.array.catagory_temperature);
	     d=getResources().getStringArray(R.array.catagory_speed);
	     e=getResources().getStringArray(R.array.catagory_area);
	     f=getResources().getStringArray(R.array.catagory_volume);
	     g=getResources().getStringArray(R.array.catagory_energy);
	     h=getResources().getStringArray(R.array.catagory_time);
	     i=getResources().getStringArray(R.array.catagory_pressure);
	     j=getResources().getStringArray(R.array.catagory_force);
	     k=getResources().getStringArray(R.array.catagory_power);
	     l=getResources().getStringArray(R.array.catagory_density);
	     m=getResources().getStringArray(R.array.catagory_luminance);
	     n=getResources().getStringArray(R.array.catagory_angle);
	     o=getResources().getStringArray(R.array.catagory_disk);
	     popup = (LinearLayout) findViewById(R.id.popup_window);

	     find_and_modify_view1();
	     find_and_modify_view2();
	     resetCalButton();
	     setMode();
	     initPopup();
	     
	    
	    	 
	    //Create the adView
//	    AdView adView = new AdView(this, AdSize.BANNER,"a14dca65647669e");
//	    LinearLayout layout = (LinearLayout) findViewById(R.id.AdLayout);
//	    layout.addView(adView);
//	    adView.loadAd(new AdRequest());

	     AdWhirlManager.setConfigExpireTimeout(1000 * 60 * 5);
	     
//	     AdWhirlTargeting.setAge(23);
//	     AdWhirlTargeting.setGender(AdWhirlTargeting.Gender.MALE);
//	     AdWhirlTargeting.setKeywords("online games gaming");
//	     AdWhirlTargeting.setPostalCode("94123");
//	     AdWhirlTargeting.setTestMode(true);
	     
	     AdWhirlLayout adWhirlLayout = (AdWhirlLayout)findViewById(R.id.adwhirl_layout);

	     int diWidth = 320;
	     int diHeight = 52;
	     float density =  getResources().getDisplayMetrics().density;
	   
	     adWhirlLayout.setAdWhirlInterface(this);
	     adWhirlLayout.setMaxWidth((int)(diWidth * density));
	     adWhirlLayout.setMaxHeight((int)(diHeight * density));
							
	 
		 
	     
	}
	     

	     
	
	
	
	private void cal_temp1(double aa){
			if  (spinner1position==spinner2position){dp2_number=aa;}
			else if (spinner1position==0 & spinner2position==1){dp2_number=aa*9/5+32;}
			else if (spinner1position==0 & spinner2position==2){dp2_number=aa+273;}
			else if (spinner1position==1 & spinner2position==0){dp2_number=(aa-32)/1.8;}
			else if (spinner1position==1 & spinner2position==2){dp2_number=(aa-32)/1.8+273;}
			else if (spinner1position==2 & spinner2position==0){dp2_number=aa-273;}
			else if (spinner1position==2 & spinner2position==1){dp2_number=(aa-273)*1.8+32;}
     } 
	
	private void cal_temp2(double bb){
		if (spinner2position==spinner1position){dp1_number=bb;}
		else if (spinner2position==0 & spinner1position==1){dp1_number=bb*1.8+32;}
		else if (spinner2position==0 & spinner1position==2){dp1_number=bb+273;}
		else if (spinner2position==1 & spinner1position==0){dp1_number=(bb-32)/1.8;}
		else if (spinner2position==1 & spinner1position==2){dp1_number=(bb-32)/1.8+273;}
		else if (spinner2position==2 & spinner1position==0){dp1_number=bb-273;}
		else if (spinner2position==2 & spinner1position==1){dp1_number=(bb-273)*1.8+32;}
 } 
	
	
	private void setMode() {
		
	if (mode==0){
		
	  if(Screen1.getText().length()<maxlength){
			// click number 1
			    Button.OnClickListener a=new Button.OnClickListener(){
				@Override
		public void onClick(View v) {
					try{
				try {float ans1 = (float) (Float.parseFloat(Screen1.getText().toString()));}
				catch(Exception e){dp1_number=0;Screen1.setText("0");}
				float ans = (float) (Float.parseFloat(Screen1.getText().toString()));
		    if (Screen1.getText().length()<maxlength) {
			///if 選到溫度
			 if (popup_position==2){
				
				String check_sign = (String) Screen1.getText();
				if (ans==0 & check_sign.indexOf("-")!=-1 & Screen1.getText().length()==2){
					Screen1.setText("-1"); dp1_number=-1; cal_temp1(dp1_number);				   				   
				    checknumber = (int)dp2_number; 
		            if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		            else {Screen2.setText(dp2_number+"");};	
				}
				 
				else if (ans==0 & Screen1.getText().length()==1 ){Screen1.setText("1"); dp1_number=1; cal_temp1(dp1_number);				   				   
				     checknumber = (int)dp2_number; 
		             if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		             else {Screen2.setText(dp2_number+"");}; 
		             } 
				else {String ansafter = (Screen1.getText())+"1";Screen1.setText(ansafter);
				       dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
				       cal_temp1(dp1_number);
				       checknumber = (int)dp2_number; 
		               if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		               else {
		            	   NumberFormat.getInstance().setMaximumFractionDigits(5);
		            	   String myString = NumberFormat.getInstance().format(dp2_number);
		            	   Screen2.setText(myString);
		              }
		        }
				 if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	
			}
			 /// 溫度以外
			 else {	
				if (ans==0 & Screen1.getText().length()==1){Screen1.setText("1"); dp1_number=1; dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position]; 
				           checknumber = (int)dp2_number; 
				           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
				           else {Screen2.setText(dp2_number+""); String myString=(String) Screen2.getText(); modify_text_dispaly(myString);} 
				}                              
				else {String ansafter = (Screen1.getText())+"1";
				  if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen1.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp2_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen1.setText(ansafter);
						 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
						 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
					 }  
						
					 checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {
			        	   NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp2_number);
	            	       Screen2.setText(myString);
	            	       modify_text_dispaly(myString);
	            	  }
			    }
				
			}
			 if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}		 
		 }
		
					}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				}			
	  };
			button1.setOnClickListener(a);
			// click number 2
			Button.OnClickListener b=new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					try{
					try {float ans1 = (float) (Float.parseFloat(Screen1.getText().toString()));}
					catch(Exception e){dp1_number=0;Screen1.setText("0");}
				float ans = (float) (Float.parseFloat(Screen1.getText().toString()));
				if (Screen1.getText().length()<maxlength) {     
				if (popup_position==2){
					
					String check_sign = (String) Screen1.getText();
					if (ans==0 & check_sign.indexOf("-")!=-1 & Screen1.getText().length()==2){
						Screen1.setText("-2"); dp1_number=-2; cal_temp1(dp1_number);				   				   
					    checknumber = (int)dp2_number; 
			            if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			            else {Screen2.setText(dp2_number+"");};	
					}
					
					else if (ans==0& Screen1.getText().length()==1){Screen1.setText("2"); dp1_number=2; 
					   cal_temp1(dp1_number);				   				   
					   checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {Screen2.setText(dp2_number+"");}; 
			           } 
					else {String ansafter = (Screen1.getText())+"2";Screen1.setText(ansafter);
					       dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					       cal_temp1(dp1_number);
					       checknumber = (int)dp2_number; 
			               if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			               else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp2_number);
	            	       Screen2.setText(myString);}
			         }
					 if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	
				 }	
				
			 else {	
				if (ans==0& Screen1.getText().length()==1){Screen1.setText("2");dp1_number=2;dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position]; 
		           			checknumber = (int)dp2_number; 
		           			if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		           			else {Screen2.setText(dp2_number+""); String myString=(String) Screen2.getText(); modify_text_dispaly(myString);};
				}
				else {String ansafter = (Screen1.getText())+"2";
				  if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen1.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp2_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen1.setText(ansafter);
						 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
						 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
					 }  
						
				 checknumber = (int)dp2_number; 
		           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		           else {NumberFormat a;
	        	   a=NumberFormat.getInstance();
	        	   a.setMaximumFractionDigits(10);
        	       String myString = a.format(dp2_number);
        	       Screen2.setText(myString);
        	       modify_text_dispaly(myString);
		           };
        	       }
				}
	                 if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	
			}
					}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
			}
			};
			button2.setOnClickListener(b);
			
			// click number 3
			Button.OnClickListener c=new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					try{
					try {float ans1 = (float) (Float.parseFloat(Screen1.getText().toString()));}
					catch(Exception e){dp1_number=0;Screen1.setText("0");}
				float ans = (float) (Float.parseFloat(Screen1.getText().toString()));
				if (Screen1.getText().length()<maxlength) {
				if (popup_position==2){
					
					String check_sign = (String) Screen1.getText();
					if (ans==0 & check_sign.indexOf("-")!=-1 & Screen1.getText().length()==2){
						Screen1.setText("-3"); dp1_number=-3; cal_temp1(dp1_number);				   				   
					    checknumber = (int)dp2_number; 
			            if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			            else {Screen2.setText(dp2_number+"");};	
					}

			
					else if (ans==0& Screen1.getText().length()==1){Screen1.setText("3"); dp1_number=3; 
					   cal_temp1(dp1_number);				   				   
					   checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {Screen2.setText(dp2_number+"");}; 
			           } 
					 else {String ansafter = (Screen1.getText())+"3";Screen1.setText(ansafter);
					       dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					       cal_temp1(dp1_number);
					       checknumber = (int)dp2_number; 
			               if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			               else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp2_number);
	            	       Screen2.setText(myString);};} 
			   if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	 
				}	
				
			 else {			
				if (ans==0& Screen1.getText().length()==1){Screen1.setText("3");dp1_number=3;dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position]; 
		           			checknumber = (int)dp2_number; 
		           			if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		           			else {Screen2.setText(dp2_number+""); String myString=(String) Screen2.getText(); modify_text_dispaly(myString);};
							}
				else {String ansafter = (Screen1.getText())+"3";
				    
				  if (ansafter.indexOf(".")!=-1){
					int intAns;
					
				    Screen1.setText(ansafter);
				   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
				    intAns=Integer.parseInt(intMy);
				     
				    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
				    
				    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
			    	{
				    	dp2_number/=10;
			    	}
				 }  
				    
				 else{
					 Screen1.setText(ansafter);
					 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
				 }  
					
				    
				       checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {NumberFormat a;
		        	   a=NumberFormat.getInstance();
		        	   a.setMaximumFractionDigits(10);
            	       String myString = a.format(dp2_number);
            	       Screen2.setText(myString);
            	       modify_text_dispaly(myString);
			           };}
				}
				if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	
				}
					}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				}
			};
			button3.setOnClickListener(c);
			
			//click number 4
			Button.OnClickListener d=new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					try{
					try {float ans1 = (float) (Float.parseFloat(Screen1.getText().toString()));}
					catch(Exception e){dp1_number=0;Screen1.setText("0");}
				float ans = (float) (Float.parseFloat(Screen1.getText().toString()));
				if (Screen1.getText().length()<maxlength) {
			if (popup_position==2){
					
				String check_sign = (String) Screen1.getText();
				if (ans==0 & check_sign.indexOf("-")!=-1 & Screen1.getText().length()==2){
					Screen1.setText("-4"); dp1_number=-4; cal_temp1(dp1_number);				   				   
				    checknumber = (int)dp2_number; 
		            if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		            else {Screen2.setText(dp2_number+"");};	
				}
				
			        else if (ans==0& Screen1.getText().length()==1){Screen1.setText("4"); dp1_number=4; 
					   cal_temp1(dp1_number);				   				   
					   checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {Screen2.setText(dp2_number+"");}; 
			           } 
					 else {String ansafter = (Screen1.getText())+"4";Screen1.setText(ansafter);
					       dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					       cal_temp1(dp1_number);
					       checknumber = (int)dp2_number; 
			               if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			               else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp2_number);
	            	       Screen2.setText(myString);};} 
					 if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}		 
			}	
				
			 else {	
				if (ans==0& Screen1.getText().length()==1){Screen1.setText("4");dp1_number=4;dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position]; 
		           checknumber = (int)dp2_number; 
		           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		           else {Screen2.setText(dp2_number+"");String myString=(String) Screen2.getText(); modify_text_dispaly(myString);};
		           }
				else {String ansafter = (Screen1.getText())+"4";
				  if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen1.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp2_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen1.setText(ansafter);
						 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
						 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
					 }  
						
					 checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {NumberFormat a;
		        	   a=NumberFormat.getInstance();
		        	   a.setMaximumFractionDigits(10);
            	       String myString = a.format(dp2_number);
            	       Screen2.setText(myString);
            	       modify_text_dispaly(myString);
			           };}
				}
			if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	
				}
					}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				}
			};
			button4.setOnClickListener(d);
			
			//click number 5
			Button.OnClickListener e=new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					try{
					try {float ans1 = (float) (Float.parseFloat(Screen1.getText().toString()));}
					catch(Exception e){dp1_number=0;Screen1.setText("0");}
				float ans = (float) (Float.parseFloat(Screen1.getText().toString()));
				if (Screen1.getText().length()<maxlength) {
				if (popup_position==2){
					
					String check_sign = (String) Screen1.getText();
					if (ans==0 & check_sign.indexOf("-")!=-1 & Screen1.getText().length()==2){
						Screen1.setText("-5"); dp1_number=-5; cal_temp1(dp1_number);				   				   
					    checknumber = (int)dp2_number; 
			            if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			            else {Screen2.setText(dp2_number+"");};	
					}
					
					else if (ans==0& Screen1.getText().length()==1){Screen1.setText("5"); dp1_number=5; 
					   cal_temp1(dp1_number);				   				   
					   checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {Screen2.setText(dp2_number+"");}; 
			           } 
					 else {String ansafter = (Screen1.getText())+"5";Screen1.setText(ansafter);
					       dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					       cal_temp1(dp1_number);
					       checknumber = (int)dp2_number; 
			               if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			               else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp2_number);
	            	       Screen2.setText(myString);};} 
					 if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	 
				}	
				
			 else {	
				if (ans==0& Screen1.getText().length()==1){Screen1.setText("5");dp1_number=5;dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position]; 
		           checknumber = (int)dp2_number; 
		           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		           else {Screen2.setText(dp2_number+"");String myString=(String) Screen2.getText(); modify_text_dispaly(myString);};
		           }
				else {String ansafter = (Screen1.getText())+"5";
				  if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen1.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp2_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen1.setText(ansafter);
						 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
						 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
					 }  
						
					 checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {NumberFormat a;
		        	   a=NumberFormat.getInstance();
		        	   a.setMaximumFractionDigits(10);
            	       String myString = a.format(dp2_number);
            	       Screen2.setText(myString);
            	       modify_text_dispaly(myString);
			           };}
				}
				if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	
				}
					}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				}
			};
			button5.setOnClickListener(e);
			
			//click number 6
			Button.OnClickListener f=new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					try{
					try {float ans1 = (float) (Float.parseFloat(Screen1.getText().toString()));}
					catch(Exception e){dp1_number=0;Screen1.setText("0");}
				float ans = (float) (Float.parseFloat(Screen1.getText().toString()));
				if (Screen1.getText().length()<maxlength) {
			if (popup_position==2){
				      String check_sign = (String) Screen1.getText();
				     if (ans==0 & check_sign.indexOf("-")!=-1 & Screen1.getText().length()==2){
					    Screen1.setText("-6"); dp1_number=-6; cal_temp1(dp1_number);				   				   
				        checknumber = (int)dp2_number; 
		                if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		                else {Screen2.setText(dp2_number+"");};	
				     }
				
				     else if (ans==0& Screen1.getText().length()==1){Screen1.setText("6"); dp1_number=6; 
					   cal_temp1(dp1_number);				   				   
					   checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {Screen2.setText(dp2_number+"");}; 
			           } 
					 else {String ansafter = (Screen1.getText())+"6";Screen1.setText(ansafter);
					       dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					       cal_temp1(dp1_number);
					       checknumber = (int)dp2_number; 
			               if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			               else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp2_number);
	            	       Screen2.setText(myString);};} 
					 if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}		 
			}	
				
			 else {	
				if (ans==0& Screen1.getText().length()==1){Screen1.setText("6");dp1_number=6;dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position]; 
		           checknumber = (int)dp2_number; 
		           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		           else {Screen2.setText(dp2_number+"");};String myString=(String) Screen2.getText(); modify_text_dispaly(myString);}
				else {String ansafter = (Screen1.getText())+"6";
				  if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen1.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp2_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen1.setText(ansafter);
						 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
						 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
					 }  
						
					 checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {NumberFormat a;
		        	   a=NumberFormat.getInstance();
		        	   a.setMaximumFractionDigits(10);
            	       String myString = a.format(dp2_number);
            	       Screen2.setText(myString);
            	       modify_text_dispaly(myString);
			           };}
				}
			if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	
				}
					}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				}
			};
			button6.setOnClickListener(f);
			
			//click number 7
			Button.OnClickListener g=new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					try{
					try {float ans1 = (float) (Float.parseFloat(Screen1.getText().toString()));}
					catch(Exception e){dp1_number=0;Screen1.setText("0");}
				float ans = (float) (Float.parseFloat(Screen1.getText().toString()));
				if (Screen1.getText().length()<maxlength) {
			if (popup_position==2){
				String check_sign = (String) Screen1.getText();
				if (ans==0 & check_sign.indexOf("-")!=-1 & Screen1.getText().length()==2){
					Screen1.setText("-7"); dp1_number=-7; cal_temp1(dp1_number);				   				   
				    checknumber = (int)dp2_number; 
		            if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		            else {Screen2.setText(dp2_number+"");};	
				}
				
				else if (ans==0& Screen1.getText().length()==1){Screen1.setText("7"); dp1_number=7; 
					   cal_temp1(dp1_number);				   				   
					   checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {Screen2.setText(dp2_number+"");}; 
			           } 
			    else {String ansafter = (Screen1.getText())+"7";Screen1.setText(ansafter);
					       dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					       cal_temp1(dp1_number);
					       checknumber = (int)dp2_number; 
			               if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			               else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp2_number);
	            	       Screen2.setText(myString);};} 
					 if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}		 
			}	
				
			 else {		
				if (ans==0& Screen1.getText().length()==1){Screen1.setText("7");dp1_number=7;dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position]; 
		           checknumber = (int)dp2_number; 
		           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		           else {Screen2.setText(dp2_number+"");String myString=(String) Screen2.getText(); modify_text_dispaly(myString);};
		           }
				else {String ansafter = (Screen1.getText())+"7";
				  if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen1.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp2_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen1.setText(ansafter);
						 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
						 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
					 }  
						
					 checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {NumberFormat a;
		        	   a=NumberFormat.getInstance();
		        	   a.setMaximumFractionDigits(10);
            	       String myString = a.format(dp2_number);
            	       Screen2.setText(myString);
            	       modify_text_dispaly(myString);
			           };}
				}
			if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	
				}
					}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				}
			};
			button7.setOnClickListener(g);
			
			//click number 8
			Button.OnClickListener h=new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					try{
					try {float ans1 = (float) (Float.parseFloat(Screen1.getText().toString()));}
					catch(Exception e){dp1_number=0;Screen1.setText("0");}
				float ans = (float) (Float.parseFloat(Screen1.getText().toString()));
				if (Screen1.getText().length()<maxlength) {
				if (popup_position==2){
					String check_sign = (String) Screen1.getText();
					if (ans==0 & check_sign.indexOf("-")!=-1 & Screen1.getText().length()==2){
						Screen1.setText("-8"); dp1_number=-8; cal_temp1(dp1_number);				   				   
					    checknumber = (int)dp2_number; 
			            if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			            else {Screen2.setText(dp2_number+"");};	
					}
					
					else if (ans==0& Screen1.getText().length()==1){Screen1.setText("8"); dp1_number=8; 
					   cal_temp1(dp1_number);				   				   
					   checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {Screen2.setText(dp2_number+"");}; 
			           } 
					 else {String ansafter = (Screen1.getText())+"8";Screen1.setText(ansafter);
				       dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
				       cal_temp1(dp1_number);
					   checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {NumberFormat a;
			        	a=NumberFormat.getInstance();
			        	a.setMaximumFractionDigits(10);
	            	    String myString = a.format(dp2_number);
	            	    Screen2.setText(myString);
	            	    };} 
					 if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	 
				}	
				
			 else {	
				if (ans==0& Screen1.getText().length()==1){Screen1.setText("8");dp1_number=8;dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position]; 
		           checknumber = (int)dp2_number; 
		           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		           else {Screen2.setText(dp2_number+"");String myString=(String) Screen2.getText(); modify_text_dispaly(myString);};
		           }
				else {String ansafter = (Screen1.getText())+"8";
				if (ansafter.indexOf(".")!=-1){
					int intAns;
					
				    Screen1.setText(ansafter);
				   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
				    intAns=Integer.parseInt(intMy);
				     
				    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
				    
				    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
			    	{
				    	dp2_number/=10;
			    	}
				 }  
				    
				 else{
					 Screen1.setText(ansafter);
					 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
				 }  
					 checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {NumberFormat a;
		        	   a=NumberFormat.getInstance();
		        	   a.setMaximumFractionDigits(10);
            	       String myString = a.format(dp2_number);
            	       Screen2.setText(myString);
            	       modify_text_dispaly(myString);
			           };}
				}
				if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	
				}
					}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				}
			};
			button8.setOnClickListener(h);
			
			//click number 9
			Button.OnClickListener i=new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					try{
					try {float ans1 = (float) (Float.parseFloat(Screen1.getText().toString()));}
					catch(Exception e){dp1_number=0;Screen1.setText("0");}
				float ans = (float) (Float.parseFloat(Screen1.getText().toString()));
				if (Screen1.getText().length()<maxlength) {
			if (popup_position==2){
					String check_sign = (String) Screen1.getText();
					if (ans==0 & check_sign.indexOf("-")!=-1 & Screen1.getText().length()==2){
						Screen1.setText("-9"); dp1_number=-9; cal_temp1(dp1_number);				   				   
						checknumber = (int)dp2_number; 
						if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
						else {Screen2.setText(dp2_number+"");};	
					}
				
			         else if (ans==0 & Screen1.getText().length()==1){Screen1.setText("9"); dp1_number=9; 
					   cal_temp1(dp1_number);				   				   
					   checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {Screen2.setText(dp2_number+"");}; 
			           } 
					 else {String ansafter = (Screen1.getText())+"9";Screen1.setText(ansafter);
					       dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					       cal_temp1(dp1_number);
					       checknumber = (int)dp2_number; 
			               if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			               else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp2_number);
	            	       Screen2.setText(myString);};} 
					 if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}		 
			}	
				
			 else {	
				if (ans==0& Screen1.getText().length()==1){Screen1.setText("9");dp1_number=9;dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position]; 
		           checknumber = (int)dp2_number; 
		           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		           else {Screen2.setText(dp2_number+"");String myString=(String) Screen2.getText(); modify_text_dispaly(myString);};
		           }
				else {String ansafter = (Screen1.getText())+"9";
				  if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen1.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp2_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen1.setText(ansafter);
						 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
						 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
					 }  
						
					 checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {NumberFormat a;
		        	   a=NumberFormat.getInstance();
		        	   a.setMaximumFractionDigits(10);
            	       String myString = a.format(dp2_number);
            	       Screen2.setText(myString);
            	       modify_text_dispaly(myString);
			           };}
				}
			if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	
				}
					}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				}
			};
			button9.setOnClickListener(i);
			
			// click number 0
			Button.OnClickListener j=new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					try{
					try {float ans1 = (float) (Float.parseFloat(Screen1.getText().toString()));}
					catch(Exception e){dp1_number=0;Screen1.setText("0");}
				float ans = (float) (Float.parseFloat(Screen1.getText().toString()));
				if (Screen1.getText().length()<maxlength) {
			if (popup_position==2){
				     
				String check_sign = (String) Screen1.getText();
				if (ans==0 & check_sign.indexOf("-")!=-1 & Screen1.getText().length()==2){
					Screen1.setText("-0"); dp1_number=-0; cal_temp1(dp1_number);				   				   
				    checknumber = (int)dp2_number; 
		            if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		            else {Screen2.setText(dp2_number+"");};	
				}
				else if (ans==0& Screen1.getText().length()==1){Screen1.setText("0"); dp1_number=0; 
					   cal_temp1(dp1_number);				   				   
					   checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {Screen2.setText(dp2_number+"");}; 
			           } 
					 else {String ansafter = (Screen1.getText())+"0";Screen1.setText(ansafter);
					       dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					       cal_temp1(dp1_number);
					       checknumber = (int)dp2_number; 
			               if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			               else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp2_number);
	            	       Screen2.setText(myString);};} 
					 if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}		 
			}	
				
			 else {	
				if (ans==0& Screen1.getText().length()==1){Screen1.setText("0");dp1_number=0;dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position]; 
		           checknumber = (int)dp2_number; 
		           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
		           else {Screen2.setText(dp2_number+"");String myString=(String) Screen2.getText(); modify_text_dispaly(myString);};
		           }
				else {String ansafter = (Screen1.getText())+"0";
				  if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen1.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp2_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen1.setText(ansafter);
						 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
						 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
					 }  
						
					 checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			           else {NumberFormat a;
		        	   a=NumberFormat.getInstance();
		        	   a.setMaximumFractionDigits(10);
            	       String myString = a.format(dp2_number);
            	       Screen2.setText(myString);
            	       modify_text_dispaly(myString);
			           };}
				}
			if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}	
				}
					}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				}
			};
			button0.setOnClickListener(j);
			
			// click del
			Button.OnClickListener k=new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
				
			    try{
				try {float ans1 = (float) (Float.parseFloat(Screen1.getText().toString()));}
				catch(Exception e){dp1_number=0;Screen1.setText("0");}
				
				String ansbefore = (String) Screen1.getText();
				if (ansbefore.length()==1){
					if(popup_position!=2){dp2_number=0;dp1_number=0;Screen1.setText("0");Screen2.setText("0");}
					else if (popup_position==2){Screen1.setText("0"); dp1_number=0; 
					cal_temp1(dp1_number);}
				}
				else { 
									 
				    String ansafter = ansbefore.substring(0, ansbefore.length()-1);
				    Screen1.setText(ansafter);
				    
				    try{dp1_number = (float) (Float.parseFloat(Screen1.getText().toString()));
					 
					}catch(Exception e){ Screen1.setText("0"); dp1_number=0; 
					cal_temp1(dp1_number);
					}
				    
				    if (popup_position==2){
				    	String check_minus=(String) Screen1.getText();
				    	if(check_minus.indexOf("-")!=-1){Screen1.setText("0"); dp1_number=0; 
						cal_temp1(dp1_number);
						} 
				    	else{cal_temp1(dp1_number);}
				    }
				    else {
				    	if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen1.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp2_number/=10;
				    	}
					    }  
					    
					    else{
						 Screen1.setText(ansafter);
						 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
						 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
					    }  
				    }
				 }
				       checknumber = (int)dp2_number; 
			           if(checknumber == dp2_number){Screen2.setText(checknumber+""); String myString=(String) Screen2.getText(); modify_text_dispaly(myString);}
			           else {NumberFormat a;
		        	   a=NumberFormat.getInstance();
		        	   a.setMaximumFractionDigits(10);
            	       String myString = a.format(dp2_number);
            	       Screen2.setText(myString);
            	       modify_text_dispaly(myString);
			           };
				   	
				
				if(spinner1position==spinner2position){Screen2.setText(Screen1.getText());}
				}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				}
			};
			button_del.setOnClickListener(k);
			
			// click dot
			Button.OnClickListener l=new Button.OnClickListener(){
				@Override
			     public void onClick(View v) {
					String checkdot = (String) Screen1.getText();
					if (checkdot.indexOf(".")==-1){
				    String ansafter = (Screen1.getText())+".";
				    Screen1.setText(ansafter);
				    }
				 }
			};
			Button.OnLongClickListener lClick=new Button.OnLongClickListener(){
				@Override
			    public boolean onLongClick(View v) {
					String check_sign = (String) Screen1.getText();
					if (popup_position==2 & check_sign.indexOf("-")==-1){ 
						//check_sign=1; 
						String changesign =(String) Screen1.getText();
                        Screen1.setText("-"+changesign);
                        dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					    cal_temp1(dp1_number);
					    checknumber = (int)dp2_number; 
			            if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			            else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp2_number);
	            	       Screen2.setText(myString);}
                    }
					else if(popup_position==2 & check_sign.indexOf("-")!=-1){
						//check_sign=0; 
						String changesign =(String) Screen1.getText();
						Screen1.setText(changesign.substring(1));
						dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					    cal_temp1(dp1_number);
					    checknumber = (int)dp2_number; 
			            if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
			            else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp2_number);
	            	       Screen2.setText(myString);}
					}
						
					return true;
				}
			};
			button_dot.setOnClickListener(l);
			button_dot.setOnLongClickListener(lClick);
		}
	        }else if (mode==1){
	        	// click number 1
	        	Button.OnClickListener a=new Button.OnClickListener(){
					@Override
					public void onClick(View v) {
						try{
						try {float ans1 = (float) (Float.parseFloat(Screen2.getText().toString()));}
	    				catch(Exception e){dp2_number=0;Screen2.setText("0");}
					float ans = (float) (Float.parseFloat(Screen2.getText().toString()));
					if (Screen2.getText().length()<maxlength) {
				 if (popup_position==2){
					 
					   String check_sign = (String) Screen2.getText();
						if (ans==0 & check_sign.indexOf("-")!=-1 & Screen2.getText().length()==2){
							Screen2.setText("-1"); dp2_number=-1; cal_temp2(dp2_number);				   				   
						    checknumber = (int)dp1_number; 
				            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				            else {Screen1.setText(dp1_number+"");};	
						}
					 
						else if (ans==0 & Screen2.getText().length()==1){Screen2.setText("1"); dp2_number=1; 
						   cal_temp2(dp2_number);				   				   
						   checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {Screen1.setText(dp1_number+"");}; 
				           } 
						 else {String ansafter = (Screen2.getText())+"1";Screen2.setText(ansafter);
						       dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						       cal_temp2(dp2_number);
						       checknumber = (int)dp1_number; 
				               if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				               else {NumberFormat a;
				        	   a=NumberFormat.getInstance();
				        	   a.setMaximumFractionDigits(10);
		            	       String myString = a.format(dp1_number);
		            	       Screen1.setText(myString);};} 
						 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}		 
				 }	
					
				 else {	
					
					if (ans==0& Screen2.getText().length()==1){Screen2.setText("1"); dp2_number=1;dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position]; 
			           checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {Screen1.setText(dp1_number+"");};}
	    			else {String ansafter = (Screen2.getText())+"1";
	    			if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen2.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp1_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen2.setText(ansafter);
						 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
					 }  
					 checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {NumberFormat a;
		        	   a=NumberFormat.getInstance();
		        	   a.setMaximumFractionDigits(10);
            	       String myString = a.format(dp1_number);
            	       Screen1.setText(myString);
            	       modify_text_dispaly1(myString);
			           };}
	    			}
				 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}
					}
						}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
					}
	    		};
	    		button1.setOnClickListener(a);
	        
	    		// click number 2
	    		Button.OnClickListener b=new Button.OnClickListener(){
	    			@Override
	    			public void onClick(View v) {
	    				try{
	    				try {float ans1 = (float) (Float.parseFloat(Screen2.getText().toString()));}
	    				catch(Exception e){dp2_number=0;Screen2.setText("0");}
	    			float ans = (float) (Float.parseFloat(Screen2.getText().toString()));
	    			if (Screen2.getText().length()<maxlength) {
	    		 if (popup_position==2){
	    			 
	    			    String check_sign = (String) Screen2.getText();
						if (ans==0 & check_sign.indexOf("-")!=-1 & Screen2.getText().length()==2){
							Screen2.setText("-2"); dp2_number=-2; cal_temp2(dp2_number);				   				   
						    checknumber = (int)dp1_number; 
				            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				            else {Screen1.setText(dp1_number+"");};	
						}
						else if (ans==0& Screen2.getText().length()==1){Screen2.setText("2"); dp2_number=2; 
						   cal_temp2(dp2_number);				   				   
						   checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {Screen1.setText(dp1_number+"");}; 
				           } 
						 else {String ansafter = (Screen2.getText())+"2";Screen2.setText(ansafter);
						       dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						       cal_temp2(dp2_number);
						       checknumber = (int)dp1_number; 
				               if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				               else {NumberFormat a;
				        	   a=NumberFormat.getInstance();
				        	   a.setMaximumFractionDigits(10);
		            	       String myString = a.format(dp1_number);
		            	       Screen1.setText(myString);};} 
						 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}	 
	    		 }	
					
				 else {	
	    			if (ans==0& Screen2.getText().length()==1){Screen2.setText("2");dp2_number=2; dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position]; 
			           checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {Screen1.setText(dp1_number+"");};
			           }
	    			else {String ansafter = (Screen2.getText())+"2";
	    			if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen2.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp1_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen2.setText(ansafter);
						 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
					 }  
					 checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {NumberFormat a;
		        	   a=NumberFormat.getInstance();
		        	   a.setMaximumFractionDigits(10);
            	       String myString = a.format(dp1_number);
            	       Screen1.setText(myString);
            	       modify_text_dispaly1(myString);
			           };}
	    			}
	    		 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}
	    			}
	    				}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
	    			}
	    		};
	    		button2.setOnClickListener(b);
	    		
	    		// click number 3
	    		Button.OnClickListener c=new Button.OnClickListener(){
	    			@Override
	    			public void onClick(View v) {
	    				try{
	    				try {float ans1 = (float) (Float.parseFloat(Screen2.getText().toString()));}
	    				catch(Exception e){dp2_number=0;Screen2.setText("0");}
	    			float ans = (float) (Float.parseFloat(Screen2.getText().toString()));
	    			if (Screen2.getText().length()<maxlength) {
	    		if (popup_position==2){
	    			    
	    			String check_sign = (String) Screen2.getText();
					if (ans==0 & check_sign.indexOf("-")!=-1 & Screen2.getText().length()==2){
						Screen2.setText("-3"); dp2_number=-3; cal_temp2(dp2_number);				   				   
					    checknumber = (int)dp1_number; 
			            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			            else {Screen1.setText(dp1_number+"");};	
					}
					else  if (ans==0& Screen2.getText().length()==1){Screen2.setText("3"); dp2_number=3; 
						   cal_temp2(dp2_number);				   				   
						   checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {Screen1.setText(dp1_number+"");}; 
				           } 
						 else {String ansafter = (Screen2.getText())+"3";Screen2.setText(ansafter);
						       dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						       cal_temp2(dp2_number);
						       checknumber = (int)dp1_number; 
				               if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				               else {NumberFormat a;
				        	   a=NumberFormat.getInstance();
				        	   a.setMaximumFractionDigits(10);
		            	       String myString = a.format(dp1_number);
		            	       Screen1.setText(myString);};} 
						 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}	 
	    		}	
					
				 else {	
	    			if (ans==0& Screen2.getText().length()==1){Screen2.setText("3");dp2_number=3;dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position]; 
			           checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {Screen1.setText(dp1_number+"");};
			           }
	    			else {String ansafter = (Screen2.getText())+"3";
	    			if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen2.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp1_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen2.setText(ansafter);
						 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
					 }  
						 checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp1_number);
	            	       Screen1.setText(myString);
	            	       modify_text_dispaly1(myString);
				           };}
	    			}
	    		if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}
	    			}
	    				}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
	    			}
	    		};
	    		button3.setOnClickListener(c);
	    		
	    		//click number 4
	    		Button.OnClickListener d=new Button.OnClickListener(){
	    			@Override
	    			public void onClick(View v) {
	    				try{
	    				try {float ans1 = (float) (Float.parseFloat(Screen2.getText().toString()));}
	    				catch(Exception e){dp2_number=0;Screen2.setText("0");}
	    			float ans = (float) (Float.parseFloat(Screen2.getText().toString()));
	    			if (Screen2.getText().length()<maxlength) {
	    			if (popup_position==2){
	    				
	    				String check_sign = (String) Screen2.getText();
						if (ans==0 & check_sign.indexOf("-")!=-1 & Screen2.getText().length()==2){
							Screen2.setText("-4"); dp2_number=-4; cal_temp2(dp2_number);				   				   
						    checknumber = (int)dp1_number; 
				            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				            else {Screen1.setText(dp1_number+"");};	
						}
						else if (ans==0& Screen2.getText().length()==1){Screen2.setText("4"); dp2_number=4; 
						   cal_temp2(dp2_number);				   				   
						   checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {Screen1.setText(dp1_number+"");}; 
				           } 
						 else {String ansafter = (Screen2.getText())+"4";Screen2.setText(ansafter);
						       dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						       cal_temp2(dp2_number);
						       checknumber = (int)dp1_number; 
				               if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				               else {NumberFormat a;
				        	   a=NumberFormat.getInstance();
				        	   a.setMaximumFractionDigits(10);
		            	       String myString = a.format(dp1_number);
		            	       Screen1.setText(myString);};} 
						 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());} 
	    			}	
					
				 else {
	    			if (ans==0& Screen2.getText().length()==1){Screen2.setText("4");dp2_number=4;dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position]; 
			           checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {Screen1.setText(dp1_number+"");};
			           }
	    			else {String ansafter = (Screen2.getText())+"4";
	    			if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen2.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp1_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen2.setText(ansafter);
						 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
					 }  
						 checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp1_number);
	            	       Screen1.setText(myString);
	            	       modify_text_dispaly1(myString);
				           };}
	    			}
	    			if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}
	    			}
	    				}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
	    			}
	    		};
	    		button4.setOnClickListener(d);
	    		
	    		//click number 5
	    		Button.OnClickListener e=new Button.OnClickListener(){
	    			@Override
	    			public void onClick(View v) {
	    				try{
	    				try {float ans1 = (float) (Float.parseFloat(Screen2.getText().toString()));}
	    				catch(Exception e){dp2_number=0;Screen2.setText("0");}
	    			float ans = (float) (Float.parseFloat(Screen2.getText().toString()));
	    			if (Screen2.getText().length()<maxlength) {
	    		if (popup_position==2){
		    			
		    			String check_sign = (String) Screen2.getText();
						if (ans==0 & check_sign.indexOf("-")!=-1 & Screen2.getText().length()==2){
							Screen2.setText("-5"); dp2_number=-5; cal_temp2(dp2_number);				   				   
						    checknumber = (int)dp1_number; 
				            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				            else {Screen1.setText(dp1_number+"");};	
						}
						else if (ans==0& Screen2.getText().length()==1){Screen2.setText("5"); dp2_number=5; 
						   cal_temp2(dp2_number);				   				   
						   checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {Screen1.setText(dp1_number+"");}; 
				           } 
						 else {String ansafter = (Screen2.getText())+"5";Screen2.setText(ansafter);
						       dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						       cal_temp2(dp2_number);
						       checknumber = (int)dp1_number; 
				               if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				               else {NumberFormat a;
				        	   a=NumberFormat.getInstance();
				        	   a.setMaximumFractionDigits(10);
		            	       String myString = a.format(dp1_number);
		            	       Screen1.setText(myString);};} 
						 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}	 
	    		}	
					
				 else {
	    			if (ans==0& Screen2.getText().length()==1){Screen2.setText("5");dp2_number=5;dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position]; 
			           checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {Screen1.setText(dp1_number+"");};
			           }
	    			else {String ansafter = (Screen2.getText())+"5";
	    			if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen2.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp1_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen2.setText(ansafter);
						 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
					 }  
						 checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp1_number);
	            	       Screen1.setText(myString);
	            	       modify_text_dispaly1(myString);
				           };}
	    			}
	    		if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}
	    			}
	    				}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
	    			}
	    		};
	    		button5.setOnClickListener(e);
	    		
	    		//click number 6
	    		Button.OnClickListener f=new Button.OnClickListener(){
	    			@Override
	    			public void onClick(View v) {
	    				try{
	    				try {float ans1 = (float) (Float.parseFloat(Screen2.getText().toString()));}
	    				catch(Exception e){dp2_number=0;Screen2.setText("0");}
	    			float ans = (float) (Float.parseFloat(Screen2.getText().toString()));
	    			if (Screen2.getText().length()<maxlength) {
	    		if (popup_position==2){
	    			
	    			String check_sign = (String) Screen2.getText();
					if (ans==0 & check_sign.indexOf("-")!=-1 & Screen2.getText().length()==2){
						Screen2.setText("-6"); dp2_number=-6; cal_temp2(dp2_number);				   				   
					    checknumber = (int)dp1_number; 
			            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			            else {Screen1.setText(dp1_number+"");};	
					}
					else if (ans==0& Screen2.getText().length()==1){Screen2.setText("6"); dp2_number=6; 
						   cal_temp2(dp2_number);				   				   
						   checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {Screen1.setText(dp1_number+"");}; 
				           } 
						 else {String ansafter = (Screen2.getText())+"6";Screen2.setText(ansafter);
						       dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						       cal_temp2(dp2_number);
						       checknumber = (int)dp1_number; 
				               if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				               else {NumberFormat a;
				        	   a=NumberFormat.getInstance();
				        	   a.setMaximumFractionDigits(10);
		            	       String myString = a.format(dp1_number);
		            	       Screen1.setText(myString);};} 
						 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}	 
	    		}	
					
				 else {
	    			if (ans==0& Screen2.getText().length()==1){Screen2.setText("6");dp2_number=6;dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position]; 
			           checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {Screen1.setText(dp1_number+"");};
			           }
	    			else {String ansafter = (Screen2.getText())+"6";
	    			if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen2.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp1_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen2.setText(ansafter);
						 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
					 }  
						 checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp1_number);
	            	       Screen1.setText(myString);
	            	       modify_text_dispaly1(myString);
				           };}
	    			}
	    		if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}
	    			}
	    				}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
	    			}
	    		};
	    		button6.setOnClickListener(f);
	    		
	    		//click number 7
	    		Button.OnClickListener g=new Button.OnClickListener(){
	    			@Override
	    			public void onClick(View v) {
	    				try{
	    				try {float ans1 = (float) (Float.parseFloat(Screen2.getText().toString()));}
	    				catch(Exception e){dp2_number=0;Screen2.setText("0");}
	    			float ans = (float) (Float.parseFloat(Screen2.getText().toString()));
	    			if (Screen2.getText().length()<maxlength) {
	    		if (popup_position==2){
		    			String check_sign = (String) Screen2.getText();
						if (ans==0 & check_sign.indexOf("-")!=-1 & Screen2.getText().length()==2){
							Screen2.setText("-7"); dp2_number=-7; cal_temp2(dp2_number);				   				   
						    checknumber = (int)dp1_number; 
				            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				            else {Screen1.setText(dp1_number+"");};	
						} 
	    				else if (ans==0& Screen2.getText().length()==1){Screen2.setText("7"); dp2_number=7; 
						   cal_temp2(dp2_number);				   				   
						   checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {Screen1.setText(dp1_number+"");}; 
				           } 
						 else {String ansafter = (Screen2.getText())+"7";Screen2.setText(ansafter);
						       dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						       cal_temp2(dp2_number);
						       checknumber = (int)dp1_number; 
				               if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				               else {NumberFormat a;
				        	   a=NumberFormat.getInstance();
				        	   a.setMaximumFractionDigits(10);
		            	       String myString = a.format(dp1_number);
		            	       Screen1.setText(myString);};} 
						 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}	 
	    		}	
					
				 else {
	    			if (ans==0& Screen2.getText().length()==1){Screen2.setText("7");dp2_number=7;dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position]; 
			           checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {Screen1.setText(dp1_number+"");};
			           }
	    			else {String ansafter = (Screen2.getText())+"7";
	    			if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen2.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp1_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen2.setText(ansafter);
						 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
					 }  
						 checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp1_number);
	            	       Screen1.setText(myString);
	            	       modify_text_dispaly1(myString);
				           };}
	    			}
	    		if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}
	    			}
	    				}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
	    			}
	    		};
	    		button7.setOnClickListener(g);
	    		
	    		//click number 8
	    		Button.OnClickListener h=new Button.OnClickListener(){
	    			@Override
	    			public void onClick(View v) {
	    				try{
	    				try {float ans1 = (float) (Float.parseFloat(Screen2.getText().toString()));}
	    				catch(Exception e){dp2_number=0;Screen2.setText("0");}
	    			float ans = (float) (Float.parseFloat(Screen2.getText().toString()));
	    			if (Screen2.getText().length()<maxlength) {
	    		   if (popup_position==2){
		    			String check_sign = (String) Screen2.getText();
						if (ans==0 & check_sign.indexOf("-")!=-1 & Screen2.getText().length()==2){
							Screen2.setText("-8"); dp2_number=-8; cal_temp2(dp2_number);				   				   
						    checknumber = (int)dp1_number; 
				            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				            else {Screen1.setText(dp1_number+"");};	
						} 
						else if (ans==0& Screen2.getText().length()==1){Screen2.setText("8"); dp2_number=8; 
						   cal_temp2(dp2_number);				   				   
						   checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {Screen1.setText(dp1_number+"");}; 
				           } 
						 else {String ansafter = (Screen2.getText())+"8";Screen2.setText(ansafter);
						       dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						       cal_temp2(dp2_number);
						       checknumber = (int)dp1_number; 
				               if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				               else {NumberFormat a;
				        	   a=NumberFormat.getInstance();
				        	   a.setMaximumFractionDigits(10);
		            	       String myString = a.format(dp1_number);
		            	       Screen1.setText(myString);};} 
						 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}	 
	    		}	
					
				 else {
	    			if (ans==0& Screen2.getText().length()==1){Screen2.setText("8");dp2_number=8;dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position]; 
			           checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {Screen1.setText(dp1_number+"");};
			           }
	    			else {String ansafter = (Screen2.getText())+"8";
	    			if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen2.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp1_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen2.setText(ansafter);
						 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
					 }  
						 checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp1_number);
	            	       Screen1.setText(myString);
	            	       modify_text_dispaly1(myString);
				           };}
	    			}
	    		if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}
	    			}
	    				}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
	    			}
	    		};
	    		button8.setOnClickListener(h);
	    		
	    		//click number 9
	    		Button.OnClickListener i=new Button.OnClickListener(){
	    			@Override
	    			public void onClick(View v) {
	    				try{
	    				try {float ans1 = (float) (Float.parseFloat(Screen2.getText().toString()));}
	    				catch(Exception e){dp2_number=0;Screen2.setText("0");}
	    			float ans = (float) (Float.parseFloat(Screen2.getText().toString()));
	    			if (Screen2.getText().length()<maxlength) {
	    		if (popup_position==2){
	    			
		    			String check_sign = (String) Screen2.getText();
						if (ans==0 & check_sign.indexOf("-")!=-1 & Screen2.getText().length()==2){
							Screen2.setText("-9"); dp2_number=-9; cal_temp2(dp2_number);				   				   
						    checknumber = (int)dp1_number; 
				            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				            else {Screen1.setText(dp1_number+"");};	
						}
						else if (ans==0& Screen2.getText().length()==1){Screen2.setText("9"); dp2_number=9; 
						   cal_temp2(dp2_number);				   				   
						   checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp1_number);
	            	       Screen1.setText(myString);}; 
				           } 
						 else {String ansafter = (Screen2.getText())+"9";Screen2.setText(ansafter);
						       dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						       cal_temp2(dp2_number);
						       checknumber = (int)dp1_number; 
				               if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				               else {Screen1.setText(dp1_number+"");};} 
						 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}	 
	    		}	
					
				 else {
	    			if (ans==0& Screen2.getText().length()==1){Screen2.setText("9");dp2_number=9;dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position]; 
			           checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {Screen1.setText(dp1_number+"");};
			           }
	    			else {String ansafter = (Screen2.getText())+"9";
	    			if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen2.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp1_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen2.setText(ansafter);
						 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
					 }  
						 checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp1_number);
	            	       Screen1.setText(myString);
	            	       modify_text_dispaly1(myString);
				           };}
	    			}
	    		if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}
	    			}
	    				}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
	    			}
	    		};
	    		button9.setOnClickListener(i);
	    		
	    		// click number 0
	    		Button.OnClickListener j=new Button.OnClickListener(){
	    			@Override
	    			public void onClick(View v) {
	    				try{
	    				try {float ans1 = (float) (Float.parseFloat(Screen2.getText().toString()));}
	    				catch(Exception e){dp2_number=0;Screen2.setText("0");}
	    			float ans = (float) (Float.parseFloat(Screen2.getText().toString()));
	    			if (Screen2.getText().length()<maxlength) {
	    		if (popup_position==2){
		    			String check_sign = (String) Screen2.getText();
						if (ans==0 & check_sign.indexOf("-")!=-1 & Screen2.getText().length()==2){
							Screen2.setText("-0"); dp2_number=-0; cal_temp2(dp2_number);				   				   
						    checknumber = (int)dp1_number; 
				            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				            else {Screen1.setText(dp1_number+"");};	
						}
						else if (ans==0& Screen2.getText().length()==1){Screen2.setText("0"); dp2_number=0; 
						   cal_temp2(dp2_number);				   				   
						   checknumber = (int)dp1_number; 
				           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				           else {Screen1.setText(dp1_number+"");}; 
				           } 
						 else {String ansafter = (Screen2.getText())+"0";Screen2.setText(ansafter);
						       dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						       cal_temp2(dp2_number);
						       checknumber = (int)dp1_number; 
				               if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				               else {NumberFormat a;
				        	   a=NumberFormat.getInstance();
				        	   a.setMaximumFractionDigits(10);
		            	       String myString = a.format(dp1_number);
		            	       Screen1.setText(myString);};} 
						 if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}	
	    		}	
					
				 else {
	    			if (ans==0& Screen2.getText().length()==1){Screen2.setText("0");dp2_number=0;dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position]; 
			           checknumber = (int)dp1_number; 
			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
			           else {Screen1.setText(dp1_number+"");};
			           }
	    			else {String ansafter = (Screen2.getText())+"0";
	    			if (ansafter.indexOf(".")!=-1){
						int intAns;
						
					    Screen2.setText(ansafter);
					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
					    intAns=Integer.parseInt(intMy);
					     
					    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
					    
					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
				    	{
					    	dp1_number/=10;
				    	}
					 }  
					    
					 else{
						 Screen2.setText(ansafter);
						 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
					 }  
	 					 checknumber = (int)dp1_number; 
	 			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
	 			           else {NumberFormat a;
			        	   a=NumberFormat.getInstance();
			        	   a.setMaximumFractionDigits(10);
	            	       String myString = a.format(dp1_number);
	            	       Screen1.setText(myString);
	            	       modify_text_dispaly1(myString);
	 			           };}
	    			}
	    		if(spinner1position==spinner2position){Screen1.setText(Screen2.getText());}
	    			}
	    				}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
	    			}
	    		};
	    		button0.setOnClickListener(j);
	    		
	    		// click del
	    		Button.OnClickListener k=new Button.OnClickListener(){
	    			@Override
	    			public void onClick(View v) {
                        try{
	    				try {float ans1 = (float) (Float.parseFloat(Screen2.getText().toString()));}
	    				catch(Exception e){dp2_number=0;Screen2.setText("0");}
	    				
	    				String ansbefore = (String) Screen2.getText();
	    				if (ansbefore.length()==1){
	    					if(popup_position!=2){dp2_number=0;dp1_number=0;Screen2.setText("0");Screen1.setText("0");}
	    					else if (popup_position==2){Screen2.setText("0"); dp2_number=0; 
	    					cal_temp2(dp2_number);}
	    				}
	    				else { 
	    					String ansafter = ansbefore.substring(0, ansbefore.length()-1);
	    				    Screen2.setText(ansafter);
	    				    
	    				    try{dp2_number = (float) (Float.parseFloat(Screen2.getText().toString()));
	    					 
	    					}catch(Exception e){ Screen2.setText("0"); dp2_number=0; 
	    					cal_temp1(dp2_number);
	    					}
	    				    
	    				    if (popup_position==2){
	    				    	String check_minus=(String) Screen2.getText();
	    				    	if(check_minus.indexOf("-")!=-1){Screen2.setText("0");Screen1.setText("0"); dp1_number=0;dp2_number=0; 
	    						cal_temp1(dp2_number);
	    						} 
	    				    	else{cal_temp2(dp2_number);}
	    				    }
	    				    else {
	    				    	if (ansafter.indexOf(".")!=-1){
	    						int intAns;
	    						
	    					    Screen2.setText(ansafter);
	    					   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
	    					    intAns=Integer.parseInt(intMy);
	    					     
	    					    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
	    					    
	    					    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
	    				    	{
	    					    	dp1_number/=10;
	    				    	}
	    					    }  
	    					    
	    					    else{
	    						 Screen2.setText(ansafter);
	    						 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
	    						 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
	    					    }  
	    				    }
	    				 }
	    				       checknumber = (int)dp1_number; 
	    			           if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
	    			           else {NumberFormat a;
	    		        	   a=NumberFormat.getInstance();
	    		        	   a.setMaximumFractionDigits(10);
	                	       String myString = a.format(dp1_number);
	                	       Screen1.setText(myString);
	                	       modify_text_dispaly1(myString);
	    			           };
	    				   	
	    				
	    				if(spinner2position==spinner1position){Screen1.setText(Screen2.getText());}
                        }catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
	    				}
                        
	    			};
	    			button_del.setOnClickListener(k);
	    		// click dot
	    		Button.OnClickListener l=new Button.OnClickListener(){
	    			public void onClick(View v) {
						String checkdot = (String) Screen2.getText();
						if (checkdot.indexOf(".")==-1){
					    String ansafter = (Screen2.getText())+".";
					    Screen2.setText(ansafter);
					    }
					 } 			
	    		};
	    		Button.OnLongClickListener lClick=new Button.OnLongClickListener(){
					@Override
				    public boolean onLongClick(View v) {
						String check_sign = (String) Screen2.getText();
						if (popup_position==2 & check_sign.indexOf("-")==-1){ 
							//check_sign=1; 
							String changesign =(String) Screen2.getText();
	                        Screen2.setText("-"+changesign);
	                        dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						    cal_temp2(dp2_number);
						    checknumber = (int)dp1_number; 
				            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				            else {NumberFormat a;
				        	   a=NumberFormat.getInstance();
				        	   a.setMaximumFractionDigits(10);
		            	       String myString = a.format(dp1_number);
		            	       Screen1.setText(myString);}
	                    }
						else if(popup_position==2 & check_sign.indexOf("-")!=-1){
							//check_sign=0; 
							String changesign =(String) Screen2.getText();
							Screen2.setText(changesign.substring(1));
							dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
						    cal_temp2(dp2_number);
						    checknumber = (int)dp1_number; 
				            if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
				            else {NumberFormat a;
				        	   a=NumberFormat.getInstance();
				        	   a.setMaximumFractionDigits(10);
		            	       String myString = a.format(dp1_number);
		            	       Screen1.setText(myString);}
						}
							
						return true;
					}
				};
	    		
	    		
	    		button_dot.setOnClickListener(l);
	    		button_dot.setOnLongClickListener(lClick);
	        }
	 
	
	}



	
    

    private void resetCalButton() {
		 button1=(Button)findViewById(R.id.keypad_1);
		 button2=(Button)findViewById(R.id.keypad_2);
		 button3=(Button)findViewById(R.id.keypad_3);
		 button4=(Button)findViewById(R.id.keypad_4);
		 button5=(Button)findViewById(R.id.keypad_5);
		 button6=(Button)findViewById(R.id.keypad_6);
		 button7=(Button)findViewById(R.id.keypad_7);
		 button8=(Button)findViewById(R.id.keypad_8);
		 button9=(Button)findViewById(R.id.keypad_9);
		 button0=(Button)findViewById(R.id.keypad_0);
		 button_dot=(Button)findViewById(R.id.keypad_dot);
		 button_del=(Button)findViewById(R.id.keypad_del);
		 Screen1 =(FontFitTextView)findViewById(R.id.Screen1);
		 Screen2 =(FontFitTextView)findViewById(R.id.Screen2);
		 
		 
         
         //
         buttonPanel=(TransparentPanel)findViewById(R.id.buttonPanel);
         buttonPanel.setPadding(3, 3, 3, 7);
         
        //////////////////////change screen mode
        Screen1.setOnClickListener(new TextView.OnClickListener(){
			@Override
			public void onClick(View v) {
				Screen1.setBackgroundResource(R.layout.text_shape);
				Screen2.setBackgroundDrawable(null);
				//Screen1.setBackgroundResource(null);
				try{int chech_int =Integer.parseInt(Screen1.getText().toString());
				} catch(Exception e){
				dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");
				}
				if(Screen1.getText().length()>9){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				mode=0;
				setMode();
				////////////////////////////////screen1變
		        
				
			}
         });
        
        Screen2.setOnClickListener(new TextView.OnClickListener(){
			@Override
			public void onClick(View v) {
				Screen1.setBackgroundDrawable(null);
				Screen2.setBackgroundResource(R.layout.text_shape);
				try{int chech_int =Integer.parseInt(Screen2.getText().toString());
				} catch(Exception e){
				dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");
				}
				if(Screen2.getText().length()>9){Screen2.setText("0");Screen1.setText("0");}
				mode=1;
				 //////////////////////////////screen2 變
				setMode();
		        
			}
         });
   }

    
    
    
        
    
    
    
	private ArrayAdapter<String> aspnCountries;
	private List<String> allcountries;
    
    private void find_and_modify_view1() {
    spinner_c = (Spinner) findViewById(R.id.spinner_1);
    allcountries = new ArrayList<String>();
    for (int i = 0; i < selections.length; i++) {
    	allcountries.add(selections[i]);
    }
    aspnCountries = new ArrayAdapter<String>(this,
    		android.R.layout.simple_spinner_item, allcountries);
    aspnCountries
    		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner_c.setAdapter(aspnCountries);
    
    
        
  //取得sinner的回傳值並計算
    spinner_c.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
        public void onItemSelected(AdapterView adapterView, View view, int position, long id){
            //make a toast,彈出視窗說選到什麼
        	///Toast.makeText(main.this, "您選擇"+adapterView.getSelectedItem().toString()+"popsion:"+position, Toast.LENGTH_LONG).show();
            //做運算
        	spinner1position=position;
        	String ansafter = (String) Screen2.getText();
        	
            //dp2_number=Float.parseFloat(Screen2.getText().toString());
            
            
            if (popup_position==2){cal_temp2(dp2_number);}
			else if (popup_position!=2 &ansafter.length()<10) {
				if (ansafter.indexOf(".")!=-1){
				int intAns;
				try{
			    Screen2.setText(ansafter);
			   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
			    intAns=Integer.parseInt(intMy);
			     
			    dp1_number=intAns*weights[spinner1position]/weights[spinner2position];
			    
			       for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++){
			    	dp1_number/=10;
		    	   }
				}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");;Screen1.setText("0");};
			   }  
				
			    
			 else{
				 try{
				 Screen2.setText(ansafter);
				 dp2_number=(float) (Float.parseFloat(Screen2.getText().toString()));
				 dp1_number=dp2_number*weights[spinner1position]/weights[spinner2position];
				 }catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
			 }  
			}
			else if (popup_position!=2 & ansafter.length()>=10) {
				dp1_number=0;dp2_number=0;Screen2.setText("0");
			}
                     
            checknumber = (int)dp1_number; 
	        if(checknumber == dp1_number){Screen1.setText(checknumber+"");}
	        else {NumberFormat a;
     	   a=NumberFormat.getInstance();
    	   a.setMaximumFractionDigits(10);
	       String myString = a.format(dp1_number);
	       Screen1.setText(myString);};
        	
        	
        	
            		
                        
        }
        public void onNothingSelected(AdapterView arg0) {
            Toast.makeText(Main.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
        }
    });
    
    }
    
    private void find_and_modify_view2() {
        spinner_2 = (Spinner) findViewById(R.id.spinner_2);
        allcountries = new ArrayList<String>();
        for (int i = 0; i < selections.length; i++) {
        	allcountries.add(selections[i]);
        }
        aspnCountries = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_item, allcountries);
        aspnCountries
        		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_2.setAdapter(aspnCountries);
        
      //取得回傳值
        spinner_2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
              /// Toast.makeText(main.this, "您選擇"+adapterView.getSelectedItem().toString()+"popsion:"+position, Toast.LENGTH_LONG).show();
            
              //做運算
             spinner2position=position;
             String ansafter = (String) Screen1.getText();
             
             
             
             if (popup_position==2){cal_temp1(dp1_number);}
			 else  if (ansafter.length()<10){
				 
				 if (ansafter.indexOf(".")!=-1){
					int intAns;
					try{
				    Screen1.setText(ansafter);
				   	String intMy=ansafter.substring(0,ansafter.indexOf("."))+ansafter.substring(ansafter.indexOf(".")+1);
				    intAns=Integer.parseInt(intMy);
				     
				    dp2_number=intAns*weights[spinner2position]/weights[spinner1position];
				    
				    for(int i=0;i<(ansafter.length()-ansafter.indexOf(".")-1);i++)
			    	{
				    	dp2_number/=10;
			    	}
					}catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");;Screen1.setText("0");}
				 }  
				    
				 else{
					 try{
					 Screen1.setText(ansafter);
					 dp1_number=(float) (Float.parseFloat(Screen1.getText().toString()));
					 dp2_number=dp1_number*weights[spinner2position]/weights[spinner1position];
					 }catch(Exception e){dp1_number=0;dp2_number=0;Screen2.setText("0");Screen1.setText("0");}
				 }  
			 }
			 else if (ansafter.length()>=10) {
					dp1_number=0;dp2_number=0;Screen1.setText("0");
				}
             
             checknumber = (int)dp2_number; 
    	     if(checknumber == dp2_number){Screen2.setText(checknumber+"");}
    	     else {NumberFormat a;
      	   a=NumberFormat.getInstance();
    	   a.setMaximumFractionDigits(10);
	       String myString = a.format(dp2_number);
	       Screen2.setText(myString);};   
                
            }
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(Main.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();	
                
            }
        });
        }
    
    
    // popup manu
    private Animation animShow, animHide;
   /* private static final String[] GENRES = new String[] {
        "Action", "Adventure", "Animation", "Children", "Comedy", "Documentary", "Drama",
        "Foreign", "History", "Independent", "Romance", "Sci-Fi", "Television", "Thriller"
    }; */
    
 //   String[] selections= new String[4];
 // 設定可選種類
	private String[] selections,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o;
    
    
    
    
    
    double[] weights = {1, 1000, 35.27396195, 0.000984207, 2.204622622, 0.157473, 0.001102311, 0.0009842065, 0.001, 32.15075, 15432.358, 1.666667,2, 26.666667};        //公斤和各單位間的轉換
    
    double[] a_numbers = {1, 1000, 35.27396195, 0.000984207, 2.204622622 ,0.157473,0.001102311,0.0009842065,0.001,32.15075, 15432.358, 1.666667,2, 26.666667};      //重量
    double[] b_numbers = {1, 0.001, 100,1000, 39.370008, 3.28084, 1.093613,0.0006213712,0.0005399568,0.5468066,0.5876131};         //長度
    //double[] c_numbers = {};                                                  //溫度 另外寫
    double[] d_numbers = {1, 3.6, 2.2369365, 3.28084, 196.8504,3.33e-9,0.03728227,0.06,1.943844};      //速度
    double[] e_numbers = {1, 10000, 0.000001, 0.0002471054, 0.0001,0.000000386102,1.19599,10.76391,1550.003,0.30250001}; //面積
    double[] f_numbers = {1, 1000,0.264172, 2.1133764,0.001,61.0237441,0.0353146662, 4.226753,67.62804,202.8841,270.5122, 33.814 ,0.0062898107, 1.0566882094};       //體積
    double[] g_numbers = {1,0.2388889,0.000000277777 ,1 ,0.0009478171,10000000,0.7375621,0.0002388889,0.1019716,0.000277777};       //能量
    double[] h_numbers = {1,1/24.0,60.0,3600.0};                                      //時間
    double[] i_numbers = {1,101325,76,29.92469,14.69595,993658.8,1.01325,760};      //壓力
    double[] j_numbers = {1,100000,0.101971622,0.224808944,7.233013853};        //力
    double[] k_numbers = {1,0.001,0.001341022,0.05686903,44.25373,0.7375622};      //功率
    double[] l_numbers = {1,1000,1000,1,62.42796,0.03612729,10.02242,8.345404};    //密度
    double[] m_numbers = {1,0.0001,0.09290304,0.00064516,0.00031416,0.0001};      //光強度
    double[] n_numbers = {1,0.01745329,1.111111,0.002777778,17.77778};      //angle
    double[] o_numbers = {1, 0.000976563, 1024, 0.000000954, 1048576, 0.0078125,8 ,8192, 8388608};      //disk
    
	protected Context context;
	private LinearLayout popup;
    
    
    private void initPopup() {
    	
    	 
    
    	 //把東西放到List裡(用adapter)
    	 ListView myList=(ListView)findViewById(R.id.myList);
    	 final LazyAdapter mAdapter=new LazyAdapter(this);
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.weight),"","Weight",R.drawable.weight));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.length),"","Length",R.drawable.length));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.temperature),"","Temperature",R.drawable.temperature));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.speed),"","Speed",R.drawable.speed));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.area),"","Area",R.drawable.area));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.volume),"","Volume",R.drawable.volume));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.energy),"","Energy",R.drawable.energy));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.time),"", "Time"  ,R.drawable.time));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.pressure),"", "Pressure"  ,R.drawable.pressure));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.force),"", "Force"  ,R.drawable.force));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.power),"", "Power"  ,R.drawable.power));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.density),"", "Density"  ,R.drawable.density));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.luminance),"", "Luminance"  ,R.drawable.luminance));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.angle),"", "angle"  ,R.drawable.angle));
    	 mAdapter.items.add(new ImageItem(getMyString(R.string.disk),"", "Data Unit" ,R.drawable.disk));
    	 //mAdapter.items.add(new ImageItem("新增","","",R.drawable.plus));
    		
    	myList.setCacheColorHint(Color.WHITE);
    	myList.setBackgroundColor(Color.WHITE);

    	        
    	//ColorDrawable divcolor = new ColorDrawable(getResources().getColor(R.drawable.ltblue));
    	        
    	myList.setAdapter(mAdapter);
    	// myList.setOnItemClickListener(OI);
    	// myList.setOnItemLongClickListener(OLI);
    	        
    	//myList.setDivider(divcolor);
    	myList.setDividerHeight(1);
    	        
	     //myList.setAdapter(new ArrayAdapter<String>(this,
	     //           android.R.layout.simple_list_item_single_choice, GENRES));
	     //myList.setItemsCanFocus(false);
	     //myList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	    
	     // Start out with the popup initially hidden.
		popup.setVisibility(View.GONE);
	     

		
		myList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				//popup.startAnimation(animHide);
				popup.setVisibility(View.GONE);
				popup_position=position;
				dp1_number=0;dp2_number=0;
				spinner1position=0;
				spinner2position=0;
				Screen2.setText("0");Screen1.setText("0");
				//Toast.makeText(main.this, popup_position+" number", 100000);
				
				////不同種類間的轉換
				if      (position==0){ChangeAdapterList(a); weights = a_numbers;showButton.setText(getMyString(R.string.weight));}
				else if (position==1){ChangeAdapterList(b); weights = b_numbers;showButton.setText(getMyString(R.string.length));}
				else if (position==2){ChangeAdapterList(c); showButton.setText(getMyString(R.string.temperature));
				//make a toast,彈出視窗說選到什麼
				Toast.makeText(Main.this, getMyString(R.string.toast), Toast.LENGTH_LONG).show();
				}
				else if (position==3){ChangeAdapterList(d); weights = d_numbers;showButton.setText(getMyString(R.string.speed));}
				else if (position==4){ChangeAdapterList(e); weights = e_numbers;showButton.setText(getMyString(R.string.area));}
				else if (position==5){ChangeAdapterList(f); weights = f_numbers;showButton.setText(getMyString(R.string.volume));}
				else if (position==6){ChangeAdapterList(g); weights = g_numbers;showButton.setText(getMyString(R.string.energy));}
				else if (position==7){ChangeAdapterList(h); weights = h_numbers;showButton.setText(getMyString(R.string.time));}
				else if (position==8){ChangeAdapterList(i); weights = i_numbers;showButton.setText(getMyString(R.string.pressure));}
				else if (position==9){ChangeAdapterList(j); weights = j_numbers;showButton.setText(getMyString(R.string.force));}
				else if (position==10){ChangeAdapterList(k); weights = k_numbers;showButton.setText(getMyString(R.string.power));}
				else if (position==11){ChangeAdapterList(l); weights = l_numbers;showButton.setText(getMyString(R.string.density));}
				else if (position==12){ChangeAdapterList(m); weights = m_numbers;showButton.setText(getMyString(R.string.luminance));}
				else if (position==13){ChangeAdapterList(n); weights = n_numbers;showButton.setText(getMyString(R.string.angle));}
				else if (position==14){ChangeAdapterList(o); weights = o_numbers;showButton.setText(getMyString(R.string.disk));}
				
				//Log.v(TAG, "onItemClick=" + arg1);
				//registerForContextMenu(arg1);
				//openContextMenu(arg1);
			}

			private void ChangeAdapterList(String[] b) {
				ArrayList<String> list = new ArrayList<String>();
			    for (int i = 0; i < b.length; i++) {list.add(b[i]);}
			    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Main.this,android.R.layout.simple_spinner_item, list);
			    myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			    spinner_c.setAdapter(myAdapter);
			    spinner_2.setAdapter(myAdapter);
			}
		});
		
		
        
		//說明畫面從哪出來 到多高 彈出多快等等
		animShow = AnimationUtils.loadAnimation(this, R.anim.popup_show);
		//animHide = AnimationUtils.loadAnimation(this, R.anim.popup_hide);
        
		//找到popup.xml裡的button
		showButton = (FontFitText_Button) findViewById(R.id.show_popup_button);
		
		//final Button hideButton = (Button) findViewById(R.id.hide_popup_button);
		
		
		//關於showButton的控制
		showButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) { 
				popup.setVisibility(View.VISIBLE);	
				popup.startAnimation(animShow); 
				//showButton.setEnabled(false); 
				//hideButton.setEnabled(true); 
			}        
			
		});
		
        
    };
    
    
    
    //option menu
    
    final int ITEM_ABOUT = 2;
	final int ITEM_SETTING = 1;
	
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
    	
    	String about_str = getResources().getText(R.string.about).toString();
    	String setting_str = getResources().getText(R.string.setting).toString();
		
		//menu.add(0, ITEM_SETTING, 0, setting_str).setIcon(R.drawable.settings);
		//menu.add(0, ITEM_FACEBOOK, 0, facebook_str).setIcon(R.drawable.facebook);
		
	    menu.add(0, ITEM_ABOUT, 0, about_str).setIcon(R.drawable.about);
		
		return super.onCreateOptionsMenu(menu);
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {

    	Intent intent = new Intent();
    	
		// 依據itemId來判斷使用者點選哪一個item
		switch (item.getItemId()) {
		/*case ITEM_SETTING:
			
			
            intent.setClass(this, SettingsActivity.class);
            this.startActivity(intent);

		    
			break; */
			
		case ITEM_ABOUT:
			
            intent.setClass(this, About.class);
            this.startActivity(intent);
            
			break;
		
						
		default:
		}
		return super.onOptionsItemSelected(item);
	}
    
    
    String getMyString(int rId){
    	return getResources().getText(rId).toString();
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
        	if(popup.isShown())
        	{
        		popup.setVisibility(View.GONE);
        	    return true;
        	}
        	else
        		finish();
        	
        }
        return super.onKeyDown(keyCode, event);
    }
    

	private void modify_text_dispaly1(String myString) {
		// TODO Auto-generated method stub
		String text1= myString;
		
		try{
			int catch_number=text1.indexOf(".");
			String before_part=text1.substring(0,catch_number);
			String after_part= text1.substring(catch_number+1, catch_number+4);
		if ( !before_part.equals("0") && after_part.equals("000")){
			text1=text1.substring(0, catch_number);
			Screen1.setText(text1);
		}
		if (after_part.equals("999")||after_part.equals("998")||after_part.equals("997")){
			text1=text1.substring(0, catch_number);
			int new_int=Integer.parseInt(text1);
			String new_text=Integer.toString(new_int+1);
			Screen1.setText(new_text);
		}
		}catch(Exception e){}
				
	}
	private void modify_text_dispaly(String myString) {
		// modify screen display 2
		String text2= myString;
		
		try{
			int catch_number=text2.indexOf(".");
			String before_part=text2.substring(0,catch_number);
			String after_part= text2.substring(catch_number+1, catch_number+4);
		if ( !before_part.equals("0") && after_part.equals("000")){
			text2=text2.substring(0, catch_number);
			Screen2.setText(text2);
		}
		if (after_part.equals("999")||after_part.equals("998")||after_part.equals("997")){
			text2=text2.substring(0, catch_number);
			int new_int=Integer.parseInt(text2);
			String new_text=Integer.toString(new_int+1);
			Screen2.setText(new_text);
		}
		}catch(Exception e){}
				
	}

	@Override
	public void adWhirlGeneric() {
		// TODO Auto-generated method stub
		
	}

    
}
    
    
    

   
    
    
   
