package pong.logics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import pong.core.Pong;

public class Bola {

	static final int LARGURA = 3;
	static final int ALTURA = 3;
	static final double VELOCIDADE = 1.4;
	
	public double posX;
	public double posY;
	public double dirX;
	public double dirY;
	
	public Bola(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		
		direcaoBola();
	}
	
	public void atualizar() {
		colisao();
		colisaoBolaJogador();
		pontuacao();
		
		posX += dirX * VELOCIDADE;
		posY += dirY * VELOCIDADE;
	}
	
	public void renderizar(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)posX, (int)posY, LARGURA, ALTURA);
	}
	
	private void colisao() {
		if(posX + (dirX*VELOCIDADE) + ALTURA >= Pong.LARGURA || posX + (dirX*VELOCIDADE) < 0) {
			dirX*= -1;
		}
	}
	
	private void colisaoBolaJogador() {
		Rectangle colisao = new Rectangle((int)(posX + (dirX*VELOCIDADE)), (int)(posY + (dirY*VELOCIDADE)), LARGURA, ALTURA);
		Rectangle colisaoJogador = new Rectangle(Pong.jogador.posX, Pong.jogador.posY, Jogador.LARGURA, Jogador.ALTURA);
		Rectangle colisaoInimigo = new Rectangle((int)Pong.inimigo.posX, (int)Pong.inimigo.posY, Inimigo.LARGURA, Inimigo.ALTURA);
		
		if(colisao.intersects(colisaoJogador)) {
			direcaoBola();
			if(dirY > 0) {
				dirY *= -1;
			}
		}else if(colisao.intersects(colisaoInimigo)) {
			direcaoBola();
			if(dirY < 0) {
				dirY *= -1;
			}
		}
	}
	
	public void pontuacao() {
		if(posY >= Pong.ALTURA) {
			System.out.println("Ponto Deles");
			new Pong();
			return;
		}else if(posY < 0) {
			System.out.println("Ponto Nosso");
			new Pong();
			return;
		}
	}

	public void direcaoBola() {
		int angulo = new Random().nextInt(120 - 45) + 46;
		this.dirX = Math.cos(Math.toRadians(angulo));
		this.dirY = Math.sin(Math.toRadians(angulo));
	}
}
