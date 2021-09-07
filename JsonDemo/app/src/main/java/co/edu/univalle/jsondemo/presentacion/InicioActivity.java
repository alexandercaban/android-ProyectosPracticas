package co.edu.univalle.jsondemo.presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.List;
import co.edu.univalle.jsondemo.R;
import co.edu.univalle.jsondemo.dominio.EstudianteLogic;
import co.edu.univalle.jsondemo.dominio.IEstudiante;
import co.edu.univalle.jsondemo.dto.TipoEstudianteDto;

public class InicioActivity  extends AppCompatActivity {
    private EditText txtUsuario;
    private EditText txtClave;
    private RadioGroup rgTipoEstudiantes;
    private int tipoEstudiante;


   public void onCreate(Bundle bundle){
       super.onCreate(bundle);
       setContentView(R.layout.activity_inicio);
       txtUsuario=findViewById(R.id.txtUsuario);
       txtClave=findViewById(R.id.txtClave);
       rgTipoEstudiantes= findViewById(R.id.rgTipoEstudiantes);
       cargarTipoEstudiantes();
       rgTipoEstudiantes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
                tipoEstudiante = checkedId;
           }
       });
   }


   public void cargarTipoEstudiantes(){

        try{
            IEstudiante iEstudiante= new EstudianteLogic();
            List<TipoEstudianteDto> lTipoEstudiantes = iEstudiante.consultarTodosTiposEstudiantes();

            for (int i=0; i<lTipoEstudiantes.size();i++){
                RadioButton radioButtonTemporal= new RadioButton(this);
                radioButtonTemporal.setText(new Integer(lTipoEstudiantes.get(i).getCodigo())==1?
                                                        AppContext.getContext().getString(R.string.lblPregrado):"postgrado");
                radioButtonTemporal.setId(new Integer(lTipoEstudiantes.get(i).getCodigo()));
                rgTipoEstudiantes.addView(radioButtonTemporal);
            }

        }catch (Exception exception){
            Log.e("ERROR","Error"+ exception.getMessage());
        }
   }

   public void accionIngresarSistema(View view){

        try{
            IEstudiante iEstudiante= new EstudianteLogic();
            iEstudiante.ingresarSistema(txtUsuario.getText()+"",
                                        txtClave.getText()+"",
                                                tipoEstudiante);

            Log.i("INFO","Se logueo con éxito");
            Intent i = new Intent(this, PrincipalActivity.class);
            startActivity(i);

        }catch(Exception exception){
                new MensajeDialog(exception.getMessage()).show(getSupportFragmentManager(),"");
        }
   }

}
