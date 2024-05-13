// Method for linear targeting
private void linearTargeting(ScannedRobotEvent e) {
    double bulletPower = Math.min(3.0, getEnergy());
    double myX = getX();
    double myY = getY();
    double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
    double enemyX = myX + e.getDistance() * Math.sin(absoluteBearing);
    double enemyY = myY + e.getDistance() * Math.cos(absoluteBearing);
    double enemyHeading = e.getHeadingRadians();
    double enemyVelocity = e.getVelocity();

    double deltaTime = 0;
    double predictedX = enemyX;
    double predictedY = enemyY;

    while ((++deltaTime) * (20.0 - 3.0 * bulletPower) < Point2D.distance(myX, myY, predictedX, predictedY)) {
        predictedX += Math.sin(enemyHeading) * enemyVelocity;
        predictedY += Math.cos(enemyHeading) * enemyVelocity;
    }
    double theta = Utils.normalAbsoluteAngle(Math.atan2(predictedX - myX, predictedY - myY));

    setTurnGunRightRadians(Utils.normalRelativeAngle(theta - getGunHeadingRadians()));
    fire(bulletPower);
}
