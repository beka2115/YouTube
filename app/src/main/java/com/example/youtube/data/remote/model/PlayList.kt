package com.example.youtube

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
    val itemCount: Int
)

data class SnippetData(
    val description: String,
    val title: String,
    val thumbnails: Thumbnails
)

data class Thumbnails(
    val standard: Standard
)

data class Standard(
    val url: String,
    val height: Int,
    val width: Int
)

