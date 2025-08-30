package edu.group.learning_contract
// shoutout to: https://www.youtube.com/watch?v=saKrGCWlJDs

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen // Added for splash screen
import androidx.viewpager2.widget.ViewPager2
import androidx.compose.material3.Text // Added for Compose
import androidx.compose.runtime.Composable // Added for Compose
import androidx.compose.ui.tooling.preview.Preview // Added for Compose Preview


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: StudentAdapter
    private lateinit var studentList: List<Student>
    private lateinit var dotsLayout: LinearLayout
    private lateinit var dots: Array<TextView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen() // Added for splash screen

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        dotsLayout = findViewById(R.id.dotsLayout)

        // my dear groupmates: your images are needed here. the img files in the drawables are temporary for now...
        studentList = listOf(
            Student(R.drawable.img5, "Leonard Damong", "BSIT - WebTech", "Baguio",
                "My primary interest lies in understanding the core principles of mobile-first design and user experience for Android. I aim to create applications that are not only functional but also intuitive and visually engaging. Learning to adapt web development concepts to the specific constraints and advantages of mobile platforms, particularly using Kotlin and perhaps Jetpack Compose, is a key motivator for me.",
                "Coming from a web-centric background, I anticipate that grasping Android-specific architecture patterns, the activity and fragment lifecycle, and efficient state management will be challenging. Adapting to a new IDE like Android Studio and its build system (Gradle) might also present an initial learning curve. Ensuring performance across a diverse range of Android devices is another area of concern.",
                "By the end of this course, I want to have developed at least one complete Android application that showcases solid UI/UX design and effective use of Kotlin. I aim to be comfortable with Android Studio, understand core Android components, and be able to integrate external APIs. A personal goal is to explore responsive layouts that work well on both phones and tablets.",
                "I expect this course to provide a structured approach to Android development, with clear explanations of fundamental concepts and hands-on projects. I'm looking for practical experience in building user interfaces, managing data, and debugging applications. Insights into current industry best practices and emerging trends in Android development would also be highly valued.",
                "I plan to contribute by actively participating in discussions, leveraging my web development background to offer different perspectives where applicable, and assisting with UI design and front-end logic. I'm also keen on exploring and sharing different approaches to UI implementation and user interaction patterns we learn in class."
            ),
            Student(R.drawable.img1, "Erwin Gaspay", "BSIT - WebTech", "Baguio",
                "I am motivated to delve into the native capabilities of Android, focusing on how to build applications that are deeply integrated with the device hardware and operating system features. I am particularly interested in aspects like background services, notifications, and local data storage using Room or SQLite. Understanding mobile security best practices is also a significant driver for me.",
                "The complexity of managing background tasks efficiently without impacting battery life or user experience seems like a potential hindrance. Properly handling permissions for sensitive data and device features, and ensuring data security in local storage will require careful study. Debugging issues related to concurrency and background processing might also be challenging.",
                "My goal is to develop an Android application that demonstrates effective use of background processing, local data persistence, and secure coding practices. I want to gain proficiency in Kotlin, understand how to work with Android services and broadcast receivers, and learn to implement robust error handling and data validation.",
                "I expect the course to cover topics like Android's process model, inter-component communication, data storage options, and security considerations in detail. I hope for practical examples and projects that involve these aspects, along with guidance on testing applications that have significant background components or handle sensitive user data.",
                "I intend to contribute by focusing on the application's backend logic, data management, and security aspects in group projects. I am willing to research and share best practices for secure data handling and efficient background task management. I can also help in creating test cases for these functionalities."
            ),
            Student(R.drawable.img2, "Raja Rane Mandapat", "BSIT - WebTech", "Baguio",
                "My motivation is to explore the creative possibilities of mobile app development, particularly in creating utility apps or simple games. I'm interested in learning how to translate an idea into a functional mobile application, focusing on user interaction design and visual feedback. The ability to quickly prototype and iterate on mobile app ideas is very appealing.",
                "The primary hindrance I foresee is mastering the UI development tools and paradigms specific to Android, such as XML layouts or Jetpack Compose, and making them behave as intended. Effectively managing different screen densities and aspect ratios to ensure a consistent look and feel could be tricky. Debugging UI-related issues can also be time-consuming.",
                "My main goal is to build a portfolio of several small but polished Android applications or prototypes by the end of the course. I aim to become proficient in either XML layouts with ViewBinding/DataBinding or Jetpack Compose for UI development. I also want to learn how to incorporate simple animations and transitions to enhance user experience.",
                "I expect this course to provide a solid foundation in Android UI development, covering both traditional and modern approaches. I'm hoping for design-focused projects that allow for creativity, along with constructive critiques on UI/UX choices. Learning about resources and tools for rapid prototyping would also be very helpful.",
                "My contribution will be centered around UI/UX design and implementation within group projects. I can create visual mockups, work on the front-end development, and focus on the overall user flow and aesthetics of the application. I am also happy to experiment with different UI libraries and share my findings."
            ),
            Student(R.drawable.img3, "Joshua Saysayen", "BSIT - WebTech", "Baguio",
                "I am highly motivated to understand how mobile applications can leverage cloud services and external APIs to provide rich, dynamic experiences. I'm excited by the prospect of building apps that connect to backend systems for data storage, user authentication, or to access third-party services like maps, weather, or social media.",
                "A potential challenge will be understanding asynchronous programming (e.g., Kotlin Coroutines, Flow) thoroughly to handle network requests without blocking the UI. Managing API keys securely and handling various network error conditions gracefully will also require careful attention. Integrating different third-party SDKs and ensuring they work well together might also be complex.",
                "My goal is to develop an Android application that seamlessly integrates with at least one major cloud service (like Firebase for authentication and database) and one third-party REST API. I aim to become proficient in using libraries like Retrofit for network calls and understand how to parse JSON/XML data effectively. Learning about OAuth and other authentication mechanisms is also on my list.",
                "I expect the course to provide comprehensive coverage of networking in Android, including making API calls, handling responses, and best practices for managing data fetched from remote sources. Guidance on using popular libraries for networking and image loading, as well as integrating with Firebase or similar BaaS platforms, would be extremely valuable.",
                "I aim to contribute by taking the lead on integrating cloud services and third-party APIs in our group projects. I can handle the networking layer, data serialization/deserialization, and ensure secure and efficient communication with backend systems. I'm also interested in setting up analytics and crash reporting using cloud-based tools."
            )
        )

        adapter = StudentAdapter(studentList)
        viewPager.adapter = adapter

        // Initialize dots and set initial page
        if (studentList.isNotEmpty()) {
            setupDots(studentList.size, 0) // Initially, dots for the first actual item
            viewPager.setCurrentItem(1, false) // Start on the first actual page (index 1)
        } else {
            setupDots(0, 0) // Handle empty list
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    if (studentList.isNotEmpty()) {
                        val actualItemCount = studentList.size
                        val currentViewPagerItem = viewPager.currentItem

                        if (currentViewPagerItem == 0) {
                            // Landed on phantom page at the start, jump to the last actual page
                            viewPager.setCurrentItem(actualItemCount, false) // Last actual page is at index 'actualItemCount'
                        } else if (currentViewPagerItem == actualItemCount + 1) {
                            // Landed on phantom page at the end, jump to the first actual page
                            viewPager.setCurrentItem(1, false) // First actual page is at index '1'
                        }
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (studentList.isNotEmpty()) {
                    val actualItemCount = studentList.size
                    var currentDotPosition = 0 // Default for safety

                    if (actualItemCount > 0) { // Ensure not dividing by zero if list becomes empty
                        currentDotPosition = when (position) {
                            0 -> actualItemCount - 1 // Phantom start, dot for last actual item
                            actualItemCount + 1 -> 0 // Phantom end, dot for first actual item
                            else -> position - 1    // Actual items (1-indexed in ViewPager)
                        }
                    }
                    setupDots(actualItemCount, currentDotPosition)
                } else {
                    setupDots(0, 0) // Handle empty list
                }
            }
        })
    }

    private fun setupDots(count: Int, currentPosition: Int) {
        dots = arrayOfNulls(count)
        dotsLayout.removeAllViews()

        for (i in 0 until count) {
            dots[i] = TextView(this).apply {
                text = "●"
                textSize = 18f
                setTextColor(if (i == currentPosition) Color.BLACK else Color.GRAY)
            }
            dotsLayout.addView(dots[i])
        }
    }
}

// Added Composable functions for Preview
@Composable
fun SimpleGreetingPreview(name: String) {
    Text(text = "Hello $name! This is a Compose Preview.")
}

@Preview(showBackground = true, name = "Default App Preview")
@Composable
fun AppDefaultPreview() {
    // If you have a Compose Theme (e.g., in a Theme.kt file for Compose),
    // you should wrap SimpleGreetingPreview with it for a more accurate preview:
    // YourAppComposeTheme {
    //     SimpleGreetingPreview("Android")
    // }
    // For now, a direct call:
    SimpleGreetingPreview("Android")
}
