import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	//arrays cuerpo vibora
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	private int lengthofsnake = 3;
	private int moves = 0;
	
	//booleanos para mov
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private Timer timer;
	private int delay = 100;
	
	//CREO UN ARRAY CON POSICIONES DE ENEMIGOS
	private int [] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
			350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,
			750,775,800,825,850};
	private int [] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,
			350,375,400,425,450,475,500,525,550,575,600,625};
	
	
	
	//VARIABLES DE IMAGENES VIBORA
	private ImageIcon snakeImage;
	private ImageIcon titleImage;
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon enemyImage;
	
	//variables para enemigos
	private Random random = new Random();
	private int xpos = random.nextInt(34); //34 son la cantidad de posiciones
	private int ypos = random.nextInt(23); // 23 cant de posiciones	
	
	//score y gameover
	private int score = 0;
	
	public Gameplay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
		
		
	}
	
	public void paint(Graphics g) {
		
		if(moves == 0) 
		{
			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;
			
			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;
		}
		
		//ACA DIBUJO EL BORDE DEL TITULO
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//ACA DIBUJO LA IMAGEN DEL TITULO
		
	titleImage = new ImageIcon("snaketitle.jpg");
	titleImage.paintIcon(this, g, 25, 11);
	
		//ACA DIBUJO EL BORDE DEL GAMEPLAY
	g.setColor(Color.WHITE);
	g.drawRect(24, 74, 851, 577);	
	
		//ACA DIBUJO EL FONDO DEL GAMEPLAY
	g.setColor(Color.black);
	g.fillRect(25, 75, 850, 575);
	
		//ACA DIBUJO EL SCORE
	g.setColor(Color.WHITE);
	g.setFont(new Font("arial", Font.PLAIN, 14));
	g.drawString("Score: "+ score, 780, 30 );
	
		//ACA DIBUJO EL LARGO DE LA VIBO
	g.setColor(Color.WHITE);
	g.setFont(new Font("arial", Font.PLAIN, 14));
	g.drawString("Largo: "+ lengthofsnake, 780, 50 );
	
		//ACE DIBUJO LA SNAKE
	rightmouth = new ImageIcon("rightmouth.png");
	rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
	
	
	for(int i = 0; i<lengthofsnake; i++) {
		
		if(i == 0 && right) {
			rightmouth = new ImageIcon("rightmouth.png");
			rightmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			
		}
		
		if(i == 0 && left) {
			leftmouth = new ImageIcon("leftmouth.png");
			leftmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
		}
		
		if(i == 0 && up) {
			upmouth = new ImageIcon("upmouth.png");
			upmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
		}
		
		if(i == 0 && down) {
			downmouth = new ImageIcon("downmouth.png");
			downmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
		}
		
		if(i != 0) {
			snakeImage = new ImageIcon("snakeimage.png");
			snakeImage.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
		
	}
	
	
	enemyImage = new ImageIcon("enemy.png");
	
	if((enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0]))	
	{
		score++;
		lengthofsnake++;
		xpos = random.nextInt(34);
		ypos = random.nextInt(23);
	}
	
	enemyImage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
	
	for(int b = 1; b < lengthofsnake; b++) {
		if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]) {
			 right = false;
			 left = false;
			 up = false;
			 down = false;
			 
			 g.setColor(Color.WHITE);
			 g.setFont(new Font("arial", Font.BOLD, 50));
			 g.drawString("Game Over", 300, 300);
			 
			 g.setFont(new Font("arial", Font.BOLD, 20));
			 g.drawString("ESPACIO para Continuar", 320, 340);
		}
	}
	
	
	
	g.dispose();
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) {
			for(int r = lengthofsnake-1; r >=0; r--) {
				snakeylength[r+1] = snakeylength[r];
			}
			for(int r = lengthofsnake; r >=0; r--) {
				if(r==0) {
					snakexlength[r] = snakexlength[r] + 25;
				}
				else {
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r] > 850) {
					snakexlength[r] = 25;
				}
			}
			repaint();
		}
		if(left) {
			for(int r = lengthofsnake-1; r >=0; r--) {
				snakeylength[r+1] = snakeylength[r];
			}
			for(int r = lengthofsnake; r >=0; r--) {
				if(r==0) {
					snakexlength[r] = snakexlength[r] - 25;
				}
				else {
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r] < 25) {
					snakexlength[r] = 850;
				}
			}
			repaint();
			
		}
		if(up) {
			for(int r = lengthofsnake-1; r >=0; r--) {
				snakexlength[r+1] = snakexlength[r];
			}
			for(int r = lengthofsnake; r >=0; r--) {
				if(r==0) {
					snakeylength[r] = snakeylength[r] - 25;
				}
				else {
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r] < 75) {
					snakeylength[r] = 625;
				}
			}
			repaint();
			
		}
		if(down) {
			for(int r = lengthofsnake-1; r >=0; r--) {
				snakexlength[r+1] = snakexlength[r];
			}
			for(int r = lengthofsnake; r >=0; r--) {
				if(r==0) {
					snakeylength[r] = snakeylength[r] + 25;
				}
				else {
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r] > 625) {
					snakeylength[r] = 75;
				}
			}
			repaint();
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			moves = 0;
			score = 0;
			lengthofsnake = 3;
			repaint();
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			moves++;
			right = true;
			if(!left) {
				right = true;
			} else {
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			moves++;
			left = true;
			if(!right) {
				left = true;
			} else {
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			
			moves++;
			up = true;
			if(!down) {
				up = true;
			} else {
				up = false;
				down = true;
			}
			left = false;
			right = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			moves++;
			down = true;
			if(!up) {
				down = true;
			} else {
				down = false;
				up = true;
			}
			right = false;
			left = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	
}
