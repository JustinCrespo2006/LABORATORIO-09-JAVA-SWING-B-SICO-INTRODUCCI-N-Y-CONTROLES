package modelo;

public class PersistenciaArchivoException extends Exception {
    public PersistenciaArchivoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}