package co.edu.univalle.jsondemo.dominio;

import co.edu.univalle.jsondemo.dto.ConsultarEstudiantesPositivaDTO;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface IEstudianteRetrofitService {
    @FormUrlEncoded
    @POST("Estudiantes/ConsultarEstudiantes")
    public Call<ConsultarEstudiantesPositivaDTO> consultarEstudiantes
                                                (@Field("clave") String clave);

}
