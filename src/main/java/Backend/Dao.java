package Backend;

import java.util.List;
import java.util.Optional;

public interface Dao<T>{
    List<T> obtenerTodos();
    boolean agregar(T t);
    boolean actualizar(T t);
    boolean borrar(int id);
    Articulos obtenerArticulo(int id);
}
