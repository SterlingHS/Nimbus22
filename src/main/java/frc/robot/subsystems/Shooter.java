
package frc.robot.subsystems;


import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 *
 */
public class Shooter extends SubsystemBase 
{
    
    private WPI_TalonSRX shooterTopMotor;
    private WPI_TalonSRX shooterBottomMotor;
    private MotorControllerGroup shooterMotor;
    private final Encoder m_encoder;

    private final TrapezoidProfile.Constraints m_constraints;
    private final ProfiledPIDController m_controller;
    private final SimpleMotorFeedforward feedforward;
    /**
    *
    */
    public Shooter() 
    {
        final double kDt = 0.02;

        shooterTopMotor = new WPI_TalonSRX(RobotMap.SHOOTER_TOP_TALON_ID);
        shooterBottomMotor = new WPI_TalonSRX(RobotMap.SHOOTER_BOTTOM_TALON_ID);
        shooterMotor = new MotorControllerGroup(shooterTopMotor,shooterBottomMotor);

        m_constraints = new TrapezoidProfile.Constraints(1.75, 0.75);
        m_controller = new ProfiledPIDController(1.0, 0.0, 0.0, m_constraints, kDt);
        feedforward = new SimpleMotorFeedforward(1, 0, 0);

        m_encoder = new Encoder(RobotMap.ShooterEncoderChannel1, RobotMap.ShooterEncoderChannel2,true, CounterBase.EncodingType.k4X);
        m_encoder.setSamplesToAverage(10);
        m_encoder.setReverseDirection(false);
    }

    @Override
    public void periodic() 
    {}

    @Override
    public void simulationPeriodic() 
    {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void shootCargo()
    {
        shooterMotor.set(RobotMap.SHOOT_CARGO_PERCENT);
    }

    public void shootCargoStop()
    {
        shooterMotor.stopMotor();
    }
    
    public void shootBallCargoIn ()
    {
        shooterMotor.set(RobotMap.REVERSE_CARGO_PERCENT);
        //System.out.println("Speed: " + m_encoder.getRate() + " - Power: " + RobotMap.REVERSE_CARGO_PERCENT);

    }    
    
    public void shootCargoPercent(double percent)
    {
        if (percent > 1) percent = 1;
        if (percent < -1) percent = -1;
        shooterMotor.set(percent);
    }

    public void shootCargoPID(double speed)
    {
        //m_controller.setGoal(50000.0);
        //double power = m_controller.calculate(-m_encoder.getRate());
        //shooterMotor.set(power);
        // System.out.println("Speed: " + speed + " - Rate: " + m_encoder.getRate() + " - Power: " + power);

        double power = m_controller.calculate(feedforward.calculate(-m_encoder.getRate(),20));
        //System.out.println("Speed: " + speed + " - Rate: " + m_encoder.getRate() + " - Power: " + power);
        shooterMotor.set(power);
    }

    public double read_speed_shooter()
    {   
        double speed = m_encoder.getRate();
        // System.out.println("Speed: " + speed + " " + m_encoder.getRaw()) ;
        return speed;
    }

    public double speed_from_distance(double desired_distance)
    { // Calculates speed for shooter in function of the distance to target
        // 13 ft with 140k
        // 10 ft with 120k 
        //  7 ft with 

        if(desired_distance > RobotMap.MAX_DISTANCE || desired_distance < RobotMap.MIN_DISTANCE) return 0;

        double m = (140-120)/(13-10);
        double b = 140 - m * 13;

        double new_speed = (m*desired_distance+b)*1000;
        // System.out.println("desired distance: " + desired_distance + " - new_speed: " + new_speed);
        return new_speed;
    }

    public double power_from_speed(double desired_speed)
    {
        // 60% power is 160k
        // 55% power is 150k
        // 50% power is 140k
        // 45% power is 125k
        // 40% power is 110k
        // 35% power is 90k
        // 30% power is 80k
        // Then we used 
        double a = .00124662;
        double b = .0568888;
        double c = 18.4751;

        desired_speed /= 1000;

        double new_power = (a*desired_speed*desired_speed+b*desired_speed+c)/100;
        double actual_speed = read_speed_shooter()/1000;
        double error_percent = (actual_speed-desired_speed)/desired_speed;
        // System.out.println("New_power: " + new_power + " - actual_speed: " + actual_speed + " - error: " + error_percent);
        if( error_percent < -RobotMap.SPEED_ACCURACY )
        {
            new_power *= (1+Math.abs(error_percent));
        }
        if( error_percent > RobotMap.SPEED_ACCURACY )
        {
            new_power *= (1-Math.abs(error_percent));
        }
        if( new_power > 1 ) new_power = 1;
        if( new_power < 0 ) new_power = 0;
        // System.out.println("New_power: " + new_power);
        return new_power;
    }

    

}

