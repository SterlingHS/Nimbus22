// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase {
  // variables


  /** Creates a new Limelight. */
  public Limelight() {}

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
  {
    double angle_of_elevation = Read_Limelight_ty()+RobotMap.limelight_angle;
    double distance=(9-RobotMap.limelight_height)/Math.atan(angle_of_elevation*Math.PI/180);
    return distance;
  }
}


/*

NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
NetworkTableEntry tx = table.getEntry("tx");
NetworkTableEntry ty = table.getEntry("ty");
NetworkTableEntry ta = table.getEntry("ta");

//read values periodically
double x = tx.getDouble(0.0);
double y = ty.getDouble(0.0);
double area = ta.getDouble(0.0);

//post to smart dashboard periodically
SmartDashboard.putNumber("LimelightX", x);
SmartDashboard.putNumber("LimelightY", y);
SmartDashboard.putNumber("LimelightArea", area);  

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static Pixy2 getPixyCamera1() {
		return pixy;
  }
  
  public static void getBiggestBlock() 
  {
		// Gets the number of "blocks", identified targets, that match signature 1 on the Pixy2,
		// does not wait for new data if none is available,
		// and limits the number of returned blocks to 25, for a slight increase in efficiency
		int blockCount = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 25);
		// System.out.println("Found " + blockCount + " blocks!"); // Reports number of blocks found
		if (blockCount > 0)//might need to be >=
		{
			ArrayList<Block> blocks = pixy.getCCC().getBlocks(); // Gets a list of all blocks found by the Pixy2
			Block largestBlock = null;
			for (Block block : blocks) 
			{ // Loops through all blocks and finds the widest one
				if (largestBlock == null) {
					largestBlock = block;
				} else if (block.getWidth() > largestBlock.getWidth()) {
					largestBlock = block;
				}
			
			}
            ball = true;
            x = largestBlock.getX();
			y = largestBlock.getY();
		}
		else
		{
			x = 0;
			y = 0;
			ball = false;
		}


	}
	public double Read_Pixy_x()
	{
		return x;
	}
	public double Read_Pixy_y()
	{
		return y;
	}
	public boolean Read_Pixy_is_Ball()
	{
		return ball;
    }
}
*/   


