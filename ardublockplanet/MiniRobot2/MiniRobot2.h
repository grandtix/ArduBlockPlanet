#ifndef MiniRobot2_h
#define MiniRobot2_h

//#include <Arduino.h>
#include <Servo.h>


// reperage des pins
#define pinSG 9
#define pinSD 8

#define pinZeroG 0
#define pinZeroD 1

#define pinOeilG 2
#define pinOeilD 3

#define pinAntenneG 4
#define pinAntenneD 5

#define pinSortie 12
#define pinLedV 3
#define pinLedR 5

#define pinDistance 4
#define dObst 55







class MiniRobot2
{
  public:
    MiniRobot2();
    void initialise();
void action(int i);
void action(int i, int p); // a coder dans ardublock - pb affichage socket bottom

    void avance();
    void recule();
    void arrete();
    void tourneDroite();
    void tourneGauche();
    void pasADroite();
    void pasAGauche();
    bool antenneGauche();
    bool antenneDroite();
    int oeilGauche();
    int oeilDroit();
    int oeilGaucheJV();
    int oeilDroitJV();
    int zeroG();
    int zeroD();
    void allumeLed(int i);
    void eteindLed(int i);
bool capteurNumerique(int i);
int capteurAnalogique(int i);
    int boutonGauche();
    int boutonDroit();
    void vitesseGauche(int v);
    void vitesseDroite(int v);
    bool obstacle();
    int distance();
    void son(int son); 
  private:
    int _zeroG;
    int _zeroD;
    Servo _servoG;
    Servo _servoD;
    void _avanceGauche();
    void _avanceDroit();
    void _reculeGauche();
    void _reculeDroit();
    void _stopGauche();
    void _stopDroit();
};

#endif
