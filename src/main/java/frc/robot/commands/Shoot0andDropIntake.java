
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

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
