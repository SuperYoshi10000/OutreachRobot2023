
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



    // PID gains for flywheel velocity loop
    // Units: error is (4096 counts/rev)/100ms. Max output is +/- 1023 units.
    public static double kFlywheelKp = 0.12;
    public static double kFlywheelKi = 0.0;
    public static double kFlywheelKd = 0.5;
    public static double kFlywheelKf = 0.014;
    public static int kFlywheelIZone = (int) (1023.0 / kFlywheelKp);
    public static double kFlywheelRampRate = 0;
    public static int kFlywheelAllowableError = 0;
    public static int kShooterLeaderId = 10;//TODO needs to be set
    public static int kShooterFolowerId = 11;
    public static int kTurretTalonId = 12; 


}