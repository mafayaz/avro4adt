object Experiment extends App {
  import java.io.ByteArrayOutputStream
  import com.sksamuel.avro4s._

  sealed trait Dibble
  case class Dobble(str: String) extends Dibble
  case class Dabble(dbl: Double) extends Dibble

  case class Drapper(dibble: Dibble)

  val drapperIn = Drapper(Dabble(2.0))
  val bios = new ByteArrayOutputStream
  val avro = AvroOutputStream.binary[Drapper](bios)
  avro.write(drapperIn)
  avro.close()

  val is = AvroInputStream.binary[Drapper](bios.toByteArray)
  val drapperOut = is.iterator().toList.head
  is.close()

  println(drapperIn == drapperOut)
}
