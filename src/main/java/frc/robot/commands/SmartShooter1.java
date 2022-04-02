
package frc.robot.commands;
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
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    start_timer();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_limelight.is_there_target())
    {
      double distance = m_limelight.Distance_to_target();
      double volt_to_shoot = m_shooter.volts_from_distance(distance);
     
      //System.out.println("Distance: " + distance + "Target Speed: " + speed_to_shoot +" - Power: " + power_to_shooter + " - Speed: " + m_shooter.read_speed_shooter() + " - Ready: " + ready);
      m_shooter.shootVolts(volt_to_shoot, 1.5*volt_to_shoot); // Send value to motor

      double tx=m_limelight.Read_Limelight_tx();

      if (tx < -8) drivesystem.turnLeft();
      else if (tx >8) drivesystem.turnRight();
           else drivesystem.stop();

      if(get_timer()>1000) m_index.cargo_index_in();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.shootCargoStop();
    m_index.index_stop();
    drivesystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(get_timer()>2000) return true;
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
