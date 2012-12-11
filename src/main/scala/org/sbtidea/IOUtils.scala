package org.sbtidea

import java.io.{File => JFile}
import sbt.{IO, File}

object IOUtils {

  def replaceUserHome(path: String): String = { 
    val userHome = SystemProps.userHome
    if (path.indexOf(userHome) == 0) {
      val offset = if (userHome.endsWith(JFile.separator)) 1 else 0
      "$USER_HOME$" + path.substring(userHome.length - offset)
    } else {
      path
    }
  }

  def relativePath(base: File, file: File, prefix: String) =
    IO.relativize(base, file.getCanonicalFile).map(prefix + _).getOrElse(replaceUserHome(file.getCanonicalPath))
}
