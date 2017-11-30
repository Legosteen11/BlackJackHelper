package legosteen11.github.io.blackjackhelper.cards

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import io.github.legosteen11.blackjacklib.Game
import io.github.legosteen11.blackjacklib.game.Card
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

        updateCardView(card, holder)

        holder.button.setOnClickListener {
            game.drewCard(card)

            updateCardView(card, holder)

            gameActivity.updateGame(game, card)
        }
    }

    fun updateCardView(card: Card, holder: CardViewHolder) {
        holder.button.text = "${card.name} (${game.getPlayersCards().count { it == card }}x)"
        holder.button.isEnabled = game.getCardsLeft().contains(card)
    }

    override fun getItemCount(): Int = game.gameType.deck.getCardsWithoutDuplicates().size

    class CardViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var button = view.card_select as Button
    }
}