package co.edu.univalle.jsondemo.dominio;

import android.os.AsyncTask;

import co.edu.univalle.jsondemo.dto.ConsultarEstudiantesPositivaDTO;
import co.edu.univalle.jsondemo.dto.IRespuestaDTO;
import co.edu.univalle.jsondemo.dto.RespuestaNegativa;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConexionServicioRetrofit extends
                AsyncTask<String, Integer, IRespuestaDTO> {
    @Override
    protected IRespuestaDTO doInBackground(String... parametros) {

        try{
            //1.Crear una instancia de retrofit
            Retrofit retrofit= new Retrofit.Builder().
                                baseUrl(parametros[0]).
                    addConverterFactory(GsonConverterFactory.create()).build();

            //2. Crear una instancia  de la interfaz para realizar el request
            // al servicio

            IEstudianteRetrofitService iEstudianteRetrofitService = retrofit.
                    create(IEstudianteRetrofitService.class);

            //3. Invocar el comportamiento definido en la interfaz haciendo uso de
            // la interfaz que provee retrofit
            Call<ConsultarEstudiantesPositivaDTO> call=  iEstudianteRetrofitService.
                                                    consultarEstudiantes(parametros[1]);

            //4. retornar la respuesta del consumo del servicio
                return call.execute().body();

        }catch(Exception exception){
                return new RespuestaNegativa("Error consultando el servicio retrofit"+exception.getMessage());
        }

    }
}
