package frc.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;

    // Joysitck configuration
    public static int JOYDRIVER_USB_PORT = 0;

    // CAN IDs
    public static int DRIVETRAIN_LEFT_FRONT = 10;
    public static int DRIVETRAIN_LEFT_BACK = 11;
    public static int DRIVETRAIN_RIGHT_FRONT = 13;
    public static int DRIVETRAIN_RIGHT_BACK = 12;

    // Driver configuration
    public static double DRIVER_SLOWDOWN = .6; 
    public static int X_AXIS = 1;
    public static int Y_AXIS = 2;
    public static int Z_AXIS = 4;
}	
