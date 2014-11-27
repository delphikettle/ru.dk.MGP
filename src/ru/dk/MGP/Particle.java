package ru.dk.MGP;
import android.graphics.*;
import android.graphics.drawable.*;
import android.view.*;
import java.util.*;
import android.util.*;

public class Particle
{
	public float x,y;
	float vx,vy;
	private float ax,ay;
	float m;//mass
	int n;//number 
	float r;//radius
	float q;//particle charge
	static public Particle[] particles;
	public static int count;
	static int n_max;//the biggest amount of particles
	static int x_min, y_min, x_max, y_max;//borders
	static float G=1;//const G
	//static double timefactor=1;
	//FloatingParticle circle;
	int color;
	int id;
	private static int max_id;
	
	
	public static final double
		Q_POSITIVE=1.0,
		Q_NEGATIVE=-1.0
	;

	public static float timefactor=1;
	
	static public void Init(int w,int h)
	{
		Particle.particles= new Particle[1024*1024];
		
		for(int i=0;i<Particle.particles.length;i++)
			Particle.particles[i]=null;
		count=0;
		n_max=0;
		x_min=0;
		y_min=0;
		x_max=w;
		y_max=h;
		max_id=-1;
	}
	
	static public float setG(float newG)
	{
		return G=(newG>=0?newG:newG);
	}

	static public int setXMax(int newXMax)
	{
		return x_max=newXMax>x_min?newXMax:x_max;
	}

	static public int setXMin(int newXMin)
	{
		return x_min=newXMin<x_max?newXMin:x_min;
	}
	static public int setYMax(int newYMax)
	{
		return y_max=newYMax>y_min?newYMax:y_max;
	}

	static public int setYMin(int newYMin)
	{
		return y_min=newYMin<y_max?newYMin:y_min;
	}
	

	static public double getG()
	{
		return G;
	}

	static public int getXMax()
	{
		return x_max;
	}

	static public int getXMin()
	{
		return x_min;
	}
	static public int getYMax()
	{
		return y_max;
	}

	static public int getYMin()
	{
		return y_min;
	}
	
	public Particle(float x_, float y_, float vx_, float vy_,float m_, float q_)
	{
		this.x=x_;
		this.y=y_;
		this.vx=vx_;
		this.vy=vy_;
		this.ax=0;
		this.ay=0;
		this.m=m_;
		this.q=q_;
		count++;
		this.r=(float)Math.sqrt(m/Math.PI);
		int i=0;
		while(particles[i]!=null)
			i++;
		particles[i]=this;
		if(i>n_max)n_max=i;
		this.n=i;
		this.id=++max_id;
		Random rnd= new Random();
		this.color=Color.argb(128,rnd.nextInt(128+64)+128-64,rnd.nextInt(128+64)+128-64,rnd.nextInt(128+64)+128-64/*0,255,0*/);
		//this.color=Color.argb(128*2-1,255,255,255);
		//circle = new FloatingParticle.Builder(MainActivity.thisis,this.n);
		/*BitmapDrawable bd = null;
		if(q>=0.25)
			bd= (BitmapDrawable) MainActivity.thisis.getResources().getDrawable(R.drawable.p_plus);
		if(q<=-0.25)
			bd= (BitmapDrawable) MainActivity.thisis.getResources().getDrawable(R.drawable.p_minus);
		if(q>-0.25&&q<0.25)
			bd= (BitmapDrawable) MainActivity.thisis.getResources().getDrawable(R.drawable.p_zero);
		this.circle= new FloatingParticle.Builder(MainActivity.thisis,this.n) 
			.withDrawable(bd)
			.withButtonColor(Color.argb(128,rnd.nextInt(128)+128,rnd.nextInt(128)+128,rnd.nextInt(128)+128)) 
			.withButtonSize(this.r*(int)Math.round((1+Math.abs(this.q/10))))
			//.withGravity(Gravity.TOP|Gravity.LEFT|Gravity.RIGHT|Gravity.BOTTOM) 
			.withGravity(Gravity.CENTER) 
			.create();
		circle.setX((float)this.x);
		circle.setY((float)this.y);
		
		//MainActivity.thisis.ShowToast(""+this.n);*/
	}

	static public void Delete(int number){
		Particle.particles[number]=null;
		count--;
	}
	
	@Override
	public String toString()
	{
		// TODO: Implement this method
		String s;
		s="n:"+this.n+", x:"+this.x+", y:"+this.y+", vx:"+this.vx+", vy:"+this.vy+", ax:"+this.ax+", ay:"+this.ay+", m:"+this.m+", q:"+this.q;
		return s;
	}
	
