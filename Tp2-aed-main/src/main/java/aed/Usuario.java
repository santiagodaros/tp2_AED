package aed;

public class Usuario implements Comparable<Usuario> {
    private int id;
    private int saldo;

    public Usuario(int id) { // O(1)
        this.id = id;
        this.saldo = 0;
    }

    public int obtenerId() { // O(1)
        return id;
    }

    public int obtenerSaldo() { // O(1)
        return saldo;
    }

    public void aumentarSaldo(int monto) { // O(1)
        saldo += monto;
    }

    @Override
    public int compareTo(Usuario otro) { // O(1)
        if (this.saldo > otro.saldo) return 1;
        if (this.saldo < otro.saldo) return -1;
        if (this.id < otro.id) return 1;
        if (this.id > otro.id) return -1;
        return 0;

    }
}
