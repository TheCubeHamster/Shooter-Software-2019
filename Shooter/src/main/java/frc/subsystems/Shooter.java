/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.commands.ShooterControl;
import frc.robot.RobotMap;


public class Shooter extends Subsystem {
  private WPI_TalonSRX flywheel, indexer;

  public static double shooterPower = 0;
  public static double indexPower = 0;

  public Shooter() {
    flywheel = new WPI_TalonSRX(RobotMap.FLYWHEEL_PORT);
    indexer = new WPI_TalonSRX(RobotMap.INDEXER_PORT);

    flywheel.setInverted(RobotMap.FLYWHEEL_INVERTED);
    flywheel.configVoltageCompSaturation(12.0);

    indexer.setInverted(RobotMap.INDEXER_INVERTED);
    indexer.configVoltageCompSaturation(12.0);

    flywheel.enableVoltageCompensation(true);
    indexer.enableVoltageCompensation(true);
  }


  public void setShooterPower(double shooterPower) {
    flywheel.set(shooterPower);
  }

  public void setIndexPower(double indexPower) {
    indexer.set(indexPower);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ShooterControl());

  }
}
