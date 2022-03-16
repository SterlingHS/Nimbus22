// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.RobotMap;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SmartShooter1 extends CommandBase {

  private final Shooter m_shooter;
  private final Limelight m_limelight;
  private final Index m_index;

  private static boolean ready;
  private static long starting_time;

  /** Creates a new SmartShooter. */
  public SmartShooter1(Shooter subsystem1, Limelight subsystem2, Index subsystem3) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem1;
    addRequirements(m_shooter);
    m_limelight = subsystem2;
    addRequirements(m_limelight);
    m_index = subsystem3;
    addRequirements(m_index);
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
    System.out.println("Test");
    if(m_limelight.is_there_target())
    {
      double distance = m_limelight.Distance_to_target();
      double speed_to_shoot = m_shooter.speed_from_distance(distance);
      double power_to_shooter = m_shooter.power_from_speed(speed_to_shoot);
      //System.out.println("Distance: " + distance + "Target Speed: " + speed_to_shoot +" - Power: " + power_to_shooter + " - Speed: " + m_shooter.read_speed_shooter() + " - Ready: " + ready);
      m_shooter.shootCargoPercent(power_to_shooter); // Send value to motor

      if(m_shooter.read_speed_shooter()>(1-RobotMap.SPEED_ACCURACY)*speed_to_shoot && ready == false)
      {
        ready = true;
        start_timer();
        System.out.println("Timer Start");
      }
      
      if(ready == true)
      {
        m_index.cargo_index_in();
      }
    }
    if(ready == true && get_timer()>2000) end(false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.shootCargoStop();
    m_index.index_stop();
    ready = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(ready == true && get_timer()>2000) return true;
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
