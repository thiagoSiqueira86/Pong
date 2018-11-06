package pong.logics;

import java.awt.Color;
import java.awt.Graphics;

import pong.core.Pong;

public class Inimigo {

	static final int LARGURA = 40;
	static final int ALTURA = 5;
	
	public double posX;
	public double posY;
	
	public Inimigo(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void reiniciarInimigo(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void atualizar() {
		posX += (Pong.bola.posX - posX - 6) * 0.07;
		colisao();
	}
	
	public void renderizar(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) posX, (int) posY, LARGURA, ALTURA);
	}
	
	public void colisao() {
		if(posX + LARGURA > Pong.LARGURA) {
			posX = Pong.LARGURA - LARGURA;
		}else if(posX < 0) {
			posX = 0;
		}
	}
}
