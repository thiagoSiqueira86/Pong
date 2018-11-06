package pong.logics;

import java.awt.Color;
import java.awt.Graphics;

import pong.core.Pong;

public class Jogador {

	static final int LARGURA = 40;
	static final int ALTURA = 5;
	
	public boolean direita;
	public boolean esquerda;
	
	public int posX;
	public int posY;
	
	public Jogador(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void reiniciarJogador(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void atualizar() {
		if(direita) {
			posX++;
		}else if(esquerda) {
			posX--;
		}
		
		colisao();
	}
	
	public void renderizar(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(posX, posY, LARGURA, ALTURA);
	}
	
	private void colisao() {
		if(posX + LARGURA > Pong.LARGURA) {
			posX = Pong.LARGURA - LARGURA;
		}else if(posX < 0) {
			posX = 0;
		}
	}
}
