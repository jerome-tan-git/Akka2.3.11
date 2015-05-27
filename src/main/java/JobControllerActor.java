import akka.actor.UntypedActor;


public class JobControllerActor extends UntypedActor {
	 
    int count = 0;
    long startedTime = System.currentTimeMillis();
    int no_of_msgs = 0;
    public JobControllerActor (int _no_of_msgs)
    {
    	this.no_of_msgs = _no_of_msgs;
    }
    @Override //��������workActor��Ϣ
    public void onReceive(Object message) throws Exception {

           if (message instanceof String) {
                  if (((String) message).compareTo("Done") == 0) {
                         count++;//��������
                         if (count == no_of_msgs) {//��������1000�������ͳ��ʱ��
                                long now = System.currentTimeMillis();
                                System.out.println("All messages processed in "
                                              + (now - startedTime) / 1000 + " seconds");

                                System.out.println("Total Number of messages processed "
                                              + count);
                                getContext().system().shutdown();
                         }
                  }
           }

    }
}
