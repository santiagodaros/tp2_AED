package aed;
import java.util.ArrayList;

public class Usuarios {
    private ArrayList<Heap<Usuario>.Handle> usuarios;
    private Heap<Usuario> heap;

    public Usuarios(int cantUsuarios) {
        this.usuarios = new ArrayList<>(cantUsuarios);
        this.heap = new Heap<>();

        for (int i = 0; i < cantUsuarios; i++) {
            Usuario u = new Usuario(i + 1);
            Heap<Usuario>.Handle handle = heap.agregar(u);
            usuarios.add(handle);
        }
    }

    public void actualizarUsuario(int id, int montoASumar) {
        Heap<Usuario>.Handle handle = usuarios.get(id - 1);
        Usuario u = handle.getValor();
        u.aumentarSaldo(montoASumar);
        heap.reubicar(handle);
    }

    public int maximoTenedor() {
        return heap.estaVacio() ? -1 : heap.verMaximo().obtenerId();
    }
}
