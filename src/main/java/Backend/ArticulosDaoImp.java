import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ArticulosDaoImp implements Dao<Articulos>{
    private final String CSV_File = "Artículos.csv";
    private Articulos articulos;
    private List<Articulos> listaArticulos;
    
    //Método auxiliar que convierte líneas CSV a un objeto de tipo Artículos
    private Articulos parsearLinea(String linea){
        String[] datos = linea.split(",");
        if(datos.length != 4) return null;
        int id = Integer.parseInt(datos[0]);
        String nombre = datos[1];
        double precio = Double.parseDouble(datos[2]);
        int cantidad = Integer.parseInt(datos[3]);
        return new Articulos(id, nombre, precio, cantidad);
    }

    //Método auxiliar que convierte un objeto de Articulos a una línea CSV
    private String convertirLinea(Articulos articulos){
        return articulos.getId() + ", " + articulos.getNombre() + ", " + articulos.getPrecio() + ", " + articulos.getCantidad();
    }

    public ArticulosDaoImp() { //Método constructor de productos
        listaArticulos.add(new Articulos(12345, "Goma", 5.0, 100));
        listaArticulos.add(new Articulos(23456, "Lápiz", 10.0, 100));
        listaArticulos.add(new Articulos(34567, "Pluma", 15.0, 150));
        listaArticulos.add(new Articulos(45678, "Tijeras escolares", 25.0, 110));
    }

    @Override
    public List<Articulos> obtenerTodos() {
        List<Articulos> lista = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(CSV_File))){
            String linea;
            while ((linea = lector.readLine()) != null){
                Articulos articulo = parsearLinea(linea);
                if (articulo != null){
                    lista.add(articulo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean agregar(Articulos articulos) {
        try (BufferedWriter lector = new BufferedWriter(new FileWriter(CSV_File))){
            lector.write(convertirLinea(articulos));
            lector.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Articulos articulos) {
        //Lee todos los artículos, los actualiza y reescribe el archivo completo
        List<Articulos> lista = obtenerTodos();
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == articulos.getId()) {
                lista.set(i, articulos);
                encontrado = true;
                break;
            }
        }
        if (encontrado){
            try (BufferedWriter lector = new BufferedWriter(new FileWriter(CSV_File))){
                for (Articulos articulo : lista) {
                    lector.write(convertirLinea(articulo));
                    lector.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean borrar(int id) {
        // Lee todos los artículos, elimina el que coincide con la id y reescribe el archivo
        List<Articulos> lista = obtenerTodos();
        boolean eliminado = lista.removeIf(articulos -> articulos.getId() == id);
        if (eliminado){
            try (BufferedWriter lector = new BufferedWriter(new FileWriter(CSV_File))){
                for (Articulos articulo : lista) {
                    lector.write(convertirLinea(articulo));
                    lector.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Articulos obtenerArticulo(int id) {
        try (BufferedReader lector = new BufferedReader(new FileReader(CSV_File))){
            String linea;
            while ((linea = lector.readLine()) != null){
                Articulos articulos = parsearLinea(linea);
                if (articulos != null && articulos.getId() == id){
                    return articulos;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
