package co.edu.univalle.jsondemo.dto;

import java.util.List;

public class ConsultarEstudiantesPositivaDTO implements  IRespuestaDTO{
private String codigoServicio;
private String tipoRespuesta;
private List<EstudianteDTO>respuesta;

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getTipoRespuesta() {
        return tipoRespuesta;
    }

    public void setTipoRespuesta(String tipoRespuesta) {
        this.tipoRespuesta = tipoRespuesta;
    }

    public List<EstudianteDTO> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<EstudianteDTO> respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String obtenerTipoRespuesta() {
        return tipoRespuesta;
    }
}
