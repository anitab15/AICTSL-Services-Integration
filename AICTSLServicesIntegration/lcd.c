#include<reg51.h>
sfr l_data=0x90;
sbit rs=P2^0;
sbit rw=P2^1;
sbit e=P2^2;
void cmd(unsigned int value);
void disp(unsigned int value);
void delay(unsigned int k);
void print(unsigned char ch[]);
void main()
{
 while(1)
 {
 cmd(0x38);
 delay(100);
 cmd(0x01);
 delay(100);
 cmd(0x0e);
 delay(100);
 cmd(0x06);
 delay(100);
 cmd(0x80);
 delay(100);
 print("WELCOME TO");
 cmd(0xc0);
 delay(100);
 print("ROBOTRONIX");
 delay(300);
 }
 }
 void print(unsigned char ch[])
 {
 int i=0;
 while(ch[i]!='\0')
 {
 disp(ch[i]);
 i++;
 }
 }
 void cmd(unsigned int value)
 {
  l_data=value;
  rs=0;
  rw=0;
  e=1;
  delay(1);
  e=0;
  }
  void disp(unsigned int value)
  {
   l_data=value;
   rs=1;
   rw=0;
   e=1;
   delay(1);
   e=0;			  
   }
   void delay(unsigned int k)
   {
    unsigned int i,j;
	for(i=0;i<=k;i++)
	for(j=0;j<1275;j++);
	}
