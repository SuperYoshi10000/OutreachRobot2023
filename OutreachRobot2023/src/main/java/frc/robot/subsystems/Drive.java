package frc.robot.subsystems;

import java.security.PublicKey;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//here is a nice reference
//https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-4/creating-test-drivetrain-program-cpp-java.html
// also code from our 2022
//https://github.com/ligerbots/RapidReact2022/blob/main/src/main/java/frc/robot/subsystems/DriveTrain.java
public class Drive extends Subsystem {
    
private final CANSparkMax m_motorRight;
private final CANSparkMax m_motorLeft;

private DifferentialDrive m_differentialDrive;

public DriveTrain(){

    m_differentialDrive = new DifferentialDrive(m_motorRight, m_motorLeft);

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
