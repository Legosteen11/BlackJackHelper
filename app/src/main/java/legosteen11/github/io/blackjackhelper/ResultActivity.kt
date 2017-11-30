package legosteen11.github.io.blackjackhelper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.github.legosteen11.blackjacklib.Game
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        game = intent.getSerializableExtra("game_result") as Game

        // set background color
        val backgroundColor = if(game.getWon()) R.color.colorWin else R.color.colorLost
        findViewById<View>(android.R.id.content).setBackgroundResource(backgroundColor)

        val scores = game.getScore()

        game_result.text = if(game.getWon()) "Gewonnen!" else "Verloren..."

        score.text = "Je ${if(scores.size > 1) "scores zijn" else "score is"}: ${scores.sortedBy { it }.joinToString()}"
        cards.text = "Je kaarten waren: ${game.getPlayersCards().joinToString { "${it.name} (${it.values.joinToString()})" }}"
    }

    override fun onBackPressed() {
        startActivity(
                Intent(this, MainActivity::class.java)
        )
    }

}
