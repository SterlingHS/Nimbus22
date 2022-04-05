// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

public class TurnAngle extends CommandBase {

  private final DriveSystem drivesystem;
  private static double angle;
  private static double startingAngle;

  public TurnAngle(DriveSystem subs, double angle1) {
    drivesystem = subs;
    angle = angle1;
    
    addRequirements(drivesystem);

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    startingAngle = drivesystem.getAngle();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    if (angle < 0) drivesystem.turnLeft();
    else           drivesystem.turnRight();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    //double a = angle + startingAngle;
    //System.out.println("Desired Angle: " + a + " - Actual angle: " + drivesystem.getAngle() + " - Inital angle: " + startingAngle);
    if (angle >= 0 && drivesystem.getAngle() > (angle + startingAngle)*.90) return true;
    if (angle <= 0 && drivesystem.getAngle() < (startingAngle + angle)*.90) return true;
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted){
    drivesystem.stop();
  }

}


