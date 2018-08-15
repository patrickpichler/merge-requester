package com.tanipa.mergerequester

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}
import com.tanipa.mergerequester.actors.GitlabMRActor

object Main extends App {

  final case class Start()

  val main: Behavior[Start] =
    Behaviors.setup { ctx =>
      val gitlabMR = ctx.spawn(GitlabMRActor.gitlabMR(), "gitlab-ci")

      REST(gitlabMR)
        .unsafeRunSync()

      Behaviors.empty
    }

  val system = ActorSystem[Start](main, "test")

  system ! Start()

}