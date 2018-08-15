package com.tanipa.mergerequester

import akka.actor.typed.ActorRef
import cats.effect._
import com.tanipa.mergerequester.msg.MergeRequestStarted
import org.http4s.server.Server
import org.http4s.server.blaze.BlazeBuilder
// import cats.effect._

import org.http4s._
import org.http4s.dsl.io._

object REST {

  def apply(actorRef: ActorRef[MergeRequestStarted]): IO[Server[IO]] = {

    val service = HttpService[IO] {
      case x@POST -> Root / "test" =>
        x.decode[String] { data =>
          actorRef ! MergeRequestStarted(data)
          Ok()
        }
    }

    BlazeBuilder[IO].bindHttp(8080, "0.0.0.0")
      .mountService(service)
      .start

  }

}
