package ListaEncadeada;
/**
 * Classe ListaDuplamenteEncadeada
 * 
 * @author Diego Pedro Goncalves da Silva 
 * @since 29 de Maio de 2010
 * @version 1.0 
 */
public class ListaDuplamenteEncadeada {
    /**
     * Cabeça da lista
     */
    private Nodo cabeca;
    /**
     * Cauda da lista
     */
    private Nodo cauda;
    
    /**
     * Tamanho atual da lista
     */
    private int tamanho;

    /**
     * Insere ordenado um elemento na lista     
     * caso o elemento seja igual insere antes
     * @param elemento elemento a se inserido
     * 
     */
    public void insere(Nodo elemento){        
        
        //a lista esta vazia
        if(cabeca == null){
            cabeca = elemento;
            cauda = elemento;
            tamanho += 1;
        }
        //insere no inicio da lista
        else if(elemento.compareTo(cabeca) <= 0)            
            insereNoInicio(elemento);
        
        //insere no final da lista
        else if(elemento.compareTo(cauda) > 0)
            insereNoFinal(elemento);
        //insere no meio
        else{
            
            Nodo nodoIterador = cabeca.getNext();
            
            //procura um elemento na lista maior ou igual que o elemento a ser inserido
            while(nodoIterador.compareTo(elemento) < 0)                
                nodoIterador = nodoIterador.getNext();    
            
            
            Nodo nodoAnterior = nodoIterador.getBack();
            
            //[anterior]--->[elemento]
            nodoAnterior.setNext(elemento);
            
            //[anterior]<-->[elemento]
            elemento.setBack(nodoAnterior);
            
            //[elemento]--->[proximo == nodoIterador]            
            elemento.setNext(nodoIterador);
            
            //[elemento]<-->[proximo == nodoIterador]    
            nodoIterador.setBack(elemento);
            
            tamanho += 1; 
        }
            
        
        
    }
        
    /**
     * Insere um elemento no final da lista
     * @param elemento a ser inserido
     */
    private void insereNoFinal(Nodo elemento){
        //...[cauda]--->[elemento]
        cauda.setNext(elemento);
        
        //...[cauda]<--->[elemento]
        elemento.setBack(cauda);
        
        //...[nodo]<--->[cauda]
        cauda = elemento;
        
        tamanho += 1;
    }
    
    /**
     * Insere um elemento no inicio da lista
     * @param elemento a ser inserido
     */
    private void insereNoInicio(Nodo elemento){            
            //[elemento]--->[cabeca]...
            elemento.setNext(cabeca);
            
            //[elemento]<--->[cabeca]...
            cabeca.setBack(elemento);
            
            //[cabeca]<--->[nodo]...
            cabeca = elemento;    
            
            tamanho += 1;
    }
    
    /**
     * Remove um elemento na lista
     * @param elemento
     * @return true caso a lista nao esteja vazia
     *         false caso a lista esteja vazia ou o elemento e nulo, ou nao existe
     */
    public boolean remove(Nodo elemento){
        
        if(elemento == null || cabeca == null)
            return false;
        
        
        if(elemento.compareTo(cabeca)==0)
            return removePrimeiro();
        
        if(elemento.compareTo(cauda)==0)
            return removeUltimo();
        
        Nodo nodoIterador = cabeca.getNext();
        
        while(nodoIterador != null && nodoIterador.compareTo(elemento)!=0)
            nodoIterador = nodoIterador.getNext();
        
        if(nodoIterador == null)
            return false;
        
        
        Nodo nodoAnterior = nodoIterador.getBack();
        
        nodoAnterior.setNext(nodoIterador.getNext());
        nodoAnterior.getNext().setBack(nodoAnterior);
        nodoIterador.setNext(null);
        nodoIterador.setBack(null);
        
        tamanho -= 1;
        return true;
    }
    /**
     * Remove o primeiro elemento da lista
     * 
     * @return true caso a lista nao esteja vazia
     *         false caso contrario
     */
    public boolean removePrimeiro(){
        
        if(cabeca == null)
            return false;
        
        Nodo novaCabeca = cabeca.getNext();
        
        //caso so tenha um no
        if(cauda.compareTo(cabeca)==0)
            cauda = null;
        
        if(novaCabeca != null)
            //[cabeca]--->[cabeca]    null<---[proximo]        
            novaCabeca.setBack(null);        

        //[cabeca]--->null    null<---[proximo]        
        cabeca.setNext(null);

        cabeca = novaCabeca;        
        
        
        tamanho -= 1;
        
        return true;
        
    }
    
    /**
     * Remove o ultimo elemento
     * @return true caso a lista nao esteja vazia
     *         false caso contrario
     */
    public boolean removeUltimo(){
        if(cauda == null)
            return false;
        
        Nodo novaCauda = cauda.getBack();
        
        //caso so tenha um no
        if(cauda.compareTo(cabeca)==0)
            cabeca = null;
        
        if(novaCauda != null)
            //[novaCauda]--->null    [anterior]<---[cauda]        
            novaCauda.setNext(null);        

        //[novaCauda]--->null    null<---[cauda]        
        cauda.setBack(null);

        cauda = novaCauda;
        
        
        
        
        tamanho -= 1;
        
        return true;
    }
    
    /**
     * Procura um nodo na lista
     * @param nodo a ser procurado
     * @return o nodo encontrado
     *         null caso o nodo nao exista
     */
    public Nodo procura(Nodo nodo){
        
        Nodo iterador = this.cabeca;
        
        while(iterador != null && iterador.compareTo(nodo) != 0)
            iterador = iterador.getNext();    
        
        return iterador;        
        
        
    }
    
    
    @Override
    public String toString(){
        String impressao = "";
        
        if(cabeca == null)
            return "[]";
        
        //ver depois utilizando o hashCode
        if(cabeca.compareTo(cauda)==0)
            return cabeca.toString();



        impressao += cabeca.toString();

        Nodo nodoIterador = cabeca.getNext();

        while(nodoIterador != null){
            impressao += "-->"+nodoIterador.toString();
            nodoIterador = nodoIterador.getNext();
        }
        
        return impressao;
        
    }
    
    /**
     * Verifica se a lista esta vazia
     */
    public boolean estaVazio(){
        return tamanho == 0;
    }
     
}