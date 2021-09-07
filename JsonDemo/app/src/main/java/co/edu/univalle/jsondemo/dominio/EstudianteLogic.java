package co.edu.univalle.jsondemo.dominio;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Arrays;
import java.util.List;
import co.edu.univalle.jsondemo.dto.ConsultarEstudiantesPositivaDTO;
import co.edu.univalle.jsondemo.dto.EstudianteDTO;
import co.edu.univalle.jsondemo.dto.IRespuestaDTO;
import co.edu.univalle.jsondemo.dto.RespuestaNegativa;
import co.edu.univalle.jsondemo.dto.TipoEstudianteDto;
import co.edu.univalle.jsondemo.enumeraciones.EnumUrl;

public class EstudianteLogic implements IEstudiante {

    private static final String URL_ACCEDER_SISTEMA;
    private static final String  URL_CONSULTAR_ESTUDIANTES;

    static{
        URL_ACCEDER_SISTEMA = EnumUrl.URL_BASE.getUrlBase()+
                              EnumUrl.ACCEDER_SISTEMA.getUrlBase();

        URL_CONSULTAR_ESTUDIANTES= EnumUrl.URL_BASE.getUrlBase()+
                                   EnumUrl.URL_USB.getUrlBase();

    }

    @Override
    public List<TipoEstudianteDto> consultarTodosTiposEstudiantes() throws Exception {

        List<TipoEstudianteDto> lTipoEstudiantes=null;

        try{
            TipoEstudianteConexionRestLogic tipoEstudianteConexionRestLogic = new TipoEstudianteConexionRestLogic();
            tipoEstudianteConexionRestLogic.execute(EnumUrl.URL_TIPO_ESTUDIANTES.getUrlBase());
            String respuestaConsumo= tipoEstudianteConexionRestLogic.get();

            if(respuestaConsumo == null || respuestaConsumo.equals("")){
                throw new Exception ("Inconvenientes al consultar los tipos de estudiantes");
            }

            Gson gson = new Gson();
            TipoEstudianteDto []  losTipoEstudiantes = gson.fromJson(respuestaConsumo,TipoEstudianteDto[].class);
            lTipoEstudiantes = Arrays.asList(losTipoEstudiantes);


        }catch (Exception exception){
                throw exception;
        }


        return lTipoEstudiantes;
    }

    @Override
    public void ingresarSistema(String usuario, String clave, int codigoTipoEstudiante) throws Exception {

        try{
            AccesoConexionRestLogic accesoConexionRestLogic= new AccesoConexionRestLogic();
            accesoConexionRestLogic.execute(URL_ACCEDER_SISTEMA,
                                                    usuario,clave,
                                                    codigoTipoEstudiante+"");
            String respuestaJson= accesoConexionRestLogic.get();
            Gson gson = new Gson();

            JsonParser jsonParser= new JsonParser();
            JsonElement  arbolJson = jsonParser.parse(respuestaJson);

            if(arbolJson.isJsonObject()){
                JsonObject jsonObject= arbolJson.getAsJsonObject();
                JsonElement tipoRespuesta = jsonObject.get("tipoRespuesta");

                if(tipoRespuesta!=null && tipoRespuesta.getAsString().equals("1")){
                    RespuestaNegativa respuestaNegativa= gson.fromJson(respuestaJson,RespuestaNegativa.class);
                    throw new Exception (respuestaNegativa.getRespuesta());
                }
            }

        }catch (Exception exception){
            throw exception;
        }
    }

    @Override
    public List<EstudianteDTO> consultarEstudiantesRetrofit() throws Exception {
        List <EstudianteDTO> lEstudiantesDTO=null;

        try{
            IRespuestaDTO respuestaDTO= new ConexionServicioRetrofit().
                    execute(URL_CONSULTAR_ESTUDIANTES,"123").get();
            String respuestaServicio =  respuestaDTO.obtenerTipoRespuesta();

            if(respuestaServicio.equalsIgnoreCase("0")){
              lEstudiantesDTO= ((ConsultarEstudiantesPositivaDTO)respuestaDTO).getRespuesta();
            } else{
                throw new Exception(((RespuestaNegativa)respuestaDTO).getRespuesta());
            }

        }catch(Exception exception){
            throw exception;
        }


        return lEstudiantesDTO;
    }
}
