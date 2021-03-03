package com.sanyam.uiPackage;

public class NumberCheck {

	public static void main(String[] args) {
		int number = 17;
		for(int i=1; i <= number;i++)
		{
			if (((i %3) == 0) && (i % 5 == 0)) {
				if (i == number )
				{
					System.out.print("divby3anddivby5");
				}
				else
				{
				System.out.print("divby3anddivby5,");
				}
			}	
			else if (i % 5 == 0) {
				if (i == number )
				{
					System.out.print("divby5");
				}
				else
				{
				System.out.print("divby5,");
				}
			}

			else if((i %3) == 0)
			{
				if (i == number )
				{
					System.out.print("divby3");
				}
				else
				{
					System.out.print("divby3,");
				}
				
			}
			else{
				if (i == number )
				{
					System.out.print(i);
				}
				else
				{
					System.out.print(i + ",");
				}
			}
		}
	}
}
		


