// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;


import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.drive.MecanumDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class DriveSystem extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_FRONT);
private WPI_TalonSRX leftRear  = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_BACK);
private WPI_TalonSRX rightFront  = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_FRONT);
private WPI_TalonSRX rightRear  = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_BACK);
private MecanumDrive mecanumDrive1 = new MecanumDrive(leftFront, leftRear, rightFront, rightRear);

// private final static Drivetrain INSTANCE = new DriveSystem();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public DriveSystem() {
 
    leftRear.setInverted(true);
    leftFront.setInverted(false);
    rightRear.setInverted(true);
    rightFront.setInverted(true);


//mecanumDrive1 = new MecanumDrive(leftFront, leftRear, rightFront, rightRear);
//addChild("Mecanum Drive 1",mecanumDrive1);
//mecanumDrive1.setSafetyEnabled(true);
//mecanumDrive1.setExpiration(0.1);
//mecanumDrive1.setMaxOutput(1.0);



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
}

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void mecanumDrive(double X, double Y, double Z, double slowdown_factor) 
    {
        if(slowdown_factor < 1 && slowdown_factor > 0)
        {
            X*=slowdown_factor;
            Y*=slowdown_factor;
            Z*=slowdown_factor;
        }

        mecanumDrive1.driveCartesian(-X, Y, -Z);
        // mecanumDrive1.drivePolar(-X, Y, Z);
    }

    public void stop() {
        leftFront.stopMotor();
        rightFront.stopMotor();
        leftRear.stopMotor();
        rightRear.stopMotor();
    }
}

