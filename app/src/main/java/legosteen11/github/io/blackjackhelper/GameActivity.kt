package legosteen11.github.io.blackjackhelper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.github.legosteen11.blackjacklib.Game
import io.github.legosteen11.blackjacklib.game.Card
import io.github.legosteen11.blackjacklib.game.GameType
import kotlinx.android.synthetic.main.activity_game.*
import legosteen11.github.io.blackjackhelper.cards.CardViewAdapter

class GameActivity : AppCompatActivity() {
    val DEFAULT_GAME_TYPE = GameType.VereenvoudigdGame


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val gameType = GameType.valueOf(intent.getStringExtra("game_type"))

        val game = Game(gameType)

        updateGame(game)

        card_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CardViewAdapter(game, this@GameActivity)
        }
    }

    fun updateGame(game: Game, drawnCard: Card? = null) {
        if(game.getWon() || game.getLost())
            gameEnded(game)

        advice.text = if(game.shouldContinue())
            "Ga door"
        else
            "Stop met spelen"

        chance_of_not_losing.text = "Kans dat je verliest is ${game.chanceOfLosing().getChanceInPercent()}%"
        chance_of_not_losing.setBackgroundResource(if(game.shouldContinue())
            R.color.colorAccent
        else
            R.color.colorLost
        )

        if(drawnCard != null)
            drawn_card.text = "Getrokken kaart: $drawnCard"
        else
            drawn_card.text = "Trek een kaart."

        val scores = game.getScore()

        total_score.text = "Je ${if(scores.size > 1) "scores zijn" else "score is"}: ${scores.sortedBy { it }.joinToString()}"
    }

    fun gameEnded(game: Game) {
        startActivity(
                Intent(this, ResultActivity::class.java).apply {
                    putExtra("game_result", game)
                }
        )
    }

}
