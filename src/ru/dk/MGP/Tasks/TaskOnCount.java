package ru.dk.MGP.Tasks;

import ru.dk.MGP.*;

public 
//Count
class TaskOnCount extends Task
{
	public static final byte T_Bigger=0,T_Smaller=1,T_Average=2;
	byte type;
	int toCount;
	int accuracy=0;
	public TaskOnCount(int count,byte type)
	{
		super();
		this.type=type;
		toCount=count;
	}

	public TaskOnCount setAccuracy(int accuracy)
	{
		this.accuracy=Math.abs(accuracy);
		return this;
	}

	@Override
	public boolean Check()
	{
		// TODO: Implement this method
		switch(this.type){
			case T_Bigger:
				if (Particle.count >= this.toCount - this.accuracy) return true; else return false;
			case T_Smaller:
				if (Particle.count <= this.toCount + this.accuracy) return true; else return false;
			case T_Average:
				if (Particle.count >= this.toCount - this.accuracy && Particle.count <= this.toCount + this.accuracy) return true; else return false;
			default: return false;
		}
	}


}
