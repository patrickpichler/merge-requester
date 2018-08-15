package com.tanipa.mergerequester

import akka.actor.typed.ActorRef
import cats.effect._
import com.tanipa.mergerequester.msg.MergeRequestStarted
import org.http4s.Response
import org.http4s.server.Server
import org.http4s.server.blaze.BlazeBuilder
// import cats.effect._

import org.http4s._
import org.http4s.dsl.io._

object REST {

  def apply(actorRef: ActorRef[MergeRequestStarted]): IO[Server[IO]] = {

    val service = HttpService[IO] {
      case GET -> Root / "test" / name =>
        actorRef ! MergeRequestStarted(name)
        IO(Response(Status.Ok))

      case _ =>
        IO(Response(Status.NotFound))
    }

    BlazeBuilder[IO].bindHttp(8080, "localhost")
      .mountService(service)
      .start

  }

}
