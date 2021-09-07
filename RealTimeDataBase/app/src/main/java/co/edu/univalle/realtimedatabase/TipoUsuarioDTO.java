package co.edu.univalle.realtimedatabase;

public class TipoUsuarioDTO {
    public Long codigo;
    public String nombre;


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString (){
        return codigo + "-" + nombre;
    }
}
