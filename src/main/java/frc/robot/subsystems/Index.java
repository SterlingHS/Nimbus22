
package frc.robot.subsystems;


import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 *
 */
public class Index extends SubsystemBase 
{
    
    private WPI_TalonSRX indexMotor;
    private DigitalInput indexLimitSwitch1;
    private DigitalInput indexLimitSwitch2;

    /**
    *
    */
    public Index() 
    {
        indexMotor = new WPI_TalonSRX(RobotMap.INDEX_MOTOR_TALON_ID);
        indexLimitSwitch1 = new DigitalInput(RobotMap.INDEXLIMITSWITCH_ID1);
        indexLimitSwitch2 = new DigitalInput(RobotMap.INDEXLIMITSWITCH_ID2);
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


    public void cargo_index_in()
    {
        indexMotor.set(-.3); //RobotMap.INDEX_MOTOR_SPEED);
    }

    public void cargo_index_out()
    {
        indexMotor.set(.3); //RobotMap.INDEX_MOTOR_SPEED);
    }
    
    public void index_stop()
    {
        indexMotor.stopMotor();
    }

    public boolean is_cargo_in_index()
    {
        return (indexLimitSwitch1.get() || indexLimitSwitch2.get());
    }
}

