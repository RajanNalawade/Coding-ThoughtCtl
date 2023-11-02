# Coding-Challenge-ThoughtCtl
1. Application runs properly with completion of around 45% unit testing.
2. To develop this challenge i used following components
   - CLEAN architecture with MVVM (ViewModel, LiveData)
   - Retrofit for remote api with Interceptors to add access token
   - I have saved all credentials related to Imgur api by using NDK, and access these with help of JNI
   - Used Hilt depedency injection to inject required objects
   - For unit testing I have used Junit, Mockito, MockWebServer, Google's Truth etc.
   - Used Jetpack Navigation Graph for simplisity
3. Working -
   - When application launches splash screen will appear
   - Then In HomeActivity, TopImagesFragment will gets attached
   - In TopImagesFrament, first i have fetched top-weekly images from api then attached to adapter for displaying as per **(Picture - 1)** in Listview and **(Picture - 2)** in GridView.
   - In TopImagesFragment we have 3 main functionalities
      - Show result in ListView when we click on list-icon.**(Picture - 1)**
      - Show result in GridView when we click on grid-icon.**(Picture - 2)**
      - On the basis of input text search we filter displaying images.**(Picture - 3)** and **(Picture - 4)**

| **Picture - 1** | **Picture - 2** |
| --- | --- |    
| ![#picture-1](https://github.com/RajanNalawade/Coding-ThoughtCtl/assets/46295390/92471585-a372-4055-bc35-8c3fea3667b1) | ![#picture-2](https://github.com/RajanNalawade/Coding-ThoughtCtl/assets/46295390/603afabe-0711-4299-b40f-ab2dcecb4003) |

| **Picture - 3** | **Picture - 4** |
| --- | --- | 
| ![#picture-3](https://github.com/RajanNalawade/Coding-ThoughtCtl/assets/46295390/cdae88a8-f68c-4399-8e28-3d8c493051e1) | ![#picture-4](https://github.com/RajanNalawade/Coding-ThoughtCtl/assets/46295390/5248f7bf-7918-44df-928a-056e421e9fea) | 
     
