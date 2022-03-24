
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase {
  // variables

  /** Creates a new Limelight. */
  public Limelight() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Limelight readers
  public double Read_Limelight_tx()
  {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);	
  }

    public double Read_Limelight_ty()
  {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  }
    
    public double Read_Limelight_ta()
  {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  }
    
    public double Read_Limelight_tv()
  {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
  }

  public double Distance_to_target()
  { // Returns distance in feet to target
    double angle_of_elevation = Read_Limelight_ty()+RobotMap.limelight_angle;
    double distance=(9-RobotMap.limelight_height)/Math.tan(angle_of_elevation*Math.PI/180);
    //System.out.println("desired distance: " + distance + " - new_speed: " + Read_Limelight_ty());
    return distance;
  }

  public double Align_target()
  {
    return Read_Limelight_tx();
  }

  public boolean is_there_target()
  {
    if (Read_Limelight_tv() == 1) return true;
    return false;
  }
}
