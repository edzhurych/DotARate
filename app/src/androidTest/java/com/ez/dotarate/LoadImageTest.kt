package com.ez.dotarate

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.ez.dotarate.adapters.BindingAdapter
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock

class LoadImageTest {

    val context: Context = mock(Context::class.java)

    val view = ImageView(context)
    val urlString = "https://games.mail.ru/hotbox/content_files/news/2021/06/09/668f053d143e46da921b3c90b2270644.jpg"
    val errorImage: Drawable = mock(Drawable::class.java)

    @Test
    fun loadImage_isValid() {
        BindingAdapter.loadImage(view, urlString, errorImage)
        assertNotNull(view.drawable)
    }
}