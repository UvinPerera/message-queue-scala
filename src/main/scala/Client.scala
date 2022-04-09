import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import java.nio.charset.StandardCharsets



object Client {

  private val QUEUE_NAME = "hello"

  def main(args: Array[String]): Unit ={
    print("Hello by client")
    val factory = new ConnectionFactory()
    factory.setHost("localhost")
    val connection:Connection = factory.newConnection()
    val channel:Channel = connection.createChannel()
    try{
      channel.queueDeclare(QUEUE_NAME, false, false, false, null)
      System.out.println(" [*] Waiting for messages. To exit press CTRL+C")

      val deliverCallback:DeliverCallback = (consumerTag: String, delivery: Delivery) => {
        def foo(consumerTag: String, delivery: Delivery) = {
          val message = new String(delivery.getBody, StandardCharsets.UTF_8)
          System.out.println(" [x] Received '" + message + "'")
        }

        foo(consumerTag, delivery)
      }

      channel.basicConsume(QUEUE_NAME, true, deliverCallback, (consumerTag:String) => {
        def foo(consumerTag:String) = {
        }
        foo(consumerTag)
      })
    }
  }

}
