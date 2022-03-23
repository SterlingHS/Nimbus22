// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Shoot0andDropIntake extends ParallelCommandGroup {
  /** Creates a new Shoot0andDropIntake. */
  private final Shooter m_shooter;
  private final Limelight m_limelight;
  private final Index m_index;
  private final Intake m_intake;
  
  public Shoot0andDropIntake(Shooter subsystem1, Limelight subsystem2, Index subsystem3, Intake subsystem4) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    m_shooter = subsystem1;
    m_limelight = subsystem2;
    m_index = subsystem3;
    m_intake = subsystem4;
    addCommands(new SmartShooter0(m_shooter, m_limelight, m_index), new IntakeShoulderDown(m_intake));
  }
}
