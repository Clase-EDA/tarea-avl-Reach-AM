package library;

public class NodoAVL<T extends Comparable> {
	NodoAVL izq, der;
	T elemento;
	int altura;
	
	public NodoAVL() {
		altura = 0;
	}
	
	public NodoAVL(T elemento) {
		this();
		this.elemento = elemento;
	}
	
	public int fe() {
		int fe = 0;
		fe += der ==null? 0 : der.altura;
		fe -= izq == null? 0 : izq.altura; 
		return fe;
	}
}
