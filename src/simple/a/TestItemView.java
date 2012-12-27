package simple.a;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextPaint;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TestItemView extends LinearLayout
{
    Context context;

    private LayoutParams a;
    
    
    private TextView textTop = null;
    private TextView textBellow = null;
    private TextView textBotton = null;
    
 
    Bitmap bmImg;
    ImageView gender = null;
    public ImageView imView;
    ImageView imView2;

	private String rate;
	private int photoNum;
	private String text;
    private String Ps;

    Drawable getIcon(int resource)
    {
        return getResources().getDrawable(resource);
    }

    public TestItemView(Context context, String text, String rate, String ps,int photoNum)
    {
        super(context);
        LinearLayout listView = (LinearLayout) new TransparentPanel(getContext());
        
        

        a = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        a.leftMargin = 150;
        this.context = context;
        this.setOrientation(HORIZONTAL);

        /*
         * imView2=(ImageView)findViewById(R.drawable.blueee);
         * imView2.setAdjustViewBounds(true);
         * imView2.setMaxHeight(10);
         * imView2.setMaxWidth(10);
         * addView(imView2);
         */
        
        imView = new ImageView(context);
        imView.setImageDrawable(getIcon(photoNum));
        imView.setAdjustViewBounds(true);

        //Bitmap bit = zoomImage(GetBitMap(url), 50, 50);

        //imView.setImageBitmap(bit);

        // imView.setImageBitmap(GetBitMap(url));
        listView.setPadding(5, 5, 5, 5);

        Paint borderPaint;
        borderPaint = new Paint();
        borderPaint.setARGB(255, 114, 133, 148);
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(Style.STROKE);
        borderPaint.setStrokeWidth(4);

        Paint innerPaint = new Paint();
        innerPaint.setARGB(225, 255, 255, 255);
        innerPaint.setAntiAlias(true);

        // imView.setBackgroundColor(Color.WHITE);

        ((TransparentPanel) listView).setBorderPaint(borderPaint);
        ((TransparentPanel) listView).setInnerPaint(innerPaint);
        listView.addView(imView);
        listView.setBackgroundColor(Color.WHITE);
        this.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lparm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparm.rightMargin = 5;
        addView(listView, lparm);

        RelativeLayout relText = new RelativeLayout(getContext());
        textTop = new TextView(context);
        textBellow = new TextView(context);
        textBotton = new TextView(context);

        textTop.setText(text);
        textBellow.setText(rate);
        textBotton.setText(ps);

        textTop.setTextSize(18);
        
        //kaiu=Typeface.createFromAsset(Typeface.SERIF);
        textTop.setTypeface(Typeface.SERIF  );
        // textTop.setTextAppearance(getContext(),
        // android.R.style.TextAppearance_Medium);
        textTop.setTextColor(Color.BLUE);
        TextPaint tp = textTop.getPaint();
        tp.setFakeBoldText(true);

        // textBellow.setTextAppearance(getContext(),android.R.style.TextAppearance_Medium);

        textBellow.setTextSize(23);
        textBellow.setTextColor(Color.BLACK);

        textBotton.setTextSize(14);
        //textBotton.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
        textBotton.setTextColor(Color.BLACK);

        textTop.setId(001);
        textBellow.setId(002);

        RelativeLayout.LayoutParams RR2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RR2.addRule(RelativeLayout.BELOW, 001);

        RelativeLayout.LayoutParams RR4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RR4.addRule(RelativeLayout.BELOW, 002);

        relText.addView(textTop);
        relText.addView(textBotton, RR2);
        //relText.addView(textBotton, RR4);

        RelativeLayout.LayoutParams RR3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RR3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        RR3.addRule(RelativeLayout.CENTER_VERTICAL);

        gender = new ImageView(context);
        /*
        if (AllData.user[dataNum].gender == 1)
        {
            gender.setImageResource(R.drawable.male);
        }
        else
        {
            gender.setImageResource(R.drawable.female);
        }*/
        relText.addView(textBellow, RR3);
        addView(relText);
        
        setBackgroundColor(Color.LTGRAY);
        // addView(ppt);
        // btn.setText(btnString);
        // addView(btn,a);

        //

        // TODO Auto-generated constructor stub
    }

    

    public Bitmap GetBitMap(String url)
    {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try
        {
            myFileUrl = new URL(url);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        try
        {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }

    public Bitmap zoomImage(Bitmap bgimage, float scaleWidth, float scaleHeight)
    {

        int bmpwidth = bgimage.getWidth();
        int bmpheight = bgimage.getHeight();

        Matrix matrix = new Matrix();

        matrix.postScale(scaleWidth / bmpwidth, scaleHeight / bmpheight);
        Bitmap resizeBmp = Bitmap.createBitmap(bgimage, 0, 0, bmpwidth, bmpheight, matrix, true);

        return resizeBmp;
    }

	public void setRate(String str) {
		textBellow.setText(str);
		
		 
	        
	       
		
	}

	public void setPhoto(int num) {
		//photoNum=num;
		imView.setImageDrawable(getIcon(num));
		
	}

	public void setText(String t) {
		textTop.setText(t);	
	}

	public void setPs(String p) {
		 textBotton.setText(p);
	}

	

}
