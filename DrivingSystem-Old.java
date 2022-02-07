
package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


// import edu.wpi.first.wpilibj.SerialPort;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;



/**
 *
 */
public class DrivingSystem extends Subsystem {

    private final WPI_TalonSRX frontLeft;
    private final WPI_TalonSRX rearLeft;
    private final WPI_TalonSRX frontRight;
    private final WPI_TalonSRX rearRight;
    private final MecanumDrive MecanumDrive1;


    public DrivingSystem() {
        frontLeft = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_FRONT);
        addChild("Drive Front Left", frontLeft);
        frontLeft.setInverted(false);

        rearLeft = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_BACK);
        addChild("Speed Controller 2", rearLeft);
        rearLeft.setInverted(false);

        frontRight = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_FRONT);
        addChild("Speed Controller 3", frontRight);
        frontRight.setInverted(false);

        rearRight = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_BACK);
        addChild("Speed Controller 4", rearRight);
        rearRight.setInverted(false);


        MecanumDrive1 = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    @Override
    public void initDefaultCommand() {

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());

        setDefaultCommand(new MecDrive());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void mecanumDrive(double X, double Y, double Z, double slowdown_factor) 
    {
    /*    if(slowdown_factor < 1 && slowdown_factor > 0)
        {

            X*=slowdown_factor;
            Y*=slowdown_factor;
            Z*=slowdown_factor;
        }*/

        MecanumDrive1.driveCartesian(-X, Y, Z);
        //MecanumDrive1.drivePolar(-X, Y, Z);
    }

}

