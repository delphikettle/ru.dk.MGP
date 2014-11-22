package ru.dk.MGP;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import java.sql.*;
import android.view.ViewGroup.*;
import android.graphics.*;
import android.graphics.drawable.*;
import ru.dk.MGP.Levels.*;

public class MainActivity extends Activity
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
		new Particle(rnd.nextFloat()*w/thislevel.getScale(),rnd.nextFloat()*h/thislevel.getScale(),(float)(Math.random()*10-5),(float)(Math.random()*10-5),rnd.nextInt(2048)+1024, rnd.nextInt(9)-4);
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

	float motionx,motiony;
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO: Implement this method

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
