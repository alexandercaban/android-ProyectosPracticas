package co.edu.univalle.jsondemo.dto;

public class RespuestaNegativa implements  IRespuestaDTO{

    private String codigoServicio;
    private String tipoRespuesta;
    private String respuesta;


    public RespuestaNegativa(){

    }

    public RespuestaNegativa(String respuesta){
        this.codigoServicio="0";
        this.tipoRespuesta="1";
        this.respuesta=respuesta;
    }

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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String obtenerTipoRespuesta() {
        return respuesta;
    }
}
