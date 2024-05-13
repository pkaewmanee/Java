import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

// A Robocode robot implementing Random Movement and Wall Smoothing
public class AgileRobot extends AdvancedRobot {

    // Main robot method
    public void run() {
        // Robot main loop
        while (true) {
            randomMove();
            execute(); // Ensure all actions are executed
        }
    }

    // Method for random movement
    private void randomMove() {
        // Choose a random angle and length for movement
        double distance = 100 + (Math.random() * 400); // Random distance between 100 and 500
        double angle = -45 + (Math.random() * 90); // Random angle from -45 to 45 degrees relative to current heading

        // Apply wall smoothing
        double x = getX();
        double y = getY();
        double fieldWidth = getBattleFieldWidth();
        double fieldHeight = getBattleFieldHeight();

        // Calculate future position based on current heading and the chosen random angle
        double futureX = x + distance * Math.sin(Math.toRadians(getHeading() + angle));
        double futureY = y + distance * Math.cos(Math.toRadians(getHeading() + angle));

        // Check if future position is too close to walls
        if (futureX < 50 || futureX > fieldWidth - 50 || futureY < 50 || futureY > fieldHeight - 50) {
            // Too close to a wall; adjust angle
            angle += 180; // Turn around
        }

        // Set the robot to turn and move
        setTurnRight(angle);
        setAhead(distance);
    }

    // Respond to being hit by a bullet
    public void onHitByBullet(HitByBulletEvent e) {
        // Reverse direction on being hit
        setBack(150);
        setTurnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));
    }

    // Additional event responses can be added here (e.g., onScannedRobot, onHitWall)
}
