#include<reg51.h>
unsigned int data_out,command=0x80,temp;
sfr lcd_data_pin=0x90;
sbit rs=P2^0;         
sbit rw=P2^1;        
sbit en=P2^2;       
unsigned char card_id[12];

void delay(unsigned int count)   
{
    int i,j;
     for(i=0;i<count;i++)
     for(j=0;j<1275;j++);
}

void lcd_command(unsigned char comm)   
{
     lcd_data_pin=comm;
     en=1;
     rs=0;
     rw=0;
     delay(1);
     en=0;
}

void lcd_data(unsigned char disp)  
{
     lcd_data_pin=disp;
     en=1;
     rs=1;
     rw=0;
     delay(1);
     en=0;
}

lcd_string(unsigned char *disp)    
{
     int x;
     for(x=0;disp[x]!=0;x++)
    {
         lcd_data(disp[x]);
    }
}
void lcd_ini()                 
{
    lcd_command(0x38);          
    delay(5);
    lcd_command(0x0F);        
    delay(5);
    lcd_command(0x80);
    delay(5);
} 

void recieve()    
{
    unsigned char k;
     for(k=0;k<12;k++)
     { 
         while(RI==0); 
          card_id[k]=SBUF;
          RI=0;
    }
}

void main()
{
    int l;
    TMOD=0x20;           
    TH1=0XFD;
    SCON=0x50;
    TR1=1;                
    lcd_ini();
    lcd_command(0x81);         
    lcd_string("UNIQUE CARD ID:");
    delay(200);
    while(1)
    {
         recieve();
         lcd_command(0xC1);       
         for(l=0;l<12;l++)
         { 
              lcd_data(card_id[l]);
          }
      }
}