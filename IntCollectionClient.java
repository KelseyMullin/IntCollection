/*
 * This client tests all of the public methods of the IntCollection class
 */

import java.util.Scanner;

public class IntCollectionClient
{
	public static final int SENTINEL = 0;

	public static void main(String[] args)
	{
	   
		int value;
		Scanner keyboard = new Scanner(System.in);
		IntCollection P = new IntCollection();
    	IntCollection N = new IntCollection();
    	IntCollection L = new IntCollection();

    	//Take user input
		System.out.println("Enter an integer to be inserted or 0 to quit:");
		value = keyboard.nextInt();
		while(value != SENTINEL)
		{
			//Make P a collection of all positive inputs, N a collection
			//of negative all inputs, and L a collection of all ints
			//where the most recent input of that int was positive
			if (value > 0) 
			{
				P.insert(value);
				L.insert(value);
			}
			else 
			{
				N.insert(-value);
				L.omit(-value);
			}
			System.out.println("Enter next integer to be inserted or 0 to quit:");
			value = keyboard.nextInt();
		}
		
		//Print information about collections
		System.out.println("\nThe values in collection P are:");
		P.print();
		System.out.println("# of ints in P: " + P.getHowMany());
		System.out.println("\nThe values in collection N are:");
		N.print();
		System.out.println("# of ints in N: " + N.getHowMany());
		if (P.equals(N)) 
			System.out.println("\nP and N are equal.");
		else 
			System.out.println("\nP and N are NOT equal.");
		System.out.println("\nThe values in collection L are:");
		L.print();
		System.out.println("# of ints in L: " + L.getHowMany());
		IntCollection A = new IntCollection();
		A.copy(L);
		System.out.println("\nThe values in the copy of L are:");
		A.print();
		System.out.println("# of ints in A: " + A.getHowMany());
		if(A.equals(L))
			System.out.println("\nA and L are equal.\n");
		else 
			System.out.println("\nA and L are NOT equal.\n");
		for(int i = 1; i < 10; i++)
		{
			System.out.println("P contains " + i + ": " + P.belongs(i));
			System.out.println("N contains " + i + ": " + N.belongs(i));
			System.out.println("L contains " + i + ": " + L.belongs(i));
			System.out.println("A contains " + i + ": " + A.belongs(i));
			System.out.println();
		}
	}
}