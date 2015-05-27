import scala.sys.Prop;
import scala.sys.Prop.Creator;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import akka.routing.RoundRobinRouter;


public class ApplicationManagerSystem  {

	private ActorSystem system;
//	private final ActorRef router;
	private final static int no_of_msgs = 10 * 1000000;

//	public ApplicationManagerSystem() {

//		final int no_of_workers = 10;
//
//		system = ActorSystem.create("LoadGeneratorApp");
//		
//		
////		final ActorRef child = system.actorOf(Props.create(A.class), "master");
//		  
//		
////		final ActorRef appManager = system.actorFor(
////			    Props.create(new MyActorC(no_of_msgs),  "jobController"));
////		final ActorRef appManager = system.actorOf(
////				new Props(new UntypedActorFactory() {
////					public UntypedActor create() {
////						return new JobControllerActor(no_of_msgs);
////					}
////				}), "jobController");
//		
////		router = system.actorOf(new Props(new UntypedActorFactory() {
////			public UntypedActor create() {
////				return new WorkerActor(appManager);
////			}
////		}).withRouter(new RoundRobinRouter(no_of_workers)));
//	}
//
//	private void generateLoad() {
//		for (int i = no_of_msgs; i >= 0; i--) {
//			router.tell("Job Id " + i + "# send", ActorRef.noSender());
//		}
//		System.out.println("All jobs sent successfully");
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("LoadGeneratorApp");
		ActorRef appManager = system.actorOf(Props.create(JobControllerActor.class, no_of_msgs), "jobController");
		ActorRef child = system.actorOf(Props.create(WorkerActor.class, appManager).withRouter(new RoundRobinRouter(10)), "myactor");
		// create the object and generate the load
//		new ApplicationManagerSystem().generateLoad();

	}
//	static class MyActorC implements Creator<JobControllerActor> {
//		private int no_of_msg = 100;
//		public MyActorC(int _no_of_msg){
//			this.no_of_msg = _no_of_msg;
//		}
//		public JobControllerActor create() throws Exception {
//			// TODO Auto-generated method stub
//			return new JobControllerActor(no_of_msg);
//		}
//		public Prop<JobControllerActor> apply(String arg0) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	}

}