package Backend;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ArticulosDao implements Dao<Articulos>{
    private final String CSV_File = "Articulos.csv";
    private Articulos articulos;
    private List<Articulos> listaArticulos;

    public ArticulosDao() {
        listaArticulos.add(new Articulos(12345, "Goma", 5.0, 100));
        listaArticulos.add(new Articulos(23456, "Cuaderno", 50.0, 100));
        listaArticulos.add(new Articulos(34567, "Lápiz", 15.0, 50));
        listaArticulos.add(new Articulos(45678, "Tijeras", 30.0, 25));
    }

    private Articulos parsearLinea(String linea){
        String[] datos = linea.split(",");
        if(datos.length != 4) return null;
        int id = Integer.parseInt(datos[0]);
        String nombre = datos[1];
        double precio = Double.parseDouble(datos[2]);
        int cantidad = Integer.parseInt(datos[3]);
        return new Articulos(id, nombre, precio, cantidad);
    }

    private String convertirLinea(Articulos articulos){
        return articulos.getId() + ", " + articulos.getNombre() + ", " + articulos.getPrecio() + ", " + articulos.getCantidad();
    }

    @Override
    public List<Articulos> obtenerTodos() {
        return List.of();
    }

    @Override
    public void guardar(Articulos articulos) {

    }

    @Override
    public void actualizar(Articulos articulos, String[] params) {

    }

    @Override
    public void borrar(Articulos articulos) {

    }
}
