import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel

object Server {
  private val QUEUE_NAME = "hello"

  def main(args: Array[String]): Unit ={
    println("Hello by server")
    val factory = new ConnectionFactory
    factory.setHost("localhost")
    val connection:Connection = factory.newConnection()
    val channel:Channel = connection.createChannel()
    try {
      channel.queueDeclare(QUEUE_NAME, false, false, false, null)
      val message = "Hello World!"
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes)
      System.out.println(" [x] Sent '" + message + "'")
    }
  }

}
