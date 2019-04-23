package animalGUI;

import java.util.*;

import javax.swing.ImageIcon;


/**
 * Animal is a abstract class that define the basic of all animals
 * @author CWH
 *
 */
abstract class AnimalBase {
	
	protected int xPos;
	protected int yPos;	
	protected int tempxPos;
	protected int tempyPos;
	protected ImageIcon icon;
	protected boolean alive = true;
	
}
interface Animal {	
	
	/**
	 * Get the name of the animal
	 * @return name of the animal
	 */
	public String name();
	/**
	 * To set the coordinate of the animals
	 * @param x the row of the animal in the cells map
	 * @param y the column of the animal in the cells map
	 */
	public void setLocaions( int x, int y);
	/**
	 * To store the temporary location of the animal after its move
	 * @param x the row of the animal after its move
	 * @param y the column of the animal after its move
	 */
	public void setTempLocations( int x, int y);
	/**
	 * Get the row of the animal in the cells map
	 * @return the row of the animal in the cells map
	 */
	public int returnXPos();
	/**
	 * Get the column of the animal in the cells map
	 * @return the column of the animal in the cells map
	 */
	public int returnYPos();
	/**
	 * Method that perform a move action for different animals
	 */
	public void move();
	/**
	 * Method that perform an attack action by animal which calls it
	 * @param enemyType The animal type of the enemy in the battle
	 * @return the result of the battle
	 */
	public boolean attack(Animal enemyType);
	/**
	 * Get the row of the animal in the cells map after its move
	 * @return the row of the animal in the cells map after its move
	 */
	public int returnTempXPos();
	/**
	 * Get the column of the animal in the cells map after its move
	 * @return the column of the animal in the cells map after its move
	 */
	public int returnTempYPos();
	/**
	 * To set whether the animal is dead or not
	 * @param status The alive status for this method
	 */
	public void setStatus(boolean status);
	/**
	 * Get the status of the animal
	 * @return the alive status of the animal
	 */
	public boolean returnStatus();
	
	
	/**
	 * get the move options of the animal on the map
	 * @return the move options
	 */
	public ArrayList<int[]> moveOptions();
	
	/**
	 * Get the type of the animal
	 * @return the type of the animal
	 */
	public Animal returnType();
	
	/**
	 * Set the personal preference icon for the animal
	 * @param the new icon user choose
	 */
	public void setIcon(ImageIcon icon);
	/**
	 * Get the new icon of the animal
	 * @return the new icon
	 */
	public ImageIcon returnIcon();
}


class Canine extends AnimalBase implements Animal {	
	
	public String name() {
		return "Canine";
	}
	
	public void setLocaions( int x, int y) {
		xPos = x;
		yPos = y;	
	}
	
	public int returnXPos() {
		return xPos;
	}
	
	public int returnYPos() {
		return yPos;
	}
	
	
	public void setTempLocations(int x, int y) {
		// TODO Auto-generated method stub
		tempxPos = x;
		tempyPos = y;
	}
	
	public int returnTempXPos() {
		return tempxPos;
	}
	
	public int returnTempYPos() {
		return tempyPos;
	}
	
	public void setStatus(boolean status) {
		alive = status;
	}
	
	public boolean returnStatus() {
		return alive;
	}
	
	public ArrayList<int[]> moveOptions() {
		ArrayList<int[]> moveOptions = new ArrayList<int[]>();		
		moveOptions.add(new int[] {0,1});
		moveOptions.add(new int[] {-1,0});
		moveOptions.add(new int[] {0,-1});
		moveOptions.add(new int[] {1,0});
		moveOptions.add(new int[] {0,2});
		moveOptions.add(new int[] {-2,0});
		moveOptions.add(new int[] {0,-2});
		moveOptions.add(new int[] {2,0});
		
		return moveOptions;
		
	}
	
