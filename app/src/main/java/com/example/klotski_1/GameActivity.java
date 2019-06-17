package com.example.klotski_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    private int cur_layout[][];
    private int step = 0;
    private int best_step = 0;
    private TextView myStep;
    private TextView bestStep;
    private View.OnTouchListener lisetner;
    Button lastStepButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myStep = findViewById(R.id.current_step_textview);
        myStep.setText(Integer.toString(step));
        bestStep = findViewById(R.id.best_step_textview);
        bestStep.setText(Integer.toString(best_step));
        resetGameFunction();
        gameTouch();
    }

    private void successCheck() {
        if (cur_layout[4][1] == 2 && cur_layout[4][2] == 2) {
            if(step < best_step || best_step == 0){
                best_step = step;
                bestStep.setText(Integer.toString(best_step));
                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(GameActivity.this);
                myAlertBuilder.setTitle(R.string.alert_title);
                myAlertBuilder.setMessage(R.string.alert_message1);
                myAlertBuilder.setPositiveButton("重新开始", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                resetGameFunction();
                            }
                        });
                myAlertBuilder.setNegativeButton("返回", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                myAlertBuilder.show();
            }
            else {
                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(GameActivity.this);
                myAlertBuilder.setTitle(R.string.alert_title);
                myAlertBuilder.setMessage(R.string.alert_message2);
                myAlertBuilder.setPositiveButton("重新开始", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                resetGameFunction();
                            }
                        });
                myAlertBuilder.setNegativeButton("返回", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                myAlertBuilder.show();
            }
        }
    }
    public void resetGame(View view) {
        resetGameFunction();
    }

    public void backToMain(View view) {
        backFunction();
    }

    private void resetGameFunction() {
        step = 0;
        myStep.setText(Integer.toString(step));
        lastStepButton.setEnabled(false);
        initLayout();
    }

    private void backFunction() {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void initLayout() {
        cur_layout = new int[][]{{1, 2, 2, 3}, {1, 2, 2, 3}, {4, 5, 5, 6}, {4, 8, 9, 6}, {7, 0, 0, 10}};
    }
    private boolean canMove(int figure, int direction) {
        int[] position = new int[4];
        boolean get_strat = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (cur_layout[i][j] == figure) {
                    if (!get_strat) {
                        position[0] = i;
                        position[1] = j;
                        get_strat = true;
                    }
                    position[2] = i;
                    position[3] = j;
                }
            }
        }
        for (int i = position[0]; i <= position[2]; i++) {
            for (int j = position[1]; j <= position[3]; j++) {
                switch (direction) {
                    case 0: {
                        if (i - 1 < 0) {
                            return false;
                        } else if (cur_layout[i-1][j] != figure && cur_layout[i-1][j] != 0) {
                            return false;
                        }
                        break;
                    }
                    case 1: {
                        if (i + 1 > 4) {
                            return false;
                        } else if (cur_layout[i+1][j] != figure && cur_layout[i+1][j] != 0) {
                            return false;
                        }
                        break;
                    }
                    case 2: {
                        if (j - 1 < 0) {
                            return false;
                        } else if (cur_layout[i][j-1] != figure && cur_layout[i][j-1] != 0) {
                            return false;
                        }
                        break;
                    }
                    case 3: {
                        if (j + 1 > 3) {
                            return false;
                        } else if (cur_layout[i][j+1] != figure && cur_layout[i][j+1] != 0) {
                            return false;
                        }
                        break;
                    }
                }
            }
        }
        lastStepButton.setEnabled(true);
        return true;
    }
    private void moveView(int figure, int direction, int view_id, int margin_start, int margin_top) {
        if (!canMove(figure, direction)) return;
        ConstraintSet set = new ConstraintSet();
        int distance = 80;
        switch (direction) {
            case 0: {
                set.setMargin(view_id, ConstraintSet.TOP, margin_top - distance);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (cur_layout[i][j] == figure) {
                            cur_layout[i-1][j] = figure;
                            cur_layout[i][j] = 0;
                        }
                    }
                }
                break;
            }
            case 1: {
                set.setMargin(view_id, ConstraintSet.TOP, margin_top + distance);
                for (int i = 4; i >= 0; i--) {
                    for (int j = 0; j < 4; j++) {
                        if (cur_layout[i][j] == figure) {
                            cur_layout[i+1][j] = figure;
                            cur_layout[i][j] = 0;
                        }
                    }
                }
                break;
            }
            case 2: {
                set.setMargin(view_id, ConstraintSet.START, margin_start - distance);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (cur_layout[i][j] == figure) {
                            cur_layout[i][j-1] = figure;
                            cur_layout[i][j] = 0;
                        }
                    }
                }
                break;
            }
            case 3: {
                set.setMargin(view_id, ConstraintSet.START, margin_start + distance);
                for (int i = 0; i < 5; i++) {
                    for (int j = 3; j >= 0; j--) {
                        if (cur_layout[i][j] == figure) {
                            cur_layout[i][j+1] = figure;
                            cur_layout[i][j] = 0;
                        }
                    }
                }
                break;
            }
        }
        step++;
        myStep.setText(Integer.toString(step));
        successCheck();
    }

    public void gameTouch() {
        lisetner = new View.OnTouchListener() {
            float start_x = (float) 0;
            float start_y = (float) 0;
            int figure;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    start_x = event.getX();
                    start_y = event.getY();

                    switch (v.getId()) {
                        case R.id.zhangfei1:
                            figure = 1;
                            break;
                        case R.id.caocao:
                            figure = 2;
                            break;
                        case R.id.machao1:
                            figure = 3;
                            break;
                        case R.id.huangzhong1:
                            figure = 4;
                            break;
                        case R.id.guanyu2:
                            figure = 5;
                            break;
                        case R.id.zhaoyun1:
                            figure = 6;
                            break;
                        case R.id.zu1:
                            figure = 7;
                            break;
                        case R.id.zu2:
                            figure = 8;
                            break;
                        case R.id.zu3:
                            figure = 9;
                            break;
                        case R.id.zu4:
                            figure = 10;
                            break;
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    float distance_x = event.getX() - start_x;
                    float distance_y = event.getY() - start_y;

                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                    int margin_start = params.getMarginStart();
                    int margin_top = params.topMargin;

                    if (distance_y < 0 && distance_x*distance_x < distance_y*distance_y) {
                        moveView(figure, 0, v.getId(), margin_start, margin_top);
                    } else if (distance_y > 0 && distance_x*distance_x < distance_y*distance_y) {
                        moveView(figure, 1, v.getId(), margin_start, margin_top);
                    } else if (distance_x < 0 && distance_x*distance_x > distance_y*distance_y) {
                        moveView(figure, 2, v.getId(), margin_start, margin_top);
                    } else if (distance_x > 0 && distance_x*distance_x > distance_y*distance_y) {
                        moveView(figure, 3, v.getId(), margin_start, margin_top);
                    }
                }
                return true;
            }
        };

    }

}