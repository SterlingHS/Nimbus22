
package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final Pixie m_pixie = new Pixie();
  private final Index m_index = new Index();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();
  private final Limelight m_limelight = new Limelight();
  private final DriveSystem drivesystem = new DriveSystem();

  // Joysticks
  private final XboxController driverController = new XboxController(RobotMap.JOYDRIVER_USB_PORT);
  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();


    // Configure default commands
    drivesystem.setDefaultCommand(new Drive( drivesystem, driverController::getLeftX, driverController::getLeftY, driverController::getRightX) ); 

    // Configure autonomous sendable chooser
    m_chooser.setDefaultOption("Auto Shoot and Back", new Autonomous1BallandBack( m_shooter, m_pixie, drivesystem, m_intake, m_index, m_limelight));
    m_chooser.addOption("Auto Shoot, Search-Shoot", new Autonomous1Ball( m_shooter, m_pixie, drivesystem, m_intake, m_index, m_limelight));
    m_chooser.addOption("Rotate 180", new TurnAngle(drivesystem, 180));
    m_chooser.addOption("Go forward for 1sec", new MoveTime(drivesystem, -.5, 1000));
    m_chooser.addOption("Pick Up Ball with Pixy", new SearchCargo(m_pixie, drivesystem, m_intake, m_index));
    m_chooser.addOption("Smart Shooter 1", new SmartShooter1(m_shooter, m_limelight, m_index));

    SmartDashboard.putData("Auto Mode", m_chooser);
    
    //drivesystem.calibrateGyro();
    //drivesystem.resetAngle();
  }

  /*public static RobotContainer getInstance() {
    return m_robotContainer;
  }*/

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Create some buttons

    // Button To PickUpBall
    final JoystickButton PickUpBallBt = new JoystickButton(driverController, XboxController.Button.kA.value);        
    PickUpBallBt.whileHeld(new PickUpBall( m_intake, m_index ) ,true);
    SmartDashboard.putData("IntakeCargoInBt",new IntakeCargoIn( m_intake ) );

    // Button for Intake OUT
    final JoystickButton intakeCargoOutBt = new JoystickButton(driverController, XboxController.Button.kY.value);        
    intakeCargoOutBt.whileHeld(new IntakeCargoOut( m_intake ) ,true);
    SmartDashboard.putData("IntakeCargoOutBt",new IntakeCargoOut( m_intake ) );

    // Button for Intake shoulder UP 
    final POVButton intakeShoulderUpBt = new POVButton(driverController,RobotMap.POV_UP);        
    intakeShoulderUpBt.whileHeld(new IntakeShoulderUp( m_intake ) ,true);
    //SmartDashboard.putData("IntakeShoulderUpBt",new IntakeShoulderUp( m_intake ) );

    // Button for Intake shoulder DOWN // we still need to add a button to the intake shoulder down
    final POVButton intakeShoulderDownBt = new POVButton(driverController,RobotMap.POV_DOWN);        
    intakeShoulderDownBt.whileHeld(new IntakeShoulderDown( m_intake ) ,true);
    //SmartDashboard.putData("IntakeShoulderDownBt",new IntakeShoulderDown( m_intake ) );

     // Button for SimpleShooter
    final JoystickButton shootSimpleCargoBT = new JoystickButton(driverController, XboxController.Button.kRightBumper.value);        
    shootSimpleCargoBT.whileHeld(new SmartShooter0( m_shooter , m_limelight, m_index) ,true);
    //SmartDashboard.putData("shootSimpleCargoBT",new ShootSimpleCargo( m_shooter ) );

      // Button for SmartShooter
    final JoystickButton shootSmartCargoBT = new JoystickButton(driverController, XboxController.Button.kLeftBumper.value);        
    shootSmartCargoBT.whenPressed(new SmartShooter1( m_shooter, m_limelight, m_index ) ,true);
    //SmartDashboard.putData("shootSimpleCargoBT",new ShootSimpleCargo( m_shooter ) );

    // Button for IndexCargoIn
    final JoystickButton IndexCargoInBT = new JoystickButton(driverController, XboxController.Button.kB.value);        
    IndexCargoInBT.whileHeld(new IndexBringCargoIn( m_index ) ,true);
    //SmartDashboard.putData("IndexBringCargoInBT",new IndexBringCargoIn( m_index ) );

    // Button for IndexCargoOut
    final JoystickButton IndexCargoOutBT = new JoystickButton(driverController, XboxController.Button.kX.value);        
    IndexCargoOutBT.whileHeld(new IndexBringCargoOut( m_index ) ,true);
    //SmartDashboard.putData("IndexBringCargoOutBT",new IndexBringCargoOut( m_index ) );

    // Button to calibrate Navx
    SmartDashboard.putData("Calibrate NavX",new CalibrateNavX(drivesystem) );
  }

  public XboxController getDriverController() {
    return driverController;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }

 public void update_smartboard(){     
       
        // SmartDashboard.putNumber("shooter speed",RobotMap.SHOOT_CARGO_SPEED);
        RobotMap.SHOOT_CARGO_PERCENT = SmartDashboard.getNumber("shooter speed", RobotMap.SHOOT_CARGO_PERCENT);
        RobotMap.INDEX_MOTOR_SPEED = SmartDashboard.getNumber("Index speed", RobotMap.INDEX_MOTOR_SPEED);
        RobotMap.DRIVER_SLOWDOWN = SmartDashboard.getNumber("Drive speed", RobotMap.DRIVER_SLOWDOWN);
        RobotMap.INTAKECARGO_SPEED = SmartDashboard.getNumber("Intake Speed", RobotMap.INTAKECARGO_SPEED);
        RobotMap.INTAKESHOULDER_SPEED = SmartDashboard.getNumber("Shoulder Speed", RobotMap.INTAKESHOULDER_SPEED);
        SmartDashboard.putNumber("angle", drivesystem.getAngle());

        // Shooter
        SmartDashboard.putNumber("Current Speed", m_shooter.read_speed_shooter());

        //Limit switches
        //SmartDashboard.putBoolean("Index Limit switch",m_index.is_cargo_in_index());

        //Limelight
        SmartDashboard.putNumber("Distance", m_limelight.Distance_to_target());
        //SmartDashboard.putNumber("Angle Target", RobotMap.limelight_angle+m_limelight.Read_Limelight_ty());
        SmartDashboard.putNumber("Speed Target", m_shooter.speed_from_distance(m_limelight.Distance_to_target()));
        //SmartDashboard.putNumber("Power Target", m_shooter.power_from_speed(m_shooter.speed_from_distance(m_limelight.Distance_to_target())));

    }
}
