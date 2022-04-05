
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Shooter;


/**
 *
 */
public class ShootSimpleCargo extends CommandBase {

    private final Shooter m_shooter;
    private final Index m_index;
    
    private static long starting_time;
 
    public ShootSimpleCargo(Shooter sub1, Index sub2) {
        m_shooter = sub1;
        addRequirements(m_shooter);
        m_index = sub2;
        addRequirements(m_index);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        start_timer();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_shooter.shootVolts(RobotMap.Shoot1Volt,RobotMap.Anti1Volt);
        if(get_timer() > 1500) m_index.cargo_index_in();
        else m_index.index_stop();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_shooter.shootCargoStop();
        m_index.index_stop();
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

    private void start_timer(){
        starting_time = System.currentTimeMillis();
    
      }
    
      private double get_timer(){
        double timer = System.currentTimeMillis() - starting_time;
        return timer;
      }
}
