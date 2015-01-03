package ru.dk.MGP;

import android.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import ru.dk.MGP.Levels.*;
import android.util.*;

public class MainActivity extends Activity //implements OnTouchListener
{
	//int ni=0;
	MoveThread mt;
	//DrawThread dt;
	Level thislevel;
	static int ex=0;
	public static Activity thisis;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		getActionBar().hide();
		thisis=this;
		Display display=((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		//Matrix.Init(display.getWidth(),display.getHeight());
		//Matrix.Init(1000,600);
		Random rnd=new Random();
		int w=display.getWidth(),h=display.getHeight();
		//Particle p= new Particle(rnd.nextInt(Matrix.w),rnd.nextInt(Matrix.h),0,0,10);
		//Matrix.Update();
		//Particle.Init(display.getWidth(),display.getHeight());
		thislevel = (new Test_Level.Builder(this,w,h)).create();
		long time= System.currentTimeMillis();
		//Particle.Move(100);
		time=System.currentTimeMillis()-time;
		Toast t1=Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT);
		t1.show();

		//new Background.Builder(MainActivity.thisis,thislevel.getWidth(),thislevel.getHeight(),thislevel).create();
		//mt = new MoveThread();
		//MoveThread.is_move=true;
		//mt.start();
		//mt.setDaemon(true);
		/*BitmapDrawable bd = (BitmapDrawable) this.getResources().getDrawable(R.drawable.ic_launcher);
		FloatingActionButton fabButton = new FloatingActionButton.Builder(this) 
			.withDrawable(bd)
			.withButtonColor(Color.WHITE) 
			.withButtonSize(Particle.particles[0].r64*4)
		 .create();*/
/*		 
		new Particle(100,100,0,0,Math.PI*10000,2);
		new Particle(300,100,0,0,Math.PI*10000,0);
		new Particle(100,300,0,0,Math.PI*10000,0);
		new Particle(300,300,0,0,Math.PI*10000,0);
		new Particle(500,100,0,0,Math.PI*10000,-1);
		new Particle(100,500,0,0,Math.PI*10000,-1);
*/		

		//new Particle(600,100,0,0,Math.PI*10000,-2*2);
		//new Particle(1400,100,0,0,Math.PI*10000,2*2);
		//new Test_Level();
		//thislevel=new Test_Level(MainActivity.thisis);
	//	FloatingParticle fp= new FloatingParticle.Builder(this,0).create();
//	thislevel.setX(0);
		//thislevel.setY(0);
        //setContentView(R.layout.main);
		for(int i=0;i<10;i++)pointers[i]=new PointerCoordinate(i);
		//thislevel.setOnTouchListener(this);
		
    }
	
	
	
