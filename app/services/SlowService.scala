package services

class SlowService {

  private val a: BigInt = BigInt(Long.MaxValue) * BigInt(Long.MaxValue)
  private val b: BigInt = BigInt(Long.MaxValue) * BigInt(Long.MaxValue)

  def loopIterator(count: Int): Unit = {
    (0 to count).foreach{ _ => val c = a*b }
  }

}
