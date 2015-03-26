package com.github.kmbulebu.nicknack.providers.pushover;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.github.kmbulebu.nicknack.core.actions.Action;
import com.github.kmbulebu.nicknack.core.actions.ActionDefinition;
import com.github.kmbulebu.nicknack.core.actions.ActionFailureException;
import com.github.kmbulebu.nicknack.core.actions.ActionAttributeException;
import com.github.kmbulebu.nicknack.core.events.EventDefinition;
import com.github.kmbulebu.nicknack.core.providers.OnEventListener;
import com.github.kmbulebu.nicknack.core.providers.Provider;
import com.github.kmbulebu.nicknack.core.providers.ProviderConfiguration;
import com.github.kmbulebu.nicknack.core.providers.settings.SettingDefinition;
import com.github.kmbulebu.nicknack.core.states.State;
import com.github.kmbulebu.nicknack.core.states.StateDefinition;
import com.github.kmbulebu.nicknack.providers.pushover.actions.AbstractPushMessageActionDefinition;
import com.github.kmbulebu.nicknack.providers.pushover.actions.PushMessageActionDefinition;

public class PushOverProvider implements Provider {
	
	public static final UUID PROVIDER_UUID = UUID.fromString("ec72c198-1ee8-4fd6-be0c-c10fd365f95a");
	
	private Map<UUID, AbstractPushMessageActionDefinition> actionDefinitions = null;;

	@Override
	public UUID getUuid() {
		return PROVIDER_UUID;
	}

	@Override
	public String getName() {
		return "Pushover";
	}

	@Override
	public String getAuthor() {
		return "NickNack";
	}

	@Override
	public int getVersion() {
		return 0;
	}

	@Override	
	public Collection<EventDefinition> getEventDefinitions() {
		return Collections.emptyList();
	}
	
	@Override
	public Collection<StateDefinition> getStateDefinitions() {
		return Collections.emptyList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection<ActionDefinition> getActionDefinitions() {
		return (Collection) actionDefinitions.values();
	}

	@Override
	public Map<String, String> getAttributeDefinitionValues(UUID attributeDefinitionUuid) {
		return null;
	}

	@Override
	public void run(Action action) throws ActionFailureException, ActionAttributeException {
		// Look it up.
		final AbstractPushMessageActionDefinition actionDef = actionDefinitions.get(action.getAppliesToActionDefinition());
		if (actionDef == null) {
			throw new ActionFailureException("Action is not provided by the PushOver provider. Please open a bug.");
		}
		
		actionDef.run(action);
	}

	@Override
	public void init(ProviderConfiguration configuration, OnEventListener onEventListener) throws Exception {
		actionDefinitions = new HashMap<>();
		actionDefinitions.put(PushMessageActionDefinition.DEF_UUID, new PushMessageActionDefinition());
	}
	
	@Override
	public void shutdown() throws Exception {
		actionDefinitions = null;
	}
	
	@Override
	public List<? extends SettingDefinition<?>> getSettingDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<State> getStates(UUID stateDefinitionUuid) {
		return Collections.emptyList();
	}

}
