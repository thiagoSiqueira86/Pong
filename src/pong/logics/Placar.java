package pong.logics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import pong.core.Pong;

public class Placar {

	public int placarJogador;
	public int placarCPU;
	public boolean jogadorVencedor;
	public boolean cpuVencedor;
	
	public Placar() {
		this.placarJogador = 0;
		this.placarCPU = 0;
		this.jogadorVencedor = false;
		this.cpuVencedor = false;
	}
	
	public void atualizar() {
		if(Pong.bola.posY >= Pong.ALTURA) {
			placarCPU++;
			resetarPosicoes();
		}else if(Pong.bola.posY < 0) {
			placarJogador++;
			resetarPosicoes();
		}
		
		if(placarJogador >= 5) {
			jogadorVencedor = true;
		}else if(placarCPU >= 5) {
			cpuVencedor = true;
		}
	}
	
	public void renderizar(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString("CPU: " + placarCPU, 30, 10);
		g.drawString("Jogador: " + placarJogador, 30, Pong.ALTURA - 10);
	}
	
	private void resetarPosicoes() {
		Pong.jogador.posX = 100;
		Pong.jogador.posY = Pong.ALTURA - 5;
		Pong.inimigo.posX = 100;
		Pong.inimigo.posY = 0;
		Pong.bola.posX = 100;
		Pong.bola.posY = Pong.ALTURA / 2 - 1;
		Pong.bola.direcaoBola();
	}
}
