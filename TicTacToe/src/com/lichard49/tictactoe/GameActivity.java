package com.lichard49.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends Activity implements OnClickListener
{
	private boolean xTurn = true;
	
	private Button[][] buttons;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe_layout);
        
        buttons = new Button[3][3];
        buttons[0][0] = (Button) findViewById(R.id.pos11);
        buttons[0][0].setOnClickListener(this);
        buttons[0][1] = (Button) findViewById(R.id.pos12);
        buttons[0][1].setOnClickListener(this);
        buttons[0][2] = (Button) findViewById(R.id.pos13);
        buttons[0][2].setOnClickListener(this);
        
        buttons[1][0] = (Button) findViewById(R.id.pos21);
        buttons[1][0].setOnClickListener(this);
        buttons[1][1] = (Button) findViewById(R.id.pos22);
        buttons[1][1].setOnClickListener(this);
        buttons[1][2] = (Button) findViewById(R.id.pos23);
        buttons[1][2].setOnClickListener(this);
        
        buttons[2][0] = (Button) findViewById(R.id.pos31);
        buttons[2][0].setOnClickListener(this);
        buttons[2][1] = (Button) findViewById(R.id.pos32);
        buttons[2][1].setOnClickListener(this);
        buttons[2][2] = (Button) findViewById(R.id.pos33);
        buttons[2][2].setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }


	@Override
	public void onClick(View v) 
	{
		Button pressedButton = (Button) v;
		if(xTurn)
			pressedButton.setText("X");
		else
			pressedButton.setText("O");
		pressedButton.setEnabled(false);
		
		for(int x = 0; x < 3; x++)
		{
			if(evaluateRow(x) || evaluateColumn(x) || evaluateDiagnol())
			{
				if(xTurn)
					Toast.makeText(getApplicationContext(), "X won!", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(getApplicationContext(), "O won!", Toast.LENGTH_SHORT).show();
				break;
			}
		}
		
		xTurn = !xTurn;
	}
	
	private boolean evaluateRow(int x)
	{
		String pos1 = buttons[x][0].getText().toString();
		String pos2 = buttons[x][1].getText().toString();
		String pos3 = buttons[x][2].getText().toString();
		return !pos1.equals("_") && pos1.equals(pos2) && pos1.equals(pos3);
	}
	
	private boolean evaluateColumn(int x)
	{
		String pos1 = buttons[0][x].getText().toString();
		String pos2 = buttons[1][x].getText().toString();
		String pos3 = buttons[2][x].getText().toString();
		return !pos1.equals("_") && pos1.equals(pos2) && pos1.equals(pos3);
	}
	
	private boolean evaluateDiagnol()
	{
		String pos11 = buttons[0][0].getText().toString();
		String pos22 = buttons[1][1].getText().toString();
		String pos33 = buttons[2][2].getText().toString();
		
		String pos31 = buttons[2][0].getText().toString();
		String pos13 = buttons[0][2].getText().toString();
		
		return !pos22.equals("_") && pos11.equals(pos22) && pos11.equals(pos33)
				|| pos31.equals(pos22) && pos31.equals(pos13);
	}
}
