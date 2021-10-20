package com.example.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var btTopL: Button
    lateinit var btTop: Button
    lateinit var btTopR: Button
    lateinit var btMidL: Button
    lateinit var btMid: Button
    lateinit var btMidR: Button
    lateinit var btBotL: Button
    lateinit var btBot: Button
    lateinit var btBotR: Button

    lateinit var tvPlayAgain: TextView
    lateinit var tvWinner: TextView
    lateinit var winnerAnimation: Animation

    lateinit var cvPlayer1: CardView
    lateinit var cvPlayer2: CardView

    lateinit var tvP1Text: TextView
    lateinit var tvP2Text: TextView

    lateinit var buttons: List<Button>
    var playerOneTurn = true
    var draw = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPlayAgain = findViewById(R.id.tvPlayAgain)
        tvWinner = findViewById(R.id.tvWinner)
        winnerAnimation = AnimationUtils.loadAnimation(this, R.anim.winner_animation)

        cvPlayer1 = findViewById(R.id.cvPlayer1)
        cvPlayer2 = findViewById(R.id.cvPlayer2)

        tvP1Text = findViewById(R.id.tvP1Text)
        tvP2Text = findViewById(R.id.tvP2Text)

        cvPlayer1.setBackgroundColor(Color.MAGENTA)

        btTopL = findViewById(R.id.btTopL)
        btTop = findViewById(R.id.btTop)
        btTopR = findViewById(R.id.btTopR)
        btMidL = findViewById(R.id.btMidL)
        btMid = findViewById(R.id.btMid)
        btMidR = findViewById(R.id.btMidR)
        btBotL = findViewById(R.id.btBotL)
        btBot = findViewById(R.id.btBot)
        btBotR = findViewById(R.id.btBotR)

        buttons = listOf(btTopL, btTop, btTopR, btMidL, btMid, btMidR, btBotL, btBot, btBotR)

        for(btn in buttons){addOnClickListener(btn)}
    }

    fun addOnClickListener(button: Button){
        button.setOnClickListener {
            if(!tvWinner.isVisible){
                if(button.text.isBlank()){
                    if(playerOneTurn){
                        button.text = "X"
                        if(checkWin()){
                            gameOver(1)
                        }else{
                            playerOneTurn = false
                            cvPlayer2.setBackgroundColor(Color.MAGENTA)
                            cvPlayer1.setBackgroundColor(Color.WHITE)
                        }
                    }else{
                        button.text = "O"
                        if(checkWin()){
                            gameOver(2)
                        }else{
                            playerOneTurn = true
                            cvPlayer1.setBackgroundColor(Color.MAGENTA)
                            cvPlayer2.setBackgroundColor(Color.WHITE)
                        }
                    }
                }else{
                    Toast.makeText(this, "Please choose another tile", Toast.LENGTH_LONG).show()
                }
            }
            draw = true
            for(button in buttons){
                if(button.text.isBlank()){draw = false}
            }
            if(draw){gameOver(0)}
        }
    }
    fun gameOver(player: Int){
        if(player>0){
            tvWinner.text = "Player $player Wins!"
        }else{
            tvWinner.text = "Draw"
        }
        tvWinner.isVisible = true
        tvWinner.startAnimation(winnerAnimation)
        tvPlayAgain.isVisible = true
        tvP1Text.text = "YES"
        cvPlayer1.setOnClickListener { this.recreate() }
        tvP2Text.text = "NO"
        cvPlayer1.setBackgroundColor(Color.BLUE)
        cvPlayer2.setBackgroundColor(Color.BLUE)
    }
    fun checkWin(): Boolean{
        if(btTopL.text == btTop.text && btTopL.text == btTopR.text && btTopL.text.isNotBlank()){return true}
        if(btMidL.text == btMid.text && btMidL.text == btMidR.text && btMidL.text.isNotBlank()){return true}
        if(btBotL.text == btBot.text && btBotL.text == btBotR.text && btBotL.text.isNotBlank()){return true}
        if(btTopL.text == btMidL.text && btTopL.text == btBotL.text && btTopL.text.isNotBlank()){return true}
        if(btTop.text == btMid.text && btTop.text == btBot.text && btTop.text.isNotBlank()){return true}
        if(btTopR.text == btMidR.text && btTopR.text == btBotR.text && btTopR.text.isNotBlank()){return true}
        if(btTopL.text == btMid.text && btTopL.text == btBotR.text && btTopL.text.isNotBlank()){return true}
        if(btBotL.text == btMid.text && btBotL.text == btTopR.text && btBotL.text.isNotBlank()){return true}
        return false
    }
}