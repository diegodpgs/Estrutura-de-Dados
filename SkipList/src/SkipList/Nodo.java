package SkipList;
/**
 * Classe nodo que sera manipulado pelo Skip List
 * 
 * @author Diego Pedro Goncalves da Silva 
 * @since 25 de Maio de 2010
 * @version 1.0
 * @category Estrutura de dados
 */
public class Nodo {
	/**
	 * Proximo nodo [x] --> [x+1]
	 */
	private Nodo next;
	/**
	 * Proximo nodo abaixo 
	 *  [x]
	 *   |
	 *  [x]
	 */
	private Nodo down;
	/**
	 * Nodo Anterior [x-1]<--[x]
	 * Utilizado apenas para tornar mais eficiente a remoção
	 */
	private Nodo back;
	/**
	 * Valor do nodo
	 */
	private int value;
	
	/**
	 * Construtor da classe Nodo
	 * @param valor do elemento
	 */
	public Nodo(int valor){
		this.value = valor;
	}
		
	/**
	 * Retorna o proximo nodo
	 * @return o proximo nodo
	 */
	public Nodo getNext() {
		return next;
	}
	
	/**
	 * Modifica a referencia do proximo nodo
	 * @param next a nova referencia para o proximo nodo
	 */
	public void setNext(Nodo next) {
		this.next = next;
	}
	
	/**
	 * Retorna o nodo que está abaixo na lista
	 * @return o nodo que está abaixo na lista
	 */
	public Nodo getDown() {
		return down;
	}
	
	/**
	 * Modifica a referencia para o nodo abaixo na lista
	 * @param down a nova referencia para o nodo abaixo na lista
	 */
	public void setDown(Nodo down) {
		this.down = down;
	}
	
	/**
	 * Retorna o nodo anterior
	 * Utilizado apenas para tornar mais eficiente a remoção
	 * @return o nodo anterior
	 */
	public Nodo getBack() {
		return back;
	}
	
	/**
	 * Modifica a referencia para o nodo anterior
	 * Utilizado apenas para tornar mais eficiente a remoção
	 * @param down a nova referencia para o nodo anterior
	 */
	public void setBack(Nodo back) {
		this.back = back;
	}
	
	/**
	 * Retorna o valor do nodo
	 * @return o valor do nodo
	 */
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString(){		
	     return "["+String.valueOf(this.value)+"]";
	}
	
	
}
