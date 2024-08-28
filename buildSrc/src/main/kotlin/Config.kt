object Config {
    object Debug {
        const val BASE_URL = "\"https://api.github.com/\""
    }

    object Release {
        const val BASE_URL = "\"https://api.github.com/\""
    }
}

object Module {
    object Core {
        const val DOMAIN = ":core:domain"
        const val DATA = ":core:data"
        const val UTILS = ":core:utils"
        const val COMMON = ":core:common"
    }

    object Feature {
        const val USER = ":feature:user"
        const val HOME = ":feature:home"
    }
}