	public static void Influence(Particle p1, Particle p2)
	{
		float t=0.01f;
		float d=(float)(Math.sqrt(Math.pow((p1.x+p1.vx*t-p2.x-p2.vx*t),2)+Math.pow((p1.y+p1.vy*t-p2.y-p2.vy*t),2)));
		
		//if(d>p1.r*(1+Math.abs(p1.q/10))+p2.r*(1+Math.abs(p2.q/10)))
		{	
			float F=-G*p1.m*p2.m*p1.q*p2.q/(d*d),F1=F/p1.m,F2=F/p2.m/**((timefactor>0)?1:-1)*/;
			p1.ax=p1.ax+F1*(p2.x-p1.x)/d;
			p2.ax=p2.ax+F2*(p1.x-p2.x)/d;
			p1.ay=p1.ay+F1*(p2.y-p1.y)/d;
			p2.ay=p2.ay+F2*(p1.y-p2.y)/d;
			//MainActivity.thisis.ShowToast(p1.ax+" "+p2.ax);
		}
		
		if(d>p1.r+p2.r&&d<=p1.r*(1+Math.abs(p1.q/5))+p2.r*(1+Math.abs(p2.q/5)))
		{
			//charging
			//MainActivity.thisis.ShowToast("2");
			Charging(p1,p2,d);
		//	MoveThread.is_move=false;
		}
		
		if(d<=p1.r+p2.r)
		{
			//repulsion
			//MainActivity.thisis.ShowToast("3");
			//p1.vx=-p1.vx;
			//p2.vx=-p2.vx;
			//p1.vy=-p1.vy;
			//p2.vy=-p2.vy;
			//Collision(p1,p2,d,true);
			//Collision(p2,p1,true);
			if(p1.m>p2.m)MassExhanging(p1,p2,d); else
				if(p2.m>p1.m)MassExhanging(p2,p1,d);
			//if(particles[p1.n]!=null&&particles[p2.n]!=null)
			//Repulsion(p1,p2,d);
		}
	}
	
	private static void Repulsion(Particle p1, Particle p2,double d)
	{
		float v1x_=p1.vx,v1y_=p1.vy,v2x_=p2.vx,v2y_=p2.vy;
		float v1x=p1.vx,v1y=p1.vy,v1=(float)Math.sqrt(v1x*v1x+v1y*v1y);
		float v2x=p2.vx,v2y=p2.vy,v2=(float)Math.sqrt(v2x*v2x+v2y*v2y);
		float m1=p1.m,m2=p2.m;
		float A=(float)((v1!=0)?((v1x/v1>=-1&&v1x/v1<=1)?Math.acos(v1x/v1):Math.asin(v1y/v1)):0),
			B=(float)((v2!=0)?((v2x/v2>=-1&&v2x/v2<=1)?Math.acos(v2x/v2):Math.asin(v2y/v2)):0);
		double C=(d!=0)?(((p2.y-p1.y)/d>=-1&&(p2.y-p1.y)/d<=1)?Math.asin((p2.y-p1.y)/d):Math.acos((p2.x-p1.x)/d)):0;
		
		v1x_=(float)((v1*Math.cos(A-C)*(m1-m2)+2*m2*v2*Math.cos(B-C))/(m1+m2)*Math.cos(C)
		+v1*Math.sin(A-C)*Math.cos(C+Math.PI/2));
		v1y_=(float)((v1*Math.cos(A-C)*(m1-m2)+2*m2*v2*Math.cos(B-C))/(m1+m2)*Math.sin(C)
		+v1*Math.sin(A-C)*Math.sin(C+Math.PI/2));
		
		v2x_=(float)((v2*Math.cos(B-C)*(m2-m1)+2*m1*v1*Math.cos(A-C))/(m2+m1)*Math.cos(C)
		+v2*Math.sin(B-C)*Math.cos(C+Math.PI/2));
		v2y_=(float)((v2*Math.cos(B-C)*(m2-m1)+2*m1*v1*Math.cos(A-C))/(m2+m1)*Math.sin(C)
		+v2*Math.sin(B-C)*Math.sin(C+Math.PI/2));
		
		p1.vx=v1x_;
		p1.vy=v1y_;
		p2.vx=v2x_;
		p2.vy=v2y_;
		
	}
	
