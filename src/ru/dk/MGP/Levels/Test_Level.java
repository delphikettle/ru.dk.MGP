package ru.dk.MGP.Levels;
import android.app.*;
import android.content.*;
import java.util.*;
import ru.dk.MGP.*;
import ru.dk.MGP.Tasks.*;
import org.apache.commons.logging.*;
import android.util.*;


public class Test_Level extends Level
{

	@Override
	public Task setTask()
	{
//		// TODO: Implement this method
		Task[] t={new TaskOnCount(50,TaskOnCount.T_Smaller), new TaskOnCount(112,TaskOnCount.T_Bigger)};
		return new TaskCombine(new Task[] {t[0],t[1],new TaskOnCount(50, TaskOnCount.T_Average)},TaskCombine.TC_OR);
		//return t[0];
	}


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
		//this.setScale(0.5f,true);
		//this.setXShift(50);
		//this.setYShift(50);

		this.setXMin(0);//-this.getXMax());
		this.setYMin(0);//-this.getYMax());
		this.setXMax(this.getXMax()*2);
		this.setYMax(this.getYMax());
		//this.setXShift(-getXMin());
		//this.setYShift(-getYMin());
		Particle.setG(4);
		Particle.timefactor=1f;
		//for(int i=0;i<3;i++)
		//new Particle(rnd.nextDouble()*1024/this.getScale(),rnd.nextDouble()*812/this.getScale(),Math.random()*100-50,Math.random()*100-50,rnd.nextInt(2048*32)+1024, -2);
		//new Particle(rnd.nextDouble()*1024/this.getScale(),rnd.nextDouble()*812/this.getScale(),Math.random()*100-50,Math.random()*100-50,rnd.nextInt(2048*32)+1024, 2);

		//new Particle(100,100,0,0,10000*Math.PI,2);
		//new Particle(500,100,0,15,2500*Math.PI,-2);
		//new Particle(0,0,0,0,1024*4*16,2);
		//new Particle(500/2,500/2,0,0,512,-2);
		new Particle(rnd.nextFloat()*(getXMax()-getXMin()-200*2*4)+getXMin()+100*2*4,rnd.nextFloat()*(getYMax()-getYMin()-200*2*4)+getYMin()+100*4*2,(float)Math.random()*100-50,(float)Math.random()*100-50,((1024*8*4*36)),2*((float)(( rnd.nextDouble()*8-4))));
		for(int i=0;i<0;i++)
			new Particle(rnd.nextFloat()*(getXMax()-getXMin()-200*2*4)+getXMin()+100*2*4,rnd.nextFloat()*(getYMax()-getYMin()-200*2*4)+getYMin()+100*4*2,(float)Math.random()*100/2-50/2,(float)Math.random()*100/2-50/2,(rnd.nextInt(1024*2)+32),(float)( rnd.nextDouble()*8-4));
		//new Particle((rnd.nextFloat()*1920*1-0*1920)/this.getScale(),(rnd.nextFloat()*1024*1-0*1024)/this.getScale(),((float)Math.random()*1000-500)/16,((float)Math.random()*1000-500)/16,(rnd.nextInt(1024*4)+2048),(float)( rnd.nextDouble()*8-4));
	}

	@Override
	public boolean Review()
	{
		// TODO: Implement this method
		//if(super.Review()) MainActivity.thisis.finish();
		//android.util.Log.i("Review",super.Review()+"");
		return super.Review();
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
