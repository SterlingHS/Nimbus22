// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.RobotMap;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SmartShooter extends CommandBase {

  private final Shooter m_shooter;
  private final Limelight m_limelight;
  private final Index m_index;
  private boolean is_speed_reached;

  /** Creates a new SmartShooter. */
  public SmartShooter(Shooter subsystem1, Limelight subsystem2, Index subsystem3) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem1;
    addRequirements(m_shooter);
    m_limelight = subsystem2;
    addRequirements(m_limelight);
    m_index = subsystem3;
    addRequirements(m_index);
    is_speed_reached = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    is_speed_reached = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_limelight.is_there_target())
    {
      double current_speed = m_shooter.read_speed_shooter(); // Read the speed of the shooter
      double power_to_shooter = 1;                           // Set the power for the motors of the shooter to 100% (Max speed)

      if(current_speed > RobotMap.SHOOT_CARGO_SPEED)         // Waits until speed is over the desired speed
      {                                                      // When we reach the speed
        //m_index.cargo_index_in();                            // Get the cargo into the shooter
        is_speed_reached = true;                             // Set flag to regulate speed
      }

      if (is_speed_reached == true)                          // Regulates speed betwee two speeds
      {
        if(current_speed > RobotMap.SHOOT_CARGO_SPEED*(1+RobotMap.DISTANCE_ACCURACY)) // If the speed so fast, reduce power to motor
          power_to_shooter = .75;    // Adjust speeds with %
        else 
          if(current_speed < RobotMap.SHOOT_CARGO_SPEED*(1+RobotMap.DISTANCE_ACCURACY)) // If the speed so slow, add power to motor
            power_to_shooter = .90;  // Adjust speeds with % 
          else power_to_shooter = .80; // Power that should hit the target
      }
      SmartDashboard.getNumber("Power Shooter", power_to_shooter);
      m_shooter.shootCargoPercent(power_to_shooter); // Send value to motor
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.shootCargoStop();
    is_speed_reached = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
