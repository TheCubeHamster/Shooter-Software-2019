/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.commands.FlywheelControl;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class Shooter extends Subsystem {
  private WPI_TalonSRX flywheel, indexer;

  public static double shooterPower = 0;
  public static double indexPower = 0;

  public Shooter() {
    flywheel = new WPI_TalonSRX(RobotMap.FLYWHEEL_PORT);
    indexer = new WPI_TalonSRX(RobotMap.INDEXER_PORT);

    flywheel.setInverted(InvertType.None);
    flywheel.configVoltageCompSaturation(12.0);

    indexer.setInverted(InvertType.InvertMotorOutput);
    indexer.configVoltageCompSaturation(12.0);

    flywheel.enableVoltageCompensation(true);
    indexer.enableVoltageCompensation(true);
  }
  public void updatePower() {
    if (Robot.m_OI.controller.getPOV() == 0) {
      shooterPower += 0.01;
    }
    else if (Robot.m_OI.controller.getPOV() == 180) {
      shooterPower -= 0.01;
    }
  }

  public void setIndex() {
    if (Robot.m_OI.controller.getPOV() == 270) {
      indexPower += 0.01;
    }
    else if (Robot.m_OI.controller.getPOV() == 90) {
      indexPower -= 0.01;
    }
  }

  public void setShootPower() {
    flywheel.set(ControlMode.PercentOutput, shooterPower);
  }
  public void setIndexPower() {
    indexer.set(ControlMode.PercentOutput, indexPower);
  }
  public void rampUp(double power) {
    flywheel.set(ControlMode.PercentOutput, power);
  }

  public void off() {
    flywheel.set(ControlMode.PercentOutput, 0);
  }
  public void index(double index) {
    indexer.set(ControlMode.PercentOutput, index);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new FlywheelControl());

  }
}
