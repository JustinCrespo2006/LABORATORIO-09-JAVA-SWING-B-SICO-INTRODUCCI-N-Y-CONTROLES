package modelo;

import java.util.ArrayList;
import java.util.List;

public class TareaModelo {
    private final List<String> listaTareas;

    public TareaModelo() {
        this.listaTareas = new ArrayList<>();
    }

    public void agregarTarea(String descripcion) throws TareaException {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new TareaException("La descripción de la tarea no puede estar vacía.");
        }
        
        String tareaLimpia = descripcion.trim();
        if (listaTareas.contains(tareaLimpia)) {
            throw new TareaException("Esa tarea ya está anotada en la lista.");
        }

        listaTareas.add(tareaLimpia);
    }

    public void completarTarea(int indice) throws TareaException {
        if (indice < 0 || indice >= listaTareas.size()) {
            throw new TareaException("Debes seleccionar una tarea de la lista para completarla.");
        }
        listaTareas.remove(indice);
    }

    public List<String> obtenerTareas() {
        return new ArrayList<>(listaTareas);
    }
}