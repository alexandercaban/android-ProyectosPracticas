package co.edu.univalle.jsondemo.dominio;

import java.util.List;
import co.edu.univalle.jsondemo.dto.EstudianteDTO;
import co.edu.univalle.jsondemo.dto.TipoEstudianteDto;

public interface IEstudiante {
    public List<TipoEstudianteDto> consultarTodosTiposEstudiantes()throws Exception;
    public void ingresarSistema(String usuario, String clave,int codigoTipoEstudiante ) throws Exception;
    List<EstudianteDTO> consultarEstudiantesRetrofit() throws Exception;


}
