package co.edu.univalle.jsondemo.presentacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import co.edu.univalle.jsondemo.R;
import co.edu.univalle.jsondemo.dominio.EstudianteLogic;
import co.edu.univalle.jsondemo.dominio.IEstudiante;
import co.edu.univalle.jsondemo.dto.EstudianteDTO;

public class PrincipalActivity  extends AppCompatActivity {

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_principal);
    }

    public void accionConsultarEstudiantes(View view){
        try {
            IEstudiante iEstudiante = new EstudianteLogic();
            List<EstudianteDTO> lEstudiante = iEstudiante.consultarEstudiantesRetrofit();
        }catch (Exception exception){
            new MensajeDialog(exception.getMessage()).show(getSupportFragmentManager(), "");
        }
    }
}
