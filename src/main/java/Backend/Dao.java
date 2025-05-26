package Backend;

import java.util.List;

public interface Dao<T>{
    List<T> obtenerTodos();
    void guardar(T t);
    void actualizar(T t, String[] params);
    void borrar(T t);
}
