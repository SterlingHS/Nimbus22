// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Pixie;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Autonomous1Ball extends SequentialCommandGroup {
  /** Creates a new Autonomous1Ball. */

  private final Shooter m_shooter;
  private final Limelight m_limelight;
  private final Index m_index;
  private final DriveSystem drivesystem;
  private final Pixie m_pixie;
  private final Intake m_intake;

  public Autonomous1Ball(Shooter sub1, Pixie sub2, DriveSystem sub3, Intake sub4, Index sub5, Limelight sub6) {
    drivesystem = sub3;
    m_pixie = sub2;
    m_intake = sub4;
    m_index = sub5;
    m_shooter = sub1;
    m_limelight = sub6;
    addRequirements(m_shooter);
    addRequirements(drivesystem);
    addRequirements(m_pixie);
    addRequirements(m_intake);
    addRequirements(m_index);
    addRequirements(m_limelight); 

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new SmartShooter1(m_shooter, m_limelight, m_index), new SearchCargo(m_pixie, drivesystem, m_intake, m_index));
  }
}
