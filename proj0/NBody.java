/**	The class actually run the simulation*/
public class NBody{
	
	/*	read the radius form the txt file*/
	public static double readRadius(String fileLocation){
		/* Start reading in the file*/
		In in = new In (fileLocation);
		/* Read the radius onformation, 
		 * first double number in the txt is the radius */
		int numberOfPlanets = in.readInt();
		double radius = in.readDouble ();
		return radius;
	}
	/*	return a array of five planets*/
	public static Planet[] readPlanets(String fileLocation){
		In in = new In(fileLocation);
		
		int numberOfPlanets = in.readInt();
		Planet[] allPlanets = new Planet[numberOfPlanets];
		double radius = in.readDouble ();
		/*	Assign properties to each allPlanets[i]*/
		for (int i = 0; i < numberOfPlanets; i++){
			/*	!!												!!
			 *	!! Remeber to instantiation every allPlanets[i] !!
				!!											    !!	*/
			allPlanets[i] = new Planet (in.readDouble(),in.readDouble(),in.readDouble(),
										in.readDouble(),in.readDouble(),in.readString());

			
		}
		return allPlanets;		
	} 
	
	
	public static void main(String[] args) {
		// store the 1st and 2nd commind line arguments as double type.
		
		double T  = Double.parseDouble(args[0]);  
		double dt = Double.parseDouble(args[1]);
		
		String filename = args[2];
			//String filename = "data/planets.txt";

		//Read in the planet and Radius, not assigned yet!!
		double radius = readRadius(filename);
		Planet[] p = readPlanets(filename);
		int n = p.length;
		//background image location

		String background = "images/starfield.jpg";
		//set scale
		StdDraw.setScale(-1.1 * radius, 1.1 * radius);
		//put image background at the ceter 
		StdDraw.picture(0.0, 0.0, background);

		/*	Draw every planets in Planet[] p using method draw*/
		for (Planet element : p){
			element.draw();
		}

		/*	To prevent flickering in the animation */
		StdDraw.enableDoubleBuffering();

		/*	Do stuff through the loop*/
		for (double t = 0; t < T; t += dt){
			//Force array
			double[] xForce = new double[5];
			double[] yForce = new double[5];

			//store X and Y net force for each planet in xForce and yForce at time t
			for (int i = 0; i < n; i++){
				 //Net force on xais exerted on planet[i] by other planets 
				xForce [i] = p[i].calcNetForceExertedByX(p);
				 //Net force on yais exerted on planet[i] by other planets 
				yForce [i] = p[i].calcNetForceExertedByY(p);
			}

			// After get all 5 planets' net force information, update them together
			for (int i = 0; i < n; i++){
				p[i].update(dt,xForce[i],yForce[i]);
			}

			//draw background at the ceter 
			StdDraw.picture(0.0, 0.0, background);
			/*	Draw every planets in Planet[] p using method draw*/
			for (Planet element : p){
				element.draw();
			}
		
			/* Shows the drawing to the screen, and waits 10 milliseconds. */
			StdDraw.show();
			StdDraw.pause(10);

		}
		StdOut.printf("%d\n", p.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < p.length; i++) {
	    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
	        p[i].xxPos, p[i].yyPos, p[i].xxVel,
	        p[i].yyVel, p[i].mass, p[i].imgFileName);   
		}
	}
}



























