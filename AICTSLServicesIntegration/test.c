#include<reg51.h>
sfr l_data=0x90;
sbit rs=P2^2;
sbit rw=P2^1;
sbit e=P2^0;
sbit ledr=P2^3;
sbit ledg=P2^4;
sbit relay=P2^7;
unsigned char dist,d[4]={48,48,48,48};
unsigned char rdata1[12];
unsigned char rdata2[29];
void cmd(unsigned int value);
void disp(unsigned int value);
void delay(unsigned int k);
void print(unsigned char ch[]);
void init();
void srinitialize();
void start();
void puts(char* p);
void putc(unsigned char chr);
void recieve1();
void recieve2();
void main()
{
	int i=0,a=0,b=0,c=0,e=0,k=0,j=0;
	char g='0';
	relay=0;
	dist=0;
	init();
	start();
	srinitialize();
	delay(4000);
	cmd(0x01);
	delay(2);
	cmd(0x80);
	delay(2);
	print("AT COMMANDS");
	delay(5000);
	puts("AT+CIPSHUT\r");
	delay(100);
	puts("AT+CIPMUX=0\r");
	delay(100);
	puts("AT+CGATT=1\r");
	delay(100);
	puts("AT+CSTT=\"SMARTNET\",\"\",\"\"\r");
	delay(100);
	puts("AT+CIICR\r");
	delay(1000);
	puts("AT+CIFSR\r");
	delay(100);
	puts("AT+SAPBR=1,1\r");
	delay(1000);
	puts("AT+HTTPINIT\r");
	delay(100);
 while(1)
 {
	 relay=0;
	 ledr=ledg=0;
	 cmd(0x01);
	 delay(10);
	 cmd(0x80);
	 delay(10);
	 for(j=0;j<12;j++)
	 rdata1[j]=0;
	 for(k=0;k<29;k++)
	 rdata2[k]=0;
	 dist=P0;
	 a=dist/1000;
	 dist=dist%1000;
	 b=dist/100;
	 dist=dist%100;
	 c=dist/10;
	 dist=dist%10;
	 e=dist;
	 d[0]=a+48;
	 d[1]=b+48;
	 d[2]=c+48;
	 d[3]=e+48;
	 print("SCAN YOUR CARD");
   recieve1();
	 puts("AT+HTTPPARA=\"URL\",\"http://techachiever.com/ibus.php?taid=");
	 puts(rdata1);
	 puts("&frm=100&dist=");
	 putc(g);
	 puts(d);
	 puts("\"\r");
	 delay(100);
	 puts("AT+HTTPACTION=0\r");
	 cmd(0x01);
	 delay(10);
	 cmd(0x80);
	 delay(10);
	 print("CARD SCANNED");
	 delay(2000);
	 relay=1;
	 puts("AT+HTTPREAD\r");
	 recieve2();
	 if(rdata2[28]==52)
	 {
		 ledr=1;
		 cmd(0x01);
		 delay(10);
		 cmd(0x80);
		 delay(10);
		 print("INVALID CARD");
	 }
	 else if(rdata2[28]==49)
	 {
		 ledr=1;
		 cmd(0x01);
		 delay(10);
		 cmd(0x80);
		 delay(10);
		 print("WELCOME TO");
		 cmd(0xC0);
		 delay(10);
		 print("TELE-RICKSHAW");
	 }
	 else if(rdata2[28]==50)
	 {
		 ledr=1;
		 cmd(0x01);
		 delay(10);
		 cmd(0x80);
		 delay(10);
		 print("YOU DON'T HAVE");
		 delay(10);
		 cmd(0xc0);
		 delay(10);
		 print("ENOUGH BALANCE");
	 }
	 else if(rdata2[28]==51)
	 {
		 ledr=1;
		 cmd(0x01);
		 delay(10);
		 cmd(0x80);
		 delay(10);
		 print("THANKS");
		 delay(10);
		 cmd(0xc0);
		 delay(10);
		 print("DISTANCE=");
		 disp(a+48);
		 disp(b+48);
		 disp(c+48);
		 disp(e+48);
		 print(" M");
	 }
	 else
	 {
		 cmd(0x01);
		 delay(10);
		 cmd(0x80);
		 delay(10);
		 print("SERVER ERROR");
	 }
	 delay(500);
 }
 }
void recieve1()    
{
    unsigned char k;
     for(k=0;k<12;k++)
     { 
         while(RI==0); 
          rdata1[k]=SBUF;
          RI=0;
    }
}
void recieve2()    
{
    unsigned char k;
     for(k=0;k<30;k++)
     { 
         while(RI==0); 
          rdata2[k]=SBUF;
          RI=0;
    }
}
void puts(char* p)
{
 char *temp = p;         
 while(*temp != 0x00)
 {
  putc(*temp);  
  temp++;
 } 
}
void putc(unsigned char chr)
{
 SBUF = chr;
 while(TI==0);          
 TI=0;                   
}
void start()
{
	cmd(0x80);
 delay(10);
 print("GETTING STARTED");
 cmd(0xc0);
 delay(10);
 print("GSM TESTING....");
 delay(30);
}
void init()
{
	cmd(0x38);
 delay(10);
 cmd(0x01);
 delay(10);
 cmd(0x0e);
 delay(10);
 cmd(0x06);
 delay(10);
}
void srinitialize()
{
 SCON  = 0x50;   
 TMOD  = 0x20;  
 TH1   = 0xFD;  
 TR1   = 1;     
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
