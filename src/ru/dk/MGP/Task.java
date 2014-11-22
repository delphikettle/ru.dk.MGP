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
	
	
	
}
