package com.oakcity.nicknack.core.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.UUID;

import com.oakcity.nicknack.core.actions.Action.ActionDefinition;
import com.oakcity.nicknack.core.events.Event.EventDefinition;

public class ProviderService {
	
	private static ProviderService service;
    private ServiceLoader<Provider> loader;
    
    private final Map<UUID, EventDefinition> eventDefinitions = new HashMap<UUID, EventDefinition>();
    private final Map<UUID, ActionDefinition> actionDefinitions = new HashMap<UUID, ActionDefinition>();

    private ProviderService() {
        loader = ServiceLoader.load(Provider.class);
    }

    public static synchronized ProviderService getInstance() {
        if (service == null) {
            service = new ProviderService();
        }
        return service;
    }
    
    public Map<UUID, ActionDefinition> getActionDefinitions() {
    	return Collections.unmodifiableMap(actionDefinitions);
    }
    
    public Map<UUID, EventDefinition> getEventDefinitions() {
    	return Collections.unmodifiableMap(eventDefinitions);
    }
    
    public List<ServiceConfigurationError> initializeProviders() {
    	Iterator<Provider> providers = loader.iterator();
    	
    	Provider provider = null;
    	List<ServiceConfigurationError> errors = new ArrayList<ServiceConfigurationError>();
		while (provider == null && providers.hasNext()) {
			try {
				provider = providers.next();
				
				if (provider.getEventDefinitions() != null) {
					for (EventDefinition eventDef : provider.getEventDefinitions()) {
						UUID uuid = eventDef.getUUID();
						if (uuid == null) {
							// Log error.
						} else {
							eventDefinitions.put(uuid, eventDef);
						}
					}
				}
				
				if (provider.getActionDefinitions() != null) {
					for (ActionDefinition actionDef : provider.getActionDefinitions()) {
						UUID uuid = actionDef.getUUID();
						if (uuid == null) {
							// Log error.
						} else {
							actionDefinitions.put(uuid, actionDef);
						}
					}
				}
			} catch (ServiceConfigurationError e) {
	    		errors.add(e);
	    	}
		}
		
		return errors;
    	
    }

}
