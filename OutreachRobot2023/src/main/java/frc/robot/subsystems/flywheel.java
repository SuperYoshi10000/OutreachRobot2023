package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;

/**
 * The flywheel has several parameters: the RPM speed, the setpoint, and the RPM
 * tolerance. When told to, the flywheel will try to spin up to the setpoint
 * within the set RPM tolerance.
 * 
 * The ball is first picked up with the Intake then is fed to the Flywheel with
 * the HoodRoller. The Turret controls the direction that the ball is fired at.
 * Finally, the Hood controls the output angle and, conversely, trajectory.
 * 
 * This is a member of the Superstructure superclass.
 * 
 * @see Intake
 * @see Hood
 * @see HoodRoller
 * @see Turret
 * @see Superstructure
 */
public class Flywheel extends Subsystem {
    WPI_TalonFX m_motorLeader;
    WPI_TalonFX m_motorFollower;

    Flywheel() {
        m_motorLeader = new WPI_TalonFX(Constants.kShooterLeaderId);
        m_motorFollower = new WPI_TalonFX(Constants.kShooterFolowerId);

        m_motorLeader.setFeedbackDevice(WPI_TalonFX.FeedbackDevice.CtreMagEncoder_Relative);
        if (m_motorLeader.isSensorPresent(
                CANTalon.FeedbackDevice.CtreMagEncoder_Relative) != CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent) {
            DriverStation.reportError("Could not detect shooter encoder!", false);
        }

        m_motorLeader.changeControlMode(WPI_TalonFX.TalonControlMode.PercentVbus);
        m_motorFollower.changeControlMode(WPI_TalonFX.TalonControlMode.Follower);
        m_motorFollower.set(Constants.kShooterMasterId);

        m_motorLeader.setPID(Constants.kFlywheelKp, Constants.kFlywheelKi, Constants.kFlywheelKd, Constants.kFlywheelKf,
                Constants.kFlywheelIZone, Constants.kFlywheelRampRate, 0);
        m_motorLeader.setProfile(0);
        m_motorLeader.reverseSensor(false);
        m_motorLeader.reverseOutput(false);
        m_motorFollower.reverseOutput(true);

        m_motorLeader.setVoltageRampRate(36.0);
        m_motorFollower.setVoltageRampRate(36.0);

        m_motorLeader.enableBrakeMode(false);
        m_motorFollower.enableBrakeMode(false);

        m_motorLeader.clearStickyFaults();
        m_motorFollower.clearStickyFaults();
    }

    public synchronized double getRpm() {
        return m_motorLeader.getSpeed();
    }

    /**
     * Sets the RPM of the flywheel. The flywheel will then spin up to the set
     * speed within a preset tolerance.
     * 
     * @param Set
     *            flywheel RPM
     */
    synchronized void setRpm(double rpm) {
        m_motorLeader.changeControlMode(CANTalon.TalonControlMode.Speed);
        m_motorLeader.set(rpm);
    }

    synchronized void setOpenLoop(double speed) {
        m_motorLeader.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        m_motorLeader.set(speed);
    }

    public synchronized double getSetpoint() {
        return m_motorLeader.getSetpoint();
    }

    /**
     * @return If the flywheel RPM is within the tolerance to the specified set
     *         point.
     */
    public synchronized boolean isOnTarget() {
        return (m_motorLeader.getControlMode() == CANTalon.TalonControlMode.Speed
                && Math.abs(getRpm() - getSetpoint()) < Constants.kFlywheelOnTargetTolerance);
    }

    @Override
    public synchronized void stop() {
        setOpenLoop(0);
    }

    @Override
    public void outputToSmartDashboard() {
        SmartDashboard.putNumber("flywheel_rpm", getRpm());
        SmartDashboard.putNumber("flywheel_setpoint", m_motorLeader.getSetpoint());
        SmartDashboard.putBoolean("flywheel_on_target", isOnTarget());
        SmartDashboard.putNumber("flywheel_master_current", m_motorLeader.getOutputCurrent());
        SmartDashboard.putNumber("flywheel_slave_current", m_motorFollower.getOutputCurrent());
    }

    @Override
    public void zeroSensors() {
        // no-op
    }
}