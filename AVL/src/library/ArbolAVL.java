package library;

import Queues.*;

public class ArbolAVL<T extends Comparable> implements ArbolBinario<T> {
	NodoAVL raiz = new NodoAVL();
	int tamaño;
	
	public ArbolAVL() {
		tamaño = 0;
	}
	
	public ArbolAVL(T elemento) {
		raiz = new NodoAVL(elemento);
		tamaño = 1;
	}
	
	@Override
	public void insertar(T elemento) {
		raiz = insertar(elemento, raiz);
		tamaño++;
	}
	
	private NodoAVL insertar(T elemento, NodoAVL n) {
		if(n == null) n = new NodoAVL(elemento);
		else{
			NodoAVL s;
			if(elemento.compareTo(n.elemento) < 0) n.izq = insertar(elemento, n.izq);
			else n.der = insertar(elemento, n.der);
			if(Math.abs(n.fe()) >= 2) n = rotar(n);
		}
		n.altura = Math.max(alt(n.izq), alt(n.der)) + 1;
		return n;
	}
	
	private NodoAVL rotar(NodoAVL n) {
		if(n.fe() <= -2) {
			if(!(n.izq.fe() > 0)) {
				NodoAVL r = n.izq;
				n.izq= r.der;
				r.der = n;
				n.altura = Math.max(alt(n.izq), alt(n.der) ) + 1;
				r.altura = Math.max(alt(r.izq), alt(n) ) + 1;
				return r;	
			}else {
				n.izq = rotar(n.izq);
				return rotar(n);
			}
		}else {
			if(n.der.fe() < 0) {
				n.der = rotar(n.der);
				return rotar(n);
				
			}else {
				NodoAVL r = n.der;
				n.der = r.izq;
				r.izq = n;
				n.altura = Math.max(alt(n.izq), alt(n.der) ) + 1;
				r.altura = Math.max(alt(r.izq), alt(n) ) + 1;
				return r;
			
			}
		
		}
	}
	
	private int alt(NodoAVL n) {
		return n==null? 0 : n.altura;
	}
	
	@Override
	public void eliminar(T elemento) {
		if(busca(elemento) != null) {
			raiz = eliminar(elemento, raiz);
			tamaño--;
		}
	}
	
	private NodoAVL eliminar(T elemento, NodoAVL n) {
		if(n==null) return n;
		else if(elemento.compareTo(n.elemento) < 0) n.izq = eliminar(elemento, n.izq);
		else if(elemento.compareTo(n.elemento) > 0) n.der = eliminar(elemento, n.der);
		else{
			if(n.izq != null && n.der != null) {
				NodoAVL sucesor = nextInOrd(n);
				n.elemento = sucesor.elemento;
				n.der = eliminar((T)sucesor.elemento, n.der);
			}else if(n.izq != null ^ n.der != null) {
				if(n.izq != null) n = n.izq;
				else n = n.der;
			} else {
				return null;
			}
		}
		if(raiz != null) {
			n.altura = Math.max(alt(n.izq), alt(n.der) ) + 1;
			if(Math.abs(n.fe()) >= 2) n = rotar(n);
		}
		return n;
	}
	
	private NodoAVL nextInOrd(NodoAVL n) {
		NodoAVL s = n.der;
		while(s.izq != null) s = s.izq;
		return s;
	}
	
	@Override
	public NodoAVL busca(T elemento) {
		return busca(elemento, raiz);
	}
	
	private NodoAVL busca(T elemento, NodoAVL n) {
		if(n != null && n.elemento.compareTo(elemento) == 0) return n;
		else if(n.izq != null && n.elemento.compareTo(elemento) < 0) return busca(elemento, n.izq);
		else if(n.der != null && n.elemento.compareTo(elemento) > 0) return busca(elemento, n.der);
		return null; 
	}
	@Override
	public String porNivel() {
		StringBuilder a = new StringBuilder("ÁrbolAVL de tamaño " + tamaño + ": [");
		CircularArrayQueue<NodoAVL> q = new CircularArrayQueue<>();
		if(raiz != null) q.enqueue(raiz);
		while(!q.isEmpty()) {
			NodoAVL n = q.dequeue();
			String s = "(" + n.elemento + " : " + n.fe() + "), ";
			a.append(s);
			if(n.izq != null) q.enqueue(n.izq);
			if(n.der != null) q.enqueue(n.der);
		}
		return a.toString().substring(0,a.toString().length()-2) + "]";
	}
}
