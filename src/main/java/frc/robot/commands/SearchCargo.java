
package frc.robot.commands;
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

    private static int stage;
 
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
        stage = 1;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_pixie.getBiggestBlock();
        // System.out.println("Autonomous");

        if(stage == 1) stage1(); // Find ball
        if(stage == 2) stage2(); // Go to ball
        if(stage == 3) stage3(); // Go forward until we pick up ball
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
        if (m_index.is_cargo_in_index()) System.out.println("Interupted");
        return m_index.is_cargo_in_index();
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

    private void stage1()
    {
        drivesystem.turnRight();
        m_intake.cargointake_stop();
        m_index.index_stop();
        if(m_pixie.Read_Pixy_is_Ball() == true) stage = 2;
    }

    private void stage2()
    {
        double x = m_pixie.Read_Pixy_x() - 240;

        if(x > 20) drivesystem.turnLeft();
        if(x < -20) drivesystem.turnRight();
            
        m_intake.cargointake_stop();
        m_index.index_stop();
        if(-20 < x && x < 20) stage = 3;
    }

    private void stage3()
    {
        double x = m_pixie.Read_Pixy_x() - 240;

        // If the ball is in sight then go forward
        double turn = 0;
        double forward = -.20;
        //System.out.println("x: " + x);
        if(x < -10) turn = -.15;
        if(x > 10)  turn = .15;
        if(m_pixie.Read_Pixy_is_Ball()) forward = -0.5;
        drivesystem.mecanumDrive(0 , forward, turn, 1);
        m_intake.cargointake();
        m_index.cargo_index_in(); 
    }
}