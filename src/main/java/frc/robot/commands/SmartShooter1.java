
package frc.robot.commands;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SmartShooter1 extends CommandBase {

  private final Shooter m_shooter;
  private final Limelight m_limelight;
  private final Index m_index;
  private final DriveSystem drivesystem;

  private static boolean ready;
  private static long starting_time;

  /** Creates a new SmartShooter. */
  public SmartShooter1(Shooter subsystem1, Limelight subsystem2, Index subsystem3, DriveSystem susbsystem4) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem1;
    addRequirements(m_shooter);
    m_limelight = subsystem2;
    addRequirements(m_limelight);
    m_index = subsystem3;
    addRequirements(m_index);
    drivesystem = susbsystem4;
    
    ready = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ready = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_limelight.is_there_target())
    {
      double distance = m_limelight.Distance_to_target();
      double speed_to_shoot = m_shooter.speed_from_distance(distance);
      double power_to_shooter = m_shooter.power_from_speed(speed_to_shoot);
      //System.out.println("Distance: " + distance + "Target Speed: " + speed_to_shoot +" - Power: " + power_to_shooter + " - Speed: " + m_shooter.read_speed_shooter() + " - Ready: " + ready);
      m_shooter.shootCargoPercent(power_to_shooter); // Send value to motor

      double ty=m_limelight.Read_Limelight_ty();

      if (ty < -30) drivesystem.turnLeft();
      else if (ty >30) drivesystem.turnRight();
           else drivesystem.stop();

      if(m_shooter.read_speed_shooter()>(1-RobotMap.SPEED_ACCURACY)*speed_to_shoot && ready == false)
      {
        ready = true;
        start_timer();
      }
      
      if(ready == true)
      {
        m_index.cargo_index_in();
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.shootCargoStop();
    m_index.index_stop();
    drivesystem.stop();
    ready = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(ready == true && get_timer()>1500) return true;
    return false;
  }

  private void start_timer(){
    starting_time = System.currentTimeMillis();

  }

  private double get_timer(){
    double timer = System.currentTimeMillis() - starting_time;
    //System.out.println(timer);
    return timer;
  }

}
