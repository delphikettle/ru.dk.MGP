package ru.dk.MGP;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.graphics.*;
import java.util.*;

public class Background extends View
{
	Level level; 
	Paint paint;
	Canvas bckgrnd;
	public Background(Context context,Level lvl)
	{
		super(context);
		this.level=lvl;
		paint=new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Paint.Style.FILL);
		paint.setARGB(255,0,255,255);
		bckgrnd= new Canvas();
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO: Implement this method
		super.onDraw(canvas);
		Random rnd=new Random();
		paint.setARGB(255/2,255,255,255);
		canvas.drawRect(0,0,level.getW(),level.getH(),paint);
		
		
		//invalidate();
	}
	
	

	static public class Builder{
		private final FrameLayout.LayoutParams params;
		private final Activity activity;
		private Background background;
		Level lvl;

		public Builder(Activity context,int w, int h,Level lvl_){

			params = new FrameLayout.LayoutParams(w,h);
			params.gravity=Gravity.LEFT|Gravity.TOP;
			this.activity = context;
			this.lvl=lvl_;
		}

		public Background create(){

			background=new Background(activity,lvl);
			
			ViewGroup root = (ViewGroup) activity.findViewById(android.R.id.content);
			root.addView(background, params);

			return background;
		}
	}
}
