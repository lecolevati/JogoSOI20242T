package br.edu.fateczl.aulasoi20242t;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private Button btnJogar;
    private Button btnReinicio;
    private TextView tvResultado;
    private int valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etNumero = findViewById(R.id.etNumero);
        etNumero.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnJogar = findViewById(R.id.btnJogar);
        btnReinicio = findViewById(R.id.btnReinicio);
        btnReinicio.setVisibility(View.INVISIBLE);
        tvResultado = findViewById(R.id.tvResultado);
        tvResultado.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        valor = (int)((Math.random() * 100) + 1);
        Log.i("Valor", String.valueOf(valor));
        btnJogar.setOnClickListener(click -> jogar());
        btnReinicio.setOnClickListener(click -> reinicio());
    }

    public void reinicio() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    public void jogar() {
        int numeroEntrada = Integer.parseInt(etNumero.getText().toString());
        etNumero.setText("");
        if (numeroEntrada > valor) {
            tvResultado.setText(R.string.menor);
        } else if (numeroEntrada < valor) {
            tvResultado.setText(R.string.maior);
        } else {
            String acertou = getString(R.string.acertou) + valor;
            tvResultado.setText(acertou);
            btnReinicio.setVisibility(View.VISIBLE);
            Toast.makeText(this, R.string.parabens, Toast.LENGTH_LONG).show();
        }
    }
}