
import robocode.*;
// import java.awt.*;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * CodeBlasters - a robot by (your name here)
 */
public class CodeBlasters extends AlphaBot
{
	/**
	 * run: CodeBlasters's default behavior
	 */
	
	/*boolean peek; // Don't turn if there's a robot there
	double moveAmount;*/

	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		
		/*peek = false;
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		peek = true;
		turnGunRight(90);
		turnRight(90);
		while (true) {
			// Look before we turn when ahead() completes.
			peek = true;
			// Move up the wall
			ahead(moveAmount);
			// Don't look now
			peek = false;
			// Turn to the next wall
			turnRight(90);
		}*/


		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
			
			

			
		}
		
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		double distance = e.getDistance(); //get the distance of the scanned robot
		
		double angle = e.getBearing();

    	if(distance > 200) //this conditions adjust the fire force according the distance of the scanned robot.
     		fire(4);
    	else
        	fire(2);
			
			turnRight(angle);
			ahead(distance/2);
		}
		
		

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		double angle = e.getBearing();
		turnRight(-angle);
		ahead(50);

	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		
		double energy = getEnergy();

		double bearing = e.getBearing(); //Get the direction which is arrived the bullet.
    	if(energy < 100){ // if the energy is low, the robot go away from the enemy
        	turnRight(360-bearing); //This isn't accurate but release your robot.
        	ahead(100); //The robot goes away from the enemy.
    	}
    	else{
        	turnRight(360);
			ahead(50);
		}	
			
	}
	
	public void onHitRobot(HitRobotEvent e){
		double angle = e.getBearing();
		turnRight(angle);		
		ahead(50);

	}	
}
