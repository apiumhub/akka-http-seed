package com.apiumtech.akka.http.infr

import java.util.Date

import spray.json.{JsString, JsValue, RootJsonFormat}

/**
 * @author kevin 
 * @since 9/28/15.
 */
trait DefaultJsonConversions {
  implicit val dateFormat = new RootJsonFormat[Date] {
    lazy val format = new java.text.SimpleDateFormat("yyy-MM-dd'T'HH:mm'Z'")

    def read(json: JsValue) = format.parse(json.compactPrint)
    def write(date: Date) = JsString(format.format(date))
  }
}
