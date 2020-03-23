package library;

public class Main {

	public static void main(String[] args) {
		ArbolAVL aa = new ArbolAVL(8);
		aa.insertar(4);		aa.insertar(12);
		aa.insertar(2);		aa.insertar(6);
		aa.insertar(10);		aa.insertar(14);
		aa.insertar(1);		aa.insertar(3);
		aa.insertar(5);		aa.insertar(7);
		aa.insertar(9);		aa.insertar(11);
		aa.insertar(13);		aa.insertar(15);
		System.out.println(aa.porNivel());
		aa.eliminar(8);
		System.out.println(aa.porNivel());
		aa.eliminar(9);
		System.out.println(aa.porNivel());
		aa.eliminar(10);
		System.out.println(aa.porNivel());
		aa.eliminar(9);
		System.out.println(aa.porNivel());
	}
	
}
