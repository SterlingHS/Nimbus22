
package frc.robot.commands;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SmartShooter2 extends CommandBase {

  private final Shooter m_shooter;
  private final Limelight m_limelight;
  private final Index m_index;
  private final DriveSystem drivesystem;
  private static int ball;
  private static boolean ready_shoot_ball1;
  private static boolean ready_shoot_ball2;

  private static long starting_time;

  /** Creates a new SmartShooter. */
  public SmartShooter2(Shooter subsystem1, Limelight subsystem2, Index subsystem3, DriveSystem susbsystem4) {
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
    ball = 1;
    ready_shoot_ball1 = false;
    ready_shoot_ball2 = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_limelight.is_there_target())
    {
      // Calculates distance and power for the shooter
      // double distance = m_limelight.Distance_to_target();
      // double volt_to_shoot = m_shooter.volts_from_distance(distance);
     
      // m_shooter.shootVolts(volt_to_shoot, 1.5*volt_to_shoot); // Send value to motor

      double distance = m_limelight.Distance_to_target();
      double speed_to_shoot = m_shooter.speed_from_distance(distance);
      double shootpercent = m_shooter.power_from_speed(speed_to_shoot);
      m_shooter.shootCargoPercent(shootpercent);

      // Check direction of the shoot and adjust if not centered
      double tx=m_limelight.Read_Limelight_tx();
      if (tx < -5) drivesystem.turnLeft();
      else if (tx > 5) drivesystem.turnRight();
           else drivesystem.stop();

      // Check if the first ball is ready to be shot in the indexer switches
      if(ball == 1 && ready_shoot_ball1 == false)
        if(m_index.is_cargo_in_index() == false) m_index.cargo_index_in();
        else
        {
          m_index.index_stop();
          ready_shoot_ball1 = true;
          start_timer();
        }

      // Shoots the first ball after 1 sec.
      if(ball == 1 && ready_shoot_ball1 == true)
      {
        if(get_timer()>1500) m_index.cargo_index_in();
        else m_index.index_stop();
        if(get_timer()>=1200) ball=2;
      }

      // Shoots the 2nd ball after 500ms of ball in indexer switches
      if(ball == 2)
      {
        boolean cargoin = false;
        if(m_index.is_cargo_in_index() == false && ready_shoot_ball2 == false) 
          cargoin = true;
        if(m_index.is_cargo_in_index() == true && ready_shoot_ball2 == false) 
        {  
          cargoin = false;
          start_timer();
          ready_shoot_ball2 = true;
        }
        if(ready_shoot_ball2 == true && get_timer() > 1500) cargoin = true;
        
        if(cargoin == true) m_index.cargo_index_in();
        else m_index.index_stop();
      }
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
    if(ready_shoot_ball2 == true && get_timer()>2000) return true;
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
