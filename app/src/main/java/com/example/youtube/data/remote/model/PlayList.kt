package com.example.youtube

import android.graphics.pdf.PdfDocument.PageInfo

data class PlayList(
    val kind: String?,
    val items: List<ItemsData>,
    val prevPageToken: String,
    val nextPageToken: String,
    val pageInfo:com.example.youtube.PageInfo
)

data class PageInfo(
    val resultsPerPage: Int,
    val totalResults: Int
)

data class ItemsData(
    val snippet: SnippetData,
    val id: String,
    val contentDetails: ContentDetails
)

data class ContentDetails(
    val itemCount: Int
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

