package com.example.kotlin_tictactoe

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var activePlayer = 1
    var player1:ArrayList<Int> = ArrayList()
    var player2:ArrayList<Int> = ArrayList()
    var player1win:Int = 0
    var player2win:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun buttonSelected(view:View){
        var selected = view as Button
        var cellId = 0

        when(selected.id){
            R.id.button1 -> cellId =1
            R.id.button2 -> cellId =2
            R.id.button3 -> cellId =3
            R.id.button4 -> cellId =4
            R.id.button5 -> cellId =5
            R.id.button6 -> cellId =6
            R.id.button7 -> cellId =7
            R.id.button8 -> cellId =8
            R.id.button9 -> cellId =9
        }

        playGame(cellId,selected)
    }



    fun playGame(cellId:Int,selected:Button){

        if (activePlayer == 1){
            selected.text = "O"
            selected.setBackgroundResource(R.color.colorGreen)
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        }else{
            selected.text = "X"
            selected.setBackgroundResource(R.color.colorBlue)
            player2.add(cellId)
            activePlayer = 1
        }

        selected.isEnabled = false


        checkWinner()


    }


    private fun checkWinner(){
        var winner = 0

        //row
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){ winner = 1 }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){ winner = 2 }

        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){ winner = 1 }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){ winner = 2 }

        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){ winner = 1 }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){ winner = 2 }

        //column
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){ winner = 1 }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){ winner = 2 }

        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){ winner = 1 }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){ winner = 2 }

        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){ winner = 1 }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){ winner = 2 }

        //diagonal
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)){ winner = 1 }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)){ winner = 2 }

        if (player1.contains(3) && player1.contains(5) && player1.contains(7)){ winner = 1 }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)){ winner = 2 }

        //
        if (winner == 1){
            player1win++
            winner("Player1")

        }else if (winner == 2){
            player2win++
            winner("Player2")
        }


    }

    fun winner(name:String){

        val builder = AlertDialog.Builder(this)

        builder.setTitle("$name is the winner!!")
        builder.setMessage("player1 won = $player1win, player2 won = $player2win")
        builder.setPositiveButton("OK"){ dialog , which->
            restartGame()
        }
        val dialog:AlertDialog = builder.create()
        dialog.show()

    }

    fun autoPlay(){
        var emptyCells = ArrayList<Int>()
        for (cellId in 1..9 ){
            if (!(player1.contains(cellId)) || player2.contains(cellId)){
                emptyCells.add(cellId)
            }
        }

        val r = Random()
        var random = r.nextInt(emptyCells.size)
        var id = emptyCells[random]

        var buttonSelected:Button
        buttonSelected =
            when(id){
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> button1
            }

        playGame(id,buttonSelected)
    }

    fun restartGame(){
        activePlayer =1
        player1.clear()
        player2.clear()

        for (id in 1..9){
            var buttonSelected:Button
            buttonSelected =
                when(id){
                    1 -> button1
                    2 -> button2
                    3 -> button3
                    4 -> button4
                    5 -> button5
                    6 -> button6
                    7 -> button7
                    8 -> button8
                    9 -> button9
                    else -> button1
                }

            buttonSelected.text = null
            buttonSelected.isEnabled = true
            buttonSelected.setBackgroundResource(R.color.colorButton)
        }
    }
}
