import robocode.*;
import static robocode.util.Utils.normalRelativeAngle;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.geom.Point2D;

public class HybridRobot extends AdvancedRobot {

    public void run() {
        setTurnRadarRightRadians(Double.POSITIVE_INFINITY); // Continuous radar sweep initially

        while (true) {
            if (getOthers() > 1) {
                oscillatingRadar(); // Use oscillating radar in a multi-robot context
            }
            randomMove();
            execute(); // Ensure all actions are executed
        }
    }

    private void randomMove() {
        double distance = 100 + (Math.random() * 400); // Random distance between 100 and 500
        double angle = -45 + (Math.random() * 90); // Random angle from -45 to 45 degrees
        checkWallAndMove(distance, angle);
    }

    private void checkWallAndMove(double distance, double angle) {
        double futureX = getX() + distance * Math.sin(Math.toRadians(getHeading() + angle));
        double futureY = getY() + distance * Math.cos(Math.toRadians(getHeading() + angle));

        if (futureX < 50 || futureX > getBattleFieldWidth() - 50 || futureY < 50 || futureY > getBattleFieldHeight() - 50) {
            angle += 180; // Adjust angle if too close to a wall
        }
        setTurnRight(angle);
        setAhead(distance);
    }

    private void oscillatingRadar() {
        double radarTurn = Double.POSITIVE_INFINITY;
        if (getTime() % 20 == 0) {
            radarTurn = -radarTurn; // Change direction of radar sweep every 20 ticks
        }
        setTurnRadarRightRadians(radarTurn);
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (getOthers() == 1) {
            lockOnRadar(e);
        } else {
            oscillatingRadar();
        }
        smartFiring(e.getDistance()); // Adjust firing power based on distance
        chooseTargetingMethod(e);
    }

    private void lockOnRadar(ScannedRobotEvent e) {
        double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        setTurnRadarRightRadians(2.0 * normalRelativeAngle(radarTurn));
    }

    private void smartFiring(double distance) {
        double firePower = Math.min(3.0, 400 / distance);
        fire(firePower);
    }

    private void chooseTargetingMethod(ScannedRobotEvent e) {
        if (e.getDistance() > 200 || e.getVelocity() < 2) {
            linearTargeting(e);
        } else {
            circularTargeting(e);
        }
    }

    private void linearTargeting(ScannedRobotEvent e) {
        // Calculate and fire at future position assuming linear movement
    }

    private void circularTargeting(ScannedRobotEvent e) {
        // Calculate and fire at future position assuming circular movement
    }

    public void onHitByBullet(HitByBulletEvent e) {
        // Reverse direction and turn on being hit
        setBack(150);
        setTurnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));
    }
}
