package com.example.codingthoughtctl.ui_layer.home

import com.example.codingthoughtctl.data_layer.remote.ImgurApiHelper
import com.example.codingthoughtctl.data_layer.remote.ImgurApiHelperImpl
import com.example.codingthoughtctl.data_layer.remote.ImgurApiService
import com.example.codingthoughtctl.data_layer.remote.models.Data
import com.example.codingthoughtctl.data_layer.remote.models.DescriptionAnnotations
import com.example.codingthoughtctl.data_layer.remote.models.Image
import com.example.codingthoughtctl.data_layer.remote.models.ImgurResponce
import com.example.codingthoughtctl.data_layer.remote.models.Processing
import com.example.codingthoughtctl.data_layer.remote.models.Tag
import com.example.codingthoughtctl.data_layer.remote.repository.ImgurRepository
import com.example.codingthoughtctl.domain_layer.GetTopWeeklyImagesUseCase
import com.example.codingthoughtctl.utilities.NetworkResult
import com.google.common.truth.Truth
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class TopImagesViewModelTest {

    //private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var useCase: GetTopWeeklyImagesUseCase
    private lateinit var viewModel: TopImagesViewModel
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: ImgurApiService
    private lateinit var helper: ImgurApiHelper
    private lateinit var repository: ImgurRepository

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
        useCase = GetTopWeeklyImagesUseCase(imgurRepository = repository)
        viewModel = TopImagesViewModel(topWeekUseCase = useCase)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        testDispatcher.cancel()
    }

    @Test
    fun `get top weekly images, valid responce`() = runTest {

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

        launch(testDispatcher) {
            useCase("top", "top", "week")
        }

        val result = viewModel.topImagesResult.value
        result?.let {
            Truth.assertThat(it).isInstanceOf(NetworkResult.Success::class.java)
            Truth.assertThat(it?.data?.status).isEqualTo(HttpURLConnection.HTTP_OK)
            Truth.assertThat(it?.data?.success).isTrue()
            Truth.assertThat(it?.data).isEqualTo(expectedResponse)
        }

    }

    @Test
    fun `for loading resource, data should be null && isLoading should be true`() = runTest {

        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(null))
        mockWebServer.enqueue(expectedResponse)

        launch(testDispatcher) {
            useCase("top", "top", "week")
        }

        val result = viewModel.topImagesResult.value
        result?.let {
            Truth.assertThat(result).isInstanceOf(NetworkResult.Loading::class.java)
            Truth.assertThat(it?.data?.status).isEqualTo(HttpURLConnection.HTTP_OK)
            Truth.assertThat(it?.data?.success).isFalse()
            Truth.assertThat(it?.data).isEqualTo(expectedResponse)
        }
    }

    @Test
    fun `for error resource, data should be null && hasError should be true && errorMessage should be Error`() =
        runTest {

            val expectedResponse = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                .setBody(Gson().toJson(null))
            mockWebServer.enqueue(expectedResponse)

            launch(testDispatcher) {
                useCase("top", "top", "week")
            }

            val result = viewModel.topImagesResult.value
            result?.let {
                Truth.assertThat(it).isEqualTo(NetworkResult.Error::class.java)
                Truth.assertThat(it?.message).isEqualTo("Not Found")
                Truth.assertThat(it?.data?.status).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND)
                Truth.assertThat(it?.data?.success).isFalse()
                Truth.assertThat(it?.data).isEqualTo(expectedResponse)
            }
        }
}