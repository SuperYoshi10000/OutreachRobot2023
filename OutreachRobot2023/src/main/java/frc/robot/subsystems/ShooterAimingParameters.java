package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;

/**
 * A container class to specify the shooter angle. It contains the desired
 * range and the turret angle
 */
public class ShooterAimingParameters {
    double range;
    Rotation2d turret_angle;

    public ShooterAimingParameters(double range, Rotation2d turret_angle, int track_id) {
        this.range = range;
        this.turret_angle = turret_angle;
    }

    public double getRange() {
        return range;
    }

    public Rotation2d getTurretAngle() {
        return turret_angle;
    }
}