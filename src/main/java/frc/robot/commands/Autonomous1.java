

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Pixie;
import frc.robot.subsystems.Shooter;


/**
 *
 */
    public class Autonomous1 extends CommandBase {

    private final Shooter m_shooter;
    private final DriveSystem drivesystem;
    private final Pixie m_pixie;
    private final Intake m_intake;
    private final Index m_index;
    private final Limelight m_limelight;

    private static int stage;

    private static boolean center;
    private static boolean ready;
 
    public Autonomous1(Shooter sub1, Pixie sub2, DriveSystem sub3, Intake sub4, Index sub5, Limelight sub6) {
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
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        center = false;
        ready  = false;
        stage  = 0;
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

        if( stage == 0) SmartShot();
        if( stage == 1) stage1();
        //if( stage == 2) stage2();
        if( stage == 3) SmartShot();

        
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
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }

    private void SmartShot()
    { // SmartShot 
        if(m_limelight.is_there_target())
        {
          double distance = m_limelight.Distance_to_target();
          double speed_to_shoot = m_shooter.speed_from_distance(distance);
          double power_to_shooter = m_shooter.power_from_speed(speed_to_shoot);
          // System.out.println("Distance: " + distance + "Target Speed: " + speed_to_shoot +" - Power: " + power_to_shooter + " - Speed: " + m_shooter.read_speed_shooter() + " - Ready: " + ready);
          m_shooter.shootCargoPercent(power_to_shooter); // Send value to motor
    
          if(m_shooter.read_speed_shooter()>(1-RobotMap.SPEED_ACCURACY)*speed_to_shoot)
          {
            ready = true;
          }
          if(ready == true)
            m_index.cargo_index_out();
        }
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
            // double y = m_pixie.Read_Pixy_y();

            // If the ball is in sight then go forward
            if(x < 20 && x > -20 || center == true){
                double turn = 0;
                if(x <= 10) turn = -.2;
                if(x > 10) turn = .2;
                drivesystem.mecanumDrive(0 , -.35, turn, 1);
                m_intake.cargointake();
                m_index.cargo_index_in();
                center = true;
                
                // System.out.println("center x: " + x);
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
                    // System.out.println("right x: " + x);
                }
            }//Fix this later
            
        }
    }
}