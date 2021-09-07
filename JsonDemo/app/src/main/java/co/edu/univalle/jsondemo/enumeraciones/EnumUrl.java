package co.edu.univalle.jsondemo.enumeraciones;

public enum EnumUrl {
    URL_BASE("http://192.168.82.131:8080/"),
    URL_TIPO_ESTUDIANTES("http://192.168.82.131:8080/usbcaliservice/Estudiantes/ConsultarTipoEstudiante"),
    ACCEDER_SISTEMA("usbcaliservice/IniciarAplicacion"),
    URL_USB("usbcaliservice/");

public String urlBase;

    private EnumUrl(String urlBase){
        this.urlBase= urlBase;
    }

    public String getUrlBase() {
        return urlBase;
    }

}
