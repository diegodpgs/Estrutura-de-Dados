package SkipList;
import java.util.Random;
/**
 * Classe Skip List
 * 
 * A implementacao e com base em nodos, ou seja a estrutura de dados sera constituida 
 * apenas por nodos.
 * 
 * referencia: 
 * http://ocw.mit.edu/OcwWeb/Electrical-Engineering-and-Computer-Science/6-046JFall-2005/VideoLectures/detail/embed12.htm
 * 
 * @author Diego Pedro Goncalves da Silva 
 * @since 25 de Maio de 2010
 * @version 1.0
 * @category Estrutura de dados
 */
public class SkipList {
	static final int MENOS_INFINITO = Integer.MIN_VALUE;
	static final int MAIS_INFINITO  = Integer.MAX_VALUE;
	/**
	 * Objeto que gerará aleoatoriamente a altura do nodo
	 */
	private Random gerador = new Random();
	/**
	 * Altura maxima da Skip List
	 */
	private int alturaMaxima;
	
	
	/**
	 * Altura atual da SkipList
	 *
	 **/
	private int alturaAtual;
	
	
	/**
	 * O nodo que esta no topo da head
	 */	
	private Nodo headTop;
	
	
	/**
	 * O nodo que esta no topo da cauda
	 */
	private Nodo caudaTop;
	
	/**
	 * Numero de elementos da SkipList
	 */
	private int numeroDeElementos;
	/**
	 * 
	 * @param tamanhoMaximo
	 */
	public SkipList(int alturaMaxima){
		this.alturaMaxima = alturaMaxima;	
		initSkipList(alturaMaxima);
	}
	
		
	/**
	 * Cria um pilar da Skip List
	 * 
	 * @param altura altura do pilar
	 * @param valor dos nodos do pilar
	 * @return o topo do pilar
	 */
	private Nodo createPilar(int altura, int valor){
		Nodo top = new Nodo(valor);
		Nodo aux = top;
		
		for(int i = 1 ; i < altura ; i++){
			Nodo novoNo = new Nodo(valor);
			aux.setDown(novoNo);			
			aux = novoNo;
		}		
		
		return top;
	}
	/**
	 * Inicia a SkipList construindo o head e a cauda
	 * @return a referencia para o topo da lista
	 */
	private void initSkipList(int altura){
		
		//seta o topo da head
		this.headTop = createPilar(altura, SkipList.MENOS_INFINITO);
		//seta o topo da cauda
		this.caudaTop = createPilar(altura, SkipList.MAIS_INFINITO);
		
		
		Nodo head = this.headTop;
		Nodo cauda = this.caudaTop;
		
		//referencia as posicoes
		for(int i = 0 ; i < altura ; i++){
			
			head.setNext(cauda);
			head = head.getDown();
			cauda = cauda.getDown();
		}
		
	}

	/**
	 * Retorna a referencia do topo da head
	 * @return a referencia do topo da head
	 */
	public Nodo getHeadTop() {
		return headTop;
	}
	
	/**
	 * Retorna a referencia do topo da cauda
	 * @return a referencia do topo da cauda
	 */
	public Nodo getCaudaTop() {
		return caudaTop;
	}
	
	/**
	 * 
	 * Retorna uma String representando a SkipList
	 * 
	 * @return uma String representando a SkipList
	 */
	@Override
	public String toString(){
		String impressao = "";
		Nodo nodoHead = this.headTop;
		
		impressao += "Altura Maxima       : "+getAlturaMaxima()+"\n";
		impressao += "Altura Atual        : "+getAlturaAtual()+"\n";
		impressao += "Numero de elementos : "+this.getNumeroDeElementos()+"\n\n";
		
		int []elementos = this.getElementos();
				
		while(nodoHead != null){
			impressao += nodoHead.toString();
			Nodo nodo = nodoHead.getNext();
			
			int indiceElementos = 0;
			//percorre uma lista ligada
			//  [x]-->[x+1]....[x+n]
			while(nodo != null){
				
				if(nodo.getValue() > elementos[indiceElementos]){
					int tamanhoDoElemento = String.valueOf(elementos[indiceElementos]).length(); 
				
					impressao += this.conector(tamanhoDoElemento + 6, false);
				}	
				else{	
					
					impressao += this.conector(3, true)+nodo.toString();			
					nodo = nodo.getNext();			
				}
				indiceElementos += 1;
			}
			impressao +="\n";
			nodoHead = nodoHead.getDown();
		}
		
		return impressao;
	}
	
