#include <Servo.h>
#define FTR A0     //El LDR esta conectador en el pin A0
int optico=7;
int abat = 13;
int ftr;
Servo bote;
char incomingByte;  // incoming data

void setup() {
  Serial.begin(9600); // initialization
 bote.attach(5);
 pinMode(7  ,INPUT);
 pinMode(FTR,INPUT);
 pinMode(13, OUTPUT);
}

void loop() {


//Serial.println(optico);

//incomingByte = Serial.read(); // read byte
optico=digitalRead(7);

if(optico ==0)
{
 

digitalWrite(abat,HIGH);


delay(2500);
ftr = analogRead(FTR);
Serial.println(ftr);
delay(1000);
if (ftr>=100 && ftr<=250)
{
  Serial.println("  plastico  ");
  //Serial.println(ftr);
 delay(1000);
}

if (ftr>=730 && ftr<=1000)
{
Serial.println("   lata ");
}
if (ftr>=580 && ftr<=700)
{
  Serial.println("  papel ");
}
bote.write(10);
delay(1000);
}
else 
{
bote.write(70);
digitalWrite(abat,LOW);
delay(2000);
}
}

