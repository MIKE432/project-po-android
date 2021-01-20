package com.apusart.got_android.api.tools

import com.apusart.got_android.api.models.*
import java.util.*

object Defaults {
    const val baseUrl = "http://10.0.2.2:8000/"
}

object Test {
    val segments = listOf(
        Segment(
            10,
            Point(10, "dsa", 2.0, 0.2, 0.2, MountainGroup("dsaasd", "asd", Region(10, "dsa"))),
            Point(10, "dsa", 2.0, 0.4, 0.5, MountainGroup("dsa", "dsa", Region(10, "dsa"))),
            "dsadsadsa",
            true,
            10.0
        ),
        Segment(
            10,
            Point(10, "dsa", 2.0, 0.4, 0.5, MountainGroup("dsaasd", "asd", Region(10, "dsa"))),
            Point(10, "dsa", 2.0, 0.8, 0.2, MountainGroup("dsa", "dsa", Region(10, "dsa"))),
            "dsadsadsa",
            true,
            10.0
        ),
        Segment(
            10,
            Point(10, "dsa", 0.6, 0.8, 0.2, MountainGroup("dsaasd", "asd", Region(10, "dsa"))),
            Point(10, "dsa", 2.0, 0.9, 1.0, MountainGroup("dsa", "dsa", Region(10, "dsa"))),
            "dsadsadsa",
            true,
            10.0
        ),
        Segment(
            10,
            Point(10, "dsa", 2.0, 0.2, 0.5, MountainGroup("dsaasd", "asd", Region(10, "dsa"))),
            Point(10, "dsa", 2.0, 0.8, 0.9, MountainGroup("dsa", "dsa", Region(10, "dsa"))),
            "dsadsadsa",
            true,
            10.0
        ),
        Segment(
            10,
            Point(10, "dsa", 2.0, 0.2, 0.5, MountainGroup("dsaasd", "asd", Region(10, "dsa"))),
            Point(10, "dsa", 2.0, 0.8, 0.9, MountainGroup("dsa", "dsa", Region(10, "dsa"))),
            "dsadsadsa",
            true,
            10.0
        ),
        Segment(
            10,
            Point(10, "dsa", 2.0, 0.2, 0.5, MountainGroup("dsaasd", "asd", Region(10, "dsa"))),
            Point(10, "dsa", 2.0, 0.8, 0.9, MountainGroup("dsa", "dsa", Region(10, "dsa"))),
            "dsadsadsa",
            true,
            10.0
        ),
        Segment(
            10,
            Point(
                10,
                "dsa",
                2.0,
                0.9,
                0.2,
                MountainGroup("adsadsada", "dadsadsa", Region(10, "dsa"))
            ),
            Point(10, "dsa", 2.0, 0.2, 0.5, MountainGroup("dsa", "dsa", Region(10, "dsa"))),
            "dsadsadsa",
            true,
            10.0
        )
    )
    val people = listOf<Person>()

    val verifiedSegments = listOf(
        VerifiedSegment(
            15,
            Date(2020, 11, 12),
            false,
            0,
            segments[0]
        ),
        VerifiedSegment(
            15,
            Date(2020, 11, 12),
            false,
            1,
            segments[1]
        ),
        VerifiedSegment(
            15,
            Date(2020, 11, 12),
            false,
            2,
            segments[2]
        )
    )

    val routes = listOf<Route>(
        Route(
            10,
            "Nazwa trasy",
            30,
            Date(2020, 11, 12),
            Date(2020, 11, 14),
            verifiedSegments
            )
    )
    val trips = listOf(
        Trip(
            10,
            "W góry",
            Date(2020, 11, 12),
            Date(2020, 11, 14),
            listOf(),
            routes[0]
        )
    )
}