	public void OnB1Click(View view)
	{
		//setContentView(R.layout.levelchooser);
		//dt = new DrawThread();
		//long time= System.currentTimeMillis();
		//Particle.Move(10000);
		//time=System.currentTimeMillis()-time;
		Display display=((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		//Random rnd=new Random();
		int w=display.getWidth(),h=display.getHeight();
		//long d=Math.round(Math.sqrt(Math.pow((Particle.particles[0].x-Particle.particles[1].x),2)+Math.pow((Particle.particles[0].y-Particle.particles[1].y),2)));
		//this.ShowToast(Particle.particles[0].toString()+"\n"+Particle.particles[1].toString());
		//Particle.Move(1000);
	//	draw();
		Random rnd=new Random();
		//new Particle(rnd.nextDouble()*w,rnd.nextDouble()*h,0,0,rnd.nextInt(2048*32/2)+1024*4, Math.random()*4-2);
		new Particle(rnd.nextFloat()*(thislevel.getXMax()-thislevel.getXMin()-200*2*4)+thislevel.getXMin()+100*2*4,rnd.nextFloat()*(thislevel.getYMax()-thislevel.getYMin()-200*2*4)+thislevel.getYMin()+100*4*2,(float)Math.random()*100/2-50/2,(float)Math.random()*100/2-50/2,(rnd.nextInt(1024*2)+32)*4,(float)( rnd.nextDouble()*8-4));
		//new Particle(rnd.nextFloat()*w/thislevel.getScale(),rnd.nextFloat()*h/thislevel.getScale(),(float)(Math.random()*10-5),(float)(Math.random()*10-5),rnd.nextInt(2048)+1024, rnd.nextInt(9)-4);
		//new Particle(100,100,0,0,Math.PI*10000, 0);
		//MoveThread.is_move=true;
		//ShowToast(""+Particle.particles[0].vx+" "+Particle.particles[0].vy+"\n"+Particle.particles[1].vx+" "+Particle.particles[1].vy+"\n\n"+Particle.particles[0].r+" "+Particle.particles[0].r);
		//Particle.particles[0].vx=0;
		//thislevel.setScale(0.1f);
		//thislevel.setXShift(thislevel.getXShift()+100);
		//Particle.timefactor-=0.1;
		//thislevel.setScale(thislevel.getScale()*1.1f);
		//Particle.particles[3]=null;
		//thislevel.setScale(1.1f*thislevel.getScale(),true);
		
	}

	PointerCoordinate[] pointers=new PointerCoordinate[10];

	
//	public class PointerCoordinate{boolean active;
//		float x,y,lastx,lasty;
//		int id;
//		PointerCoordinate(int id){
//			active=false;
//			this.id=id;
//			lastx=x=lasty=y=-1;
//		}
//		void Down(float x, float y){
//			active=true;
//			this.lastx=this.x=x;
//			this.lasty=this.y=y;
//			Log.i("PointerCoordinate","Down: "+this);
//		}
//		PointerCoordinate Move(float x, float y){
//			this.lastx=this.x;
//			this.lasty=this.y;
//			this.x=x;
//			this.y=y;
//			Log.i("PointerCoordinate","Move: "+this);
//			return this;
//		}
//		
//		void Up(){
//			active=false;
//			Log.i("PointerCoordinate","Up  : "+this);
//		}
//
//		@Override
//		public String toString()
//		{
//			// TODO: Implement this method
//			return "id:"+this.id+", active:"+this.active+", lastx:"+this.lastx+", lasty:"+this.lasty+", x:"+this.x+", y:"+this.y;
//		}
//		
//		
//	}
//	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO: Implement this method
		int actionMask=event.getActionMasked();
		int actionPointer=event.getActionIndex();
		int pointerCount=event.getPointerCount();
		switch(actionMask){
//			case MotionEvent.ACTION_DOWN:
//				pointers[0].Down(event.getX(),event.getY());
//				break;
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				pointers[event.getPointerId(actionPointer)].Down(event.getX(actionPointer),event.getY(actionPointer));
				break;
			case MotionEvent.ACTION_MOVE:
				for(int i=0;i<pointerCount;i++)
					pointers[event.getPointerId(i)].Move(event.getX(i),event.getY(i));
				thislevel.onTouchLevel(pointers,event.getPointerId(actionPointer));
				break;
			case MotionEvent.ACTION_POINTER_UP:
				pointers[event.getPointerId(actionPointer)].Up();
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				for(int i=0;i<10;i++)if(pointers[i].active){
						if(pointers[i].x==pointers[i].firstx&&pointers[i].y==pointers[i].firsty)
						{
							thislevel.separateMainParticle((pointers[i].firstx)/thislevel.getScale()-thislevel.getXShift(),(pointers[i].firsty)/thislevel.getScale()-thislevel.getYShift());
							Log.i("separate",pointers[i].firstx+" "+pointers[i].firsty);
						}
						pointers[i].Up();
				}
		}
		//Log.i("onTouchEvent",actionMask+""+actionPointer+""+pointerCount+"");
/*
		switch(event.getActionMasked()){
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:	
				motionx=event.getX();
				motiony=event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
			case MotionEvent.ACTION_POINTER_UP:
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				thislevel.setXShift((thislevel.getXShift()*thislevel.getScale()+event.getX()-motionx)/thislevel.getScale());
				motionx=event.getX();
				thislevel.setYShift((thislevel.getYShift()*thislevel.getScale()+event.getY()-motiony)/thislevel.getScale());
				motiony=event.getY();
		}
		*/
		
		return super.onTouchEvent(event);
	}
	
	
	
	
	static public void ShowToast(String s)
	{
		Toast t1=Toast.makeText(thisis,s,Toast.LENGTH_LONG);
		t1.show();
		
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		ShowToast("onDestroy");
		//mt.t.destroy();
		super.onDestroy();
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		ShowToast("onPause");
		thislevel.Pause();
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		ShowToast("onResume");
		thislevel.Resume();
		super.onResume();
	}

	@Override
	protected void onRestart()
	{
		// TODO: Implement this method
		ShowToast("onRestart");
		super.onRestart();
	}

	@Override
	protected void onStart()
	{
		// TODO: Implement this method
		ShowToast("onStart");
		super.onStart();
	}

	@Override
	protected void onStop()
	{
		// TODO: Implement this method
		ShowToast("onStop");
		super.onStop();
	}

	@Override
	public void onLowMemory()
	{
		// TODO: Implement this method
		ShowToast("onLowMemory");
		super.onLowMemory();
	}
	
	
	
	/*public void Create_Button()
	{
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		Button button = new Button(context);
		button.setLayoutParams(layoutParams);
		layout.addView(button);
	}*/
	
}
