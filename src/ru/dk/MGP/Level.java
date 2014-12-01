package ru.dk.MGP;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;
import java.util.*;


public abstract class Level extends View
{
	String name;
	int x_min,y_min,x_max,y_max;
	int type;
	int w,h;
	float x_shift,y_shift,scale;
	//Task[] tasks;
	//public static Level thislevel;
	//public final int
	//	T_GETCHARGE=1
	//	;
	boolean is_gamer_needed;
	MoveThread mt;
	Background background;
	Task task;
	int mainID;
	//private FrameLayout.LayoutParams params;
	//private final Activity activity;
	
	private Paint paint;
	public Level(Context context,int w,int h,int type){
		super(context);
		//MainActivity.thisis.getSystemService(MainActivity.thisis.WINDOW_SERVICE);
		//Display display=((WindowManager) MainActivity.thisis.getSystemService(MainActivity.thisis.WINDOW_SERVICE)).getDefaultDisplay();
		this.w=w;
		this.h=h;
		x_shift=0;
		y_shift=0;
		scale=1;
		mainID=0;
		Particle.Init(w,h);
		this.setXMin(Particle.getXMin());
		this.setXMax(Particle.getXMax());
		this.setYMin(Particle.getYMin());
		this.setYMax(Particle.getYMax());
		
		paint=new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Paint.Style.FILL);
		
		this.Init();
		/*
		this.activity=context;
		ViewGroup root = (ViewGroup) activity.findViewById(android.R.id.content);
		root.addView(this, params);
		*/
		task=setTask();
		background=newBackground(); 
		background.setX(0);
		background.setY(0);
		
