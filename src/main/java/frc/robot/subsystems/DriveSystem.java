
package frc.robot.subsystems;


import frc.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.SerialPort;


/**
 *
 */
public class DriveSystem extends SubsystemBase {

private WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_FRONT);
private WPI_TalonSRX leftRear  = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_BACK);
private WPI_TalonSRX rightFront  = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_FRONT);
private WPI_TalonSRX rightRear  = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_BACK);
private MecanumDrive mecanumDrive1 = new MecanumDrive(leftFront, leftRear, rightFront, rightRear);
    
private AHRS navx_device;

    /**
    *
    */
    public DriveSystem() {
 
    leftRear.setInverted(true);
    leftFront.setInverted(false);
    rightRear.setInverted(true);
    rightFront.setInverted(true);

    mecanumDrive1.setSafetyEnabled(true);
    mecanumDrive1.setExpiration(0.1);
    mecanumDrive1.setMaxOutput(1.0);

    navx_device = new AHRS(SerialPort.Port.kUSB1);  
    navx_device.enableLogging(true);

}

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }


    public void mecanumDrive(double X, double Y, double Z, double slowdown_factor) 
    {
        if(slowdown_factor < 1 && slowdown_factor > 0)
        {
            X*=slowdown_factor;
            Y*=slowdown_factor;
            Z*=slowdown_factor;
        }

        mecanumDrive1.driveCartesian(-X, Y, -Z);
    }

    public void stop() {
        leftFront.stopMotor();
        rightFront.stopMotor();
        leftRear.stopMotor();
        rightRear.stopMotor();
    }
    public void turnRight() {
        mecanumDrive(0,0,0.4,1);
    }// might need to swap the negative signs

    public void turnLeft(){
        mecanumDrive(0,0,-0.4,1);
    }
    public void forward(){
        System.out.println("Forward");
        mecanumDrive(0,-0.35,0,1);
    }
    public void backward(){
        mecanumDrive(0,0.35,0,1);
    }
}

