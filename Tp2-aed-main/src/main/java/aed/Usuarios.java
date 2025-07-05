package aed;

public class Usuarios {
    private Usuario[] usuarios;
    private Heap<Usuario> heap;

    public Usuarios(int cantUsuarios) {
        this.usuarios = new Usuario[cantUsuarios];
        this.heap = new Heap<>();

        for (int i = 0; i < cantUsuarios; i++) {
            Usuario u = new Usuario(i + 1);
            usuarios[i] = u;
            heap.agregar(u);
        }
    }

    public void actualizarUsuario(int id, int montoASumar) {
        Usuario u = usuarios[id - 1];
        u.aumentarSaldo(montoASumar);

        // reconstrucción del heap para mantener orden (O(p))
        heap = new Heap<>();
        for (Usuario usuario : usuarios) {
            heap.agregar(usuario);
        }
    }

    public int maximoTenedor() {
        return heap.estaVacio() ? -1 : heap.verMaximo().obtenerId();
    }
}
