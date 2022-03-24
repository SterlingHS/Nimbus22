
package frc.robot.subsystems;


import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 *
 */
public class Intake extends SubsystemBase 
{
    
    private WPI_TalonSRX intakeCargo;
    private WPI_TalonSRX intakeShoulder;
    private DigitalInput shoulderLimitSwitchUP;
    private DigitalInput shoulderLimitSwitchDOWN;

    /**
    *
    */
    public Intake() 
    {
        intakeCargo = new WPI_TalonSRX(RobotMap.INTAKECARGO_TALON_ID);
        intakeShoulder = new WPI_TalonSRX(RobotMap.INTAKESHOULDER_TALON_ID);
        shoulderLimitSwitchUP = new DigitalInput(RobotMap.SHOULDER_LIMIT_SWITCH_UP);
        shoulderLimitSwitchDOWN = new DigitalInput(RobotMap.SHOULDER_LIMIT_SWITCH_DOWN);
    }

    @Override
    public void periodic() 
    {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() 
    {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void cargointake()
    {
        intakeCargo.set(RobotMap.INTAKECARGO_SPEED);
    }

    public void cargointake_clear()
    {
        intakeCargo.set(-RobotMap.INTAKECARGO_SPEED);
    }

    public void cargointake_stop()
    {
        intakeCargo.stopMotor();
    }

    public void intake_up()
    {
        if (is_intake_up())
        {
            cargointake_stop();
        }
        else 
        { 
            intakeShoulder.set(RobotMap.INTAKESHOULDER_SPEED);
        }
    }

    public void intake_down()
    { 
        intakeShoulder.set(-RobotMap.INTAKESHOULDER_SPEED); 
    }

    public void shoulder_stop()
    {
        intakeShoulder.stopMotor();
    }

    public boolean is_intake_up()
    {
        return shoulderLimitSwitchUP.get();
    }

    public boolean is_intake_down()
    {
        return shoulderLimitSwitchDOWN.get();
    }
}

