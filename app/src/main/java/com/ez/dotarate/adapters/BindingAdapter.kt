package com.ez.dotarate.adapters

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.RecyclerView
import com.ez.domain.model.*
import com.ez.dotarate.App
import com.ez.dotarate.R
import com.ez.dotarate.constants.*
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


object BindingAdapter {

    @BindingAdapter("url", "errorImage")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?, errorImage: Drawable) {
        if (TextUtils.isEmpty(url))
            view.setImageResource(R.drawable.ic_empty_avatar)
        else
            Picasso.get().load(url).placeholder(errorImage).error(
                errorImage
            ).into(view)
    }

    /**
     * Set Leaderboard Rank
     */
    @BindingAdapter("leaderboardRank")
    @JvmStatic
    fun setLeaderboardRank(view: TextView, leaderboardRank: Int?) {
        if (leaderboardRank == null) {
            return

        } else {
            view.text = leaderboardRank.toString()
        }
    }

    /**
     * Set Region
     */
    @BindingAdapter("region")
    @JvmStatic
    fun setRegion(view: TextView, region: Int) {

        val context = App.applicationContext()

        when (region) {
            EUROPE_WEST -> view.text = context.getString(R.string.europeWest)
            RUSSIA -> view.text = context.getString(R.string.russia)
            EUROPE_EAST -> view.text = context.getString(R.string.europeEast)
        }
    }

    /**
     * Set Winner
     */
    @BindingAdapter("winner")
    @JvmStatic
    fun setWinner(view: TextView, radiantWin: Boolean) {

        val context = App.applicationContext()

        if (radiantWin) {
            view.setTextColor(context.getColor(R.color.colorRadiant))
            view.text = context.getString(R.string.gameScreenRadiantVictory)
        } else {
            view.setTextColor(context.getColor(R.color.colorDire))
            view.text = context.getString(R.string.gameScreenDireVictory)
        }
    }

    /**
     * Set Duration
     */
    @BindingAdapter("duration")
    @JvmStatic
    fun setDuration(view: TextView, duration: Int) {

        val minutes = duration / 60

        val seconds = duration % 60

        val sb = StringBuilder()
        sb.append(minutes).append(":")

        if (seconds < 10) sb.append("0$seconds")
        else sb.append(seconds)

        view.text = sb.toString()
    }

    /**
     * Set Hero Image
     */
    @BindingAdapter("android:src")
    @JvmStatic
    fun findHeroImage(view: ImageView, heroID: Int) {

        when (heroID) {
            ANTI_MAGE -> view.setImageResource(R.drawable.anti_mage)
            AXE -> view.setImageResource(R.drawable.axe)
            BANE -> view.setImageResource(R.drawable.bane)
            BLOODSEEKER -> view.setImageResource(R.drawable.bloodseeker)
            CRYSTAL_MAIDEN -> view.setImageResource(R.drawable.crystal_maiden)
            DROW_RANGER -> view.setImageResource(R.drawable.drow_ranger)
            EARTHSHAKER -> view.setImageResource(R.drawable.earthshaker)
            JUGGERNAUT -> view.setImageResource(R.drawable.juggernaut)
            MORPHLING -> view.setImageResource(R.drawable.morphling)
            MIRANA -> view.setImageResource(R.drawable.mirana)
            SHADOW_FIEND -> view.setImageResource(R.drawable.sf)
            PHANTOM_LANCER -> view.setImageResource(R.drawable.phantom_lancer)
            PUCK -> view.setImageResource(R.drawable.puck)
            PUDGE -> view.setImageResource(R.drawable.pudge)
            RAZOR -> view.setImageResource(R.drawable.razor)
            SAND_KING -> view.setImageResource(R.drawable.sand_king)
            STORM -> view.setImageResource(R.drawable.storm)
            SVEN -> view.setImageResource(R.drawable.sven)
            TINY -> view.setImageResource(R.drawable.tiny)
            VENGEFUL_SPIRIT -> view.setImageResource(R.drawable.vengeful_spirit)
            WINDRANGER -> view.setImageResource(R.drawable.wr)
            ZEUS -> view.setImageResource(R.drawable.zeus)
            KUNKKA -> view.setImageResource(R.drawable.kunkka)
            LINA -> view.setImageResource(R.drawable.lina)
            LION -> view.setImageResource(R.drawable.lion)
            SHADOW_SHAMAN -> view.setImageResource(R.drawable.shadow_shaman)
            SLARDAR -> view.setImageResource(R.drawable.slardar)
            TIDEHUNTER -> view.setImageResource(R.drawable.tide)
            WITCH_DOCTOR -> view.setImageResource(R.drawable.wd)
            LICH -> view.setImageResource(R.drawable.lich)
            RIKI -> view.setImageResource(R.drawable.riki)
            ENIGMA -> view.setImageResource(R.drawable.enigma)
            TINKER -> view.setImageResource(R.drawable.tinker)
            SNIPER -> view.setImageResource(R.drawable.sniper)
            NECROPHOS -> view.setImageResource(R.drawable.necr)
            WARLOCK -> view.setImageResource(R.drawable.warlock)
            BEASTMASTER -> view.setImageResource(R.drawable.beastmaster)
            QUEEN_OF_PAIN -> view.setImageResource(R.drawable.qop)
            VENOMANCER -> view.setImageResource(R.drawable.venomancer)
            VOID -> view.setImageResource(R.drawable.faceless_void)
            WRAITH_KING -> view.setImageResource(R.drawable.wraith_king)
            DEATH_PROPHET -> view.setImageResource(R.drawable.death_prophet)
            PHANTOM_ASSASSIN -> view.setImageResource(R.drawable.phantom_assassin)
            PUGNA -> view.setImageResource(R.drawable.pugna)
            TEMPLAR_ASSASSIN -> view.setImageResource(R.drawable.templar_assassin)
            VIPER -> view.setImageResource(R.drawable.viper)
            LUNA -> view.setImageResource(R.drawable.luna)
            DRAGON_KNIGHT -> view.setImageResource(R.drawable.dragon_knight)
            DAZZLE -> view.setImageResource(R.drawable.dazzle)
            CLOCKWERK -> view.setImageResource(R.drawable.clockwerk)
            LESHRAC -> view.setImageResource(R.drawable.leshrac)
            FURION -> view.setImageResource(R.drawable.furion)
            LIFESTEALER -> view.setImageResource(R.drawable.lifestealer)
            DARK_SEER -> view.setImageResource(R.drawable.dark_seer)
            CLINKZ -> view.setImageResource(R.drawable.clinkz)
            OMNI -> view.setImageResource(R.drawable.omni)
            ENCHANTRESS -> view.setImageResource(R.drawable.enchantress)
            HUSKAR -> view.setImageResource(R.drawable.huskar)
            NIGHT_STALKER -> view.setImageResource(R.drawable.night_stalker)
            BROODMOTHER -> view.setImageResource(R.drawable.broodmother)
            BOUNTY_HUNTER -> view.setImageResource(R.drawable.bounty_hunter)
            WEAVER -> view.setImageResource(R.drawable.weaver)
            JAKIRO -> view.setImageResource(R.drawable.jakiro)
            BATRIDER -> view.setImageResource(R.drawable.batrider)
            CHEN -> view.setImageResource(R.drawable.chen)
            SPECTRE -> view.setImageResource(R.drawable.spectre)
            APPARATION -> view.setImageResource(R.drawable.apparation)
            DOOM -> view.setImageResource(R.drawable.doom)
            URSA -> view.setImageResource(R.drawable.ursa)
            SPIRIT_BREAKER -> view.setImageResource(R.drawable.spirit_breaker)
            GYROCOPTER -> view.setImageResource(R.drawable.gyrocopter)
            ALCHEMIST -> view.setImageResource(R.drawable.alchemist)
            INVOKER -> view.setImageResource(R.drawable.invoker)
            SILENCER -> view.setImageResource(R.drawable.silencer)
            OUTWORLD_DEVOURER -> view.setImageResource(R.drawable.outworld_devourer)
            LYCAN -> view.setImageResource(R.drawable.lycan)
            BREWMASTER -> view.setImageResource(R.drawable.brewmaster)
            SHADOW_DEMON -> view.setImageResource(R.drawable.shadow_demon)
            LONE_DRUID -> view.setImageResource(R.drawable.lone_druid)
            CHAOS_KNIGHT -> view.setImageResource(R.drawable.chaos_knight)
            MEEPO -> view.setImageResource(R.drawable.meepo)
            TREANT -> view.setImageResource(R.drawable.treant)
            OGRE -> view.setImageResource(R.drawable.ogre)
            UNDYING -> view.setImageResource(R.drawable.undying)
            RUBICK -> view.setImageResource(R.drawable.rubick)
            DISRUPTOR -> view.setImageResource(R.drawable.disruptor)
            NYX -> view.setImageResource(R.drawable.nyx)
            NAGA_SIREN -> view.setImageResource(R.drawable.naga_siren)
            KOTL -> view.setImageResource(R.drawable.kotl)
            IO -> view.setImageResource(R.drawable.io)
            VISAGE -> view.setImageResource(R.drawable.visage)
            SLARK -> view.setImageResource(R.drawable.slark)
            MEDUSA -> view.setImageResource(R.drawable.medusa)
            TROLL -> view.setImageResource(R.drawable.troll)
            CENTAUR -> view.setImageResource(R.drawable.centaur)
            MAGNUS -> view.setImageResource(R.drawable.magnus)
            TIMBERSAW -> view.setImageResource(R.drawable.timber)
            BRISTLEBACK -> view.setImageResource(R.drawable.bristleback)
            TUSKAR -> view.setImageResource(R.drawable.tusk)
            SKY -> view.setImageResource(R.drawable.sky)
            ABADDON -> view.setImageResource(R.drawable.abbadon)
            ELDER_TITAN -> view.setImageResource(R.drawable.elder_titan)
            LEGOIN_COMANDER -> view.setImageResource(R.drawable.legion_commander)
            TECHIES -> view.setImageResource(R.drawable.techies)
            EMBER_SPIRIT -> view.setImageResource(R.drawable.ember_spirit)
            EARTH_SPIRIT -> view.setImageResource(R.drawable.earth_spirit)
            UNDERLORD -> view.setImageResource(R.drawable.underlord)
            TERRORBLADE -> view.setImageResource(R.drawable.terrorblade)
            PHOENIX -> view.setImageResource(R.drawable.phoenix)
            ORACLE -> view.setImageResource(R.drawable.oracle)
            WYVERN -> view.setImageResource(R.drawable.wyvern)
            ARC_WARDEN -> view.setImageResource(R.drawable.arc_warden)
            MONKEY_KING -> view.setImageResource(R.drawable.monkey_king)
            DARK_WILLOW -> view.setImageResource(R.drawable.dark_willow)
            PANGOLIER -> view.setImageResource(R.drawable.pango)
            GRIMSTROKE -> view.setImageResource(R.drawable.grimstroke)
            VOID_SPIRIT -> view.setImageResource(R.drawable.void_spirit)
            SNAPFIRE -> view.setImageResource(R.drawable.snapfire)
            MARS -> view.setImageResource(R.drawable.mars)
        }
    }

    /**
     * Determines Player Name
     */
    @BindingAdapter("playerName")
    @JvmStatic
    fun playerName(view: TextView, playerName: String?) {

        val context = App.applicationContext()

        if (playerName != null) {
            when {
                playerName.length in 12..15 -> view.textSize = 16F
                playerName.length in 16..19 -> view.textSize = 14F
                playerName.length in 20..29 -> view.textSize = 12F
                playerName.length >= 30 -> view.textSize = 10F
                else -> view.textSize = 18F
            }
            view.text = playerName
        } else view.text = context.getString(R.string.anonym)
    }

    /**
     * Determines Hero Name
     */
    @BindingAdapter("heroName", "from")
    @JvmStatic
    fun findHeroName(view: TextView, heroID: Int, fromId: Int) {

        val context = App.applicationContext()

        val heroName: String

        when (heroID) {
            ANTI_MAGE -> heroName = context.getString(R.string.anti_mage)
            AXE -> heroName = context.getString(R.string.axe)
            BANE -> heroName = context.getString(R.string.bane)
            BLOODSEEKER -> heroName = context.getString(R.string.bloodseeker)
            CRYSTAL_MAIDEN -> heroName = context.getString(R.string.crystal_maiden)
            DROW_RANGER -> heroName = context.getString(R.string.drow_ranger)
            EARTHSHAKER -> heroName = context.getString(R.string.earthshaker)
            JUGGERNAUT -> heroName = context.getString(R.string.juggernaut)
            MORPHLING -> heroName = context.getString(R.string.morphling)
            MIRANA -> heroName = context.getString(R.string.mirana)
            SHADOW_FIEND -> heroName = context.getString(R.string.shadow_fiend)
            PHANTOM_LANCER -> heroName = context.getString(R.string.phantom_lancer)
            PUCK -> heroName = context.getString(R.string.puck)
            PUDGE -> heroName = context.getString(R.string.pudge)
            RAZOR -> heroName = context.getString(R.string.razor)
            SAND_KING -> heroName = context.getString(R.string.sand_king)
            STORM -> heroName = context.getString(R.string.storm)
            SVEN -> heroName = context.getString(R.string.sven)
            TINY -> heroName = context.getString(R.string.tiny)
            VENGEFUL_SPIRIT -> heroName = context.getString(R.string.vengeful_spirit)
            WINDRANGER -> heroName = context.getString(R.string.windranger)
            ZEUS -> heroName = context.getString(R.string.zeus)
            KUNKKA -> heroName = context.getString(R.string.kunkka)
            LINA -> heroName = context.getString(R.string.lina)
            LION -> heroName = context.getString(R.string.lion)
            SHADOW_SHAMAN -> heroName = context.getString(R.string.shadow_shaman)
            SLARDAR -> heroName = context.getString(R.string.slardar)
            TIDEHUNTER -> heroName = context.getString(R.string.tidehunter)
            WITCH_DOCTOR -> heroName = context.getString(R.string.witch_doctor)
            LICH -> heroName = context.getString(R.string.lich)
            RIKI -> heroName = context.getString(R.string.riki)
            ENIGMA -> heroName = context.getString(R.string.enigma)
            TINKER -> heroName = context.getString(R.string.tinker)
            SNIPER -> heroName = context.getString(R.string.sniper)
            NECROPHOS -> heroName = context.getString(R.string.necrophos)
            WARLOCK -> heroName = context.getString(R.string.warlock)
            BEASTMASTER -> heroName = context.getString(R.string.beastmaster)
            QUEEN_OF_PAIN -> heroName = context.getString(R.string.queen_of_pain)
            VENOMANCER -> heroName = context.getString(R.string.venomancer)
            VOID -> heroName = context.getString(R.string.faceless_void)
            WRAITH_KING -> heroName = context.getString(R.string.wraith_king)
            DEATH_PROPHET -> heroName = context.getString(R.string.death_prophet)
            PHANTOM_ASSASSIN -> heroName = context.getString(R.string.phantom_assassin)
            PUGNA -> heroName = context.getString(R.string.pugna)
            TEMPLAR_ASSASSIN -> heroName = context.getString(R.string.templar_assassin)
            VIPER -> heroName = context.getString(R.string.viper)
            LUNA -> heroName = context.getString(R.string.luna)
            DRAGON_KNIGHT -> heroName = context.getString(R.string.dragon_knight)
            DAZZLE -> heroName = context.getString(R.string.dazzle)
            CLOCKWERK -> heroName = context.getString(R.string.clockwerk)
            LESHRAC -> heroName = context.getString(R.string.leshrac)
            FURION -> heroName = context.getString(R.string.natures_prophet)
            LIFESTEALER -> heroName = context.getString(R.string.lifestealer)
            DARK_SEER -> heroName = context.getString(R.string.dark_seer)
            CLINKZ -> heroName = context.getString(R.string.clinkz)
            OMNI -> heroName = context.getString(R.string.omniknight)
            ENCHANTRESS -> heroName = context.getString(R.string.enchantress)
            HUSKAR -> heroName = context.getString(R.string.huskar)
            NIGHT_STALKER -> heroName = context.getString(R.string.night_stalker)
            BROODMOTHER -> heroName = context.getString(R.string.broodmother)
            BOUNTY_HUNTER -> heroName = context.getString(R.string.bounty_hunter)
            WEAVER -> heroName = context.getString(R.string.weaver)
            JAKIRO -> heroName = context.getString(R.string.jakiro)
            BATRIDER -> heroName = context.getString(R.string.batrider)
            CHEN -> heroName = context.getString(R.string.chen)
            SPECTRE -> heroName = context.getString(R.string.spectre)
            APPARATION -> heroName = context.getString(R.string.ancient_apparition)
            DOOM -> heroName = context.getString(R.string.doom)
            URSA -> heroName = context.getString(R.string.ursa)
            SPIRIT_BREAKER -> heroName = context.getString(R.string.spirit_breaker)
            GYROCOPTER -> heroName = context.getString(R.string.gyrocopter)
            ALCHEMIST -> heroName = context.getString(R.string.alchemist)
            INVOKER -> heroName = context.getString(R.string.invoker)
            SILENCER -> heroName = context.getString(R.string.silencer)
            OUTWORLD_DEVOURER -> heroName = context.getString(R.string.outworld_devourer)
            LYCAN -> heroName = context.getString(R.string.lycan)
            BREWMASTER -> heroName = context.getString(R.string.brewmaster)
            SHADOW_DEMON -> heroName = context.getString(R.string.shadow_demon)
            LONE_DRUID -> heroName = context.getString(R.string.lone_druid)
            CHAOS_KNIGHT -> heroName = context.getString(R.string.chaos_knight)
            MEEPO -> heroName = context.getString(R.string.meepo)
            TREANT -> heroName = context.getString(R.string.treant)
            OGRE -> heroName = context.getString(R.string.ogre_magi)
            UNDYING -> heroName = context.getString(R.string.undying)
            RUBICK -> heroName = context.getString(R.string.rubick)
            DISRUPTOR -> heroName = context.getString(R.string.disruptor)
            NYX -> heroName = context.getString(R.string.nyx_assassin)
            NAGA_SIREN -> heroName = context.getString(R.string.naga_siren)
            KOTL -> heroName = context.getString(R.string.keeper_of_the_light)
            IO -> heroName = context.getString(R.string.io)
            VISAGE -> heroName = context.getString(R.string.visage)
            SLARK -> heroName = context.getString(R.string.slark)
            MEDUSA -> heroName = context.getString(R.string.medusa)
            TROLL -> heroName = context.getString(R.string.troll_warlord)
            CENTAUR -> heroName = context.getString(R.string.centaur_warrunner)
            MAGNUS -> heroName = context.getString(R.string.magnus)
            TIMBERSAW -> heroName = context.getString(R.string.timbersaw)
            BRISTLEBACK -> heroName = context.getString(R.string.bristleback)
            TUSKAR -> heroName = context.getString(R.string.tuskar)
            SKY -> heroName = context.getString(R.string.skywrath_mage)
            ABADDON -> heroName = context.getString(R.string.abaddon)
            ELDER_TITAN -> heroName = context.getString(R.string.elder_titan)
            LEGOIN_COMANDER -> heroName = context.getString(R.string.legion_commander)
            TECHIES -> heroName = context.getString(R.string.techies)
            EMBER_SPIRIT -> heroName = context.getString(R.string.ember_spirit)
            EARTH_SPIRIT -> heroName = context.getString(R.string.earth_spirit)
            UNDERLORD -> heroName = context.getString(R.string.underlord)
            TERRORBLADE -> heroName = context.getString(R.string.terrorblade)
            PHOENIX -> heroName = context.getString(R.string.phoenix)
            ORACLE -> heroName = context.getString(R.string.oracle)
            WYVERN -> heroName = context.getString(R.string.winter_wyvern)
            ARC_WARDEN -> heroName = context.getString(R.string.arc_warden)
            MONKEY_KING -> heroName = context.getString(R.string.monkey_king)
            DARK_WILLOW -> heroName = context.getString(R.string.dark_willow)
            PANGOLIER -> heroName = context.getString(R.string.pangolier)
            GRIMSTROKE -> heroName = context.getString(R.string.grimstroke)
            VOID_SPIRIT -> heroName = context.getString(R.string.void_spirit)
            SNAPFIRE -> heroName = context.getString(R.string.snapfire)
            MARS -> heroName = context.getString(R.string.mars)
            else -> heroName = context.getString(R.string.unknown_hero)
        }

        when (fromId) {
            GAMES_FRAGMENT_ID -> {
                if (heroName.length > 15) view.textSize = 14F
                else view.textSize = 16F
            }
            GAME_FRAGMENT_DETAIL_ID -> {
                when {
                    heroName.length in 12..15 -> view.textSize = 12F
                    heroName.length in 16..21 -> view.textSize = 10F
                    heroName.length >= 22 -> view.textSize = 8F
                    else -> view.textSize = 14F
                }
            }
        }

        view.text = heroName
    }

    /**
     * Return Game Date String
     */
    @BindingAdapter("gameDate")
    @JvmStatic
    fun getTimeForGame(view: TextView, startTime: Long) {

        val context = App.applicationContext()

        if (startTime == 0L) {
            view.text = context.getString(R.string.dont_play)
            return
        }

        // Текущая дата
        val currentDate = Date()
        // Количество секунд прошедших с 1 января 1970г
        val currentTime = currentDate.time / 1000
        // Разница в секундах между текущей датой и датой игры
        val differenceTime = currentTime - startTime
        // Количество часов прошедших после игры
        val hours = differenceTime.toInt() / 3600
        // Количество дней прошедших после игры
        val days = differenceTime.toInt() / 3600 / 24
        // Количество месяцев
        val months = differenceTime.toInt() / 3600 / 24 / 30
        // Количество минут прошедших после игры
        val minutes: Int

        when {
            hours < 1 -> {
                minutes = differenceTime.toInt() / 60
                view.text = if (minutes == 1) context.getString(R.string.minute)
                else String.format(context.getString(R.string.minutes), minutes)
            }
            hours == 1 -> view.text = context.getString(R.string.hour)
            hours < 24 -> view.text = String.format(context.getString(R.string.hours), hours)
            days == 1 -> view.text = context.getString(R.string.day)
            days < 31 -> view.text = String.format(context.getString(R.string.days), days)
            months == 1 -> view.text = context.getString(R.string.month)
            months < 12 -> view.text = String.format(context.getString(R.string.months), months)
            months < 24 -> view.text = String.format(context.getString(R.string.year), months)
            else -> view.text = context.getString(R.string.two_years)
        }
    }

    /**
     * Return Game result.
     * Win or Lose
     */
    @BindingAdapter("playerSlot", "leaverStatus", "radiantWin")
    @JvmStatic
    fun winOrLose(view: TextView, playerSlot: Int, leaverStatus: Int, radiantWin: Boolean) {
        val context = App.applicationContext()

        if (!(ABANDONED == leaverStatus && leaverStatus != ABANDONED_TWO)) {
            view.text = context.getString(R.string.abandoned)
            view.textSize = 14F
            view.setTextColor(context.getColor(R.color.colorRed))
        }

        if (playerSlot in 0..4) {
            if (radiantWin) {
                view.text = context.getString(R.string.won_game)
                view.textSize = 16F
                view.setTextColor(context.getColor(R.color.colorLightGreen))
            } else {
                view.text = context.getString(R.string.lost_game)
                view.textSize = 16F
                view.setTextColor(context.getColor(R.color.colorRed))
            }
        } else if (playerSlot in 128..132) {
            if (!radiantWin) {
                view.text = context.getString(R.string.won_game)
                view.textSize = 16F
                view.setTextColor(context.getColor(R.color.colorLightGreen))
            } else {
                view.text = context.getString(R.string.lost_game)
                view.textSize = 16F
                view.setTextColor(context.getColor(R.color.colorRed))
            }
        }
    }

    /**
     * Determines ranked or not ranked
     */
    @BindingAdapter("rank")
    @JvmStatic
    fun rankedOrNot(view: TextView, lobbyType: Int) {
        val context = App.applicationContext()

        return if (lobbyType == RANKED) view.text = context.getString(R.string.ranked)
        else view.text = context.getString(R.string.normal)
    }

    /**
     * Determines Game Mode
     */
    @BindingAdapter("mode")
    @JvmStatic
    fun gameMode(view: TextView, mode: Int) {

        val context = App.applicationContext()

        when (mode) {
            ALL_PICK, ALL_DRAFT -> view.text = context.getString(R.string.all_pick)
            SINGLE_DRAFT -> view.text = context.getString(R.string.single_draft)
            CAPTAINS_MODE -> view.text = context.getString(R.string.captains_mode)
            ALL_RANDOM -> view.text = context.getString(R.string.all_random)
            RANDOM_DRAFT -> view.text = context.getString(R.string.random_draft)
            else -> view.text = context.getString(R.string.unknown_mode)
        }
    }

    /**
     * Determines Skill Bracket
     */
    @BindingAdapter("skill")
    @JvmStatic
    fun skillPlayer(view: TextView, skill: Int) {
        val context = App.applicationContext()

        return when (skill) {
            NORMALL_SKILL -> view.text = context.getString(R.string.normal_skill)
            HIGH_SKILL -> view.text = context.getString(R.string.high_skill)
            VERY_HIGH_SKILL -> view.text = context.getString(R.string.very_high_skill)
            else -> view.text = context.getString(R.string.unknown_skill)
        }
    }

    /**
     * Determines percent KDA
     */
    @BindingAdapter("game", "necessaryValue")
    @JvmStatic
    fun percentKda(view: View, game: Game, necessaryValue: Int) {
        val kills = game.kills
        val deaths = game.deaths
        val assists = game.assists

        val sum = kills + deaths + assists

        val params = view.layoutParams as LinearLayout.LayoutParams

        when (necessaryValue) {
            KILLS_ID -> {
                params.weight = ((kills * 100.0) / sum).roundToInt() / 100F
                Log.d("EDYA", "УБИЙСТВ = $kills. ЗНАЧЕНИЕ ВЕСА = ${params.weight}")
                Log.d("EDYA", "-------------------")
            }
            DEATHS_ID -> {
                params.weight = ((deaths * 100.0) / sum).roundToInt() / 100F
                Log.d("EDYA", "СМЕРТЕЙ = $deaths. ЗНАЧЕНИЕ ВЕСА = ${params.weight}")
            }
            ASSISTS_ID -> {
                params.weight = ((assists * 100.0) / sum).roundToInt() / 100F
                Log.d("EDYA", "АССИСТОВ = $assists. ЗНАЧЕНИЕ ВЕСА = ${params.weight}")
            }
        }
    }

    /**
     * Determines Player RANG
     */
    @BindingAdapter("rankTier")
    @JvmStatic
    fun rankOfPlayer(view: ImageView, rankTier: Int?) {

        if (rankTier == null || rankTier == 0) {
            view.visibility = View.GONE
            return
        } else {
            view.visibility = View.VISIBLE
        }

        when (rankTier) {
            HERALD_1 -> view.setImageResource(R.drawable.herald_1)
            HERALD_2 -> view.setImageResource(R.drawable.herald_2)
            HERALD_3 -> view.setImageResource(R.drawable.herald_3)
            HERALD_4 -> view.setImageResource(R.drawable.herald_4)
            HERALD_5 -> view.setImageResource(R.drawable.herald_5)
            GUARDIAN_1 -> view.setImageResource(R.drawable.guardian_1)
            GUARDIAN_2 -> view.setImageResource(R.drawable.guardian_2)
            GUARDIAN_3 -> view.setImageResource(R.drawable.guardian_3)
            GUARDIAN_4 -> view.setImageResource(R.drawable.guardian_4)
            GUARDIAN_5 -> view.setImageResource(R.drawable.guardian_5)
            CRUSADER_1 -> view.setImageResource(R.drawable.crusader_1)
            CRUSADER_2 -> view.setImageResource(R.drawable.crusader_2)
            CRUSADER_3 -> view.setImageResource(R.drawable.crusader_3)
            CRUSADER_4 -> view.setImageResource(R.drawable.crusader_4)
            CRUSADER_5 -> view.setImageResource(R.drawable.crusader_5)
            ARCHON_1 -> view.setImageResource(R.drawable.archon_1)
            ARCHON_2 -> view.setImageResource(R.drawable.archon_2)
            ARCHON_3 -> view.setImageResource(R.drawable.archon_3)
            ARCHON_4 -> view.setImageResource(R.drawable.archon_4)
            ARCHON_5 -> view.setImageResource(R.drawable.archon_5)
            LEGEND_1 -> view.setImageResource(R.drawable.legend_1)
            LEGEND_2 -> view.setImageResource(R.drawable.legend_2)
            LEGEND_3 -> view.setImageResource(R.drawable.legend_3)
            LEGEND_4 -> view.setImageResource(R.drawable.legend_4)
            LEGEND_5 -> view.setImageResource(R.drawable.legend_5)
            ANCIENT_1 -> view.setImageResource(R.drawable.ancient_1)
            ANCIENT_2 -> view.setImageResource(R.drawable.ancient_2)
            ANCIENT_3 -> view.setImageResource(R.drawable.ancient_3)
            ANCIENT_4 -> view.setImageResource(R.drawable.ancient_4)
            ANCIENT_5 -> view.setImageResource(R.drawable.ancient_5)
            DIVINE_1 -> view.setImageResource(R.drawable.divine_1)
            DIVINE_2 -> view.setImageResource(R.drawable.divine_2)
            DIVINE_3 -> view.setImageResource(R.drawable.divine_3)
            DIVINE_4 -> view.setImageResource(R.drawable.divine_4)
            DIVINE_5 -> view.setImageResource(R.drawable.divine_5)
            IMMORTAL -> view.setImageResource(R.drawable.immortal)
        }
    }

    /**
     * Устанавливает пробелы в больших цифрах
     */
    @BindingAdapter("largeNumbers")
    @JvmStatic
    fun correctValue(view: TextView, value: Int) {
        val length = value.toString().length

        val result = StringBuilder()
        when {
            length < 4 -> view.text = value.toString()
            length == 4 -> {
                val array = value.toString().toCharArray()
                result.append(array[0]).append(" ").append(array[1]).append(array[2])
                    .append(array[3])
                view.text = result.toString()
            }
            length == 5 -> {
                val array = value.toString().toCharArray()
                result.append(array[0]).append(array[1]).append(" ").append(array[2])
                    .append(array[3])
                    .append(array[4])
                view.text = result.toString()
            }
        }
    }

    /**
     * Set Item Icon
     */
    @BindingAdapter("itemIcon")
    @JvmStatic
    fun itemIcon(view: ImageView, itemId: Int) {

        when (itemId) {
            BLINK_DAGGER -> view.setImageResource(R.drawable.blink_dagger)
            BLADES_OF_ATTACK -> view.setImageResource(R.drawable.blades_of_attack)
            BROADSWORD -> view.setImageResource(R.drawable.broadsword)
            CHAINMAIL -> view.setImageResource(R.drawable.bhainmail)
            CLAYMORE -> view.setImageResource(R.drawable.claymore)
            HELM_OF_IRON_WILL -> view.setImageResource(R.drawable.helm_of_iron_will)
            JAVELIN -> view.setImageResource(R.drawable.javelin)
            MITHRIL_HAMMER -> view.setImageResource(R.drawable.mithril_hammer)
            PLATEMAIL -> view.setImageResource(R.drawable.platemail)
            QUARTERSTAFF -> view.setImageResource(R.drawable.quarterstaff)
            QUELLING_BLADE -> view.setImageResource(R.drawable.quelling_blade)
            RING_OF_PROTECTION -> view.setImageResource(R.drawable.ring_of_protection)
            GAUNTLETS_OF_STRENGTH -> view.setImageResource(R.drawable.gauntlets_of_strength)
            SLIPPERS_OF_AGILITY -> view.setImageResource(R.drawable.slippers_of_agility)
            MANTLE_OF_INTELLIGENCE -> view.setImageResource(R.drawable.mantle_of_intelligence)
            IRON_BRANCH -> view.setImageResource(R.drawable.iron_branch)
            BELT_OF_STRENGTH -> view.setImageResource(R.drawable.belt_of_strength)
            BAND_OF_ELVENSKIN -> view.setImageResource(R.drawable.band_of_elvenskin)
            ROBE_OF_THE_MAGI -> view.setImageResource(R.drawable.robe_of_the_magi)
            CIRCLET -> view.setImageResource(R.drawable.circlet)
            OGRE_AXE -> view.setImageResource(R.drawable.ogre_club)
            BLADE_OF_ALACRITY -> view.setImageResource(R.drawable.blade_of_alacrity)
            STAFF_OF_WIZARDRY -> view.setImageResource(R.drawable.staff_of_wizardry)
            ULTIMATE_ORB -> view.setImageResource(R.drawable.ultimate_orb)
            GLOVES_OF_HASTE -> view.setImageResource(R.drawable.gloves_of_haste)
            MORBID_MASK -> view.setImageResource(R.drawable.morbid_mask)
            RING_OF_REGEN -> view.setImageResource(R.drawable.ring_of_regen)
            SAGES_MASK -> view.setImageResource(R.drawable.sages_mask)
            BOOTS_OF_SPEED -> view.setImageResource(R.drawable.boots_of_speed)
            GEM_OF_TRUE_SIGHT -> view.setImageResource(R.drawable.gem_of_true_sight)
            CLOAK -> view.setImageResource(R.drawable.cloak)
            TALISMAN_OF_EVASION -> view.setImageResource(R.drawable.talisman_of_evasion)
            CHEESE -> view.setImageResource(R.drawable.cheese)
            MAGIC_STICK -> view.setImageResource(R.drawable.magic_stick)
            MAGIC_WAND_RECIPE -> view.setImageResource(R.drawable.magic_wand_recipe_icon)
            MAGIC_WAND -> view.setImageResource(R.drawable.magic_wand)
            GHOST_SCEPTER -> view.setImageResource(R.drawable.ghost_scepter)
            CLARITY -> view.setImageResource(R.drawable.clarity)
            HEALING_SALVE -> view.setImageResource(R.drawable.healing_salve)
            DUST_OF_APPEARANCE -> view.setImageResource(R.drawable.dust_of_appearance)
            BOTTLE -> view.setImageResource(R.drawable.bottle)
            OBSERVER_WARD -> view.setImageResource(R.drawable.observer_ward)
            SENTRY_WARD -> view.setImageResource(R.drawable.sentry_ward)
            TANGO -> view.setImageResource(R.drawable.tango)
            BOOTS_OF_TREVEL_RECIPE -> view.setImageResource(R.drawable.boots_of_travel_recipe_icon)
            BOOTS_OF_TREVEL_1_LVL -> view.setImageResource(R.drawable.boots_of_travel)
            PHASE_BOOTS -> view.setImageResource(R.drawable.phase_boots)
            DEMON_EDGE -> view.setImageResource(R.drawable.demon_edge)
            EAGLESONG -> view.setImageResource(R.drawable.eaglehorn)
            REAVER -> view.setImageResource(R.drawable.reaver)
            SACRED_RELIC -> view.setImageResource(R.drawable.sacred_relic)
            HYPERSTONE -> view.setImageResource(R.drawable.hyperstone)
            RING_OF_HEALTH -> view.setImageResource(R.drawable.ring_of_health)
            VOID_STONE -> view.setImageResource(R.drawable.void_stone)
            MYSTIC_STAFF -> view.setImageResource(R.drawable.mystic_staff)
            ENERGY_BOOSTER -> view.setImageResource(R.drawable.energy_booster)
            POINT_BOOSTER -> view.setImageResource(R.drawable.point_booster)
            VITALITY_BOOSTER -> view.setImageResource(R.drawable.vitality_booster)
            POWER_TREADS -> view.setImageResource(R.drawable.power_treads)
            HAND_OF_MIDAS_RECIPE -> view.setImageResource(R.drawable.hand_of_midas_recipe_icon)
            HAND_OF_MIDAS -> view.setImageResource(R.drawable.hand_of_midas)
            OBLIVION_STAFF -> view.setImageResource(R.drawable.oblivion_staff)
            PERSEVERANCE -> view.setImageResource(R.drawable.perseverance)
            BRACER_RECIPE -> view.setImageResource(R.drawable.bracer_recipe_icon)
            BRACER -> view.setImageResource(R.drawable.bracer)
            WRAITH_BAND_RECIPE -> view.setImageResource(R.drawable.wraith_band_recipe_icon)
            WRAITH_BAND -> view.setImageResource(R.drawable.wraith_band)
            NULL_TALISMAN_RECIPE -> view.setImageResource(R.drawable.null_talisman_recipe_icon)
            NULL_TALISMAN -> view.setImageResource(R.drawable.null_talisman)
            MEKANSM_RECIPE -> view.setImageResource(R.drawable.mekansm_recipe_icon)
            MEKANSM -> view.setImageResource(R.drawable.mekansm)
            VLADMIRS_OFFERING_RECIPE -> view.setImageResource(R.drawable.default_recipe_icon)
            VLADMIRS_OFFERING -> view.setImageResource(R.drawable.vladmirs_offering)
            BUCKLER_RECIPE -> view.setImageResource(R.drawable.buckler_recipe_icon)
            BUCKLER -> view.setImageResource(R.drawable.buckler)
            RING_OF_BASILIUS -> view.setImageResource(R.drawable.ring_of_basilius)
            PIPE_OF_INSIGHT_RECIPE -> view.setImageResource(R.drawable.pipe_of_insight_recipe_icon)
            PIPE_OF_INSIGHT -> view.setImageResource(R.drawable.pipe_of_insight)
            URN_OF_SHADOWS_RECIPE -> view.setImageResource(R.drawable.urn_of_shadows_recipe_icon)
            URN_OF_SHADOWS -> view.setImageResource(R.drawable.urn_of_shadows)
            HEADRESS_RECIPE -> view.setImageResource(R.drawable.headdress_recipe_icon)
            HEADRESS -> view.setImageResource(R.drawable.headdress)
            SCYTHE_OF_VYSE -> view.setImageResource(R.drawable.scythe_of_vyse)
            ORCHID_MALEVOLENCE_RECIPE -> view.setImageResource(R.drawable.orchid_malevolence_recipe_icon)
            ORCHID_MALEVOLENCE -> view.setImageResource(R.drawable.orchid_malevolence)
            EULS_SCEPTER_OF_DIVINITY_RECIPE -> view.setImageResource(R.drawable.euls_scepter_of_divinity_recipe_icon)
            EULS_SCEPTER_OF_DIVINITY -> view.setImageResource(R.drawable.euls_scepter_of_divinity)
            FORCE_STAFF_RECIPE -> view.setImageResource(R.drawable.force_staff_recipe_icon)
            FORCE_STAFF -> view.setImageResource(R.drawable.force_staff)
            DAGON_RECIPE -> view.setImageResource(R.drawable.dagon_recipe_icon)
            DAGON_1_LVL -> view.setImageResource(R.drawable.dagon_1)
            NECRONOMICON_RECIPE -> view.setImageResource(R.drawable.necronomicon_recipe_icon)
            NECRONOMICON_1_LVL -> view.setImageResource(R.drawable.necronomicon_1)
            AGHANIMS_SCEPTER -> view.setImageResource(R.drawable.aghanims_scepter)
            REFRESHER_ORB_RECIPE -> view.setImageResource(R.drawable.refresher_orb_recipe_icon)
            REFRESHER_ORB -> view.setImageResource(R.drawable.refresher_orb)
            ASSAULT_CUIRASS_RECIPE -> view.setImageResource(R.drawable.assault_cuirass_recipe_icon)
            ASSAULT_CUIRASS -> view.setImageResource(R.drawable.assault_cuirass)
            HEART_OF_TARASQUE_RECIPE -> view.setImageResource(R.drawable.heart_of_tarrasque_recipe_icon)
            HEART_OF_TARASQUE -> view.setImageResource(R.drawable.heart_of_tarrasque)
            BLACK_KING_BAR_RECIPE -> view.setImageResource(R.drawable.black_king_bar_recipe_icon)
            BLACK_KING_BAR -> view.setImageResource(R.drawable.black_king_bar)
            AEGIS_OF_THE_IMMORTAL -> view.setImageResource(R.drawable.aegis_of_the_immortal)
            SHIVAS_GUARD_RECIPE -> view.setImageResource(R.drawable.shivas_guard_recipe_icon)
            SHIVAS_GUARD -> view.setImageResource(R.drawable.shivas_guard)
            BLOODSTONE -> view.setImageResource(R.drawable.bloodstone)
            LINKENS_SPHERE_RECIPE -> view.setImageResource(R.drawable.linkens_sphere_recipe_icon)
            LINKENS_SPHERE -> view.setImageResource(R.drawable.linkens_sphere)
            VANGUARD -> view.setImageResource(R.drawable.vanguard)
            BLADE_MAIL -> view.setImageResource(R.drawable.blade_mail)
            SOUL_BOOSTER -> view.setImageResource(R.drawable.soul_booster)
            HOOD_OF_DEFIANCE -> view.setImageResource(R.drawable.hood_of_defiance)
            DIVINE_RAPIER -> view.setImageResource(R.drawable.divine_rapier)
            MONKEY_KING_BAR -> view.setImageResource(R.drawable.monkey_king_bar)
            RADIANCE_RECIPE -> view.setImageResource(R.drawable.radiance_recipe_icon)
            RADIANCE -> view.setImageResource(R.drawable.radiance)
            BUTTERFLY -> view.setImageResource(R.drawable.butterfly)
            DAEDALUS_RECIPE -> view.setImageResource(R.drawable.daedalus_recipe_icon)
            DAEDALUS -> view.setImageResource(R.drawable.daedalus)
            SKULL_BASHER_RECIPE -> view.setImageResource(R.drawable.skull_basher_recipe_icon)
            SKULL_BASHER -> view.setImageResource(R.drawable.skull_basher)
            BATTLE_FURY_RECIPE -> view.setImageResource(R.drawable.battle_fury_recipe_icon)
            BATTLE_FURY -> view.setImageResource(R.drawable.battle_fury)
            MANTA_STYLE_RECIPE -> view.setImageResource(R.drawable.manta_style_recipe_icon)
            MANTA_STYLE -> view.setImageResource(R.drawable.manta_style)
            CRYSTALYS_RECIPE -> view.setImageResource(R.drawable.crystalys_recipe_icon)
            CRYSTALYS -> view.setImageResource(R.drawable.crystalys)
            ARMLET_OF_MORDIGGIAN_RECIPE -> view.setImageResource(R.drawable.armlet_of_mordiggian_recipe_icon)
            ARMLET_OF_MORDIGGIAN -> view.setImageResource(R.drawable.armlet_of_mordiggian)
            SHADOW_BLADE -> view.setImageResource(R.drawable.shadow_blade)
            SANGE_AND_YASHA -> view.setImageResource(R.drawable.sange_and_yasha)
            SATANIC -> view.setImageResource(R.drawable.satanic)
            MJOLLNIR_RECIPE -> view.setImageResource(R.drawable.mjollnir_recipe_icon)
            MJOLLNIR -> view.setImageResource(R.drawable.mjollnir)
            EYE_OF_SKADI -> view.setImageResource(R.drawable.eye_of_skadi)
            SANGE_RECIPE -> view.setImageResource(R.drawable.sange_recipe_icon)
            SANGE -> view.setImageResource(R.drawable.sange)
            HELM_OF_THE_DOMINATOR -> view.setImageResource(R.drawable.helm_of_the_dominator)
            MAELSTROM -> view.setImageResource(R.drawable.maelstrom)
            DESOLATOR -> view.setImageResource(R.drawable.desolator)
            YASHA_RECIPE -> view.setImageResource(R.drawable.yasha_recipe_icon)
            YASHA -> view.setImageResource(R.drawable.yasha)
            MASK_OF_MADNESS -> view.setImageResource(R.drawable.mask_of_madness)
            DIFFUSAL_BLADE_RECIPE -> view.setImageResource(R.drawable.diffusal_blade_recipe_icon)
            DIFFUSAL_BLADE -> view.setImageResource(R.drawable.diffusal_blade)
            ETHEREAL_BLADE -> view.setImageResource(R.drawable.ethereal_blade)
            SOUL_RING_RECIPE -> view.setImageResource(R.drawable.soul_ring_recipe_icon)
            SOUL_RING -> view.setImageResource(R.drawable.soul_ring)
            ARCANE_BOOTS -> view.setImageResource(R.drawable.arcane_boots)
            ORB_OF_VENOM -> view.setImageResource(R.drawable.orb_of_venom)
            STOUT_SHIELD -> view.setImageResource(R.drawable.stout_shield)
            DRUM_OF_ENDURANCE_RECIPE -> view.setImageResource(R.drawable.drum_of_endurance_recipe_icon)
            DRUM_OF_ENDURANCE -> view.setImageResource(R.drawable.drum_of_endurance)
            MEDALLION_OF_COURAGE -> view.setImageResource(R.drawable.medallion_of_courage)
            SMOKE_OF_DECEIT -> view.setImageResource(R.drawable.smoke_of_deceit)
            VEIL_OF_DISCORD_RECIPE -> view.setImageResource(R.drawable.veil_of_discord_recipe_icon)
            VEIL_OF_DISCORD -> view.setImageResource(R.drawable.veil_of_discord)
            NECRONOMICON_2_LVL -> view.setImageResource(R.drawable.necronomicon_2)
            NECRONOMICON_3_LVL -> view.setImageResource(R.drawable.necronomicon_3)
            DAGON_2_LVL -> view.setImageResource(R.drawable.dagon_2)
            DAGON_3_LVL -> view.setImageResource(R.drawable.dagon_3)
            DAGON_4_LVL -> view.setImageResource(R.drawable.dagon_4)
            DAGON_5_LVL -> view.setImageResource(R.drawable.dagon_5)
            ROD_OF_ATOS_RECIPE -> view.setImageResource(R.drawable.rod_of_atos_recipe_icon)
            ROD_OF_ATOS -> view.setImageResource(R.drawable.rod_of_atos)
            ABYSSAL_BLADE_RECIPE -> view.setImageResource(R.drawable.abyssal_blade_recipe_icon)
            ABYSSAL_BLADE -> view.setImageResource(R.drawable.abyssal_blade)
            HEAVENS_HALBERD -> view.setImageResource(R.drawable.heavens_halberd)
            TRANQUIL_BOOTS -> view.setImageResource(R.drawable.tranquil_boots)
            SHADOW_AMULET -> view.setImageResource(R.drawable.shadow_amulet)
            ENCHANTED_MANGO -> view.setImageResource(R.drawable.enchanted_mango)
            OBSERVER_AND_SENTRY_WARDS -> view.setImageResource(R.drawable.observer_and_sentry_wards_icon)
            BOOTS_OF_TRAVEL_2_LVL -> view.setImageResource(R.drawable.boots_of_travel_2)
            METEOR_HAMMER -> view.setImageResource(R.drawable.meteor_hammer)
            NULLIFIER -> view.setImageResource(R.drawable.nullifier)
            LOTUS_ORB -> view.setImageResource(R.drawable.lotus_orb)
            SOLAR_CREST_RECIPE -> view.setImageResource(R.drawable.solar_crest_recipe_icon)
            SOLAR_CREST -> view.setImageResource(R.drawable.solar_crest)
            GUARDIAN_GREAVES_RECIPE -> view.setImageResource(R.drawable.guardian_greaves_recipe_icon)
            GUARDIAN_GREAVES -> view.setImageResource(R.drawable.guardian_greaves)
            AETHER_LENS -> view.setImageResource(R.drawable.aether_lens)
            AETHER_LENS_RECIPE -> view.setImageResource(R.drawable.aether_lens_recipe_icon)
            OCTARINE_CORE -> view.setImageResource(R.drawable.octarine_core)
            DRAGON_LANCE -> view.setImageResource(R.drawable.dragon_lance)
            FAERIE_FIRE -> view.setImageResource(R.drawable.faerie_fire)
            BLIGHT_STONE -> view.setImageResource(R.drawable.blight_stone)
            TANGO_SHARED -> view.setImageResource(R.drawable.tango_shared_icon)
            CRIMSON_GUARD -> view.setImageResource(R.drawable.crimson_guard)
            CRIMSON_GUARD_RECIPE -> view.setImageResource(R.drawable.crimson_guard_recipe_icon)
            WIND_LANCE -> view.setImageResource(R.drawable.wind_lace)
            BLOODTHORN_RECIPE -> view.setImageResource(R.drawable.bloodthorn_recipe_icon)
            MOON_SHARD -> view.setImageResource(R.drawable.moon_shard)
            SILVER_EDGE_RECIPE -> view.setImageResource(R.drawable.silver_edge_recipe_icon)
            SILVER_EDGE -> view.setImageResource(R.drawable.silver_edge)
            BLOODTHORN -> view.setImageResource(R.drawable.bloodthorn)
            ECHO_SABRE -> view.setImageResource(R.drawable.echo_sabre)
            GLIMMER_CAPE -> view.setImageResource(R.drawable.glimmer_cape)
            AEON_DISK_RECIPE -> view.setImageResource(R.drawable.aeon_disk_recipe_icon)
            AEON_DISK -> view.setImageResource(R.drawable.aeon_disk)
            TOME_OF_KNOWLEDGE -> view.setImageResource(R.drawable.tome_of_knowledge)
            KAYA_RECIPE -> view.setImageResource(R.drawable.kaya_recipe_icon)
            KAYA -> view.setImageResource(R.drawable.kaya)
            CROWN -> view.setImageResource(R.drawable.crown)
            HURRICANE_PIKE_RECIPE -> view.setImageResource(R.drawable.hurricane_pike_recipe_icon)
            HURRICANE_PIKE -> view.setImageResource(R.drawable.hurricane_pike)
            INFUSED_RAINDROPS -> view.setImageResource(R.drawable.infused_raindrop)
            SPIRIT_VESSEL_RECIPE -> view.setImageResource(R.drawable.spirit_vessel_recipe_icon)
            SPIRIT_VESSEL -> view.setImageResource(R.drawable.spirit_vessel)
            HOLY_LOCKET_RECIPE -> view.setImageResource(R.drawable.holy_locket_recipe_icon)
            HOLY_LOCKET -> view.setImageResource(R.drawable.holy_locket)
            KAYA_AND_SANGE -> view.setImageResource(R.drawable.kaya_and_sange)
            YASHA_AND_KAYA -> view.setImageResource(R.drawable.yasha_and_kaya)
            RING_OF_TARRASQUE -> view.setImageResource(R.drawable.ring_of_tarrasque)
        }
    }

    /**
     * Set Buff Icon
     */
    @BindingAdapter("buffIcon", "position")
    @JvmStatic
    fun buffIcon(view: ImageView, buffs: List<PermanentBuff>?, position: Int) {

        if (buffs == null) return

        if (position < buffs.size) {
            when (buffs[position].permanent_buff) {
                MOON_SHARD_BUFF -> view.setImageResource(R.drawable.moon_shard_buff)
                AGHANIM_SCEPTER_BUFF -> view.setImageResource(R.drawable.aghanims_scepter_buff)
                GLAIVES_OF_WISDOM_BUFF -> view.setImageResource(R.drawable.glaives_of_wisdom_icon)
                FLESH_HEAP_BUFF -> view.setImageResource(R.drawable.flesh_heap_icon)
                DUEL_BUFF -> view.setImageResource(R.drawable.duel_icon)
                TOME_OF_KNOWLEDGE_BUFF -> view.setImageResource(R.drawable.tome_of_knowledge_buff)
                FINGER_OF_DEATH_BUFF -> view.setImageResource(R.drawable.finger_of_death_icon)
                ESSENCE_SHIFT_BUFF -> view.setImageResource(R.drawable.essence_shift_icon)
                ATROPHY_AURA_BUFF -> view.setImageResource(R.drawable.atrophy_aura_icon)
                JINADA_BUFF -> view.setImageResource(R.drawable.jinada_icon)
            }
        }
    }

    /**
     * Set Buff Count
     */
    @BindingAdapter("buffCount", "position")
    @JvmStatic
    fun buffCount(view: TextView, buffs: List<PermanentBuff>?, position: Int) {

        if (buffs == null) return

        if (position < buffs.size) {
            when (buffs[position].permanent_buff) {
                MOON_SHARD_BUFF, AGHANIM_SCEPTER_BUFF -> {
                }
                else -> view.text = buffs[position].stack_count.toString()
            }
        }
    }

    /**
     * Set Last Hits and Denies
     */
    @BindingAdapter("lastHits", "denies")
    @JvmStatic
    fun lastHitsAndDenies(view: TextView, lastHits: Int, denies: Int) {
        view.text = String.format("$lastHits / $denies")
    }

    /**
     * Determines Win Rate
     */
    @BindingAdapter("winRate")
    @JvmStatic
    fun winRate(view: TextView, user: User?) {
        if (user != null) {
            val wins = user.wins!!
            val losses = user.losses!!

            val winRate = (wins * 100.0) / (wins + losses)

            val result = Math.round(winRate * 100.0) / 100.0

            val sb = StringBuilder()

            view.text = sb.append(result).append('%')
        }
    }

    /**
     * Determines Win Rate for hero_recycler_view
     */
    @Suppress("IMPLICIT_CAST_TO_ANY")
    @BindingAdapter("heroWinRate")
    @JvmStatic
    fun heroWinRate(view: TextView, hero: Hero) {
        val sb = StringBuilder()

        val wins = hero.win
        val games = hero.games

        if (games != 0) {
            val wr = (wins * 100.0) / games

            Log.d("MyLogs", "!!!!!!!!!!!!!!!!!!!!!!!     ИГР НА ГЕРОЕ = $games")
            Log.d("MyLogs", "!!!!!!!!!!!!!!!!!!!!!!!     ПОБЕД НА ГЕРОЕ = $wins")
            val wrToInt = (wr * 10).roundToInt()

            val result = if (wrToInt % 10.0 == 0.0) (wrToInt / 10.0).toInt()
            else wrToInt / 10.0

            view.text = sb.append(result).append('%')
        } else view.text = sb.append('0').append('%')
    }

    /**
     * Sets Recycler View to the start
     */
    @BindingAdapter("scrollToStart")
    @JvmStatic
    fun scrollToStart(view: RecyclerView, isNeedPositionToStart: ObservableBoolean) {
        Log.d("MyLogs", "ФУНКЦИЯ УСТАНОВКИ RECYCLER'A В НАЧАЛО")
        if (isNeedPositionToStart.get()) view.scrollToPosition(0)
    }

    /**
     * Sets last game time for players
     */
    @BindingAdapter("lastGameTime")
    @JvmStatic
    fun lastGameTime(view: TextView, lastGameTime: String?) {
        if (lastGameTime == null) return

        val context = App.applicationContext()

        val subDate = lastGameTime.substringBefore("T")

        val currentDate = Date()

        val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date = format.parse(subDate)

        val lastGameTimeSeconds = date!!.time / 1000

        // Количество секунд прошедших с 1 января 1970г
        val currentTime = currentDate.time / 1000
        // Разница в секундах между текущей датой и датой последней игры
        val differenceTime = currentTime - lastGameTimeSeconds
        // Количество часов прошедших после игры
        val hours = differenceTime.toInt() / 3600
        // Количество дней прошедших после игры
        val days = differenceTime.toInt() / 3600 / 24
        // Количество месяцев
        val months = differenceTime.toInt() / 3600 / 24 / 30
        // Количество минут прошедших после игры
        val minutes: Int

        when {
            hours < 1 -> {
                minutes = differenceTime.toInt() / 60
                view.text = if (minutes == 1) context.getString(R.string.minute_search_users_screen)
                else String.format(context.getString(R.string.minutes_search_users_screen), minutes)
            }
            hours == 1 -> view.text = context.getString(R.string.hour_search_users_screen)
            hours < 24 -> view.text =
                String.format(context.getString(R.string.hours_search_users_screen), hours)
            days == 1 -> view.text = context.getString(R.string.day_search_users_screen)
            days < 31 -> view.text =
                String.format(context.getString(R.string.days_search_users_screen), days)
            months == 1 -> view.text = context.getString(R.string.month_search_users_screen)
            months < 12 -> view.text =
                String.format(context.getString(R.string.months_search_users_screen), months)
            else -> view.text = context.getString(R.string.year_search_users_screen)
        }
    }
}