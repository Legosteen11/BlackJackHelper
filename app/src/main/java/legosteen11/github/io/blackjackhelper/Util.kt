package legosteen11.github.io.blackjackhelper

import android.content.Context
import android.widget.Toast

/**
 * Created by wouter on 11/28/17.
 */
fun Context.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}