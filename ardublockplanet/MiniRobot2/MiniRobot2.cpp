/*
  MiniRobot2.cpp - Library for flashing MiniRobot2 code.
*/

#include <Arduino.h>
#include "MiniRobot2.h"

MiniRobot2::MiniRobot2()
{
  _zeroG = 90;
  _zeroD = 90;
}

void MiniRobot2::initialise()
{	
  // initialise les servos
  _servoG.attach(pinSG);
  _servoD.attach(pinSD);
  arrete();

  // initialise le piezzo
  pinMode(pinSortie, OUTPUT);
  digitalWrite(pinSortie, LOW);

  // initialise les LAIDES
  pinMode(pinLedR, OUTPUT);
  digitalWrite(pinLedR, LOW);
  pinMode(pinLedV, OUTPUT);
  digitalWrite(pinLedV, LOW);
  
  // attente touchage antenne avant depart pour reglages
  //while(!(antenneGauche() || antenneDroite()))
  //for(int i=0;i<50;i++)
  while(!obstacle())
  {
    _zeroG = map(analogRead(pinZeroG) , 0, 1023, 80 , 100);
    _zeroD = map(analogRead(pinZeroD) , 0, 1023, 80 , 100);
    arrete();
    //delay(100);
  }
  //}
  son(6);
  delay(500);
}	






void MiniRobot2::action(int i)
{
/*
switch i:

case 1:
break;
...

*/

}

void MiniRobot2::action(int i, int p)
{
/*
switch i:

case 1:
break;
...

*/

}


bool MiniRobot2::capteurNumerique(int i)
{
//digitalRead(i);
}

int MiniRobot2::capteurAnalogique(int i)
{
//analogRead(i);
}

void MiniRobot2::_avanceGauche() {
  _servoG.write(0);
}

void MiniRobot2::_avanceDroit() {
  _servoD.write(180);
}

void MiniRobot2::_reculeGauche() {
  _servoG.write(180);
}

void MiniRobot2::_reculeDroit() {
  _servoD.write(0);
}

void MiniRobot2::_stopGauche() {
  _servoG.write(_zeroG);
}

void MiniRobot2::_stopDroit() {
  _servoD.write(_zeroD);
}

void MiniRobot2::avance() {
    _avanceGauche();
    _avanceDroit();
}

void MiniRobot2::recule() {
    _reculeGauche();
    _reculeDroit();
}

void MiniRobot2::arrete() {
    _stopGauche();
    _stopDroit();
}

void MiniRobot2::tourneDroite() {
    _avanceGauche();
    _reculeDroit();
}

void MiniRobot2::tourneGauche() {
    _avanceDroit();
    _reculeGauche();
}

void MiniRobot2::pasADroite() {
    _stopGauche();
    _avanceDroit();
}

void MiniRobot2::pasAGauche() {
    _avanceGauche();
    _stopDroit();
}



bool MiniRobot2::antenneGauche() {
  return(analogRead(pinAntenneG) > 512);
}

bool MiniRobot2::antenneDroite() {
  return(analogRead(pinAntenneD) > 512);
}

int MiniRobot2::oeilGauche() {
  return(map(analogRead(pinOeilG), 0 , 1023, 0, 100));
}

int MiniRobot2::oeilDroit() {
  return(map(analogRead(pinOeilD), 0 , 1023, 0, 100));
}


int MiniRobot2::oeilGaucheJV() {
  return(map(analogRead(pinAntenneG), 0 , 1023, 0, 100));
}

int MiniRobot2::oeilDroitJV() {
  return(map(analogRead(pinAntenneD), 0 , 1023, 0, 100));
}


int MiniRobot2::zeroG() {
  //_zeroG = map(analogRead(pinZeroG) , 0, 1023, 80 , 100);
  return(_zeroG);
}

int MiniRobot2::zeroD() {
  //_zeroD = map(analogRead(pinZeroD) , 0, 1023, 80 , 100);
  return(_zeroD);
}

void MiniRobot2::allumeLed(int i) {
	digitalWrite(i, HIGH);
}

void MiniRobot2::eteindLed(int i) {
	digitalWrite(i, LOW);
}


int MiniRobot2::boutonGauche() {
	return(map(analogRead(pinZeroG) , 0, 1023, 0 , 100));
}
	
int MiniRobot2::boutonDroit() {
	return(map(analogRead(pinZeroD) , 0, 1023, 0 , 100));
}

void MiniRobot2::vitesseGauche(int v) {
	int vG = constrain(zeroG() - v, 0, 180);
	_servoG.write(vG);
}

void MiniRobot2::vitesseDroite(int v) {
	int vD = constrain(zeroD() + v, 0, 180);
	_servoD.write(vD);
}

bool MiniRobot2::obstacle() {
   return(distance() < dObst);
}

int MiniRobot2::distance() {
	return(100 - map(analogRead(pinDistance), 0 , 1023, 0, 100));
}


	
void MiniRobot2::son(int son) {
switch (son) {

case 1:
  for(int i=500; i < 1400;i=i+2) {
    digitalWrite(pinSortie, HIGH);
    delayMicroseconds(i);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(i);
  }
break;
case 2:
  for(int i=1400; i > 500;i=i-2) {
    digitalWrite(pinSortie, HIGH);
    delayMicroseconds(i);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(i);
  }
break;
case 3:
  for(int i=1400; i > 500;i=i-2) {
    digitalWrite(pinSortie, HIGH);
    delayMicroseconds(i);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(i);
  }
break;
case 4:
  for(int i=250; i < 400;i++) {
    digitalWrite(pinSortie, HIGH);
    delayMicroseconds(i*2);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(i*2);
  }
  for(int i=700; i > 250;i--) {
    digitalWrite(pinSortie, HIGH);
    delayMicroseconds(i);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(i);
  }
break;
case 5:
  for(int i=250; i < 400;i++) {
    digitalWrite(pinSortie, HIGH);
    delayMicroseconds(i*2);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(i*2);
  }
  for(int i=700; i > 250;i--) {
    digitalWrite(pinSortie, HIGH);
    delayMicroseconds(i);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(i);
  }
break;
case 6:
  for(int i=0; i < 180; i++) {
    digitalWrite(pinSortie, HIGH);
    delayMicroseconds(250);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(250);
  }
  
  for(int i=250; i < 600; i=i+2) {
    digitalWrite(pinSortie, HIGH);
    delayMicroseconds(i);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(i);
  }
  for(int i=600; i > 150; i=i-2) {
    digitalWrite(pinSortie, HIGH);
    delayMicroseconds(i);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(i);
  }  
break;
case 7:
  for(int i=1; i < 1000;i=i+1) {
    digitalWrite(pinSortie, HIGH);
    delayMicroseconds(2000);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(2000);
    digitalWrite(pinSortie, HIGH);
  }
  for(int i=1; i < 1000;i=i+1) {
    delayMicroseconds(1000);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(1000);
    digitalWrite(pinSortie, HIGH);
  }
  for(int i=1; i < 1000;i=i+1) {
    delayMicroseconds(700);
    digitalWrite(pinSortie, LOW);
    delayMicroseconds(700);
  }
}
}  

