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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
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
                Text(text = game?.name ?: "")
            }
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