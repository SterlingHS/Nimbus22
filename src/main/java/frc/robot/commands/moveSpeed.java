
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveSystem;


/**
 *
 */
    public class moveSpeed extends CommandBase {

    private final DriveSystem drivesystem;

    private double speed;

    public moveSpeed(DriveSystem sub, double speed1) {
        drivesystem = sub;
        speed = speed1;

        addRequirements(drivesystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivesystem.forwardSpeed(speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivesystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }


}