// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SmartShooter extends CommandBase {

  private final Shooter m_shooter;
  private final Limelight m_limelight;
  private boolean correct_speed;
  private boolean indexing;

  /** Creates a new SmartShooter. */
  public SmartShooter(Shooter subsystem1, Limelight subsystem2) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem1;
    addRequirements(m_shooter);
    m_limelight = subsystem2;
    addRequirements(m_limelight);
    correct_speed = false;
    indexing = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    correct_speed = false;
    indexing = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    check_conditions();
    //if (correct_speed < )

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }


  private boolean check_conditions()
  {
    if (m_limelight.is_there_target()) 
      if(m_limelight.Distance_to_target_correct()
      return true;
    
    
    return false;
  }
}
