/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package sample;


import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.*;


/**
 * RamFire - a sample robot by Mathew Nelson.
 * <p>
 * Drives at robots trying to ram them.
 * Fires when it hits them.
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */
public class CodeBlasters extends BravoBot
{
    /**
     * run: CodeBlasters's default behavior
     */

	/*boolean peek; // Don't turn if there's a robot there
	double moveAmount;*/
    int turnDirection = 1;

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
            ahead(50);
            turnRight(120 * turnDirection);

        }

    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Calculate exact location of the robot
        double absoluteBearing = getHeading() + e.getBearing();
        double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

        if (e.getBearing() >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }
        // If it's close enough, fire!
        if (Math.abs(bearingFromGun) <= 3) {

            turnRight(bearingFromGun*turnDirection);
            // We check gun heat here, because calling fire()
            // uses a turn, which could cause us to lose track
            // of the other robot.
            if (getGunHeat() == 0) {
                fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
            }
        } // otherwise just set the gun to turn.
        // Note:  This will have no effect until we call scan()
        else {
            turnRight(bearingFromGun);
        }
        // Generates another scan event if we see a robot.
        // We only need to call this if the gun (and therefore radar)
        // are not turning.  Otherwise, scan is called automatically.
        if (bearingFromGun == 0) {
            scan();
        }// Might want to move ahead again!
    }



    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        double angle = e.getBearing();
        turnRight(-angle);
        ahead(100);

    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like

        double energy = getEnergy();

        double bearing = e.getBearing(); //Get the direction which is arrived the bullet.
        if(energy < 100){ // if the energy is low, the robot go away from the enemy
            turnRight(-bearing); //This isn't accurate but release your robot.
            ahead(100); //The robot goes away from the enemy.
        }
        else{
            turnRight(360);
            ahead(50);
        }

    }
    public void fireAt(HitRobotEvent e){
        if (e.getEnergy() > 16) {
            fire(3);
        } else if (e.getEnergy() > 10) {
            fire(2);
        } else if (e.getEnergy() > 4) {
            fire(1);
        } else if (e.getEnergy() > 2) {
            fire(.5);
        } else if (e.getEnergy() > .4) {
            fire(.1);
        }
    }
    public void onHitRobot(HitRobotEvent e){
        double angle = e.getBearing();
        turnRight(angle);
        fireAt(e);
        back(50);

    }
}