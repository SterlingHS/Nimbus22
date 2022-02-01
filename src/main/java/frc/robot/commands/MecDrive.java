

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class MecDrive extends Command {

    public MecDrive() {

        //requires(Robot.drivingSystem);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double X = Robot.oi.joyDriver.getRawAxis(RobotMap.X_AXIS);
        double Y = Robot.oi.joyDriver.getRawAxis(RobotMap.Y_AXIS);
        double Z = Robot.oi.joyDriver.getRawAxis(RobotMap.Z_AXIS);
	
        Robot.drivesystem.mecanumDrive(X, Y, Z, RobotMap.DRIVER_SLOWDOWN);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
