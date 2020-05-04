package sg.edu.np.WhackAMole;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Declare Variables
    private static final String TAG = "Whack-A-Mole";

    private TextView resultTextView;
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private List<Button> buttonList = new ArrayList<>();
    private Integer randomLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = (TextView)findViewById(R.id.resultTextView);
        Button1 = (Button)findViewById(R.id.button1);//using id of the buttons in xml
        buttonList.add(Button1);
        Button2 = (Button)findViewById(R.id.button2);
        buttonList.add(Button2);
        Button3 = (Button)findViewById(R.id.button3);
        buttonList.add(Button3);
        Log.v(TAG, "Finished Pre-initialisation.");//just to inform user
    }

    //Loaded
    @Override
    protected void onStart() {
        super.onStart();

        setNewMole();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonPressed = (Button) v;
                Log.v(TAG,"Reached");
                switch (buttonList.indexOf(buttonPressed)) {
                    case 0:
                        Log.v(TAG,"Button Left Clicked!");
                        break;
                    case 1:
                        Log.v(TAG,"Button Middle Clicked!");
                        break;
                    case 2:
                        Log.v(TAG,"Button Right Clicked!");
                        break;
                    default:
                        Log.v(TAG,"Unknown Button pressed. Found within ButtonList");
                }

                Integer score = Integer.parseInt(resultTextView.getText().toString());
                switch (buttonPressed.getText().toString()) {
                    case "0":
                        Log.v(TAG,"Missed, score deducted!");
                        score -= 1;//deduct 1 from score
                        resultTextView.setText(score.toString());
                        break;
                    case "*":
                        Log.v(TAG,"Hit, score added!");
                        score += 1;//Add 1 to score
                        resultTextView.setText(score.toString());
                        break;
                    default:
                        Log.v(TAG,"Unknown button pressed, no case for it's text set.");
                }
                resetMole();
                setNewMole();
            }
        };
        Button1.setOnClickListener(listener);
        Button2.setOnClickListener(listener);
        Button3.setOnClickListener(listener);

        Log.v(TAG,"Starting Gui.");
    }

    public void setNewMole()
    {
        Random ran = new Random();
        randomLocation = ran.nextInt(3);
        buttonList.get(randomLocation).setText("*");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG,"Resuming.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"Pausing.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"Destroying.");
    }

    public void resetMole()
    {
        buttonList.get(randomLocation).setText("0");
    }
}

