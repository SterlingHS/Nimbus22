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

  /** Creates a new SmartShooter. */
  public SmartShooter(Shooter subsystem1, Limelight subsystem2) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem1;
    addRequirements(m_shooter);
    m_limelight = subsystem2;
    addRequirements(m_limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
