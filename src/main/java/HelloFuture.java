
 
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
 
 
/**
 * Created by liyanxin on 2015/1/8.
 */
public class HelloFuture {
 
    public static class A extends UntypedActor {
 
        private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
 
        @Override
        public void onReceive(Object message) throws Exception {
            if (message instanceof String) {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " sleep end");
                System.out.println("���յ���Ϣ��" + message);
                // ����һ����Ϣ
                this.getSender().tell("hello world", this.getSelf());
                System.out.println("sender path=" + this.getSender().path());
                getContext().stop(this.getSelf());
                log.info("|||{} has stop", this.getSelf().path());
            }
        }
    }
 
    public static void main(String args[]) throws Exception {
        System.out.println(Thread.currentThread().getName());
        ActorSystem system = ActorSystem.create("mySystem");
        ActorRef a = system.actorOf(Props.create(A.class), "helloWorld");
        Timeout timeout = new Timeout(Duration.create(5, "seconds"));
        Future<Object> future = Patterns.ask(a, "are you ready?", timeout);
 
        // This will cause the current thread to block and wait for the UntypedActor to ��complete��
        // the Future with it��s reply.
        // ������������� Await.result �����ϣ�����ᵼ�����ܵ���ʧ��
        String result = (String) Await.result(future, timeout.duration());
        
        System.out.println(result);
    }
 
}