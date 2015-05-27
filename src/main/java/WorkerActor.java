import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import scala.concurrent.duration.Duration;

public class WorkerActor extends UntypedActor {
	private ActorRef jobController;// jobController�ǽ�����

	@Override
	// ���ܵ�generateLoad�ķ�����Ϣ
	public void onReceive(Object message) throws Exception {
		// using scheduler to send the reply after 1000 milliseconds

		ActorSystem system = getContext().system();

		system.scheduler().scheduleOnce(
				Duration.create(1000, TimeUnit.MILLISECONDS), jobController,
				"Done", system.dispatcher(), null);// jobController�ǽ�����
	}

	public WorkerActor(ActorRef inJobController) {
		jobController = inJobController;
	}
}
