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
    robot.avance();
    delay(2000);
    robot.arrete();
    delay(500);
    robot.recule();
    delay(1000);
    robot.arrete();
    delay(500);
    robot.son1();
    robot.tourneDroite();
    delay(1000);
    robot.arrete();
    delay(500);
    robot.tourneGauche();
    delay(1000);
    delay(500);
}
//---------------------------------------------//

