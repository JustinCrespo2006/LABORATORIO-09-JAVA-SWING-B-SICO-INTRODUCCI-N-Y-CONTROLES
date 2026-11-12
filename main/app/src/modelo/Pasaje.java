package modelo;

import java.util.List;

public class Pasaje implements EntidadPersistible {
    private String nombres;
    private String documentoIdentidad;
    private String fechaViaje;
    private String origen;
    private String destino;
    private String piso;
    private List<String> serviciosOpcionales;
    private String calidadServicio;

    public Pasaje(String nombres, String documentoIdentidad, String fechaViaje,
                  String origen, String destino, String piso,
                  List<String> serviciosOpcionales, String calidadServicio) 
                  throws DatosPasajeInvalidosException {
        
        validarDatos(nombres, documentoIdentidad, fechaViaje, origen, destino, piso, calidadServicio);

        this.nombres = nombres;
        this.documentoIdentidad = documentoIdentidad;
        this.fechaViaje = fechaViaje;
        this.origen = origen;
        this.destino = destino;
        this.piso = piso;
        this.serviciosOpcionales = serviciosOpcionales;
        this.calidadServicio = calidadServicio;
    }

    private void validarDatos(String nombres, String documento, String fecha,
                              String origen, String destino, String piso, String calidad) 
                              throws DatosPasajeInvalidosException {
        
        if (nombres == null || nombres.isEmpty()) {
            throw new DatosPasajeInvalidosException("El campo de 'Nombres' no puede estar vacío.");
        }
        // Valida que contenga solo dígitos (entre 8 y 12 caracteres)
        if (documento == null || !documento.matches("\\d{8,12}")) {
            throw new DatosPasajeInvalidosException("El 'Documento de Identidad' debe contener entre 8 y 12 dígitos numéricos.");
        }
        // Valida formato dd/mm/aaaa mediante expresión regular
        if (fecha == null || !fecha.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")) {
            throw new DatosPasajeInvalidosException("La 'Fecha de Viaje' debe tener un formato válido (dd/mm/aaaa).");
        }
        if (origen == null || origen.startsWith("--") || destino == null || destino.startsWith("--")) {
            throw new DatosPasajeInvalidosException("Debe elegir un lugar de origen y uno de destino en los cuadros combinados.");
        }
        if (origen.equals(destino)) {
            throw new DatosPasajeInvalidosException("El lugar de origen y el de destino no pueden ser la misma ciudad.");
        }
        if (piso == null) {
            throw new DatosPasajeInvalidosException("Debe seleccionar si desea viajar en el 1er o 2do piso.");
        }
        if (calidad == null) {
            throw new DatosPasajeInvalidosException("Debe seleccionar una calidad de servicio de la lista (Económico, Standard o VIP).");
        }
    }

    @Override
    public String toFormatoArchivo() {
        String servicios = serviciosOpcionales.isEmpty() 
                ? "Ninguno" 
                : String.join(", ", serviciosOpcionales);
        return String.format("%s | Doc: %s | Fecha: %s | Ruta: %s -> %s | Piso: %s | Calidad: %s | Extras: [%s]",
                nombres, documentoIdentidad, fechaViaje, origen, destino, piso, calidadServicio, servicios);
    }

    @Override
    public String toResumen() {
        String servicios = serviciosOpcionales.isEmpty() 
                ? "Ninguno" 
                : String.join(", ", serviciosOpcionales);

        return "=== RESUMEN DE COMPRA DE PASAJE ===\n\n"
             + "• Pasajero: " + nombres + "\n"
             + "• Documento de Identidad: " + documentoIdentidad + "\n"
             + "• Fecha de Viaje: " + fechaViaje + "\n"
             + "• Lugar de Origen: " + origen + "\n"
             + "• Lugar de Destino: " + destino + "\n"
             + "• Nivel del Bus: " + piso + "\n"
             + "• Servicios Opcionales: " + servicios + "\n"
             + "• Calidad de Servicio: " + calidadServicio + "\n";
    }
}