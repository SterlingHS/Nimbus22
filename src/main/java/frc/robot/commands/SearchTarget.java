
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Limelight;


/**
 *
 */
    public class SearchTarget extends CommandBase {

    private final DriveSystem drivesystem;
    private final Limelight m_limelight;
 
    public SearchTarget(DriveSystem sub1, Limelight sub2) {
        drivesystem = sub1;
        m_limelight = sub2;
 
        addRequirements(drivesystem);
        addRequirements(m_limelight);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        stage1();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivesystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_limelight.is_there_target() == true && m_limelight.Read_Limelight_tx() < 3 && m_limelight.Read_Limelight_tx() > -3 ) return true;
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

    private void stage1()
    {
        if(m_limelight.is_there_target() == true)
        {
            if(m_limelight.Read_Limelight_tx() > 3) drivesystem.turnRight();
            if(m_limelight.Read_Limelight_tx() < -3) drivesystem.turnLeft();

        }
        else drivesystem.turnLeft();
    }
}