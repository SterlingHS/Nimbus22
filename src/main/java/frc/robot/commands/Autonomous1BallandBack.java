
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Pixie;
import frc.robot.subsystems.Shooter;

public class Autonomous1BallandBack extends SequentialCommandGroup {
  /** Creates a new Autonomous1Ball. */

  private final Shooter m_shooter;
  private final Limelight m_limelight;
  private final Index m_index;
  private final DriveSystem drivesystem;
  private final Pixie m_pixie;
  private final Intake m_intake;

  public Autonomous1BallandBack(Shooter sub1, Pixie sub2, DriveSystem sub3, Intake sub4, Index sub5, Limelight sub6) {
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
    addCommands(new Shoot0andDropIntake(m_shooter, m_limelight, m_index, m_intake), 
    new MoveTime(drivesystem, .5, 1200));
  }

}
