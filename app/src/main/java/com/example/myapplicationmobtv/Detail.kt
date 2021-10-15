package com.example.myapplicationmobtv


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplicationmobtv.MainActivity.Companion.feed
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory


class Detail : Fragment() {
    private var videoPlayer: SimpleExoPlayer? = null
    private var sampleUrl =
        "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"

    // private var sampleUrl ="https://archive.org/download/Popeye_forPresident/Popeye_forPresident_512kb.mp4"
    //  private var sampleUrl ="http://roku.content.video.llnw.net/smedia/59021fabe3b645968e382ac726cd6c7b/An/Ad7-vxssRzyBjjdbdLdikctclTqxwEGsVWZGpiKp4/roku_ep_115_segment_5_paula_nw_050515.mp4"
    // private var sampleUrl="http://roku.content.video.llnw.net/smedia/59021fabe3b645968e382ac726cd6c7b/p-/IBQ_ar_jBKNjk2ZGYCNHHNcN7v6Qy2Gf4V517CuX0/roku_ep_115_segment_1_ted_nw_050515.mp4"
    private lateinit var episodeUrl: String

    private var isFinishing = false
    private var episodeNumber = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        episodeNumber = arguments?.getInt("episodeNumber") ?: 0


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.video_detail, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        episodeUrl =
            feed?.series?.get(0)?.seasons?.get(0)?.episodes?.get(episodeNumber)?.content?.videos?.get(
                0
            )?.url!!
        Log.d("Tag", " episodeNumber ================== $episodeNumber")
        initializePlayer(view)


    }

    private fun buildMediaSource(): MediaSource {
        val dataSourceFactory = DefaultDataSourceFactory(requireContext(), "sample")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(sampleUrl)))
    }

    private fun initializePlayer(view: View) {
        videoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
        (view.findViewById<PlayerView>(R.id.video_player_view)).player = videoPlayer
        buildMediaSource().let {
            videoPlayer?.setMediaItem(MediaItem.fromUri(Uri.parse(sampleUrl)))
        }


        isFinishing = true
    }

    override fun onResume() {
        super.onResume()
        videoPlayer?.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        videoPlayer?.playWhenReady = false

        if (isFinishing) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        videoPlayer?.release()
    }


}

