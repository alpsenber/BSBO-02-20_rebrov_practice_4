package ru.mirea.rebrov.thread;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import ru.mirea.rebrov.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        Log.d("ThreadProject", String.format("Запущен поток студентом группы %s номер по списку No %s ", "БСБО-02-20", "15"));
                        int work_days = Integer.parseInt(String.valueOf(binding.editTextTextPersonName.getText()));
                        int pars = Integer.parseInt(String.valueOf(binding.editTextTextPersonName2.getText()));
                        runOnUiThread(new Runnable() {
                            @Override

                            public void run() {
                                binding.textView.setText(String.format("среднее количество пар в день = %s", pars/work_days));
                            }});
                        Log.d("ThreadProject", "Поток завершил работу");
                    }
                });
                thread.start();
            }
        });
    }
}