package com.github.kmbulebu.nicknack.basicproviders.clock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.kmbulebu.nicknack.core.actions.Action;
import com.github.kmbulebu.nicknack.core.actions.ActionDefinition;
import com.github.kmbulebu.nicknack.core.actions.ActionFailureException;
import com.github.kmbulebu.nicknack.core.actions.ActionParameterException;
import com.github.kmbulebu.nicknack.core.attributes.impl.DayOfMonthAttributeDefinition;
import com.github.kmbulebu.nicknack.core.attributes.impl.DayOfWeekAttributeDefinition;
import com.github.kmbulebu.nicknack.core.attributes.impl.HourOfDayAttributeDefinition;
import com.github.kmbulebu.nicknack.core.attributes.impl.MinuteOfHourAttributeDefinition;
import com.github.kmbulebu.nicknack.core.attributes.impl.MonthOfYearNumericalAttributeDefinition;
import com.github.kmbulebu.nicknack.core.attributes.impl.SecondOfMinuteAttributeDefinition;
import com.github.kmbulebu.nicknack.core.attributes.impl.YearAttributeDefinition;
import com.github.kmbulebu.nicknack.core.events.EventDefinition;
import com.github.kmbulebu.nicknack.core.events.impl.BasicTimestampedEvent;
import com.github.kmbulebu.nicknack.core.providers.OnEventListener;
import com.github.kmbulebu.nicknack.core.providers.Provider;
import com.github.kmbulebu.nicknack.core.providers.ProviderConfiguration;
import com.github.kmbulebu.nicknack.core.providers.settings.ProviderSettingDefinition;
import com.github.kmbulebu.nicknack.core.states.State;
import com.github.kmbulebu.nicknack.core.states.StateDefinition;

/**
 * Provides real time clock capabilities to Nick Nack.
 * @author kmbulebu
 *
 */
public class ClockProvider implements Provider, Runnable {
	
	public static final UUID PROVIDER_UUID = UUID.fromString("58f948a0-f10c-11e3-8031-d31507193ec8");
	
	private List<EventDefinition> eventDefinitions;
	private List<ActionDefinition> actionDefinitions;
	private List<StateDefinition> stateDefinitions;
	private ScheduledExecutorService executorService;
	
	private OnEventListener onEventListener;
	
	@Override
	public UUID getUuid() {
		return PROVIDER_UUID;
	}
	@Override
	public String getName() {
		return "Clock";
	}
	@Override
	public String getAuthor() {
		return "Nick Nack";
	}
	@Override
	public int getVersion() {
		return 1;
	}
	
	@Override
	public List<EventDefinition> getEventDefinitions() {
		return Collections.unmodifiableList(eventDefinitions);
	}
	
	@Override
	public Collection<StateDefinition> getStateDefinitions() {
		return stateDefinitions;
	}
	
	@Override
	public List<ActionDefinition> getActionDefinitions() {
		return Collections.unmodifiableList(actionDefinitions);
	}
	
	@Override
	public void init(ProviderConfiguration configuration, OnEventListener onEventListener) throws Exception {
		final long nextSecond = ((System.currentTimeMillis() + 2000)/1000)*1000;
		final long initialDelay = nextSecond - System.currentTimeMillis();
		
		eventDefinitions = new ArrayList<>(1);
		eventDefinitions.add(ClockTickEventDefinition.INSTANCE);
		
		actionDefinitions = new ArrayList<>(0);
		
		stateDefinitions = new ArrayList<>(1);
		
		stateDefinitions.add(ClockStateDefinition.INSTANCE);
		
		executorService = Executors.newScheduledThreadPool(2);
		
		executorService.scheduleAtFixedRate(this, initialDelay, 1000, TimeUnit.MILLISECONDS);
		this.onEventListener = onEventListener;
	}

	@Override
	public void run() {
		// Fire off an Event.
		if (onEventListener != null) {
			onEventListener.onEvent(new BasicTimestampedEvent(ClockTickEventDefinition.INSTANCE));
		}
	}	

	@Override
	public Map<String, String> getAttributeDefinitionValues(UUID attributeDefinitionUuid) {
		if (DayOfMonthAttributeDefinition.INSTANCE.getUUID().equals(attributeDefinitionUuid)) {
			return DayOfMonthAttributeDefinition.INSTANCE.getStaticValues();
		} else if (DayOfWeekAttributeDefinition.INSTANCE.getUUID().equals(attributeDefinitionUuid)) {
			return DayOfWeekAttributeDefinition.INSTANCE.getStaticValues();
		} else if (HourOfDayAttributeDefinition.INSTANCE.getUUID().equals(attributeDefinitionUuid)) {
			return HourOfDayAttributeDefinition.INSTANCE.getStaticValues();
		} else if (MinuteOfHourAttributeDefinition.INSTANCE.getUUID().equals(attributeDefinitionUuid)) {
			return MinuteOfHourAttributeDefinition.INSTANCE.getStaticValues();
		} else if (MonthOfYearNumericalAttributeDefinition.INSTANCE.getUUID().equals(attributeDefinitionUuid)) {
			return MonthOfYearNumericalAttributeDefinition.INSTANCE.getStaticValues();
		} else if (SecondOfMinuteAttributeDefinition.INSTANCE.getUUID().equals(attributeDefinitionUuid)) {
			return SecondOfMinuteAttributeDefinition.INSTANCE.getStaticValues();
		} else if (YearAttributeDefinition.INSTANCE.getUUID().equals(attributeDefinitionUuid)) {
			return YearAttributeDefinition.INSTANCE.getStaticValues();
		}
		return null;
	}

	@Override
	public void run(Action action) throws ActionFailureException, ActionParameterException {
		// Do nothing
		return;
	}
	
	@Override
	public List<State> getStates(UUID stateDefinitionUuid) {
		if (ClockStateDefinition.INSTANCE_UUID.equals(stateDefinitionUuid)) {
			return getClockStates();
		}
		return Collections.emptyList();
	}

	private List<State> getClockStates() {
		final List<State> states = new ArrayList<>();
		states.add(new ClockState());	
		return states;
	}

	@Override
	public List<? extends ProviderSettingDefinition<?>> getSettingDefinitions() {
		return null;
	}

	@Override
	public void shutdown() throws Exception {
		executorService.shutdown();
		executorService = null;
		onEventListener = null;
		
		eventDefinitions = null;
		actionDefinitions = null;
		stateDefinitions = null;
	}

}
