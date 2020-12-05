//Arhum Mudassir
//Alex Manolt
//Created November/21st/2020
//T-Shirt Cannon 2020

// The two joycons on the xbox controller drive using tank drive
// Only pressing Left and Right Bumper turns the canon left and right

// Cannon1 Use A and Left bumper
// Cannon2 Use A and Right bumper
// Cannon3 Use B and Left bumper
// Cannon4 Use B and Right bumper
// Cannon5 Use X and Left bumper
// Cannon6 Use X and Rigth bumper

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

//import com.ctre.phoenix.motorcontrol.can.VictorSPX;
//import edu.wpi.first.wpilibj.drive.RobotDriveBase;


public class Robot extends TimedRobot 

{
  
  VictorSP frontLeft = new VictorSP(1);
  VictorSP rearLeft = new VictorSP(2);
  SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeft, rearLeft);
 
  VictorSP frontRight = new VictorSP(3);
  VictorSP rearRight = new VictorSP(4);
  SpeedControllerGroup m_right = new SpeedControllerGroup(frontRight, rearRight);
 
  DifferentialDrive m_robotDrive = new DifferentialDrive(m_left, m_right);
  
  private final XboxController controller  = new XboxController(0);

  //Check if the motor controllers are correct

  DoubleSolenoid Turret_Left;
  DoubleSolenoid Turret_Right;

  Solenoid Cannon1;
  Solenoid Cannon2;
  Solenoid Cannon3;
  Solenoid Cannon4;
  Solenoid Cannon5;
  Solenoid Cannon6;
  
  Boolean loadcannonA;
  Boolean loadcannonB;
  Boolean loadcannonX;
  

  public Robot() 

  {

  Turret_Left = new DoubleSolenoid(1,2);
  Turret_Right = new DoubleSolenoid(3,4);
  Cannon1 = new Solenoid(3);
  Cannon2 = new Solenoid(4);
  Cannon3 = new Solenoid(5);
  Cannon4 = new Solenoid(6);
  Cannon5 = new Solenoid(7);
  Cannon6 = new Solenoid(8);
  
  loadcannonA = false;
  loadcannonB = false;
  loadcannonX = false;

  //TO DO: Check if the .kleft and .kright correspond to turret movement
  //TO DO: Check if the port numbers correspond to the right cannon
  }


  @Override
  public void teleopPeriodic() 
  
  {
    //Tank Drive Control
    m_robotDrive.arcadeDrive(controller.getY(), controller.getX());

  
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
      loadcannonA = true;
      if(controller.getBumperPressed(Hand.kLeft)){
        Cannon1.set(true);
      }
      if(controller.getBumperPressed(Hand.kRight)){
        Cannon2.set(true);
      }
    }

    if(controller.getBButton()){
      loadcannonB = true;
      if(controller.getBumperPressed(Hand.kLeft)){
        Cannon3.set(true);
      }
      if(controller.getBumperPressed(Hand.kRight)){
        Cannon4.set(true);
      }
    }

    if(controller.getXButton()){
      loadcannonX = true;
      if(controller.getBumperPressed(Hand.kLeft)){
        Cannon4.set(true);
      }
      if(controller.getBumperPressed(Hand.kRight)){
        Cannon5.set(true);
      }
    }
  }
}  