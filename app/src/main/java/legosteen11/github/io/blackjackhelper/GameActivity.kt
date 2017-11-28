package legosteen11.github.io.blackjackhelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.github.legosteen11.blackjacklib.Game
import io.github.legosteen11.blackjacklib.game.GameType

import kotlinx.android.synthetic.main.activity_game.*
import legosteen11.github.io.blackjackhelper.cards.CardViewAdapter

class GameActivity : AppCompatActivity() {
    val DEFAULT_GAME_TYPE = GameType.VereenvoudigdGame


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val gameType = savedInstanceState?.get("game_type") as? GameType ?: DEFAULT_GAME_TYPE

        val game = Game(gameType)

        updateGame(game)

        card_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CardViewAdapter(game, this@GameActivity)
        }
    }

    fun updateGame(game: Game) {
        advice.text = if(game.getWon())
            "Je hebt gewonnen!" // TODO: Launch activity with score etc.
        else if(game.getLost())
            "Je hebt verloren :(" // TODO: Launch activity with score etc.
        else if(game.shouldContinue())
            "Ga door"
        else
            "Stop met spelen"

        Log.d("score:", game.getCardsLeft().joinToString())
    }

}