	public Animal returnType() {
		return this;
	}
	public void move() {
		Random randomGen = new Random();System.out.println("Canine start move()");
		int theRandomNumber = randomGen.nextInt(5) - 2;		
		int theRandomNumber1 = randomGen.nextInt(3) - 1;
		boolean moved = false;
		while(moved == false) {
			System.out.println("Canine start while loop");
			if (Math.random() < 0.5) {
				if (Math.random() < 0.5) {					
					while(theRandomNumber1 == 0) {
						System.out.println("Canine start while loop rand");
						theRandomNumber1 = randomGen.nextInt(3) - 1;
					}
					if ( xPos + theRandomNumber1 >= 0 && xPos + theRandomNumber1 < 15 ) {				
						this.setTempLocations((xPos + theRandomNumber1), yPos);		
						moved = true;
					}			
					else {
						theRandomNumber1 = randomGen.nextInt(3) - 1;
					}
				}
				else {					
					while(theRandomNumber1 == 0) {
						System.out.println("Canine start while loop1");
						theRandomNumber1 = randomGen.nextInt(3) - 1;
					}
					if ( yPos + theRandomNumber1 >= 0 && yPos + theRandomNumber1 < 15 ) {				
						this.setTempLocations(xPos, (yPos + theRandomNumber1));		
						moved = true;
					}				
					else {
						theRandomNumber1 = randomGen.nextInt(3) - 1;
					}
				}
			}
			else {
				
				if (Math.random() < 0.5) {					
					while(theRandomNumber == 0) {
						System.out.println("Canine start while loop2");
						theRandomNumber = randomGen.nextInt(5) - 2;
					}
					if ( xPos + theRandomNumber >= 0 && xPos + theRandomNumber < 15 ) {				
						this.setTempLocations((xPos + theRandomNumber), yPos);		
						moved = true;
					}		
					else {
						theRandomNumber = randomGen.nextInt(5) - 2;
					}
				}
				else {					
					while(theRandomNumber == 0) {
						System.out.println("Canine start while loop3");
						theRandomNumber = randomGen.nextInt(5) - 2;
					}
					if ( yPos + theRandomNumber >= 0 && yPos + theRandomNumber < 15 ) {				
						this.setTempLocations(xPos, (yPos + theRandomNumber));		
						moved = true;
					}		
					else {
						System.out.println("Canine start while loop4");
						theRandomNumber = randomGen.nextInt(5) - 2;
					}
				}
			}
		}
	}
			
			
	
	public boolean attack(Animal enemyType) {
		// TODO Auto-generated method stub		
		
		if (enemyType instanceof Feline) {
			if(Math.random() < 0.5) {			
				return true;
			}			
			else {							
				return false;
			}
		}
		else if( enemyType instanceof Turtle ) {
			if (Math.random() < 0.2 ) {				
				return true;
			}
			else {								
				return false;
			}
				
		}
		return false;
	}

	@Override
	public void setIcon(ImageIcon buttonIcon) {
		// TODO Auto-generated method stub
		icon = buttonIcon;
	}

	@Override
	public ImageIcon returnIcon() {
		// TODO Auto-generated method stub
		return icon;
	}
	
}

class Feline extends AnimalBase implements Animal  {
	public void setIcon(ImageIcon buttonIcon) {
		// TODO Auto-generated method stub
		icon = buttonIcon;
	}

	@Override
	public ImageIcon returnIcon() {
		// TODO Auto-generated method stub
		return icon;
	}
	public String name() {
		return "Feline";
	}
	
	public void setLocaions( int x, int y) {
		xPos = x;
		yPos = y;	
	}
	
	public int returnXPos() {
		return xPos;
	}
	
	public int returnYPos() {
		return yPos;
	}

	public void setTempLocations(int x, int y) {
		// TODO Auto-generated method stub
		tempxPos = x;
		tempyPos = y;
	}
	
	public int returnTempXPos() {
		return tempxPos;
	}
	
	public int returnTempYPos() {
		return tempyPos;
	}
	
	public void setStatus(boolean status) {
		alive = status;
	}
	
	public boolean returnStatus() {
		return alive;
	}
	
	public ArrayList<int[]> moveOptions() {
		ArrayList<int[]> moveOptions = new ArrayList<int[]>();		
		moveOptions.add(new int[] {0,1});
		moveOptions.add(new int[] {-1,0});
		moveOptions.add(new int[] {0,-1});
		moveOptions.add(new int[] {1,0});
		moveOptions.add(new int[] {-1,1});
		moveOptions.add(new int[] {-1,-1});
		moveOptions.add(new int[] {1,1});
		moveOptions.add(new int[] {1,-1});
		
		return moveOptions;
		
	}
	
