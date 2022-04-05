// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class TriggerR2Button extends Trigger {
  XboxController driverController;
  /** Creates a new TriggerR2Button. */
  public TriggerR2Button(XboxController sub1) {
    // Use addRequirements() here to declare subsystem dependencies.
driverController = sub1;

  }
  public boolean get() {
    return driverController.getRawAxis(3) > 0.5;
  }

}
