package animalGUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import animalGUI.Animal;
import animalGUI.Canine;
import animalGUI.Turtle;

/**
 * @author ASUS
 *	the GUI class of the animal game
 */
public class AnimalGUI extends JFrame {
	static ArrayList<Animal> aList = new ArrayList<Animal>();
	static JCheckBox [] jcb = new JCheckBox[8];
	static JLabel[][] mapSquares = new JLabel[15][15];
	private static char [][] cells = new char[15][15];
	static ImageIcon[] aniIcon = new ImageIcon[8];
	static JFrame animalIconSelection = new JFrame("animalIconSelection");
	static JFrame map = new JFrame("map");
	static String [] animalList = {"Cat", "Dog", "Fox", "Hippo", "Lion", "Tiger", "Turtle", "Wolf"};
	MouseEvent listener = new MouseEvent();
	MotionListener mListener = new MotionListener();
	
	
	/**
	 * set up the icon selection menu
	 */
	public void setupAIS() {				
		
		JPanel panel = null;
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
		JButton[] aniImgChosButton = new JButton[8];
		aList.add( new Cat() );
		aList.add( new Dog() );
		aList.add( new Fox() );
		aList.add( new Hippo() );
		aList.add( new Lion() );
		aList.add( new Tiger() );
		aList.add( new Turtle() );
		aList.add( new Wolf() );
		animalIconSelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i = 0; i < animalList.length; i++) {
			String temp = animalList[i];
			jcb[i]= new JCheckBox(temp);
			
			jcb[i].setSelected(true);
			aniImgChosButton[i] = new JButton("Pick an alternative icon");
			aniImgChosButton[i].addActionListener(new chooseButtonListener(i));
			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			panel.add(jcb[i]);
			panel.add(aniImgChosButton[i]);
			controlPanel.add(panel);
		}
		
		JButton start = new JButton("start");
		panel = new JPanel();
		panel.add(start);
		controlPanel.add(panel);
		map.setSize(900, 900);
	
