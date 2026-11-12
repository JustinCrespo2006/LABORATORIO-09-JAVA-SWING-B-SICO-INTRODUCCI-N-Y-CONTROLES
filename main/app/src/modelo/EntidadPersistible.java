package modelo;

public interface EntidadPersistible {
    // Define cómo se guardará el objeto en una línea del archivo de texto
    String toFormatoArchivo();
    
    // Define el texto que se mostrará en el cuadro de diálogo
    String toResumen();
}