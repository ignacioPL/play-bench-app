package services

class SlowService {

  private val a: BigInt = BigInt(Long.MaxValue) * BigInt(Long.MaxValue)
  private val b: BigInt = BigInt(Long.MaxValue) * BigInt(Long.MaxValue)
  //71000000
  def loopIterator(count: Int): Unit = {
    (0 to count).foreach{ i =>
      val c = a*b*BigInt(i)
    }
  }

}
