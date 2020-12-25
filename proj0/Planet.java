public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;


	/** Initial the Planet*/
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** Make a copy of planet */
	public Planet(Planet p){
		//xxPos = p.xxPos;
		//Explicit constructor invocation
		this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
	}

	/** Calculates the distance between two planets*/
	public double calcDistance(Planet p){
		// the dx and dy of the Other Planet p to "this" Planet
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		//Not using Math.pow to get a faster code
		double squaDistance = dx * dx + dy * dy; 
		return Math.sqrt(squaDistance);
	}
	/** Return the total force exerted on this Planet by the given Planet p*/
	public double calcForceExertedBy (Planet p){
		// F = G * m1 * m2 / r^2
		double force = G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
		return force;
	}

	/** Return the force exerted on this planet by P on X axis */
	public double calcForceExertedByX (Planet p){
		//Fx = F * dx / r
		double dx = p.xxPos - this.xxPos;
		double forceOnX = this.calcForceExertedBy (p) * dx / this.calcDistance( p);
		return forceOnX;
	}

	/** Return the force exerted on this planet by P on Y axis */
	public double calcForceExertedByY (Planet p){
		//Fy = F * dy / r
		double dy = p.yyPos - this.yyPos;
		double forceOnY = this.calcForceExertedBy (p) * dy / this.calcDistance(p);
		return forceOnY;
	}

	/** Return the net force exerted on this Planet on X axis */
	public double calcNetForceExertedByX(Planet[] p){
		double netForceOnX =0.0;
		//calculate the force exerted by each plant p[i] 
		for (Planet element : p){
			//Skip the planet in the array equals to "this" Planet
			if (this.equals(element) == true){
				continue;
			}
			netForceOnX += this.calcForceExertedByX(element);
		}
		return netForceOnX;
	}

	/** Return the net force exerted on this Planet on Y axis */
	public double calcNetForceExertedByY(Planet[] p){
		double netForceOnY =0.0;
		//calculate the force exerted by each plant p[i] 
		for (Planet element : p){
			//Skip the planet in the array equals to "this" Planet
			if (this.equals(element) == true){
				continue;
			}
			netForceOnY += this.calcForceExertedByY(element);
		}
		return netForceOnY;
	}

	/** Update the Velocity and postion of "this" Planet due to the t-s force exerted */
	public void update(double t,double forceOnX, double forceOnY){
		// acceleration on x and y due to force forceOnX and forceOnY
		double aOnX;
		double aOnY;
		aOnX = forceOnX / this.mass;
		aOnY = forceOnY / this.mass;

		// Update velocity of "this" Planet
		this.xxVel += aOnX * t;
		this.yyVel += aOnY * t;

		//Update Position of "this" Planet
		this.xxPos += this.xxVel * t;
		this.yyPos += this.yyVel * t;
	}
	/*Draw the planet itsef using the StdDraw*/
	public void draw (){

		StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
	}

	
}
















