import robocode.*;

// API help: https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

public class Robocode extends AdvancedRobot {
    public void run() {
        while (true) {
            setTurnRadarRightRadians(Double.POSITIVE_INFINITY); // Keep turning radar right
            ahead(100); // Move ahead 100 units
            turnGunRight(360); // Spin gun around
            back(100); // Move back 100 units
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double enemyBearing = this.getHeading() + e.getBearing();
        double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
        double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));
        double enemyHeading = e.getHeading();
        double enemyVelocity = e.getVelocity();

        // Basic linear targeting
        double bulletPower = Math.min(3.0, getEnergy());
        double myX = getX();
        double myY = getY();
        double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
        setTurnGunRightRadians(robocode.util.Utils.normalRelativeAngle(absoluteBearing - getGunHeadingRadians() + (enemyVelocity * Math.sin(enemyHeading - absoluteBearing) / 13.0)));
        fire(bulletPower);

        // Move randomly
        setAhead((Math.random() > 0.5 ? 1 : -1) * 100);
        setTurnRight(Math.random() * 100 - 50);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        // Move away if hit
        setBack(150);
    }
}
