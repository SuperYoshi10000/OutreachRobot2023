
package frc.robot;
/**
 * A list of constants used by the rest of the robot code. This include physics
 * constants as well as constants determined through calibrations.
 */
public class Constants {


    // Wheels
   
    // Hood constants 
    //taken from 254 2016s robot 
    // public static double kMinHoodAngle = 42.48;
    // public static double kMaxHoodAngle = 71.42;
    // public static double kBatterHoodAngle = 42.5;
    // public static double kHoodNeutralAngle = 42.5;
    // public static double kHoodMaxSafeAngle = 43.0;
    // public static double kHoodOnTargetTolerance = 0.4;
    // public static double kHoodGearReduction = 12.0 / 644.0;

    // // Turret constants
    // public static double kHardMaxTurretAngle = 109.5;
    // public static double kHardMinTurretAngle = -116.5;
    // public static double kSoftMaxTurretAngle = 108.0;
    // public static double kSoftMinTurretAngle = -115.0;
    // public static double kTurretSafeTolerance = 2.0;
    // public static double kTurretOnTargetTolerance = 1.0;
    // public static double kTurretRotationsPerTick = 14.0 / 50.0 * 14.0 / 322.0;

    // // Flywheel constants
    // public static double kFlywheelOnTargetTolerance = 100.0;
    // public static double kFlywheelGoodBallRpmSetpoint = 6200.0;
    // public static double kFlywheelBadBallRpmSetpoint = kFlywheelGoodBallRpmSetpoint;

    // Auto aiming/shooter constants
    public static double kAutoAimMinRange = 10.0;
    public static double kAutoAimMaxRange = 220.0;
    public static double kAutoShootMaxDriveSpeed = 18.0;
    public static double kAutoAimPredictionTime = 0.25;
    public static int kAutoAimMinConsecutiveCyclesOnTarget = 3;
    public static double kShootActuationTime = 0.75;
    public static double kHoodUnstowToFlywheelSpinTime = 0.4;
    public static double kLoadingTime = 0.5;
    public static double kStowingOverrideTime = 2.0;

    // Goal tracker constants
    public static double kMaxGoalTrackAge = 0.3;
    public static double kMaxTrackerDistance = 18.0;
    public static double kCameraFrameRate = 30.0;
    public static double kTrackReportComparatorStablityWeight = 1.0;
    public static double kTrackReportComparatorAgeWeight = 1.0;
    public static double kTrackReportComparatorSwitchingWeight = 3.0;
    public static double kTrackReportComparatorDistanceWeight = 2.0; // Unused

    public static int kAndroidAppTcpPort = 8254;

    public static double kLooperDt = 0.01;

    // CONTROL LOOP GAINS

    // PID gains for hood position loop
    // Units: error is degrees of hood rotation. Max output is +/- 1.0.
    // Loop runs at 100Hz
    public static double kHoodKp = 0.1;
    public static double kHoodKi = 0.0;
    public static double kHoodKd = 0.0;
    public static double kHoodDeadband = 0.3; // degrees

    // PID gains for drive velocity loop (LOW GEAR)
    // Units: error is 4096 counts/rev. Max output is +/- 1023 units.
    public static double kDriveVelocityKp = 1.0;
    public static double kDriveVelocityKi = 0.0;
    public static double kDriveVelocityKd = 6.0;
    public static double kDriveVelocityKf = 0.5;
    public static int kDriveVelocityIZone = 0;
    public static double kDriveVelocityRampRate = 0.0;
    public static int kDriveVelocityAllowableError = 0;

    // PID gains for drive base lock loop
    // Units: error is 4096 counts/rev. Max output is +/- 1023 units.
    public static double kDriveBaseLockKp = 0.5;
    public static double kDriveBaseLockKi = 0;
    public static double kDriveBaseLockKd = 0;
    public static double kDriveBaseLockKf = 0;
    public static int kDriveBaseLockIZone = 0;
    public static double kDriveBaseLockRampRate = 0;
    public static int kDriveBaseLockAllowableError = 10;

    // PID gains for constant heading velocity control
    // Units: Error is degrees. Output is inches/second difference to
    // left/right.
    public static double kDriveHeadingVelocityKp = 4.0; // 6.0;
    public static double kDriveHeadingVelocityKi = 0.0;
    public static double kDriveHeadingVelocityKd = 50.0;

    // Path following constants
    public static double kPathFollowingLookahead = 24.0; // inches
    public static double kPathFollowingMaxVel = 120.0; // inches/sec
    public static double kPathFollowingMaxAccel = 80.0; // inches/sec^2

    // PID gains for turret position loop
    // Units: error is 4096 counts/rev. Max output is +/- 1023 units.
    public static double kTurretKp = 0.7;
    public static double kTurretKi = 0.0;
    public static double kTurretKd = 30.0;
    public static double kTurretKf = 0;
    public static int kTurretIZone = (int) (1023.0 / kTurretKp);
    public static double kTurretRampRate = 0;
    public static int kTurretAllowableError = 100;

    // PID gains for intake current loop
    // Units: error is mA. Max output is +/- 1023 units.
    public static double kIntakeTargetCurrent = 20.0; // Amps
    public static double kIntakeKp = 0.04;
    public static double kIntakeKi = 0.0;
    public static double kIntakeKd = 0.0;
    public static double kIntakeKf = 0.0175;
    public static int kIntakeIZone = (int) (1023.0 / kIntakeKp);
    public static double kIntakeRampRate = 0;
    public static int kIntakeAllowableError = 0;

    // PID gains for flywheel velocity loop
    // Units: error is (4096 counts/rev)/100ms. Max output is +/- 1023 units.
    public static double kFlywheelKp = 0.12;
    public static double kFlywheelKi = 0.0;
    public static double kFlywheelKd = 0.5;
    public static double kFlywheelKf = 0.014;
    public static int kFlywheelIZone = (int) (1023.0 / kFlywheelKp);
    public static double kFlywheelRampRate = 0;
    public static int kFlywheelAllowableError = 0;


}