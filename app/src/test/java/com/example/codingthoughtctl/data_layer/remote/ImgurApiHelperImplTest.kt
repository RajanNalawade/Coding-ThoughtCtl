package com.example.codingthoughtctl.data_layer.remote

import com.example.codingthoughtctl.data_layer.remote.models.Data
import com.example.codingthoughtctl.data_layer.remote.models.DescriptionAnnotations
import com.example.codingthoughtctl.data_layer.remote.models.Image
import com.example.codingthoughtctl.data_layer.remote.models.ImgurResponce
import com.example.codingthoughtctl.data_layer.remote.models.Processing
import com.example.codingthoughtctl.data_layer.remote.models.Tag
import com.example.codingthoughtctl.data_layer.remote.repository.ImgurRepository
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ImgurApiHelperImplTest {

    private lateinit var api: ImgurApiService
    private lateinit var repository: ImgurRepository
    private lateinit var helper: ImgurApiHelper
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImgurApiService::class.java)
        helper = ImgurApiHelperImpl(api)
        repository = ImgurRepository(helper)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `for access token invalid, api must return with http code 404 and null data`() = runTest {
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.getTopWeeklyImages("top", "top", "week")
        assertThat(actualResponse.code()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND)
        assertThat(actualResponse.body()).isNull()
    }

    @Test
    fun `for server error, api must return code 5xx`() = runTest {
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.getTopWeeklyImages("top", "top", "week")
        assertThat(actualResponse.code()).isEqualTo(HttpURLConnection.HTTP_INTERNAL_ERROR)
    }

    @Test
    fun `for server error, api must return with http code 5xx and error message`() = runTest {
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.getTopWeeklyImages("top", "top", "week")
        assertThat(actualResponse.code()).isEqualTo(HttpURLConnection.HTTP_INTERNAL_ERROR)
        assertThat(actualResponse.message()).isEqualTo("Server Error")
    }

    @Test
    fun `for Data, api must return with http code 200 and Data object`() = runTest {
        val images = ImgurResponce(
            success = true,
            status = 200,
            data = listOf<Data>(
                Data(
                    id = "9uETttX",
                    title = "Who did it better?",
                    description = null,
                    datetime = 1698529444,
                    cover = "fXsjBbU",
                    coverWidth = 960,
                    coverHeight = 1708,
                    accountUrl = "DraconianMaryon",
                    accountId = 175531953,
                    privacy = "hidden",
                    layout = "blog",
                    views = 114075,
                    link = "https://imgur.com/a/9uETttX",
                    ups = 4249,
                    downs = 86,
                    points = 4163,
                    score = 4220,
                    isAlbum = true,
                    vote = null,
                    favorite = false,
                    nsfw = false,
                    section = "",
                    commentCount = 203,
                    favoriteCount = 838,
                    topic = null,
                    topicId = null,
                    imagesCount = 1,
                    inGallery = true,
                    isAd = false,
                    tags = listOf<Tag>(
                        Tag(
                            accent = "",
                            backgroundHash = "",
                            backgroundIsAnimated = false,
                            description = "",
                            descriptionAnnotations = DescriptionAnnotations(),
                            displayName = "",
                            followers = 0,
                            following = false,
                            isPromoted = false,
                            isWhitelisted = false,
                            logoDestinationUrl = "",
                            logoHash = "",
                            name = "",
                            thumbnailHash = "",
                            thumbnailIsAnimated = false,
                            totalItems = 0
                        )
                    ),
                    adType = 0,
                    adUrl = "",
                    inMostViral = true,
                    includeAlbumAds = false,
                    mp4Size = 0,
                    hasSound = true,
                    images = listOf(
                        Image(
                            id = "fXsjBbU",
                            title = null,
                            description = null,
                            datetime = 1698529398,
                            type = "video/mp4",
                            animated = true,
                            width = 960,
                            height = 1708,
                            size = 15679867,
                            views = 1777369,
                            bandwidth = 27868909529923,
                            vote = null,
                            favorite = false,
                            nsfw = null,
                            section = null,
                            accountUrl = null,
                            accountId = null,
                            isAd = false,
                            inMostViral = false,
                            hasSound = true,
                            tags = listOf<Tag>(
                                Tag(
                                    accent = "",
                                    backgroundHash = "",
                                    backgroundIsAnimated = false,
                                    description = "",
                                    descriptionAnnotations = DescriptionAnnotations(),
                                    displayName = "",
                                    followers = 0,
                                    following = false,
                                    isPromoted = false,
                                    isWhitelisted = false,
                                    logoDestinationUrl = "",
                                    logoHash = "",
                                    name = "",
                                    thumbnailHash = "",
                                    thumbnailIsAnimated = false,
                                    totalItems = 0
                                )
                            ),
                            adType = 0,
                            adUrl = "",
                            edited = "0",
                            inGallery = false,
                            link = "https://i.imgur.com/fXsjBbU.mp4",
                            mp4Size = 15679867,
                            mp4 = "https://i.imgur.com/fXsjBbU.mp4",
                            gifv = "https://i.imgur.com/fXsjBbU.gifv",
                            hls = "https://i.imgur.com/fXsjBbU.m3u8",
                            processing = Processing(status = "completed"),
                            commentCount = null,
                            favoriteCount = null,
                            ups = null,
                            downs = null,
                            points = null,
                            score = null,
                            looping = false
                        ),
                    ),
                    adConfig = null,
                    animated = false,
                    bandwidth = 0,
                    edited = 0,
                    gifv = "",
                    height = 5,
                    hls = "",
                    mp4 = "",
                    processing = Processing(status = ""),
                    size = 5,
                    type = "",
                    width = 9
                ), Data(
                    id = "9uETfdttX",
                    title = "Who did it better?",
                    description = null,
                    datetime = 1698529444,
                    cover = "fXsjBbU",
                    coverWidth = 960,
                    coverHeight = 1708,
                    accountUrl = "DraconianMaryon",
                    accountId = 175531953,
                    privacy = "hidden",
                    layout = "blog",
                    views = 114075,
                    link = "https://imgur.com/a/9uETttX",
                    ups = 4249,
                    downs = 860,
                    points = 4163,
                    score = 4220,
                    isAlbum = true,
                    vote = null,
                    favorite = false,
                    nsfw = false,
                    section = "",
                    commentCount = 203,
                    favoriteCount = 838,
                    topic = null,
                    topicId = null,
                    imagesCount = 1,
                    inGallery = true,
                    isAd = false,
                    tags = listOf<Tag>(
                        Tag(
                            accent = "",
                            backgroundHash = "",
                            backgroundIsAnimated = false,
                            description = "",
                            descriptionAnnotations = DescriptionAnnotations(),
                            displayName = "",
                            followers = 0,
                            following = false,
                            isPromoted = false,
                            isWhitelisted = false,
                            logoDestinationUrl = "",
                            logoHash = "",
                            name = "",
                            thumbnailHash = "",
                            thumbnailIsAnimated = false,
                            totalItems = 0
                        )
                    ),
                    adType = 0,
                    adUrl = "",
                    inMostViral = true,
                    includeAlbumAds = false,
                    mp4Size = 0,
                    hasSound = true,
                    images = listOf(
                        Image(
                            id = "fXsjBbU",
                            title = null,
                            description = null,
                            datetime = 1698529398,
                            type = "video/mp4",
                            animated = true,
                            width = 960,
                            height = 1708,
                            size = 15679867,
                            views = 1777369,
                            bandwidth = 27868909529923,
                            vote = null,
                            favorite = false,
                            nsfw = null,
                            section = null,
                            accountUrl = null,
                            accountId = null,
                            isAd = false,
                            inMostViral = false,
                            hasSound = true,
                            tags = listOf<Tag>(
                                Tag(
                                    accent = "",
                                    backgroundHash = "",
                                    backgroundIsAnimated = false,
                                    description = "",
                                    descriptionAnnotations = DescriptionAnnotations(),
                                    displayName = "",
                                    followers = 0,
                                    following = false,
                                    isPromoted = false,
                                    isWhitelisted = false,
                                    logoDestinationUrl = "",
                                    logoHash = "",
                                    name = "",
                                    thumbnailHash = "",
                                    thumbnailIsAnimated = false,
                                    totalItems = 0
                                )
                            ),
                            adType = 0,
                            adUrl = "",
                            edited = "0",
                            inGallery = false,
                            link = "https://i.imgur.com/fXsjBbU.mp4",
                            mp4Size = 15679867,
                            mp4 = "https://i.imgur.com/fXsjBbU.mp4",
                            gifv = "https://i.imgur.com/fXsjBbU.gifv",
                            hls = "https://i.imgur.com/fXsjBbU.m3u8",
                            processing = Processing(status = "completed"),
                            commentCount = null,
                            favoriteCount = null,
                            ups = null,
                            downs = null,
                            points = null,
                            score = null,
                            looping = false
                        ),
                    ),
                    adConfig = null,
                    animated = false,
                    bandwidth = 0,
                    edited = 0,
                    gifv = "",
                    height = 5,
                    hls = "",
                    mp4 = "",
                    processing = Processing(status = ""),
                    size = 5,
                    type = "",
                    width = 9
                )
            )
        )
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(images))
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.getTopWeeklyImages("top", "top", "week")
        actualResponse?.let {
            assertThat(it.message()).isEqualTo("OK")
            assertThat(it.code()).isEqualTo(HttpURLConnection.HTTP_OK)
            assertThat(it.body()?.data?.size).isGreaterThan(1)
        }
    }
}