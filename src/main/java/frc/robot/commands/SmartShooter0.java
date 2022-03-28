
package frc.robot.commands;
import frc.robot.RobotMap;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SmartShooter0 extends CommandBase {

  private final Shooter m_shooter;
  private final Limelight m_limelight;
  private final Index m_index;

  private static long starting_time;

  /** Creates a new SmartShooter. */
  public SmartShooter0(Shooter subsystem1, Limelight subsystem2, Index subsystem3) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem1;
    addRequirements(m_shooter);
    m_limelight = subsystem2;
    addRequirements(m_limelight);
    m_index = subsystem3;
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
    
      m_shooter.shootCargoPercent(RobotMap.Shoot0Speed); // Send value to motor

      if(get_timer()>1500)
      {
        m_index.cargo_index_in();
      }

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
    if(get_timer()>3000) return true;
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