		mt=new MoveThread();
		mt.setDaemon(true);
		mt.start();
		mt.Resume();
		invalidate();
	}
	public abstract Task setTask();
	public abstract Background newBackground();
	public abstract void Init();
	public boolean Review(){
//		boolean f=true;
//		for(int i=0;i<tasks.length;i++) {
//			if(tasks[i]!=null)if(!tasks[i].is_ended())f=false;
//		}
		return task.is_ended();
	}

	float getAverageLastX(PointerCoordinate[] pointers){
		float sum=0;
		int n=0;
		for(int i=0;i<pointers.length;i++)
			if(pointers[i].active){
				n++;
				sum+=pointers[i].lastx;
			}
		if(n>0)return (sum/n); else return -1;
	}
	float getAverageX(PointerCoordinate[] pointers){
		float sum=0;
		int n=0;
		for(int i=0;i<pointers.length;i++)
			if(pointers[i].active){
				n++;
				sum+=pointers[i].x;
			}
		if(n>0)return (sum/n); else return -1;
	}
	float getAverageLastY(PointerCoordinate[] pointers){
		float sum=0;
		int n=0;
		for(int i=0;i<pointers.length;i++)
			if(pointers[i].active){
				n++;
				sum+=pointers[i].lasty;
			}
		if(n>0)return (sum/n); else return -1;
	}
	float getAverageY(PointerCoordinate[] pointers){
		float sum=0;
		int n=0;
		for(int i=0;i<pointers.length;i++)
			if(pointers[i].active){
				n++;
				sum+=pointers[i].y;
			}
		if(n>0)return (sum/n); else return -1;
	}
	
	private float getDistance(float x1,float y1,float x2,float y2){
		return (float)(Math.sqrt(Math.pow(x2-x1,2)+Math.pow(x2-x1,2)));
	}
	
	float getScaleChange(PointerCoordinate[] pointers){
		float d=1;
		int n=0;
		float lasts=0;
		for(int i=0;i<pointers.length;i++)
			for(int j=i+1;j<pointers.length;j++)
				if(pointers[i].active&&pointers[j].active)
				{
					n++;
					lasts+=getDistance(pointers[i].lastx,pointers[i].lasty,pointers[j].lastx,pointers[j].lasty);
				}
		float s=0;
		for(int i=0;i<pointers.length;i++)
			for(int j=i+1;j<pointers.length;j++)
				if(pointers[i].active&&pointers[j].active)
					s+=getDistance(pointers[i].x,pointers[i].y,pointers[j].x,pointers[j].y);
		
		if(n>0)
			d=1/(lasts/s);		
		return d;
	}
	
	void onTouchLevel(PointerCoordinate[] pointers,int index){
		//if(pointers[index].active)
		{
			this.setXShift((this.getXShift()*this.getScale()+ getAverageX(pointers) - getAverageLastX(pointers))/this.getScale());
			this.setYShift((this.getYShift()*this.getScale()+ getAverageY(pointers) - getAverageLastY(pointers))/this.getScale());
			
			//scaling
			setXShift(getXShift()+getW() *( getScale()-getScaleChange(pointers)* getScale()));
			setYShift(getYShift()+ getH()*( getScale()-getScaleChange(pointers)* getScale()));
			setScale(getScale()*getScaleChange(pointers),true);
		}
	}
	
	public int getW(){
		return this.w;
	}
	
	public int getH(){
		return this.h;
	}
	
	public void Pause()
	{
		mt.Pause();
	}
	
	public void Resume()
	{
		mt.Resume();
	}

	public float setScale(float newscale, boolean smoothing)
	{
		invalidate();
		//if(this.scale-newscale>0.001f)this.setScale(newscale+0.001f);
		//if(this.scale-newscale<-0.005f)this.setScale(newscale-0.001f);
		return this.scale=(newscale>=0.05f)?((newscale<=1f)?(newscale):1f):0.05f;
	}

	public float setXShift(float newXShift)
	{
		return this.x_shift=newXShift;
	}

	public float setYShift(float newYShift)
	{
		return this.y_shift=newYShift;
	}
	public float getScale()
	{
		return this.scale;
	}

	public float getXShift()
	{
		return this.x_shift;
	}

	public float getYShift()
	{
		return this.y_shift;
	}
	

	public int setXMax(int newXMax)
	{
		
		//return x_max=newXMax>x_min?newXMax:x_max;
		return x_max=Particle.setXMax(newXMax);
	}

	public int setXMin(int newXMin)
	{
		//return x_min=newXMin<x_max?newXMin:x_min;
		return x_min=Particle.setXMin(newXMin);
	}
	public int setYMax(int newYMax)
	{
		//return y_max=newYMax>y_min?newYMax:y_max;
		return y_max=Particle.setYMax(newYMax);
	}

	public int setYMin(int newYMin)
	{
		//return y_min=newYMin<y_max?newYMin:y_min;
		return y_min=Particle.setYMin(newYMin);
	}

	public int getXMax()
	{
		return x_max;
	}

	public int getXMin()
	{
		return x_min;
	}
	public int getYMax()
	{
		return y_max;
	}

	public int getYMin()
	{
		return y_min;
	}
	
	protected void DrawMainParticle(Canvas canvas, Particle p)
	{
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(p.r*0.05f*scale);
		if(p.q>=0.0)paint.setShadowLayer((p.q*p.r*scale*0.05f),0,0,Color.argb(255,255,0,0));
		if(p.q<=-0.0)paint.setShadowLayer((-p.q*p.r*scale*0.05f),0,0,Color.argb(255,0,0,255));
		paint.setColor(Color.argb(255,255,255,255));
		canvas.drawCircle((p.x+x_shift)*scale,(p.y+y_shift)*scale,p.r*scale,paint);
		canvas.drawCircle((p.x+x_shift)*scale,(p.y+y_shift)*scale,p.r*scale*0.8f,paint);
		canvas.drawCircle((p.x+x_shift)*scale,(p.y+y_shift)*scale,p.r*scale*0.6f,paint);
		canvas.drawCircle((p.x+x_shift)*scale,(p.y+y_shift)*scale,p.r*scale*0.4f,paint);
		canvas.drawCircle((p.x+x_shift)*scale,(p.y+y_shift)*scale,p.r*scale*0.2f,paint);
		paint.setStyle(Paint.Style.FILL);
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO: Implement this method
		super.onDraw(canvas);
		paint.setARGB(255,0,255,0);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(16*8*scale);
		paint.setShadowLayer(64*8*scale,0,0,Color.argb(255,0,255,0));
		canvas.drawRect((x_min+x_shift-16)*scale,(y_min+y_shift-16)*scale,(x_max+x_shift+16)*scale,(y_max+y_shift+16)*scale,paint);
		paint.setStyle(Paint.Style.FILL);
		Particle p=Particle.particles[0];
		
		for (int i=0;i<=Particle.n_max;p=Particle.particles[i+=1])
			if(p!=null)
			{
				
				if(p.q>=0.0)paint.setShadowLayer((p.q*p.r*scale/5.0f),0,0,Color.argb(255,255,0,0));
				//if(p.q>-0.0125&&p.q<0.0125)paint.setShadowLayer((float)Math.abs(p.q*p.r*scale/5.0f),0,0,Color.argb(255,0,0,0));
				if(p.q<=-0.0)paint.setShadowLayer((-p.q*p.r*scale/5.0f),0,0,Color.argb(255,0,0,255));
				
				//paint.setShadowLayer((float)Math.abs(p.r*p.q),0,0,Color.argb(100,p.q>0?(int)p.q/10*255:0,0,p.q<0?-(int)p.q/10*255:0));
				paint.setColor(p.color);
				canvas.drawCircle((p.x+x_shift)*scale,(p.y+y_shift)*scale,p.r*scale,paint);
				//paint.setARGB(255,0,0,0);
				//canvas.drawText(""+Math.round(p.q*100)/100.0,(float)p.x,(float)p.y,paint);
				if(p.id==mainID)DrawMainParticle(canvas,p);
			}
		/*
		p=Particle.particles[0];
		if(p.q>=0.0)paint.setShadowLayer((p.q*p.r*scale/5.0f),0,0,Color.argb(255,255,0,0));
		//if(p.q>-0.0125&&p.q<0.0125)paint.setShadowLayer((float)Math.abs(p.q*p.r*scale/5.0f),0,0,Color.argb(255,0,0,0));
		if(p.q<=-0.0)paint.setShadowLayer((-p.q*p.r*scale/5.0f),0,0,Color.argb(255,0,0,255));

		//paint.setShadowLayer((float)Math.abs(p.r*p.q),0,0,Color.argb(100,p.q>0?(int)p.q/10*255:0,0,p.q<0?-(int)p.q/10*255:0));
		paint.setColor(p.color);
		canvas.drawCircle((p.x+x_shift)*scale,(p.y+y_shift)*scale,p.r*scale*0.8f,paint);
		canvas.drawCircle((p.x+x_shift)*scale,(p.y+y_shift)*scale,p.r*scale*0.6f,paint);
		canvas.drawCircle((p.x+x_shift)*scale,(p.y+y_shift)*scale,p.r*scale*0.4f,paint);
		canvas.drawCircle((p.x+x_shift)*scale,(p.y+y_shift)*scale,p.r*scale*0.2f,paint);
		*/
		
		this.Review();
			
		invalidate();
	}
