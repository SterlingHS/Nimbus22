
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Intake;

/**
 *
 */
public class IntakeShoulderDown extends CommandBase {

    private final Intake m_intake;

    public IntakeShoulderDown(Intake subsystem) {

        m_intake = subsystem;
        addRequirements(m_intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(m_intake.is_intake_down() == false) m_intake.intake_down();
        else m_intake.shoulder_stop();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
   m_intake.shoulder_stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_intake.is_intake_down();
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
