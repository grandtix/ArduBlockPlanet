// declaration des librairies utilisées
#include <Servo.h>
#include <MiniRobot2.h>

// declaration des variables globales et initialisation
int i = 0 ;

MiniRobot2 miniRobot;

// declaration des methodes appelables
void calculi();

// methode d'initialisation du programme
//ne se lance qu'une fois au démarrage ou apres un reset
void setup()
{
miniRobot.initialise();
}

void loop()
{
calculi();
if (miniRobot.obstacle())
{
miniRobot.arrete();
miniRobot.son(i);
if (( ( i ) <= ( 3 ) ))
{
miniRobot.tourneGauche();
}
else
{
miniRobot.tourneDroite();
}
delay( ( i * 250 ) );
}
else
{
miniRobot.avance();
}
}

void calculi()
{
i = ( i + 1 ) ;
if (( ( i ) > ( 7 ) ))
{
i = 1 ;
}
}


