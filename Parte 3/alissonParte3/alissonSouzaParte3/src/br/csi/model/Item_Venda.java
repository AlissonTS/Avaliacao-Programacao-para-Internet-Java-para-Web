package br.csi.model;

public class Item_Venda {
		
	private long id_venda;
	private long id_produto;
	private int qtd;
	private float totalItem;
	
	public long getId_venda() {
		return id_venda;
	}
	public void setId_venda(long id_venda) {
		this.id_venda = id_venda;
	}
	public long getId_produto() {
		return id_produto;
	}
	public void setId_produto(long id_produto) {
		this.id_produto = id_produto;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public float getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(float totalItem) {
		this.totalItem = totalItem;
	}
	
	
}