	public Animal returnType() {
		return this;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		Random randomGen = new Random();
		System.out.println("Feline start move");
		boolean moved = false;
		int theRandomNumber = randomGen.nextInt(3) - 1;
		int theRandomNumber1 = randomGen.nextInt(3) - 1;
		while (moved == false) {
			System.out.println("Feline start while loop");
			while(theRandomNumber == 0 && theRandomNumber1 == 0 ) {
				theRandomNumber = randomGen.nextInt(3) - 1;
				theRandomNumber1 = randomGen.nextInt(3) - 1;
			}
			if ( xPos + theRandomNumber >= 0 && xPos + theRandomNumber < 15 && yPos + theRandomNumber1 >= 0 && yPos + theRandomNumber1 < 15 ) {					
					this.setTempLocations((xPos + theRandomNumber ), (yPos + theRandomNumber1 ));
					moved = true;
			}			
			else {
				theRandomNumber = randomGen.nextInt(3) - 1;
				theRandomNumber1 = randomGen.nextInt(3) - 1;
				System.out.println("Feline start while loop1");

			}
				
		}		
		
	}
	
	public boolean attack(Animal enemyType) {
		// TODO Auto-generated method stub
		
		
		if (enemyType instanceof Canine) {			
			return true;
		}
		else if( enemyType instanceof Turtle ) {
			if (Math.random() < 0.2 ) {				
				return true;
			}
			else {								
				return false;
			}				
		}
		
		return false;
			
	}
}

class Hippo extends AnimalBase implements Animal  {
	public void setIcon(ImageIcon buttonIcon) {
		// TODO Auto-generated method stub
		icon = buttonIcon;
	}

	@Override
	public ImageIcon returnIcon() {
		// TODO Auto-generated method stub
		return icon;
	}
	public String name() {
		return "Hippo";
	}
	
	public void setLocaions( int x, int y) {
		xPos = x;
		yPos = y;	
	}
	
	public int returnXPos() {
		return xPos;
	}
	
	public int returnYPos() {
		return yPos;
	}

	public void setTempLocations(int x, int y) {
		// TODO Auto-generated method stub
		tempxPos = x;
		tempyPos = y;
	}
	
	public int returnTempXPos() {
		return tempxPos;
	}
	
	public int returnTempYPos() {
		return tempyPos;
	}
	
	public void setStatus(boolean status) {
		alive = status;
	}
	
	public boolean returnStatus() {
		return alive;
	}
	
	
	public ArrayList<int[]> moveOptions() {
		ArrayList<int[]> moveOptions = new ArrayList<int[]>();		
		moveOptions.add(new int[] {0,1});
		moveOptions.add(new int[] {-1,0});
		moveOptions.add(new int[] {0,-1});
		moveOptions.add(new int[] {1,0});
		
		
		return moveOptions;
		
	}
	
	public Animal returnType() {
		return this;
	}
	public void move() {
		// TODO Auto-generated method stub
		
		Random randomGen = new Random();		
		int theRandomNumber1 = randomGen.nextInt(3) - 1;
		boolean moved = false;	
		while ( moved == false) {
			if (Math.random() < 0.5) {				
				while(theRandomNumber1 == 0) {
					theRandomNumber1 = randomGen.nextInt(3) - 1;
				}
				if ( xPos + theRandomNumber1 >= 0 && xPos + theRandomNumber1 < 15 ) {				
					this.setTempLocations((xPos + theRandomNumber1), yPos);		
					moved = true;
				}	
				else {
					theRandomNumber1 = randomGen.nextInt(3) - 1;
				}
			}
			else {				
				while(theRandomNumber1 == 0) {
					theRandomNumber1 = randomGen.nextInt(3) - 1;
				}
				if ( yPos + theRandomNumber1 >= 0 && yPos + theRandomNumber1 < 15 ) {				
					this.setTempLocations(xPos, (yPos + theRandomNumber1));		
					moved = true;
				}		
				else {
					theRandomNumber1 = randomGen.nextInt(3) - 1;
				}
			}
		}
		
	}

	
	public boolean attack(Animal enemyType) {
		// TODO Auto-generated method stub		
		if( enemyType instanceof Turtle ) {
			if (Math.random() < 0.2 ) {				
				return true;
			}
			else {								
				return false;
			}
				
		}
		return false;
	}
}

class Turtle extends AnimalBase implements Animal  {	
	public void setIcon(ImageIcon buttonIcon) {
		// TODO Auto-generated method stub
		icon = buttonIcon;
	}

	@Override
	public ImageIcon returnIcon() {
		// TODO Auto-generated method stub
		return icon;
	}
	public String name() {
		return "Turtle";
	}
	
	public void setLocaions( int x, int y) {
		xPos = x;
		yPos = y;	
	}
	
	public int returnXPos() {
		return xPos;
	}
	
	public int returnYPos() {
		return yPos;
	}

	public void setTempLocations(int x, int y) {
		// TODO Auto-generated method stub
		tempxPos = x;
		tempyPos = y;
	}
	
