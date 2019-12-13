/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;

public class FlywheelControl extends Command {
  private Timer timer;
  private double lastUpdate = 0;
  private static final double UPDATE_TIME = 0.2;
  public FlywheelControl() {
    requires (Robot.m_Shooter);
    timer = new Timer();
  }



  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (timer.get() - lastUpdate > UPDATE_TIME) {
      Robot.m_Shooter.updatePower();
      Robot.m_Shooter.setIndex();
      lastUpdate = timer.get();
    }

    if(Robot.m_OI.controller.getRawButton(OI.Button.A.getButtonNumber())) {
      Robot.m_Shooter.rampUp(-0.9);
    } else {
      Robot.m_Shooter.rampUp(0);
    }

    if(Robot.m_OI.controller.getRawButton(OI.Button.B.getButtonNumber())) {
      Robot.m_Shooter.index(0.5);
    } else {
      Robot.m_Shooter.index(0);
    }

    SmartDashboard.putNumber("Flywheel Power", Robot.m_Shooter.shooterPower);
    SmartDashboard.putNumber("Indexer Power", Robot.m_Shooter.indexPower);
  }

  // Make this return trsue when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_Shooter.rampUp(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
