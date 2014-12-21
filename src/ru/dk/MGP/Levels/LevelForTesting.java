package ru.dk.MGP.Levels;

import android.app.*;
import android.content.*;
import java.util.*;
import ru.dk.MGP.*;

public class LevelForTesting extends Level
{


	@Override
	public Task setTask()
	{
//		// TODO: Implement this method
		return null;
		//return t[0];
	}


	@Override
	public Background newBackground()
	{
		// TODO: Implement this method
		return (new Background.Builder(MainActivity.thisis,this.getW(),this.getH(),this)).create();
	}


	LevelForTesting(Context context,int w, int h){
		super(context,w,h,1);
	}

	@Override
	public void Init()
	{
		// TODO: Implement this method
		Random rnd=new Random();
		
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
			return new LevelForTesting(context, w, h);
		}


	}
	
}
