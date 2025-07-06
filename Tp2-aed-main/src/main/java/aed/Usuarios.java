package aed;
import aed.Heap.Handle;

public class Usuarios {
    private Heap<Usuario>.Handle[] usuarios;
    private Heap<Usuario> heap;

    @SuppressWarnings("unchecked")
    public Usuarios(int cantUsuarios) {
        this.usuarios = (Heap<Usuario>.Handle[]) new Heap.Handle[cantUsuarios];
        this.heap = new Heap<>();

        for (int i = 0; i < cantUsuarios; i++) {
            Usuario u = new Usuario(i + 1);
            this.usuarios[i] = heap.agregar(u);
        }
    }

    public void actualizarUsuario(int id, int montoASumar) {
        Heap<Usuario>.Handle handle = usuarios[id - 1];
        Usuario u = handle.getValor();
        u.aumentarSaldo(montoASumar);
        heap.reubicar(handle);
    }

    public int maximoTenedor() {
        return heap.estaVacio() ? -1 : heap.verMaximo().obtenerId();
    }
}