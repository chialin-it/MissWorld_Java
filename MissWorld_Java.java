// Full Name: Lee Chia Lin
// Full Time
// Tutorial Group: T03
// ClassNo: 58
// Declaration: This is my own work,
// 				I have not passed my program to my friends.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class MissWorld_Java
{
	//declare array of countries
	private final String [] Countries = {"Singapore", "Malaysia", "Thailand", 
										"Brazil", "North Korea", "Japan", "USA", "France"};
	
	public static String getChampion(ArrayList <MissWorld> alist)
	{
		double champScore = 0;
		String champCountry = "";
		
		for(MissWorld champ:alist)
		{
			//to get highest score
			if(champScore < champ.average())
			{
				champScore = champ.average();
				champCountry = champ.getCountry();
			}
		}	
		return champCountry;
	}
	
	public static String firstRunnerUp(ArrayList <MissWorld> alist)
	{
		double firstRUScore = 0;
		String firstRUCountry = "";
		
		for(MissWorld firstRU:alist)
		{
			//to get highest score, excluding Champion
			if(firstRU.getCountry()!=getChampion(alist))
			{
				if(firstRUScore < firstRU.average())
				{
					firstRUScore = firstRU.average();
					firstRUCountry = firstRU.getCountry();
				}
			}
		}	
		return firstRUCountry;
	}
	
	public static String secondRunnerUp(ArrayList <MissWorld> alist)
	{
		double secondRUScore = 0;
		String secondRUCountry = "";
		
		for(MissWorld secondRU:alist)
		{
			//to get highest score, excluding Champion and firstRunnerUp
			if(secondRU.getCountry()!=getChampion(alist) && secondRU.getCountry()!=firstRunnerUp(alist))
			{
				if(secondRUScore < secondRU.average())
				{
					secondRUScore = secondRU.average();
					secondRUCountry = secondRU.getCountry();
				}
			}
		}	
		return secondRUCountry;
	}
	
	public static void main (String [] args)
	{
		MissWorld_Java mainClass = new MissWorld_Java();
		
		//Print header
		System.out.printf("%-15s", "Countries");				 
		for(int i=1; i<=8; i++)
		{
			System.out.printf("%9s","J"+i);
		}
		System.out.printf("\t  %-10s%n", "Average");
		
		//List to store MissWorld objects
		ArrayList <MissWorld> mWorldList = new ArrayList <MissWorld>();
		
		//Print scores for countries
		for(int i=0; i<mainClass.Countries.length; i++)
		{
			System.out.printf("%-15s",mainClass.Countries[i]);
			MissWorld mWorld = new MissWorld(mainClass.Countries[i]);
			mWorld.printInfo();
			
			//add object to mWorldList
			mWorldList.add(mWorld);
		}
		
		//Print result for Winner, 1st and 2nd Runner Up
		System.out.println("\nThe result is: ");
		System.out.println("Winner: Miss " + getChampion(mWorldList));
		System.out.println("1st runner up: Miss " + firstRunnerUp(mWorldList));
		System.out.println("2nd runner up: Miss " + secondRunnerUp(mWorldList));
	}
}

class MissWorld
{
	//declare variables
	public static int SIZE = 8;
	private String country;
	private double [] score;
	
	//default constructor
	public MissWorld()
	{
		//do nothing
	}
	
	//other constructor
	public MissWorld(String country)
	{
		this.country = country;
		score = new double [SIZE];
		getScore();
	}
	
	//Accessor
	public String getCountry()
	{
		return country;
	}
	
	//to generate random scores
	private void getScore()
	{
		for(int i=0; i<SIZE; i++)
		{
			score[i] = Math.random()*9;
		}
	}
	
	//Other methods
	private int highest()
	{
		//declare and set default as index 0 value
		//values will be used for comparison
		double highestScore = score [0];
		int highestIndex = 0;
		
		//loop start at index 1 because index 0 no need to compare with itself
		for (int i=1; i<SIZE; i++)
		{
			if (highestScore < score [i])
			{
				highestScore = score [i];
				highestIndex = i;
			}
		}	
		return highestIndex;	
	}
	
	private int lowest()
	{
		//declare and set default as index 0 value
		//values will be used for comparison
		double lowestScore = score [0];
		int lowestIndex = 0;
		
		//loop start at index 1 because index 0 no need to compare with itself
		for (int i=1; i<SIZE; i++)
		{
			if (lowestScore > score [i])
			{
				lowestScore = score [i];
				lowestIndex = i;
			}
		}	
		return lowestIndex;
	}
	
	public double average()
	{
		double sumOfScore = 0;
		
		for(int i=0; i<SIZE; i++)
		{
			//excluding highest and lowest
			if(i!=highest() && i!=lowest())
			{
				sumOfScore += score[i];
			}
		}
		
		//excluding highest and lowest, therefore SIZE-2 
		return sumOfScore/(SIZE-2);
	}
	
	//print score and average
	public void printInfo()
	{
		for(int i=0; i<SIZE; i++)
		{
			System.out.printf("%9.1f",score[i]);
		}
		System.out.printf("\t  %-10.2f%n",average());
	}
}