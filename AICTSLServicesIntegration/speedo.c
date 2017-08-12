#include<reg51.h>
sbit sw=P1^0;
void main()
{
	unsigned int i=0;
	sw=0;
	while(1)
	{
		if(sw==1)
		{
			i++;
		while(sw==1);
		}
		P2=i*30;
	}
}