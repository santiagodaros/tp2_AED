package aed;
import java.util.ArrayList;

public class Usuarios {
    private ArrayList<Heap<Usuario>.Handle> usuarios;
    private Heap<Usuario> heap;

    public Usuarios(int cantUsuarios) { // O(P)
        Usuario[] usuarios = new Usuario[cantUsuarios]; // O(1)
        this.usuarios = new ArrayList<>(cantUsuarios); // O(1)
        for (int i = 0; i < cantUsuarios; i++) { // O(P)
            Usuario u = new Usuario(i + 1); //O(1)
            usuarios[i] = u; //O(1)
        }
        heap = new Heap<Usuario>(usuarios,this.usuarios); // O(P)
    }

    public void actualizarUsuario(int id, int montoASumar) { // O(log p)
        Heap<Usuario>.Handle handle = usuarios.get(id - 1); // O(1)
        Usuario u = handle.getValor(); // O(1)
        u.aumentarSaldo(montoASumar); // O(1)
        heap.reubicar(handle); // O(log p)
    }

    public int maximoTenedor() { // O(1)
        return heap.estaVacio() ? -1 : heap.verMaximo().obtenerId(); // O(1)
    }
}
