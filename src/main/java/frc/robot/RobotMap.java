package frc.robot;

public class RobotMap {

    // Intake constants
    public static int INTAKECARGO_TALON_ID = 13;
    public static int INTAKESHOULDER_TALON_ID = 12;
    public static double INTAKECARGO_SPEED = 0.65;
    public static double INTAKESHOULDER_SPEED = 0.4;
    public static int SHOULDER_LIMIT_SWITCH_UP = 2;
    public static int SHOULDER_LIMIT_SWITCH_DOWN = 3;

    // Joysitck configuration
    public static int JOYDRIVER_USB_PORT = 0;

    // CAN IDs
    public static int DRIVETRAIN_LEFT_FRONT = 2;
    public static int DRIVETRAIN_LEFT_BACK = 3;
    public static int DRIVETRAIN_RIGHT_FRONT = 4;
    public static int DRIVETRAIN_RIGHT_BACK = 5;

    // Driver configuration
    public static double DRIVER_SLOWDOWN = 1; 
    public static int X_AXIS = 0;
    public static int Y_AXIS = 1;
    public static int Z_AXIS = 4;

    //Shooter Constant
    public static int SHOOTER_TOP_TALON_ID = 22;
    public static int SHOOTER_BOTTOM_TALON_ID = 23;
    public static int SHOOTER_ANTI_TOP_ID = 10;
    public static double SHOOT_CARGO_PERCENT = 0.45;
    public static double REVERSE_CARGO_PERCENT = 0;
    public static final int ShooterEncoderChannel1 = 4;
    public static final int ShooterEncoderChannel2 = 5;

    //Index Constants
    public static int INDEX_MOTOR_TALON_ID = 21;
    public static double INDEX_MOTOR_SPEED = 0.6;
    public static int INDEXLIMITSWITCH_ID1 = 0;
    public static int INDEXLIMITSWITCH_ID2 = 1;

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
    public static double limelight_angle = 29;
    public static double limelight_height = 2;

    // Shooting variables
    public static double DISTANCE_TO_SHOOT = 10; // TBD with testing
    public static double DISTANCE_ACCURACY = .1; // TBD with testing
    public static double SPEED_ACCURACY = .02;
    public static final double MAX_DISTANCE = 20;
    public static final double MIN_DISTANCE = 5;
    public static final double Shoot0Speed = 0.37;
    public static final double SHOOTER_ANTI_TOP_PERCENT = .40;

}

