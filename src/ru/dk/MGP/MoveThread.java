package ru.dk.MGP;
import android.os.*;
import android.graphics.drawable.*;

public class MoveThread extends Thread
{
	static long time;
	static boolean is_move=false,is_end=false;
	
	@Override
	public void run()
	{
		// TODO: Implement this method
		time=System.currentTimeMillis();
		for(;!is_end;)
			if(is_move)
				Particle.Move((int)-(time-(time=System.currentTimeMillis())));
		
	}
	
	public void Pause()
	{
		is_move=false;
	}

	public void Resume()
	{
		time=System.currentTimeMillis();
		is_move=true;
	}
	
	public void End()
	{
		is_end=true;
	}
	
	
}