	private static void Collision(Particle p1, Particle p2,double d,boolean f)
	{
		
		float v1x=p1.vx,v1y=p1.vy,v2x=p2.vx,v2y=p2.vy;
		float m1=p1.m,m2=p2.m;
		/*
		double A=Math.acos((p2.x-p1.x)/d)+90;
		double u1x=-v1x;
		double u1y=-v1y;
		double u2x=-v2x;
		double u2y=-v2y;
		if(!((A>-1&A<1)||(A>179&A<181)))
		{
			u1x=2*(-v1x+Math.tan(A)*v1y)/(Math.pow(Math.tan(A),2)-1)-v1x;
			u1y=2*(-v1x+Math.tan(A)*v1y)/(Math.pow(Math.tan(A),2)-1)*Math.tan(A)-v1y;
			u2x=2*(-v2x+Math.tan(A)*v2y)/(Math.pow(Math.tan(A),2)-1)-v2x;
			u2y=2*(-v2x+Math.tan(A)*v2y)/(Math.pow(Math.tan(A),2)-1)*Math.tan(A)-v2y;
		}
		p1.vx=u1x;
		p1.vy=u1y;
		p2.vx=u2x;
		p2.vy=u2y;
		*/
		
		float u1x=(v1x*(m1-m2)+2*m2*v2x)/(m1+m2);
		float u2x=(v2x*(m2-m1)+2*m1*v1x)/(m1+m2);
		float u1y=(v1y*(m1-m2)+2*m2*v2y)/(m1+m2);
		float u2y=(v2y*(m2-m1)+2*m1*v1y)/(m1+m2);
		
		float A=(float)(Math.acos((p2.x-p1.x)/d)-135);
		float u1x_=u1x;
		float u1y_=u1y;
		float u2x_=u2x;
		float u2y_=u2y;
		
		if(!((A>-1&A<1)||(A>179&A<181)))
		{
			u1x_=(float)(2*(-u1x+Math.tan(A)*u1y)/(Math.pow(Math.tan(A),2)-1)-u1x);
			u1y_=(float)(2*(-u1x+Math.tan(A)*u1y)/(Math.pow(Math.tan(A),2)-1)*Math.tan(A)-u1y);
			u2x_=(float)(2*(-u2x+Math.tan(A)*u2y)/(Math.pow(Math.tan(A),2)-1)-u2x);
			u2y_=(float)(2*(-u2x+Math.tan(A)*u2y)/(Math.pow(Math.tan(A),2)-1)*Math.tan(A)-u2y);
		}
		
		p1.vx=u1x_;
		p1.vy=u1y_;
		p2.vx=u2x_;
		p2.vy=u2y_;
		/*
		
		/*
		double v1x=p1.vx,v1y=p1.vy,v1=Math.sqrt(v1x*v1x+v1y*v1y);
		double v2x=p2.vx,v2y=p2.vy,v2=Math.sqrt(v2x*v2x+v2y*v2y);
		double A=Math.acos(v1x/v1),B=Math.asin(v1y/v1),C=Math.acos((p2.x-p1.x)/d)/*+Math.asin((p2.y-p1.y)/d) /*((p2.x-p1.x!=0)?Math.atan(((p2.y-p1.y)/(p2.x-p1.x))):((p1.x-p1.x>=0)?90:270));
		double m1=p1.m,m2=p2.m;
		double u1x=((v1*Math.cos(A-C)*(m1-m2)+2*m2*v2*Math.cos(B-C))/(m1+m2)*Math.cos(C)+v1*Math.sin(A-C)*Math.cos(C+Math.PI/2.0));
		double u1y=((v1*Math.cos(A-C)*(m1-m2)+2*m2*v2*Math.cos(B-C))/(m1+m2)*Math.sin(C)+v1*Math.sin(A-C)*Math.sin(C+Math.PI/2.0));
	//	C=Math.acos(-(p2.x-p1.x)/d);
		double u2x=((v2*Math.cos(B-C)*(m2-m1)+2*m1*v1*Math.cos(A-C))/(m2+m1)*Math.cos(C)+v2*Math.sin(B-C)*Math.cos(C+Math.PI/2.0));
		double u2y=((v2*Math.cos(B-C)*(m2-m1)+2*m1*v1*Math.cos(A-C))/(m2+m1)*Math.sin(C)+v2*Math.sin(B-C)*Math.sin(C+Math.PI/2.0));
		//double u1x=-100;
		//double u1y=0;
		//double u1x=(m1*v1x+m2*v2x-m2*u2x)/m1;
		//double u1y=(m1*v1y+m2*v2y-m2*u2y)/m1;
		//double u2x=(m1*v1x+m2*v2x-m1*u1x)/m2;
		//double u2y=(m1*v1y+m2*v2y-m1*u1y)/m2;
		p1.vx=u1x;
		p1.vy=u1y;
		p2.vx=u2x;
		p2.vy=u2y;
		/*
		/*
		if(f)
		{
			Collision(p2,p1,false);
			p1.vx=u1x;
			p1.vy=u1y;
			
			double
				a=Math.pow(p1.vx-p2.vx,2)+Math.pow(p1.vy-p2.vy,2),
				b=2*((p1.x-p2.x)*(p1.vx-p2.vx)+(p1.y-p2.y)*(p1.vy-p2.vy)),
				c=Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2)-Math.pow(p1.r+p2.r+10,2),
				D=b*b-4*a*c,
				t1=(-b-Math.sqrt(D))/(2*a),
				//10,
				t2=(-b+Math.sqrt(D))/(2*a)
				;
			if(D>=0)
			if(t1>0)
			{
				p1.x+=p1.vx*t1;
				p1.y+=p1.vy*t1;
				p2.x+=p2.vx*t1;
				p2.y+=p2.vy*t1;
			}else{
				p1.x+=p1.vx*t2;
				p1.y+=p1.vy*t2;
				p2.x+=p2.vx*t2;
				p2.y+=p2.vy*t2;
				
			}
			/*
		}else{
			p1.vx=u1x;
			p1.vy=u1y;
			//MainActivity.ShowToast("!!!");
		}
		*/
	}
	private static boolean MoveMass(Particle from,Particle to,float mass)
	{
		if(mass>from.m+1)return false; else {
			//String log="mass:"+mass+"\nBefore:from:"+from.toString()+"\nto:"+to.toString();
			to.q=(to.q*to.m+from.q*mass)/(to.m+mass);
			to.vx=(to.vx*to.m+from.vx*mass)/(to.m+mass);
			to.vy=(to.vy*to.m+from.vy*mass)/(to.m+mass);
			//to.color=(int)((to.color*to.m+from.color*mass)/(to.m+mass));
			to.m=to.m+mass;
			from.m=from.m-mass;
			if(from.m<1)Particle.Delete(from.n); else
				from.r=(float)Math.sqrt(from.m/Math.PI);
			to.r=(float)Math.sqrt(to.m/Math.PI);
			//log=log+"\nAfter:from:"+from.toString()+"\nto:"+to.toString();
			//Log.i("Particle.MoveMass",log);
			return true;
		}
		
	}
	private static boolean MassExhanging(Particle p1, Particle p2, float d_)
	{
		//p1.m>p2.m
		//String log="Before:1:"+p1.toString()+"\n2:"+p2.toString();
		boolean f=false;
		float d= (float)Math.sqrt(Math.pow(p2.x-p1.x,2)+Math.pow(p2.y-p1.y,2)) ;
		if(d<=p1.r)
		{
			return MoveMass(p2,p1,p2.m);
		}else if(d>p1.r)
		{
			float a=p1.m,b=p2.m;
			float sqrt=(float)(Math.PI*(2*a*d*d+2*b*d*d-Math.PI*Math.pow(d,4)));
			if(sqrt<0)f=false; else
			{
				float mass=(float)(Math.sqrt(sqrt)-a+b)/2;
				if(mass<=0)f=false; else
				f= MoveMass(p2,p1,mass);
			}
		}
		//log=log+"\nAfter:1:"+p1.toString()+"\n2:"+p2.toString();
		//Log.i("Particle.MassExhanging",log);
		/*
		float m1=p1.m, m2=p2.m,d//=1+(Math.sqrt(Math.pow((p1.x-p2.x),2)+Math.pow((p1.y-p2.y),2)));
		;
		d=d_;
		float sqrt=(float)(Math.PI*d*d*(2*m1+2*m2-Math.PI*d*d));
		float m;
		if(sqrt>=0)
		{
			m=(float)((-Math.sqrt(sqrt)-m1+m2)/2*1.1);
			if(m<0)m=(float)((Math.sqrt(Math.PI*d*d*(2*m1+2*m2-Math.PI*d*d))-m1+m2)/2*1.1);
			if(m<0)return;
		}else return;
		if(m>m2)
		{
			m=m2;
			Particle.particles[p2.n]=null;
		}
		float m1_=m1+m,m2_=m2-m;
		float q1_=(m1*p1.q+m*p2.q)/m1_,q2_=(m2*p2.q-m*p2.q)/m2_;
		float v1x_=(m1*p1.vx+m*p2.vx)/m1_,v2x_=(m2*p2.vx-m*p2.vx)/m2_;
		float v1y_=(m1*p1.vy+m*p2.vy)/m1_,v2y_=(m2*p2.vy-m*p2.vy)/m2_;
		p1.m=m1_;
		p2.m=m2_;
		p1.q=q1_;
		p2.q=q2_;
		p1.vx=v1x_;
		if(p2.m>=1)p2.vx=v2x_;
		p1.vy=v1y_;
		if(p2.m>=1)p2.vy=v2y_;
		if(p1.m>=1)p1.r=(int) Math.round(Math.sqrt(p1.m/Math.PI));
		if(p2.m>=1)p2.r=(int) Math.round(Math.sqrt(p2.m/Math.PI));
		if(p2.m<1)Particle.particles[p2.n]=null;
		*/
		
		return f;
	}
	
