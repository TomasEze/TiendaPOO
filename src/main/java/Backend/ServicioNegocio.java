import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioNegocio {
    private Dao articuloDao;
    private List<String> registrarOperaciones;

    public ServicioNegocio(Dao articuloDao) {
        this.articuloDao = articuloDao;
        this.registrarOperaciones = new ArrayList<>();
    }

    public boolean agregarArticulo(Articulos articulo) {
        boolean resultado = articuloDao.agregar(articulo);
        if (resultado) {
            registrarOperaciones.add("Agregado");
        }
        return resultado;
    }

    public Articulos obtenerArticulo(int id) {
        return articuloDao.obtenerArticulo(id);
    }

    public List<Articulos> obtenerTodos() {
        return articuloDao.obtenerTodos();
    }

    public boolean eliminarArticulo(int id) {
        Articulos articulo = obtenerArticulo(id);
        boolean resultado = articuloDao.borrar(id);
        if (resultado && articulo != null) {
            registrarOperaciones.add("Eliminado");
        }
        return resultado;
    }

    private void registrarOperacion(String operacion, Articulos articulo) {
        LocalDateTime ahora = LocalDateTime.now();
        String registro = ahora + "-" + operacion + ": " + articulo.toString();
        registrarOperaciones.add(registro);
    }

    public List<String> getRegistrarOperaciones() {
        return registrarOperaciones;
    }

    public boolean generarReporte(String rutaArchivo) {
        try (BufferedWriter lector = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String registro : registrarOperaciones) {
                lector.write(registro);
                lector.newLine();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
