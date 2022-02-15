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
import frc.robot.subsystems.Shooter;


/**
 *
 */
    public class SmartDrive extends CommandBase {

    private final Shooter m_shooter;
 
    public SmartDrive(Shooter subsystem) {
        m_drive = subsystems;
        m_pixie = subsystems;
        m_intake = subsystems;
        m_index = subsystems;
        addRequirements(m_drive);
        addRequirements(m_pixie);
        addRequirements(m_intake);
        addRequirements(m_index);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if m_pixie.Read_Pixy_is_Ball() == false {
            m_drive.turnRight();
        }
        else{
            int x = m_pixie.Read_Pixy_x();
            int y = m_pixes.Read_Pixy_y();

            if x < 20 && x > -20{
                m_drive.turnLeft();
            }
            else {
                m_drive.turnRight();
            }//Fix this later
            if x < 20 && x > -20 {
                m_drive.forward();
             // m_intake.intake_down() // The shoulder might already be down
                m_intake.cargointake()
                
            }
            else {
                //something might be here
            }
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_shooter.shootCargoStop();
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
