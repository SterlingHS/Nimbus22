// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems
  private final Index m_index = new Index();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();
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
      
    //Smartdashboard Subsystems
    //SmartDashboard.putData(m_intake);
    //SmartDashboard.putData(Robot.drivesystem);
    //SmartDashboard Buttons
    //SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    //SmartDashboard.putData("Drive", new Drive( Robot.drivesystem ));
    //SmartDashboard.putData("IntakeCargoIn", new IntakeCargoIn( m_intake ));
    //SmartDashboard.putData("IntakeCargoOut", new IntakeCargoOut( m_intake ));
    //SmartDashboard.putData("IntakeShoulderUp", new IntakeShoulderUp( m_intake ));
    //SmartDashboard.putData("IntakeShoulderDown", new IntakeShoulderDown( m_intake ));

    // Configure default commands
    drivesystem.setDefaultCommand(new Drive( drivesystem, driverController::getLeftX, driverController::getLeftY, driverController::getRightX) ); //driverController::getRightZ) );

    // Configure autonomous sendable chooser
    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  /* public static RobotContainer getInstance() {
    return m_robotContainer;
  } */

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Create some buttons

    // Button for Intake IN
    final JoystickButton intakeCargoInBt = new JoystickButton(driverController, XboxController.Button.kA.value);        
    intakeCargoInBt.whileHeld(new IntakeCargoIn( m_intake ) ,true);
    SmartDashboard.putData("IntakeCargoInBt",new IntakeCargoIn( m_intake ) );

    // Button for Intake OUT
    final JoystickButton intakeCargoOutBt = new JoystickButton(driverController, XboxController.Button.kY.value);        
    intakeCargoOutBt.whileHeld(new IntakeCargoOut( m_intake ) ,true);
    SmartDashboard.putData("IntakeCargoOutBt",new IntakeCargoOut( m_intake ) );

    // Button for Intake shoulder UP // we still need to add a button to the intake shoulder Up
    // final POVButton intakeShoulderUpBt = new POVButton(driverController,RobotMap.POV_UP);        
    // intakeShoulderUpBt.whileHeld(new IntakeShoulderUp( m_intake ) ,true);
    // SmartDashboard.putData("IntakeShoulderUpBt",new IntakeShoulderUp( m_intake ) );

    // Button for Intake shoulder DOWN // we still need to add a button to the intake shoulder down
    // final POVButton intakeShoulderDownBt = new POVButton(driverController,RobotMap.POV_DOWN);        
    // intakeShoulderDownBt.whileHeld(new IntakeShoulderDown( m_intake ) ,true);
    // SmartDashboard.putData("IntakeShoulderDownBt",new IntakeShoulderDown( m_intake ) );

     // Button for SimpleShooter
    final JoystickButton shootSimpleCargoBT = new JoystickButton(driverController, XboxController.Button.kRightBumper.value);        
    shootSimpleCargoBT.whileHeld(new ShootSimpleCargo( m_shooter ) ,true);
    SmartDashboard.putData("shootSimpleCargoBT",new ShootSimpleCargo( m_shooter ) );

    // Button for IndexCargoIn
    final JoystickButton IndexCargoInBT = new JoystickButton(driverController, XboxController.Button.kB.value);        
    IndexCargoInBT.whileHeld(new IndexBringCargoIn( m_index ) ,true);
    SmartDashboard.putData("IndexBringCargoInBT",new IndexBringCargoIn( m_index ) );

    // Button for IndexCargoOut
    final JoystickButton IndexCargoOutBT = new JoystickButton(driverController, XboxController.Button.kX.value);        
    IndexCargoOutBT.whileHeld(new IndexBringCargoOut( m_index ) ,true);
    SmartDashboard.putData("IndexBringCargoOutBT",new IndexBringCargoOut( m_index ) );
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
        //SmartDashboard.putBoolean("Rotator Right", rotator...);
        //SmartDashboard.putNumber("intake speed",RobotMap.INTAKECARGO_SPEED);
        
        SmartDashboard.putNumber("drive speed",RobotMap.DRIVER_SLOWDOWN);
        //SmartDashboard.putNumber("Index speed",RobotMap.INDEX_MOTOR_SPEED);
        SmartDashboard.putBoolean("Index Limit switch",m_index.is_cargo_in_index());
        SmartDashboard.putBoolean("Intake limit switch",m_intake.is_intake_up());

        RobotMap.SHOOT_CARGO_SPEED = SmartDashboard.getNumber("Shooter Speed", RobotMap.SHOOT_CARGO_SPEED);
        RobotMap.INDEX_MOTOR_SPEED = SmartDashboard.getNumber("Index Speed", RobotMap.INDEX_MOTOR_SPEED);
        RobotMap.INTAKECARGO_SPEED = SmartDashboard.getNumber("Intake Speed", RobotMap.INTAKECARGO_SPEED);
        
       
        // SmartDashboard.putNumber("shooter speed",RobotMap.SHOOT_CARGO_SPEED);
        RobotMap.SHOOT_CARGO_SPEED = SmartDashboard.getNumber("shooter speed", .5);
        RobotMap.INDEX_MOTOR_SPEED = SmartDashboard.getNumber("Index speed", .2);
        RobotMap.DRIVER_SLOWDOWN = SmartDashboard.getNumber("Drive speed", .6);
        RobotMap.INTAKECARGO_SPEED = SmartDashboard.getNumber("Intake Speed", .5);
        RobotMap.INTAKESHOULDER_SPEED = SmartDashboard.getNumber("Shoulder Speed", 0)

        //Limit switches
        SmartDashboard.putBoolean("Index Limit switch",m_index.is_cargo_in_index());
        SmartDashboard.putBoolean("Intake limit switch",m_intake.is_intake_up());

        // SmartDashboard.putNumber("Limelight TX", shooter.Read_Limelight_tx());
        // SmartDashboard.putNumber("Limelight TY", shooter.Read_Limelight_ty());
        // SmartDashboard.putNumber("Limelight TA", shooter.Read_Limelight_ta());
        // SmartDashboard.putNumber("Limelight TV", shooter.Read_Limelight_tv());
        // SmartDashboard.putBoolean("Pixy Ball",pixyTracker.Read_Pixy_is_Ball());
        // SmartDashboard.putNumber("x from pixy",pixyTracker.Read_Pixy_x());
    }
}

