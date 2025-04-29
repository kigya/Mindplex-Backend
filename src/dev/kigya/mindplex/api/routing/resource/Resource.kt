package dev.kigya.mindplex.api.routing.resource

import io.ktor.resources.*

@Resource("/")
class RootResource

@Resource("/metrics")
class MetricsResource

@Resource("/questions")
class QuestionsResource

@Resource("/facts")
class FactsResource(val limit: Int? = 3)

@Resource("/user")
class UserResource {
    @Resource("/profile")
    class ProfileResource(val parent: UserResource)

    @Resource("/score")
    class ScoreResource(val parent: UserResource)

    @Resource("/generate-debug")
    class GenerateDebugResource(val parent: UserResource)
}

@Resource("/leaderboard")
class LeaderboardResource(val limit: Int? = 10)
