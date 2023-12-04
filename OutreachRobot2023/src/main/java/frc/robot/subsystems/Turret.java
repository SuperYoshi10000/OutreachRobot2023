package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The Turret subsystem controls the direction the ball is fired. On the Turret
 * assembly is the Hood and Flywheel. The Turret can only rotate within 240
 * degrees (+/- 120), and mechanical bumper switches indicate when the
 * mechanical limits are reached. This is part of the Superstructure superclass.
 * 
 * The ball is first picked up with the Intake then is fed to the Flywheel with
 * the HoodRoller. The Turret controls the direction that the ball is fired at.
 * Finally, the Hood controls the output angle and trajectory of the shot.
 * 
 * @see Flywheel
 * @see Hood
 * @see HoodRoller
 * @see Intake
 * @see Superstructure
 */
public class Turret extends Subsystem {
    private final WPI_TalonFX m_motorLeader;

    Turret() {
        // The turret has one Talon.
        m_motorLeader = new WPI_TalonFX(Constants.kTurretTalonId);
        m_motorLeader.enableBrakeMode(true);
        m_motorLeader.enableLimitSwitch(true, true);
        m_motorLeader.ConfigFwdLimitSwitchNormallyOpen(true);
        m_motorLeader.ConfigRevLimitSwitchNormallyOpen(true);
        m_motorLeader.setStatusFrameRateMs(CANTalon.StatusFrameRate.Feedback, 10);
        m_motorLeader.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        if (m_motorLeader.isSensorPresent(
                CANTalon.FeedbackDevice.CtreMagEncoder_Relative) != Talon.FeedbackDeviceStatus.FeedbackStatusPresent) {
            DriverStation.reportError("Could not detect turret encoder!", false);
        }

        m_motorLeader.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

        m_motorLeader.setPID(Constants.kTurretKp, Constants.kTurretKi, Constants.kTurretKd, Constants.kTurretKf,
                Constants.kTurretIZone, Constants.kTurretRampRate, 0);
        m_motorLeader.setProfile(0);
        m_motorLeader.reverseSensor(true);
        m_motorLeader.reverseOutput(false);

        // We use soft limits to make sure the turret doesn't try to spin too
        // far.
        m_motorLeader.configForwardSoftLimitEnable(true);
        m_motorLeader.configReverseSoftLimitEnable(true);
        m_motorLeader.setForwardSoftLimit(Constants.kSoftMaxTurretAngle / (360.0 * Constants.kTurretRotationsPerTick));
        m_motorLeader.setReverseSoftLimit(Constants.kSoftMinTurretAngle / (360.0 * Constants.kTurretRotationsPerTick));
    }

    // Set the desired angle of the turret (and put it into position control
    // mode if it isn't already).
    synchronized void setDesiredAngle(Rotation2d angle) {
        m_motorLeader.changeControlMode(CANTalon.TalonControlMode.Position);
        m_motorLeader.set(angle.getRadians() / (2 * Math.PI * Constants.kTurretRotationsPerTick));
    }

    // Manually move the turret (and put it into vbus mode if it isn't already).
    synchronized void setOpenLoop(double speed) {
        m_motorLeader.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        m_motorLeader.set(speed);
    }

    // Tell the Talon it is at a given position.
    synchronized void reset(Rotation2d actual_rotation) {
        m_motorLeader.setPosition(actual_rotation.getRadians() / (2 * Math.PI * Constants.kTurretRotationsPerTick));
    }

    public synchronized Rotation2d getAngle() {
        return Rotation2d.fromRadians(Constants.kTurretRotationsPerTick * m_motorLeader.getPosition() * 2 * Math.PI);
    }

    public synchronized boolean getForwardLimitSwitch() {
        return m_motorLeader.isFwdLimitSwitchClosed();
    }

    public synchronized boolean getReverseLimitSwitch() {
        return m_motorLeader.isRevLimitSwitchClosed();
    }

    public synchronized double getSetpoint() {
        return m_motorLeader.getSetpoint() * Constants.kTurretRotationsPerTick * 360.0;
    }

    private synchronized double getError() {
        return getAngle().getDegrees() - getSetpoint();
    }

    // We are "OnTarget" if we are in position mode and close to the setpoint.
    public synchronized boolean isOnTarget() {
        return (m_motorLeader.getControlMode() == CANTalon.TalonControlMode.Position
                && Math.abs(getError()) < Constants.kTurretOnTargetTolerance);
    }

    /**
     * @return If the turret is within its mechanical limits and in the right
     *         state.
     */
    public synchronized boolean isSafe() {
        return (m_motorLeader.getControlMode() == CANTalon.TalonControlMode.Position && m_motorLeader.getSetpoint() == 0 && Math.abs(
                getAngle().getDegrees() * Constants.kTurretRotationsPerTick * 360.0) < Constants.kTurretSafeTolerance);
    }

    @Override
    public synchronized void stop() {
        setOpenLoop(0);
    }

    @Override
    public void outputToSmartDashboard() {
        SmartDashboard.putNumber("turret_error", getError());
        SmartDashboard.putNumber("turret_angle", getAngle().getDegrees());
        SmartDashboard.putNumber("turret_setpoint", getSetpoint());
        SmartDashboard.putBoolean("turret_fwd_limit", getForwardLimitSwitch());
        SmartDashboard.putBoolean("turret_rev_limit", getReverseLimitSwitch());
        SmartDashboard.putBoolean("turret_on_target", isOnTarget());
    }

    @Override
    public void zeroSensors() {
        reset(new Rotation2d());
    }
}