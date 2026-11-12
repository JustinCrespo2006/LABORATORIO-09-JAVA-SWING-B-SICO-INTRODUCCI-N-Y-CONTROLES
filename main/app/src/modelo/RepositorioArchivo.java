package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo<T extends EntidadPersistible> {
    private final String rutaArchivo;
    private final List<T> registrosEnMemoria;

    public RepositorioArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.registrosEnMemoria = new ArrayList<>();
    }

    public void guardar(T entidad) throws PersistenciaArchivoException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(entidad.toFormatoArchivo());
            writer.newLine();
            registrosEnMemoria.add(entidad);
        } catch (IOException e) {
            // Encapsulamos la IOException en nuestra excepción de dominio
            throw new PersistenciaArchivoException(
                    "No se pudo escribir el registro en el archivo '" + rutaArchivo + "'.", e
            );
        }
    }

    public List<String> leerHistorialArchivo() throws PersistenciaArchivoException {
        List<String> lineas = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return lineas;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            throw new PersistenciaArchivoException(
                    "Error al leer el historial desde el archivo '" + rutaArchivo + "'.", e
            );
        }
        return lineas;
    }

    public List<T> getRegistrosEnMemoria() {
        return registrosEnMemoria;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }
}