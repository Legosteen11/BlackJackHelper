package legosteen11.github.io.blackjackhelper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.legosteen11.blackjacklib.Game
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        game = intent.getSerializableExtra("game_result") as Game

        val scores = game.getScore()

        game_result.text = if(game.getWon()) "Gewonnen!" else "Verloren..."

        score.text = "Je ${if(scores.size > 1) "scores zijn" else "score is"}: ${scores.sortedBy { it }.joinToString()}"
        cards.text = "Je kaarten waren: ${game.getPlayersCards().joinToString()}"
    }

    override fun onBackPressed() {
        startActivity(
                Intent(this, MainActivity::class.java)
        )
    }

}
