package example.typeClassesTrain

sealed trait JsonValue
case class JsonObject (entries: Map[String, JsonValue]) extends JsonValue
case class JsonArray (entries: Seq[JsonValue]) extends JsonValue
case class JsonString (value: String) extends JsonValue
case class JsonNumber (value: Int) extends JsonValue
case object JsonNull extends JsonValue