		start.addActionListener(new ActionListener() {

		@Override
			public void actionPerformed(ActionEvent arg0) {
				
				animalIconSelection.setVisible(false);
				setupMap();
				map.setVisible(true)
				;
			}
			
		});
		animalIconSelection.getContentPane().add(controlPanel);

		
		animalIconSelection.pack();
		animalIconSelection.setResizable(false);
		animalIconSelection.setVisible(true);
		
		
		
	}
	
	
	ImageIcon redIcon;
	private void setupMap() {
		JPanel panelMap = new JPanel(new GridLayout(0,15));
		map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelMap.addMouseMotionListener(mListener);
		panelMap.addMouseListener(listener);
		final char[] st = {'c', 'd', 'f', 'h', 'l', 't', 'u', 'w'};
		int x, y;
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
		for ( int ii = 0 ; ii < mapSquares.length; ii++) {
			for ( int jj = 0; jj< mapSquares[ii].length; jj++) {
				JLabel b = new JLabel();
				b.setBorder(border);
				b.setOpaque(true);
				b.setBackground(Color.white);
				b.setName("");
				mapSquares[ii][jj] = b;
				panelMap.add(mapSquares[ii][jj]);				
			}
		}
		
		
		
		for (int i = 0; i < jcb.length; i++) {
			if (!jcb[i].isSelected()) {
				for( Animal a : aList) {
					if (a.name().equals(animalList[i])) {
						aList.remove(a);
						break;
					}
				}
			}
		}
		
		for (Animal a : aList) {
			
			while (true) {
				x = (int) (Math.random()*15);
				y = (int) (Math.random()*15);
				if (cells[x][y] == 0) {
					cells[x][y] = st[aList.indexOf(a)];
					a.setLocaions(x, y);
					a.setStatus(true);
					try {
						String type = a.name();
						BufferedImage img = ImageIO.read(new File("/Users/ASUS/Desktop/Icon/"+ type + ".png"));
						if(a.returnIcon() == null)
							mapSquares[x][y].setIcon(new ImageIcon(img)); 
						else
							mapSquares[x][y].setIcon(a.returnIcon());
						mapSquares[x][y].setName(type);
						
						BufferedImage red = ImageIO.read(new File("/Users/ASUS/Desktop/Icon/red.png"));
						redIcon = new ImageIcon(red);
						
					} catch (Exception ex) {
						System.out.println(ex);
					}
					break;
					
				}
			}			
		}
		
		JPanel mode = new JPanel();
		JButton modeSelection = new JButton("Mode");
		mode.add(modeSelection);
		modeSelection.addActionListener( new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int aniExist = 0;
				maunalGameMode();
				for(Animal a: aList) {
					if (a.returnStatus() == true) {
						aniExist++;
					}
				}
				if (aniExist == 1) {
					for ( int ii = 0 ; ii < mapSquares.length; ii++) {
						for ( int jj = 0; jj< mapSquares[ii].length; jj++) {
							mapSquares[ii][jj].setIcon(null);
							mapSquares[ii][jj].setName("");
							mapSquares[ii][jj].setBackground(Color.white);
							cells[ii][jj] = 0;							
						}
					}
					for (Animal a : aList) {
						
						while (true) {
							int x = (int) (Math.random()*15);
							int y = (int) (Math.random()*15);
							if (cells[x][y] == 0) {
								cells[x][y] = st[aList.indexOf(a)];
								a.setLocaions(x, y);
								a.setStatus(true);
								try {
									String type = a.name();
									BufferedImage img = ImageIO.read(new File("/Users/ASUS/Desktop/Icon/"+ type + ".png"));
									
									mapSquares[x][y].setIcon(new ImageIcon(img)); 
									mapSquares[x][y].setName(type);
									
									
									
								} catch (Exception ex) {
									System.out.println(ex);
								}
								break;
								
							}
						}			
					}
				}
				
			}
			
		});
		panelMap.add(mode);
		map.getContentPane().add(panelMap);
		
		
	}
	
	private void maunalGameMode() {	
		
		int beforeX, beforeY, afterX, afterY ;	
		
				 		 
			// When the animal list haven't finished run through all animals
			for(Animal a : aList) {				
				beforeX = a.returnXPos();
				beforeY = a.returnYPos();
				// only move when animal is alive
				if ( a.returnStatus() == true) {
					a.move();				
					afterX = a.returnTempXPos();
					afterY = a.returnTempYPos();
					
					
					//Special case for Canine which moves 2 steps a time
					if (a instanceof Canine) {
						if ( (afterX - beforeX) == 2 || (afterY - beforeY) == 2 || (afterX - beforeX) == -2 || (afterY - beforeY) == -2) {
							if ((afterX - beforeX) == 2 ) {
								if ( (mapSquares[afterX - 1][afterY].getName() == null || mapSquares[afterX - 1][afterY].getName() == "") && (mapSquares[afterX][afterY].getName() == null || mapSquares[afterX][afterY].getName() == "") ) {
									moveOnMap(a, beforeX, beforeY, afterX - 1, afterY);
									moveOnMap(a, afterX - 1, afterY, afterX, afterY);
									System.out.println(a.name() + " moved from " + beforeX + ", " + beforeY + " to " + afterX + ", " + afterY);


								}
								else {
									attackOnMap(a, beforeX, beforeY, afterX - 1, afterY);
									attackOnMap(a, afterX - 1, afterY, afterX, afterY);
								}
								
							}
							else if ( (afterY - beforeY) == 2 ) {
								if ( (mapSquares[afterX][afterY - 1].getName() == null ||  mapSquares[afterX][afterY - 1].getName() == "") && (mapSquares[afterX][afterY].getName() == null || mapSquares[afterX][afterY].getName() == "") ) {
									moveOnMap(a, beforeX, beforeY, afterX,afterY - 1);
									moveOnMap(a, afterX, afterY - 1, afterX,afterY);
									System.out.println(a.name() + " moved from " + beforeX + ", " + beforeY + " to " + afterX + ", " + afterY);

								}
								else {
									attackOnMap(a, beforeX, beforeY, afterX, afterY - 1);
									attackOnMap(a, afterX, afterY - 1, afterX,afterY);
								}
							}
							else if ((afterX - beforeX) == -2) {
								if ( (mapSquares[afterX + 1][afterY].getName() == null || mapSquares[afterX + 1][afterY].getName() == "") && (mapSquares[afterX][afterY].getName() == null || mapSquares[afterX][afterY].getName() == "")) {
									moveOnMap(a, beforeX, beforeY, afterX + 1,afterY);
									moveOnMap(a, afterX + 1, afterY, afterX,afterY);
									System.out.println(a.name() + " moved from " + beforeX + ", " + beforeY + " to " + afterX + ", " + afterY);

								}
								else {
									attackOnMap(a, beforeX, beforeY, afterX + 1, afterY);
									attackOnMap(a, afterX + 1, afterY, afterX,afterY);
								}

							}
							else if ((afterY - beforeY) == -2) {
								if (( mapSquares[afterX][afterY + 1].getName() == null || mapSquares[afterX][afterY + 1].getName() == "" ) && (mapSquares[afterX][afterY].getName() == null || mapSquares[afterX][afterY].getName() == "")) {
									moveOnMap(a, beforeX, beforeY, afterX,afterY + 1);
									moveOnMap(a, afterX, afterY + 1, afterX,afterY);
									System.out.println(a.name() + " moved from " + beforeX + ", " + beforeY + " to " + afterX + ", " + afterY);

								}
								else {
									attackOnMap(a, beforeX, beforeY, afterX, afterY + 1);
									attackOnMap(a, afterX, afterY + 1, afterX, afterY);
								}
							}							
						}
						else if ( mapSquares[afterX][afterY].getName() == null ||  mapSquares[afterX][afterY].getName() == "") {
							
							moveOnMap(a, beforeX, beforeY, afterX, afterY);
							System.out.println(a.name() + " moved from " + beforeX + ", " + beforeY + " to " + afterX + ", " + afterY);
						}
						// Encountered other animal
						else {						
							attackOnMap(a, beforeX, beforeY, afterX, afterY);
						}
					}
					// The next location has no animals to encounter
					else if ( mapSquares[afterX][afterY].getName() == null ||  mapSquares[afterX][afterY].getName() == "") {
						
						moveOnMap(a, beforeX, beforeY, afterX, afterY);
						System.out.println(a.name() + " moved from " + beforeX + ", " + beforeY + " to " + afterX + ", " + afterY);
					}
					// Encountered other animal
					else {						
						attackOnMap(a, beforeX, beforeY, afterX, afterY);
					}

				}
				
			}
			
				
			
	}
	
	
	
	private void moveOnMap(Animal currentAnimal, int oldPosX, int oldPosY, int newPosX, int newPosY) {
		Animal a = currentAnimal;
		int beforeX = oldPosX;
		int beforeY = oldPosY;
		int afterX = newPosX;
		int afterY = newPosY;
		
		
		Icon tempIcon = mapSquares[beforeX][beforeY].getIcon();
		String tempName = mapSquares[beforeX][beforeY].getName();
		mapSquares[afterX][afterY].setIcon(tempIcon);
		mapSquares[afterX][afterY].setName(tempName);
		mapSquares[beforeX][beforeY].setIcon(null);
		mapSquares[beforeX][beforeY].setName("");
		
		a.setLocaions(afterX, afterY);
		
	}
	
	private void attackOnMap(Animal currentAnimal, int oldPosX, int oldPosY, int newPosX, int newPosY) {
		Animal a = currentAnimal;
		int beforeX = oldPosX;
		int beforeY = oldPosY;
		int afterX = newPosX;
		int afterY = newPosY;
		
		// Search for the enemy animal type
		for (Animal enemy : aList) {
			System.out.println(enemy.name());
			System.out.println(enemy.returnStatus());
			if ( enemy.returnXPos() == afterX && enemy.returnYPos() == afterY) {
				// Special case for turtle where it doesn't move
				System.out.println("hi same location");
				if ( enemy instanceof Turtle) {
					break;
				}
				//Check whether the enemy is dead already
			
				
				else if ( enemy.returnStatus() == false) {
					break;
				}
				else {
					System.out.print(a.name() + " from " +  beforeX + ", " + beforeY + " attacks " + enemy.name() + " at " + enemy.returnXPos() + ", " + enemy.returnYPos() + " and ");
					// Enemy wins
					if ( a.attack(enemy) == false ) {
						// Change locations when die
						while( mapSquares[afterX][afterY].getName() != "") {
							a.move();

							afterX = a.returnTempXPos();
							afterY = a.returnTempYPos();
						}
						
						
						try {
						BufferedImage img = ImageIO.read(new File("/Users/ASUS/Desktop/Icon/"+ a.name() + "Died.png"));
						a.setStatus(false);
						mapSquares[afterX][afterY].setIcon(new ImageIcon(img));
						mapSquares[afterX][afterY].setBackground(Color.yellow);
						mapSquares[afterX][afterY].setName("died");
						} catch (Exception ex) {
							System.out.println("cant load file");
						}
						
						mapSquares[beforeX][beforeY].setIcon(null);
						mapSquares[beforeX][beforeY].setName("");
						a.setLocaions(afterX, afterY);
						a.setStatus(false);
						System.out.println("loses");
						System.out.println(a.name() + " dies at " + afterX + ", " + afterY);
						break;
					}
					// Enemy loses
					else {
						int enemyX = afterX;
						int enemyY = afterY;
						// Change locations when die
						while( mapSquares[afterX][afterY].getName() != "") {
							enemy.move();
							afterX = enemy.returnTempXPos();
							afterY = enemy.returnTempYPos();
						}
					
						
						try {
							String diedAni = enemy.name();
							BufferedImage img = ImageIO.read(new File("/Users/ASUS/Desktop/Icon/"+ diedAni + "Died.png"));
							enemy.setStatus(false);
							mapSquares[afterX][afterY].setIcon(new ImageIcon(img));
							mapSquares[afterX][afterY].setBackground(Color.yellow);
							mapSquares[afterX][afterY].setName("died");
							} catch (Exception ex) {
								System.out.println("cant load file");
							}
						
						
						mapSquares[enemyX][enemyY].setIcon(null);
						mapSquares[enemyX][enemyY].setName("");
						
						
						enemy.setLocaions(afterX, afterY);
						enemy.setStatus(false);
						System.out.println("wins");
						System.out.println(enemy.name() + " dies at " + afterX + ", " + afterY);
						// Attacker move again to other position
						/*a.move();
						afterX = a.returnTempXPos();
						afterY = a.returnTempYPos();
						while(cells[afterX][afterY] != 0) {
							enemy.move();
							afterX = enemy.returnTempXPos();
							afterY = enemy.returnTempYPos();
						}
						
						temp = cells[beforeX][beforeY];
						cells[enemyX][enemyY] = 0;
						cells[afterX][afterY] = temp;
						
						*/
						break;
					}
				}
			}
		}
	}
	
	
	class MouseEvent implements MouseListener {
		int[] tempLocation = new int[2];
		Animal tempType;
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
			System.out.println("MouseEntered");
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
			
			System.out.println("MouseExited");

		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			Component source = e.getComponent().getComponentAt(e.getPoint());
			
			
			for ( int ii = 0; ii < mapSquares.length; ii++) {
				for ( int jj = 0 ; jj < mapSquares[ii].length; jj++) {
					if ( source == mapSquares[ii][jj] && mapSquares[ii][jj].getIcon() != null) {
						for( Animal a : aList) {
							if(mapSquares[ii][jj].getName().equals(a.name())) {	
								tempLocation[0] = ii;
								tempLocation[1] = jj;
								tempType = a.returnType();
								System.out.println(tempType.toString());								
								for( int[] i : a.moveOptions()) {
									
									if(ii + i[0] < 15 && ii + i[0] >= 0 && jj + i[1] < 15 && jj + i[1] >= 0) {
										if(mapSquares[ii + i[0]][jj + i[1]].getIcon() != null && mapSquares[ii + i[0]][jj + i[1]].getBackground() != Color.yellow){
											mapSquares[ii + i[0]][jj + i[1]].setBackground(Color.RED);
											
										}
										else if(mapSquares[ii + i[0]][jj + i[1]].getIcon() == null)
											mapSquares[ii + i[0]][jj + i[1]].setBackground(Color.orange);
									}
								}
								
							break;
							}					
						}
					}
				}
			}
			

		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			int x = -1, y = -1;
			String type = null;
			Component source = e.getComponent().getComponentAt(e.getPoint());
			if(tempLocation != null) {
				x = tempLocation[0];
				y = tempLocation[1];
				type = mapSquares[x][y].getName();
				
			}
			Icon tempIcon;
		
			
			if (source.getBackground() == Color.blue) {
				for ( int ii = 0; ii < mapSquares.length; ii++) {
					for ( int jj = 0 ; jj < mapSquares[ii].length; jj++) {						
						if(source == mapSquares[ii][jj]) {							
							if(mapSquares[ii][jj].getIcon() == null) {
								tempIcon = mapSquares[x][y].getIcon();
								mapSquares[ii][jj].setIcon(tempIcon); 
								mapSquares[ii][jj].setName(type);
								tempType.setLocaions(ii, jj);
								mapSquares[x][y].setIcon(null);
								mapSquares[x][y].setName("");
							}						
							//encounter enemy
							else {		
								//Check type of enemy
								for (Animal b : aList) {
									
										if(mapSquares[ii][jj].getName().equals(b.name())) {
											// Attacker wins
											if(tempType.attack(b.returnType()) == true) {
												tempIcon = mapSquares[ii][jj].getIcon();
												for( int[] i : b.moveOptions()) {
													if(ii + i[0] < 15 && ii + i[0] >= 0 && jj + i[1] < 15 && jj + i[1] >= 0) {
														if(mapSquares[ii + i[0]][jj + i[1]].getIcon() == null){
															try {
																String diedAniType = b.name();
																BufferedImage img = ImageIO.read(new File("/Users/ASUS/Desktop/Icon/"+ diedAniType + "Died.png"));
																b.setStatus(false);
																mapSquares[ii + i[0]][jj + i[1]].setIcon(new ImageIcon(img));
																mapSquares[ii + i[0]][jj + i[1]].setBackground(Color.yellow);
																mapSquares[ii + i[0]][jj + i[1]].setName("Died");
															} catch (Exception ex) {
																System.out.println(ex);	
															}
															
															mapSquares[ii][jj].setIcon(null);
															mapSquares[ii][jj].setName("");
															tempType.setLocaions(x, y);
															b.setLocaions(ii, jj);
															break;
														}														
													}
												}
											}
											// Attacker lose
											else {
												tempIcon = mapSquares[x][y].getIcon();
												for( int[] i : tempType.moveOptions()) {
													if(x + i[0] < 15 && x + i[0] >= 0 && y + i[1] < 15 && y + i[1] >= 0) {
														if(mapSquares[x + i[0]][y + i[1]].getIcon() == null){															
															try {
																String diedAniType = tempType.name();
																BufferedImage img = ImageIO.read(new File("/Users/ASUS/Desktop/Icon/"+ diedAniType + "Died.png"));
																tempType.setStatus(false);
																mapSquares[x + i[0]][y + i[1]].setIcon(new ImageIcon(img));	
																mapSquares[x + i[0]][y + i[1]].setBackground(Color.yellow);
																mapSquares[x + i[0]][y + i[1]].setName("Died");
															} catch (Exception ex) {
																System.out.println(ex);	
															}															
															mapSquares[x][y].setIcon(null);
															mapSquares[x][y].setName("");
															tempType.setLocaions(x, y);
															b.setLocaions(ii, jj);
															break;
														}														
													}
												}
											}
											break;
										}
								}
							}
						}										
					}
				}
			}			
			for( int[] i : tempType.moveOptions()) {
				
				if(x + i[0] < 15 && x + i[0] >= 0 && y + i[1] < 15 && y + i[1] >= 0) {	
					if(mapSquares[x + i[0]][y + i[1]].getBackground() != Color.yellow)
						mapSquares[x + i[0]][y + i[1]].setBackground(Color.white);
				}
				
			}		
			tempLocation = new int[2];
			type = new String();
			x = -1; y = -1;
			
		}
		
	}
	
	class MotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			Component source = e.getComponent().getComponentAt(e.getPoint());
			if ( source.getBackground() == Color.orange || source.getBackground() == Color.red)
				source.setBackground(Color.blue);
			if (source.getBackground() == Color.white) {				
				for ( int ii = 0; ii < mapSquares.length; ii++) {
					for ( int jj = 0 ; jj < mapSquares[ii].length; jj++) {
						if(mapSquares[ii][jj].getBackground() == Color.blue) {
							mapSquares[ii][jj].setBackground(Color.orange);
						}
					}
				}
			}
				
		}

		@Override
		public void mouseMoved(java.awt.event.MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AnimalGUI a = new AnimalGUI();
		a.setupAIS();
		
		
	}

}

class chooseButtonListener implements ActionListener{
	private int I;
	chooseButtonListener(int _I){
		I = _I;
	}
	/**
	 * change animal icon
	 */
	
	public void actionPerformed(ActionEvent ae){
		JFrame aniCho = new JFrame("Choose a new Icon");
		
		aniCho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFileChooser choose = new JFileChooser("Choose a new Icon");
		aniCho.getContentPane().add(choose);
		aniCho.pack();
		aniCho.setVisible(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG & GIF Images", "jpg", "gif", "png");
		choose.setFileFilter(filter);
		int returnVal = choose.showOpenDialog(aniCho);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		   System.out.println("You chose to open this file: " + choose.getSelectedFile().getName());
		   Image img = (new ImageIcon(choose.getSelectedFile().getPath())).getImage();
		   Image newimg = img.getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH);
		   
		   for(Animal a: AnimalGUI.aList) {
			   if(AnimalGUI.aList.indexOf(a) == I){
				   a.setIcon(new ImageIcon(newimg));
			   }
		   }
		   
		}
		
		aniCho.setVisible(false);
		
	}
}
