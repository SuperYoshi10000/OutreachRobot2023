package frc.robot.subsystems;

import java.security.PublicKey;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//here is a nice reference
//https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-4/creating-test-drivetrain-program-cpp-java.html
// also code from our 2022
//https://github.com/ligerbots/RapidReact2022/blob/main/src/main/java/frc/robot/subsystems/DriveTrain.java
public class Drive extends Subsystem {
    
private final CANSparkMax m_motorRight = new CANSparkMax(Constants.DriveMotorRightId, MotorType.kBrushless);
private final CANSparkMax m_motorLeft = new CANSparkMax(Constants.DriveMotorLeftId, MotorType.kBrushless);

private DifferentialDrive m_differentialDrive;

public Drive(){

    
    m_differentialDrive = new DifferentialDrive(m_motorRight, m_motorLeft);

}

public double getRightSpeed(){
    return -m_motorRight.get();
}

public double getLeftSpeed(){
    return -m_motorLeft.get(); 

}

public void drive(double throttle, double rotate, boolean squaredInput) {
    // SmartDashboard.putNumber("driveTrain/throttle", throttle);

    m_differentialDrive.arcadeDrive(throttle, -rotate, squaredInput);
}

@Override
public void zeroSensors() {
}

@Override
public void outputToSmartDashboard() {
    
}

@Override
public void stop() {
    
}

}
