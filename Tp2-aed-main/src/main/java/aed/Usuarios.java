package aed;

import aed.Heap.Handle;

public class Usuarios {
    private Heap<Usuario>.Handle[] usuarios;
    private Heap<Usuario> heap;

    @SuppressWarnings("unchecked")
    public Usuarios(int cantUsuarios) {
        Usuario[] usuarios = new Usuario[cantUsuarios];

        this.usuarios = (Heap<Usuario>.Handle[]) new Heap.Handle[cantUsuarios];
        this.heap = new Heap<>();

        for (int i = 0; i < cantUsuarios; i++) {
            Usuario u = new Usuario(i + 1);
            usuarios[i] = u;
        }
        this.heap = new Heap(usuarios);

    }

    public void actualizarUsuario(int id, int montoASumar) {
        Usuario u = usuarios[id - 1].getValor();
        u.aumentarSaldo(montoASumar);
        heap.heapify(usuarios[id-1].getIndice());
    }

    public int maximoTenedor() {
        return heap.estaVacio() ? -1 : heap.verMaximo().obtenerId();
    }
}
