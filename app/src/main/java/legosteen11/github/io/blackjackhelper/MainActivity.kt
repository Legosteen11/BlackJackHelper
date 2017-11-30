package legosteen11.github.io.blackjackhelper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import io.github.legosteen11.blackjacklib.game.GameType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game_type_select.adapter = ArrayAdapter<GameType>(this, android.R.layout.simple_list_item_1, GameType.values())
        //game_type_select.setSelection(0)

        new_game_button.setOnClickListener {
            startNewGame()
        }
    }

    fun getGameType() = game_type_select.selectedItem as GameType

    fun startNewGame() {
        startActivity(
                Intent(this, GameActivity::class.java).apply {
                    putExtra("game_type", getGameType().name)
                }
        )
    }

}
