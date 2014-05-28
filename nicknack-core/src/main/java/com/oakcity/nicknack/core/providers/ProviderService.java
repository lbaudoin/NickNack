package com.oakcity.nicknack.core.providers;

import java.util.Map;
import java.util.UUID;

import rx.Observable;

import com.oakcity.nicknack.core.actions.Action.ActionDefinition;
import com.oakcity.nicknack.core.events.Event;
import com.oakcity.nicknack.core.events.Event.EventDefinition;

public interface ProviderService {

	public Map<UUID, ActionDefinition> getActionDefinitions();

	public Map<UUID, EventDefinition> getEventDefinitions();
	
	public Observable<Event> getEvents();

}