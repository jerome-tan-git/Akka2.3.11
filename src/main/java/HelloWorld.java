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
     * ������ʵ������һ�����ܣ�A actor�� B actor ������Ϣ�����պ󷵻���Ϣ˵���յ�
     */
    public static class A extends UntypedActor {
//        @Override
//        public void preStart() throws Exception {
// 
//            // ʹ�õ�ǰactor��context ������һ����actor��B actor����A actor����actor
//            // using an actor��s context will create a child actor
//            final ActorRef child =
//                    getContext().actorOf(Props.create(B.class), "myChild");
//            child.tell("good moring", this.getSelf());
//        }
 
        @Override
        public void onReceive(Object message) throws Exception {
            if (message instanceof String) {
                System.out.println("���յ�B Actor����Ϣ��" + message);
                Thread.sleep(1000);
//                getContext().stop(getSelf());
            }
        }
    }
 
    public static class B extends UntypedActor {
        @Override
        public void onReceive(Object message) throws Exception {
            if (message instanceof String) {
                System.out.println("���յ�A Actor����Ϣ��" + message);
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
        // ͨ��ActorSystem �� ActorContext�Ĺ�������actorOf����actor
        // ����������Ҫ����һ��Props instance
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