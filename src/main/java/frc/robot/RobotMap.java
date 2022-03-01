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

    // Intake constants
    public static int INTAKECARGO_TALON_ID = 13;
    public static int INTAKESHOULDER_TALON_ID = 11;
    public static double INTAKECARGO_SPEED = 0.5;
    public static double INTAKESHOULDER_SPEED = 0.5;
    public static int INTAKELIMITSWITCH_ID = 0;

    // Joysitck configuration
    public static int JOYDRIVER_USB_PORT = 0;

    // CAN IDs
    public static int DRIVETRAIN_LEFT_FRONT = 2;
    public static int DRIVETRAIN_LEFT_BACK = 3;
    public static int DRIVETRAIN_RIGHT_FRONT = 4;
    public static int DRIVETRAIN_RIGHT_BACK = 5;

    // Driver configuration
    public static double DRIVER_SLOWDOWN = .6; 
    public static int X_AXIS = 0;
    public static int Y_AXIS = 1;
    public static int Z_AXIS = 4;

    //Shooter Constant
    public static int SHOOTER_TOP_TALON_ID = 22;
    public static int SHOOTER_BOTTOM_TALON_ID = 23;
    public static double SHOOT_CARGO_SPEED = 0.5;
    public static double REVERSE_CARGO_SPEED = -0.1;
    public static final int ShooterEncoderChannel1 = 0;
    public static final int ShooterEncoderChannel2 = 1;

    //Index Constants
    public static int INDEX_MOTOR_TALON_ID = 21;
    public static int INDEX_LIMIT_SWITCH_ID = 15;
    public static double INDEX_MOTOR_SPEED = 0.2;

    //POV Buttons
    public static int POV_UP = 90;
    public static int POV_DOWN = 270;
    public static int POV_LEFT = 180;
    public static int POV_RIGHT = 0;
    public static int POV_TOPRIGHT_DIAGONAL = 45;
    public static int POV_BOTTOMRIGHT_DIAGONAL = 315;
    public static int POV_TOPLEFT_DIAGONAL = 135;
    public static int POV_BOTTOMLEFT_DIAGONAL = 225;

    // Limelight
    public static double limelight_angle = 27.56417632;
    public static double limelight_height = 2.3;

    // SHooting variables
    public static double DISTANCE_TO_SHOOT = 10; // TBD with testing
    public static double DISTANCE_ACCURACY = .1; // TBD with testing

}

