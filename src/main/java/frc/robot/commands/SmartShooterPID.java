// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.RobotMap;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SmartShooterPID extends CommandBase {

  private final Shooter m_shooter;
  private final Limelight m_limelight;
  private final Index m_index;
  private double speed;

  /** Creates a new SmartShooter. */
  public SmartShooterPID(Shooter subsystem1, Limelight subsystem2, Index subsystem3, double spd) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem1;
    addRequirements(m_shooter);
    m_limelight = subsystem2;
    addRequirements(m_limelight);
    m_index = subsystem3;
    addRequirements(m_index);
    speed = spd;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.shootCargoPID(speed); // Test to shoot to 6000 of "speed"
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.shootCargoStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public boolean is_speed_reached(){
    double current_speed = m_shooter.read_speed_shooter();
    if(current_speed > RobotMap.SHOOT_CARGO_SPEED*(1+RobotMap.DISTANCE_ACCURACY) 
          && current_speed < RobotMap.SHOOT_CARGO_SPEED*(1+RobotMap.DISTANCE_ACCURACY))
            return true;
          
    return false;  
  }

}
