package legosteen11.github.io.blackjackhelper.cards

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.github.legosteen11.blackjacklib.Game
import android.view.LayoutInflater
import android.widget.Button
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.card_view.view.*
import legosteen11.github.io.blackjackhelper.GameActivity
import legosteen11.github.io.blackjackhelper.R


/**
 * Created by wouter on 11/28/17.
 */
class CardViewAdapter(val game: Game, val gameActivity: GameActivity): RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder = CardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
    )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = game.gameType.deck.getCardsWithoutDuplicates()[position]

        holder.button.text = card.name
        holder.button.setOnClickListener {
            game.drewCard(card)
            if(!game.getCardsLeft().contains(card))
                it.isEnabled = false
            gameActivity.drawn_card.text = "Getrokken kaart: $card"
            gameActivity.updateGame(game)
        }
    }

    override fun getItemCount(): Int = game.gameType.deck.getCardsWithoutDuplicates().size

    class CardViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var button = view.card_select as Button
    }
}