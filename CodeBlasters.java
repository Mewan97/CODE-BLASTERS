
import robocode.*;

public class CodeBlasters extends CharlieBot {
	int turnDirection = 1;

	public void run() {
		
		while (true) {
			
			turnRight(120 * turnDirection);
			ahead(100);
		}
	}

	public void onRobotDetected(ScannedRobotEvent e){

		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}

		turnRight(e.getBearing());
		if(getNumSentries() > 6)
			fire(1);
		if(getNumSentries() == 1)
			fire(2);	
		ahead(e.getDistance() + 5);
		scan();
	}

	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() >= 0) {
			turnDirection = -1;
		} else {
			turnDirection = 1;
		}
	
		turnRight(e.getBearing());

		if (e.getEnergy() > 16) {
			fire(4);
		} else if (e.getEnergy() > 10) {
			fire(3);
		} else if (e.getEnergy() > 4) {
			fire(1);
		} else if (e.getEnergy() > 2) {
			fire(.5);
		} else if (e.getEnergy() > .4) {
			fire(.1);
		}
		ahead(100);
	}
	
	public void onBulletHit(BulletHitEvent e){
		
		double energy = getEnergy();
		
		if(energy < 50){
			turnRight(45);
			back(20);
		}
	}
	
	public void onHitWall(HitWallEvent e){
		if(getNumSentries() < 3){
			turnRight(180);
			ahead(50);
		}
	}
}