	/**
	 * Utilizado para gerar o conector --- da Skip List 
	 * 
	 * @param tamanho tamanho do conector
	 * @return o conector
	 */
	private String conector(int tamanho, boolean comSeta){
		String impressao = "";
		for(int i = 0 ; i < tamanho ; i++)
			impressao += "-";
		
		if(comSeta)
			impressao += ">";
	    return impressao;
	}
	/**
	 * Retorna os elementos da SkipList, considerando a head e a cauda
	 * @return um vetor com todos os elementos da SkipList
	 */
	private int[] getElementos(){
		int []elementos = new int[this.numeroDeElementos + 3];
		
		Nodo auxHead = this.headTop;
		
		while(auxHead.getDown() != null)
			auxHead = auxHead.getDown();
		
		int indice = 0;
		elementos[indice++] = auxHead.getValue();
		
		while(auxHead != null){
			elementos[indice++] = auxHead.getValue();
			auxHead = auxHead.getNext();
		}
		
		return elementos;
	}
		
	/**
	 * Pesquisa um elemento na SkipList
	 * @param valor a ser procurado
	 * @return true se elemento existe 
	 *         false caso contrario
	 * 
	 */
	public boolean search(int valor){
			
			
		//Nodo sentinela de percorrimento da SkipList
		Nodo nodoAtual = this.headTop;
				
		while(nodoAtual != null){	
			
			if(nodoAtual.getNext() != null){
				if(valor == nodoAtual.getNext().getValue())
					return true;
				else if(nodoAtual.getNext().getValue() < valor)
					nodoAtual = nodoAtual.getNext();	
				else nodoAtual = nodoAtual.getDown();		
			}	
		}
		return false;
	}
	/**
	 * Insere um elemento na skipList
	 */
	public void insert(int valor){
		int alturaDoNodo = gerador.nextInt(this.alturaMaxima)+1;	
		Nodo elemento = createPilar(alturaDoNodo, valor);
		
		int alturaNodoAtual = this.alturaMaxima;
		
		//Nodo sentinela de percorrimento da SkipList
		Nodo nodoAtual = this.headTop;
		//Nodo sentinela do pila do elemento inserido
		Nodo nodoInserido = elemento;
		
		//[A]<---->[C]
		//B e o nodo a ser inserido
		while(nodoAtual != null){			
			if(nodoAtual.getNext().getValue() < valor){
				nodoAtual = nodoAtual.getNext();		
			}			
			else if(alturaNodoAtual <= alturaDoNodo) {
				//[B]-->[C]
				nodoInserido.setNext(nodoAtual.getNext());
				//[B]<-->[C]
				nodoInserido.getNext().setBack(nodoInserido);
				//[A]<--[B]
				nodoInserido.setBack(nodoAtual);
				//[A]<-->[B]
				nodoAtual.setNext(nodoInserido);
				nodoInserido = nodoInserido.getDown();				
				alturaNodoAtual -= 1;			
				
				nodoAtual = nodoAtual.getDown();
			}
			else{
				nodoAtual = nodoAtual.getDown();
				alturaNodoAtual -= 1;
			}
				
		}
		
		numeroDeElementos += 1;
	}
	
	 
	 public boolean remove(int valor){
				
				
			//Nodo sentinela de percorrimento da SkipList
			Nodo nodoAtual = this.headTop;
					
			while(nodoAtual != null){	
				
				if(nodoAtual.getNext() != null){
					
					if(valor == nodoAtual.getNext().getValue()){
						Nodo nodoAremover = nodoAtual.getNext();	
						System.out.println(this.toString());
						desconectaNodo(nodoAremover);	
						System.out.println(this.toString());
						return true;
					}					
					else if(nodoAtual.getNext().getValue() < valor)
						nodoAtual = nodoAtual.getNext();
					else nodoAtual = nodoAtual.getDown();	
				}	
			}
			return false;
		}
	
	private void desconectaNodo(Nodo nodoAremover){
		
		
		while(nodoAremover != null && nodoAremover.getNext() != null){
			//[A]<-->[B]<-->[C]
			//B e o nodo a ser removido
			//[A]<---[C]
			nodoAremover.getNext().setBack(nodoAremover.getBack());
			//[A]<-->[C]
			nodoAremover.getBack().setNext(nodoAremover.getNext()); 		
			
			Nodo nodoremovido = nodoAremover;	
			
			nodoAremover = nodoAremover.getDown();
			
			nodoremovido.setNext(null);
			nodoremovido.setBack(null);
			nodoremovido.setDown(null);
			
			System.out.println("desconecta");
		}
	}
	/**
	 * Retorna a altura atual da SkipList
	 * @return a altura atual da SkipList
	 */
	public int getAlturaAtual(){
		Nodo aux = this.headTop;
		int altura = this.alturaMaxima;
		
		while(aux.getDown() != null){
			
			if (aux.getNext().getValue() < SkipList.MAIS_INFINITO)
				return altura;
				
			altura -= 1;
			
			aux = aux.getDown();
			
		}
		return altura;
		
	}
	
	
	/**
	 * Retorna o numero de elementos da Skip List
	 * @return o numero de elementos da Skip List
	 */
	
	public int getNumeroDeElementos(){
		return this.numeroDeElementos;
	}


	public int getAlturaMaxima() {
		return alturaMaxima;
	}
	
	
}
