package ru.dk.MGP;
import android.util.*;
public class PointerCoordinate{
	boolean active;
	float x,y,lastx,lasty;
	int id;
	PointerCoordinate(int id){
		active=false;
		this.id=id;
		lastx=x=lasty=y=-1;
	}
	void Down(float x, float y){
		active=true;
		this.lastx=this.x=x;
		this.lasty=this.y=y;
		Log.i("PointerCoordinate","Down: "+this);
	}
	PointerCoordinate Move(float x, float y){
		this.lastx=this.x;
		this.lasty=this.y;
		this.x=x;
		this.y=y;
		Log.i("PointerCoordinate","Move: "+this);
		return this;
	}

	void Up(){
		active=false;
		Log.i("PointerCoordinate","Up  : "+this);
	}

	@Override
	public String toString()
	{
		// TODO: Implement this method
		return "id:"+this.id+", active:"+this.active+", lastx:"+this.lastx+", lasty:"+this.lasty+", x:"+this.x+", y:"+this.y;
	}


}
	
