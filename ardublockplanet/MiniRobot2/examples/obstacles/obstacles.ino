#include <Servo.h>
#include <MiniRobot2.h>
MiniRobot2 robot;

//---------------------------------------------//
void setup() {
  robot.initialise();
  
  // initialize the serial communication:
  Serial.begin(9600);  
}
//---------------------------------------------//


//---------------------------------------------//
void loop() {
  if( robot.obstacle())
  {
    robot.arrete();
    robot.son(2);
    robot.recule();
    delay(1000);
    robot.arrete();
    delay(150);
    robot.son(1);
    robot.tourneDroite();
    delay(1000);
    robot.arrete();
    delay(150);
  } else
  {
    robot.avance();
  }
}
//---------------------------------------------//

