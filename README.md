# Coding-Challenge-ThoughtCtl

![Screenshot_20231102_154624](https://github.com/RajanNalawade/Coding-ThoughtCtl/assets/46295390/92471585-a372-4055-bc35-8c3fea3667b1)
![Screenshot_20231102_154900](https://github.com/RajanNalawade/Coding-ThoughtCtl/assets/46295390/603afabe-0711-4299-b40f-ab2dcecb4003)
![Screenshot_20231102_154830](https://github.com/RajanNalawade/Coding-ThoughtCtl/assets/46295390/cdae88a8-f68c-4399-8e28-3d8c493051e1)
![Screenshot_20231102_154804](https://github.com/RajanNalawade/Coding-ThoughtCtl/assets/46295390/5248f7bf-7918-44df-928a-056e421e9fea)

1. Application runs properly with completion of around 45% unit testing.
2. To develop this challenge i used following components
   a. CLEAN architecture with MVVM (ViewModel, LiveData)
   b. Retrofit for remote api with Interceptors to add access token
   c. I have saved all credentials related to Imgur api by using NDK, and access these with help of JNI
   d. Used Hilt depedency injection to inject required objects
   e. For unit testing I have used Junit, Mockito, MockWebServer, Google's Truth etc.

   f. Used Jetpack Navigation Graph for simplisity
4. Working -
  a. When application launches splash screen will appear
  b. Then In HomeActivity, TopImagesFragment will gets attached
  c. In TopImagesFrament, first i have fetched top-weekly images from api then attached to adapter for displaying as per #picture-1# in Listview and #picture-2# in GridView.
  d. In TopImagesFragment we have 3 main functionalities
      1. Show result in ListView when we click on list-icon. [picture-1]
      2. Show result in GridView when we click on grid-icon. [picture-2]
      3. On the basis of input text search we filter displaying images. [picture-3, picture-4]   
     