	public int returnTempXPos() {
		return tempxPos;
	}
	
	public int returnTempYPos() {
		return tempyPos;
	}
	
	public void setStatus(boolean status) {
		alive = status;
	}
	
	public boolean returnStatus() {
		return alive;
	}
	
	public Animal returnType() {
		return this;
	}
	public ArrayList<int[]> moveOptions() {
		ArrayList<int[]> moveOptions = new ArrayList<int[]>();		
		moveOptions.add(new int[] {0,1});
		moveOptions.add(new int[] {-1,0});
		moveOptions.add(new int[] {0,-1});
		moveOptions.add(new int[] {1,0});
		
		
		return moveOptions;
		
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		Random randomGen = new Random();
		int theRandomNumber1 = randomGen.nextInt(3) - 1;
		System.out.println("Turtle start move");

		boolean moved = false;
		while(moved == false) {
			
				if (Math.random() < 0.5) {
					
						while(theRandomNumber1 == 0) {
							theRandomNumber1 = randomGen.nextInt(3) - 1;
							System.out.println("Turtle start while loop");
						}
						if ( xPos + theRandomNumber1 >= 0 && xPos + theRandomNumber1 < 15 ) {				
							this.setTempLocations((xPos + theRandomNumber1), yPos);		
							moved = true;
						}
						else {
							theRandomNumber1 = randomGen.nextInt(3) - 1;
						}
					
				}
				else {
					
						while(theRandomNumber1 == 0) {
							theRandomNumber1 = randomGen.nextInt(3) - 1;
							System.out.println("Turtle start while loop");

						}
						if ( yPos + theRandomNumber1 >= 0 && yPos + theRandomNumber1 < 15 ) {				
							this.setTempLocations(xPos, (yPos + theRandomNumber1));		
							moved = true;
						}
						else {
							theRandomNumber1 = randomGen.nextInt(3) - 1;
						}
					
				}
			}
			
			
	}
	

	
	public boolean attack(Animal enemyType) {
		// TODO Auto-generated method stub		
		if (Math.random() < 0.5 ) {
			return true;
		}
		else {				
			return false;
		}
	}
}

class Dog extends Canine {
	
	public String name() {
		return "Dog";
	}
	
	public void setLocaions( int x, int y) {
		xPos = x;
		yPos = y;	
	}
	
	public int returnXPos() {
		return xPos;
	}
	
	public int returnYPos() {
		return yPos;
	}
}

class Fox extends Canine {
	
	public String name() {
		return "Fox";
	}
	
	public void setLocaions( int x, int y) {
		xPos = x;
		yPos = y;	
	}
	
	public int returnXPos() {
		return xPos;
	}
	
	public int returnYPos() {
		return yPos;
	}
	
	public boolean attack(Animal enemyType) {
		// TODO Auto-generated method stub
		boolean success;
		
		if (enemyType instanceof Cat) {			
			return true;
		}
		else 
			success = super.attack(enemyType);
		
		return success;
			
	}
}

class Wolf extends Canine {
	
	public String name() {
		return "Wolf";
	}
	
	public void setLocaions( int x, int y) {
		xPos = x;
		yPos = y;	
	}
	
	public int returnXPos() {
		return xPos;
	}
	
	public int returnYPos() {
		return yPos;
	}
}

class Cat extends Feline {	
	
	public String name() {
		return "Cat";
	}
	
	public void setLocaions( int x, int y) {
		xPos = x;
		yPos = y;	
	}
	
	public int returnXPos() {
		return xPos;
	}
	
	public int returnYPos() {
		return yPos;
	}
}

class Lion extends Feline {
	
	public String name() {
		return "Lion";
	}
	
	public void setLocaions( int x, int y) {
		xPos = x;
		yPos = y;	
	}
	
	public int returnXPos() {
		return xPos;
	}
	
	public int returnYPos() {
		return yPos;
	}
	
	public boolean attack(Animal enemyType) {
		// TODO Auto-generated method stub
		boolean success;
		
		if (enemyType instanceof Hippo) {			
			return true;
		}
		else 
			success = super.attack(enemyType);
		
		return success;
			
	}
}

class Tiger extends Feline {
	
	public String name() {
		return "Tiger";
	}
	
	public void setLocaions( int x, int y) {
		xPos = x;
		yPos = y;	
	}
	
	public int returnXPos() {
		return xPos;
	}
	
	public int returnYPos() {
		return yPos;
	}
}


