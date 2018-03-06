package com.example.toshiba.bohnanzagamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity  {
    EditText testTextEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testTextEdit = (EditText) findViewById(R.id.testText);
        Button runTestButton = (Button)findViewById(R.id.testButton);
        runTestButton.setOnClickListener(new buttonListener());
    }


    private class buttonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Card redBean = new Card("Red Bean");
            Card[] cardArray = {redBean};

            testTextEdit.setText("Actions:\n");

            BohnanzaState firstInstance = new BohnanzaState();
            BohnanzaPlayerState[] firstInstancePL = firstInstance.getPlayerList();
            BohnanzaState secondInstance = new BohnanzaState(firstInstance, 0);

            testTextEdit.append("Give player1 10 coins to buy a 3rd bean field\n");
            firstInstancePL[0].setCoins(10);
            firstInstance.buyThirdField(0);

            testTextEdit.append("Plant 2 beans from Player 1's hand to Player 1's bean field 2\n");
            firstInstance.plantBean(0, 1, firstInstancePL[0].getHand());
            firstInstance.plantBean(0, 1, firstInstancePL[0].getHand());

            testTextEdit.append
                    ("Plant 1 bean from Player 1's hand to Player 1's bean field 1, then harvest it\n");
            firstInstance.plantBean(0, 0, firstInstancePL[0].getHand());
            firstInstance.harvestField(0, firstInstancePL[0].getField(0));

            testTextEdit.append("Player 1 turns over two cards that are up for trade\n");
            firstInstance.turn2Cards(0);

            testTextEdit.append("Player 1 opens trading for any remaining trade cards\n");
            firstInstance.startTrading(0);

            testTextEdit.append("Player 3 makes an offer 1 Red bean to player 1\n");
            firstInstance.makeOffer(2, cardArray);

            testTextEdit.append("Player 2 will choose not to trade\n");
            firstInstance.abstainFromTrading(1);

            testTextEdit.append("Player 1 accepts Player 2's offer\n");
            //firstInstance.acceptOffer(0, 2);

            testTextEdit.append("Player 1 ends their turn\n");
            firstInstance.draw3Cards(0);

            testTextEdit.append("\nInstance 1:\n");
            testTextEdit.append(firstInstance.toString());
            testTextEdit.append("\nInstance 2:");
            testTextEdit.append(secondInstance.toString());

        }
    }
}

