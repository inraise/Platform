package compose.project.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform