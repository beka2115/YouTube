package com.example.youtube

import android.graphics.pdf.PdfDocument.PageInfo

data class PlayList(
    val kind: String?,
    val items: List<ItemsData>
)

data class ItemsData(
    val snippet: SnippetData,
    val id: String,
    val contentDetails: ContentDetails
)

data class ContentDetails(
    val itemCount: Int,
    val videoId: String
)

data class SnippetData(
    val description: String,
    val title: String,
    val thumbnails: Thumbnails
)

data class Thumbnails(
    val maxres: Maxres
)

data class Maxres(
    val url: String,
    val height: Int,
    val width: Int
)

