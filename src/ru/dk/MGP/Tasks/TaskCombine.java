package ru.dk.MGP.Tasks;

import ru.dk.MGP.*;

public 
//TaskCombine
class TaskCombine extends Task
{
	public static final byte TC_AND=0,TC_OR=1,TC_NOT=2,TC_XOR=3;

	Task[] tasks;
	byte flag;
	public TaskCombine(Task[] tasks, byte flag)
	{
		this.tasks=tasks;
		this.flag=flag;
	}

	public TaskCombine addTask(Task t){
		Task[] newtasks=new Task[this.tasks.length+1];
		for(int i=0;i<this.tasks.length;i++)
			newtasks[i]=this.tasks[i];
		newtasks[this.tasks.length]=t;
		this.tasks=newtasks;
		return this;
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
