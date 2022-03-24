
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;


/**
 *
 */
    public class MoveBack extends CommandBase {

    private final DriveSystem drivesystem;
    private static long starting_time;

 
    public MoveBack(DriveSystem sub) {
        drivesystem = sub;

        addRequirements(drivesystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        start_timer();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(get_timer()<1200) drivesystem.mecanumDrive(0, .5, 0, 1);
        else drivesystem.stop();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivesystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(get_timer()>1250)
        {
            end(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }

    private void start_timer(){
        starting_time = System.currentTimeMillis();
    
      }
    
      private double get_timer(){
        double timer = System.currentTimeMillis() - starting_time;
        System.out.println(timer);
        return timer;
      }
}