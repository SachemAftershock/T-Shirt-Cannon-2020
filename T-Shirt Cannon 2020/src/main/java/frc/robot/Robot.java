//Arhum Mudassir
//Alex Manolt
//Created November/21st/2020
//T-Shirt Cannon 2020

// The two joysticks on the xbox controller drive using tank drive
// Only pressing Left and Right Bumper turns the turret left and right

// Cannon1 Use A and Left bumper
// Cannon2 Use A and Right bumper
// Cannon3 Use B and Left bumper
// Cannon4 Use B and Right bumper
// Cannon5 Use X and Left bumper
// Cannon6 Use X and Right bumper

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Robot extends TimedRobot {
  
  VictorSP frontLeft = new VictorSP(1);
  VictorSP rearLeft = new VictorSP(2);
  SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeft, rearLeft);
 
  VictorSP frontRight = new VictorSP(3);
  VictorSP rearRight = new VictorSP(4);
  SpeedControllerGroup m_right = new SpeedControllerGroup(frontRight, rearRight); 
  //Might need to use the follow method instead of the speed controller group
 
  DifferentialDrive m_robotDrive = new DifferentialDrive(m_left, m_right);
  
  private final XboxController controller  = new XboxController(0);

  Solenoid Turret_Left;
  Solenoid Turret_Right;

  Solenoid Cannon1;
  Solenoid Cannon2;
  Solenoid Cannon3;
  Solenoid Cannon4;
  Solenoid Cannon5;
  Solenoid Cannon6;
  
  

  public Robot() {

  Turret_Left = new Solenoid(1);
  Turret_Right = new Solenoid(2);
  
  Cannon1 = new Solenoid(3);
  Cannon2 = new Solenoid(4);
  Cannon3 = new Solenoid(5);
  Cannon4 = new Solenoid(6);
  Cannon5 = new Solenoid(7);
  Cannon6 = new Solenoid(8);
  

  //Check if the .kleft and .kright correspond to turret movement
  //Check if the port numbers correspond to the right cannon
  //See what kOFF does
  //Check if the turret is controlled by a single or double solenoid

  }


  @Override
  public void teleopPeriodic() {
    
    //Tank Drive Control
    m_robotDrive.tankDrive(controller.getY(Hand.kRight), controller.getY(Hand.kLeft));

  
    //Turret Control

    if(controller.getBumper(Hand.kLeft)){
      Turret_Left.set(Value.kForward);
      Turret_Right.set(Value.kReverse);
    }

    if(controller.getBumper(Hand.kRight)){
      Turret_Left.set(Value.kReverse);
      Turret_Right.set(Value.kForward);
    }  

    if(controller.getYButton()){
      Turret_Left.set(Value.kOff);
      Turret_Right.set(Value.kOff);
    }

    
    //Cannon Controll

    if(controller.getAButton()){
      if(controller.getBumperPressed(Hand.kLeft)){
        Cannon1.set(true);
      }
      if(controller.getBumperPressed(Hand.kRight)){
        Cannon2.set(true);
      }
    }

    if(controller.getBButton()){
      if(controller.getBumperPressed(Hand.kLeft)){
        Cannon3.set(true);
      }
      if(controller.getBumperPressed(Hand.kRight)){
        Cannon4.set(true);
      }
    }

    if(controller.getXButton()){
      if(controller.getBumperPressed(Hand.kLeft)){
        Cannon5.set(true);
      }
      if(controller.getBumperPressed(Hand.kRight)){
        Cannon6.set(true);
      }
    }
  }
}  