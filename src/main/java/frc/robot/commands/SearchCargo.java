
package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pixie;


/**
 *
 */
    public class SearchCargo extends CommandBase {

    private final DriveSystem drivesystem;
    private final Pixie m_pixie;
    private final Intake m_intake;
    private final Index m_index;

    private static boolean center;
 
    public SearchCargo(Pixie sub2, DriveSystem sub3, Intake sub4, Index sub5) {
        drivesystem = sub3;
        m_pixie = sub2;
        m_intake = sub4;
        m_index = sub5;
 
        addRequirements(drivesystem);
        addRequirements(m_pixie);
        addRequirements(m_intake);
        addRequirements(m_index);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        center = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_pixie.getBiggestBlock();
        // System.out.println("Autonomous");
        SmartDashboard.putNumber("Pixie x", m_pixie.Read_Pixy_x());
        SmartDashboard.putNumber("Pixie y", m_pixie.Read_Pixy_y());
        SmartDashboard.putBoolean("Pixie ball", m_pixie.Read_Pixy_is_Ball());
        SmartDashboard.putNumber("Pixie angle", m_pixie.Read_Pixy_angle());
        SmartDashboard.putNumber("Pixie signature", m_pixie.Read_Pixy_signature());
        SmartDashboard.putNumber("Pixie dimx", m_pixie.Read_Pixy_dimx());
        SmartDashboard.putNumber("Pixie dimy", m_pixie.Read_Pixy_dimy());

        stage1();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivesystem.stop();
        m_index.index_stop();
        m_intake.cargointake_stop();
        m_intake.shoulder_stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (m_index.is_cargo_in_index())
        System.out.println("Interupted");
        return m_index.is_cargo_in_index();
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

    private void stage1()
    {
        if(m_pixie.Read_Pixy_is_Ball() == false && center == false) {
            drivesystem.turnRight();
            m_intake.cargointake_stop();
            m_index.index_stop();
        }
        else
        {
            double x = m_pixie.Read_Pixy_x() - 240;

            // If the ball is in sight then go forward
            if(-20 < x && x < 20 || center == true){
                double turn = 0;
                System.out.println("x: " + x);
                if(x <= 10) turn = -.15;
                if(x > 10) turn = .15;
                drivesystem.mecanumDrive(0 , -.35, turn, 1);
                m_intake.cargointake();
                m_index.cargo_index_in();
                center = true;
            }
            else {
                if(x <= 20){
                    drivesystem.turnLeft();
                    m_intake.cargointake_stop();
                    m_index.index_stop();
                    center = false;
                    // System.out.println("left x: " + x);
                }
                else 
                {
                    drivesystem.turnRight();
                    m_intake.cargointake_stop();
                    m_index.index_stop();
                    center = false;  
                }
            } 
        }
    }
}