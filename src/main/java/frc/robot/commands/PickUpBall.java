
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;


/**
 *
 */
public class PickUpBall extends CommandBase {

    private final Intake m_intake;
    private final Index m_index;
 
    public PickUpBall(Intake sub1, Index sub2) {
        m_intake = sub1;
        addRequirements(m_intake);
        m_index = sub2;
        addRequirements(m_index);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_intake.cargointake();
        if(m_index.is_cargo_in_index() == false) m_index.cargo_index_in();
        else m_index.index_stop();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_intake.cargointake_stop();
        m_index.index_stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
