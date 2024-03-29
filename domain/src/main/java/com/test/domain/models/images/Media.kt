package com.test.domain.models.images

data class Media(
    val avg_color: String,
    val duration: Int,
    val full_res: Any,
    val height: Int,
    val id: Int,
    val image: String,
    val liked: Boolean,
    val photographer: String,
    val photographer_id: Int,
    val photographer_url: String,
    val src: SrcX,
    val tags: List<Any>,
    val type: String,
    val url: String,
    val user: User,
    val video_files: List<VideoFile>,
    val video_pictures: List<VideoPicture>,
    val width: Int
)