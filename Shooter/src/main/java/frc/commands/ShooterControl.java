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

public class ShooterControl extends Command {
  private Timer timer;
  private double lastUpdate;
  private double shooterPower, indexPower;

  private static final double UPDATE_TIME = 0.25;
  public ShooterControl() {
    requires(Robot.m_Shooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer = new Timer();
    timer.reset();
    timer.start();
    lastUpdate = 0;
    shooterPower = 0;
    indexPower = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    if(timer.get() - lastUpdate > UPDATE_TIME) {
      if(Robot.m_OI.controller.getPOV() == 0) {
        shooterPower += 0.01;
        lastUpdate = timer.get();
      } else if(Robot.m_OI.controller.getPOV() == 180) {
        shooterPower -= 0.01;
        lastUpdate = timer.get();
      }
      
      if(Robot.m_OI.controller.getPOV() == 90) {
        indexPower += 0.01;
        lastUpdate = timer.get();
      } else if(Robot.m_OI.controller.getPOV() == 270) {
        indexPower -= 0.01;
        lastUpdate = timer.get();
      }
    }

    if(Robot.m_OI.controller.getRawButton(OI.Button.A.getButtonNumber())) {
      Robot.m_Shooter.setShooterPower(shooterPower);
    } else {
      Robot.m_Shooter.setShooterPower(0);
    }

    if(Robot.m_OI.controller.getRawButton(OI.Button.B.getButtonNumber())) {
      Robot.m_Shooter.setIndexPower(indexPower);
    } else {
      Robot.m_Shooter.setIndexPower(0);
    }


    SmartDashboard.putNumber("Indexer Power", indexPower);
    SmartDashboard.putNumber("Shooter Power", shooterPower);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_Shooter.setIndexPower(0);
    Robot.m_Shooter.setShooterPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
