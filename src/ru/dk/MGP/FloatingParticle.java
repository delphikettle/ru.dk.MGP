package ru.dk.MGP;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

public class FloatingParticle extends View {
	Context context;
	Paint mButtonPaint;
	Paint mDrawablePaint;
	Bitmap mBitmap;
	boolean mHidden = false;
	Particle p;
//	boolean f=false;

	public FloatingParticle(Context context,Particle p_) {
		super(context);
		this.context = context;
		this.p=p_;
		init(Color.WHITE);
		this.setSelected(false);
		//MainActivity.thisis.ShowToast(" ! ");
	}

	public void setFloatingActionButtonColor(int FloatingActionButtonColor) {
		init(FloatingActionButtonColor);
	}

	public void setFloatingActionButtonDrawable(Drawable FloatingActionButtonDrawable) {
		mBitmap = ((BitmapDrawable) FloatingActionButtonDrawable).getBitmap();
		invalidate();
	}

	public void init(int FloatingActionButtonColor) {
		setWillNotDraw(false);
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);

		mButtonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mButtonPaint.setColor(FloatingActionButtonColor);
		mButtonPaint.setStyle(Paint.Style.FILL);
		mButtonPaint.setShadowLayer(/*10.0f, 0.0f, 3.5f*/(float)(Math.abs(this.p.q*this.p.r/10.0)),0.0f,0.0f, Color.argb(100, 0, 0, 255));
		mDrawablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//setClickable(false);
		//MainActivity.thisis.ShowToast("d");
		//if(!f)
		{
			canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (this.p.r), mButtonPaint);
			//canvas.drawBitmap(mBitmap, (getWidth() - mBitmap.getWidth()) / 2,
			//			  (getHeight() - mBitmap.getHeight()) / 2, mDrawablePaint);		
			canvas.drawText(""+Math.round(this.p.q*100)/100.0,getWidth() / 2, getHeight() / 2,mDrawablePaint);
		mButtonPaint.setShadowLayer(/*10.0f, 0.0f, 3.5f*/(float)(Math.abs(this.p.q*this.p.r/100.0)),0.0f,0.0f, Color.argb(100, 0, 0, 255));
			canvas.drawCircle(0, 0, (float) (this.p.r)/10.0f, mButtonPaint);
		//	f=true;
		}
		mButtonPaint.setShadowLayer(/*10.0f, 0.0f, 3.5f*/(float)(Math.abs(this.p.q*this.p.r/1.0)),0.0f,0.0f, Color.argb(100, 0, 0, 255));
		
		//this.draw(canvas);
		//super.onDraw(canvas);
		//this.setX((float)(this.p.x));
		//this.setY((float)(this.p.y));
		//draw();
		this.setX((float)(this.p.x-this.p.r*(1/*+Math.abs(this.p.q/10)*/)));
		this.setY((float)(this.p.y-this.p.r*(1/*+Math.abs(this.p.q/10)*/)));
		BitmapDrawable bd = (BitmapDrawable) MainActivity.thisis.getResources().getDrawable(R.drawable.p_zero);
		if(this.p.q>=0.125)
			bd= (BitmapDrawable) MainActivity.thisis.getResources().getDrawable(R.drawable.p_plus);
		if(this.p.q<=-0.125)
			bd= (BitmapDrawable) MainActivity.thisis.getResources().getDrawable(R.drawable.p_minus);
		//invalidate();
		this.setFloatingActionButtonDrawable(bd);
		
		invalidate();
	}

	
/*	protected void draw()
	{
	}*/
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		/*if (event.getAction() == MotionEvent.ACTION_UP) {
			setAlpha(1.0f);
		} else if (event.getAction() == MotionEvent.ACTION_DOWN) {
			setAlpha(0.6f);
		 }*/
		//this.setX((float)(this.p.x));
		//this.setY((float)(this.p.y));
		return super.onTouchEvent(event);
	}

	public void hideFloatingActionButton() {
		if (!mHidden) {
			ObjectAnimator scaleX = ObjectAnimator.ofFloat(this, "scaleX", 1, 0);
			ObjectAnimator scaleY = ObjectAnimator.ofFloat(this, "scaleY", 1, 0);
			AnimatorSet animSetXY = new AnimatorSet();
			animSetXY.playTogether(scaleX, scaleY);
			animSetXY.setInterpolator(new AccelerateInterpolator());
			animSetXY.setDuration(100);
			animSetXY.start();
			mHidden = true;
		}
	}

	public void showFloatingActionButton() {
		if (mHidden) {
			ObjectAnimator scaleX = ObjectAnimator.ofFloat(this, "scaleX", 0, 1);
			ObjectAnimator scaleY = ObjectAnimator.ofFloat(this, "scaleY", 0, 1);
			AnimatorSet animSetXY = new AnimatorSet();
			animSetXY.playTogether(scaleX, scaleY);
			animSetXY.setInterpolator(new OvershootInterpolator());
			animSetXY.setDuration(200);
			animSetXY.start();
			mHidden = false;
		}
	}

	public boolean isHidden() {
		return mHidden;
	}

	static public class Builder {
		private FrameLayout.LayoutParams params;
		private final Activity activity;
		int gravity /*= Gravity.BOTTOM | Gravity.RIGHT*/; // default bottom right
		Drawable drawable;
		int color = Color.WHITE;
		int size = 0;
		float scale = 0;
		int n;

		public Builder(Activity context,int n_) {
			scale = context.getResources().getDisplayMetrics().density;
			// The calculation (value * scale + 0.5f) is a widely used to convert to dps to pixel units
			// based on density scale
			// see developer.android.com (Supporting Multiple Screen Sizes)
		//	size = (int) (72 * scale + 0.5f); // default size is 72dp by 72dp
			params = new FrameLayout.LayoutParams(size, size);
			params.gravity = gravity;
			n=n_;
			
			this.activity = context;
		}

		/**
		 * Sets the gravity for the FAB
		 */
		public Builder withGravity(int gravity) {
			this.gravity = gravity;
			return this;
		}

		/**
		 * Sets the margins for the FAB in dp
		 */
		public Builder withMargins(int left, int top, int right, int bottom) {
			params.setMargins((int) (left * scale + 0.5f), (int) (top * scale + 0.5f),
							  (int) (right * scale + 0.5f), (int) (bottom * scale + 0.5f));
			return this;
		}

		/**
		 * Sets the FAB drawable
		 */
		public Builder withDrawable(final Drawable drawable) {
			this.drawable = drawable;
			return this;
		}

		/**
		 * Sets the FAB color
		 */
		public Builder withButtonColor(final int color) {
			this.color = color;
			return this;
		}

		/**
		 * Sets the FAB size in dp
		 */
		public Builder withButtonSize(int size) {
			size = (int) (2*size *1.5+100/** scale + 0.5f*/);
			params = new FrameLayout.LayoutParams(size,size);
			return this;
		}

		public FloatingParticle create() {
			final FloatingParticle button = new FloatingParticle(activity,Particle.particles[n]);
			button.setFloatingActionButtonColor(this.color);
			button.setFloatingActionButtonDrawable(this.drawable);
			params.gravity = this.gravity;
			ViewGroup root = (ViewGroup) activity.findViewById(android.R.id.content);
			root.addView(button, params);
			return button;
		}
	}
}
