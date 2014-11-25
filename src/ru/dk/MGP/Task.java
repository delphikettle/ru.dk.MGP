package ru.dk.MGP;

abstract public class Task
{
	boolean is_ended;
	
	public Task(){
		is_ended=false;
	}
	
	public boolean is_ended(){
		if(!is_ended) is_ended=Check();
		return is_ended;
	}
	
	abstract public boolean Check();
	

	//Tasks
	
	//TaskCombine
	public class TaskCombine extends Task
	{
		static final byte TC_AND=0,TC_OR=1,TC_NOT=2,TC_XOR=3;
		
		Task[] tasks;
		byte flag;
		public TaskCombine(Task[] tasks, byte flag)
		{
			this.tasks=tasks;
			this.flag=flag;
		}

		@Override
		public boolean Check()
		{
			// TODO: Implement this method
			boolean f;
			if(flag==TC_AND) f=true; else f=false;
			for(int i=0;i<tasks.length;i++){
				switch(flag){
					case TC_AND:
						if(!tasks[i].Check()) return false;
						break;
					case TC_OR:
						if(tasks[i].Check()) return true;
						break;
					case TC_XOR:
						if(tasks[i].Check()&&f) return false;
						if(tasks[i].Check()) f=true;
						break;
					case TC_NOT: 
						if(tasks[i].Check()) return false; else
							return true;
						//break;
					default: return false;
				}
			}
			return f;
		}

		
	}
	
	//Count
	public class TaskOnCount extends Task
	{
		static final byte T_Bigger=0,T_Smaller=1,T_Average=2;
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
}