//
//	float motionx,motiony;
//	
//	@Override
//	public boolean onTouchEvent(MotionEvent event)
//	{
//		// TODO: Implement this method
//		//MainActivity.ShowToast( MotionEvent.actionToString( event.getActionMasked()));
//		//MainActivity.ShowToast( event.getX()+" "+event.getY());
//		switch(event.getActionMasked()){
//			case MotionEvent.ACTION_DOWN:
//				motionx=event.getX();
//				break;
//			case MotionEvent.ACTION_MOVE:
//				this.setXShift(getXShift()+event.getX()-motionx);
//				motionx=event.getX();
//		}
//		return super.onTouchEvent(event);
//	}


	
	
	abstract static public class Builder{
		private FrameLayout.LayoutParams params;
		private final Activity activity;
		private final Level level;
		
		public Builder(Activity context,int w, int h){

			params = new FrameLayout.LayoutParams(w,h);
			params.gravity=Gravity.LEFT|Gravity.TOP;
			this.activity = context;
			this.level=getLevel(context,w,h);
		}
		public abstract Level getLevel(Activity context,int w, int h);
		
		public Level create(){
			
			ViewGroup root = (ViewGroup) activity.findViewById(android.R.id.content);
			root.addView(level, params);
			
			return level;
		}
	}
}
