package dsesignpatterndeocrator1;

import java.util.List;



public class BaseNotifier implements Notifier {
	List<Notifier> notifiers;
	

	public BaseNotifier(List<Notifier> notifiers) {
		super();
		this.notifiers = notifiers;
	}

	@Override
	public void sendMeeage() {
		for (Notifier aNotifier : notifiers) {
			aNotifier.sendMeeage();
		}

	}

}
