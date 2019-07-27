/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package sample;


import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.AlphaBot;
import robocode.ScannedRobotEvent;

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
public class CodeBlasters extends AlphaBot{
    int turnDirection = 120; // Clockwise or counterclockwise
    int dist = 50; // distance to move when we're hit
    /**
     * run: Spin around looking for a target
     */
    public void run() {
        // Set colors
        setBodyColor(Color.lightGray);
        setGunColor(Color.gray);
        setRadarColor(Color.darkGray);

        // Spin the gun around slowly... forever
        while (true) {
            turnRight(5 * turnDirection);
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {
        if (getEnergy() > 16) {
            fire(3);
        } else if (getEnergy() > 10) {
            fire(2);
        } else if (getEnergy() > 4) {
            fire(1);
        } else if (getEnergy() > 2) {
            fire(.5);
        } else if (getEnergy() > .4) {
            fire(.1);
        }
        ahead(40);
    }

    /**
     * onScannedRobot:  Fire!
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        if (e.getBearing() >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }

        turnRight(e.getBearing());
        ahead(e.getDistance() + 5);
        scan(); // Might want to move ahead again!
    }

    /**
     * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
     */
    public void onHitByBullet(HitByBulletEvent e) {

        if (getEnergy() > 16) {
            fire(3);
        } else if (getEnergy() > 10) {
            fire(2);
        } else if (getEnergy() > 4) {
            fire(1);
        } else if (getEnergy() > 2) {
            fire(.5);
        } else if (getEnergy() > .4) {
            fire(.1);
        }
        ahead(40);
    }

    /**
     * onHitRobot:  Aim at it.  Fire Hard!
     */
    public void onHitRobot(HitRobotEvent e) {
        if (e.getBearing() >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }
        turnRight(e.getBearing());

        // Determine a shot that won't kill the robot...
        // We want to ram him instead for bonus points
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
        ahead(40); // Ram him again!
    }
}
