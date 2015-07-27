package braincollaboration.colormaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class LevelParent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_mode_normal);
    }

}
