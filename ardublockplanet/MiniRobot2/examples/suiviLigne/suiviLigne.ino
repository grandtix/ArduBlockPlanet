#include <Servo.h>
#include <MiniRobot2.h>

MiniRobot2 robot;

float gainMax = 4.0;
float vMoyMax = 30.0;

//---------------------------------------------//
void setup() {
  robot.initialise();
  
  // initialize the serial communication:
  Serial.begin(9600);  
}
//---------------------------------------------//


//---------------------------------------------//
void loop() {
    int valG = robot.oeilGauche();
    int valD = robot.oeilDroit();
    
    int diff = -(valD - valG);
    int diff0 = 0;
    diff = diff - diff0;
    
    float gain = gainMax * 0.01 * robot.boutonDroit();
    float vMoy = vMoyMax * 0.01 * robot.boutonGauche();
    
//    Serial.print("Vmoy : ");
//    Serial.println(vMoy);
//    Serial.print("Gain : ");
//    Serial.println(gain);
//    Serial.print("Diff : ");
//    Serial.println(diff);
//    Serial.println("");    
//    delay(500);
    
    robot.vitesseDroite(vMoy-gain*diff);
    robot.vitesseGauche(vMoy+gain*diff);
}
//---------------------------------------------//

