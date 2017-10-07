package com.algonquincollege.dosh0005.hilo_game;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Harsh Doshi - dosh0005
 * Date: 2017-10-05
 * Purpose : HiLo Game - Assignment - fun
 */

public class MainActivity extends AppCompatActivity {

    private static String ABOUT_DIALOG_TAG = "About Dialog";
    private LinearLayout layout_init;
    private RelativeLayout layout_game;
    private Button btn1, btn2, btn3, btn4;
    private EditText plname, plGuess;
    private TextView gr, gu;
    private GameHiLo game;

    // onCreate function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize game
        // connect xml with java element
        init_app();

        // create onClickListeners

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game_start(plname.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                short g = (short) Integer.parseInt(plGuess.getText().toString());
                if (validateUsername(g)) {
                    game.userAttempt(g);
                    game_stat();
                    Toast.makeText(getApplicationContext()
                            , game_msg()
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game_restart();
            }
        });

        // Listner for Long Click Event

        btn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                String ans = "Answer : " + String.valueOf(game.getRandomNumber());
                Toast.makeText(getApplicationContext()
                        , ans
                        , Toast.LENGTH_SHORT).show();
                game_restart();
                return true;
            }
        });


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_init.setVisibility(LinearLayout.VISIBLE);
                layout_game.setVisibility(RelativeLayout.GONE);
                plname.setText("");
            }
        });

    }

    // set main menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    // set event on menu item selected.

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menuAbout:
                DialogFragment aboutFragment = new AboutDialogFragment();
                aboutFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
                break;
            case R.id.menuAppInfo:
                Toast.makeText(getApplicationContext()
                        , "App Ver 2.0"
                        , Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    // initialize game function
    // connect xml with java element

    private void init_app() {
        layout_init = (LinearLayout) findViewById(R.id.layout_init);
        layout_game = (RelativeLayout) findViewById(R.id.layout_game);

        plname = (EditText) findViewById(R.id.getPlayerName);
        plGuess = (EditText) findViewById(R.id.plGuess);

        plname.setText("");
        plGuess.setText("");

        btn1 = (Button) findViewById(R.id.btnStart);
        btn2 = (Button) findViewById(R.id.btn_guess);
        btn3 = (Button) findViewById(R.id.btn_reset);
        btn4 = (Button) findViewById(R.id.btn_restart);

        gr = (TextView) findViewById(R.id.guess_remain);
        gu = (TextView) findViewById(R.id.guess_used);

        layout_init.setVisibility(LinearLayout.VISIBLE);
        layout_game.setVisibility(RelativeLayout.GONE);
    }

    // game start function

    private void game_start(String playerName) {
        layout_init.setVisibility(LinearLayout.GONE);
        layout_game.setVisibility(RelativeLayout.VISIBLE);
        plGuess.setText("");
        game = new GameHiLo(playerName);
        game_stat();

    }

    // game restart function

    private void game_restart() {
        plGuess.setText("");
        game.gameReset();
        game_stat();
    }

    // set game player status

    private void game_stat() {
        gr.setText(String.valueOf(game.getGuessRemain()));
        gu.setText(String.valueOf(game.getUserInutCount()));
    }

    // set message string

    private String game_msg() {
        String caseString = game.getRMsg();
        String rs = "";
        switch (caseString) {
            case "winm1":
                rs = getString(R.string.winm1);
                break;
            case "winm2":
                rs = getString(R.string.winm2);
                break;
            case "fail1":
                rs = getString(R.string.fail1);
                break;
            case "fail2":
                rs = getString(R.string.fail2);
                break;
            case "fail3":
                rs = getString(R.string.fail3);
                break;
            default:
                rs = getString(R.string.fail3);
        }

        return rs;
    }

    // user input validation

    private boolean validateUsername(short userInput) {
        if (userInput > 1000) {
            plGuess.setError(getString(R.string.guess_validation_Err));
            plGuess.requestFocus();
            return false;
        }
        return true;
    }


}
