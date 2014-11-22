package ru.dk.MGP.Levels;
import ru.dk.MGP.*;
import android.content.*;
import android.app.*;
import java.util.*;


public class Test_Level extends Level
{

	@Override
	public Background newBackground()
	{
		// TODO: Implement this method
		return (new Background.Builder(MainActivity.thisis,this.getW(),this.getH(),this)).create();
	}


	Test_Level(Context context,int w, int h){
		super(context,w,h,1);
	}
	
	@Override
	public void Init()
	{
		// TODO: Implement this method
		Random rnd=new Random();
		this.setScale(0.3f,true);
		//this.setXShift(50);
		//this.setYShift(50);

		this.setXMin(-this.getXMax()*2);
		this.setYMin(-this.getYMax()*2);
		this.setXMax(this.getXMax()*4);
		this.setYMax(this.getYMax()*4);
		Particle.setG(32);
		Particle.timefactor=1f;
		//for(int i=0;i<3;i++)
		//new Particle(rnd.nextDouble()*1024/this.getScale(),rnd.nextDouble()*812/this.getScale(),Math.random()*100-50,Math.random()*100-50,rnd.nextInt(2048*32)+1024, -2);
		//new Particle(rnd.nextDouble()*1024/this.getScale(),rnd.nextDouble()*812/this.getScale(),Math.random()*100-50,Math.random()*100-50,rnd.nextInt(2048*32)+1024, 2);

		//new Particle(100,100,0,0,10000*Math.PI,2);
		//new Particle(500,100,0,15,2500*Math.PI,-2);
		//new Particle(0,0,0,0,1024*4*16,2);
		//new Particle(500/2,500/2,0,0,512,-2);
		for(int i=0;i<1.0*1024;i++)
			new Particle((rnd.nextFloat()*1920*1-0*1920)/this.getScale(),(rnd.nextFloat()*1024*1-0*1024)/this.getScale(),(float)Math.random()*100-50,(float)Math.random()*100-50,(rnd.nextInt(1024*4)+32),(float)( rnd.nextDouble()*8-4));
	}

	@Override
	public void Review()
	{
		// TODO: Implement this method
	}

	static public class Builder extends Level.Builder
	{
		public Builder(Activity context,int w, int h){
			super(context,w,h);
		}
		@Override
		public Level getLevel(Activity context,int w, int h)
		{
			// TODO: Implement this method
			return new Test_Level(context, w, h);
		}

		
	}
}
