
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.ArrayList;

import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.SPILink;
/**
 *
 */
public class Pixie extends SubsystemBase 
{
    private static Pixy2 pixy = null;
    private static double x , y, angle, dimx, dimy;
	private static int signature;
    private static boolean ball;

    /**
    *
    */
    public Pixie() 
    {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        pixy = Pixy2.createInstance(new SPILink()); // Creates a new Pixy2 camera using SPILink
        pixy.init(); // Initializes the camera and prepares to send/receive data
        pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
        pixy.setLED(255, 255, 255); // Sets the RGB LED to full white  
    }

    @Override
    public void periodic() 
    {
        // This method will be called once per scheduler run
		getBiggestBlock();
    }

    @Override
    public void simulationPeriodic() 
    {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static Pixy2 getPixyCamera1() {
		return pixy;
  }
  
  	public void getBiggestBlock() 
  	{
		// Gets the number of "blocks", identified targets, that match signature 1 on the Pixy2,
		// does not wait for new data if none is available,
		// and limits the number of returned blocks to 25, for a slight increase in efficiency
		int blockCount;
		if(DriverStation.getAlliance() == DriverStation.Alliance.Blue)
			blockCount = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 25);
		else
			blockCount = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG2, 25);
		// System.out.println("Found " + blockCount + " blocks!"); // Reports number of blocks found
		// SmartDashboard.putNumber("Pixy BlocksCount", blockCount);

		if (blockCount > 0)
		{
			ArrayList<Block> blocks = pixy.getCCC().getBlockCache(); // Gets a list of all blocks found by the Pixy2
			if (blocks.size() > 0)
			{
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
				angle = largestBlock.getAngle();
				signature = largestBlock.getSignature();
				// System.out.println("Signature:" + signature);
				dimx = largestBlock.getWidth();
				dimy = largestBlock.getHeight();
				//largestBlock.print();
			}
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
	public double Read_Pixy_angle()
	{
		return angle;
	}
	public double Read_Pixy_signature()
	{
		return signature;
	}
	public double Read_Pixy_dimx()
	{
		return dimx;
	}
	public double Read_Pixy_dimy()
	{
		return dimy;
	}
	public boolean Read_Pixy_is_Ball()
	{
		return ball;
    }
	
}
   


