import java.util.ArrayList;
import java.util.List;

// Clase Libro
class Libro {
    private int id;
    private String titulo;

    public Libro(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Libro{id=" + id + ", titulo='" + titulo + "'}";
    }
}

// Interfaz LibroRepository
interface LibroRepository {
    void agregarLibro(Libro libro);
    Libro obtenerLibro(int id);
    List<Libro> obtenerTodosLosLibros();
    void EliminarLibro(int id);
    void ActualizarLibro(Libro libro);
}

// Implementación de LibroRepository
class LibroRepositoryImpl implements LibroRepository {
    private List<Libro> libros = new ArrayList<>();

    @Override
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    @Override
    public Libro obtenerLibro(int id) {
        return libros.stream().filter(libro -> libro.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Libro> obtenerTodosLosLibros() {
        return new ArrayList<>(libros);
    }

    @Override

    public void EliminarLibro(int id) {
        libros.removeIf(Libro -> Libro.getId()== id);
    }


    @Override
    public void ActualizarLibro(Libro libro) {
        for (int i =0; i < libros.size(); i++) {
            if (libros.get(i).getId() == libro.getId()) {
                libros.set(i, libro);
                return;
            }
        }
    }

}

// Clase principal
public class Main {
    public static void main(String[] args) {
        LibroRepository libroRepo = new LibroRepositoryImpl() {

        };

        libroRepo.agregarLibro(new Libro(1, "1984"));
        libroRepo.agregarLibro(new Libro(2, "Cien años de soledad"));

        System.out.println("Todos los libros:");
        libroRepo.obtenerTodosLosLibros().forEach(System.out::println);


        System.out.println("Libro con ID 1: " + libroRepo.obtenerLibro(1));
    }
}