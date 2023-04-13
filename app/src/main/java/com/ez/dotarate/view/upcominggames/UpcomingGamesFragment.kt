package com.ez.dotarate.view.upcominggames

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import com.ez.domain.model.UpcomingGame
import com.ez.dotarate.R
import com.ez.dotarate.adapters.BindingAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import com.ez.dotarate.Log as log


class UpcomingGamesFragment : Fragment() {

    private val vm: UpcomingGamesViewModel by viewModel(clazz = UpcomingGamesViewModel::class)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log.d("UpcomingGamesFragment. AfterCreateView")

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Screen()
            }
        }
    }

    @Composable
    fun Screen() {
        Column(modifier = Modifier.padding(top = 48.dp)) {
            Categories(mapOf(0 to "All", 1 to "Major", 2 to "INTERNATIONAL"))
            Games()
        }
    }

    @Composable
    fun Categories(categories: Map<Int, String>) {
        LazyRow {
            itemsIndexed(categories.values.toList()) { index, name ->
                Category(name)
            }
        }
    }

    @Composable
    fun Category(name: String) {
        Text(
            text = name,
            modifier = Modifier
                .border(
                    border = BorderStroke(width = 1.dp, color = Color.Black),
                    shape = RoundedCornerShape(2.dp)
                )
                .padding(horizontal = 8.dp)
        )
    }

    @Composable
    fun Games() {
        val lazyPagingItems = vm.liveUpcomingGames.collectAsLazyPagingItems()

        LazyColumn {
            if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
                item {
                    CircularProgressIndicator()
                }
            }

            itemsIndexed(lazyPagingItems) { index, game ->
                Game(game)
            }
        }
    }

    @Composable
    fun Game(game: UpcomingGame?) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                .border(
                    border = BorderStroke(width = 1.dp, color = Color.Black),
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            val (
                gameDate,
                leagueName,
                leagueImage,
                firstTeamImage,
                firstTeamName,
                gameTime,
                numberOfGames,
                secondTeamName,
                secondTeamImage
            ) = createRefs()

            Text(
                text = parseGameDate(game?.begin_at),
                modifier = Modifier.constrainAs(gameDate) {
                    start.linkTo(parent.start, margin = 8.dp)
                    top.linkTo(leagueImage.top)
                    bottom.linkTo(leagueImage.bottom)
                },
                fontSize = 18.sp,
                color = colorResource(R.color.colorTabsUnSelectedGreen)
            )

            Text(
                text = game?.league?.name ?: "",
                modifier = Modifier.constrainAs(leagueName) {
                    start.linkTo(gameDate.end, margin = 16.dp)
                    top.linkTo(leagueImage.top)
                    end.linkTo(leagueImage.start, margin = 8.dp)
                    bottom.linkTo(leagueImage.bottom)
                },
                fontSize = 18.sp,
                color = colorResource(R.color.colorTabsUnSelectedGreen),
                maxLines = 1
            )

            AsyncImage(
                model = game?.league?.image_url,
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(leagueImage) {
                        top.linkTo(parent.top, margin = 8.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                    }
                    .size(width = 30.dp, height = 30.dp)
            )

            parseTeamLogo(game?.opponents, 0)?.let {
                AsyncImage(
                    model = game?.opponents?.get(0)?.opponent?.image_url,
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(firstTeamImage) {
                            start.linkTo(parent.start, margin = 8.dp)
                            top.linkTo(secondTeamImage.top)
                            bottom.linkTo(parent.bottom, margin = 8.dp)
                        }
                        .size(width = 40.dp, height = 40.dp)
                )
            } ?: Image(
                painterResource(R.drawable.ic_no_logo),
                null,
                Modifier
                    .constrainAs(firstTeamImage) {
                        start.linkTo(parent.start, margin = 8.dp)
                        top.linkTo(secondTeamImage.top)
                        bottom.linkTo(parent.bottom, margin = 8.dp)
                    }
                    .size(width = 40.dp, height = 40.dp)
            )

            Text(
                text = parseTeamName(game?.opponents, 0) ?: "",
                modifier = Modifier.constrainAs(firstTeamName) {
                    start.linkTo(firstTeamImage.end, margin = 8.dp)
                    top.linkTo(firstTeamImage.top)
                    end.linkTo(gameTime.start, margin = 16.dp)
                    bottom.linkTo(firstTeamImage.bottom)
                },
                fontSize = 18.sp,
                color = colorResource(R.color.colorDarkGreen),
                maxLines = 1
            )

            Text(
                text = parseGameTime(game?.begin_at),
                modifier = Modifier.constrainAs(gameTime) {
                    start.linkTo(parent.start)
                    top.linkTo(firstTeamImage.top)
                    end.linkTo(parent.end)
//                    bottom.linkTo()
                },
                color = colorResource(R.color.colorGreen)
            )

            Text(
                text = parseNumberOfGames(game?.number_of_games),
                modifier = Modifier.constrainAs(numberOfGames) {
                    start.linkTo(gameTime.start)
                    top.linkTo(gameTime.bottom)
                    end.linkTo(gameTime.end)
                    bottom.linkTo(firstTeamImage.bottom)
                },
                color = colorResource(R.color.colorTabsUnSelectedGreen),
                fontSize = 12.sp
            )

            Text(
                text = parseTeamName(game?.opponents, 1) ?: "",
                modifier = Modifier.constrainAs(secondTeamName) {
                    start.linkTo(gameTime.end, margin = 16.dp)
                    top.linkTo(secondTeamImage.top)
                    end.linkTo(secondTeamImage.start, margin = 8.dp)
                    bottom.linkTo(secondTeamImage.bottom)
                },
                fontSize = 18.sp,
                color = colorResource(R.color.colorDarkGreen),
                maxLines = 1
            )

            parseTeamLogo(game?.opponents, 1)?.let {
                AsyncImage(
                    model = parseTeamLogo(game?.opponents, 1),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(secondTeamImage) {
                            top.linkTo(leagueImage.bottom, margin = 16.dp)
                            end.linkTo(parent.end, margin = 8.dp)
                        }
                        .size(width = 40.dp, height = 40.dp)
                )
            } ?: Image(
                painterResource(R.drawable.ic_no_logo),
                null,
                Modifier
                    .constrainAs(secondTeamImage) {
                        top.linkTo(leagueImage.bottom, margin = 16.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                    }
                    .size(width = 40.dp, height = 40.dp)
            )
        }
    }

    @Preview
    @Composable
    fun CategoryPreview() {
        Category("All")
    }

    private fun twitchIsInstalled(): Boolean {
        val packageName = "tv.twitch.android.app"
        return try {
            requireContext().packageManager.getPackageInfo(
                packageName,
                PackageManager.GET_ACTIVITIES
            )
            true
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            false
        }
    }

    private fun loadTwitch() {
        val packageName = "tv.twitch.android.app"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=$packageName")
        requireContext().startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        log.d("UpcomingGamesFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        log.d("UpcomingGamesFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log.d("UpcomingGamesFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        log.d("UpcomingGamesFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        log.d("UpcomingGamesFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        log.d("UpcomingGamesFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        log.d("UpcomingGamesFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        log.d("UpcomingGamesFragment. onDestroy")
    }
}