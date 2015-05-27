import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import akka.routing.RoundRobinRouter;
 
/**
 * Created by liyanxin on 2015/1/8.
 */
public class HelloWorld {
 
    /**
     * 在这里实现这样一个功能，A actor给 B actor 发送消息，接收后返回消息说已收到
     */
    public static class A extends UntypedActor {
//        @Override
//        public void preStart() throws Exception {
// 
//            // 使用当前actor的context 创建了一个子actor，B actor就是A actor的子actor
//            // using an actor’s context will create a child actor
//            final ActorRef child =
//                    getContext().actorOf(Props.create(B.class), "myChild");
//            child.tell("good moring", this.getSelf());
//        }
 
        @Override
        public void onReceive(Object message) throws Exception {
            if (message instanceof String) {
                System.out.println("接收到B Actor的消息：" + message);
                Thread.sleep(1000);
//                getContext().stop(getSelf());
            }
        }
    }
 
    public static class B extends UntypedActor {
        @Override
        public void onReceive(Object message) throws Exception {
            if (message instanceof String) {
                System.out.println("接收到A Actor的消息：" + message);
                this.getSender().tell("thank you!", this.getSelf());
            }
        }
    }
 
 
    public static void main(String args[]) throws InterruptedException {
    	Config conf = ConfigFactory.load();
    	System.out.println(conf.getConfig("my-dispatcher"));
        ActorSystem system = ActorSystem.create("test");
        
        // Actors are created by passing a Props instance into the actorOf factory method which is available on
        // ActorSystem and ActorContext.
        // 通过ActorSystem 和 ActorContext的工场方法actorOf创建actor
        // 工场方法需要接收一个Props instance
        ActorRef child = system.actorOf(new RoundRobinPool(5).props(Props.create(A.class)), "myactor");
        for(int i=0;i<6;i++)
        {
        	child.tell("test" + i, null);
        	System.out.println("msg: " + i);
        }
        
//        Thread.sleep(1000);
//        child.tell("aaaaaaaaaa", null);
//        system.actorOf(Props.create(B.class), "helloWorld1");
       // system.shutdown();
    }
}