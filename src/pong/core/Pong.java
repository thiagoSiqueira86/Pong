package pong.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import pong.logics.Bola;
import pong.logics.Inimigo;
import pong.logics.Jogador;
import pong.logics.Placar;

public class Pong extends Canvas implements Runnable, KeyListener {

	
	private static final long serialVersionUID = 1L;
	
	public static final int LARGURA = 240;
	public static final int ALTURA = 120;
	public static final int ESCALA = 3;
	public BufferedImage layer;
	
	public static Jogador jogador;
	public static Inimigo inimigo;
	public static Bola bola;
	public static Placar placar;
	
	private boolean isRunnable = false;
	private boolean gameOver = false;
	
	public Pong() {
		this.setPreferredSize(getConfiguracaoJanela());
		this.addKeyListener(this);
		
		jogador = new Jogador(100, ALTURA - 5);
		inimigo = new Inimigo(100, 0);
		bola = new Bola(100, ALTURA/ 2 - 1);
		placar = new Placar();
		
		layer = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
	}
	
	public void atualizar() {
		jogador.atualizar();
		inimigo.atualizar();
		bola.atualizar();
		placar.atualizar();
	}
	
	public void renderizar() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LARGURA, ALTURA);
		jogador.renderizar(g);
		inimigo.renderizar(g);
		bola.renderizar(g);
		placar.renderizar(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, LARGURA*ESCALA, ALTURA*ESCALA, null);
		
		if(!isRunnable && gameOver == false) {
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Precione Enter para Começar", 100, ALTURA);
		}
		
		if(placar.jogadorVencedor) {
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Parabens você venceu!!(Enter para Reiniciar)", 100, ALTURA);
			isRunnable = false;
			gameOver = true;
		}
		
		if(placar.cpuVencedor) {
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Que pena você perdeu!!(Enter para Reiniciar)", 100, ALTURA);
			isRunnable = false;
			gameOver = true;
		}
		
		bs.show();
	}
	
	public static void main(String[] args) {
		Pong pong = new Pong();
		getConfiguracaoFrame(pong);
		new Thread(pong).start();
	}
	
	private static void getConfiguracaoFrame(Pong pong) {
		JFrame f = new JFrame("PONG!");
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(pong);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	private Dimension getConfiguracaoJanela() {
		Dimension d = new Dimension();
		d.setSize(LARGURA*ESCALA, ALTURA*ESCALA);
		return d;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			jogador.direita = true;
			break;
		case KeyEvent.VK_LEFT:
			jogador.esquerda = true;
			break;
		case KeyEvent.VK_ENTER:
			if(gameOver == false) {
				isRunnable = true;
			}else {
				gameOver = false;
				new Pong();
			}
			break;
		case KeyEvent.VK_ESCAPE:
			isRunnable = false;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			jogador.direita = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			jogador.esquerda = false;
		}
		
	}

	@Override
	public void run() {
		while(true) {
			if(isRunnable) {
				atualizar();
				renderizar();
			}else {
				renderizar();
			}
			try {
				Thread.sleep(1000/60);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
