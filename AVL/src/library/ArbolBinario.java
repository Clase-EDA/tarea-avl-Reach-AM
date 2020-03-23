package library;

public interface ArbolBinario<T extends Comparable> {
	void insertar(T elemento);
	void eliminar(T elemento);
	NodoAVL busca(T elemento);
	String porNivel();
}