	private static void Charging(Particle p1, Particle p2,double d)
	{
		float charge_average=(p1.m*p1.q+p2.m*p2.q)/(p1.m+p2.m)/2;
		float charge=(float)(Math.abs((1-(d-p1.r-p2.r)/(Math.abs(p1.r*p1.q/10)+Math.abs(p2.r*p2.q/10)))*((Math.abs(charge_average-p1.q)>0.00001)?0.00005*Math.abs(charge_average-p1.q):Math.abs(charge_average-p1.q))));
		charge=(float)((charge>0.0001)?0.0001:charge);
		if(p1.q>p2.q)
		{
			p1.q=(p1.q*p1.m-charge*p2.m)/p1.m;
			p2.q=(p2.q*p2.m+charge*p1.m)/p2.m;
		} else
		{
			p1.q=(p1.q*p1.m+charge*p2.m)/p1.m;
			p2.q=(p2.q*p2.m-charge*p1.m)/p2.m;
		}
		//MainActivity.thisis.ShowToast("charging"+charge);
	}
	
	public boolean CollisionWithBorders()
	{
		boolean f=false;
		
		//left
		if(this.x-this.r<=getXMin()){
			this.vx=-this.vx;
			this.x=getXMin()+this.r+1;
			f=true;
		}
		
		//right
		if(this.x+this.r>=getXMax()){
			this.vx=-this.vx;
			this.x=getXMax()-this.r-1;
			f=true;
		}
		
		//top
		if(this.y-this.r<=getYMin()){
			this.vy=-this.vy;
			this.y=getYMin()+this.r+1;
			f=true;
		}

		//bottom
		if(this.y+this.r>=getYMax()){
			this.vy=-this.vy;
			this.y=getYMax()-this.r-1;
			f=true;
		}
		
		return f;
	}
	
