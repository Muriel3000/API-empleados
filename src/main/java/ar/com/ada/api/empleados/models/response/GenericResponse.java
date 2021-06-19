package ar.com.ada.api.empleados.models.response;

public class GenericResponse {

    public GenericResponse() {
    }

    public GenericResponse(int id, boolean isOk){
        this.id = id;
        this.isOk = isOk;
    }

    public String message;
    public int id;
    public boolean isOk;
}
