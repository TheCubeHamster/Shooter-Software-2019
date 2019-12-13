/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.commands.FlywheelControl;
import frc.commands.IndexerControl;

/**
 * Add your docs here.
 */
public class OI {

    public static enum Button {
        A(1),
        B(2),
        X(3),
        Y(4),
        LBumper(5),
        RBumper(4),
        Start(6),
        Back(7),
        LJoystickButton(8),
        RJoystickButton(9);
        private int buttonNumber;
        private Button(int buttonNumber) {
            this.buttonNumber = buttonNumber;
        }
        public int getButtonNumber() {
            return buttonNumber;
        }
    }
    public static enum Axis {
        lJoyX(0),
        lJoyY(1),
        rJoyX(4),
        rJoyY(5),
        lTrigger(2),
        rTrigger(3);
        private int axisNumber;
        private Axis(int axisNumber) {
            this.axisNumber = axisNumber;
        }
        public int getAxisNumber() {
            return axisNumber;
        }
    }
    public Joystick controller;
    public JoystickButton buttonA, buttonB, buttonX, buttonY;
    int power = 0;
    public OI() {
        controller = new Joystick(0);
        buttonA = new JoystickButton(controller, Button.A.getButtonNumber());
        buttonB = new JoystickButton(controller, Button.B.getButtonNumber());
        buttonX = new JoystickButton(controller, Button.X.getButtonNumber());
        buttonY = new JoystickButton(controller, Button.Y.getButtonNumber());
    }
}