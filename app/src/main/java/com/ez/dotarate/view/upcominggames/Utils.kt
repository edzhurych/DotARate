package com.ez.dotarate.view.upcominggames

import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ez.domain.model.Opponent
import com.ez.dotarate.App
import com.ez.dotarate.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


fun parseGameDate(gameDate: String?): String = gameDate?.let {
    val splitDate = gameDate.substringBefore("T").split("-")

    val sb = StringBuilder()
    for (i in splitDate.indices.reversed()) {
        sb.append(splitDate[i])
        if (i != 0) sb.append(".")
    }

    sb.toString()
} ?: "-"


/**
 * Game Time
 */
fun parseGameTime(gameTime: String?) = gameTime?.let {
    val formatToDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    formatToDate.timeZone = TimeZone.getTimeZone("UTC")
    val date = formatToDate.parse(gameTime)

    val formatFromDate = SimpleDateFormat("HH:mm", Locale.US)
    formatFromDate.format(date!!)
} ?: "-"


/**
 * Number of Games
 */
fun parseNumberOfGames(numberOfGames: Int?): String {
    val context = App.applicationContext()

    return String.format(context.getString(R.string.upcoming_games_screen_bo), numberOfGames)
}

/**
 * Team Name
 */
fun parseTeamName(listOpponents: ArrayList<Opponent>?, teamNumber: Int): String? {
    val context = App.applicationContext()

    if (listOpponents.isNullOrEmpty()) {
        return context.getString(R.string.upcoming_games_screen_tbd)
    }

    return when (listOpponents.size) {
        1 -> {
            if (teamNumber == 0) {
                if (listOpponents[0].opponent.acronym != null) listOpponents[0].opponent.acronym
                else listOpponents[0].opponent.name
            } else context.getString(R.string.upcoming_games_screen_tbd)
        }
        2 -> {
            if (listOpponents[teamNumber].opponent.acronym != null) listOpponents[teamNumber].opponent.acronym
            else listOpponents[teamNumber].opponent.name
        }
        else -> context.getString(R.string.upcoming_games_screen_tbd)
    }
}


/**
 * Load Team Logo
 */
fun parseTeamLogo(listOpponents: ArrayList<Opponent>?, teamNumber: Int): String? {
       if (listOpponents.isNullOrEmpty()) {
        return null
    }

    return when (listOpponents.size) {
        1 -> {
            if (teamNumber == 0) listOpponents[0].opponent.image_url
            else null
        }
        2 -> listOpponents[teamNumber].opponent.image_url
        else -> null
    }
}
