import java.util.*;
import java.lang.*;
// Vehicle and its inherited classes.
public abstract class Vehicle 
{
	// initialising variables
	// defining a two dimension array to give the floor as well as the spot number of the parking slot
	public static int parkingSpots[][] = new int[8][100]; 
	public static String licensePlate; 
	public static int spotsNeeded;
	public static int key;
	static Map <Integer, String> lsno = new HashMap<Integer, String>();
	static Map <Integer, Integer> flag = new HashMap<Integer, Integer>();
	static Scanner sc = new Scanner(System.in);
	public static int[] OneEmptySpot()
	{
		int count = 0;
		int floorslotno[]= new int[2];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<100; j++)
			{
				if(parkingSpots[i][j]==0)
				{
					floorslotno[0]= i;
					floorslotno[1]= j;
					count=1;
					break;
				}
			}
			if(count==1)
			break;
		}
		return floorslotno;
	}
	public static int[] TwoEmptySpot()
	{
		int count = 0;
		int floorslotno[]= new int[2];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<99; j++)
			{
				if((parkingSpots[i][j]==0)&&(parkingSpots[i][j+1]==0))
				{
					floorslotno[0]= i;
					floorslotno[1]= j;
					count=1;
					break;
				}
			}
			if(count==1)
			break;
		}
		return floorslotno;
	}
	public static int[] FourEmptySpot()
	{
		int count = 0;
		int floorslotno[]= new int[2];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<97; j++)
			{
				if((parkingSpots[i][j]==0) && (parkingSpots[i][j+1]==0) && (parkingSpots[i][j+2]==0) && (parkingSpots[i][j+3]==0))
				{
					floorslotno[0]= i;
					floorslotno[1]= j;
					count=1;
					break;
				}
			}
			if(count==1)
			break;
		}
		return floorslotno;
	}
	// Park vehicle in this spot (among others, potentially)
	public static int addVehicleToSpot(int parkingSpots[][], int spotsNeeded) 
	{
		int uniqueToken;
			if(spotsNeeded==1)
			{
				int a[]= new int[2];
				a= OneEmptySpot();
				int f= a[0];
				int sn= a[1];
				parkingSpots[f][sn]=1;
				uniqueToken = ((f+1)*1000) + sn +1;
			}
			else if(spotsNeeded==2)
			{
				int a[]= new int[2];
				a= TwoEmptySpot();
				int f= a[0];
				int sn= a[1];
				parkingSpots[f][sn]=1;
				parkingSpots[f][sn+1]=1;
				uniqueToken = ((f+1)*1000) + sn +1;
			}
			else
			{
				int a[]= new int[2];
				a= FourEmptySpot();
				int f= a[0];
				int sn= a[1];
				parkingSpots[f][sn]=1;
				parkingSpots[f][sn+1]=1;
				parkingSpots[f][sn+2]=1;
				parkingSpots[f][sn+3]=1;
				uniqueToken = ((f+1)*1000) + sn +1;
			}
		return uniqueToken;
	} 
	public static void main(String args[])
	{
		char ch;
		int choice=0;
		int exit = 0;
		int uk=0;
		int hour,hour1;
		String date1;
		Date d1 = new Date();
		while(exit!=1)
		{
			System.out.println("WELCOME TO PARSH \n1.Add a vehicle\n2.Remove a vehicle\n3.Display\n4.Exit");
			choice = sc.nextInt();
			switch(choice)
			{
				case 1:	System.out.print("Enter the type of vehicle (M/C/B) :");
						ch = sc.next().charAt(0);
						Date dot1 = new Date();
						d1=dot1;
						switch (ch)
						{
							case 'M' :	spotsNeeded = 1;
										key = addVehicleToSpot(parkingSpots , spotsNeeded);
										System.out.print("Enter the License Plate Number : ");
										licensePlate = sc.next();
										lsno.put(key,licensePlate);
										flag.put(key,1);
										System.out.println("Your Unique Code: "+key);
										break;
							case 'C' :	spotsNeeded = 2;
										key = addVehicleToSpot(parkingSpots , spotsNeeded);	
										System.out.print("Enter the License Plate Number : ");
										licensePlate = sc.next();
										lsno.put(key,licensePlate);
										flag.put(key,2);
										System.out.println("Your Unique Code: "+key);
										break;
							case 'B' :	spotsNeeded = 4;
										key = addVehicleToSpot(parkingSpots , spotsNeeded);
										System.out.print("Enter the License Plate Number : ");
										licensePlate = sc.next();
										lsno.put(key,licensePlate);
										flag.put(key,4);
										System.out.println("Your Unique Code: "+key);
										break;
						}
						break;
					case 2: System.out.print("Enter your unique key: ");
						uk = sc.nextInt();
						int floor, slot, type;
						double bill;
						type = flag.get(uk);
						slot = uk%1000;
						floor = uk/1000;
						date1 = d1.toString();
						hour = Integer.parseInt(date1.substring(11, 13));
						Date d2 = new Date();
						String date2 = d2.toString();
						hour1 = Integer.parseInt(date2.substring(11, 13));
						switch(type)
						{
							case 1: parkingSpots[floor-1][slot-1]=0;
									if((hour1-hour)<4)
										bill= 30;
									else
										bill= 30 + (hour1-hour-3)*5;        
									System.out.println(" _____________________________ ");
									System.out.println("|        PARKING TICKET       |");
									System.out.println("|       ENTRY Time & Date     |");
									System.out.println("|"+date1+" |");
									System.out.println("|                             |");
									System.out.println("| Fee Paid:"+bill+"               |");
									System.out.println("|                             |");
									System.out.println("|LicensePlate:"+lsno.get(uk)+"      |");
									System.out.println("| Reg:"+uk+"                    |");
									System.out.println("| EXIT Time & Date            |");
									System.out.println("|"+d2+" |");
									System.out.println("|_____________________________|");
									lsno.remove(uk);
									flag.remove(uk);
									break;
							case 2: parkingSpots[floor-1][slot-1]=0;
									parkingSpots[floor-1][slot]=0;
									if((hour1-hour)<4)
										bill= 50;
									else
										bill= 50 + (hour1-hour-3)*5;
									System.out.println(" _____________________________ ");
									System.out.println("|        PARKING TICKET       |");
									System.out.println("|       ENTRY Time & Date     |");
									System.out.println("|"+date1+" |");
									System.out.println("|                             |");
									System.out.println("| Fee Paid:"+bill+"               |");
									System.out.println("|                             |");
									System.out.println("|LicensePlate:"+lsno.get(uk)+"      |");
									System.out.println("| Reg:"+uk+"                    |");
									System.out.println("| EXIT Time & Date            |");
									System.out.println("|"+d2+" |");
									System.out.println("|_____________________________|");
									lsno.remove(uk);
									flag.remove(uk);
									break; 
							case 4: parkingSpots[floor-1][slot-1]=0;
									parkingSpots[floor-1][slot]=0;
									parkingSpots[floor-1][slot+1]=0;
									parkingSpots[floor-1][slot+2]=0;
									if((hour1-hour)<4)
										bill= 80;
									else
										bill= 80 + (hour1-hour-3)*10;
									System.out.println(" _____________________________ ");
									System.out.println("|        PARKING TICKET       |");
									System.out.println("|       ENTRY Time & Date     |");
									System.out.println("|"+date1+" |");
									System.out.println("|                             |");
									System.out.println("| Fee Paid:"+bill+"               |");
									System.out.println("|                             |");
									System.out.println("|LicensePlate:"+lsno.get(uk)+"      |");
									System.out.println("| Reg:"+uk+"                    |");
									System.out.println("| EXIT Time & Date            |");
									System.out.println("|"+d2+" |");
									System.out.println("|_____________________________|");
									lsno.remove(uk);
									flag.remove(uk);
									break;       
						}
						break;
					case 3: if(lsno.isEmpty())
								System.out.println("Parking Lot is empty!");
							else
							{
								System.out.println("      Floor      Slot Number      License Plate     ");
								for(Map.Entry m:lsno.entrySet())
								{
									System.out.println("        "+((int)m.getKey())/1000+"            "+((int)m.getKey())%1000+"             "+m.getValue()); 
								}
							}
							break;
					case 4: exit =1;
							break; 
			}
		}
	}
}