	public void Change(int time/*in milliseconds*/)
	{
		float t=time/1000.0f*timefactor;
		this.x=(float)(this.x+this.vx*t+this.ax*Math.pow(t,2)/2);
		this.y=(float)(this.y+this.vy*t+this.ay*Math.pow(t,2)/2);
		this.vx=this.vx+this.ax*t;
		this.vy=this.vy+this.ay*t;
		CollisionWithBorders();
		//MainActivity.thisis.ShowToast(this.ax+" "+this.ay);
		//Log.i("Particle.Change",this.toString());
		this.ax=0;
		this.ay=0;
		/*
		if(x<0){
			x=-x;
			vx=-vx;
		}
		if(y<0){
			y=-y;
			vy=-vy;
		}
		if (x>1024){
			x=1024-(x-1024);
			vx=-vx;
		}
		if (y>1024){
			y=1024-(y-1024);
			vy=-vy;
		}
		*/
		//this.circle.setX((float)this.x);
		//this.circle.setY((float)this.y);
	}
	
	public static void Move(int time)
	{
		//if(time>32)Move(time-32); else
		{
			for(int i=0;i<=n_max;i++)
				for(int j=i+1; j<=n_max;j++)
					if(Particle.particles[i]!=null&&Particle.particles[j]!=null)Particle.Influence(Particle.particles[i],Particle.particles[j]);
					
			for(int i=0;i<=n_max;i++)
				if(Particle.particles[i]!=null)Particle.particles[i].Change(time);
		}
	}
}
