package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;
    private ListaEnlazada<Transaccion>.Handle handle;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) { // O(1)
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

    public Transaccion(Transaccion transaccion) { // O(1)
        this.id = transaccion.id_transaccion();
        this.id_comprador = transaccion.id_comprador();
        this.id_vendedor = transaccion.id_vendedor();
        this.monto = transaccion.monto();
    }

    @Override
    public int compareTo(Transaccion otra) { // O(1)
        if (this.monto > otra.monto) return 1;
        if (this.monto < otra.monto) return -1;
        if (this.id > otra.id) return 1;
        if (this.id < otra.id) return -1;
        return 0;
    }

    public boolean equals(Object otra) { // O(1)
        boolean otraIsNull=(otra==null);
        boolean claseDistinta=otra.getClass()!=this.getClass();
        if (otraIsNull||claseDistinta){
            return false;
        }
        Transaccion otraTx=(Transaccion) otra;

        return otraTx.id==id;
    }
    public int id_transaccion(){ // O(1)
        return id;
    }

    public int monto() { // O(1)
        return monto;
    }

    public int id_comprador() { // O(1)
        return id_comprador;
    }
    
    public int id_vendedor() { // O(1)
        return id_vendedor;
    }

    public boolean esCreacion(){ // O(1) 
        return id_comprador==0;
    }
    
    public void setHandle(ListaEnlazada<Transaccion>.Handle handle) {// O(1)
        this.handle = handle;
    }

    public ListaEnlazada<Transaccion>.Handle getHandle() {// O(1)
        return this.handle;
    }
}
