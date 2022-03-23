// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

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

    //private static Timer timer_shooter;
 
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