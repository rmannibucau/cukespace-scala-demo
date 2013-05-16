package com.github.rmannibucau.cukespace.scala.demo

import org.junit.runner.RunWith
import cucumber.runtime.arquillian.ArquillianCucumber
import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import cucumber.api.scala.{EN, ScalaDsl}
import javax.inject.Inject
import org.junit.Assert.{assertEquals, assertNotNull}

object ScalaCukeSpaceTest {
  @Deployment
  def archive() = {
    ShrinkWrap.create(classOf[WebArchive], "cuke-in-space-with-scala.war")
      .addClass(classOf[ScalaStuff])
      .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
  }
}

@RunWith(classOf[ArquillianCucumber])
class ScalaCukeSpaceTest extends ScalaDsl with EN {
  @Inject
  private [this] var stuff: ScalaStuff = _

  private [this] var world: String = _

  Given( """^I have a stuff$""") {
    assertNotNull(stuff)
  }

  When( """^I ask the world where stuff is$""") {
    world = stuff.whereAmI()
  }

  Then( """^I get "([^"]*)"$""") { world: String =>
      assertEquals("Scala", world)
  }
}
