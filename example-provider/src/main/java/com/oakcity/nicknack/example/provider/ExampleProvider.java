package com.oakcity.nicknack.example.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.oakcity.nicknack.core.Unit;
import com.oakcity.nicknack.core.actions.Action;
import com.oakcity.nicknack.core.actions.Action.ActionDefinition;
import com.oakcity.nicknack.core.events.Event.EventDefinition;
import com.oakcity.nicknack.core.providers.OnEventListener;
import com.oakcity.nicknack.core.providers.Provider;

public class ExampleProvider implements Provider {
	
	private final List<EventDefinition> eventDefinitions;
	private final List<ActionDefinition> actionDefinitions;
	
	final Random random = new Random();
	
	final SwitchChangeEventFactory switchChangeEventFactory = new SwitchChangeEventFactory();
	
	public ExampleProvider () {
		eventDefinitions = new ArrayList<EventDefinition>();
		actionDefinitions = new ArrayList<ActionDefinition>();
		
		// Switch change event.
		eventDefinitions.add(SwitchChangeEventDefinition.INSTANCE);
		actionDefinitions.add(new LightBulbActionDefinition());
	}
	

	@Override
	public List<Unit<?>> getUnits() {
		// Does not define any new Units.
		return Collections.emptyList();
	}

	@Override
	public List<EventDefinition> getEventDefinitions() {
		return Collections.unmodifiableList(eventDefinitions);
	}

	@Override
	public List<ActionDefinition> getActionDefinitions() {
		return Collections.unmodifiableList(actionDefinitions);
	}


	@Override
	public String getName() {
		return "Example Light Bulb and Switch Provider";
	}


	@Override
	public int getVersion() {
		return 1;
	}


	@Override
	public String getAuthor() {
		return "NickNack";
	}

	@Override
	public void run(Action action) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init(final OnEventListener onEvent) throws Exception {
		// Every 30 seconds, the light switch is randomly turned on or off.
		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				final String macAddress = "0b:41:c1:3a:89:36";
				final boolean position = random.nextBoolean();
				onEvent.onEvent(switchChangeEventFactory.newEvent(position, macAddress));
			}
			
		}, 10000, 10000);
	}

}
