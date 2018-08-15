package com.tanipa.mergerequester

package actors {

  import akka.actor.typed.Behavior
  import akka.actor.typed.scaladsl.Behaviors
  import com.tanipa.mergerequester.msg.MergeRequestStarted

  object GitlabMRActor {

    def gitlabMR(): Behavior[MergeRequestStarted] = Behaviors.receive { (ctx, msg) =>

      println(msg)

      gitlabMR()
    }

  }

}
